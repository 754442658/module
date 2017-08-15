package com.module.utils;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


import com.module.R;
import com.module.base.TApplication;

/**
 * Created by ShiShow_xk on 2017/7/24.
 */

public class VerBinnerUtils {
    public interface CallBack {
        void onSetViewListener(int position);
    }

    // 刷新轮播过后的界面数据
    private static CallBack callBack;

    // 这个是要进行垂直滚动的布局  注意此布局外面需要一个父布局，上下要有margin
    private static LinearLayout ll;
    // 显示和隐藏的动画
    private static Animation animStart, animEnd;
    private static int curPosition = 0;
    private static int listSize = 0;
    private static Handler mhander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    //消失
                    curPosition = curPosition + 1;
                    if (curPosition >= listSize) {
                        curPosition = 0;
                    }
                    ll.startAnimation(animEnd);
                    ll.setVisibility(View.GONE);
                    mhander.sendEmptyMessageDelayed(11, 400);
                    break;
                case 11:
                    //出现
                    setForeShowListValue(callBack);
                    break;
            }
        }
    };


    /**
     * 开始轮播
     *
     * @param listSize 要轮播的数据源的数量
     * @param ll       要轮播的布局
     * @param callBack 每次轮播要重新填充布局里面的数据的回调
     */
    public static void startBinner(int listSize, LinearLayout ll, CallBack callBack) {
        VerBinnerUtils.ll = ll;
        VerBinnerUtils.listSize = listSize;
        VerBinnerUtils.callBack = callBack;

        if (listSize <= 0) {
            ll.setVisibility(View.GONE);
            return;
        }
        animStart = AnimationUtils.loadAnimation(TApplication.context, R.anim.anim_binner_in_bottom);
        animEnd = AnimationUtils.loadAnimation(TApplication.context, R.anim.anim_binner_out_top);
        setForeShowListValue(callBack);
    }

    // 出现
    private static void setForeShowListValue(CallBack callBack) {
        // 出现后需要在回调方法里面设置数据
        callBack.onSetViewListener(curPosition);
        ll.startAnimation(animStart);
        ll.setVisibility(View.VISIBLE);
        mhander.sendEmptyMessageDelayed(10, 3000);
    }

    /**
     * 刷新轮播数据
     *
     * @param listSize 轮播的集合数量
     */
    public static void refresh(int listSize) {
        mhander.removeCallbacksAndMessages(null);
        ll.clearAnimation();
        VerBinnerUtils.listSize = listSize;
        setForeShowListValue(callBack);
    }

    /**
     * 销毁
     */
    public static void onDestory() {
        mhander.removeCallbacksAndMessages(null);
    }
}
