package oa.freelitecoin.customcamera1;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

public class ShowActivity extends AppCompatActivity {

    private SubsamplingScaleImageView mImageView;
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
                    .load(new File(image))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            e.printStackTrace();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .into(new Target<Drawable>() {
                @Override
                public void onLoadStarted(@Nullable Drawable placeholder) {
                    Log.d("Camera image","onLoadStarted" );
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    Log.d("Camera image","onLoadFailed" );

                }

                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    mImageView.setImage(ImageSource.cachedBitmap(drawableToBitmap(resource)));
                    Log.d("Camera image","onResourceReady" );

                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                    Log.d("Camera image","onLoadCleared" );

                }

                @Override
                public void getSize(@NonNull SizeReadyCallback cb) {
                    cb.onSizeReady(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels);
                    Log.d("Camera image","getSize" );

                }

                @Override
                public void removeCallback(@NonNull SizeReadyCallback cb) {
                    Log.d("Camera image","removeCallback" );

                }

                @Override
                public void setRequest(@Nullable Request request) {
                    Log.d("Camera image","setRequest" );

                }

                @Nullable
                @Override
                public Request getRequest() {
                    Log.d("Camera image","getRequest" );

                    return null;

                }

                @Override
                public void onStart() {
                    Log.d("Camera image","onStart" );

                }

                @Override
                public void onStop() {
                    Log.d("Camera image","onStop" );

                }

                @Override
                public void onDestroy() {
                    Log.d("Camera image","onDestroy" );

                }
            });
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
