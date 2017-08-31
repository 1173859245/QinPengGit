package com.qp.inst_life.presenter;

import com.qp.inst_life.bean.QueryNews;
import com.qp.inst_life.fragment.Fragment2;
import com.qp.inst_life.model.QueryNewsModel;
import com.qp.inst_life.model.QueryNewsModelImp;
import com.qp.inst_life.view.QueryNewsView;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class QueryNewsPresenterlmi extends QueryNewsPresenter implements QueryNewsModelImp.QueryNewsListener {
    private QueryNewsModel queryNewsModel;
    private QueryNewsView queryNewsView;


    public QueryNewsPresenterlmi(QueryNewsView queryNewsView) {
        this.queryNewsModel = new QueryNewsModelImp(this);
        this.queryNewsView = queryNewsView;
    }



    @Override
    public void getQueryNewsData(String key, String q) {
        queryNewsView.showProgress();
        addSubscription(queryNewsModel.getQueryNewsData(key,q));

    }

    @Override
    public void onSuccess(QueryNews s) {
        queryNewsView.loadQueryNews(s);
        queryNewsView.hideProgress();

    }

    @Override
    public void onFailure(Throwable e) {
      queryNewsView.hideProgress();
    }
}
