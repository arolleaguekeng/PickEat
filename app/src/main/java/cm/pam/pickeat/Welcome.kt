package cm.pam.pickeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Welcome: AppCompatActivity(){

    private val titles = arrayOf<String>("Enjoy",
        "Order and receive",
        "With PickEat, ")
    private val details = arrayOf<String>("Sharing of good meal",
        "Anytime and Anywhere",
        "Eating has never been so fun!")

    private val images = arrayOf(R.drawable.welcome, R.drawable.welcome, R.drawable.welcome)
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        val nextButton = findViewById<Button>(R.id.nextBtn)
        val skipBtn = findViewById<TextView>(R.id.skipBtn)
        val detail = findViewById<TextView>(R.id.textView6)
        val title = findViewById<TextView>(R.id.textView5)
        val main = findViewById<ConstraintLayout>(R.id.main)

        skipBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        nextButton.setOnClickListener {
            if(i!=3)
            {
                main.setBackgroundResource(images[i])
                title.text = titles[i]
                detail.text= details[i]
                i++
            } else{startActivity(Intent(applicationContext, MainActivity::class.java))}
        }
    }
}