package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Order
import retrofit2.Call
import retrofit2.http.*

interface OrderInterface {

    @GET("rpc/user_orders?")
    fun getUserOrders(@Query("PHONE_NUMBER") phoneNumber: Long): Call<List<Order>>

    @POST("orders")
    fun createOrder(@Body order: Order): Call<Order>

}