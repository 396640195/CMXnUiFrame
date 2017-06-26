package com.xn.uiframe.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xn.uiframe.R;

/**
 * Created by xn068074 on 2017/6/26.
 */

public class HeaderRelativeLayout extends RelativeLayout{

    /**
     * 分隔线的高度
     */
    private float mHeight;
    /**
     * 分隔线的颜色
     */
    private @ColorInt int mColor;

    public HeaderRelativeLayout(Context context) {
        super(context);
    }

    public HeaderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.HeaderRelativeLayout);
        if (attributes != null) {
            mHeight = attributes.getDimension(R.styleable.HeaderRelativeLayout_split_line_height, 0);
            mColor = attributes.getColor(R.styleable.HeaderRelativeLayout_split_line_color, Color.BLACK);
            attributes.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mHeight);

        canvas.drawLine(0, this.getHeight()-mHeight, this.getWidth(), this.getHeight()-mHeight, paint);
    }

    public void setColor(@ColorRes int mColor) {
        this.mColor = getResources().getColor(mColor);
        this.invalidate();
    }
}
