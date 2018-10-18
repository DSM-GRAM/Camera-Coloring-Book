package coloring.com.camera_coloring_book.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_photo.view.*
import java.io.FileNotFoundException


internal class AlbumAdapter(private val context: Context,
                            private val albumList : ArrayList<String>) : RecyclerView.Adapter<AlbumViewHolder>() {

    fun add(path : String){
        albumList.add(path)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder
            = AlbumViewHolder(parent)

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.itemView.photo.setImageURI(Uri.parse(albumList[position]))
    }

    override fun getItemCount() = albumList.size

    private fun resize(context : Context, uri : Uri, resize: Int) : Bitmap? {
        var resizeBitmap : Bitmap? = null

        val options = BitmapFactory.Options()
        try {
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options) // 1번

            var width = options.outWidth
            var height = options.outHeight
            var samplesize = 1

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break
                width /= 2
                height /= 2
                samplesize *= 2
            }

            options.inSampleSize = samplesize
            val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options) //3번
            resizeBitmap = bitmap

        } catch (e : FileNotFoundException) {
            e.printStackTrace()
        }
        return resizeBitmap
    }
}