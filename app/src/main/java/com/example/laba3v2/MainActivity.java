package com.example.laba3v2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button buttonShowData = findViewById(R.id.button_view);
        Button buttonAddStudent = findViewById(R.id.button_add);
        Button buttonUpdateStudent = findViewById(R.id.button_update);

        buttonShowData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
            startActivity(intent);
        });

        buttonAddStudent.setOnClickListener(v -> {
            dbHelper.addStudent("Иванов Иван Иванович");
        });

        buttonUpdateStudent.setOnClickListener(v -> {
            dbHelper.updateLastStudent("Петров Петр Петрович");
        });
    }
}
