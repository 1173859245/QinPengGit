package com.qp.inst_life.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qp.inst_life.api.ApiManager;
import com.qp.inst_life.activity.WebViewActivity;
import com.qp.inst_life.adapter.HeadLInesAdapter;
import com.qp.inst_life.bean.Headlines;
import com.qp.inst_life.bean.HeadlinesTitle;
import com.qp.inst_life.presenter.HeadLinesPresenter;
import com.qp.inst_life.presenter.HeadLinesPresenterlmi;
import com.qp.inst_life.R;
import com.qp.inst_life.view.HeadLinesView;
import com.qp.inst_life.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Fragment4 extends Fragment implements HeadLinesView{

    private HeadLinesPresenter presenter;
    private List<HeadlinesTitle> strings;
    private List<Headlines.ResultBean.DataBean> datas=new ArrayList<>();
    private TitleAdapter titleAdapter;
    private RecyclerView headRecy;
    private RecyclerView contetRecy;
    private HeadLInesAdapter adapter;
    private int mTouchSlop;         //滑动限定停止值
    private float mFirstY;
    private float mCurrentY;
    private RelativeLayout mtitle;
    private RelativeLayout mcontent;
    private FloatingActionButton fab;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment4,container,false);
        Utils.init(getActivity());
        mtitle= (RelativeLayout) view.findViewById(R.id.title);
        mcontent=(RelativeLayout)view.findViewById(R.id.content);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        headRecy=(RecyclerView)view.findViewById(R.id.title_Recy);
        contetRecy=(RecyclerView)view.findViewById(R.id.ContentRecy);
        progressBar=(ProgressBar)view.findViewById(R.id.progressbar);
        inintContentRecy();
        fabonClickEvent();
        contetRecyOnScrollEvent();
        showOrHide(true);
        //头条数据获取
        initTitleData();
        titleAdapter=new TitleAdapter(strings,getActivity());
        LinearLayoutManager ll=new LinearLayoutManager(getActivity());
        ll.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecy.setLayoutManager(ll);
        headRecy.setAdapter(titleAdapter);
        clickTitileGetData();
        //内容数据获取
        presenter=new HeadLinesPresenterlmi(this);
        presenter.getHeadLinesData(ApiManager.HEADLINES_KEY,"top");

        return view;
    }

    @Override
    public void showProgress() {
      progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 回调获取数据
     * @param headlines
     */
    @Override
    public void loadHeadLines(Headlines headlines) {
        int error_code = headlines.getError_code();
        if (error_code == 0) {
             datas = headlines.getResult().getData();
             inintContentRecy();

        }
    }

    /**
     * FloatingActionButton点击事件
     */
    private void fabonClickEvent(){
          fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contetRecy.smoothScrollToPosition(0);
            }
        });

    }

