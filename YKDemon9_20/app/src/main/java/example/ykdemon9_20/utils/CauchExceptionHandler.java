package example.ykdemon9_20.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * Created by 段昱 on 2017/9/21.
 */


public class CauchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static CauchExceptionHandler cauchExceptionHandler = null;

    private CauchExceptionHandler() {

    }

    public static CauchExceptionHandler getInstance() {

        if (cauchExceptionHandler == null) {

            synchronized (CauchExceptionHandler.class) {

                if (cauchExceptionHandler == null) {
                    cauchExceptionHandler = new CauchExceptionHandler();
                }
            }
        }

        return cauchExceptionHandler;
    }

    public void setDefaultUnCachExceptionHandler() { //在Application开始时调用

        Thread.setDefaultUncaughtExceptionHandler(this); //设置应用默认的全局捕获异常器
    }

    @Override //应用没有捕抓的异常会到这里来,如果我们设置了应用的默认全局捕抓异常为CauchExceptionHandler的话
    public void uncaughtException(Thread thread, Throwable throwable) {

        Log.d("TAG", "程序出现异常");//异常信息
        try {
            saveToSDCard("User","程序出现异常" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToSDCard(String filename, String content) throws Exception {
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        OutputStream out = new FileOutputStream(file);
        out.write(content.getBytes());
        out.close();
    }
}

