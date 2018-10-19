package coloring.com.camera_coloring_book.ui.coloring

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coloring.com.camera_coloring_book.R
import com.seotm.coloringview.ColoringView
import kotlinx.android.synthetic.main.activity_coloring.*
import kotlinx.android.synthetic.main.dialog_coloringview.*
import kotlinx.android.synthetic.main.fragment_coloring.*
import kotlinx.android.synthetic.main.item_coloringview.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import pl.polidea.view.ZoomView

class ColoringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        val view = layoutInflater.inflate(R.layout.item_coloringview, null, false)

        val zoom = ZoomView(this).apply {
            addView(view)
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            maxZoom = 4f
        }

        coloring_coloringview.addView(zoom)

        coloring_reset_tv.onClick { ShowDialog("reset") }

        coloring_save_tv.onClick { ShowDialog("save") }



//        coloring_coloringview.setOnTouchListener { v, event ->
//            when (event.action) {
//                else -> false
//            }



    }

    fun ShowDialog(type: String) {
        val dialog = Dialog(this)

        when (type) {
            "reset" -> {

                dialog.setContentView(R.layout.dialog_coloringview)
                val title = dialog.findViewById<TextView>(R.id.dialog_coloring_tv)
                val ok_btn = dialog.findViewById<Button>(R.id.coloring_ok_btn)
                val cancel_btn = dialog.findViewById<Button>(R.id.coloring_cancel_btn)

                title.text = "초기화 하시겠습니까?"

                dialog.show()

                dialog.window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                ok_btn.setOnClickListener { dialog.dismiss() }

                cancel_btn.setOnClickListener { dialog.dismiss() }

            }

            "save" -> {
                dialog.setContentView(R.layout.dialog_coloringview)
                val title = dialog.findViewById<TextView>(R.id.dialog_coloring_tv)
                val ok_btn = dialog.findViewById<Button>(R.id.coloring_ok_btn)
                val cancel_btn = dialog.findViewById<Button>(R.id.coloring_cancel_btn)

                title.text = "저장 하시겠습니까?"

                dialog.show()

                dialog.window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                ok_btn.setOnClickListener { dialog.dismiss() }

                cancel_btn.setOnClickListener { dialog.dismiss() }
            }

            else -> null
        }
    }

}