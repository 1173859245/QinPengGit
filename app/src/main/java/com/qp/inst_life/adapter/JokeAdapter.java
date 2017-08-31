package com.qp.inst_life.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qp.inst_life.bean.NewJoke;
import com.qp.inst_life.R;


import java.util.List;

/**
 * Created by Administrator on 2017/6/14 0014.
 * ｐｓ：本来想偷懒在网上找个通用的adapter搞搞事情的，无奈各种达不到自己想要效果，还是老老实实的自己写吧
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.MyViewHolder> {


    private List<NewJoke.ResultBean.DataBean> dataList;
    private LayoutInflater layoutInflater;
    public JokeAdapter(List<NewJoke.ResultBean.DataBean> dataList, Context context) {
        this.layoutInflater=layoutInflater.from(context);
        this.dataList = dataList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(layoutInflater.inflate(R.layout.kokeitem,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.updateTIme.setText(dataList.get(position).getUpdatetime());
        holder.content.setText(dataList.get(position).getContent());


    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView updateTIme;
        private TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            updateTIme = (TextView) itemView.findViewById(R.id.update_time);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    @Override
    public int getItemCount() {
        return (dataList == null || dataList.size() == 0)?0:dataList.size();
    }
}
