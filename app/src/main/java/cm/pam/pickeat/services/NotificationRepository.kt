package cm.pam.pickeat.services

import cm.pam.pickeat.models.Notification
import cm.pam.pickeat.instances.NotificationInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificationRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var notificationInstances: NotificationInterface = retrofitBuilder.create(NotificationInterface::class.java)

    fun getNotificationsForAnUser(phoneNumber: Long) {
        var getNotifications = notificationInstances.getNotificationsForAnUser(phoneNumber)
        getNotifications.enqueue(object: Callback<List<Notification>> {
            override fun onResponse(
                call: Call<List<Notification>>,
                response: Response<List<Notification>>
            ) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Notification>>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun createNotification(notification: Notification) {
        var createNotification = notificationInstances.createNotification(notification)
        createNotification.enqueue(object: Callback<Notification> {
            override fun onResponse(
                call: Call<Notification>,
                response: Response<Notification>
            ) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Notification>, t: Throwable) {
                val result = t.message
            }

        })
    }
}