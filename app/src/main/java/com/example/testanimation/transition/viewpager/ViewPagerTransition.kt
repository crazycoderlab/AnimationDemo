package com.example.testanimation.transition.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.testanimation.R
import com.example.testanimation.transition.viewpager.MyAdapter.Companion.FRAGMENTS_COLORS
import com.example.testanimation.transition.viewpager.MyAdapter.Companion.FRAGMENTS_TITLES
import kotlin.math.abs
import kotlin.math.max

class ViewPagerTransition : AppCompatActivity(){
    lateinit var viewPager: ViewPager
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_transition)
        viewPager = findViewById(R.id.viewpager_transition_vp)
        viewPager.adapter = MyAdapter(supportFragmentManager)
        if(flag){
            viewPager.setPageTransformer(true,ZoomOutPageTransformer())
        }else{
            viewPager.setPageTransformer(true,DepthPageTransformer())
        }
    }

}

class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){

    companion object{
        val FRAGMENTS = listOf(
            VpFragment(),
            VpFragment(),
            VpFragment()
        )
        val FRAGMENTS_TITLES = listOf("CrazyCoder","Android","Developer")
        val FRAGMENTS_COLORS = listOf(android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_blue_light)
    }

    override fun getItem(position: Int): Fragment {
        return FRAGMENTS[position].apply {
            this.index = position
        }
    }


    override fun getCount(): Int {
        return FRAGMENTS.size
    }

}

class VpFragment: Fragment(){
    var index = 0
    var setBgColor = true
    lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_viewpager_transition_fragment,container,false)
        textView = rootView.findViewById(R.id.viewpager_transition_title)
        textView.text = FRAGMENTS_TITLES[index]
        context?.let {
            if(setBgColor)
            rootView.setBackgroundColor(ContextCompat.getColor(it,FRAGMENTS_COLORS[index]))
        }
        return rootView
    }
}

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}


class DepthPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    alpha = 1 - position

                    translationX = pageWidth * -position

                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}