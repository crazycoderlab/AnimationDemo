package com.example.testanimation.transition.viewgroup

import android.animation.LayoutTransition
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testanimation.R

class LinearLayoutTransitionActivity : AppCompatActivity(){
    lateinit var container: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linearylayout_transition)

        container = findViewById(R.id.linearlayout_transition_container)
        container.layoutTransition = LayoutTransition()

        findViewById<Button>(R.id.linearlayout_transition_bt).setOnClickListener {
            when(container.childCount){
                0 -> {
                    val icon = ImageView(this)
                    icon.setBackgroundResource(R.mipmap.spring_anim_icon)
                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.bottomMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20f,resources.displayMetrics).toInt()
                    icon.layoutParams = layoutParams
                    container.addView(icon)
                }
                1 -> {
                    val name = TextView(this)
                    name.text = "Crazy Coder"
                    name.textSize = 20f
                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.bottomMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20f,resources.displayMetrics).toInt()
                    name.layoutParams = layoutParams
                    container.addView(name)
                }
                2 -> {
                    val dest = TextView(this)
                    dest.text = "Android Developer"
                    dest.textSize = 20f
                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.bottomMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20f,resources.displayMetrics).toInt()
                    dest.layoutParams = layoutParams
                    container.addView(dest)
                }
                else -> it.isEnabled = false
            }
        }
    }
}