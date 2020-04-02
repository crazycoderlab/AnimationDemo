package com.example.testanimation.transition.drawable

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R
import com.example.testanimation.transition.viewpager.VpFragment

class TransitionDrawableActivity: AppCompatActivity(){

    lateinit var frameLayout: FrameLayout
    lateinit var transitionDrawable: TransitionDrawable

    private var first = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transitiondrawable)
        frameLayout = findViewById(R.id.transition_drawable_fl)

        transitionDrawable = frameLayout.background as TransitionDrawable

        findViewById<Button>(R.id.transition_drawable_bt).setOnClickListener {
            replaceFragment()
            if(first)
                transitionDrawable.startTransition(500)
            else
                transitionDrawable.reverseTransition(500)
        }

        replaceFragment()
    }

    private fun replaceFragment(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.transition_drawable_fl,VpFragment().apply {
                this.index = if(first) 0 else 1
                this.setBgColor = false
            })
            .commit()

        first = !first

    }

}


