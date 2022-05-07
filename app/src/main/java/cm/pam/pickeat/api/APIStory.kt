package cm.pam.pickeat.api

import cm.pam.pickeat.model.StoryModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIStory {
   @Headers("Content-Type: application/json")
   @POST("Story")
   fun create(@Body story: StoryModel): Call<StoryModel>

   @GET("Story")
   fun get(): Call<List<StoryModel>>
}

