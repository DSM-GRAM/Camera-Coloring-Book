package coloring.com.ccb.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.ImageView
import coloring.com.ccb.adapter.PaletteAdapter
import coloring.com.ccb.model.ARGB
import coloring.com.ccb.util.TinyDBUtil.loadSharedPreferencesData
import coloring.com.ccb.util.TinyDBUtil.saveSharedPreferenceData
import java.lang.Exception

class TouchImageView : ImageView {
    lateinit var bitmap: Bitmap
    var paletteAdapter: PaletteAdapter? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            try {
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                val alphaValue = Color.alpha(pixel)
                val redValue = Color.red(pixel)
                val blueValue = Color.blue(pixel)
                val greenValue = Color.green(pixel)

                val list = loadSharedPreferencesData(context)
                list.add(ARGB(alphaValue, redValue, greenValue, blueValue))
                saveSharedPreferenceData(context, list)

                if (paletteAdapter != null) paletteAdapter!!.changeList(list)
            } catch (e : Exception){ }
        }
        return super.onTouchEvent(event)
    }
}