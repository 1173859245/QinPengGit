package com.qp.inst_life.view;

import com.qp.inst_life.bean.NewJoke;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public interface JokeView {
    void showProgress();
    void hideProgress();
    void loadWeather(NewJoke newJokes);

}
