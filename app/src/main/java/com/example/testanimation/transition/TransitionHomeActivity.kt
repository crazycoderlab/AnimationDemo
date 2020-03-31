package com.example.testanimation.transition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R
import com.example.testanimation.transition.fragment.FragmentCardTransition

class TransitionHomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_home)
        findViewById<Button>(R.id.transition_bt1).setOnClickListener {
            val intent = Intent(this@TransitionHomeActivity,
                FragmentCardTransition::class.java)
            startActivity(intent)
        }
    }
}