package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Category
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryInterface {

    @GET("categories")
    fun getCategories(): Call<List<Category>>

    @POST("categories")
    fun createCategories(@Body category: Category): Call<Category>

}