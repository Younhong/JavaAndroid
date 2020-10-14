package com.example.mission3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView;
    ImageView imageView2;

    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        scrollView2 = findViewById(R.id.scrollView2);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        scrollView.setHorizontalScrollBarEnabled(true);
        scrollView2.setHorizontalScrollBarEnabled(true);

        Resources resources = getResources();
        bitmap = (BitmapDrawable) resources.getDrawable(R.drawable.beach);

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmap.getIntrinsicWidth();
        imageView.getLayoutParams().height = bitmap.getIntrinsicHeight();
    }

    public void upButtonClicked(View v) {
        changeToUpImage();
    }

    public void downButtonClicked(View v) {
        changeToDownImage();
    }

    private void changeToDownImage() {
        Resources resources = getResources();
        bitmap = (BitmapDrawable) resources.getDrawable(R.drawable.beach);

        imageView2.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        imageView2.setImageDrawable(bitmap);
        imageView2.getLayoutParams().width = bitmap.getIntrinsicWidth();
        imageView2.getLayoutParams().height = bitmap.getIntrinsicHeight();
    }

    private void changeToUpImage() {
        Resources resources = getResources();
        bitmap = (BitmapDrawable) resources.getDrawable(R.drawable.beach);

        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmap.getIntrinsicWidth();
        imageView.getLayoutParams().height = bitmap.getIntrinsicHeight();
    }
}