package coloring.com.camera_coloring_book.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView
import coloring.com.camera_coloring_book.R

class Circle : TextView {

    private var mRadius: Float = 0.toFloat()
    private var backgroundColor: Int? = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
/*        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.circleTextView)
        backgroundColor = typedArray.getColor(R.styleable.circleTextView_ct_backgroundColor, Color.TRANSPARENT)
        typedArray.recycle()*/
    }

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

