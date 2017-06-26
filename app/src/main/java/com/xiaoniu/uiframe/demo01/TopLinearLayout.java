package com.xiaoniu.uiframe.demo01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xiaoniu.uiframe.R;

/**
 * Created by xn068074 on 2017/6/26.
 */

public class TopLinearLayout extends LinearLayout {
    public TopLinearLayout(Context context) {
        super(context);
    }

    public TopLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.background_color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        canvas.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1, paint);
    }
}
