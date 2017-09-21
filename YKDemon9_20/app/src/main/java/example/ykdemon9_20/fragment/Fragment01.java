package example.ykdemon9_20.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import example.ykdemon9_20.EndLessOnScrollListener;
import example.ykdemon9_20.R;
import example.ykdemon9_20.adapter.MyAdapter;
import example.ykdemon9_20.bean.Data;
import example.ykdemon9_20.utils.MyUtils;
import example.ykdemon9_20.utils.OKhttpManager;

/**
 * Created by 段昱 on 2017/9/20.
 */

public class Fragment01 extends Fragment implements MyAdapter.MyItemclickListener{

    private String json_path = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";
    //        使用封装后的OKhttp,所定义的成员变量
    private static OKhttpManager instance;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private Data data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frgmt, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        Button button=v.findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object o=null;
             System.out.print(o.toString());
            }
        });
        MyUtils.isNetworkAvailable(getActivity());
        initData();
        return v;
    }


    private void initData() {
        instance = OKhttpManager.getInstance();
        instance.asyncJsonStringByURL(json_path, new OKhttpManager.Function1() {
            @Override
            public void onResponse(String result) {
                Log.e("TAG", result);
                data = new Gson().fromJson(result, Data.class);
                //适配器初始化
                adapter = new MyAdapter(getActivity(), data);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
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

    private void loadMoreData() {
        for (int i = 0; i < 10; i++) {
            data.zqf.add(data.zqf.get(i));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemclick(View view, int position) {

    }

}