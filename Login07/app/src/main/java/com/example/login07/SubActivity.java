package com.example.login07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        textView = findViewById(R.id.textView);
        textView.setText(name);

        textView = findViewById(R.id.textView);
        Button toMenuButton = findViewById(R.id.toMenuButton);
        toMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent();
                menuIntent.putExtra("dest", "menu");
                setResult(RESULT_OK, menuIntent);
                finish();
            }
        });

        Button toLoginButton = findViewById(R.id.toLoginButton);
        toLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent();
                loginIntent.putExtra("dest", "login");
                loginIntent.putExtra("from", name);
                setResult(RESULT_OK, loginIntent);
                finish();
            }
        });
    }
}