package com.qp.inst_life.model;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public interface HeadLinesModel {
    Subscription getGetHeadLinesData(String appkey,String type);
}
