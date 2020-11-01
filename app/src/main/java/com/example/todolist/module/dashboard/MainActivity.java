package com.example.todolist.module.dashboard;

import android.view.View;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class MainActivity extends BaseFragmentHolderActivity {
    MainFragment mainFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        mainFragment = new MainFragment();
        setCurrentFragment(mainFragment, false);
    }
}
