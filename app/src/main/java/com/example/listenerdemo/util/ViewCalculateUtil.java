package com.example.listenerdemo.util;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewCalculateUtil
{
    private static final String TAG = ViewCalculateUtil.class.getSimpleName();

    /**
     * 根据屏幕的大小设置view的高度，间距
     *
     * @param view
     * @param width
     * @param height
     * @param topMargin
     * @param bottomMargin
     * @param lefMargin
     * @param rightMargin
     */
    public static void setViewLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin, int rightMargin)
    {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

        if (layoutParams != null)
        {
            if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT)
            {
                layoutParams.width = UIUtil.getInstance().getWidth(width);
            }
            else
            {
                layoutParams.width = width;
            }
            if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT)
            {
                layoutParams.height = UIUtil.getInstance( ).getHeight(height);
            }
            else
            {
                layoutParams.height = height;
            }

            layoutParams.topMargin = UIUtil.getInstance( ).getHeight(topMargin);
            layoutParams.bottomMargin = UIUtil.getInstance( ).getHeight(bottomMargin);
            layoutParams.leftMargin = UIUtil.getInstance( ).getWidth(lefMargin);
            layoutParams.rightMargin = UIUtil.getInstance( ).getWidth(rightMargin);
            view.setLayoutParams(layoutParams);
        }
        else
        {
        }
    }

    /**
     * @param view
     * @param width
     * @param height
     * @param topMargin
     * @param bottomMargin
     * @param lefMargin
     * @param rightMargin
     */
    public static void setViewFrameLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                               int rightMargin)
    {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.width = UIUtil.getInstance( ).getWidth(width);
        }
        else
        {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.height = UIUtil.getInstance( ).getHeight(height);
        }
        else
        {
            layoutParams.height = height;
        }

        layoutParams.topMargin = UIUtil.getInstance( ).getHeight(topMargin);
        layoutParams.bottomMargin = UIUtil.getInstance( ).getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtil.getInstance( ).getWidth(lefMargin);
        layoutParams.rightMargin = UIUtil.getInstance( ).getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置view的内边距
     *
     * @param view
     * @param topPadding
     * @param bottomPadding
     * @param leftpadding
     * @param rightPadding
     */
    public static void setViewPadding(View view, int topPadding, int bottomPadding, int leftpadding, int rightPadding)
    {
        view.setPadding(UIUtil.getInstance( ).getWidth(leftpadding),
                UIUtil.getInstance( ).getHeight(topPadding),
                UIUtil.getInstance( ).getWidth(rightPadding),
                UIUtil.getInstance( ).getHeight(bottomPadding));
    }

    /**
     * 设置字号
     *
     * @param view
     * @param size
     */
    public static void setTextSize(TextView view, int size)
    {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtil.getInstance( ).getHeight(size));
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParam(View view, int width, int height)
    {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.width = UIUtil.getInstance( ).getWidth(width);
        }
        else
        {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.height = UIUtil.getInstance( ).getHeight(height);
        }
        else
        {
            layoutParams.height = height;
        }

        view.setLayoutParams(layoutParams);
    }

    public static void setViewGroupLayoutParam(View view, int width, int height)
    {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.width = UIUtil.getInstance( ).getWidth(width);
        }
        else
        {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.height = UIUtil.getInstance( ).getHeight(height);
        }
        else
        {
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                                int rightMargin)
    {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.width = UIUtil.getInstance( ).getWidth(width);
        }
        else
        {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT)
        {
            layoutParams.height = UIUtil.getInstance( ).getHeight(height);
        }
        else
        {
            layoutParams.height = height;
        }

        layoutParams.topMargin = UIUtil.getInstance( ).getHeight(topMargin);
        layoutParams.bottomMargin = UIUtil.getInstance( ).getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtil.getInstance( ).getWidth(lefMargin);
        layoutParams.rightMargin = UIUtil.getInstance( ).getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }
}
