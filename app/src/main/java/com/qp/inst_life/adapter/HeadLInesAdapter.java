package com.qp.inst_life.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qp.inst_life.bean.Headlines;

import com.qp.inst_life.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class HeadLInesAdapter extends RecyclerView.Adapter<HeadLInesAdapter.mViewHoder> {
      private List<Headlines.ResultBean.DataBean> datas;
      private LayoutInflater inflater;
      private Context context;
      private  OnItemClickListener onItemClickListener;

    public HeadLInesAdapter(List<Headlines.ResultBean.DataBean> datas, Context context) {
        this.datas = datas;
        this.inflater = inflater.from(context);
        this.context=context;
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }

    @Override
    public mViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.headlines_content,parent,false);
        mViewHoder mViewHoder=new mViewHoder(view);
        return mViewHoder;
    }

    @Override
    public void onBindViewHolder(final mViewHoder holder, final int position) {
        Headlines.ResultBean.DataBean  dataBean=datas.get(position);
        holder.date.setText(dataBean.getDate());
        holder.author_name.setText(dataBean.getAuthor_name());
        holder.title.setText(dataBean.getTitle());
        holder.title.setTag(dataBean.getUrl());
        Glide.with(context)
        .load(dataBean.getThumbnail_pic_s())
        .into(holder.imageView);
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemClick(holder.itemView,holder.title,position);
                }
            });
        }



    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    class mViewHoder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView author_name;
        private TextView date;
        private TextView title;


        public mViewHoder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.thumbnail_pic_s);
            author_name=(TextView)itemView.findViewById(R.id.author_name);
            date=(TextView)itemView.findViewById(R.id.date);
            title=(TextView)itemView.findViewById(R.id.title);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, TextView textView,int position);
    }
}
