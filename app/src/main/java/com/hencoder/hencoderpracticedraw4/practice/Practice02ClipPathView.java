package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    private Path mPath;

    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPath = new Path();
        mPath.addCircle(point1.x+bitmap.getWidth()/2,point1.y+bitmap.getHeight()/2,100, Path.Direction.CW);
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
//        FillType.WINDING：取path所有所在区域；
//        FillType.EVEN_ODD：取path所在并不相交区域；
//        FillType.INVERSE_WINDING：取path所有未占区域；
//        FillType.INVERSE_EVEN_ODD：取path未占或相交区域；
        mPath.setFillType(Path.FillType.INVERSE_WINDING);
        mPath.addCircle(point2.x+bitmap.getWidth()/2,point2.y+bitmap.getHeight()/2,200, Path.Direction.CW);
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
