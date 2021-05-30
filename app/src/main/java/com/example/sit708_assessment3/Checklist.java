package com.example.sit708_assessment3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a RecyclerView with CardView that displays a list of task names and checkbox.
 * - Clicking an item navigates to corresponding detail screen.
 * - Clicking the checkbox changes the state of the checkbox and the state are saved.
 */
public class Checklist extends AppCompatActivity {

    private RecyclerView checklist;
    private ChecklistAdapter checklistAdapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
        // Get the intent.
        Intent i = getIntent();
        // Retrieve the list of task names.
        data = i.getStringArrayListExtra("data");
        // Create recycler view.
        checklist = findViewById(R.id.checklist);
        // Give the recycler view a default layout manager.
        checklist.setLayoutManager(new LinearLayoutManager(this));
        // Create an adapter and supply the task names to be displayed.
        checklistAdapter = new ChecklistAdapter(this, data);
        // Connect the adapter with the recycler view.
        checklist.setAdapter(checklistAdapter);
    }

}
