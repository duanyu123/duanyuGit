package example.yuekaolianxi8_24;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class viewPagerAdapter  extends FragmentPagerAdapter {
    private List<String>tablist;

    public viewPagerAdapter(FragmentManager fm, List<String> tablist) {
        super(fm);
        this.tablist = tablist;
    }

    @Override
    public Fragment getItem(int position) {
        return Frgmt.getin("http://api.kkmh.com/v1/topic_new/discovery_module_list/210?offset=0&limit=3&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3OTE2MTcwNjE5LCJwcm9wZXJ0aWVzIjp7IiRvc192ZXJzaW9uIjoiNC4yLjIiLCJHZW5kZXJUeXBlIjoi5peg5rOV6I635Y-WIiwiVHJpZ2dlckl0ZW0iOjE3LCJWaXNpdFBhZ2VOYW1lIjoi5paw5L2c5LiK5p62IiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJUcmlnZ2VySXRlbU5hbWUiOiLmlrDkvZzkuIrmnrYiLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJMaXN0VHlwZSI6IuaXp-eJiCIsIlByb3BlcnR5RXZlbnQiOiJSZWFkTGlzdCIsIlRyaWdnZXJPcmRlck51bWJlciI6MCwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjQ2LCIkc2NyZWVuX3dpZHRoIjoxMDI0LCIkb3MiOiJBbmRyb2lkIiwiVHJpZ2dlckl0ZW1UeXBlIjowLCJUcmlnZ2VyUGFnZSI6IkZpbmRQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkTGlzdCJ9&style=2");
    }

    @Override
    public int getCount() {
        return tablist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}
