package com.example.todolist.module.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.Adapter;
import com.example.todolist.R;
import com.example.todolist.base.BaseFragment;
import com.example.todolist.model.ToDo;
import com.example.todolist.module.create.TodoListCreate;
import com.example.todolist.module.show.TodoListShow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends BaseFragment<MainActivity, MainContract.Presenter> implements MainContract.View {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton buttonAdd;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_main, container, false);
        mPresenter = new MainPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recycler_view_ly);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<ToDo> data = mPresenter.getDataSet();
        mAdapter = new Adapter(data);
        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");

        buttonAdd = fragmentView.findViewById(R.id.fab);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask();
            }
        });

        ((Adapter) mAdapter).setOnItemClickListener(new Adapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                editTask(id);
            }
        });

        return fragmentView;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void gotoNewTask() {
        Intent intent = new Intent(activity, TodoListCreate.class);
        startActivity(intent);
    }

    public void editTask(String id) {
        Intent intent = new Intent(activity, TodoListShow.class);
        intent.putExtra("TaskId", id);
        startActivity(intent);
    }
}
