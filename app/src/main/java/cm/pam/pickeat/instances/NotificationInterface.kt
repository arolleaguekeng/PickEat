package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Notification
import retrofit2.Call
import retrofit2.http.*

interface NotificationInterface {

    @GET("users?phoneNumber=eq.{phoneNumber}")
    fun getNotificationsForAnUser(@Path("phoneNumber") phoneNumber: Long): Call<List<Notification>>

    @POST("users")
    @Headers("Content-Type:application/json")
    fun createNotification(@Body params: Notification): Call<Notification>

    @PATCH("users?phoneNumber=eq.{phoneNumber}")
    @Headers("Content-Type:application/json")
    fun updateUser(@Path("phoneNumber") phoneNumber: Long): Call<Notification>

}