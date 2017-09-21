package com.example.anadministrator.monthprepar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anadministrator.monthprepar.Adapter.FragmentAdapter;
import com.example.anadministrator.monthprepar.Fragments.FriendFragment;
import com.example.anadministrator.monthprepar.Fragments.MysFragment;
import com.example.anadministrator.monthprepar.Fragments.QuanziFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.anadministrator.monthprepar.R.id.rbQuanzi;

/**
 * Fragment+ViewPager实现底部导航栏对应的三个Fragment滑动切换
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private List<Fragment> list;
    /**
     * 圈子
     */
    private RadioButton mRbQuanzi;
    /**
     * 朋友
     */
    private RadioButton mRbFriend;
    /**
     * 我的
     */
    private RadioButton mRbMys;
    private RadioGroup mRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //准备数据
        preparData();
        //初始化ViewPager
        initViewPager();
        //监听
        ListenerAbout();

    }

    /**
     * 监听
     */
    private void ListenerAbout() {
        //按钮和ViewPager  联动
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbQuanzi:
                        mVp.setCurrentItem(0);
                        mRbQuanzi.setTextColor(Color.WHITE);
                        mRbFriend.setTextColor(Color.BLACK);
                        mRbMys.setTextColor(Color.BLACK);
                        break;
                    case R.id.rbFriend:
                        mVp.setCurrentItem(1);
                        mRbQuanzi.setTextColor(Color.BLACK);
                        mRbFriend.setTextColor(Color.WHITE);
                        mRbMys.setTextColor(Color.BLACK);
                        break;
                    case R.id.rbMys:
                        mVp.setCurrentItem(2);
                        mRbQuanzi.setTextColor(Color.BLACK);
                        mRbFriend.setTextColor(Color.BLACK);
                        mRbMys.setTextColor(Color.WHITE);
                        break;
                    default:
                        break;
                }
            }
        });

        //viewPager 和 按钮联动
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        mRg.check(rbQuanzi);
                        break;
                    case 1:
                        mRg.check(R.id.rbFriend);
                        break;
                    case 2:
                        mRg.check(R.id.rbMys);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 准备数据
     */
    private void preparData() {
        list = new ArrayList<>();
        FriendFragment friendFragment = new FriendFragment();
        QuanziFragment quanziFragment = new QuanziFragment();
        MysFragment mysFragment = new MysFragment();
        list.add(quanziFragment);
        list.add(friendFragment);
        list.add(mysFragment);

    }

    private void initViewPager() {
        //搭建适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(fragmentAdapter);
    }


    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mRbQuanzi = (RadioButton) findViewById(rbQuanzi);
        mRbFriend = (RadioButton) findViewById(R.id.rbFriend);
        mRbMys = (RadioButton) findViewById(R.id.rbMys);
        mRg = (RadioGroup) findViewById(R.id.rg);
    }
}
