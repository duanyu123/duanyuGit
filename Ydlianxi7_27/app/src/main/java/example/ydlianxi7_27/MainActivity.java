package example.ydlianxi7_27;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.andy.share.QQOauthUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tab)
    private TabLayout tabLayout;
    @ViewInject(R.id.text)
    private TextView textView;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    @ViewInject(R.id.touxiang)
    private ImageView touxiang;
    String Url = "http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3NzQyMjQwNjE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjEzIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6ImJpZ25veCIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjAsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTEsIiRzY3JlZW5fd2lkdGgiOjcyMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNoaW5hIE1vYmlsZSIsIiRtb2RlbCI6IlZQaG9uZSIsIiRhcHBfdmVyc2lvbiI6IjMuNi4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo2YWRkYzdhZTQ1MjUwMzY1Iiwib3JpZ2luYWxfaWQiOiJBOjZhZGRjN2FlNDUyNTAzNjUiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
    List<Data.DataBean.ComicsBean> comics = new ArrayList<>();
    private SharedPreferences sh;
    private String jsonstr;
    private UMShareAPI umShareAPI;
    private QQOauthUtils qqOauthUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = getSharedPreferences("get", MODE_PRIVATE);
        x.view().inject(this);
        tabView();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    jsonstr = sh.getString("user_setting", null);
                                                    if (jsonstr == null) {
                                                        List<ChannelBean> list = new ArrayList<>();
                                                        for (int i = 0; i < 20; i++) {
                                                            ChannelBean bean = null;
                                                            if (i < 10) {
                                                                bean = new ChannelBean(comics.get(i).getLabel_text(), true);
                                                            } else {
                                                                bean = new ChannelBean(comics.get(i).getLabel_text(), false);
                                                            }
                                                            list.add(bean);
                                                        }
                                                        ChannelActivity.startChannelActivity(MainActivity.this, list);
                                                    } else {
                                                        ChannelActivity.startChannelActivity(MainActivity.this, jsonstr);
                                                    }
                                                }
                                            }

                );
            }
        });
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                              qqOauthUtils = new QQOauthUtils();
                              qqOauthUtils.qqLogin(MainActivity.this, new QQOauthUtils.QQCallBack() {
                                  @Override
                                  public void logsuccess() {
                                      Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                                  }

                                  @Override
                                  public void getuserinfo(Map<String, String> map) {

                                  }

                                  @Override
                                  public void getUserName(String name) {

                                  }

                                  @Override
                                  public void getImagepath(String url) {
                                      x.image().bind(touxiang, url);
                                  }
                              });
                              umShareAPI = UMShareAPI.get(MainActivity.this);



            }
        });

    }

    private void tabView() {
        RequestParams params = new RequestParams(Url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Data data = gson.fromJson(result, Data.class);
                comics.addAll(data.getData().getComics());
                ViewPagerAdaft viewPagerAdaft = new ViewPagerAdaft(getSupportFragmentManager(), comics);
                viewPager.setAdapter(viewPagerAdaft);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ChannelActivity.REQUEST_CODE == requestCode && ChannelActivity.RESULT_CODE == resultCode) {
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            sh.edit().putString("user_setting", jsonstr).commit();
        }
        super.onActivityResult(requestCode, resultCode, data);
        qqOauthUtils.onActivityResult(requestCode, resultCode, data);
    }


}