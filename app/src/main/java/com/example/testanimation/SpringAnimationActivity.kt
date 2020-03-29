package com.example.testanimation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

class SpringAnimationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring)
        findViewById<TextView>(R.id.spring_view1).setOnClickListener {
            val springAnimation = SpringAnimation(it,SpringAnimation.TRANSLATION_X,400f)
            //起始值
//            springAnimation.setStartValue(10f)
            //取值范围
//            springAnimation.setMinValue(-100f)
//            springAnimation.setMaxValue(200f)
            //起始速度 定义在动画开始时动画属性更改的速度。默认起始速度设置为 0 像素/秒。
//            springAnimation.setStartVelocity(5f)
            //阻尼  回弹到初始位置时抖动的频率 阻尼越小，抖动越大
            springAnimation.spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            //钢度  越低 类似于弹簧看起来越软  材质越柔
            springAnimation.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            //自定义SpringForce
            // val customSpringForce = SpringForce().setDampingRatio(x).setStiffness(y)
            // springAnimation.spring = customSpringForce
            //启动动画
            springAnimation.start()
            //终止动画
            // springAnimation.cancel() 在动画当前所在位置停止  只能在主线程调用
            // springAnimation.skipToEnd() 跳到动画终止位置然后停止，如果当前在动画中间，会有视觉上的跳跃
        }

    }
}