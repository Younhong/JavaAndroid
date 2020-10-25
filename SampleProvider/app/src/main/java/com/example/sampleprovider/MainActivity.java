package com.example.sampleprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner2;
    TextView textView;
    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;

    String[] items = {"name", "age", "mobile"};
    String selectedItem = "name";
    String selectedItem2 = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem2 = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty() == false
                        && editText2.getText().toString().isEmpty() == false
                        && editText3.getText().toString().isEmpty() == false) {
                    insertPerson();
                }
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson();
            }
        });
    }

    public void insertPerson() {
        println("insertPerson()");
        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        println("columns count->" + columns.length);

        for (int i = 0; i < columns.length; i++) {
            println("#" + i + " : " + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", editText.getText().toString());
        values.put("age", editText2.getText().toString());
        values.put("mobile", editText3.getText().toString());

        uri = getContentResolver().insert(uri, values);
        println("insert result-> " + uri.toString());

        editText.setText("");
        editText2.setText("");
        editText3.setText("");
    }

    public void queryPerson() {
        try {
            String uriString = "content://com.example.sampleprovider/person";
            Uri uri = new Uri.Builder().build().parse(uriString);

            String[] columns = new String[] {"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            println("Query Result: " + cursor.getCount());

            int index = 0;
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));

                println("#" + index + "-> " + name + ", " + age + ", " + mobile);
                index += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePerson() {
        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = selectedItem + " = ?";
        String[] selectionArgs = new String[] {editText4.getText().toString()};
        ContentValues updateValue = new ContentValues();
        updateValue.put(selectedItem, editText5.getText().toString());

        int count = getContentResolver().update(uri, updateValue, selection, selectionArgs);
        println("update result: " + count);

        editText4.setText("");
        editText5.setText("");
    }

    public void deletePerson() {
        String uriString = "content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = selectedItem2 + " = ?";
        String[] selectionArgs = new String[] {editText6.getText().toString()};

        int count = getContentResolver().delete(uri, selection, selectionArgs);
        println("delete result: " + count);
        editText6.setText("");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}