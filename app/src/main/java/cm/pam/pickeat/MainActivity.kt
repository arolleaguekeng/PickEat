package cm.pam.pickeat

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cm.pam.pickeat.controller.auth
import cm.pam.pickeat.controller.currentPhone
import cm.pam.pickeat.controller.notification.MessageActivity
import cm.pam.pickeat.controller.readOnFile
import cm.pam.pickeat.databinding.ActivityMainBinding
import cm.pam.pickeat.ui.favori.FavoryFragment
import cm.pam.pickeat.ui.home.HomeFragment
import cm.pam.pickeat.ui.notifications.NotificationsFragment
import cm.pam.pickeat.ui.profile.ProfileFragment
import cm.pam.pickeat.ui.publication.PublicationFragment
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        var currentUer = auth.currentUser

        var file = File("PhoneNumber.txt")
        currentPhone = readOnFile(this, file)

        if (currentUer == null) {
            startActivity(Intent(applicationContext, AuthentificationPhone::class.java))
            finish()
        }
        else{
//            title=resources.getString(R.string.title_home)
//            loadFragment(HomeFragment())
            startActivity(Intent(applicationContext, MessageActivity::class.java))
            finish()
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_favorite-> {
                    title=resources.getString(R.string.title_favourites)
                    loadFragment(FavoryFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_home-> {
                    title=resources.getString(R.string.title_home)
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_publish-> {
                    title=resources.getString(R.string.title_publish)
                    loadFragment(PublicationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notification-> {
                    title=resources.getString(R.string.title_notification)
                    loadFragment(NotificationsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_account-> {
                    title=resources.getString(R.string.title_account)
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }



        /*Firebase.messaging.subscribeToTopic("New Meal")
             .addOnCompleteListener { task ->
                 var msg = getString(R.string.msg_subscribed)
                 if (!task.isSuccessful) {
                     msg = getString(R.string.msg_subscribe_failed)
                 }
                 Log.d(TAG, msg)
                 Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
             }

         /*binding.category.setOnClickListener{
             loadFragment(Menu())
         }*/*/
    }


    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}