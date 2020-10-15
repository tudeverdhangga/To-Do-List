package com.example.todolist.module.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.todolist.R;
import com.example.todolist.base.BaseActivity;
import com.example.todolist.db.DB;
import com.example.todolist.module.dashboard.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;

public class TodoListShow extends BaseActivity {
    private EditText title;
    private EditText notes;
    private EditText due_date;
    private RadioButton complete;
    private TodoListShowPresenter presenter;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_todo_list_show);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.update_fab);
        title = (EditText) findViewById(R.id.form_name_pt);
        notes = (EditText) findViewById(R.id.form_notes_pt);
        due_date = (EditText) findViewById(R.id.form_due_date_pt);
        complete = (RadioButton)  findViewById(R.id.completed_rb);
        presenter = new TodoListShowPresenter();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final int id = (int) bundle.get("id");
            String mTitle = DB.getInstance().getDb().get(id).getTitle();
            String mNotes = DB.getInstance().getDb().get(id).getNotes();
            String mDueDate = DB.getInstance().getDb().get(id).getDue_date();

            title.setText(mTitle);
            notes.setText(mNotes);
            due_date.setText(mDueDate);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.update(title, notes, due_date, complete, id);
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    startActivityForResult(i, 0);
                }
            });
        }
    }

}
