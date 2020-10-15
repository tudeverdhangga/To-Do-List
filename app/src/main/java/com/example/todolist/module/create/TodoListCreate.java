package com.example.todolist.module.create;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.todolist.R;
import com.example.todolist.base.BaseActivity;
import com.example.todolist.module.dashboard.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodoListCreate extends BaseActivity {
    private EditText task_name;
    private EditText notes;
    private EditText due_date;
    private TodoListCreatePresenter presenter;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_todo_list_create);
        FloatingActionButton fab = findViewById(R.id.save_fab);
        task_name = (EditText) findViewById(R.id.form_name_pt);
        notes  = (EditText) findViewById(R.id.form_notes_pt);
        due_date  = (EditText) findViewById(R.id.form_due_date_pt);
        presenter = new TodoListCreatePresenter();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.save(task_name, notes, due_date);
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }
}
