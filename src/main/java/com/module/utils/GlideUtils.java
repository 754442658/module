package com.module.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;
import com.module.state.Constant;

/**
 * Created by ShiShow_xk on 2017/1/7.
 */
public class GlideUtils {


    /**
     * 设置高斯模糊
     *
     * @param context
     * @param img     要设置高斯模糊的控件
     * @param bacImg  要设置高斯模糊的图片资源Id
     */
    public static void setBac(Context context, ImageView img, int bacImg) {
        // 设置高斯模糊背景
        Glide.with(context)
                .load(bacImg)
                .crossFade(1000)
                .bitmapTransform(new BlurTransformation(context, 23, 4)) // “23”：设置模糊度(在0.0到25.0之间)
                // ，默认”25";"4":图片缩放比例,默认“1”。
                .into(img);
    }

    /**
     * 加载头像
     *
     * @param context
     * @param url
     * @param img
     */
    public static void showPhoto(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url).asBitmap()
                .dontAnimate()
                .placeholder(Constant.PHOTO)
                .error(Constant.ERROR_PHOTO)
                .into(img);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param img
     */
    public static void showPic(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url).asBitmap()
                .dontAnimate()
                .placeholder(Constant.BAC)
                .error(Constant.ERROR_BAC)
                .into(img);
    }
}
