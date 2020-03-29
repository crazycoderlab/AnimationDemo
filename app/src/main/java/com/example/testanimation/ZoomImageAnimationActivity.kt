package com.example.testanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity

class ZoomImageAnimationActivity : AppCompatActivity(){

    private lateinit var expandedImageView: ImageView

    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_image)
        findViewById<ImageButton>(R.id.imagebutton1).setOnClickListener {
            zoomImage(it as ImageButton,R.mipmap.zoom_image_big_1)
        }
        findViewById<ImageButton>(R.id.imagebutton2).setOnClickListener {
            zoomImage(it as ImageButton,R.mipmap.zoom_image_big_2)
        }
        expandedImageView = findViewById(R.id.imageview)
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    private var currentAnimator: AnimatorSet? = null
    private fun zoomImage(imageButton: View,@DrawableRes res: Int){
        currentAnimator?.cancel()

        expandedImageView.setImageResource(res)

        /**
         * 计算放大图像的开始和结束范围
         */
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        /**
         * 起始边界是缩略图的全局可见矩形，而最终边界是容器视图的全局可见矩形
         * 还要将容器视图的偏移量设置为边界的原点，因为这是定位动画属性（X，Y）的原点
         */
        imageButton.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        /**
         * 使用center crop，将开始范围调整为与最终范围相同的长宽比
         * 这样可以防止在动画过程中发生不必要的拉伸
         * 还要计算起始比例因子（终止比例因子始终为1.0）
         */
        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // 水平延伸起点
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // 竖直延伸起点
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        /**
         * 隐藏缩略图并显示放大视图。动画开始时，它将放大视图放置在缩略图的位置。
         */
        imageButton.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        /**
         * 将SCALE_X和SCALE_Y转换的枢轴点设置到放大视图的左上角（默认为视图的中心）。
         */
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        /**
         * 构造并运行四个平移和缩放属性（X，Y，SCALE_X和SCALE_Y）的并行动画。
         */
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                expandedImageView,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        /**
         * 单击放大的图像后，它应缩小到原始范围并显示缩略图而不是展开的图像。
         */
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            //并行地对四个定位/大小设置属性进行动画处理，使其恢复为原始值。
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        imageButton.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        imageButton.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }

    }

}