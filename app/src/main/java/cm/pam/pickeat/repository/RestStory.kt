package cm.pam.pickeat.repository

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cm.pam.pickeat.api.APIStory
import cm.pam.pickeat.model.StoryModel
import cm.pam.pickeat.ui.story.StoriesActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestStory {
    fun addStory(story: StoryModel, onResult: (Any?) -> Unit){
        val retrofit = StoriesActivity.ServiceBuilder.buildService(APIStory::class.java)
        retrofit.create(story).enqueue(
            object : Callback<StoryModel> {
                override fun onFailure(call: Call<StoryModel>, t: Throwable) {
                    onResult(t.message)
                }
                override fun onResponse(call: Call<StoryModel>, response: Response<StoryModel>) {
                    val result = response.body()
                    onResult(response.code())
                    println("------------->${response.message()}")
                }
            }
        )
    }

    fun getStory(onResult: (List<StoryModel>?) -> Unit){
        val retrofit = StoriesActivity.ServiceBuilder.buildService(APIStory::class.java)
        retrofit.get().enqueue(
            object : Callback<List<StoryModel>> {
                override fun onResponse(call: Call<List<StoryModel>>, response: Response<List<StoryModel>>) {
                    var stories = response.body()
                    if (stories != null) {
                        onResult(stories)
                        println(response.body())
                    }
                }
                override fun onFailure(call: Call<List<StoryModel>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}