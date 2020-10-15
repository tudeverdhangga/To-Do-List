package com.example.todolist.module.dashboard;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.todolist.Adapter;
import com.example.todolist.R;
import com.example.todolist.module.create.TodoListCreate;
import com.example.todolist.module.show.TodoListShow;
import com.example.todolist.base.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {
    private Adapter adapter;
    private TextView empty;
    private RecyclerView recyclerView;
    private MainPresenter mainPresenter;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        empty = (TextView) findViewById(R.id.empty_task_tv);
        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_ly);

        mainPresenter = new MainPresenter();

        setSupportActionBar(toolbar);
        empty.setVisibility(View.INVISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), TodoListCreate.class);
                startActivityForResult(i, 0);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        setData();
    }

    private void setData() {
        String returnMsg = mainPresenter.setData(adapter);
        empty.setText(returnMsg);
        empty.setVisibility(View.VISIBLE);
    }

    public void taskShow(View title) {
        int i = mainPresenter.taskShow(title);
        Intent intent = new Intent(MainActivity.this, TodoListShow.class);
        intent.putExtra("id", i);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
