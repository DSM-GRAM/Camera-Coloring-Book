package coloring.com.camera_coloring_book.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import com.owater.library.R

/**
 * Created by Owater on 2015/7/23.
 */
class Circle : TextView {

    private var mRadius: Float = 0.toFloat()
    private var backgroundColor: Int? = 0
    private var borderColor: Int = 0
    private var borderWidth: Float = 0.toFloat()
    private var borderAlpha: Float = 0.toFloat()
    private var ctType: Int = 0

    private val mCornerRadius = 360f
    private val mDx = 0f
    private val mDy = 0f

    private var canvas: Canvas? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.circleTextView)
        backgroundColor = typedArray.getColor(R.styleable.circleTextView_ct_backgroundColor, Color.WHITE)
        borderColor = typedArray.getColor(R.styleable.circleTextView_ct_border_color, Color.TRANSPARENT)
        borderWidth = typedArray.getDimension(R.styleable.circleTextView_ct_border_width, 0f)
        borderAlpha = typedArray.getFloat(R.styleable.circleTextView_ct_border_alpha, 1f)
        ctType = typedArray.getInt(R.styleable.circleTextView_ct_type, DEFAULT_FILL_TYPE)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        mRadius = (Math.min(height, width) / 2).toFloat()

        when (ctType) {
            1 -> setBackgroundCompat(width, height)
            2 -> {
                paint.color = borderColor
                paint.isAntiAlias = true
                paint.alpha = (255 * borderAlpha).toInt()
                canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), mRadius, paint)
            }
            else -> borderWidth = 0f
        }

        paint.color = backgroundColor!!
        paint.isAntiAlias = true
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), mRadius - borderWidth, paint)

        this.canvas = canvas
        super.onDraw(canvas)
    }

    fun setBackgroundCircleColor(color: Int) {
        backgroundColor = color
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun setBackgroundCompat(w: Int, h: Int) {
        val bitmap = createShadowBitmap(w, h, mCornerRadius, borderWidth + 5, mDx, mDy, borderColor)
        val drawable = BitmapDrawable(resources, bitmap)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable)
        } else {
            background = drawable
        }
    }

    private fun createShadowBitmap(shadowWidth: Int, shadowHeight: Int, cornerRadius: Float, shadowRadius: Float,
                                   dx: Float, dy: Float, shadowColor: Int): Bitmap {

        val output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ALPHA_8)
        val canvas = Canvas(output)

        val shadowRect = RectF(
                shadowRadius,
                shadowRadius,
                shadowWidth - shadowRadius,
                shadowHeight - shadowRadius)

        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy)
            shadowRect.bottom -= Math.abs(dy)
        }

        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx)
            shadowRect.right -= Math.abs(dx)
        }

        val shadowPaint = Paint()
        shadowPaint.isAntiAlias = true
        shadowPaint.color = shadowColor
        shadowPaint.style = Paint.Style.FILL

        if (!isInEditMode) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }

        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)

        return output
    }

    companion object {

        private val DEFAULT_FILL_TYPE = 0
    }

}

