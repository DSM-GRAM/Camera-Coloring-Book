package coloring.com.ccb.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.ccb.R
import coloring.com.ccb.ui.palette.AddPaletteColorActivity
import kotlinx.android.synthetic.main.item_photo.view.*

class AlbumAdapter(private val context: Context,
                   private val albumList: ArrayList<String>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun decodeSampledBitmapFromURI(path: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, this)

            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(path, this)
        }
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val img = decodeSampledBitmapFromURI(albumList[position], 200, 200)

        if (img != null) holder.itemView.photo.setImageBitmap(img)
        else holder.itemView.photo.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.photo.setOnClickListener {
            val intent = Intent(context, AddPaletteColorActivity::class.java)
            if(img != null) intent.putExtra("path", albumList[position])
            else intent.putExtra("path", "noPath")
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder = AlbumViewHolder(parent)

    class AlbumViewHolder(parentView : View) : RecyclerView.ViewHolder(
            LayoutInflater.from(parentView.context).inflate(R.layout.item_photo, null, false))
}