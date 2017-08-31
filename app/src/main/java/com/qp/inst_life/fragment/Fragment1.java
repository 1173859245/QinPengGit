package com.qp.inst_life.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.adapter.JokeAdapter;
import com.qp.inst_life.bean.NewJoke;
import com.qp.inst_life.presenter.JokePresenter;
import com.qp.inst_life.presenter.JokePresenterImi;
import com.qp.inst_life.R;
import com.qp.inst_life.view.JokeView;
import com.qp.inst_life.customview.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Fragment1 extends Fragment implements JokeView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Unbinder unbinder;
    @BindView(R.id.recy_joke)
    RefreshRecyclerView recyJoke;
    private List<NewJoke.ResultBean.DataBean> datas = new ArrayList<>();;
    private JokePresenter jokePresenter;
    private boolean isErr;
    private boolean mLoadMoreEndGone = false;
    private JokeAdapter jokeAdapter;
    private int curryPage = 1;
    private static int dataCountSize=6;
    private int lastVisibleItem;
    LinearLayoutManager ll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        jokePresenter = new JokePresenterImi(this);
        jokePresenter.getJokeData(ApiManager.JOKE_KEY, curryPage, dataCountSize);
        initListener();
        return view;

    }


    private void initViewAndAdapter() {
        ll = new LinearLayoutManager(getActivity());
        recyJoke.setLayoutManager(ll);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.rgb(47, 223, 189));
        jokeAdapter = new JokeAdapter(datas,getActivity());
        recyJoke.setLoadMoreEnable(true);//允许加载更多
        recyJoke.setFooterResource(R.layout.footer_view);//设置脚布局
        recyJoke.setAdapter(jokeAdapter);
        recyJoke.notifyData();
         Log.e("TAG","laile");


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadWeather(NewJoke newJokes) {
        int error_code = newJokes.getError_code();
        if (error_code == 0) {
            swipe.setRefreshing(false);
           // datas = newJokes.getResult().getData();
            datas.addAll(newJokes.getResult().getData());
            initViewAndAdapter();
            Log.e("TAG","datas"+datas.toString());


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        jokePresenter.onUnsubscribe();
    }


    @Override
    public void onRefresh() {
        Log.e("ssssss", "执行刷新");
        curryPage=1;
        datas.clear();
        jokePresenter.getJokeData(ApiManager.JOKE_KEY, curryPage, dataCountSize);
        recyJoke.notifyData();
        swipe.setRefreshing(false);


    }



    private void initListener() {
            recyJoke.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
                @Override
                public void loadMoreListener() {
                    curryPage++;
                    jokePresenter.getJokeData(ApiManager.JOKE_KEY, curryPage, dataCountSize);
                    Log.e("curryPage",curryPage+"");




                }
            });

        }




}