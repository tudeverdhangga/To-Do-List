package com.example.todolist.module.show;

import android.view.View;

import com.example.todolist.base.BaseFragmentHolderActivity;

public class TodoListShow extends BaseFragmentHolderActivity {
    TodoListShowFragment todoListShowFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        todoListShowFragment = new TodoListShowFragment();
        String id = getIntent().getExtras().getString("TaskId");
        todoListShowFragment.setId(id);
        setCurrentFragment(todoListShowFragment, false);
    }

//    @Override
//    protected void initializeView() {
//        setContentView(R.layout.activity_todo_list_show);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.update_fab);
//        title = (EditText) findViewById(R.id.form_name_pt);
//        notes = (EditText) findViewById(R.id.form_notes_pt);
//        due_date = (EditText) findViewById(R.id.form_due_date_pt);
//        complete = (RadioButton)  findViewById(R.id.completed_rb);
//        presenter = new TodoListShowPresenter_old();
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            final int id = (int) bundle.get("id");
//            String mTitle = DB.getInstance().getDb().get(id).getTitle();
//            String mNotes = DB.getInstance().getDb().get(id).getNotes();
//            String mDueDate = DB.getInstance().getDb().get(id).getDue_date();
//
//            title.setText(mTitle);
//            notes.setText(mNotes);
//            due_date.setText(mDueDate);
//
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    presenter.update(title, notes, due_date, complete, id);
//                    Intent i = new Intent(view.getContext(), MainActivity.class);
//                    startActivityForResult(i, 0);
//                }
//            });
//        }
//    }

}
