package cm.pam.pickeat

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cm.pam.pickeat.controller.SendNotificationPack.*
import com.example.chatmekotlin.Util.AppUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.installations.FirebaseInstallations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class SendNotif : AppCompatActivity() {
    private lateinit var UserTB: EditText
    private lateinit var Title:EditText
    private lateinit var Message:EditText
    private lateinit var send: Button
    private lateinit var apiService: APIService
    private var hisId: String? = null
    private var chatId: String? = null
    private var myName: String? = null
    private lateinit var appUtil: AppUtil
    private lateinit var myId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notif)
        UserTB=findViewById(R.id.UserID)
        Title=findViewById(R.id.Title)
        Message=findViewById(R.id.Message)
        send=findViewById(R.id.button)
        apiService = Client.getClient("https://fcm.googleapis.com/fcm/send").create(APIService::class.java)
        send.setOnClickListener(View.OnClickListener {
            FirebaseDatabase.getInstance().getReference().child("Tokens").child(UserTB.getText().toString().trim()).child("token").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var usertoken:String=dataSnapshot.getValue(String::class.java).toString()
                    sendNotification(usertoken, Title.text.toString().trim(),Message.text.toString().trim())
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        })
        UpdateToken()

        appUtil = AppUtil()
        myId = appUtil.getUID()!!
        //getToken(Message.text.toString())
    }

    private fun UpdateToken(){
        var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var refreshToken:String= FirebaseInstallations.getInstance().getToken(true).toString()
        var token:Token=Token(refreshToken)
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).setValue(token)
    }
    private fun sendNotification(usertoken:String,title: String,message: String){
        var data=Data(title,message)
        var sender:NotificationSender= NotificationSender(data,usertoken)
        apiService.sendNotifcation(sender)!!.enqueue(object : Callback<MyResponse?> {

            override fun onResponse(call: Call<MyResponse?>, response: Response<MyResponse?>) {
                if (response.code() === 200) {
                    println("-------------------------------------------------**********responce*********-------------------------------------")
                    if (response.body()!!.success !== 1) {
                        Toast.makeText(this@SendNotif, "Failed ", Toast.LENGTH_LONG).show()
                        println("---------------------------------------------------------------Not send !!------------------------------------------------------")
                    }
                    else
                    {
                         println("---------------------------------------------------------------sending sucessfully !!------------------------------------------------------")
                    }
                }
                else println("---------------------------------------------------------------error------------------------------------------------------")
            }

            override fun onFailure(call: Call<MyResponse?>, t: Throwable?) {

            }
        })
    }


//    private fun getToken(message: String) {
//        val databaseReference = FirebaseDatabase.getInstance().getReference().child("Tokens").child(UserTB.getText().toString().trim()).child("token")
//        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val token = snapshot.child("token").value.toString()
//
//                    val to = JSONObject()
//                    val data = JSONObject()
//
//                    data.put("hisId", myId)
//                    data.put("title", myName)
//                    data.put("message", message)
//                    data.put("chatId", chatId)
//
//                    to.put("to", token)
//                    to.put("data", data)
//                    sendNotification(to)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//    }




//    private fun sendNotification(to: JSONObject) {
//
//        val request: JsonObjectRequest = object : JsonObjectRequest(
//            Method.POST,
//            AppConstants.NOTIFICATION_URL,
//            to,
//            Response.Listener { response: JSONObject ->
//
//                Log.d("TAG", "onResponse: $response")
//            },
//            Response.ErrorListener {
//
//                Log.d("TAG", "onError: $it")
//            }) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val map: MutableMap<String, String> = HashMap()
//
//                map["Authorization"] = "key=" + AppConstants.SERVER_KEY
//                map["Content-type"] = "application/json"
//                return map
//            }
//
//            override fun getBodyContentType(): String {
//                return "application/json"
//            }
//        }
//
//        val requestQueue = Volley.newRequestQueue(this)
//        request.retryPolicy = DefaultRetryPolicy(
//            30000,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        )
//
//        requestQueue.add(request)
//
//    }
}
