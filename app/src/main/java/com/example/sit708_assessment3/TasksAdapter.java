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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An Adapter for the RecyclerView on the home screen.
 * Add a click handler for each item in the ViewHolder.
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ArrayList<String> data;
    private final ArrayList<String> images;
    private Context context;

    // Constructor of TasksAdapter
    TasksAdapter(Context context, ArrayList<String> data, ArrayList<String> images)
    {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.images = images;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * The new ViewHolder will be used to display items of the adapter using onBindViewHolder(ViewHolder, int, List).
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @NonNull
    @Override
    public TasksAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View taskView = layoutInflater.inflate(R.layout.tasks_item, parent, false);
        return new TaskViewHolder(taskView);
    }

    /**
     * Called by RecyclerView to display the task names and images at the specified position.
     * This method should update the contents of the ViewHolder.itemView to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Retrieve the task name for that position.
        String title = data.get(position);
        // Add the task name to the view holder.
        holder.taskTitle.setText(title);
        // Retrieve the task image for that position.
        int id = context.getResources().getIdentifier("com.example.sit708_assessment3:drawable/" + images.get(position), null, null);
        // Add the task image to the view holder.
        holder.taskImage.setImageResource(id);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Creates a new custom view holder to hold the view to display in the RecyclerView.
     * Handle the itemView on button click.
     */
    public class TaskViewHolder extends RecyclerView.ViewHolder {

        ImageView taskImage;
        TextView taskTitle;

        public TaskViewHolder (@NonNull View itemView) {
            super(itemView);
            // Set itemView on button click.
            itemView.setOnClickListener(v -> {
                // Create new Intent .
                Intent i = new Intent(v.getContext(),TaskDetails.class);
                // Create new bundle.
                Bundle extras = new Bundle();
                // Put a list of task names into bundle.
                extras.putStringArrayList("data", data);
                // Put the position of itemView into bundle.
                extras.putInt("index", getBindingAdapterPosition());
                // Put bundle into the intent.
                i.putExtras(extras);
                // Pass the intent to the new activity.
                v.getContext().startActivity(i);
            });
            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskImage = itemView.findViewById(R.id.taskImage);
        }
    }
}
