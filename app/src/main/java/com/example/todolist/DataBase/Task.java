package com.example.todolist.DataBase;

public class Task {

    String title ;
    String description;
    int is_completed;
    String date ;


    public Task( String title, String description, int is_completed, String date) {
        this.title = title;
        this.description = description;
        this.is_completed = is_completed;
        this.date = date;
    }



    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    public int getIs_completed() {
        return is_completed;
    }


    public String getDate() {
        return date;
    }


}
