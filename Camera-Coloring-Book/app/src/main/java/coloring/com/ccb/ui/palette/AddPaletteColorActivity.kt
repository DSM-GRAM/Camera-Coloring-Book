package coloring.com.ccb.ui.palette

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coloring.com.ccb.R
import coloring.com.ccb.util.TinyDB
import coloring.com.ccb.model.ARGB
import coloring.com.ccb.adapter.PaletteAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_add_color.*
import org.jetbrains.anko.imageURI

class AddColorActivity : AppCompatActivity(), View.OnTouchListener{

    private lateinit var bitmap : Bitmap
    private lateinit var paletteAdapter : PaletteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)

        val path = intent.getStringExtra("path")
        if(path.contains("noPath"))
            img.setImageResource(R.drawable.ic_launcher_background)
        else
            img.imageURI = Uri.parse(path)

        bitmap = (img.drawable as BitmapDrawable).bitmap

        img.setOnTouchListener(this)

        paletteAdapter = PaletteAdapter(this, 1, loadSharedPreferencesData(), null)
        val paletteLayoutManager
                = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.COLUMN
            justifyContent = JustifyContent.CENTER
        }

        add_palette.apply {
            adapter = paletteAdapter
            layoutManager = paletteLayoutManager
        }
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if(event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x.toInt()
            val y = event.y.toInt()

            val pixel = bitmap.getPixel(x, y)

            val alphaValue = Color.alpha(pixel)
            val redValue = Color.red(pixel)
            val blueValue = Color.blue(pixel)
            val greenValue = Color.green(pixel)

            Log.e("ARGB", "A : $alphaValue , R : $redValue , G : $greenValue , B : $blueValue")

            val list = loadSharedPreferencesData()
            list.add(ARGB(alphaValue, redValue, greenValue, blueValue))
            saveSharedPreferenceData(list)
            paletteAdapter.changeList(list)
        }
        return false
    }

    private fun loadSharedPreferencesData() : ArrayList<ARGB> {
        val db = TinyDB(applicationContext)
        return db.getListObject("color", ARGB::class.java) as ArrayList<ARGB>
    }

    private fun saveSharedPreferenceData(list : ArrayList<ARGB>){
        val db = TinyDB(applicationContext)
        db.putListObject("color", list as java.util.ArrayList<Any>)
    }
}
