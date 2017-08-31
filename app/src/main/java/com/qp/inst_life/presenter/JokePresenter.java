package com.qp.inst_life.presenter;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public abstract class JokePresenter extends  BasePresenter {

    public abstract void getJokeData(String key, int page,int pagesize);
}
