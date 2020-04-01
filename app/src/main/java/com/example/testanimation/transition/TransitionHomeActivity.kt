package com.example.testanimation.transition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R
import com.example.testanimation.transition.activity.ActivityTransitionFirst
import com.example.testanimation.transition.fragment.FragmentCardTransition
import com.example.testanimation.transition.viewgroup.LayoutChangeActivity
import com.example.testanimation.transition.viewgroup.LinearLayoutTransitionActivity
import com.example.testanimation.transition.viewpager.ViewPagerTransition

class TransitionHomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_home)
        findViewById<Button>(R.id.transition_bt1).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                FragmentCardTransition::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.transition_bt2).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                LinearLayoutTransitionActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.transition_bt3).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                ActivityTransitionFirst::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.transition_bt4).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                LayoutChangeActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.transition_bt5).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                ViewPagerTransition::class.java)
            startActivity(intent)
        }
    }
}