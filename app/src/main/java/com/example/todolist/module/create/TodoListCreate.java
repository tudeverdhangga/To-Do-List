package com.example.todolist.module.create;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class TodoListCreate extends BaseFragmentHolderActivity {
    TodoListCreateFragment todoListCreateFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        todoListCreateFragment = new TodoListCreateFragment();
        setCurrentFragment(todoListCreateFragment, false);
    }
}
