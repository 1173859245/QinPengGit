package com.qp.inst_life.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qp.inst_life.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public abstract class BaseActivity  extends AppCompatActivity{
    /**
     * 记录所有活动的Activity
     */

    //布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();
    private Unbinder mUnBinder;

    private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        inintTranslationBar();
        initData();
       mUnBinder=ButterKnife.bind(this);

    }

    private void inintTranslationBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#18D764"));//可以自定义状态栏颜色
        }

    }



    abstract void initData();
    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }
    /**
     * 退出应用
     */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    mUnBinder.unbind();

    }




    //添加fragment
    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


}
