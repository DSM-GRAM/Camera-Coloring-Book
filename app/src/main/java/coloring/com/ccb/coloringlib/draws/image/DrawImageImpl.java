package coloring.com.ccb.coloringlib.draws.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import coloring.com.ccb.coloringlib.draws.Position;
import coloring.com.ccb.coloringlib.draws.ViewSize;

/**
 * Created by seotm on 08.06.17.
 */

public class DrawImageImpl implements DrawImage {

    Drawable drawableImg;
    BitmapHolder bitmapImg;
    Rect bounds;
    private final ViewSize viewSize = new ViewSize();

    @Override
    public void setImage(@Nullable Drawable image) {
        this.drawableImg = image;
        setupImageBounds();
    }

    public void setDrawableImg(Drawable drawableImg) {
        this.drawableImg = drawableImg;
    }

    private void setupImageBounds() {
        SetupImageBounds setupBounds = new SetupImageBounds(this);
        setupBounds.setupImageBounds(viewSize);
    }

    @Override
    public void setImage(@Nullable Bitmap image) {
        this.bitmapImg = null;
        if (image != null) {
            this.bitmapImg = new BitmapHolder(image, false);
        }
        drawableImg = null;
        setupImageBounds();
    }

    @Override
    public void setStateImage(@Nullable Bitmap image) {
        this.bitmapImg = null;
        if (image != null) {
            this.bitmapImg = new BitmapHolder(image, true);
        }
        drawableImg = null;
        setupImageBounds();
    }

    @Nullable
    @Override
    public Bitmap getImage() {
        if (bitmapImg != null) {
            return bitmapImg.bitmap;
        }
        return null;
    }

    @Override
    public Position toBitmapPosition(int x, int y) {
        if (bitmapImg == null) return Position.invalid();

        int imageX = x - bounds.left;
        int imageY = y - bounds.top;
        if (imageX > bounds.width() || imageY > bounds.height()) {
            return Position.invalid();
        }
        if (imageX < 0 || imageY < 0) {
            return Position.invalid();
        }

        return new Position(imageX, imageY);
    }

    @Override
    public void updateSize(int w, int h, int oldw, int oldh) {
        viewSize.setSize(w, h);
        setupImageBounds();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (bitmapImg == null || bounds == null) {
            return;
        }
        canvas.drawBitmap(bitmapImg.bitmap, bounds.left, bounds.top, null);
    }

}
