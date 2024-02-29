package com.bawp.todoister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.todoister.R;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.Util;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final List<Task> taskList;
    private final OnTodoClickListener onTodoClickListener;

    public RecycleViewAdapter(List<Task> taskList, OnTodoClickListener onTodoClickListener) {
        this.taskList = taskList;
        this.onTodoClickListener = onTodoClickListener;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        String formattedDate = Util.formatDate(task.getDueDate());

        holder.taskTV.setText(task.getTask());
        holder.todayChip.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public AppCompatRadioButton radioBtn;
        public AppCompatTextView taskTV;
        public Chip todayChip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioBtn = itemView.findViewById(R.id.todo_radio_button);
            taskTV = itemView.findViewById(R.id.todo_row_todo);
            todayChip = itemView.findViewById(R.id.todo_row_chip);
            itemView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            Task currTask = taskList.get(getAdapterPosition());

            int id = view.getId();
            if (id == R.id.todo_row_layout) {
                onTodoClickListener.onTodoClick(getAdapterPosition(), currTask);
            }
//            else if (id == R.id.todo_radio_button) {
//                onTodoClickListener.onTodoRadioButtonClick(currTask);

            }

        }
    }

