package com.example.todolist.module.show;

import android.widget.EditText;
import android.widget.RadioButton;

import com.example.todolist.db.DB;
import com.example.todolist.model.ToDo;

public class TodoListShowPresenter {
    public void update(EditText title, EditText notes, EditText due_date, RadioButton complete, int id) {
        String task_name_str = title.getText().toString();
        String notes_str = notes.getText().toString();
        String due_date_str = due_date.getText().toString();
        String status = "";
        if (complete.isChecked()) {
            status = "Completed";
        }
        if (status.equals("Completed")) {
            DB.getInstance().getDb().remove(id);
        } else {
            ToDo updateList = new ToDo(task_name_str, due_date_str, notes_str);
            DB.getInstance().getDb().set(id, updateList);
        }
    }
}
