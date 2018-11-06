package coloring.com.camera_coloring_book.ui.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.R

class AlbumViewHolder(parentView : View) : RecyclerView.ViewHolder(
        LayoutInflater.from(parentView.context).inflate(R.layout.item_photo, null, false))