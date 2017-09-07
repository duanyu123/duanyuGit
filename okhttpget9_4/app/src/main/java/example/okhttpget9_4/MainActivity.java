package example.okhttpget9_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String path = "http://169.254.53.96:8080/web/LoginServlet";

    private EditText et_qq;
    private EditText et_pwd;
    private TextView tv_status;
    private String qq;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                  initView();
    }

    private void initView() {
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_status = (TextView) findViewById(R.id.tv_status);
    }
    public  void login(View view){
        qq = et_qq.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(qq)||TextUtils.isEmpty(pwd)){
            //的到用户输入信息
            Toast.makeText(MainActivity.this,"不能输入为空",Toast.LENGTH_LONG).show();
            return;
        }
        new Thread(){

            private OkHttpClient build;

            @Override
            public void run() {
                //设置连接超时
//设置读取超时时间
//设置写入的超时时间
                build = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时
                        .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(10, TimeUnit.SECONDS)//设置写入的超时时间
                        .build();
                //表单对象
                FormBody formBody = new FormBody.Builder()
                        .add("qq", qq)
                        .add("pwd", pwd)
                        .build();
                Request request=new Request.Builder()
                        .post(formBody)
                        .url(path)
                        .build();
                Call call =build.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                           final  String s=response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_status.setText(s);
                            }
                        });
                    }
                });
            }
        }.start();
    }
}
