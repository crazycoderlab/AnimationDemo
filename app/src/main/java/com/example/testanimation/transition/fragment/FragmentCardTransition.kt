package com.example.testanimation.transition.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testanimation.R

class FragmentCardTransition : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_transition)
        supportFragmentManager.beginTransaction()
            .add(R.id.card_container,
                FragmentCard1()
            )
            .commit()
        findViewById<View>(R.id.card_container).setOnClickListener {
            flipCard()
        }
    }

    var showingBack = false
    //翻转
    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            showingBack = false
            return
        }

        showingBack = true

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.fragment_card_transition_flip_right_in,
                R.animator.fragment_card_transition_flip_right_out,
                R.animator.fragment_card_transition_flip_left_in,
                R.animator.fragment_card_transition_flip_left_out
            )
            .replace(R.id.card_container,
                FragmentCard2()
            )
            .addToBackStack(null)
            .commit()
    }

}

class FragmentCard1 : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_tansition_fragment_card1,container,false)
    }
}

class FragmentCard2 : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_tansition_fragment_card2,container,false)
    }
}