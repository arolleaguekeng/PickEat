package cm.pam.pickeat.services

import cm.pam.pickeat.models.Menu
import cm.pam.pickeat.instances.MenuInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var menuInstances: MenuInterface = retrofitBuilder.create(MenuInterface::class.java)

    fun getMenusFromCategory(id: Int) {
        var getMenus = menuInstances.getMenus(id)
        getMenus.enqueue(object: Callback<List<Menu>> {
            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun createMenu(menu: Menu) {
        var createMenu = menuInstances.createMenu(menu)
        createMenu.enqueue(object: Callback<Menu> {
            override fun onResponse(call: Call<Menu>, response: Response<Menu>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Menu>, t: Throwable) {
                val result = t.message
            }

        })
    }
}