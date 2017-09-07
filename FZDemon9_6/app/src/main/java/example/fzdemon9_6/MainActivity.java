package example.fzdemon9_6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String json_path = "https://publicobject.com/helloworld.txt";
    private String Login_path = "http://169.254.53.96:8080/web/LoginServlet";
    private String Picture_path = "https://10.url.cn/eth/ajNVdqHZLLAxibwnrOxXSzIxA76ichutwMCcOpA45xjiapneMZsib7eY4wUxF6XDmL2FmZEVYsf86iaw/";
    private ImageView mImageView;
    private OkhttpManager mokhttpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mokhttpManager = OkhttpManager.getinstance();
    }

    /**
     * 通过点击事件执行okhttp里封装的根据网址,获取字符串的逻辑操作.
     *
     * @param view
     */
    public void okhttp_json(View view) {
        mokhttpManager.asyncjsonStringByURL(json_path, new OkhttpManager.Func1() {
            @Override
            public void onResponse(String result) {
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }
        });
    }

    //像服务器提交账号及密码
    public void okhttp_table(View view) {
        Map<String,String>map=new HashMap<>();
        map.put("qq","1000");
        map.put("pwd","abcde");
        mokhttpManager.onSuccessJsonStringMethod(Login_path, new OkhttpManager.Function4() {
            @Override
            public void onResponse(String result) {
         Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
            }
        });
    }

    //下载图片
    public void okhttp_picture(View view) {
mokhttpManager.asyncjsonStringByURL(Picture_path, new OkhttpManager.Func1() {
    @Override
    public void onResponse(String result) {
        Glide.with(MainActivity.this).load(Picture_path).into(mImageView);
    }
});
    }

}

