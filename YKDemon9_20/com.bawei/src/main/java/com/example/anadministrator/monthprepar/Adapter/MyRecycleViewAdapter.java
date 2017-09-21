package com.example.anadministrator.monthprepar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anadministrator.monthprepar.JavaBean.Bean;
import com.example.anadministrator.monthprepar.R;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private Bean bean;

    public MyRecycleViewAdapter(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        if (mMyItemclickListener != null) {
            mMyItemclickListener.itemclick(view, (Integer) view.getTag());

        }
    }


    //接口  ***
    public interface MyItemclickListener {
        void itemclick(View view, int position);
    }

    public MyItemclickListener mMyItemclickListener;

    public void setmMyItemclickListener(MyItemclickListener mMyItemclickListener) {
        this.mMyItemclickListener = mMyItemclickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        holder.itemView.setTag(position);
        //放数据
        Bean.zqfBean.MomentBean moment = bean.zqf.get(position).Moment;
        Glide.with(context).load(bean.zqf.get(position).Moment.pictureList.get(0)).into(holder.imageTitle);
        Glide.with(context).load(bean.zqf.get(position).User.head).into(holder.iv1);
        Glide.with(context).load(bean.zqf.get(position).User.head).into(holder.iv2);
        Glide.with(context).load(bean.zqf.get(position).User.head).into(holder.iv3);
        holder.tvTitle.setText(bean.zqf.get(position).User.name);
        holder.tvDate.setText(moment.date);
        holder.tvZan.setText(bean.zqf.get(position).Commentzqf.get(0).momentId+"点赞");
        holder.tvPing.setText("评论:"+bean.zqf.get(position).Commentzqf.get(0).content);
    }




    @Override
    public int getItemCount() {
        return bean.zqf.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView imageTitle;
        private final TextView tvTitle;
        private final TextView tvDate;
        private final TextView tvZan;
        private final TextView tvPing;
        private final ImageView iv1;
        private final ImageView iv2;
        private final ImageView iv3;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageTitle = itemView.findViewById(R.id.ImageTitle);
            tvTitle = itemView.findViewById(R.id.itemTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvZan = itemView.findViewById(R.id.tvZan);
            tvPing = itemView.findViewById(R.id.tvPing);
            iv1 = itemView.findViewById(R.id.iv1);
            iv2 = itemView.findViewById(R.id.iv2);
            iv3 = itemView.findViewById(R.id.iv3);
        }
    }


}