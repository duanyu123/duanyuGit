package example.ydlianxi7_27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bm.library.PhotoView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 段昱 on 2017/7/27.
 */
@ContentView(R.layout.twoactivty)
public class Twoactivity extends AppCompatActivity {
    @ViewInject(R.id.photoView)
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoactivty);
        x.view().inject(this);
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String user = preferences.getString("imge", "");
        x.image().bind(photoView, user);
        photoView.enable();
    }
}