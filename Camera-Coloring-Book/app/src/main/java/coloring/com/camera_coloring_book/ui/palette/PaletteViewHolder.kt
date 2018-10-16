package coloring.com.camera_coloring_book.ui.palette

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.R

class PaletteViewHolder(parentView : View) : RecyclerView.ViewHolder(
        LayoutInflater.from(parentView.context).inflate(R.layout.item_palette, null, false))