package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TodoViewHolder>{
    private List<ToDo> todo = new ArrayList<>();
    private MyClickListener myClickListener;
    private View view;

    public Adapter(ArrayList<ToDo> data) {
        todo = data;
    }

    @NonNull
    @Override
    public Adapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_list_task, viewGroup, false);
        return new TodoViewHolder(view, myClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int i) {
        ToDo temp = todo.get(i);

        holder.title.setText(temp.getTitle());
        holder.notes.setText(temp.getNotes());
        holder.due_date.setText("(" + temp.getDue_date() + ")");
    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    public void updateData(List<ToDo> toDoList) {
        this.todo = toDoList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        void onItemClick(int position);
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView notes;
        private TextView due_date;
        private MyClickListener myClickListener;

        public TodoViewHolder(@NonNull View itemView, MyClickListener myClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            notes = itemView.findViewById(R.id.notes_tv);
            due_date = itemView.findViewById(R.id.due_date_tv);
            this.myClickListener = myClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position);
        }
    }
}
