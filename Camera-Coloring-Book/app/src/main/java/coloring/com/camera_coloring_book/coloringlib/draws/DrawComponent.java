package coloring.com.camera_coloring_book.coloringlib.draws;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

/**
 * Created by seotm on 08.06.17.
 */

public interface DrawComponent {

    void updateSize(int w, int h, int oldw, int oldh);
    void draw(@NonNull Canvas canvas);

}
