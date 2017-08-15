package com.module.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by ShiShow_xk on 2017/8/15.
 * 1. 当一个fragment被创建的时候，它会经历以下状态.。
 * onAttach()
 * onCreate()
 * onCreateView()
 * onActivityCreated()
 * 2. 当这个fragment对用户可见的时候，它会经历以下状态。
 * onStart()
 * onResume()
 * 3. 当这个fragment进入“后台模式”的时候，它会经历以下状态。
 * onPause()
 * onStop()
 * 4. 当这个fragment被销毁了（或者持有它的activity被销毁了），它会经历以下状态。
 * onPause()
 * onStop()
 * onDestroyView()
 * onDestroy() // 本来漏掉类这个回调，感谢xiangxue336提出。
 * onDetach()
 * <p>
 * onAttached() —— 当fragment和activity关联之后，调用这个方法。
 * onCreateView() —— 创建fragment中的视图的时候，调用这个方法。
 * onActivityCreated() —— 当activity的onCreate()方法被返回之后，调用这个方法。
 * onDestroyView() —— 当fragment中的视图被移除的时候，调用这个方法。
 * onDetach() —— 当fragment和activity分离的时候，调用这个方法。
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, DialogInterface.OnDismissListener, BaseFunction {

    private View contentView;
    private FragmentCallBack callBack;

    interface FragmentCallBack {
        void callBack(Object... arg);
    }

    /**
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallBack) {
            callBack = (FragmentCallBack) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            findView();
            initView();
            addListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public void setContentView(int id) {
        this.contentView = getActivity().getLayoutInflater().inflate(id, null);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    public FragmentCallBack getCallBack() {
        return callBack;
    }
}
