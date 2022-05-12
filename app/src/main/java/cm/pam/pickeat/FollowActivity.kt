package cm.pam.pickeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import cm.pam.pickeat.Service.adapters.ViewPageAdapter
import cm.pam.pickeat.ui.friend.following.FollowingListFragment
import cm.pam.pickeat.ui.friend.list.FriendListFragment
import com.google.android.material.tabs.TabLayout
import java.util.*

class FollowActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)



        var toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.topAppBar)
        var viewPager = findViewById(R.id.viewPager) as ViewPager
        var tablayout = findViewById<TabLayout>(R.id.tabLayout)
        val fragmentAdapter = ViewPageAdapter(supportFragmentManager)

        fragmentAdapter.addFragment(FriendListFragment(),"Home")
        fragmentAdapter.addFragment(FollowingListFragment(),"Following")

        viewPager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewPager)


        for(i in 0..tablayout.tabCount){
            val tab : TabLayout.Tab? = tablayout.getTabAt(i)
            //var tabView : View = LayoutInflater.from(this).inflate(R.layout.activity_follow, (ViewGroup) tab.getCustomView(), false)

        }



    }





    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
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