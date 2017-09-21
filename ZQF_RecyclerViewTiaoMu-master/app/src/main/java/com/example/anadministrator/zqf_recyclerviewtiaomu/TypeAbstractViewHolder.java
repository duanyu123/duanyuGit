package com.example.anadministrator.zqf_recyclerviewtiaomu;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.anadministrator.zqf_recyclerviewtiaomu.JavaBean.Bean;

/**
 * Created by 张祺钒
 * on2017/9/8.
 */

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }
    public  abstract void bindholder(Bean bean);
}
