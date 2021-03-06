package cm.pam.pickeat

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cm.pam.pickeat.repository.SendNotificationPack.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.installations.FirebaseInstallations
import retrofit2.Callback


class SendNotif : AppCompatActivity() {
    private lateinit var UserTB: EditText
    private lateinit var Title:EditText
    private lateinit var Message:EditText
    private lateinit var send: Button
    private lateinit var apiService: APIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notif)
        UserTB=findViewById(R.id.UserID)
        Title=findViewById(R.id.Title)
        Message=findViewById(R.id.Message)
        send=findViewById(R.id.button)
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService::class.java)
        send.setOnClickListener(View.OnClickListener {
            FirebaseDatabase.getInstance().getReference().child("Tokens").child(UserTB.getText().toString().trim()).child("token").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var usertoken:String=dataSnapshot.getValue(String::class.java).toString()
                    sendNotification(usertoken, Title.text.toString().trim(),Message.text.toString().trim())
                    Toast.makeText(this@SendNotif, " Error : an Error occured ! ", Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        })
        UpdateToken()
    }

    private fun UpdateToken(){
        var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var refreshToken:String= FirebaseInstallations.getInstance().toString()
        var token: Token = Token(refreshToken)
//        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).setValue(token)
    }
    private fun sendNotification(usertoken:String,title: String,message: String){
        var data= Data(title,message)
        var sender: NotificationSender = NotificationSender(data,usertoken)
        apiService.sendNotifcation(sender)!!.enqueue(object : Callback<MyResponse?> {
            override fun onResponse(
                call: retrofit2.Call<MyResponse?>,
                response: retrofit2.Response<MyResponse?>
            ) {
                if (response.code() === 200) {
                    if (response.body()!!.success !== 1) {
                        Toast.makeText(this@SendNotif, "Failed ", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<MyResponse?>, t: Throwable) {
                Toast.makeText(this@SendNotif, " Error : an Error occured ! ", Toast.LENGTH_LONG).show()
            }


        })
    }
}
