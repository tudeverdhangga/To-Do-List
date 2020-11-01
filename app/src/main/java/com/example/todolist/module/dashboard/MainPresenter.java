package com.example.todolist.module.dashboard;

import com.example.todolist.model.ToDo;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public ArrayList<ToDo> getDataSet() {
        ArrayList<ToDo> data = new ArrayList<>();
        data.add(new ToDo("1", "Activity 1", "25 Nov 2020", "Kerjakan task 1"));
        data.add(new ToDo("2", "Activity 2", "12 Nov 2020", "Kerjakan task 2"));
        return data;
    }
}
