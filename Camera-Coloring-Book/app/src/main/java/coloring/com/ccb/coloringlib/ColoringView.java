package coloring.com.camera_coloring_book.coloringlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import coloring.com.camera_coloring_book.coloringlib.draws.Position;
import coloring.com.camera_coloring_book.coloringlib.draws.image.DrawImage;
import coloring.com.camera_coloring_book.coloringlib.draws.image.DrawImageImpl;

/**
 * Created by seotm on 08.06.17.
 */

public class ColoringView extends View {

    final DrawImage drawImage;
    Bitmap bitmap;
    private int resid;
    int paintColor;
    boolean enableColoringBlackColor;
    boolean flag = false;

    private OnFillColorListener fillColorListener;

    public ColoringView(Context context) {
        this(context, null);
    }

    public ColoringView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColoringView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        drawImage = new DrawImageImpl();
        new SetupResources(context, attrs, defStyleAttr).setup(this);
    }

    public void setImage(@Nullable Drawable image) {
        drawImage.setImage(image);
        invalidate();
    }

    public void setImage(@Nullable Bitmap image) {
        drawImage.setImage(image);
        invalidate();
    }

    public void setPaintColor(@ColorInt int paintColor) {
        this.paintColor = paintColor;
    }

    public void setState(@Nullable ColoringState state) {
        if (state != null) {
            state.restoreState(this);
            invalidate();
        }
    }

    public void setStateImage(){
        BitMapHolder.bitmap = drawImage.getImage();
        invalidate();
    }

    public ColoringState getState() {
        return new ColoringState(this);
    }

    public Bitmap getStateImage(){
        return BitMapHolder.bitmap;
    }

    public void setResid(int resid) {
        BitMapHolder.resid = resid;
    }

    public int getResid() {
        return BitMapHolder.resid;
    }

    public void setFillColorListener(OnFillColorListener listener) {
        this.fillColorListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        drawImage.updateSize(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawImage.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("tag","a");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Position bitmapPosition = drawImage.toBitmapPosition(x, y);
                Bitmap image = drawImage.getImage();

                if (!new ColorValidation(enableColoringBlackColor)
                        .isValidPosition(bitmapPosition, image)) {
                    return true;
                }

                int pixel = image.getPixel(bitmapPosition.x,bitmapPosition.y);

                int current_red = Color.red(pixel);
                int current_green = Color.green(pixel);
                int current_blue = Color.blue(pixel);


//                if(current_red == 255 && current_green == 255 & current_blue == 255){
                    new DrawFloodFilter(bitmapPosition)
                            .draw(paintColor, image);
                    invalidate();
//                } else {
//                    new DrawFloodFilter(bitmapPosition)
//                            .draw(Color.parseColor("#ffffff"), image);
//                    invalidate();
//                }

                if (fillColorListener != null) {
                    fillColorListener.onFillColor(paintColor);
                }



        }
        return true;
    }

    public void resetColor(){
        Bitmap image = drawImage.getImage();
        int x = (int) getX();
        int y = (int) getY();
        Position bitmapPosition = drawImage.toBitmapPosition(x, y);

        new DrawFloodFilter(bitmapPosition)
                .draw(Color.parseColor("#fff"),image);
    }
}
