package coloring.com.ccb.coloringlib.draws.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import coloring.com.ccb.coloringlib.draws.DrawComponent;
import coloring.com.ccb.coloringlib.draws.Position;

/**
 * Created by seotm on 09.06.17.
 */

public interface DrawImage extends DrawComponent {

    void setImage(@Nullable Drawable image);
    void setImage(@Nullable Bitmap image);
    void setStateImage(@Nullable Bitmap image);

    @Nullable
    Bitmap getImage();

    Position toBitmapPosition(int x, int y);

}
