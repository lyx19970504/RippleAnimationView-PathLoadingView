package com.example.listenerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class RippleCircleView extends View {

    private static final String TAG = "RippleCircleView";

    private RippleAnimationView mRippleAnimationView;
    private int mStrokeWidth;
    private Paint mPaint;

    public RippleCircleView(RippleAnimationView rippleAnimationView) {
        this(rippleAnimationView.getContext(),null);
        mRippleAnimationView = rippleAnimationView;
        mStrokeWidth = mRippleAnimationView.mStrokeWidth;
        mPaint = mRippleAnimationView.mPaint;
        setVisibility(INVISIBLE);
    }

    public RippleCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RippleCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = Math.min(getWidth(),getHeight()) / 2;
        canvas.drawCircle(radius,radius,radius - mStrokeWidth,mPaint);
    }
}
