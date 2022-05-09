package cm.pam.pickeat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cm.pam.pickeat.databinding.ActivityAuthetificationCodeBinding
import cm.pam.pickeat.repository.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class AuthetificationCode : AppCompatActivity() {
    lateinit var binding: ActivityAuthetificationCodeBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAuthetificationCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("users")
        mODatabaseRef = FirebaseDatabase.getInstance().getReference("users")

        val storedVerificationId = intent.getStringExtra("storedVerificationId")
        val verify = binding.confirmBtn
        val otpGiven = binding.otp

        //Save the current phone number for futures usages
        val file = File("phone")

        verify.setOnClickListener{
            val otp = otpGiven.text.toString().trim()
            if(otp.isNotEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)


            }
            else{
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                    //var file = File("PhoneNumber.txt")

                    finish()
                    println("ok")
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == RC_STORAGE_WRITE_PERMS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    private fun checkWriteExternalStoragePermission(): Boolean {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Array<String>(1){ Manifest.permission.WRITE_EXTERNAL_STORAGE },
                RC_STORAGE_WRITE_PERMS
            )
            return true
        }
        return false
    }
}