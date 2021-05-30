package com.example.sit708_assessment3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements a RecyclerView with CardView that displays a list of task names and images.
 * - Clicking an item navigates to corresponding detail screen.
 * - Clicking the app bar item Checklist navigates to checklist screen.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView tasks;
    private TasksAdapter tasksAdapter;
    private ArrayList<String> items;
    private ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // initialize ArrayList.
        items = new ArrayList<>();
        // Get a list of task names.
        String[] task_category = getResources().getStringArray(R.array.task_category);
        // Add a list of task names to ArrayList items.
        items.addAll(Arrays.asList(task_category));
        // initialize ArrayList.
        images = new ArrayList<>();
        // Get a list of task images.
        String[] task_image = getResources().getStringArray(R.array.task_image);
        // Add a list of task images to ArrayList images.
        images.addAll(Arrays.asList(task_image));
        // Create recycler view.
        tasks = findViewById(R.id.tasks);
        // Give the recycler view a default layout manager.
        tasks.setLayoutManager(new LinearLayoutManager(this));
        // Create an adapter and supply the task names and images to be displayed.
        tasksAdapter = new TasksAdapter(this, items, images);
        // Connect the adapter with the recycler view.
        tasks.setAdapter(tasksAdapter);
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Checklist on button click.
        if (id == R.id.action_checklist) {
            // Create a intent.
            Intent i = new Intent(this,Checklist.class);
            // Put a list of task names to this intent.
            i.putExtra("data", items);
            // Start the new activity.
            this.startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}