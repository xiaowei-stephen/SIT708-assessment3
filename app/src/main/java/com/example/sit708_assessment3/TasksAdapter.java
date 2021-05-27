package com.example.sit708_assessment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    private Context context;

    TasksAdapter(Context context, List<String> data)
    {
        this.context = context;
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
        String image_name = "ic_launcher_background";
        int id = context.getResources().getIdentifier("com.example.sit708_assessment3:drawable/" + image_name, null, null);
        holder.taskImage.setImageResource(id);
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
                Bundle extras = new Bundle();
                extras.putString("title", data.get(getBindingAdapterPosition()) );
                extras.putInt("index", getBindingAdapterPosition());
                i.putExtras(extras);
                v.getContext().startActivity(i);
            });
            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskImage = itemView.findViewById(R.id.taskImage);
        }
    }
}
