package com.qp.inst_life.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qp.inst_life.R;

import com.qp.inst_life.bean.QueryNews;
import com.zly.www.easyrecyclerview.adapter.CommonAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;



/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class QueryNewsAdapter extends CommonAdapter<QueryNews.ResultBean,QueryNewsAdapter.MyViewHolder>{


   private OnItemClickListener onItemClickListener;

    private Context context;
      public QueryNewsAdapter( Context context) {

         this.context=context;
    }



    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }

    public class MyViewHolder extends BaseViewHolder{

        private TextView title;
        private TextView content;
        private TextView pdate;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_query);
            content = (TextView) itemView.findViewById(R.id.content);
            pdate = (TextView) itemView.findViewById(R.id.pdate);
            imageView=(ImageView)itemView.findViewById(R.id.img);
        }
    }


    @Override
    public MyViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        View view=inflateView(R.layout.query_newsitem,parent);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void bindCustomViewHolder(final MyViewHolder holder, QueryNews.ResultBean resultBean, final int position) {

        holder.pdate.setText(resultBean.getPdate());
        holder.title.setText(resultBean.getTitle());
        holder.content.setText(resultBean.getContent());
        holder.title.setTag(resultBean.getUrl());
        if (resultBean.getImg()==""){
            holder.imageView.setImageResource(R.mipmap.gv_empty);
        }else {
            Glide.with(context)
                    .load(resultBean.getImg())
                    .into(holder.imageView);
        }


        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,holder.title,position);
                }
            });
        }
    }





    public interface OnItemClickListener {
        void onItemClick(View view, TextView textView,int position);
    }
}
