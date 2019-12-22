package com.example.listenerdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * 使用PathMeasure完成绘制
 */
public class PathLoadingView extends View {

    private Paint mPaint;
    private int mRadius;
    private int mColor;
    private int mStrokeWidth;
    private static final int DEFAULT_RADIUS = 150;    //默认的半径
    private static final int DEFAULT_STROKE_WIDTH = 8;   //默认的Stroke width
    private Path mPath;
    private Path mDst;
    private float mAnimateValue;
    private PathMeasure mPathMeasure;
    private float mSegmentLength;     //用于记录当前圆弧长度

    public PathLoadingView(Context context) {
        super(context);
    }

    public PathLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public PathLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attributeSet){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        mDst = new Path();  //最终输入Path
        TypedArray array = context.obtainStyledAttributes(attributeSet,R.styleable.PathLoadingView);
        mRadius = array.getColor(R.styleable.PathLoadingView_path_radius,DEFAULT_RADIUS);
        mColor = array.getInt(R.styleable.PathLoadingView_path_color, ContextCompat.getColor(context,R.color.rippleColor));
        mStrokeWidth = array.getInt(R.styleable.PathLoadingView_path_strokeWidth,DEFAULT_STROKE_WIDTH);
        array.recycle();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPath.addCircle(300f,300f,mRadius,Path.Direction.CW);
        mPathMeasure = new PathMeasure(mPath,true);
        ValueAnimator animator = ValueAnimator.ofFloat(0,1f);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimateValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        mSegmentLength = mAnimateValue * mPathMeasure.getLength();
        float halfSegment = mPathMeasure.getLength() / 2;
        float start = 0;
        if(mSegmentLength > halfSegment){
            start = (mSegmentLength - halfSegment) * 2;
        }
        mPathMeasure.getSegment(start,mSegmentLength,mDst,true);
        canvas.drawPath(mDst,mPaint);
    }
}
