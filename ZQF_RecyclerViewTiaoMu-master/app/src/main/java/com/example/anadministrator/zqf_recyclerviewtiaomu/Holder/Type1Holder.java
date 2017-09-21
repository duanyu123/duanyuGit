package com.example.anadministrator.zqf_recyclerviewtiaomu.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anadministrator.zqf_recyclerviewtiaomu.JavaBean.Bean;
import com.example.anadministrator.zqf_recyclerviewtiaomu.R;
import com.example.anadministrator.zqf_recyclerviewtiaomu.TypeAbstractViewHolder;

/**
 * Created by 张祺钒
 * on2017/9/8.
 */

public class Type1Holder extends TypeAbstractViewHolder{
    public final ImageView avatar1;
    public final TextView name1;
    public Type1Holder(View itemView) {
        super(itemView);
        avatar1 = itemView.findViewById(R.id.avatar1);
        name1 = itemView.findViewById(R.id.name1);
    }

    @Override
    public void bindholder(Bean bean) {
        avatar1.setBackgroundResource(bean.avatarColor);
        name1.setText(bean.name);
    }
}
