package com.example.login07;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.customerBtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("name", "고객 관리");
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button button2 = findViewById(R.id.outputBtn);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("name", "매출 관리");
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        Button button3 = findViewById(R.id.productBtn);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("name", "상품 관리");
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MENU) {
            if (resultCode == RESULT_OK) {
                String dest = data.getStringExtra("dest");
                String from = data.getStringExtra("from");
                if (dest.equals("login")) {
                    Intent intent = new Intent();
                    intent.putExtra("from", from);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }
}