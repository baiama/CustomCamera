package oa.freelitecoin.customcamera1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ShowActivity extends AppCompatActivity {

    private ImageView mImageView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mImageView = findViewById(R.id.imgView);

        String image = getIntent().getStringExtra("image");
        if (image != null){
            GlideApp.with(mImageView)
                    .load(new File(image)).into(mImageView);
        }
    }
}
