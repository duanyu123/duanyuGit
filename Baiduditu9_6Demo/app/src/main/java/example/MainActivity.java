package example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import example.baiduditu9_6demo.R;

/**
 * Created by 段昱 on 2017/9/7.
 *
 */

public class MainActivity extends AppCompatActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
        mBaiduMap = mMapView.getMap();
//1.0,使用MapVIew对象,控制是否显示MapVIew上面的按钮
//设置不显示比例尺控件    该方法的参数是boolen,false是不显示缩放控件
        mMapView.showScaleControl(true);
//设置不显示缩放控件    该方法的参数是boolen,false是不显示比例尺控件
        mMapView.showZoomControls(true);
        initBaiduMap();

    }

    private void initBaiduMap() {
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 创建定位所用到的类
        LocationClient mLocClient = new LocationClient(this);
        //注册定位的监听器
        //   mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型,使用的地图加密算法
        option.setScanSpan(1000);//可选设置定位间隔时间,注意间隔需要大于等于1000ms
        mLocClient.setLocOption(option);//地图界面显示到定位位置
        mLocClient.start();//开启定位
    }
    public  void  but(View v){
        Intent intent=new Intent(MainActivity.this,Location_Activity.class);
        startActivity(intent);
    }
    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView = null;
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//在ativity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

    }
}