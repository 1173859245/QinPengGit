package com.qp.inst_life.model;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public interface NewJokeModel {


    Subscription getJokeData(String appKey,int page,int PageSize);
}
