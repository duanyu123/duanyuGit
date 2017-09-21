package example.ykdemon9_20.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 段昱 on 2017/9/20.
 */

public class pagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public pagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }
//    public void setList(List<Fragment> list) {
//        this.list = list;
//    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
