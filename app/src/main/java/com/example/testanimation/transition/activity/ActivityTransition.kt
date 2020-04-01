package com.example.testanimation.transition.activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.example.testanimation.R
import com.example.testanimation.transition.activity.ItemData.Companion.EXTRA_PARAM_IMG
import com.example.testanimation.transition.activity.ItemData.Companion.EXTRA_PARAM_NAME


class ActivityTransitionFirst : AppCompatActivity(){
    lateinit var layoutGoogle: LinearLayout
    lateinit var layoutAndroid: LinearLayout
    lateinit var layoutKotlin: LinearLayout
    lateinit var layoutFlutter: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_first)

        layoutGoogle = findViewById(R.id.activity_transition_google)
        layoutAndroid = findViewById(R.id.activity_transition_android)
        layoutKotlin = findViewById(R.id.activity_transition_kotlin)
        layoutFlutter = findViewById(R.id.activity_transition_flutter)

        layoutGoogle.setOnClickListener { transition(layoutGoogle,1) }
        layoutAndroid.setOnClickListener { transition(layoutAndroid,2) }
        layoutKotlin.setOnClickListener { transition(layoutKotlin,3) }
        layoutFlutter.setOnClickListener { transition(layoutFlutter,4) }

    }

    private fun transition(layout:ViewGroup,id: Int){
        val intent = Intent(this,ActivityTransitionSecond::class.java)
        intent.putExtra(ItemData.EXTRA_PARAM_ID,id)

        val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            Pair(layout.getChildAt(0),EXTRA_PARAM_NAME),
            Pair(layout.getChildAt(1),EXTRA_PARAM_IMG)
        )

        ActivityCompat.startActivity(this,intent,option.toBundle())
    }

}

class ActivityTransitionSecond: AppCompatActivity(){
    private var itemData: ItemData? = null

    lateinit var name: TextView
    lateinit var icon: ImageView
    lateinit var desc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_second)
        name = findViewById(R.id.activity_transition_sec_title)
        icon = findViewById(R.id.activity_transition_sec_img)
        desc = findViewById(R.id.activity_transition_sec_desc)

        itemData = ItemData.DATAS.find { it.id == intent.getIntExtra(ItemData.EXTRA_PARAM_ID,1)}

        itemData?.let {
            ViewCompat.setTransitionName(name,EXTRA_PARAM_NAME)
            ViewCompat.setTransitionName(icon,EXTRA_PARAM_IMG)

            name.text = it.name
            icon.setImageResource(it.iconRes)
            desc.text = it.description
        }
    }
}


data class ItemData(val id: Int,val name: String, @DrawableRes val iconRes: Int,val description: String){
    companion object{
        const val EXTRA_PARAM_ID = "item:_id"
        const val EXTRA_PARAM_NAME = "item:name"
        const val EXTRA_PARAM_IMG = "item:img"
        val DATAS = listOf(
            ItemData(1,"Google",R.mipmap.google,"Google是源自美国的跨国科技公司，为Alphabet Inc.的子公司，业务范围涵盖互联网广告、互联网搜索、云计算等领域，开发并提供大量基于互联网的产品与服务，其主要利润来自于AdWords等广告服务。"),
            ItemData(2,"Android",R.mipmap.android,"Android，中文常译作安卓或安致，是一个基于Linux内核的开放源代码移动操作系统，由谷歌成立的开放手持设备联盟持续领导与开发，主要设计用于触摸屏移动设备如智能手机和平板电脑与其他便携式设备。"),
            ItemData(3,"Kotlin",R.mipmap.kotlin,"Kotlin是一种在Java虚拟机上运行的静态类型编程语言，它也可以被编译成为JavaScript源代码。它主要是由俄罗斯圣彼得堡的JetBrains开发团队所发展出来的编程语言，其名称来自于圣彼得堡附近的科特林岛。"),
            ItemData(4,"Flutter",R.mipmap.flutter,"Flutter是一个由谷歌开发的开源移动应用软件开发工具包，用于为Android、iOS、 Windows、Mac、Linux、Google Fuchsia开发应用。 Flutter第一个版本支持Android操作系统，开发代号称作“Sky”。")
        )
    }
}