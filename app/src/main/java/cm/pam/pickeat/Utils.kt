package cm.pam.pickeat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult
import cm.pam.pickeat.model.UserModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.lang.reflect.Method


val REQUEST_CODE_CAMERA = 200
val REQUEST_CODE_GALLERY = 100
var currentUser: UserModel? = UserModel("Test")

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("RestrictedApi")
fun takePhoto(activity: Activity, view: FloatingActionButton){
    val popupMenu = PopupMenu(activity.baseContext, view)
    popupMenu.menuInflater.inflate(R.menu.image_menu, popupMenu.menu)

    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
        when(it.itemId) {
            R.id.camera -> capturePhoto(activity)
            R.id.gallery -> openGalleryForImage(activity)
            else ->true
        }
    })
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
        popupMenu.setForceShowIcon(true)
    }else{
        try{
            val fields = popupMenu.javaClass.declaredFields

            for(field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field[popupMenu]
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons: Method = classPopupHelper.getMethod(
                        "setForceShowIcon",
                        Boolean::class.javaPrimitiveType
                    )
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        }catch (e: Exception){
            Toast.makeText(activity, "${e.message}", Toast.LENGTH_SHORT*1000).show()
        }
    }
    popupMenu.show()
}

fun capturePhoto(activity: Activity): Boolean {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(activity, cameraIntent, REQUEST_CODE_CAMERA, null)
    return true
}

fun openGalleryForImage(activity: Activity): Boolean {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(activity, intent, REQUEST_CODE_GALLERY, null)
    return true
}

fun bytearrayToBitmap(image: ByteArray?): Bitmap? {
    var bitmap: Bitmap? = null
    if (image != null) {
        val options = BitmapFactory.Options()
        bitmap = BitmapFactory.decodeByteArray(
            image,
            0,
            image.size,
            options
        ) //Convert bytearray to bitmap
        //for performance free the memmory allocated by the bytearray and the bl
    }
    return bitmap
}

fun Bitmap.toBase64(): String? {
    if(this!=null){
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG,60,this)
            var byteArray = this.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }
    return null
}

fun String?.toBitmap(): Bitmap?{
    var imageBytes = Base64.decode(this, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

private fun makeViewFullWidth(view: View, activity: Activity?) {
    val point = Point()
    // point will be populated with screen width and height
    activity?.windowManager?.defaultDisplay?.getSize(point)
    val param = view.layoutParams
    param.width = point.x
    param.height = point.y
    view.layoutParams = param
}
