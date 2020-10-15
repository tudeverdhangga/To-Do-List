package com.example.todolist.db;

import com.example.todolist.model.ToDo;

import java.util.ArrayList;

public class DB {
    private ArrayList<ToDo> db = new ArrayList<>();

    private DB() {
    }

    public ArrayList<ToDo> getDb() {
        return db;
    }

    public void setDb(ToDo list) {
        db.add(list);
    }

    public static DB getInstance() {
        return DBHolder.INSTANCE;
    }

    private static class DBHolder {

        private static final DB INSTANCE = new DB();
    }
}
