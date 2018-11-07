package coloring.com.ccb.ui.coloring

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import coloring.com.ccb.R
import coloring.com.ccb.adapter.PaletteAdapter
import coloring.com.ccb.util.TinyDBUtil.loadSharedPreferencesData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_coloring.*
import kotlinx.android.synthetic.main.dialog_coloringview.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class ColoringActivity : AppCompatActivity() {
    private var currentBitmap: Bitmap? = null
    private var resId: Int = 0
    private lateinit var paletteAdapter : PaletteAdapter

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        resId = intent.extras!!.getInt("resId")

        currentBitmap = coloring_work_view.stateImage

        if (currentBitmap != null && resId == coloring_work_view.resid) {
            coloring_work_view.setImage(currentBitmap)
            Log.d("rere", "current bitmap")
        } else {
            coloring_work_view.setImage(resources.getDrawable(resId))
            Log.d("LOADDEBUG", resId.toString())
            Log.d("LOADDEBUG", coloring_work_view.resid.toString())
            Log.d("rere", "null")
        }

        coloring_work_view.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_POINTER_DOWN -> return@setOnTouchListener true
                else -> return@setOnTouchListener false
            }
        }

        coloring_reset_tv.onClick { RSDialog("reset") }
        coloring_save_tv.onClick { RSDialog("save") }

        coloring_palette_rv.apply {
            adapter = PaletteAdapter(applicationContext, 2, loadSharedPreferencesData(applicationContext), coloring_work_view)
            layoutManager = FlexboxLayoutManager(applicationContext).apply {
                flexDirection = FlexDirection.COLUMN
                justifyContent = JustifyContent.CENTER
            }
        }
    }

    private fun RSDialog(type: String) {
        val dialog = Dialog(this)

        when (type) {
            "reset" -> {
                dialog.setContentView(R.layout.dialog_coloringview)

                dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                dialog.dialog_coloring_tv.text = "초기화 하시겠습니까?"
                dialog.coloring_ok_btn.setOnClickListener {
                    coloring_work_view.setImage(resources.getDrawable(resId))
                    dialog.dismiss()
                }
                dialog.coloring_cancel_btn.setOnClickListener { dialog.dismiss() }

                dialog.show()
            }
            "save" -> {
                dialog.setContentView(R.layout.dialog_coloringview)

                dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                dialog.dialog_coloring_tv.text = "저장 하시겠습니까?"
                dialog.coloring_ok_btn.setOnClickListener {
                    toast("저장이 완료되었습니다.")
                    dialog.dismiss()
                    finish()
                }
                dialog.coloring_cancel_btn.setOnClickListener { dialog.dismiss() }

                dialog.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        coloring_work_view.setStateImage()
        coloring_work_view.resid = resId
        Log.d("SAVEDEBUG", resId.toString())
        Log.d("rere", "pause " + currentBitmap.toString())
    }
}