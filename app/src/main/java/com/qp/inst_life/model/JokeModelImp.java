package com.qp.inst_life.model;

import android.util.Log;

import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.bean.NewJoke;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class JokeModelImp implements NewJokeModel {
  private JokeOnListener jokeOnListener;

    public JokeModelImp(JokeOnListener jokeOnListener) {
        this.jokeOnListener = jokeOnListener;
    }




    @Override
    public Subscription getJokeData(String appKey, int page, int PageSize) {

        Observable<NewJoke> request = ApiManager.getJokeData(appKey,page,PageSize).cache();

        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewJoke>() {
                    @Override
                    public void call(NewJoke newJokes) {
                     jokeOnListener.onSuccess(newJokes);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        jokeOnListener.onFailure(throwable);
                        Log.e("throwable",throwable.toString());
                    }
                });
        return  sub;

    }

    /**
     *回调接口
     */
    public interface JokeOnListener{
        void onSuccess(NewJoke s);
        void onFailure(Throwable e);
    }
}
