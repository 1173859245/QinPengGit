package com.qp.inst_life.model;

import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.bean.QueryNews;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class QueryNewsModelImp implements QueryNewsModel{
    private QueryNewsListener queryNewsListener;

    public QueryNewsModelImp(QueryNewsListener queryNewsListener) {
        this.queryNewsListener = queryNewsListener;
    }

    @Override
    public Subscription getQueryNewsData(String key, String q) {

        Observable<QueryNews> request = ApiManager.getQueryNewsData(key,q);

        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<QueryNews>() {
                    @Override
                    public void call(QueryNews queryNews) {
                        queryNewsListener.onSuccess(queryNews);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        queryNewsListener.onFailure(throwable);
                    }
                });
        return  sub;


    }

    /**
     *回调接口
     */
    public interface QueryNewsListener{
        void onSuccess(QueryNews s);
        void onFailure(Throwable e);
    }
}
