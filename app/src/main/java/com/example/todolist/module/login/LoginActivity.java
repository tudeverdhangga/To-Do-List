package com.example.todolist.module.login;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }
}
