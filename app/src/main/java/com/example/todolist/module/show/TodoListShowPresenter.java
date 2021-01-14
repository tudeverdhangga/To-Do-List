package com.example.todolist.module.show;

import android.util.Log;

import com.example.todolist.model.ToDo;
import com.google.firebase.database.DatabaseReference;

public class TodoListShowPresenter implements TodoListShowContract.Presenter{
    private final TodoListShowContract.View view;

    public TodoListShowPresenter(TodoListShowContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(String id, ToDo task, DatabaseReference mDatabaseReference) {
        Log.d("ID_TASK", "saveData: " + task.getTitle());
        mDatabaseReference.child("task")
                            .child(id)
                            .setValue(task);
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        ToDo task = new ToDo("Activity New", "20 Nov 2020", "Kerjakan Activity 3");
        view.showData(task);
    }

    @Override
    public void deleteData(String id, DatabaseReference mDatabaseReference) {
        mDatabaseReference.child("task")
                .child(id)
                .removeValue();
        view.redirectToTaskList();
    }
}
