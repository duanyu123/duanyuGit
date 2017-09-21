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

public class Type3Holder extends TypeAbstractViewHolder{
    public final ImageView avatar3;

    public final TextView name3;

    public final TextView content3;

    public final ImageView contentImage3;


    public Type3Holder(View itemView) {
        super(itemView);
        name3 = itemView.findViewById(R.id.name3);
        avatar3 = itemView.findViewById(R.id.avatar3);
        contentImage3 = itemView.findViewById(R.id.contentImage3);
        content3 = itemView.findViewById(R.id.content3);
    }

    @Override
    public void bindholder(Bean bean) {
        name3.setText(bean.name);
        avatar3.setBackgroundResource(bean.avatarColor);
        contentImage3.setBackgroundResource(bean.contentColr);
        content3.setText(bean.content);
    }
}
