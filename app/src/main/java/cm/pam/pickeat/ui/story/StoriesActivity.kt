package cm.pam.pickeat.ui.story

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import cm.pam.pickeat.*
import cm.pam.pickeat.model.MenuModel
import cm.pam.pickeat.model.StoryModel
import cm.pam.pickeat.repository.MenuRepository
import cm.pam.pickeat.repository.RestStory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.GsonBuilder
import jp.shts.android.storiesprogressview.StoriesProgressView
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class StoriesActivity : AppCompatActivity(), StoriesProgressView.StoriesListener {

    lateinit var storiesProgressView: StoriesProgressView
    lateinit var imageView: ImageView
    lateinit var button: FloatingActionButton
    lateinit var restStory: RestStory
    lateinit var selectedMenu: MenuRepository
    var images: ArrayList<Bitmap> = ArrayList()
    var pressTime: Long = 0L
    var limit: Long = 500L
    var counter: Int = 0
    val  onTouchListener: View.OnTouchListener =  object : View.OnTouchListener{

        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            when(event?.action){
                MotionEvent.ACTION_DOWN ->{
                    pressTime = System.currentTimeMillis()
                    storiesProgressView!!.pause();
                    return false
                }
                MotionEvent.ACTION_UP ->{
                    var now: Long = System.currentTimeMillis()
                    storiesProgressView!!.resume()
                    return limit < now - pressTime
                }
            }
            return false
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        selectedMenu = intent.getParcelableExtra<MenuRepository>("menu")!!
        restStory = RestStory()

        imageView = findViewById(R.id.image)
        storiesProgressView = findViewById(R.id.stories)
        button = findViewById(R.id.buttonAddStory)

        button.setOnClickListener {
            takePhoto(this, button)
            if (images.size != 0) {
                storiesProgressView.pause()
            }
        }
        playStory()
    }

    private fun getStory(stories: List<StoryModel>){
        images = ArrayList()
        stories.forEach {
            if(it.menuId == selectedMenu.menuId)
                images.add(it.image!!.toBitmap()!!)
        }
    }
    private fun playStory(){
        restStory.getStory { st -> getStory(st!!) }
        if(images.size!=0) {

            findViewById<RelativeLayout>(R.id.nothing).visibility = View.GONE
            counter = 0
            storiesProgressView.setStoriesCount(images.size)
            storiesProgressView.setStoryDuration(3000L)
            storiesProgressView.setStoriesListener(this)
            storiesProgressView.startStories()
            imageView.setImageBitmap(images[counter])

            Glide.with(this)

            val reverse = findViewById<View>(R.id.reverse)
            reverse.setOnClickListener{
                storiesProgressView.reverse()
            }
            reverse.setOnTouchListener(onTouchListener)

            val skip = findViewById<View>(R.id.skip)
            skip.setOnClickListener{
                storiesProgressView.skip()
            }
            skip.setOnTouchListener(onTouchListener)
        }else
            findViewById<RelativeLayout>(R.id.nothing).visibility = View.VISIBLE

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var bitmap: Bitmap? = null
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null){
            bitmap = data.extras!!.get("data") as Bitmap
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GALLERY){
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data?.data)
        }
        if(bitmap!=null){

            saveStory(bitmap.toBase64())
        }
        if(images.size>1)
            storiesProgressView.destroy()

        thread{
            playStory()
        }
    }

    override fun onNext() {
        Glide.with(this)
            .load(images[++counter]).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun onPrev() {
        Toast.makeText(this, "prev", Toast.LENGTH_LONG*20)
        if((counter -1)<0)
            return
        Glide.with(this)
            .load(images[--counter]).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun onComplete() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun saveStory(bitmap: String?){
        try{
            if(bitmap!=null){
                var story = StoryModel(
                    currentUser!!.phoneNumber,
                    selectedMenu!!.menuId,
                    0,
                    Date(),
                    bitmap,
                )
                var gson = GsonBuilder().setPrettyPrinting().create()
                var prettyString = gson.toJson(story)

                restStory.addStory(story){
                    Toast.makeText(this, "${story}", Toast.LENGTH_LONG*10000).show()
                    println(prettyString)
                }
            }
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG*10000).show()
        }
    }

    object ServiceBuilder {

        private val dispatcher = Dispatcher()
        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.137.1:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T {
            var service = retrofit.create(service)
            return service
        }
    }
}