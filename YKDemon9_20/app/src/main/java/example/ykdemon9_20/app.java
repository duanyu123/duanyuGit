package example.ykdemon9_20;

import android.app.Application;

import example.ykdemon9_20.utils.CauchExceptionHandler;

/**
 * Created by 段昱 on 2017/9/21.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CauchExceptionHandler.getInstance().setDefaultUnCachExceptionHandler();
    }
}
