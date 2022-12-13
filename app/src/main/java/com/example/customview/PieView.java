package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PieView extends View {
    private static final String TAG = "PieView";
    private int mTotalWidth;
    private int mTotalHeight;
    private float mRadius;
    private Paint mPaintRect;
    private RectF mRectF;
    private List<PieEntry> mPieLists;
    private List<Integer> mColorList;
    private Paint mPaintArc;
    private final int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    //延长点和饼图边缘的间距
    private float distance = 14F;
    //延长点的大小
    private float smallCircleRadius = 3F;
    //延长点上的同心圆环的大小
    private float bigCircleRadius = 7F;
    //延长线转折点的横向偏移
    private float xOffset = 7F;
    //延长线转折点的纵向偏移
    private float yOffset = 14F;
    //延长线最长段部分的长度
    private float extend = 100F;

    private Paint mPaintCenterCircle;
    private Paint mPaintPoint;
    private Paint mPaintCircle;
    private Paint mPaint;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // initData();
        initPaint();
    }

    //初始化画笔
    private void initPaint() {
        //扇形
        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);

        //中心圆
        mPaintCenterCircle = new Paint();
        mPaintCenterCircle.setColor(Color.WHITE);
        mPaintCenterCircle.setAntiAlias(true);

        //正方形
        mPaintRect = new Paint();
        mPaintRect.setColor(Color.RED);
        mPaintRect.setStyle(Paint.Style.STROKE);
        mPaintRect.setStrokeCap(Paint.Cap.ROUND);
        mPaintRect.setAntiAlias(true);

        //延长点
        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStyle(Paint.Style.FILL);

        //延长线上的圈
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取实际的宽高
        mTotalWidth = w - getPaddingStart() - getPaddingRight();
        mTotalHeight = h - getPaddingTop() - getPaddingBottom();
        Log.d(TAG, "mTotalWidth --- > " + mTotalWidth);
        Log.d(TAG, "mTotalHeight --- > " + mTotalHeight);
        //绘制饼图所处的正方形rect
        initRectF();
    }

    private void initData() {
        if (isPieListsNull()) {
            return;
        }
        float currentStartAngle = -90;
        for (int i = 0; i < mPieLists.size(); i++) {
            PieEntry pie = mPieLists.get(i);
            pie.setCurrentStartAngle(currentStartAngle);
            //每个数据百分比对应的角度
            float sweepAngle = pie.getPercentage() / 100 * 360;
            pie.setSweepAngle(sweepAngle);  //扫描过的弧度(总角度)
            Log.d(TAG, "sweepAngle --- > " + sweepAngle);
            currentStartAngle += sweepAngle;    //设置下一个扇形的起始角度
            pie.setColor(mColorList.get(i));
            Log.d(TAG, "Color --- > " + mColorList.get(i));
        }
    }

    private void initRectF() {
        //创建一个正方形
        float shortSideLength;
        shortSideLength = Math.min(mTotalWidth, mTotalHeight);
        Log.d(TAG, "shortSideLength --- > " + shortSideLength);

        //边长/2，即为饼图的半径
        mRadius = shortSideLength / 2f;
        mRadius -= 50;
        Log.d(TAG, "mRadius --- > " + mRadius);

        mRectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标中心移到view的中心
        canvas.translate(mTotalWidth / 2f, mTotalHeight / 2f);

        //先绘制正方形
        // canvas.drawRect(mRectF, mPaintRect);

        //绘制饼图
        drawPie(canvas);
        //绘制扇形旁的延长点
       // drawPoint(canvas);
        //绘制点旁边的文本
        drawLineAndText(canvas);
    }

    //绘制饼图
    private void drawPie(Canvas canvas) {
        for (PieEntry pie : mPieLists) {
            Log.d(TAG, "pie --- > " + pie.toString());
            mPaintArc.setColor(pie.getColor());
            canvas.drawArc(mRectF, pie.getCurrentStartAngle(), pie.getSweepAngle(), true, mPaintArc);
        }
        canvas.drawCircle(0, 0, mRadius * 0.6f, mPaintCenterCircle);
    }

    private void drawLineAndText(Canvas canvas) {
        //算出延长线转折点相对起点的正余弦值
        double offsetRadians = Math.atan(yOffset / xOffset);
        float cosOffset = (float) Math.cos(offsetRadians);
        float sinOffset = (float) Math.sin(offsetRadians);

        for (PieEntry pie : mPieLists) {
            //延长点的位置处于扇形的中间
            float halfAngle = pie.getCurrentStartAngle() + pie.getSweepAngle() / 2;
            float cos = (float) Math.cos(Math.toRadians(halfAngle));
            float sin = (float) Math.sin(Math.toRadians(halfAngle));
            //通过正余弦算出延长点的位置
            float xCirclePoint = (mRadius + distance) * cos;
            float yCirclePoint = (mRadius + distance) * sin;

            mPaint.setColor(pie.getColor());
            //绘制延长点
            canvas.drawCircle(xCirclePoint, yCirclePoint, smallCircleRadius, mPaint);
            //绘制同心圆环
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(xCirclePoint, yCirclePoint, bigCircleRadius, mPaint);
            mPaint.setStyle(Paint.Style.FILL);

            //将饼图分为4个象限，从右上角开始顺时针，每90度分为一个象限
            int quadrant = (int) (halfAngle + 90) / 90;
            //初始化 延长线的起点、转折点、终点
            float xLineStartPoint = 0;
            float yLineStartPoint = 0;
            float xLineTurningPoint = 0;
            float yLineTurningPoint = 0;
            float xLineEndPoint = 0;
            float yLineEndPoint = 0;
            //创建要显示的文本
            String text = pie.getLabel() + " " +
                    new DecimalFormat("#.#").format(pie.getPercentage()) + "%";
            //延长点、起点、转折点在同一条线上
            //不同象限转折的方向不同
            float cosLength = bigCircleRadius * cosOffset;
            float sinLength = bigCircleRadius * sinOffset;
            switch (quadrant) {
                case 0:
                    xLineStartPoint = xCirclePoint + cosLength;
                    yLineStartPoint = yCirclePoint - sinLength;
                    xLineTurningPoint = xLineStartPoint + xOffset;
                    yLineTurningPoint = yLineStartPoint - yOffset;
                    xLineEndPoint = xLineTurningPoint + extend;
                    yLineEndPoint = yLineTurningPoint;
                    mPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                    break;
                case 1:
                    xLineStartPoint = xCirclePoint + cosLength;
                    yLineStartPoint = yCirclePoint + sinLength;
                    xLineTurningPoint = xLineStartPoint + xOffset;
                    yLineTurningPoint = yLineStartPoint + yOffset;
                    xLineEndPoint = xLineTurningPoint + extend;
                    yLineEndPoint = yLineTurningPoint;
                    mPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                    break;
                case 2:
                    xLineStartPoint = xCirclePoint - cosLength;
                    yLineStartPoint = yCirclePoint + sinLength;
                    xLineTurningPoint = xLineStartPoint - xOffset;
                    yLineTurningPoint = yLineStartPoint + yOffset;
                    xLineEndPoint = xLineTurningPoint - extend;
                    yLineEndPoint = yLineTurningPoint;
                    mPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                    break;
                case 3:
                    xLineStartPoint = xCirclePoint - cosLength;
                    yLineStartPoint = yCirclePoint - sinLength;
                    xLineTurningPoint = xLineStartPoint - xOffset;
                    yLineTurningPoint = yLineStartPoint - yOffset;
                    xLineEndPoint = xLineTurningPoint - extend;
                    yLineEndPoint = yLineTurningPoint;
                    mPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                    break;
                default:
                    break;
            }
            //绘制延长线
            canvas.drawLine(xLineStartPoint, yLineStartPoint, xLineTurningPoint, yLineTurningPoint, mPaint);
            canvas.drawLine(xLineTurningPoint, yLineTurningPoint, xLineEndPoint, yLineEndPoint, mPaint);
        }
    }

    public void setColors(ArrayList<Integer> colors) {
        this.mColorList = colors;
        initColors();
        invalidate();
    }

    private void initColors() {
        if (isPieListsNull()) {
            return;
        }
        for (int i = 0; i < mPieLists.size(); i++) {
            mPieLists.get(i).setColor(mColorList.get(i));
        }
    }

    public void setData(ArrayList<PieEntry> data) {
        this.mPieLists = data;
        initData();
        invalidate();
    }

    //判断数据是否为空
    private boolean isPieListsNull() {
        return mPieLists == null || mPieLists.size() == 0;
    }
}
