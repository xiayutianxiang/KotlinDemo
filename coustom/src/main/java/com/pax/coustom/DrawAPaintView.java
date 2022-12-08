package com.pax.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawAPaintView extends View {

    private Paint mPaint;

    public DrawAPaintView(Context context) {
        this(context,null);
    }

    public DrawAPaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawAPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(300,300,200,mPaint);
    }
}
