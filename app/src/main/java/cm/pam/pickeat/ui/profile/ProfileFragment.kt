package cm.pam.pickeat.ui.profile

import Publication
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cm.pam.pickeat.FollowActivity
import cm.pam.pickeat.R
import cm.pam.pickeat.Service.adapters.PublicationAdapter
import cm.pam.pickeat.databinding.FragmentProfileBinding
import cm.pam.pickeat.repository.*
import cm.pam.pickeat.ui.sarterApp.Authentification.AuthentificationPhone
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class ProfileFragment : Fragment() {


    //Test Values
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    var publications = arrayListOf<Publication>()

    lateinit var binding: FragmentProfileBinding
    private val FILE_NAME = "photo.jpg"
    private var selectedItem: Uri? = null
    var selectedBitmap: Bitmap? = null
    lateinit var mDialogView: View
    val REQUEST_CODE_CAMERA = 200
    val REQUEST_CODE_GALLERY = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.followers.setOnClickListener{
            Intent(it.context, FollowActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.abonnement.setOnClickListener {
            Intent(it.context, FollowActivity::class.java).also {
                startActivity(it)
            }
        }

        val recycleView = binding.publicationList
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleView!!.layoutManager = mLayoutManager
        val mAdapter = PublicationAdapter(publications)
        recycleView!!.adapter = mAdapter
        println(mAdapter.itemCount)
        binding.phonenumber.text = currentPhone

        val toAppBarr = binding.topAppBar
        toAppBarr.setOnMenuItemClickListener{menuItemListener(it)}
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu is MenuBuilder) (menu as MenuBuilder).setOptionalIconsVisible(true)
        inflater.inflate(R.menu.profile_option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun menuItemListener(item: MenuItem): Boolean{
        return when (item.itemId) {
            R.id.logout -> {
                auth = FirebaseAuth.getInstance()
                // navigate to settings screen
                if (auth.currentUser != null) {
                    auth.signOut()
                    startActivity(Intent(activity, AuthentificationPhone::class.java))
                }
                Toast.makeText(activity, "$currentPhone", Toast.LENGTH_SHORT).show()
                println("Logout!")
                true
            }
            R.id.edit -> {
                // save profile changes
                edit()
                Toast.makeText(activity, "Edit user!", Toast.LENGTH_SHORT).show()
                println("Edit!")
                true
            }
            else -> false
        }
    }

    fun readFileDirectlyAsText(fileName: String): String
            = File(fileName).readText(Charsets.UTF_8)

    private fun edit(){
        mDialogView = LayoutInflater.from(context).inflate(R.layout.layout_edit, null)
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setView(mDialogView)
        var mAlertDialog = mBuilder.show()

        var name = mDialogView.findViewById<TextView>(R.id.newUsername)
        var photo = mDialogView.findViewById<ImageView>(R.id.user_profile)

        mDialogView.findViewById<Button>(R.id.save_profile).setOnClickListener {

            if(!name.text.isEmpty()){
                newUserName = name.text.toString()
                mAlertDialog.dismiss()
            }
            Toast.makeText(context, "Chose an image !", Toast.LENGTH_LONG*7)
        }

        photo.setOnClickListener {

            val popupMenu = PopupMenu(context, photo)
            popupMenu.menuInflater.inflate(R.menu.popup_photo_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menuItem_openCamera -> capturePhoto()
                    R.id.menuItem_openGallery -> openGalleryForImage()
                    else ->true
                }
            })

            Toast.makeText(context, "Unable to open camera", Toast.LENGTH_SHORT).show()
            popupMenu.show()
        }

        mDialogView.findViewById<Button>(R.id.cancel_profile).setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    fun capturePhoto(): Boolean {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA)
        return true
    }
    private fun openGalleryForImage(): Boolean {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null){
            mDialogView.findViewById<ImageView>(R.id.user_profile).setImageBitmap(data.extras!!.get("data") as Bitmap)
            binding.circleImageView.setImageBitmap(data.extras!!.get("data") as Bitmap)
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GALLERY){
            mDialogView.findViewById<ImageView>(R.id.user_profile).setImageURI(data?.data) // handle chosen image
            binding.circleImageView.setImageURI(data?.data)
        }
    }
}