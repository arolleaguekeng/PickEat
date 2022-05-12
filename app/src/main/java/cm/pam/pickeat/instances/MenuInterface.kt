package cm.pam.pickeat.instances

import cm.pam.pickeat.models.Menu
import retrofit2.Call
import retrofit2.http.*

interface MenuInterface {

    @GET("rpc/menu_details?")
    fun getMenus(@Query("CATEGORY_ID") category: Int): Call<List<Menu>>

    @POST("menus")
    fun createMenu(@Body menu: Menu): Call<Menu>

}