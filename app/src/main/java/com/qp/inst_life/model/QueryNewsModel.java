package com.qp.inst_life.model;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public interface QueryNewsModel  {

    Subscription getQueryNewsData(String key,String q);
}
