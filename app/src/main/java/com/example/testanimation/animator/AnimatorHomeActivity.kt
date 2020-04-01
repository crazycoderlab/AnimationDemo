package com.example.testanimation.animator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R
import com.example.testanimation.animator.view.CircularRevealAnimatorActivity
import com.example.testanimation.animator.view.ZoomImageAnimatorActivity

class AnimatorHomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_home)

        findViewById<Button>(R.id.animator_bt1).setOnClickListener {
            val intent = Intent(this@AnimatorHomeActivity, CircularRevealAnimatorActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.animator_bt2).setOnClickListener {
            val intent = Intent(this@AnimatorHomeActivity, ZoomImageAnimatorActivity::class.java)
            startActivity(intent)
        }
    }
}