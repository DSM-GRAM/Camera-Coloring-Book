package coloring.com.ccb.coloringlib.draws.image;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;


/**
 * Created by seotm on 12.06.17.
 */

class BitmapHolder {

    final Bitmap bitmap;
    final boolean allowGrown;

    BitmapHolder(@NonNull Bitmap bitmap, boolean allowGrown) {
        this.bitmap = bitmap;
        this.allowGrown = allowGrown;
    }
}
