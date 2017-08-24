package example.yuekaolianxi8_24;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tab)
    private TabLayout tabLayout;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    private List<String> tablists = new ArrayList<>();
    private SharedPreferences sh;
    private String jsonstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        checkNetworkState();
        inittab();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater菜单填充
        getMenuInflater().inflate(R.menu.name, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
// TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.itm:
                sh = getSharedPreferences("user_setting", MODE_PRIVATE);

                jsonstr = sh.getString("user_setting", null);
                if (jsonstr == null) {
                    List<ChannelBean> list = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        ChannelBean bean = null;
                        if (i < 10) {
                            bean = new ChannelBean(tablists.toString(), true);
                        } else {
                            bean = new ChannelBean(tablists.toString(), false);
                        }
                        list.add(bean);
                    }
                    ChannelActivity.startChannelActivity(MainActivity.this, list);
                } else {
                    ChannelActivity.startChannelActivity(MainActivity.this, jsonstr);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ChannelActivity.REQUEST_CODE == requestCode && ChannelActivity.RESULT_CODE == resultCode) {
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            sh.edit().putString("user_setting", jsonstr).commit();
        }
    }

    private void inittab() {
        for (int i = 0; i < 7; i++) {
            tablists.add("星期" + i);
        }
        viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(), tablists);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    private boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            setNetwork();
        }
        return flag;
    }

    private void setNetwork() {
        Toast.makeText(this, "wifi is closed!", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }

}
