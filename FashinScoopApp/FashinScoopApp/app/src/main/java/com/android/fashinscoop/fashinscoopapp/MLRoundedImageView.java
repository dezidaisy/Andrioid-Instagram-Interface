package com.android.fashinscoop.fashinscoopapp;

/**
 * Created by Desiree Dias on 8/18/2017.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

public class MLRoundedImageView extends AppCompatImageView {

    private Paint paintBorder;
    private int borderWidth = 4;

    public MLRoundedImageView(Context context) {

        super(context);

    }



    public MLRoundedImageView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }



    public MLRoundedImageView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }



    @Override

    protected void onDraw(Canvas canvas) {



        Drawable drawable = getDrawable();



        if (drawable == null) {

            return;

        }



        if (getWidth() == 0 || getHeight() == 0) {

            return;

        }

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);



        int w = getWidth(), h = getHeight();



        int radius = w > h ? h:w; // set the smallest edge as radius.
        Bitmap roundBitmap = getCroppedBitmap(bitmap, radius);
        //Bitmap roundBitmap = getCroppedBitmap(bitmap, w);

        canvas.drawBitmap(roundBitmap, 0, 0, null);



    }

    public void setBorderWidth(int borderWidth)
    {
        borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor)
    {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

         this.invalidate();
    }

    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {

        Bitmap sbmp;

        radius=radius-10;

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {

            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());

            float factor = smallest / radius;

            sbmp = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() / factor), (int)(bmp.getHeight() / factor), false);

        } else {

            sbmp = bmp;

        }



        Bitmap output = Bitmap.createBitmap(radius, radius,

                Config.ARGB_8888);

        Canvas canvas = new Canvas(output);



        final int color = 0xffa19774;

        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, radius, radius);

        //BitmapShader shader = new BitmapShader(canvasBitmap, TileMode.CLAMP,
        //TileMode.CLAMP);

        paint.setAntiAlias(true);

        paint.setFilterBitmap(true);

        paint.setDither(true);
        //paint.setShader(shader);
        //paint.setShader(null);
       // paint.setStyle(Paint.Style.STROKE);
        int borderwidth=0;
       // paint.setStrokeWidth(borderwidth);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(Color.parseColor("#BAB399"));
        paintBorder = new Paint();
        setBorderColor(Color.WHITE);
        paintBorder.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);

        canvas.drawCircle(radius / 2 + 0.7f,

                radius / 2 + 0.7f, radius / 2 + 0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(sbmp, rect, rect, paint);



        return output;

    }



}
