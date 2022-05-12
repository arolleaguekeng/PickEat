package cm.pam.pickeat.ui.friend.list

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.FileUtils
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.MainActivity
import cm.pam.pickeat.R
import cm.pam.pickeat.Service.adapters.FollowAdapter
import cm.pam.pickeat.currentUser
import cm.pam.pickeat.model.Follower
import cm.pam.pickeat.model.User
import cm.pam.pickeat.repository.ItemClickListener
import cm.pam.pickeat.ui.ProfileFriend
import java.time.Instant
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FriendListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendListFragment : Fragment(), ItemClickListener {
    // TODO: Rename and change types of parameters
    lateinit var recyclerViewFollow: RecyclerView
    lateinit var followAdapter: FollowAdapter

    companion object {
        var follow = false

        @RequiresApi(Build.VERSION_CODES.O)
        var FollowList = mutableListOf(Follower(300599, 68943085, Date.from(Instant.now())),
            Follower(300599, 45568494, Date.from(Instant.now())),
            Follower(300599, 890088, Date.from(Instant.now()))
        )

        var UserList = mutableListOf(User(68943085, "toroto", R.drawable.harden, 400.05, 1),
            User(45568494, "ALEXIS", R.drawable.b3, 400.05, 1),
            User(890088, "asta", R.drawable.asta, 500.0, 1)
        )
        var CurrentFollow: User? = null
        var CurrentUser = User(300599, "Morgan", R.drawable.asta, 500.0, 1)
    }










    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)

        var swiper : androidx.swiperefreshlayout.widget.SwipeRefreshLayout = view.findViewById(R.id.SwipeRefresh)

        swiper.setOnRefreshListener(){
            swiper.isRefreshing = false

            var list = mutableListOf<User>()
            for(i in 0 until UserList.size)
            {
                list.add(UserList[i])
            }


            followAdapter.reload(list)

        }



        recyclerViewFollow = view.findViewById(R.id.RecycleViewFollowers)

        followAdapter = FollowAdapter(UserList,  this)

        recyclerViewFollow.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = followAdapter
        }

        return view

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onButtonClicked(user: User) {
        Log.e("Data", user.toString())
        var position: Int= 0



        if (follow)
        {

            for(i in 0..FollowList.lastIndex){

                if(user.phoneNumber == FollowList[i].followedId && CurrentUser.phoneNumber == FriendListFragment.FollowList[i].followerId) {
                    position = i
                    follow = true
                    break
                }

            }
            FollowList?.add(Follower(CurrentUser.phoneNumber, user.phoneNumber, Date.from(Instant.now())))
            FollowList?.removeAt(position)
            //user.notifications?.add(Notification(CurrentUser.image, "Hello ${user.fullname} user ${CurrentUser.fullname} follow you", "2PM"))
        }
        else
        {
            for(i in 0..FollowList.lastIndex){

                if(user.phoneNumber == FollowList[i].followedId && CurrentUser.phoneNumber == FriendListFragment.FollowList[i].followerId) {
                    position = i
                    follow = false
                    break
                }

            }

            FollowList?.removeAt(position)
        }

    }


    override fun onItemClicked(user: User, view: View)
    {
        //Toast.makeText(view.context, "echo", Toast.LENGTH_SHORT).show()
        CurrentFollow = user
        var intent = Intent(view.context, ProfileFriend::class.java)
        startActivity(intent)

    }









}

