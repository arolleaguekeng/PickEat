package cm.pam.pickeat.services

import cm.pam.pickeat.instances.LocationInterface
import cm.pam.pickeat.models.Location
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var locationInstances: LocationInterface = retrofitBuilder.create(LocationInterface::class.java)

    fun getLocations() {
        var getLocations = locationInstances.getLocations()
        getLocations.enqueue(object: Callback<List<Location>> {
            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun getLocationByName(quarter: String) {
        var getLocations = locationInstances.getLocationByQuarterName(quarter)
        getLocations.enqueue(object: Callback<Location> {
            override fun onResponse(call: Call<Location>, response: Response<Location>) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun createLocation(Location: Location) {
        var createLocation = locationInstances.createLocation(Location)
        createLocation.enqueue(object: Callback<Location> {
            override fun onResponse(call: Call<Location>, response: Response<Location>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                val result = t.message
            }

        })
    }
}