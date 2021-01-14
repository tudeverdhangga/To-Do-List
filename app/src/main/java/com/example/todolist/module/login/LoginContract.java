package com.example.todolist.module.login;

import com.example.todolist.base.BasePresenter;
import com.example.todolist.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
    }

    interface Presenter extends BasePresenter {
    }
}
