package net.tipam2022.pickeat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import cm.pam.pickeat.AuthentificationPhone
import cm.pam.pickeat.R
import cm.pam.pickeat.controller.TIME_OUT

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(applicationContext,AuthentificationPhone::class.java)
            startActivity(intent)
            finish()
        },TIME_OUT)
    }
}