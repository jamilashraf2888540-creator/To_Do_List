package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.DataBase.Task;
import com.example.todolist.DataBase.DataBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class dialog_add_task extends AppCompatActivity {

    EditText etTaskTitle , etTaskDescription;
    Button btnSaveTask , btnCancel ;
    DataBase dataBase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_task);
        FindViewById();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title =etTaskTitle.getText().toString().trim();
                String description = etTaskDescription.getText().toString().trim();
                if (title.isEmpty()){
                    Toast.makeText(dialog_add_task.this, "Please enter a task title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Task task = new Task(title,description,0,date);
                dataBase = new DataBase(dialog_add_task.this);
                dataBase.addTask(task);
                finish();


            }
        });


    }

    void FindViewById(){

        etTaskDescription = findViewById(R.id.etTaskDescription);
        etTaskTitle = findViewById(R.id.etTaskTitle);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

    }

}