package cm.pam.pickeat.services


import cm.pam.pickeat.models.User
import cm.pam.pickeat.instances.UserInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {
    //private const val BASE_URL = "http://192.168.43.2:3000/"
    private val BASE_URL = "192.168.137.1:3000/"
    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var userInstance: UserInterface = retrofitBuilder.create(UserInterface::class.java)

    fun createUser(user: User) {
        var createUser = userInstance.createUser(user)
        createUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun getUsers() {
        var getUsers = userInstance.getUsers()
        getUsers.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.code() == 200) {
                    val result = response.body()!!
                } else {
                    val result = response.body()!!
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun getUserByPhoneNumber(phoneNumber: Long) {
        var getUser = userInstance.getUserByPhoneNumber(phoneNumber)
        getUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    val result = response.body()!!
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun getUserByName(name: String) {
        var getUsers = userInstance.getUserByName(name)
        getUsers.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.code() == 200) {
                    val result = response.body()!!
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun getUserProfile(phoneNumber: Long) {
        var getUserProfile = userInstance.getUserProfile(phoneNumber)
        getUserProfile.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    val result = response.body()!!
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                val result = t.message
            }
        })
    }

    fun updateUser(phoneNumber: Long) {
        var updateUser = userInstance.updateUser(phoneNumber)
        updateUser.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    val result = response.code()
                } else {
                    val result = response.errorBody()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                val result = t.message
            }
        })
    }
}