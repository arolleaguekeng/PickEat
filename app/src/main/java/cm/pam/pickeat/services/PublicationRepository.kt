package cm.pam.pickeat.services

import cm.pam.pickeat.models.Publication
import cm.pam.pickeat.instances.PublicationInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PublicationRepository {
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var publicationInstances: PublicationInterface = retrofitBuilder.create(PublicationInterface::class.java)

    fun createPublication(publication: Publication) {
        var createPublication = publicationInstances.createPublication(publication)
        createPublication.enqueue(object: Callback<Publication> {
            override fun onResponse(call: Call<Publication>, response: Response<Publication>) {
                if(response.isSuccessful) {
                    val result = response.code()
                }
            }

            override fun onFailure(call: Call<Publication>, t: Throwable) {
                val result = t.message
            }

        })
    }

    fun getUserPublications(phoneNumber: Long) {
        var getUserPublications = publicationInstances.getUserPublications(phoneNumber)
        getUserPublications.enqueue(object: Callback<List<Publication>> {
            override fun onResponse(
                call: Call<List<Publication>>,
                response: Response<List<Publication>>
            ) {
                if(response.code() == 200) {
                    val result = response.body()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<Publication>>, t: Throwable) {
                val result = t.message
            }

        })
    }
}