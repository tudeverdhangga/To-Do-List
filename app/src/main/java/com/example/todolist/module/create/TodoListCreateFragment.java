package com.example.todolist.module.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolist.R;
import com.example.todolist.base.BaseFragment;
import com.example.todolist.model.ToDo;
import com.example.todolist.module.dashboard.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodoListCreateFragment extends BaseFragment<TodoListCreate, TodoListCreateContract.Presenter> implements TodoListCreateContract.View {

    private EditText task_name;
    private EditText notes;
    private EditText due_date;
    private FloatingActionButton btnSave;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;

    public TodoListCreateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_todo_list_create, container, false);
        mPresenter = new TodoListCreatePresenter(this);
        mPresenter.start();

        task_name = fragmentView.findViewById(R.id.form_name_pt);
        notes  = fragmentView.findViewById(R.id.form_notes_pt);
        due_date  = fragmentView.findViewById(R.id.form_due_date_pt);
        btnSave = fragmentView.findViewById(R.id.save_fab);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        FirebaseApp.initializeApp(activity);
        mFirebaseInstance = FirebaseDatabase.getInstance("https://todolist-c95cc-default-rtdb.firebaseio.com/");
        mDatabaseReference = mFirebaseInstance.getReference();

        return fragmentView;
    }

    public void setBtSaveClick(){
        String task_name_str = task_name.getText().toString();
        String notes_str = notes.getText().toString();
        String due_date_str = due_date.getText().toString();
        ToDo task = new ToDo(task_name_str, due_date_str, notes_str);

        mDatabaseReference.child("task").push().setValue(task).addOnSuccessListener(activity, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(activity, "Data barang berhasil di simpan", Toast.LENGTH_SHORT).show();
                redirectToTaskList();
            }
        });
    }

    @Override
    public void setPresenter(TodoListCreateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
