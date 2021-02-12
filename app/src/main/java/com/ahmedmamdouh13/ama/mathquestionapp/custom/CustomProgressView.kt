package com.ahmedmamdouh13.ama.mathquestionapp.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.ahmedmamdouh13.ama.mathquestionapp.R

class CustomProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {

        this.setWillNotDraw(false)
    }

    var customColor = ContextCompat.getColor(context, android.R.color.transparent)

    private var progress: Float = 1f
    private var paint = Paint()
        .apply {
            this.color = customColor
            this.isAntiAlias = true
            this.strokeWidth = 10f
        }

    var speed = 0.001f


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (progress != 1f) {
            speed += 0.001f
            invalidate()
        }
        else
            speed = 1f

        canvas?.drawRect(0f, 0f, (width / progress) * speed, height.toFloat(), paint)

    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    fun setColor(color: Int) {
        this.customColor = color
        paint = Paint()
            .apply {
                this.color = customColor
                this.isAntiAlias = true
                this.strokeWidth = 10f
            }
        invalidate()
    }
}