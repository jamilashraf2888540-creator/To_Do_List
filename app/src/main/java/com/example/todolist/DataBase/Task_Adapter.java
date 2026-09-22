package com.example.todolist.DataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;

import java.util.List;

public class Task_Adapter extends RecyclerView.Adapter<Task_Adapter.TaskViewHolder> {

    List<Task>taskList;
    Context context;

    public Task_Adapter(Context context, List<Task> taskList) {
        this.context  = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public Task_Adapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Task_Adapter.TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.tvTaskTitle.setText(task.getTitle());
        holder.tvTaskTime.setText(task.getDate());
        holder.checkTask.setChecked(task.getIs_completed() == 1);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView tvTaskTitle , tvTaskTime ;
        CheckBox checkTask ;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvTaskTime = itemView.findViewById(R.id.tvTaskTime);
            checkTask = itemView.findViewById(R.id.checkTask);

        }
    }
}
