package com.qp.inst_life.fragment;


import android.support.v4.app.Fragment;

import com.qp.inst_life.activity.BaseActivity;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public  abstract class BaseFragment  extends Fragment{
    protected BaseActivity mActivity;







    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
