package com.example.anadministrator.zqf_recyclerviewtiaomu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anadministrator.zqf_recyclerviewtiaomu.Adapter.MyRecyclerViewAdapter;
import com.example.anadministrator.zqf_recyclerviewtiaomu.JavaBean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Bean> list;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    /**
     * 1.搭建环境(添加依赖,添加控件,初始化控件)
     * 2.初始化数据
     * 3.创建适配器
     * 4.设置适配器,布局管理器
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //初始化数据
        initData();
        //创建适配器
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(list, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(myRecyclerViewAdapter);

    }


    /**
     * Type:分类型
     * String 字符串 name
     * String content
     * color
     */
    int colors[] = {android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light};

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int type = (int) (Math.random() * 3 + 1);

            //创建Bean类  实际开发中,则是解析服务器拿到的数据
            Bean bean = new Bean();
            bean.avatarColor = colors[type - 1];//+1  -1 则是为了防止角标越界
            bean.type = type;
            bean.name = "Name" + i;
            bean.content = "Content" + i;
            bean.contentColr = colors[(type + 1) % 3];
            list.add(bean);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }
}
