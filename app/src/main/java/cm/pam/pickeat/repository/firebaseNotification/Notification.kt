package cm.pam.pickeat.controller.firebaseNotification

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cm.pam.pickeat.R
import cm.pam.pickeat.databinding.ActivityNotificationBinding

import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/myTopic2"

class Notification : AppCompatActivity() {

    val TAG = "Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding  = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_notification)
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstallations.getInstance().id.addOnSuccessListener {
            FirebaseService.token = it
            binding.etToken.setText(it)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding.btnSend.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val message = binding.etMessage.text.toString()
            val recipientToken = binding.etToken.text.toString()

            if(title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                        NotificationData(title, message),
                        recipientToken
                ).also {
                    sendNotification(it)
                    println("----------------------------------------------------------------------------------"+recipientToken)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
                println("--------------------------------------------------------------------------------------------error")
            }
        } catch(e: Exception) {
            println("-----------------------------------------catch---------------------------------------------------error")
            Log.e(TAG, e.toString())

        }
    }
}
