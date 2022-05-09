package cm.pam.pickeat.controller.SendNotificationPack


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
public interface APIService {
    @Headers(
            "Content-Type:application/json",
            "Authorization:key=AAAAisxI2VY:APA91bEzl5TrAC2fv3yK-pDT___-tRWflfjdHfjrSdhnMWS6RyPcnfMKa_M-auOsDuLsy7RJ1dNivlr4HU4bDWc53wvgGNYDW353j48fNwMMsrAiPHjIw4awc84CpYCoROmxx1npa-gT" // Your server key refer to video for finding your server key
    )
    @POST("fcm/send")
    open fun sendNotifcation(@Body body: NotificationSender?): Call<MyResponse?>?

}