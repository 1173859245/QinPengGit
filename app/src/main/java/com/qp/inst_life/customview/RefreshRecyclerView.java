package com.qp.inst_life.customview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;


/**
 * Author: zhuliyuan
 * Time: 下午 3:26
 * 将脚布局放在最后一个条目,当加载更多的时候显示,加载完成的时候隐藏
 *
 */

public class RefreshRecyclerView extends RecyclerView {

    private AutoLoadAdapter autoLoadAdapter;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private boolean isLoadingMore = false;//是否正在加载更多
    private OnLoadMoreListener loadMoreListener;//加载数据监听
    private boolean loadMoreEnable = false;//是否允许加载更多
    private int footerResource = -1;//脚布局
    private boolean footer_visible = false;//脚部是否可以见
    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (getAdapter() != null && getLayoutManager() != null) {
                    int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                    int itemCount = getAdapter().getItemCount();
                    /**
                     * 控制下拉刷新回调
                     * itemCount != 0 排除没有数据情况
                     * lastVisiblePosition + 4 >= itemCount - 1 最后可见+4 >= 总条数 加载更多
                     * distanceY < 0 为上拉的时候才刷新
                     */
                    if (distanceY < 0 && itemCount != 0 && lastVisiblePosition + 4 >= itemCount - 1 && !isLoadingMore && loadMoreEnable) {
                        Log.i("test","加载更多");
                        //正在加载更多
                        loading();
                        if (footerResource != -1){//有脚布局
                            //显示脚布局
                            footer_visible = true;
                            getAdapter().notifyItemChanged(itemCount - 1);
                        }
                        if (loadMoreListener != null) {
                            loadMoreListener.loadMoreListener();
                        }
                    }
                }
            }
        });
    }

    /**
     * 判断滑动方向
     */
    private float distanceY = 0;
    float startY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = y - startY;
                startY = y;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        SlideInBottomAnimationAdapter slideInBottomAnimationAdapter = new SlideInBottomAnimationAdapter(adapter);
        slideInBottomAnimationAdapter.setDuration(600);
        autoLoadAdapter = new AutoLoadAdapter(slideInBottomAnimationAdapter);//添加动画
        super.setAdapter(autoLoadAdapter);
    }

    /**
     * 设置是否允许加载更多
     *
     * @param isEnable
     */
    public void setLoadMoreEnable(boolean isEnable) {
        this.loadMoreEnable = isEnable;
    }

    /**
     * 设置脚布局
     */
    public void setFooterResource(int footerResource) {
        this.footerResource = footerResource;
    }


    /**
     * 加载完成
     */
    private void loadMoreComplete() {
        this.isLoadingMore = false;
    }

    /**
     * 正在刷新
     */
    private void loading(){
        this.isLoadingMore = true;//设置正在刷新
    }

    /**
     * 加载更多数据回调
     *
     * @param listener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void loadMoreListener();//上拉刷新
    }


    /**
     * 刷新数据
     */
    public void notifyData() {
        if (getAdapter() != null) {
            loadMoreComplete();
            if(footerResource != -1 && loadMoreEnable){
                //隐藏脚布局
                footer_visible = false;
            }
            getAdapter().notifyDataSetChanged();

        }
    }

    public class AutoLoadAdapter extends Adapter<ViewHolder> {
        private Adapter dataAdapter;//数据adapter
        private final int TYPE_FOOTER = Integer.MAX_VALUE;//底部布局

        public AutoLoadAdapter(Adapter adapter) {
            this.dataAdapter = adapter;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1 && loadMoreEnable && footerResource != -1 && footer_visible) {
                return TYPE_FOOTER;
            }
            if (dataAdapter.getItemViewType(position) == TYPE_FOOTER) {
                throw new RuntimeException("adapter中itemType不能为:" + Integer.MAX_VALUE);
            }
            return dataAdapter.getItemViewType(position);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = null;
            if (viewType == TYPE_FOOTER) {//脚部
                holder = new FooterViewHolder(LayoutInflater.from(getContext()).inflate(footerResource, parent, false));
            } else {//数据
                holder = dataAdapter.onCreateViewHolder(parent, viewType);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int itemViewType = getItemViewType(position);
            if (itemViewType != TYPE_FOOTER) {
                dataAdapter.onBindViewHolder(holder, position);
            }
        }

        @Override
        public int getItemCount() {
            if (dataAdapter.getItemCount() != 0) {
                int count = dataAdapter.getItemCount();
                if (loadMoreEnable && footerResource != -1 && footer_visible) {
                    count++;
                }
                return count;
            }
            return 0;
        }

        public class FooterViewHolder extends ViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

}
