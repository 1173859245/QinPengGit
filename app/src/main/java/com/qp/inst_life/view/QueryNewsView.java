package com.qp.inst_life.view;

import com.qp.inst_life.bean.Headlines;
import com.qp.inst_life.bean.QueryNews;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public interface QueryNewsView {
    void showProgress();
    void hideProgress();
    void loadQueryNews(QueryNews queryNews);
}
