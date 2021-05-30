package com.example.sit708_assessment3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * An Adapter for the RecyclerView on the checklist screen.
 * Add a click handler for each item and checkbox in the ViewHolder.
 */
public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>{

    private final LayoutInflater layoutInflater;
    private final ArrayList<String> data;
    Context context;

    // Constructor of ChecklistAdapter
    ChecklistAdapter(Context context, ArrayList<String> data){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
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
    public ChecklistAdapter.ChecklistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View checklistView = layoutInflater.inflate(R.layout.checklist_item, parent, false);
        return new ChecklistViewHolder(checklistView);
    }

    /**
     * Called by RecyclerView to display the task names and checkbox at the specified position.
     * This method should update the contents of the ViewHolder.itemView to reflect the item at the given position.
     * This method should retrieve the saved state of each checkbox from SharedPreference and set the state to checkbox.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ChecklistAdapter.ChecklistViewHolder holder, int position) {
        // Retrieve the task name for that position.
        String title = data.get(position);
        // Add the task name to the view holder.
        holder.taskTitle.setText(title);
        // Retrieve the saved state of the checkbox at the given position.
        boolean checked = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(String.valueOf(position), false);
        // Set the state to checkbox.
        holder.checkBox.setChecked(checked);
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
     * Handle the itemView on button click and checkbox on button click.
     * Save the state of checkbox to SharedPreference.
     */
    public class ChecklistViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitle;
        CheckBox checkBox;

        public ChecklistViewHolder (@NonNull View itemView) {
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
            checkBox = itemView.findViewById(R.id.checkbox);
            // Set checkBox on button click.
            checkBox.setOnClickListener(v -> {
                //save the state of the checkbox to SharedPreference.
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(String.valueOf(getBindingAdapterPosition()),checkBox.isChecked()).apply();
            });
        }
    }

}
