package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Location
import retrofit2.Call
import retrofit2.http.*

interface LocationInterface {

    @GET("locations")
    fun getLocations(): Call<List<Location>>

    @GET("locations?quater=fts.{name}")
    fun getLocationByQuarterName(@Path("quarter") name: String): Call<Location>

    @POST("locations")
    @Headers("Content-Type:application/json")
    fun createLocation(@Body params: Location): Call<Location>

}