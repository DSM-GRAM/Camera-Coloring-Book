package coloring.com.camera_coloring_book.ui.coloring

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coloring.com.camera_coloring_book.R
import kotlinx.android.synthetic.main.activity_coloring.*

class ColoringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coloring)

        coloring_coloringview.setPaintColor(Color.parseColor("#FFFF00"))
    }
}