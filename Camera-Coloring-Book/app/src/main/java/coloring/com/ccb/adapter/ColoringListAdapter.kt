package coloring.com.camera_coloring_book.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.model.ColoringItem
import coloring.com.camera_coloring_book.ui.coloring.ColoringActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_coloring.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ColoringListAdapter(val items : ArrayList<ColoringItem>,
                          val context : Context) : RecyclerView.Adapter<ColoringListAdapter.ColoringViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColoringViewHolder
        = ColoringViewHolder(parent)

    override fun onBindViewHolder(holder: ColoringViewHolder, position: Int) {
        Glide.with(context).load(items[position].imgresid).into(holder.itemView.coloring_list_item)

        holder.itemView.coloring_list_item.onClick {
            val intent = Intent(context, ColoringActivity::class.java)
            intent.putExtra("resid",items[position].imgresid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    class ColoringViewHolder(parentView : View) : RecyclerView.ViewHolder(
            LayoutInflater.from(parentView.context).inflate(R.layout.item_coloring, null, false))
}