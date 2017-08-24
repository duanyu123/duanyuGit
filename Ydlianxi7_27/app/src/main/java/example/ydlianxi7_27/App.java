package example.ydlianxi7_27;

import org.xutils.x;

/**
 * Created by 段昱 on 2017/7/27.
 */

public class App extends com.andy.share.App {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
