package coloring.com.camera_coloring_book.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.model.ARGB
import coloring.com.camera_coloring_book.ui.palette.PaletteFragment.Companion.behavior
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.item_palette.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PaletteAdapter(private val context : Context,
                     private val paletteType : Int,
                     private val colorList : ArrayList<ARGB>): RecyclerView.Adapter<PaletteViewHolder>() {

    fun add(argb : ARGB){
        colorList.add(argb)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {

        holder.itemView.color_view.setBackgroundCircleColor(
                Color.argb(colorList[position].A,
                        colorList[position].R,
                        colorList[position].G,
                        colorList[position].B))
        // 내 팔레트 부분
        if(paletteType == 0) {
            if (position == itemCount - 1) {
                holder.itemView.color_view.setBackgroundCircleColor(
                        Color.argb(255,100, 140, 186))
                holder.itemView.add_view.apply {
                    visibility = View.VISIBLE
                    onClick {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            }
        }
        holder.itemView.color_view.setOnClickListener {
            when(paletteType){
                // 팔레트 수정 부분
                1->{

                }
                // 컬러링 작업 팔레트 부분
                2->{

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder
            = PaletteViewHolder(parent)
}