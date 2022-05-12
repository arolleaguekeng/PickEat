package cm.pam.pickeat.ui.friend.list

import android.content.Intent
import android.os.Build
import android.os.Bundle
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

        var CurrentUser = User(300599, "Morgan", R.drawable.asta, 500.0, 1)
    }







    var UserList = mutableListOf(User(68943085, "toroto", R.drawable.harden, 400.05, 1),
                                User(45568494, "ALEXIS", R.drawable.b3, 400.05, 1),
                                User(890088, "asta", R.drawable.asta, 500.0, 1)
                                )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)



        recyclerViewFollow = view.findViewById(R.id.RecycleViewFollowers)

        followAdapter = FollowAdapter(UserList, this)

        recyclerViewFollow.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = followAdapter
        }

        return view

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onButtonClicked(user: User) {
        Log.e("Data", user.toString())

        var j : Int = 0

        var position: Int = 0



        while (j<FollowList.lastIndex){
            if(user.phoneNumber == FollowList[j].followedId && CurrentUser.phoneNumber == FollowList[j].followerId){
                follow = true;
                position = j
            }
            j++
        }
        if (follow) {
            follow = false
            FollowList?.removeAt(position)
            //user.notifications?.add(Notification(CurrentUser.image, "Hello ${user.fullname} user ${CurrentUser.fullname} follow you", "2PM"))
        } else {
            follow = true
            FollowList?.add(Follower(CurrentUser.phoneNumber, user.phoneNumber, Date.from(Instant.now())))
        }

    }


    override fun onItemClicked(user: User, view: View) {
        Toast.makeText(view.context, "echo", Toast.LENGTH_SHORT).show()
        //CurrentFollow = user
        //var intent = Intent(view.context, MainActivity::class.java)
        //startActivity(intent)
    }





}

