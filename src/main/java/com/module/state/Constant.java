package com.module.state;

import android.os.Environment;

import java.io.File;

import com.module.R;


public class Constant {
    // 默认头像图片
    public static final int PHOTO = R.drawable.head_none;
    // 默认背景图片
    public static final int BAC = R.drawable.ic_launcher;
    // 出错头像图片
    public static final int ERROR_PHOTO = R.drawable.head_none;
    // 出错背景图片
    public static final int ERROR_BAC = R.drawable.ic_launcher;


    // 是否是debug模式
    public static final boolean isDebug = true;
    // 应用缓存文件
    public static final File CACHE_FILE = new File(Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/MyCache");
}
