package com.example.todolist.module.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Adapter;
import com.example.todolist.R;
import com.example.todolist.base.BaseFragment;
import com.example.todolist.model.ToDo;
import com.example.todolist.module.create.TodoListCreate;
import com.example.todolist.module.login.LoginActivity;
import com.example.todolist.module.show.TodoListShow;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends BaseFragment<MainActivity, MainContract.Presenter> implements MainContract.View, Adapter.MyClickListener
{

    RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton buttonAdd;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private ArrayList<ToDo> list_task;
    private TextView empty_task_tv;
    private Button logout_btn;
    private GoogleSignInClient mGoogleSignInClient;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_main, container, false);
        mPresenter = new MainPresenter(this);
        mPresenter.start();

        empty_task_tv = fragmentView.findViewById(R.id.empty_task_tv);
        logout_btn = fragmentView.findViewById(R.id.logout_btn);

        mRecyclerView = fragmentView.findViewById(R.id.recycler_view_ly);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FirebaseApp.initializeApp(activity);
        mFirebaseInstance = FirebaseDatabase.getInstance("https://todolist-c95cc-default-rtdb.firebaseio.com/");
        mDatabaseReference = mFirebaseInstance.getReference();
        mDatabaseReference.child("task").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_task = new ArrayList<>();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {
                    ToDo task = mDataSnapshot.getValue(ToDo.class);
                    task.setId(mDataSnapshot.getKey());
                    list_task.add(task);
                }

                if (list_task.isEmpty())
                    empty_task_tv.setText("Task Empty");
                else
                    empty_task_tv.setVisibility(View.GONE);

                mAdapter = new Adapter(list_task);
                mAdapter.setOnItemClickListener(MainFragment.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setTitle("Todo List");

        buttonAdd = fragmentView.findViewById(R.id.fab);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        redirectToLogin();
                    }
                });
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

    public void editTask(ToDo task) {
        Intent intent = new Intent(activity, TodoListShow.class);
        intent.putExtra("TASK", task);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        ToDo task = list_task.get(position);
        editTask(task);
    }

    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
