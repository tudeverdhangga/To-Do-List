package com.example.todolist.module.show;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolist.R;
import com.example.todolist.base.BaseFragment;
import com.example.todolist.model.ToDo;
import com.example.todolist.module.dashboard.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodoListShowFragment extends BaseFragment<TodoListShow, TodoListShowContract.Presenter> implements TodoListShowContract.View{

    private EditText task_name;
    private EditText notes;
    private EditText due_date;
    private FloatingActionButton btnSave;
    private RadioButton complete;
    private ToDo task;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private Button mButton;
    private String task_name_str = "";
    private String notes_str = "";
    private String due_date_str = "";
    private String message = "";
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

        task = (ToDo) getActivity().getIntent().getSerializableExtra("TASK");
        showData(task);

        FirebaseApp.initializeApp(activity);
        mFirebaseInstance = FirebaseDatabase.getInstance("https://todolist-c95cc-default-rtdb.firebaseio.com/");
        mDatabaseReference = mFirebaseInstance.getReference();

        mButton = fragmentView.findViewById(R.id.share_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInputDialog();
            }
        });
        return fragmentView;
    }

    private void showUserInputDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Input Email");
        final EditText input = new EditText(activity);
        alert.setView(input);

        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String to = input.getText().toString();
                sendEmail(to);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogBox, int id) {
                dialogBox.cancel();
            }
        });

        AlertDialog alertDialogAndroid = alert.create();
        alertDialogAndroid.show();
    }

    private void sendEmail(String to) {
        String subject = "To Do List";
        task_name_str = task_name.getText().toString();
        notes_str = notes.getText().toString();
        due_date_str = due_date.getText().toString();
        message = "Task Name : " + task_name_str + "\nNotes : " + notes_str + "\nDue Date : " + due_date_str;

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(email, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setBtSaveClick(){
        task_name_str = task_name.getText().toString();
        notes_str = notes.getText().toString();
        due_date_str = due_date.getText().toString();
        String status = "";
        ToDo newTask = new ToDo(task_name_str, due_date_str, notes_str);

        if (complete.isChecked())
            status = "Completed";

        if (status.equals("Completed"))
            mPresenter.deleteData(task.getId(), mDatabaseReference);
        else
            mPresenter.saveData(task.getId(), newTask, mDatabaseReference);
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
