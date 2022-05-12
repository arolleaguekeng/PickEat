package cm.pam.pickeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cm.pam.pickeat.ui.friend.following.FollowingListFragment
import cm.pam.pickeat.ui.friend.list.FriendListFragment
import cm.pam.pickeat.ui.home.HomeViewModel
import cm.pam.pickeat.ui.profile.ProfileFragment
import com.google.android.material.tabs.TabLayout

class FollowActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        var toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.topAppBar)

        toolBar.title = FriendListFragment.CurrentUser.name

        var tablayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tabLayout)
        var tabFollowing = findViewById<com.google.android.material.tabs.TabItem>(R.id.tabFollowing)


        for(i in 0..tablayout.tabCount){
            val tab : TabLayout.Tab? = tablayout.getTabAt(i)
            //var tabView : View = LayoutInflater.from(this).inflate(R.layout.activity_follow, (ViewGroup) tab.getCustomView(), false)

        }



    }





    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentFollow, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        if(tab.position == 0){

            Toast.makeText(applicationContext, "yyoo", Toast.LENGTH_SHORT).show()
        }
        else{

            Toast.makeText(applicationContext, "yyaa", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }
}