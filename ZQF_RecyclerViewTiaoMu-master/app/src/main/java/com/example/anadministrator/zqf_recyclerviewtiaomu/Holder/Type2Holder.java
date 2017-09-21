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

public class Type2Holder extends TypeAbstractViewHolder{
    public final ImageView avatar2;
    public final TextView content2;
    public final TextView name2;
    public Type2Holder(View itemView) {
        super(itemView);
        avatar2 = itemView.findViewById(R.id.avatar2);
        name2 = itemView.findViewById(R.id.name2);
        content2 = itemView.findViewById(R.id.content2);
    }

    @Override
    public void bindholder(Bean bean) {
        avatar2.setBackgroundResource(bean.avatarColor);
        name2.setText(bean.name);
        content2.setText(bean.content);

    }
}
