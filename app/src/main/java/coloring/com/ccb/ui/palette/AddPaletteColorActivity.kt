package coloring.com.ccb.ui.palette

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coloring.com.ccb.R
import coloring.com.ccb.adapter.PaletteAdapter
import coloring.com.ccb.util.TinyDBUtil.loadSharedPreferencesData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_add_color.*
import org.jetbrains.anko.imageURI

class AddPaletteColorActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)

        val path = intent.getStringExtra("path")
        if(path.contains("noPath")) img.setImageResource(R.drawable.ic_launcher_background)
        else img.imageURI = Uri.parse(path)

        img.bitmap = (img.drawable as BitmapDrawable).bitmap

        img.paletteAdapter = PaletteAdapter(this, 1, loadSharedPreferencesData(applicationContext), null)
        val paletteLayoutManager
                = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.COLUMN
            justifyContent = JustifyContent.CENTER
        }

        add_palette.apply {
            adapter = img.paletteAdapter
            layoutManager = paletteLayoutManager
        }
    }
}
