package coloring.com.camera_coloring_book.ui.palette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.model.ARGB
import kotlinx.android.synthetic.main.fragment_palette.*

class PaletteFragment : Fragment(){

    lateinit var paletteAdapter : PaletteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paletteAdapter = PaletteAdapter(context!!, 0, ArrayList())


        my_palette_recycler.adapter = paletteAdapter
        paletteAdapter.add(ARGB(255, 100, 140, 186))
        paletteAdapter.add(ARGB(255, 255, 64, 129))
        paletteAdapter.add(ARGB(255, 100, 140, 186))
        paletteAdapter.add(ARGB(255, 255, 64, 129))
        paletteAdapter.add(ARGB(255, 100, 140, 186))
        paletteAdapter.add(ARGB(255, 255, 64, 129))
        paletteAdapter.add(ARGB(255, 100, 140, 186))
        paletteAdapter.add(ARGB(255, 255, 64, 129))
        paletteAdapter.add(ARGB(255, 100, 140, 186))

        val gridLayoutManager = GridLayoutManager(context!!, 6)
        my_palette_recycler.layoutManager = gridLayoutManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_palette, container, false)
        return v
    }
}