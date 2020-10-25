package com.example.sampledatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;
    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = editText2.getText().toString();
                createTable(tableName);
                insertRecord();
            }
        });
    }

    private void createDatabase(String name) {
        println("createDatabase()");
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
        println("Database created: " + name);
    }

    private void createTable(String name) {
        println("createTable()");
        if (database == null) {
            println("Create Database First");
            return;
        }

        database.execSQL("Create table if not exists " + name + "(" +
                " _id integer PRIMARY KEY autoincrement, " + " name text, "
                + " age integer, " + " mobile text)");
        println("Table created: " + name);
    }

    private void insertRecord() {
        println("insertRecord()");
        if (database == null) {
            println("Create database first");
            return;
        }
        if (tableName == null) {
            println("Create table first");
            return;
        }
        database.execSQL("insert into " + tableName + "(name, age, mobile) " + " values "
                + "('John', 20, '010-1234-5678')");
        println("Record added");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}