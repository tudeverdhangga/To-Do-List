package com.example.todolist.base;

import com.example.todolist.R;


public abstract class BaseFragmentHolderActivity extends BaseActivity {
    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
    }
}
