package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Story
import retrofit2.Call
import retrofit2.http.*

interface StoryInterface {

    @GET("rpc/stories_by_menu?")
    fun getStoriesByMenu(@Query("MENU_ID") menu_id: Int): Call<List<Story>>

    @POST("stories")
    fun createStory(@Body story: Story): Call<Story>

}