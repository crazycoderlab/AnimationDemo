package com.example.testanimation.animator.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R
import kotlin.math.hypot

class CircularRevealAnimatorActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal)

        val myView: View = findViewById(R.id.circular_icon)

        myView.postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx = myView.width / 2
                val cy = myView.height / 2

                val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
                val anim: Animator
                if(myView.visibility == View.INVISIBLE){
                    anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius)
                    myView.visibility = View.VISIBLE
                }else{
                    anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, finalRadius, 0f)
                    anim.addListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator) {
                            myView.visibility = View.INVISIBLE
                        }
                    })
                }
                anim.duration = 2000
                anim.start()
            } else {
                myView.visibility = View.VISIBLE
            }
        },500)

    }
}