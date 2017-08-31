package com.qp.inst_life.presenter;

import com.qp.inst_life.bean.Headlines;
import com.qp.inst_life.model.HeadLinesModel;
import com.qp.inst_life.model.HeadLinesModelImp;
import com.qp.inst_life.view.HeadLinesView;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class HeadLinesPresenterlmi extends HeadLinesPresenter implements HeadLinesModelImp.HeadLinesListener {

    private HeadLinesModel headLinesModel;
    private HeadLinesView headLinesView;

    public HeadLinesPresenterlmi( HeadLinesView headLinesView) {
        this.headLinesModel = new HeadLinesModelImp(this);
        this.headLinesView = headLinesView;
    }

    @Override
    public void getHeadLinesData(String key, String type) {
        headLinesView.showProgress();
        addSubscription(headLinesModel.getGetHeadLinesData(key,type));

    }

    @Override
    public void onSuccess(Headlines s) {
        headLinesView.loadHeadLines(s);
        headLinesView.hideProgress();

    }

    @Override
    public void onFailure(Throwable e) {
    headLinesView.hideProgress();
    }
}
