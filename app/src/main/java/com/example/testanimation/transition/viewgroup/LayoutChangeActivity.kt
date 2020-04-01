package com.example.testanimation.transition.viewgroup

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.transition.*
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testanimation.R

class LayoutChangeActivity: AppCompatActivity(){

    lateinit var scene1: Scene
    lateinit var scene2: Scene
    lateinit var sceneRoot: FrameLayout
    lateinit var sceneContainer: ConstraintLayout
    var isFirst = true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layoutchange)

        sceneRoot = findViewById(R.id.layoutchange_scene_root)
        sceneContainer = findViewById(R.id.layoutchange_container)

        scene1 = Scene(sceneRoot,sceneContainer)
        scene2 = Scene.getSceneForLayout(sceneRoot,R.layout.activity_layoutchange_anotherone,this)

        findViewById<Button>(R.id.layoutchange_bt).setOnClickListener {
            isFirst = if(isFirst){
                TransitionManager.go(scene2,ChangeBounds())
//                TransitionManager.go(scene2, AutoTransition())
//                TransitionManager.go(scene2, ChangeClipBounds())
//                TransitionManager.go(scene2, ChangeScroll())
//                TransitionManager.go(scene2, Explode())
//                TransitionManager.go(scene2, Slide())
                false
            }else{
                TransitionManager.go(scene1,ChangeBounds())
//                TransitionManager.go(scene1,ChangeImageTransform())
//                TransitionManager.go(scene1, ChangeTransform())
//                TransitionManager.go(scene1, Fade())
                true
            }
        }

    }
}