package cm.pam.pickeat.instances

import cm.pam.pickeat.models.User
import retrofit2.Call
import retrofit2.http.*

interface UserInterface {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users?name=fts.{name}")
    fun getUserByName(@Path("name") name: String): Call<List<User>>

    @GET("users?phoneNumber=eq.{phoneNumber}")
    fun getUserByPhoneNumber(@Path("phoneNumber") phoneNumber: Long): Call<User>

    @GET("rpc/return_profil_by_user?")
    fun getUserProfile(@Query("NUM") phoneNumber: Long): Call<User>

    @POST("users")
    @Headers("Content-Type:application/json")
    fun createUser(@Body params: User): Call<User>

    @PATCH("users?phoneNumber=eq.{phoneNumber}")
    @Headers("Content-Type:application/json")
    fun updateUser(@Path("phoneNumber") phoneNumber: Long): Call<User>

}