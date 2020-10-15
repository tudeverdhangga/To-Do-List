package com.example.todolist.module.create;

import android.widget.EditText;

import com.example.todolist.db.DB;
import com.example.todolist.model.ToDo;

public class TodoListCreatePresenter {
    public void save(EditText task_name, EditText notes, EditText due_date) {
        String task_name_str = task_name.getText().toString();
        String notes_str = notes.getText().toString();
        String due_date_str = due_date.getText().toString();
        ToDo newList = new ToDo(task_name_str, due_date_str, notes_str);
        DB.getInstance().setDb(newList);
    }
}
