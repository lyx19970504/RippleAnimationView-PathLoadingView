package com.example.listenerdemo.util;

import android.content.Context;
import android.mtp.MtpConstants;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtil {

    private static UIUtil sUIUtil;
    private static final float STANDARD_WIDTH = 1080f;
    private static final float STANDARD_HEIGHT = 1920f;
    private static float display_width;
    private static float display_height;

    private static final String STATUS_BAR_HEIGHT = "status_bar_height";
    private static final String SYSTEM_BAR_HEIGHT = "system_bar_height";
    private static final String DIMEN = "dimen";
    private static final String PACKAGE = "android";
    private static final String CLASS_NAME = "com.android.internal.R$dimen";
    private static final int DEFAULT_HEIGHT = 48;

    public static void initialize(Context context){
        if(sUIUtil == null){
            sUIUtil = new UIUtil(context);
        }
    }

    public static UIUtil getInstance(){
        if(sUIUtil == null){
            throw new RuntimeException("Please initial this util first!");
        }
        return sUIUtil;
    }

    private UIUtil(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager != null){
            if(display_width != 0 || display_height != 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int systemBarHeight = getSystemBarHeight(context);
                //竖屏
                if(displayMetrics.heightPixels > displayMetrics.widthPixels){
                    display_width = (float) displayMetrics.widthPixels;
                    display_height = (float) displayMetrics.heightPixels - systemBarHeight;
                }else{
                    display_width = (float) displayMetrics.heightPixels;
                    display_height = (float) displayMetrics.widthPixels - systemBarHeight;
                }
            }
        }
    }

    public int getWidth(int width){
        return Math.round(width * display_width / STANDARD_WIDTH);
    }

    public int getHeight(int height){
        return Math.round(height * display_height / STANDARD_HEIGHT);
    }

    private int getSystemBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier(STATUS_BAR_HEIGHT,DIMEN,PACKAGE);
        int height = context.getResources().getDimensionPixelSize(resourceId);
        if(height != -1){
            return height;
        }
        return getValue(context);
    }

    private int getValue(Context context){
        try {
            Class<?> clazz = Class.forName(CLASS_NAME);
            Object object = clazz.newInstance();
            Field field = clazz.getField(SYSTEM_BAR_HEIGHT);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_HEIGHT;
    }
}
