package cm.pam.pickeat.services

import cm.pam.pickeat.models.Story
import cm.pam.pickeat.instances.StoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "192.168.137.1:3000/"

class StoryRepository {
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var storyInstance: StoryInterface = retrofitBuilder.create(StoryInterface::class.java)

    fun createStory(story: Story, callback:()->Unit) {
        var createStory = storyInstance.createStory(story)
        createStory.enqueue(object: Callback<Story> {
            override fun onResponse(call: Call<Story>, response: Response<Story>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Story>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun getMenuStories(menu_id: Int, callback:(List<Story>)->Unit) {
        var getStories = storyInstance.getStoriesByMenu(menu_id)
        getStories.enqueue(object: Callback<List<Story>> {
            override fun onResponse(call: Call<List<Story>>, response: Response<List<Story>>) {
                if(response.code() == 200) {
                    val result = response.body()!!
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Story>>, t: Throwable) {
                val result = t.message
            }
        })
    }
}