package com.qp.inst_life.view;

import com.qp.inst_life.bean.Headlines;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public interface HeadLinesView {
    void showProgress();
    void hideProgress();
    void loadHeadLines(Headlines headlines);
}
