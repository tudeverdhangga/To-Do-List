package com.example.todolist.module.create;

import com.example.todolist.base.BasePresenter;
import com.example.todolist.base.BaseView;

public interface TodoListCreateContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
    }

    interface Presenter extends BasePresenter {
        void saveData(String task_name, String notes, String due_date);
    }
}
