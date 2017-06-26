package com.xiaoniu.uiframe.demo01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoniu.uiframe.R;

/**
 * Created by xn068074 on 2017/6/26.
 */

public class AxisView extends View {

    float radius;
    boolean start;
    boolean end;
    float width;

    public AxisView(Context context) {
        super(context);
    }

    public AxisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.AxisView);
        if (attributes != null) {
            radius = attributes.getDimension(R.styleable.AxisView_radius, 0);
            width = attributes.getDimension(R.styleable.AxisView_circleWidth,0);
            attributes.recycle();
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        float x = (this.getWidth() -width)/2;
        if (this.start) {
            canvas.drawLine(x, this.getHeight()/2, x, this.getHeight(), paint);
        } else if (this.end) {
            canvas.drawLine(x, 0, x, this.getHeight()/2, paint);
        }else{
            canvas.drawLine( x, 0,x, this.getHeight(), paint);
        }


        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawCircle(x, this.getHeight() / 2,(int)(this.radius*2), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, this.getHeight() / 2,(int)(this.radius*2)-1, paint);

        paint.setStrokeWidth(width);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, this.getHeight() / 2, this.radius, paint);


    }

    public void setStart(boolean start) {
        this.start = start;
        this.invalidate();
    }

    public void setEnd(boolean end) {
        this.end = end;
        this.invalidate();
    }
}
