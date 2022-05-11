package cm.pam.pickeat.repository
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import java.io.*

var TIME_OUT:Long = 3000
val RC_STORAGE_WRITE_PERMS = 100
lateinit var currentPhone: String
lateinit var auth: FirebaseAuth
lateinit var newUserName: String
lateinit var mStorageRef: StorageReference
lateinit var mODatabaseRef: DatabaseReference

fun showDialog(context: Context) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage("The message here")
    dialogBuilder.setPositiveButton("Done",
        DialogInterface.OnClickListener { dialog, whichButton -> })
    val b = dialogBuilder.create()
    b.show()
}

fun getExtension(uri: Uri, activity: Activity): String? {
    var cr: ContentResolver = activity.contentResolver
    var mime: MimeTypeMap = MimeTypeMap.getSingleton()
    return mime.getExtensionFromMimeType(cr.getType(uri))
}

fun createOrGetFile(destination: File, fileName: String, folderName: String): File {
    var folder = File(destination, folderName)
    return File(folder, fileName)
}

fun readOnFile(context: Context, file: File): String {
    var result = ""
    if (file.exists()) {
        var br: BufferedReader
        try {
            br = BufferedReader(FileReader(file))
            try {
                var sb: StringBuilder = StringBuilder()
                var line: String = br.readLine()
                while (line != null) {
                    sb.append(line)
                    sb.append("\n")
                    line = br.toString()
                }
                result = sb.toString()
            } finally {
                br.close()
            }
        } catch (e: IOException) {
            //Toast.makeText(context, context.getString(com.google.android.material.R.string.error_happened), Toast.LENGTH_SHORT).show()
        }
    }
    return result
}

fun writeOnFile(context: Context, text: String, file: File) {
    try {
        file.parentFile.mkdirs()
        var fos = FileOutputStream(file)
        var w = BufferedWriter(OutputStreamWriter(fos))
        try {
            w.write(text)
            w.flush()
            fos.fd.sync()
        } finally {
            w.close()
            //Toast.makeText(context, context.getString(R.string.saved), Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        //Toast.makeText(context, context.getString(R.string.error_happened), Toast.LENGTH_SHORT).show()
    }
}

fun getTextFromStorage(
    rootDestination: File,
    context: Context,
    fileName: String,
    folderName: String
): String {
    var file = createOrGetFile(rootDestination, fileName, folderName)
    return readOnFile(context, file)
}

fun setTextInStorage(
    rootDestination: File,
    context: Context,
    fileName: String,
    folderName: String,
    text: String
) {
    var file = createOrGetFile(rootDestination, fileName, folderName)
    writeOnFile(context, text, file)
}

fun isExternalStorageWritable(): Boolean {
    var state = Environment.getExternalStorageState()
    return (Environment.MEDIA_MOUNTED == state)
}

fun isExternalStorageReadable(): Boolean {
    var state = Environment.getExternalStorageState()
    return (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state)
}
