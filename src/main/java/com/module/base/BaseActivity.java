package com.module.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import com.module.R;
import com.module.utils.T;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener, AdapterView
        .OnItemClickListener {

    // 整个Bar,title布局
    private AutoRelativeLayout rl_root, rl_title;
    // 左侧text 标题 右侧text
    private TextView tv_left, tv_mid, tv_right;
    // 左侧布局 右侧布局
    private AutoLinearLayout ll_left, ll_right;
    // 右侧img
    private ImageView img_right;

    // 打开activity动画
    private boolean inAnim;
    // 关闭activity动画
    private boolean outAnim;


    public int request_code = 1;
    // SD卡读写权限
    public String[] SDpermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.title);
        try {
            addActivity();
            initTitle();
//            findView();
//            initView();
//            addListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化设置标题栏
     */
    private void initTitle() {
        rl_root = (AutoRelativeLayout) findViewById(R.id.rl_root);
        rl_title = (AutoRelativeLayout) findViewById(R.id.rl_title);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_mid = (TextView) findViewById(R.id.tv_mid);
        tv_right = (TextView) findViewById(R.id.tv_right);
        ll_left = (AutoLinearLayout) findViewById(R.id.ll_left);
        ll_right = (AutoLinearLayout) findViewById(R.id.ll_right);
        img_right = (ImageView) findViewById(R.id.img_right);

        tv_left.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        tv_mid.setVisibility(View.GONE);
        img_right.setVisibility(View.GONE);
        rl_title.setVisibility(View.VISIBLE);
        // 开启进入/退出界面动画
        inAnim = true;
        outAnim = true;
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void setContentView(int layoutResID) {
        // 子类的activity的布局view
        View view = getLayoutInflater().inflate(layoutResID, null);
        AutoRelativeLayout.LayoutParams lp = new AutoRelativeLayout.LayoutParams(AutoRelativeLayout.LayoutParams.MATCH_PARENT, AutoRelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.rl_title);
        if (rl_root != null)
            rl_root.addView(view, lp);
    }

    /**
     * 隐藏/显示title
     */
    public void showRoot(boolean isShow) {
        if (isShow) {
            rl_title.setVisibility(View.VISIBLE);
        } else {
            rl_title.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示左侧的Text
     *
     * @param isShow
     */
    public void showLeftText(boolean isShow) {
        if (isShow) {
            tv_left.setVisibility(View.VISIBLE);
        } else {
            tv_left.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示右侧的Text
     *
     * @param isShow
     */
    public void showRightText(boolean isShow) {
        if (isShow) {
            tv_right.setVisibility(View.VISIBLE);
        } else {
            tv_right.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示标题中间的Text
     *
     * @param isShow
     */
    public void showTitleText(boolean isShow) {
        if (isShow) {
            tv_mid.setVisibility(View.VISIBLE);
        } else {
            tv_mid.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示右侧的ImageView
     *
     * @param isShow
     */
    public void showRightImg(boolean isShow) {
        if (isShow) {
            img_right.setVisibility(View.VISIBLE);
        } else {
            img_right.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左侧text文本
     *
     * @param text
     */
    public void setLeftText(String text) {
        tv_left.setText(text);
    }

    /**
     * 设置右侧text文本
     *
     * @param text
     */
    public void setRightText(String text) {
        tv_right.setText(text);
    }

    /**
     * 设置标题text文本
     *
     * @param text
     */
    public void setTitle(String text) {
        tv_mid.setText(text);
    }

    /**
     * 设置右侧img图片
     *
     * @param resId
     */
    public void setRightImg(int resId) {
        img_right.setImageResource(resId);
    }

    /**
     * 获取左侧布局以设置点击事件
     */
    public AutoLinearLayout getLeft() {
        return ll_left;
    }

    /**
     * 获取右侧布局已设置点击事件
     */
    public AutoLinearLayout getRight() {
        return ll_right;
    }

    public void addActivity() {
        try {
            TApplication.context.addActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeActivity() {
        try {
            TApplication.context.removeActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            removeActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断用户是否给权限，没有给的话索要权限
     *
     * @param permission   权限数组
     * @param request_code 请求权限请求码
     * @return true 有权限，false 无权限
     */
    public boolean getPermission(String[] permission, int request_code) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permission.length; i++) {
                int permissionState = ContextCompat.checkSelfPermission(this, permission[i]);
                if (permissionState == PackageManager.PERMISSION_DENIED) {
                    // 用户未授予权限，索要权限
                    ActivityCompat.requestPermissions(this, permission, request_code);
                    return false;
                }
            }
        }
        if (callBack != null)
            callBack.hasPermission(true);
        return true;
    }

    public interface CallBack {
        /**
         * 用户是否已授权
         *
         * @param hasPermission
         */
        void hasPermission(boolean hasPermission);
    }

    public CallBack callBack;

    /**
     * 用户授权结束，判断是否获取权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == request_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户已授权
                if (callBack != null)
                    callBack.hasPermission(true);
            } else {
                // 用户未授权
                if (callBack != null)
                    callBack.hasPermission(false);
            }
        }
    }

    public boolean isInAnim() {
        return inAnim;
    }

    /**
     * 设置进入界面动画
     *
     * @param inAnim
     */
    public void setInAnim(boolean inAnim) {
        this.inAnim = inAnim;
    }

    public boolean isOutAnim() {
        return outAnim;
    }

    /**
     * 设置退出界面动画
     *
     * @param outAnim
     */
    public void setOutAnim(boolean outAnim) {
        this.outAnim = outAnim;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (inAnim) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (inAnim) {
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (outAnim) {
            overridePendingTransition(R.anim.out_to_right, R.anim.in_from_left);
        }
    }


    // 当前点击的时间
    public long curTime = 0;

    /**
     * 双击退出
     */
    public void backDown() {
        long time1 = System.currentTimeMillis();
        if (time1 - curTime <= 2000) {
            TApplication.context.exit();
            super.onBackPressed();
        } else {
            curTime = time1;
            T.showShort(this, "再按一次退出");
        }
    }
}
