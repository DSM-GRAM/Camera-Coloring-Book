package coloring.com.camera_coloring_book.coloringlib;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

/**
 * Created by seotm on 12.06.17.
 */

public class ColoringState {

    final Bitmap bitmap;

    ColoringState(@NonNull ColoringView view) {
        this.bitmap = view.drawImage.getImage();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    void restoreState(@NonNull ColoringView view) {
        if (bitmap != null) {
            view.drawImage.setStateImage(bitmap);
        }
    }
}
