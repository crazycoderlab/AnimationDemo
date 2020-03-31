package com.example.testanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.testanimation.animator.AnimatorHomeActivity
import com.example.testanimation.transition.TransitionHomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener {
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent(this@MainActivity, AnimatorHomeActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            val intent = Intent(this@MainActivity, TransitionHomeActivity::class.java)
            startActivity(intent)
        }
    }
}
