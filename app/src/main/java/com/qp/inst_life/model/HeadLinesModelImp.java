package com.qp.inst_life.model;

import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.bean.Headlines;



import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class HeadLinesModelImp implements HeadLinesModel {

    private HeadLinesListener headLinesListener;

    public HeadLinesModelImp(HeadLinesListener headLinesListener) {
        this.headLinesListener = headLinesListener;
    }

    @Override
    public Subscription getGetHeadLinesData(String appkey, String type) {
        Observable<Headlines> request = ApiManager.getHeadLinesData(appkey,type);

        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Headlines>() {
                    @Override
                    public void call(Headlines headlines) {
                      headLinesListener.onSuccess(headlines);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        headLinesListener.onFailure(throwable);
                    }
                });
        return  sub;
    }

    /**
     *回调接口
     */
    public interface HeadLinesListener{
        void onSuccess(Headlines s);
        void onFailure(Throwable e);
    }
}
