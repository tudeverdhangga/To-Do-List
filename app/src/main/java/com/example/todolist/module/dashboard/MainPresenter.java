package com.example.todolist.module.dashboard;

import android.view.View;
import android.widget.TextView;

import com.example.todolist.Adapter;
import com.example.todolist.db.DB;
import com.example.todolist.model.ToDo;

import java.util.ArrayList;

public class MainPresenter{


    public String setData(Adapter adapter) {
        ArrayList<ToDo> toDoList = DB.getInstance().getDb();

        if (toDoList.size() == 0) {
            return "Task is Empty";
        }

        adapter.updateData(toDoList);
        return "";
    }

    public int taskShow(View title) {
        TextView task = (TextView) title;
        int i = (int) task.getTag();
        return i;
    }
}
