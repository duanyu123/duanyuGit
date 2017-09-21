package example.ykdemon9_20;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import example.ykdemon9_20.adapter.pagerAdapter;
import example.ykdemon9_20.fragment.Fragment01;
import example.ykdemon9_20.fragment.Fragment02;
import example.ykdemon9_20.fragment.Fragment03;

public class MainActivity extends AppCompatActivity  {
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();

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

    private void ListenerAbout() {
        //按钮和ViewPager  联动
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbQuanzi:
                        viewPager.setCurrentItem(0);
                        mRbQuanzi.setTextColor(Color.WHITE);
                        mRbFriend.setTextColor(Color.BLACK);
                        mRbMys.setTextColor(Color.BLACK);
                        break;
                    case R.id.rbFriend:
                        viewPager.setCurrentItem(1);
                        mRbQuanzi.setTextColor(Color.BLACK);
                        mRbFriend.setTextColor(Color.WHITE);
                        mRbMys.setTextColor(Color.BLACK);
                        break;
                    case R.id.rbMys:
                        viewPager.setCurrentItem(2);
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRg.check(R.id.rbQuanzi);
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

    private void preparData() {
        list = new ArrayList<>();
        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
    }

    private void initViewPager() {
        //搭建适配器
        pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mRbQuanzi = (RadioButton) findViewById(R.id.rbQuanzi);
        mRbFriend = (RadioButton) findViewById(R.id.rbFriend);
        mRbMys = (RadioButton) findViewById(R.id.rbMys);
        mRg = (RadioGroup) findViewById(R.id.rg);
    }

}
