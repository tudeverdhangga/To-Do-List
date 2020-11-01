package com.example.todolist.module.create;

import com.example.todolist.model.ToDo;

public class TodoListCreatePresenter implements TodoListCreateContract.Presenter{
    private final TodoListCreateContract.View view;

    public TodoListCreatePresenter(TodoListCreateContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(String task_name, String notes, String due_date) {
        ToDo task = new ToDo("3", "Activity New", "20 Nov 2020", "Kerjakan Activity 3");
        view.redirectToTaskList();
    }
}
