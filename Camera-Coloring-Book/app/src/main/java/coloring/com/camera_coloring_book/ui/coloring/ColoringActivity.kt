package coloring.com.camera_coloring_book.ui.coloring

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import coloring.com.camera_coloring_book.ColoringViewModel
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.TinyDB
import coloring.com.camera_coloring_book.Util.DataBindingActivity
import coloring.com.camera_coloring_book.coloringlib.ColoringView
import coloring.com.camera_coloring_book.databinding.ActivityColoringBinding
import coloring.com.camera_coloring_book.model.ARGB
import coloring.com.camera_coloring_book.ui.palette.PaletteAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_coloring.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class ColoringActivity : DataBindingActivity<ActivityColoringBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_coloring

    var current_bitmap : Bitmap? = null
    var resid : Int = 0
    lateinit var coloring_frame : ColoringView

    val viewModel by lazy { ViewModelProviders.of(this)[ColoringViewModel::class.java] }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        binding.viewModel = viewModel

        resid = intent.extras.getInt("resid")

        coloring_frame = findViewById<ColoringView>(R.id.coloringitem)

        current_bitmap = coloring_frame.stateImage

        if(current_bitmap != null && resid == coloring_frame.resid){
            coloring_frame.setImage(current_bitmap)
            Log.d("rere","current bitmap")
        } else {
            coloring_frame.setImage(resources.getDrawable(resid))
            Log.d("LOADDEBUG",resid.toString())
            Log.d("LOADDEBUG",coloring_frame.resid.toString())
            Log.d("rere","null")
        }
//
//        val zoom = ZoomView(this).apply {
//            addView(view)
//            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//            maxZoom = 4f
//        }

//        coloring_coloringview.addView(zoom)

        val paletteAdapter = PaletteAdapter(this, 2, loadSharedPreferencesData(), coloring_frame, viewModel)
        paletteAdapter.notifyDataSetChanged()

        val paletteLayoutManager
                = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.COLUMN
            justifyContent = JustifyContent.CENTER
        }

        coloring_palette_rv.apply {
            adapter = paletteAdapter
            layoutManager = paletteLayoutManager
        }



        coloring_frame.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_POINTER_DOWN -> return@setOnTouchListener true
                else -> return@setOnTouchListener false
            }
        }

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

                ok_btn.setOnClickListener {
                    coloring_frame.setImage(resources.getDrawable(resid))
                    dialog.dismiss()
                }

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

                ok_btn.setOnClickListener {
                    dialog.dismiss()

                    toast("저장이 완료되었습니다.")
                    finish()
                }

                cancel_btn.setOnClickListener { dialog.dismiss() }
            }

            else -> null
        }
    }



    private fun loadSharedPreferencesData() : ArrayList<ARGB> {
        val db = TinyDB(this)
        return db.getListObject("color", ARGB::class.java) as ArrayList<ARGB>
    }

    override fun onPause() {
        super.onPause()
        coloring_frame.setStateImage()
        coloring_frame.resid = resid
        Log.d("SAVEDEBUG",resid.toString())
        Log.d("rere","pause " + current_bitmap.toString())
    }

}