package coloring.com.ccb.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Circle : View {
    private var mRadius: Float = 0.toFloat()
    private var backgroundColor: Int? = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        mRadius = (Math.min(height, width) / 2).toFloat()

        paint.color = backgroundColor!!
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), mRadius, paint)
        super.onDraw(canvas)
    }

    fun setBackgroundCircleColor(color: Int) {
        backgroundColor = color
    }
}

