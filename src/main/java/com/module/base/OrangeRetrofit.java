package com.module.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.lzy.okhttputils.OkHttpUtils;
import com.module.utils.L;

import java.util.ArrayList;

/**
 * Created by ShiShow_xk on 2017/8/15.
 */

public class OrangeRetrofit {
    public static Application context;
    private static ArrayList<Activity> activityList = new ArrayList<Activity>();

    public static void init(Application context) {
        if (null != context) {
            OrangeRetrofit.context = context;
            OkHttpUtils.init(context);
        }
    }

    /**
     * 把所有的activity注册 到集合
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除某个adtivity
     */
    public static void removeActivity(Activity ac) {
        activityList.remove(ac);
    }

    /**
     * 完全退出
     */
    public static void exit() {
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
