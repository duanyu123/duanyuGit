package example.yuekaolianxi8_24;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import example.yuekaolianxi8_24.utils.dao;
import me.maxwin.view.XListView;

/**
 * Created by 段昱 on 2017/8/24.
 */
@ContentView(R.layout.frgmt)
public class Frgmt extends Fragment implements XListView.IXListViewListener {
    private View v;
    private XListView xlv1;
    private List<Data.DataBean.ComicsBean> list = new ArrayList<>();
    private int startNum = 10;

    public static Frgmt getin(String url) {
        Frgmt frgmt = new Frgmt();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.get(url);
        return frgmt;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgmt, container, false);
        x.view().inject(R.layout.frgmt, container);
        initView();
        initHttp();
        xlv1.setXListViewListener(this);
        xlv1.setPullLoadEnable(true);
        xlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences preferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", list.get(position - 1).getCover_image_url());
                editor.commit();
                Intent intent = new Intent(getActivity(), TwoActivity.class);
                startActivity(intent);
            }
        });

        return v;

    }

    private void initView() {
        xlv1 = (XListView) v.findViewById(R.id.xlv);
    }

    private void initHttp() {
        RequestParams params = new RequestParams("http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODI4ODU4NjAwLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJGcm9tSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D");
        x.http().get(params, new Callback.CommonCallback<String>() {

            private example.yuekaolianxi8_24.utils.dao dao;

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Data data = gson.fromJson(result, Data.class);
                Log.e("TAG", data + "----------------------");
                list.addAll(data.getData().getComics());
                for (Data.DataBean.ComicsBean bean : list) {
                    dao = new dao(getActivity());
                    dao.insert(bean.getTitle(), bean.getCover_image_url());
                }
                List<DataLX> all = dao.findAll();
//                for (String b:all) {
//
//                }
                if (getActivity() != null) {
                    ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    MyAdapter myAdapter = new MyAdapter(list, getActivity());
                    xlv1.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();

                }else {

                    MyAdapter myAdapter = new MyAdapter(all, getActivity());
                    xlv1.setAdapter(myAdapter);
                }


                StopXlist();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "解析失敗", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onRefresh() {
        startNum = 10;
        list.clear();
        initHttp();
    }

    @Override
    public void onLoadMore() {
        startNum++;
        startNum = list.size();
        initHttp();
    }

    public void StopXlist() {
        xlv1.stopRefresh();
        xlv1.stopLoadMore();
        xlv1.setRefreshTime("刚刚");
    }
}
