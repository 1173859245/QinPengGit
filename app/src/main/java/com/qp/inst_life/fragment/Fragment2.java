package com.qp.inst_life.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qp.inst_life.R;
import com.qp.inst_life.activity.WebViewActivity;
import com.qp.inst_life.adapter.QueryNewsAdapter;
import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.bean.QueryNews;
import com.qp.inst_life.presenter.QueryNewsPresenter;
import com.qp.inst_life.presenter.QueryNewsPresenterlmi;
import com.qp.inst_life.view.QueryNewsView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zly.www.easyrecyclerview.EasyRecyclerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Fragment2 extends Fragment implements QueryNewsView {
    @BindView(R.id.edtext)
    MaterialEditText edtext;

    Unbinder unbinder;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.query_recy)
    EasyRecyclerView queryRecy;
    private String q;//需检索的参数
    private QueryNewsAdapter adapter;
    private List<QueryNews.ResultBean> datas = new ArrayList<>();
    private View view;
    private QueryNewsPresenter queryNewsPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        queryNewsPresenter=new QueryNewsPresenterlmi(this);
        queryNewsPresenter.getQueryNewsData(ApiManager.QUERYNEWS_KEY,"上海");
        SearchOnClick();
         return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void loadQueryNews(QueryNews queryNews) {
        Log.e("TAG",queryNews.toString());
        int error_code = queryNews.getError_code();
        if (error_code == 0) {
            datas = queryNews.getResult();
            inintContentRecy();
            clickItemIntoWebView();
        } else if (error_code == 213802) {

            Snackbar.make(view, "查询不到相关内容哦！", Snackbar.LENGTH_SHORT)
                    .setAction("提示", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setDuration(2000)
                    .show();
        }

    }

    /**
     * 初始化RecyclerView
     */
    private void inintContentRecy() {
        queryRecy.showEmptyView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        queryRecy.setLayoutManager(linearLayoutManager);
        adapter = new QueryNewsAdapter(getActivity());
        adapter.addAll(datas);
        queryRecy.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
    /**
     * 输入关键字检索
     */
    private  void SearchOnClick(){
          searchBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  q=edtext.getText().toString();
                  if (TextUtils.isEmpty(edtext.getText())){
                      Snackbar.make(view, "请输入搜索内容！", Snackbar.LENGTH_SHORT)
                              .setAction("提示", new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                  }
                              })
                              .setDuration(2000)
                              .show();
                  }else {
                      queryNewsPresenter.getQueryNewsData(ApiManager.QUERYNEWS_KEY,q);
                  }
              }
          });
    }

    /**
     * 点击列表进webView
     */
    private void clickItemIntoWebView(){
        adapter.setOnItemClickLitener(new QueryNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                String url= (String) textView.getTag();
                Log.e("TAG","url"+url);
                Intent intent=new Intent(getActivity().getApplicationContext(),WebViewActivity.class);
                intent.putExtra("url",url);
                getActivity().startActivity(intent);
            }
        });
    }

}
