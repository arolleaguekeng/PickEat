package cm.pam.pickeat.Service.adapters

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.models.User
import cm.pam.pickeat.ui.friend.list.FriendListFragment

class FollowersViewHolder(view: View): RecyclerView.ViewHolder(view) {


    val textName: TextView = view.findViewById(R.id.userName)
    val followNumber: TextView = view.findViewById(R.id.countFollowers)
    val imageProfil : ImageView = view.findViewById(R.id.imageUser)
    val button : Button = view.findViewById(R.id.btnFollow)


    @SuppressLint("ResourceAsColor")
    fun bindData(user: User){
        textName.text = user.name
        followNumber.text = "12"+" rate follower"
        //imageProfil.setImageResource(user.profile)
        var test = 0

        for(i in 0..FriendListFragment.FollowList.lastIndex){

            /*if(user.phoneNumber == FriendListFragment.FollowList[i].followedId && FriendListFragment.CurrentUser.phoneNumber == FriendListFragment.FollowList[i].followerId) {
                FriendListFragment.follow = true
                test += 1
                break
            }*/

        }


        if(FriendListFragment.follow && test != 0){
            button.text = "unfollow"
        }
        else{
            button.text = "Follow"
        }




    }





}