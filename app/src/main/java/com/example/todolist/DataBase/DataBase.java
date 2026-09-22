package com.example.todolist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String TASK_TABLE = "task";
    public static final String CO_ID = "id";
    public static final String CO_TITLE = "title";  // اسم المهمة
    public static final String CO_DESCRIPTION = "description" ; // وصف للمهمة
    public static final String CO_DATE = "date" ; // تاريخ اضافة المهمة
    public static final String CO_IS_COMPLETED = "is_completed" ; // هل المهمة منجزة ولا
    public DataBase(@Nullable Context context ) {
        super(context, "Database ToDoList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qwiry = "CREATE TABLE " + TASK_TABLE + " (" +
                CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CO_TITLE + " TEXT, "
                + CO_DESCRIPTION + " TEXT, "
                + CO_DATE + " TEXT, "
                + CO_IS_COMPLETED + " INTEGER" + ")";
        db.execSQL(qwiry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE  IF EXISTS "+TASK_TABLE);
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CO_TITLE,        task.getTitle());
        cv.put(CO_DESCRIPTION,  task.getDescription());
        cv.put(CO_DATE,         task.getDate());
        cv.put(CO_IS_COMPLETED, task.getIs_completed());

        db.insert(TASK_TABLE, null, cv);
        db.close();
    }
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TASK_TABLE,
                null, null, null, null, null, null
        );

        while (cursor.moveToNext()) {
            String title       = cursor.getString(cursor.getColumnIndexOrThrow(CO_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(CO_DESCRIPTION));
            String date        = cursor.getString(cursor.getColumnIndexOrThrow(CO_DATE));
            int is_completed   = cursor.getInt(cursor.getColumnIndexOrThrow(CO_IS_COMPLETED));

            taskList.add(new Task(title, description, is_completed, date));
        }

        cursor.close();
        db.close();
        return taskList;
    }

    public void deleteTask(String title){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE,CO_TITLE+"=?",new String[]{title});
        db.close();
    }
}
