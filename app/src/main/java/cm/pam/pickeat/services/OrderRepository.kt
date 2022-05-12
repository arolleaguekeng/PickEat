package cm.pam.pickeat.services

import cm.pam.pickeat.models.Order
import cm.pam.pickeat.instances.OrderInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var orderInstances: OrderInterface = retrofitBuilder.create(OrderInterface::class.java)

    fun createOrder(order: Order) {
        var createOrder = orderInstances.createOrder(order)
        createOrder.enqueue(object: Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if(response.isSuccessful) {
                    val result = response.code()
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun listUserOrders(phoneNumber: Long) {
        var listOrdersForUser = orderInstances.getUserOrders(phoneNumber)
        listOrdersForUser.enqueue(object: Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                val result = t.message
            }
        })
    }
}