/**
 * contetRecy滑动事件判断
 */
  private void contetRecyOnScrollEvent(){
      contetRecy.setOnScrollListener(new RecyclerView.OnScrollListener() {
          @Override
          public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
              super.onScrolled(recyclerView, dx, dy);

              if (dy>0){
                  fab.setVisibility(View.VISIBLE);
              }else {
                  fab.setVisibility(View.GONE);
              }
          }
      });
  }


    /**
     * 新闻内容的数据注册
     */
    private void inintContentRecy(){
        LinearLayoutManager ll=new LinearLayoutManager(getActivity());
        contetRecy.setLayoutManager(ll);
        adapter=new HeadLInesAdapter(datas,getActivity());
        contetRecy.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        clickItemIntoWebView();
        contetRecy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        if (mCurrentY - mFirstY > mTouchSlop) {
                            showOrHide(true); //下滑 显示titlebar
                        } else if (mFirstY - mCurrentY > mTouchSlop) {
                            showOrHide(false); //上滑 隐藏titlebar
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
    /**
     * 点击列表item进入详情webView
     */
    private void clickItemIntoWebView(){
      adapter.setOnItemClickLitener(new HeadLInesAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(View view, TextView textView, int position) {
                  String url= (String) textView.getTag();
                 Intent intent=new Intent(getActivity().getApplicationContext(),WebViewActivity.class);
                 intent.putExtra("url",url);
                 getActivity().startActivity(intent);


          }
      });
    }


    /**
     * 点击新闻内容
     */
    private  void clickTitileGetData(){
        titleAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                   String type= (String) view.getTag();
                   presenter.getHeadLinesData(ApiManager.HEADLINES_KEY,type);
            }
        });
    }


    private Animator mAnimationTitle;
    private Animator mAnimationContent;

    private void showOrHide(boolean tag) {
        if (mAnimationTitle != null && mAnimationTitle.isRunning()) {
            mAnimationTitle.cancel();
        }
        if (mAnimationContent != null && mAnimationContent.isRunning()) {
            mAnimationContent.cancel();
        }
        if (tag) {
            mAnimationTitle = ObjectAnimator.ofFloat(mtitle, "translationY", mtitle.getTranslationY() ,0);
            mAnimationContent = ObjectAnimator.ofFloat(mcontent, "translationY", mcontent.getTranslationY(), getResources().getDimension(R.dimen.title));
        } else {
            mAnimationTitle = ObjectAnimator.ofFloat(mtitle,"translationY",mtitle.getTranslationY(),-mtitle.getHeight());
            mAnimationContent = ObjectAnimator.ofFloat(mcontent,"translationY",mcontent.getTranslationY(),-mtitle.getHeight());
        }
        mAnimationTitle.start();
        mAnimationContent.start();
    }



    /**
     * titleAdapter定义
     */
    class  TitleAdapter extends RecyclerView.Adapter<TitleAdapter.mViewHoler>{
        private OnItemClickListener onItemClickListener;
         private List<HeadlinesTitle> titles;
         private LayoutInflater inflater;
         private List<Boolean> isChecked;

        public TitleAdapter(List<HeadlinesTitle> titles, Context context) {
            this.titles = titles;
            this.inflater=inflater.from(context);
            isChecked=new ArrayList<>();
            for (int i=0;i<titles.size();i++){
                if (i==0){
                    isChecked.add(true);
                }
                isChecked.add(false);
            }
        }
        public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
            this.onItemClickListener = mOnItemClickListener;
        }
        @Override
        public mViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=inflater.inflate(R.layout.headlines_titleview,parent,false);
            mViewHoler  mViewHoler=new mViewHoler(view);
            return mViewHoler;
        }

        @Override
        public void onBindViewHolder(final mViewHoler holder, final int position) {
            HeadlinesTitle title=titles.get(position);
            holder.textView.setText(title.getText());
            //通过tag属性便于点击获取
            holder.itemView.setTag(title.getType());
            if (isChecked.get(position)){
                holder.textView.setTextColor(Color.parseColor("#fc5677"));
            }else {
                holder.textView.setTextColor(Color.parseColor("#303F9F"));
            }
            //如果设置了回调事件则设置点击事件
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i=0;i<isChecked.size();i++){

                            isChecked.set(i,false); //全部变成false
                        }
                        isChecked.set(position,true);//点击的这项变成false;
                        notifyDataSetChanged();
                        onItemClickListener.onItemClick(holder.itemView,holder.textView,position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return titles.size();
        }

        class mViewHoler extends RecyclerView.ViewHolder{
            private TextView textView;
            public mViewHoler(View itemView) {
                super(itemView);
                textView=(TextView)itemView.findViewById(R.id.headlinesTitle);
            }
        }
    }

    /**
     * 新闻头条数据
     */
    private void initTitleData(){
        strings=new ArrayList<>();
        HeadlinesTitle title0=new HeadlinesTitle("头条","top");
        strings.add(title0);
        HeadlinesTitle title1=new HeadlinesTitle("社会","shehui");
        strings.add(title1);
        HeadlinesTitle title2=new HeadlinesTitle("国内","guonei");
        strings.add(title2);
        HeadlinesTitle title3=new HeadlinesTitle("国际","guoji");
        strings.add(title3);
        HeadlinesTitle title4=new HeadlinesTitle("娱乐","yule");
        strings.add(title4);
        HeadlinesTitle title5=new HeadlinesTitle("体育","tiyu");
        strings.add(title5);
        HeadlinesTitle title6=new HeadlinesTitle("军事","junshi");
        strings.add(title6);
        HeadlinesTitle title7=new HeadlinesTitle("科技","keji");
        strings.add(title7);
        HeadlinesTitle title8=new HeadlinesTitle("财经","caijing");
        strings.add(title8);
        HeadlinesTitle title9=new HeadlinesTitle("时尚","shishang");
        strings.add(title9);






    }

    /**
     * 点击头部
     */
    public interface OnItemClickListener {
        void onItemClick(View view, TextView textView,int position);
    }




}
