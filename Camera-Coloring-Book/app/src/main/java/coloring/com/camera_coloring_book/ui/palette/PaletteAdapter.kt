package coloring.com.camera_coloring_book.ui.palette

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.model.ARGB
import kotlinx.android.synthetic.main.item_palette.view.*

class PaletteAdapter(private val context : Context,
                     private val paletteType : Int,
                     private val colorList : ArrayList<ARGB>): RecyclerView.Adapter<PaletteViewHolder>() {

    fun add(argb : ARGB){
        colorList.add(argb)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {

        holder.itemView.color_view.setBackgroundCircleColor(Color.argb(colorList[position].A, colorList[position].R, colorList[position].G, colorList[position].B))
        holder.itemView.color_view.setOnClickListener {
            when(paletteType){
                // 내 팔레트 부분
                0->{
                    if(position == itemCount-1)
                        context.startActivity(Intent(context, AddColorActivity::class.java))
                }
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