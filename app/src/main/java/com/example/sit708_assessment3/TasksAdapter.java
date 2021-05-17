package com.example.sit708_assessment3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<String> data;

    TasksAdapter(Context context, List<String> data)
    {
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public TasksAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskView = layoutInflater.inflate(R.layout.tasks_item, parent, false);
        return new TaskViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String title = data.get(position);
        holder.taskTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        ImageView taskImage;
        TextView taskTitle;

        public TaskViewHolder (@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                Intent i = new Intent(v.getContext(),TaskDetails.class);
                i.putExtra("title", data.get(getBindingAdapterPosition()) );
                v.getContext().startActivity(i);
            });
            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskImage = itemView.findViewById(R.id.taskImage);
        }
    }
}
