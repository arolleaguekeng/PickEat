package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Publication
import retrofit2.Call
import retrofit2.http.*

interface PublicationInterface {

    @GET("publications")
    fun getPublications(): Call<List<Publication>>

    @GET("rpc/list_publications_by_user?")
    fun getUserPublications(@Query("PHONE_NUMBER") phoneNumber: Long): Call<List<Publication>>

    @POST("publications")
    @Headers("Content-Type:application/json")
    fun createPublication(@Body publication: Publication): Call<Publication>

    @PATCH("publications?phoneNumber=eq.{phoneNumber}")
    @Headers("Content-Type:application/json")
    fun updatePublication(@Path("phoneNumber") phoneNumber: Long): Call<Publication>

}