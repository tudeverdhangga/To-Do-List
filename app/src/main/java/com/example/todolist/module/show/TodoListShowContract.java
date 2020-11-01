package com.example.todolist.module.show;

import com.example.todolist.base.BasePresenter;
import com.example.todolist.base.BaseView;
import com.example.todolist.model.ToDo;

public interface TodoListShowContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData(ToDo task);
        void setId(String id);
    }

    interface Presenter extends BasePresenter {
        void saveData(String task_name, String notes, String due_date);
        void loadData(String id);
        void deleteData(String id);
    }
}
