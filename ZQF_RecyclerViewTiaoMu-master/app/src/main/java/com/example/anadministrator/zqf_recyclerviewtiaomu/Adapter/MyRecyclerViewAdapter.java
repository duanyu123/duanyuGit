package com.example.anadministrator.zqf_recyclerviewtiaomu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.anadministrator.zqf_recyclerviewtiaomu.Holder.Type1Holder;
import com.example.anadministrator.zqf_recyclerviewtiaomu.Holder.Type2Holder;
import com.example.anadministrator.zqf_recyclerviewtiaomu.Holder.Type3Holder;
import com.example.anadministrator.zqf_recyclerviewtiaomu.JavaBean.Bean;
import com.example.anadministrator.zqf_recyclerviewtiaomu.R;
import com.example.anadministrator.zqf_recyclerviewtiaomu.TypeAbstractViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张祺钒
 * on2017/9/8.
 * RecyclerView的  多布局适配器
 * 1.创建一个类 继承RecyclerView的Adapter
 * 2.定义RecyclerView.ViewHolder以便多布局
 * 3.创建3种类型的Item布局以及ViewHolder
 * 4.使用getitemViewTyper
 * 5.create 和 bind
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bean> list = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    public MyRecyclerViewAdapter(List<Bean> list1, Context context) {
        layoutInflater = LayoutInflater.from(context);
        list.addAll(list1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case Bean.Type1:
                return new Type1Holder(layoutInflater.inflate(R.layout.type1,parent,false));
            case Bean.Type2:
                return new Type2Holder(layoutInflater.inflate(R.layout.type2,parent,false));
            case Bean.Type3:
                return new Type3Holder(layoutInflater.inflate(R.layout.type3,parent,false));
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bean bean = list.get(position);
        ((TypeAbstractViewHolder)holder).bindholder(bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }
}
