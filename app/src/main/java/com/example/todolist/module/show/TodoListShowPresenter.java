package com.example.todolist.module.show;

import com.example.todolist.model.ToDo;

public class TodoListShowPresenter implements TodoListShowContract.Presenter{
    private final TodoListShowContract.View view;

    public TodoListShowPresenter(TodoListShowContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(String task_name, String notes, String due_date) {
        ToDo newTask = new ToDo("3", task_name, notes, due_date);
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        ToDo task = new ToDo("3", "Activity New", "20 Nov 2020", "Kerjakan Activity 3");
        view.showData(task);
    }

    @Override
    public void deleteData(String id) {
        ToDo task = new ToDo("3", "Activity New", "20 Nov 2020", "Kerjakan Activity 3");
        view.redirectToTaskList();
    }
}
