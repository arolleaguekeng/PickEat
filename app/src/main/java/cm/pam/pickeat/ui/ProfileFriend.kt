package cm.pam.pickeat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cm.pam.pickeat.R
import cm.pam.pickeat.databinding.ActivityMainBinding
import cm.pam.pickeat.databinding.ActivityProfileFriendBinding
import cm.pam.pickeat.ui.friend.list.FriendListFragment

class ProfileFriend : AppCompatActivity() {

    lateinit var binding: ActivityProfileFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_friend)
        binding = ActivityProfileFriendBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.phonenumber.text = FriendListFragment.CurrentFollow!!.phoneNumber.toString()
        //binding.circleImageView.setImageResource(FriendListFragment.CurrentFollow!!.profile)
        binding.name.text = FriendListFragment.CurrentFollow!!.name

        if(FriendListFragment.follow){
            binding.btnFollow.isEnabled = false
            binding.btnUnfollow.isEnabled = true
        }
        else{
            binding.btnFollow.isEnabled = true
            binding.btnUnfollow.isEnabled = false
        }

    }


}