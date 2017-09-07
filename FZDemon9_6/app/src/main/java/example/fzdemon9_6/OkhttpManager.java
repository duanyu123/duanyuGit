package example.fzdemon9_6;

import android.os.Handler;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 段昱 on 2017/9/6.
 * okhttp中级封装,实现两个功能,从服务端下载数据,从客户端提交数据
 * 封装优秀的okhttp:okhttpUtils,okGo(更深入的封装,研究OKGO)
 * 1.节约内存使所有的网络请求都用一个IKhttpclient和Handler对象
 * 2.解决okhttp,网络请求成功,代码子线程的问题,把请求成功的逻辑代码放到主线程中执行
 * 3.简化代码
 *
 * 这次封装用到哪些知识点?
 * 1.单例模式 2.handler 3.接口 4.okhttp
 * //我們在使用單例模式時,構造方法一般使用權限私有,這樣保證對象的唯一性(RvenBus,如果看源代碼,他的構造方法是pubiic所以一方面可以通過單例方法拿到對象)
 */

public class OkhttpManager {
    //定义成员变量
    private OkHttpClient mCleient;
    private  static Handler mhandler;
    private  volatile  static  OkhttpManager sManager;//防止多个线程同时访问,volatile
    private FormBody.Builder form_duider;

    //使用构造方法完成初始化
    private   OkhttpManager (){
        mCleient=new OkHttpClient();
        mhandler= new Handler();
    }
    //使用单例模式,通过获取的方式拿到对象
    public  static  OkhttpManager getinstance(){
        OkhttpManager instance=null;
        if (sManager ==null) {
            synchronized (OkhttpManager.class) {
                instance = new OkhttpManager();
                sManager = instance;
            }
        }
        return instance;
    }




    //定义接口
interface  Func1{
        void  onResponse(String result);
    }
    interface Func2{
        void  onResponse(byte[] result);
    }
    interface  Func3{
        void  onResponse(JSONObject jsonObject);
    }
    interface Function4{
        void onResponse(String result);
    }
    //使用Hander,接口,保证处理保证处理数据在逻辑在主线程
private  static  void  onSuccessJsonSyringMethod(final  String jsonValue,final  Func1 callBack){
    //這里我用的都是 mhandler.post方法,把數據放到主線程,你們可以用EventBus或Rxjava的線程調度器去完成
    mhandler.post(new Runnable() {
        @Override
        public void run() {
            if (callBack!=null){
                try {
                    callBack.onResponse(jsonValue);
                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        }
    });
}
    //暴露提供给外界用的方法
    //請求指定的URL返回的結果是json字符串
    public  void  asyncjsonStringByURL(String url,final  Func1 callBack){
        //簡化代碼
        Request request = new Request.Builder().url(url).build();
        mCleient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    //判斷response是否有對象.成功
                if (response!=null&&response.isSuccessful()){
                    onSuccessJsonSyringMethod(response.body().string(),callBack);
                }
            }
        });
    }
    //提交表單
    public static void onSuccessJsonStringMethod(final String jsonValue, final Function4 callBack){
        //这里使用Handler.post 把数据放到主线程,也可以使用EventBus或RxJava的线程调度器去完成
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    try {
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}