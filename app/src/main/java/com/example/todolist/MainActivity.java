package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.DataBase.DataBase;
import com.example.todolist.DataBase.Task;
import com.example.todolist.DataBase.Task_Adapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddTask;
    TextView tvEmpty;
    RecyclerView recyclerView;
    DataBase dataBase;
    Task_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        dataBase = new DataBase(this);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dialog_add_task.class);
                startActivity(intent);
            }
        });

    }

    void findViewById() {
        btnAddTask   = findViewById(R.id.btnAddTask);
        tvEmpty      = findViewById(R.id.tvEmpty);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    void loadTasks() {
        List<Task> taskList = dataBase.getAllTasks();

        if (taskList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            adapter = new Task_Adapter(this, taskList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }


}