package coloring.com.camera_coloring_book.ui.palette

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.ColoringViewModel
import coloring.com.camera_coloring_book.Util.getInfo
import coloring.com.camera_coloring_book.Util.saveInfo
import coloring.com.camera_coloring_book.coloringlib.ColoringView
import coloring.com.camera_coloring_book.model.ARGB
import coloring.com.camera_coloring_book.ui.coloring.ColoringActivity
import kotlinx.android.synthetic.main.item_palette.view.*
import org.jetbrains.anko.toast

class PaletteAdapter(private val context : Context,
                     private val paletteType : Int,
                     private val colorList : ArrayList<ARGB>,
                     private val coloringBook : ColoringView,
                     private val viewmodel : ColoringViewModel): RecyclerView.Adapter<PaletteViewHolder>() {

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
                    val a = java.lang.Integer.toHexString(colorList.get(position).A)
                    val r = java.lang.Integer.toHexString(colorList.get(position).R)
                    val g = java.lang.Integer.toHexString(colorList.get(position).G)
                    val b = java.lang.Integer.toHexString(colorList.get(position).B)
                    val argb = a.toString().toTwo() + r.toString().toTwo()  + g.toString().toTwo() + b.toString().toTwo()

                    Log.i("PaletteAdapter", argb)
                    Log.i("PaletteAdapter a", a)
                    Log.i("PaletteAdapter r", r)
                    Log.i("PaletteAdapter g", g)
                    Log.i("PaletteAdapter b", b)
                    val hex_color = "#$argb"
                    context.toast("색이 변경되었습니다.")

                    coloringBook.setPaintColor(hex_color.toInt())


                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder
            = PaletteViewHolder(parent)

    fun String.toTwo(): String{
        if(this.length == 1)
            return "$this$0"
        else
            return this
    }
}