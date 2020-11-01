package com.example.todolist.module.create;

import android.view.View;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class TodoListCreate extends BaseFragmentHolderActivity {
    TodoListCreateFragment todoListCreateFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        todoListCreateFragment = new TodoListCreateFragment();
        setCurrentFragment(todoListCreateFragment, false);
    }
}
