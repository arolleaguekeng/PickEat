package cm.pam.pickeat.repository

import cm.pam.pickeat.api.APIStory
import cm.pam.pickeat.model.Story
import cm.pam.pickeat.ui.story.StoriesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestStory {
    fun addStory(story: Story, onResult: (Any?) -> Unit){
        val retrofit = StoriesActivity.ServiceBuilder.buildService(APIStory::class.java)
        retrofit.create(story).enqueue(
            object : Callback<Story> {
                override fun onFailure(call: Call<Story>, t: Throwable) {
                    onResult(t.message)
                }
                override fun onResponse(call: Call<Story>, response: Response<Story>) {
                    val result = response.body()
                    onResult(response.code())
                    println("------------->${response.message()}")
                }
            }
        )
    }

    fun getStory(onResult: (List<Story>?) -> Unit){
        val retrofit = StoriesActivity.ServiceBuilder.buildService(APIStory::class.java)
        retrofit.get().enqueue(
            object : Callback<List<Story>> {
                override fun onResponse(call: Call<List<Story>>, response: Response<List<Story>>) {
                    var stories = response.body()
                    if (stories != null) {
                        onResult(stories)
                        println(response.body())
                    }
                }
                override fun onFailure(call: Call<List<Story>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}