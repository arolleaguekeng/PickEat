package cm.pam.pickeat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import cm.pam.pickeat.controller.AuthentificationController
import cm.pam.pickeat.controller.auth
import cm.pam.pickeat.controller.currentPhone
import cm.pam.pickeat.databinding.ActivityAuthentificationPhoneBinding
import cm.pam.pickeat.ui.home.HomeFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthentificationPhone : AppCompatActivity() {
    lateinit var binding: ActivityAuthentificationPhoneBinding
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val pattern = Regex("[6][7,5,8,9]\\d{7}")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAuthentificationPhoneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val login = binding.loginBtn

        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        login.setOnClickListener{
            login()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, HomeFragment::class.java))
                finish()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Log.d("TAG", "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token

                val intent = Intent(applicationContext, AuthetificationCode::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
            }
        }
    }

    private fun login(){
        val mobileNumber = binding.phoneNumber
        var number = mobileNumber.text.toString().trim()
        println("$number")
        currentPhone = number
        if(!number.matches(pattern)){

            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_LONG*2).show()
        }
        else if(number.isNotEmpty()){
            number = "+237$number"
            sendVerificationCode(number)
        }
        else{
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerificationCode(phoneNumber: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}