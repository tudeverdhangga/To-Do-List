package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolist.model.ToDo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.TodoViewHolder>{
    private List<ToDo> todo = new ArrayList<>();
    private static MyClickListener myClickListener;

    public Adapter(ArrayList<ToDo> data) {
        todo = data;
    }

    @NonNull
    @Override
    public Adapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_list_task, viewGroup, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int i) {
        ToDo temp = todo.get(i);

        holder.title.setText(temp.getTitle());
        holder.title.setTag(i);
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
        public void onItemClick(int position, View v);
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView notes;
        private TextView due_date;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            notes = itemView.findViewById(R.id.notes_tv);
            due_date = itemView.findViewById(R.id.due_date_tv);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            myClickListener.onItemClick(position, v);
        }
    }
}
