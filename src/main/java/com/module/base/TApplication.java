package com.module.base;

import android.app.Activity;
import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;

/**
 * Created by ShiShow_xk on 2017/7/11.
 */
public class TApplication extends Application {
    private ArrayList<Activity> activityList = new ArrayList<Activity>();
    public static TApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            context = this;
            // 初始化Okhttps
            OkHttpUtils.init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把所有的activity注册 到集合
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除某个adtivity
     */
    public void removeActivity(Activity ac) {
        activityList.remove(ac);
    }

    /**
     * 完全退出
     */
    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
