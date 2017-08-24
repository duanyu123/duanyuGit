package example.yuekaolianxi8_24;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class TwoActivity extends AppCompatActivity {
    private PhotoView photoView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoactivity);
        photoView = (PhotoView) findViewById(R.id.photoView);
        SharedPreferences preferences = getSharedPreferences("User", MODE_PRIVATE);
        String name = preferences.getString("name", "");
        Glide
                .with(this)
                .load(name)
                .into(photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }
        });
    }

}
