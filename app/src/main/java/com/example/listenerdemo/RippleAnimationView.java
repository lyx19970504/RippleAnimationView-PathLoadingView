package com.example.listenerdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.listenerdemo.util.UIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RippleAnimationView extends RelativeLayout {

    private static final String TAG = "RippleAnimationView";
    public Paint mPaint;
    private int mAnim_Color;
    private int mAnim_Type;
    public int mRadius;
    public int mStrokeWidth;
    private static final int DEFAULT_RADIUS = 54;
    private static final int DEFAULT_STROKE_WIDTH = 2;
    private static final int RIPPLE_COUNT = 4;   //水波纹数量，可自行调整
    private static final int RIPPLE_DURATION = 3500; //动画一共完成时间，可自行调整
    private AnimatorSet mAnimatorSet;
    private List<RippleCircleView> mViewList;
    public static final int maxScale = 10;  //最大缩放系数
    private boolean animationRunning = false;   //动画是否执行中


    public RippleAnimationView(Context context) {
        super(context);
    }

    public RippleAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        UIUtils.getInstance(context);
        mViewList = new ArrayList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.RippleAnimationView);
        mAnim_Color = array.getColor(R.styleable.RippleAnimationView_ripple_anim_color, ContextCompat.getColor(context, R.color.rippleColor));
        mAnim_Type = array.getInt(R.styleable.RippleAnimationView_ripple_anim_type, 0);
        if (mAnim_Type == 0) {
            mPaint.setStyle(Paint.Style.FILL);
        } else if (mAnim_Type == 1) {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mRadius = array.getInt(R.styleable.RippleAnimationView_radius, DEFAULT_RADIUS);
        mStrokeWidth = array.getInt(R.styleable.RippleAnimationView_strokeWidth, DEFAULT_STROKE_WIDTH);
        array.recycle();
        mPaint.setStrokeWidth(UIUtils.getInstance().getWidth(mStrokeWidth));
        mPaint.setColor(mAnim_Color);

        LayoutParams params = new LayoutParams(UIUtils.getInstance().getWidth(mRadius + mStrokeWidth),
                UIUtils.getInstance().getWidth(mRadius + mStrokeWidth));
        params.addRule(CENTER_IN_PARENT, TRUE);

        int singleDelay = RIPPLE_DURATION / RIPPLE_COUNT;   //单个水波纹执行时间
        List<Animator> list = new ArrayList<>();

        for (int i = 0; i < RIPPLE_COUNT; i++) {   //产生4条水波纹，这个可以自行设置数量
            RippleCircleView rippleCircleView = new RippleCircleView(this);
            addView(rippleCircleView,params);
            mViewList.add(rippleCircleView);

            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleCircleView, View.SCALE_X,1f,maxScale);
            scaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ValueAnimator.RESTART);
            scaleXAnimator.setDuration(RIPPLE_DURATION);
            scaleXAnimator.setStartDelay(i * singleDelay);
            list.add(scaleXAnimator);

            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleCircleView,View.SCALE_Y,1f,maxScale);
            scaleYAnimator.setRepeatMode(ValueAnimator.RESTART);
            scaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
            scaleYAnimator.setStartDelay(i * singleDelay);
            scaleYAnimator.setDuration(RIPPLE_DURATION);
            list.add(scaleYAnimator);

            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleCircleView,View.ALPHA,1.0f,0);
            alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ValueAnimator.RESTART);
            alphaAnimator.setStartDelay(i * singleDelay);
            alphaAnimator.setDuration(RIPPLE_DURATION);
            list.add(alphaAnimator);
        }

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(list);
        mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());   //先加速后减速插值器
    }


    public void startAnimation(){
        if(!animationRunning){
            for (RippleCircleView view : mViewList){
                view.setVisibility(VISIBLE);
            }
            mAnimatorSet.start();
            animationRunning = true;
        }
    }

    public void stopAnimation(){
        if(animationRunning){
            Collections.reverse(mViewList);
//            for (RippleCircleView view : mViewList){
//                view.setVisibility(INVISIBLE);
//            }
            mAnimatorSet.end();
            animationRunning = false;
        }
    }

    public boolean isRunning(){
        return animationRunning;
    }
}
