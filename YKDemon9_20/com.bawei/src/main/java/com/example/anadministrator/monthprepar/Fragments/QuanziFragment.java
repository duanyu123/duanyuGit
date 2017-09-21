package com.example.anadministrator.monthprepar.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anadministrator.monthprepar.Adapter.MyRecycleViewAdapter;
import com.example.anadministrator.monthprepar.JavaBean.Bean;
import com.example.anadministrator.monthprepar.Listener.EndLessOnScrollListener;
import com.example.anadministrator.monthprepar.R;
import com.example.anadministrator.monthprepar.Utils.NetWorkUtils;
import com.example.anadministrator.monthprepar.Utils.OkHttpManager;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanziFragment extends Fragment{
    private String path = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";


    private View view;
    private RecyclerView mRecycler;
    private MyRecycleViewAdapter adapter;
    private Bean bean;

    public QuanziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanzi, container, false);
        initView(view);
        //判断网络
        HasNet();
        //网络请求数据
        RequestData();
        return view;
    }

    private void HasNet() {
        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(getContext());
        if(netWorkAvailable==false){
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setTitle("提示");
            builder.setMessage("是否设置网络");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getContext(),"不设置拉倒！！",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                }
            });
            builder.create().show();
        }

    }

    private void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler);
    }

    private void RequestData() {
        OkHttpManager instance = OkHttpManager.getInstance();
        instance.asyncJsonStringByURL(path, new OkHttpManager.Function1() {
            @Override
            public void onResponse(String result) {
                bean = new Gson().fromJson(result, Bean.class);
                //适配器初始化
                adapter = new MyRecycleViewAdapter(getActivity(), bean);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        mRecycler.setLayoutManager(linearLayoutManager);
                        mRecycler.setAdapter(adapter);
                        mRecycler.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
                            @Override
                            public void onLoadMore(int currentPage) {
                                loadMoreData();

                            }
                        });
                    }
                });
            }
        });
    }
    //每次上拉加载的时候，给RecyclerView的后面添加了10条数据数据
    private void loadMoreData() {
        for (int i =0; i < 10; i++){
            bean.zqf.add(bean.zqf.get(i));
            adapter.notifyDataSetChanged();
        }
    }

}
