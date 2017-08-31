package com.qp.inst_life.presenter;

import android.util.Log;

import com.qp.inst_life.bean.NewJoke;
import com.qp.inst_life.model.JokeModelImp;
import com.qp.inst_life.model.NewJokeModel;
import com.qp.inst_life.view.JokeView;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class JokePresenterImi extends JokePresenter implements JokeModelImp.JokeOnListener {
    /**
     * WeatherModel和WeatherView都是通过接口来实现，这就Java设计原则中依赖倒置原则使用
     */
    private NewJokeModel mJoleModel;
    private JokeView mJokeView;

    public JokePresenterImi( JokeView mJokeView) {
        this.mJoleModel = new JokeModelImp(this);
        this.mJokeView = mJokeView;
    }
    @Override
    public void onSuccess(NewJoke s) {
      mJokeView.loadWeather(s);
      mJokeView.hideProgress();
        Log.d("-------", "onSuccess() called with: " + "s = [" + s.toString() + "]");
    }

    @Override
    public void onFailure(Throwable e) {
        mJokeView.hideProgress();
        Log.e("-------", "onFailure" + e.getMessage());
    }

    @Override
    public void getJokeData(String key, int page, int pagesize) {
        mJokeView.showProgress();
        addSubscription(mJoleModel.getJokeData(key,page,pagesize));
    }
}
