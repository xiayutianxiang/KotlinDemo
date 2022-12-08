package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawAPaintView extends View {

    private Paint mPaintCircle;
    private Paint mPaintRect;
    private Paint mPaintPoint;
    float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
    private Paint mPaintRoundRect;
    private Paint mPaintArc;
    private Paint mPaintPath;
    private Path mPath;
    // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)

    public DrawAPaintView(Context context) {
        this(context, null);
    }

    public DrawAPaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawAPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();
    }

    private void initPaint() {
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.RED);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(20);

        mPaintRect = new Paint();
        mPaintRect.setStyle(Paint.Style.FILL);

        mPaintPoint = new Paint();
        mPaintPoint.setStyle(Paint.Style.STROKE);
        mPaintPoint.setStrokeWidth(20);
        mPaintPoint.setStrokeCap(Paint.Cap.ROUND);
        mPaintPoint.setColor(Color.YELLOW);

        //画圆角矩形
        mPaintRoundRect = new Paint();
        mPaintRoundRect.setColor(Color.CYAN);
        //绘制弧形或扇形
        mPaintArc = new Paint();
        mPaintArc.setColor(Color.BLUE);
        //绘制自定义
        mPaintPath = new Paint();
        mPath = new Path();     //初始化path
        /*mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);*/
        mPaintPath.setStyle(Paint.Style.STROKE);
        mPaintPath.setColor(Color.MAGENTA);
        mPath.lineTo(200, 200);
        mPath.rLineTo(100, 0);
        mPath.close();  //封闭当前图形
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(100, 100, 50, mPaintCircle);

        canvas.drawRect(100, 100, 200, 200, mPaintRect);

        canvas.drawPoint(0, 0, mPaintPoint);

        canvas.drawPoints(points, 2, 8, mPaintPoint);

        canvas.drawRoundRect(0, 210, 250, 300, 40, 60, mPaintRoundRect);

        canvas.drawArc(0, 200, 200, 300, -110, 100, true, mPaintArc);
        canvas.drawArc(0, 200, 200, 300, 110, 100, false, mPaintArc);

        canvas.drawPath(mPath, mPaintPath);
    }
}
