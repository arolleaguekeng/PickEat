package cm.pam.pickeat.services

import cm.pam.pickeat.models.Category
import cm.pam.pickeat.instances.CategoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var categoryInstances: CategoryInterface = retrofitBuilder.create(CategoryInterface::class.java)

    fun createCategory(category: Category) {
        var createCategory = categoryInstances.createCategories(category)
        createCategory.enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun getCategories() {
        var getCategories = categoryInstances.getCategories()
        getCategories.enqueue(object: Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                val result = t.message
            }

        })
    }
}