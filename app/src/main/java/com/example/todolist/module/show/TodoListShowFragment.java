package com.example.todolist.module.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.todolist.R;
import com.example.todolist.base.BaseFragment;
import com.example.todolist.model.ToDo;
import com.example.todolist.module.dashboard.MainActivity;

import androidx.annotation.Nullable;

public class TodoListShowFragment extends BaseFragment<TodoListShow, TodoListShowContract.Presenter> implements TodoListShowContract.View {

    private EditText task_name;
    private EditText notes;
    private EditText due_date;
    private Button btnSave;
    private RadioButton complete;
    String id;

    public TodoListShowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_todo_list_show, container, false);
        mPresenter = new TodoListShowPresenter(this);
        mPresenter.start();

        task_name = fragmentView.findViewById(R.id.form_name_pt);
        notes = fragmentView.findViewById(R.id.form_notes_pt);
        due_date = fragmentView.findViewById(R.id.form_due_date_pt);
        complete = fragmentView.findViewById(R.id.completed_rb);
        btnSave = fragmentView.findViewById(R.id.update_fab);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        return fragmentView;
    }

    public void setBtSaveClick(){
        String task_name_str = task_name.getText().toString();
        String notes_str = notes.getText().toString();
        String due_date_str = due_date.getText().toString();
        String status = "";

        if (complete.isChecked()) {
            status = "Completed";
        }

        if (status.equals("Completed"))
            mPresenter.deleteData(id);
        else
            mPresenter.saveData(task_name_str, notes_str, due_date_str);
    }

    @Override
    public void setPresenter(TodoListShowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showData(ToDo task) {
        this.task_name.setText(task.getTitle());
        this.notes.setText(task.getNotes());
        this.due_date.setText(task.getDue_date());
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }
}
