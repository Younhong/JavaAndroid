package com.example.login07;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    EditText idText;
    EditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        idText = findViewById(R.id.idInput);
        pwText = findViewById(R.id.pwInput);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (idText.getText().toString().isEmpty() | pwText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                } else{
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_MENU);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("from");
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
            }
        }
    }
}