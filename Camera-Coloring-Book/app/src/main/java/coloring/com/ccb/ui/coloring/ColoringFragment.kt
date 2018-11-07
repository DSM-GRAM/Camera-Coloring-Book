package coloring.com.camera_coloring_book.ui.coloring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import coloring.com.camera_coloring_book.adapter.ColoringListAdapter
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.model.ColoringItem
import kotlinx.android.synthetic.main.fragment_coloring.*

class ColoringFragment : Fragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = arrayListOf<ColoringItem>().apply {
            add(ColoringItem(R.drawable.coloring_img1))
            add(ColoringItem(R.drawable.coloring_img2))
            add(ColoringItem(R.drawable.coloring_img3))
            add(ColoringItem(R.drawable.coloring_img4))
            add(ColoringItem(R.drawable.coloring_img5))
            add(ColoringItem(R.drawable.coloring_img6))
            add(ColoringItem(R.drawable.coloring_img7))
            add(ColoringItem(R.drawable.coloring_img8))
            add(ColoringItem(R.drawable.coloring_img9))
        }

        val coloringlistAdapter = ColoringListAdapter(images,activity!!)

        with(coloring_img_rv){
            adapter = coloringlistAdapter
            layoutManager = GridLayoutManager(activity!!,3)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_coloring,container,false)
    }
}