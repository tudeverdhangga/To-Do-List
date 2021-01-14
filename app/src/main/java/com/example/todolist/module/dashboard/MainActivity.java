package com.example.todolist.module.dashboard;

import com.example.todolist.Adapter;
import com.example.todolist.base.BaseFragmentHolderActivity;

public class MainActivity extends BaseFragmentHolderActivity implements Adapter.MyClickListener {
    MainFragment mainFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        mainFragment = new MainFragment();
        setCurrentFragment(mainFragment, false);
    }

    @Override
    public void onItemClick(int position) {

    }
}
