package com.example.todolist.module.show;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class TodoListShow extends BaseFragmentHolderActivity {
    TodoListShowFragment todoListShowFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        todoListShowFragment = new TodoListShowFragment();
        String id = getIntent().getExtras().getString("TaskId");
        todoListShowFragment.setId(id);
        setCurrentFragment(todoListShowFragment, false);
    }
}
