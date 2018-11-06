package coloring.com.camera_coloring_book.Adapter

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.ColoringViewModel
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.model.ColoringItem
import coloring.com.camera_coloring_book.ui.coloring.ColoringActivity
import coloring.com.camera_coloring_book.ui.coloring.ColoringFragment
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk27.coroutines.onClick

class ColoringListAdapter(val items : ArrayList<ColoringItem>,val context : Context, val fragment: ColoringFragment) : RecyclerView.Adapter<ColoringListAdapter.ColoringViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColoringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coloring,parent,false)
        return ColoringViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColoringViewHolder, position: Int) {
        Glide.with(context).load(items[position].imgresid).into(holder.image)
        holder.image.onClick {
            val intent = Intent(context,ColoringActivity::class.java)
            intent.putExtra("resid",items[position].imgresid)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = items.size


    inner class ColoringViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image  = itemView.findViewById<ImageView>(R.id.coloringlist_image)
    }
}