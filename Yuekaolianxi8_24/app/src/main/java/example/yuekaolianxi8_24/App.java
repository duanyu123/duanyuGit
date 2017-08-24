package example.yuekaolianxi8_24;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class App extends Application {
     @Override
         public void onCreate() {
             super.onCreate();
             x.Ext.init(this);
             x.Ext.setDebug(BuildConfig.DEBUG);
         }







}
