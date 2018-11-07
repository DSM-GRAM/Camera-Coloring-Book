package coloring.com.ccb.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.ccb.R
import coloring.com.ccb.coloringlib.ColoringView
import coloring.com.ccb.model.ARGB
import coloring.com.ccb.ui.palette.PaletteFragment.Companion.behavior
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.item_palette.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.lang.Integer.toHexString

class PaletteAdapter(private val context: Context,
                     private val paletteType: Int,
                     private var colorList: ArrayList<ARGB>,
                     private val coloringBook: ColoringView?): RecyclerView.Adapter<PaletteAdapter.PaletteViewHolder>() {

    companion object {
        const val MY_PALETTE = 0
        const val MY_PALETTE_MODIFY = 1
        const val COLORING_WORK = 2
    }

    fun changeList(list : ArrayList<ARGB>){
        colorList = list
        notifyDataSetChanged()
    }

    fun add(argb : ARGB){
        colorList.add(argb)
        notifyDataSetChanged()
    }

    private fun String.toTwo(): String = if(this.length == 1) "$this$0" else this

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.itemView.color_view.setBackgroundCircleColor(
                Color.argb(colorList[position].A, colorList[position].R, colorList[position].G, colorList[position].B))

        if(MY_PALETTE == paletteType){
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
                MY_PALETTE_MODIFY ->{ }
                COLORING_WORK ->{
                    val argb = toHexString(colorList[position].A).toTwo() +
                            toHexString(colorList[position].R).toTwo()  +
                            toHexString(colorList[position].G).toTwo() +
                            toHexString(colorList[position].B).toTwo()

                    coloringBook!!.setPaintColor(Color.argb(colorList[position].A, colorList[position].R, colorList[position].G, colorList[position].B))
                    context.toast("색이 변경되었습니다.")
                }
            }
        }
    }

    override fun getItemCount(): Int = colorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder
            = PaletteViewHolder(parent)

    class PaletteViewHolder(parentView : View) : RecyclerView.ViewHolder(
            LayoutInflater.from(parentView.context).inflate(R.layout.item_palette, null, false))
}