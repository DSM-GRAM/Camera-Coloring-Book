package coloring.com.camera_coloring_book.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coloring.com.camera_coloring_book.R
import kotlinx.android.synthetic.main.item_photo.view.*

class AlbumAdapter(private val context: Context,
                   private val albumList: ArrayList<String>) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder = AlbumViewHolder(parent)

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val img = decodeSampledBitmapFromURI(albumList[position], 200, 200)
        if (img != null) holder.itemView.photo.setImageBitmap(img)
        else holder.itemView.photo.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount() = albumList.size

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

    private fun decodeSampledBitmapFromURI(
            path: String,
            reqWidth: Int,
            reqHeight: Int
    ): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, this)

            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(path, this)
        }
    }
}