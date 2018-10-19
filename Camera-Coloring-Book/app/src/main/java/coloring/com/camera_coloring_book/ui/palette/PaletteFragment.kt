package coloring.com.camera_coloring_book.ui.palette

import android.annotation.SuppressLint
import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import coloring.com.camera_coloring_book.R
import coloring.com.camera_coloring_book.model.ARGB
import coloring.com.camera_coloring_book.ui.MainActivity
import coloring.com.camera_coloring_book.ui.adapter.AlbumAdapter
import coloring.com.camera_coloring_book.ui.adapter.PaletteAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_palette.*
import java.util.*
import kotlin.collections.ArrayList

class PaletteFragment : Fragment(){

    private lateinit var paletteAdapter : PaletteAdapter
    private lateinit var albumAdapter : AlbumAdapter
        companion object {
        lateinit var behavior: BottomSheetBehavior<ConstraintLayout>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        behavior = BottomSheetBehavior.from(album_list_bottom_sheet)

        paletteAdapter = PaletteAdapter(context!!, 0, ArrayList())
        getAllImages((activity as MainActivity?)!!)

        val paletteLayoutManager
                = FlexboxLayoutManager(context!!).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }

        my_palette_recycler.apply {
            adapter = paletteAdapter
            layoutManager = paletteLayoutManager
        }

        album_recycler.apply {
            adapter = albumAdapter
            layoutManager = GridLayoutManager(context, 3)
            smoothScrollToPosition(0)
        }
        paletteAdapter.add(ARGB(255, 100, 140, 186))
        paletteAdapter.add(ARGB(255, 255, 64, 129))
        paletteAdapter.add(ARGB())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_palette, container, false)
    }

    @SuppressLint("Recycle")
    private fun getAllImages(activity: Activity){
        val uri: Uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val columnIndexData: Int
        val albumList = ArrayList<String>()

        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = activity.contentResolver.query(uri, projection, null, null, null)

        columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor.moveToNext()) {
            albumList.add(cursor.getString(columnIndexData))
        }
        albumList.reverse()
        albumAdapter = AlbumAdapter(context!!, albumList)
        albumAdapter.notifyDataSetChanged()
    }
}