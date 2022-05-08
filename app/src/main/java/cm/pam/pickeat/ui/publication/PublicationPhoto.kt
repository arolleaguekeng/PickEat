package cm.pam.pickeat.ui.publication

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import cm.pam.pickeat.R
import java.io.File

private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File

class PublicationPhoto: AppCompatActivity() {


    lateinit var spinner: Spinner
    private var selectedItem: Uri? = null
    private var selectedBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_publication)

        val categories = arrayOf("Mexican", "Traditional cameroonian", "Chineese", "Arab")
        spinner = findViewById(R.id.categoriesSpiner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinner.adapter = adapter

        var imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            var popup = PopupMenu(this, imageButton)
            popup.inflate(R.menu.layout_popup_photo_menu)
        }

    }

    private fun captureImage (view: View?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)
        var imageView = findViewById<ImageView>(R.id.imageView)

        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)
        val fileProvider = FileProvider.getUriForFile(this, "edu.standard.rkpandey.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        if (takePictureIntent.resolveActivity(this.packageManager) != null) {
            imageView.setImageBitmap(null)
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }
        else {
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDir = getExternalFilesDir((Environment.DIRECTORY_PICTURES))
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var imageView = findViewById<ImageView>(R.id.imageView)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //val takenImage = data?.extras?.get("data") as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)

            imageView.setImageBitmap(takenImage)
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedItem = data.data
        }
        try {
            if(selectedItem != null) {
                if(Build.VERSION.SDK_INT >= 20) {
                    val source = ImageDecoder.createSource(contentResolver, selectedItem!!)
                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                    imageView.setImageBitmap(selectedBitmap)
                } else {
                    selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedItem)
                    imageView.setImageBitmap(selectedBitmap)
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}