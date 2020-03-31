package com.example.testanimation.animation

import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.testanimation.R
import kotlin.math.abs


class SpringAnimationSplashActivity : AppCompatActivity(){

    companion object{
        val handler = Handler()
    }

    private lateinit var imageViewIcon: ImageView
    private lateinit var titleLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_splash)
        val dm = resources.displayMetrics
        val screenHeight = dm.heightPixels

        imageViewIcon = findViewById(R.id.spring_splash_icon)
        titleLinearLayout = findViewById(R.id.spring_splash_title_ll)

        imageViewIcon.translationY = screenHeight.toFloat()

        //icon期初透明 动画过程中逐渐不透明
        imageViewIcon.alpha = 0f

        //icon动画
        val springAnimationIcon = SpringAnimation(imageViewIcon,SpringAnimation.TRANSLATION_Y,0f).apply {
            spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            //由下往上  故Y向速度<0
            setStartVelocity(-200f)
        }
        springAnimationIcon.addUpdateListener {
                _, value, _ ->
            //上浮动画过程中，变更icon透明度
            imageViewIcon.alpha = abs(value - screenHeight) / screenHeight
        }
        springAnimationIcon.addEndListener { _, _, _, _ ->  imageViewIcon.animate().rotationY(360f).setDuration(300).start() }

        val childAnimationList = mutableListOf<SpringAnimation>()
        //未title中的每一个字母添加动画
        for(i in 0 until titleLinearLayout.childCount){
            titleLinearLayout.getChildAt(i).translationY = screenHeight.toFloat()
            //title动画
            val springAnimationTitle = SpringAnimation(titleLinearLayout.getChildAt(i),SpringAnimation.TRANSLATION_Y,0f).apply {
                spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
                //由下往上  故Y向速度<0
                setStartVelocity(-200f)
            }
            childAnimationList.add(springAnimationTitle)
        }

        handler.postDelayed({
            //开启icon动画
            springAnimationIcon.start()
            //循环开启各字母动画
            for(animation in childAnimationList){
                handler.postDelayed({
                    animation.start()
                },
                20 * childAnimationList.indexOf(animation).toLong())
            }
        },500)

    }
}