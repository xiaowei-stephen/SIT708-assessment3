package com.example.sit708_assessment3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Implements a ExpandableListView that displays the categories and information of a task.
 * - Clicking the app bar item Checklist navigates to checklist screen.
 */
public class TaskDetails extends AppCompatActivity {
    ExpandableListView details;
    ArrayList<String> data;
    List<String> detailCategory;
    HashMap<String, String> detailInfo;
    DetailAdapter adapter;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // Find the ExpandableListView.
        details = findViewById(R.id.details);
        // initialize ArrayList.
        detailCategory = new ArrayList<>();
        // initialize HashMap.
        detailInfo = new HashMap<>();
        // Create an adapter and supply the task categories and information to be displayed.
        adapter = new DetailAdapter(this, detailCategory, detailInfo);
        // Connect the adapter with the ExpandableListView.
        details.setAdapter(adapter);
        // Get the bundle.
        Bundle extras = getIntent().getExtras();
        // Retrieve the list of task names.
        data = extras.getStringArrayList("data");
        // Retrieve the position of the task in the list.
        index = extras.getInt("index");
        // Set the title of this screen.
        this.setTitle(data.get(index));
        // Initialize the data.
        initListData();
    }

    /**
     * Initialize the data set detailCategory and detailInfo by adding corresponding data inside.
     */
    private void initListData() {
        // Add first category to detailCategory.
        detailCategory.add(getString(R.string.category_what));
        // Add second category to detailCategory.
        detailCategory.add(getString(R.string.category_why));
        // Add third category to detailCategory.
        detailCategory.add(getString(R.string.category_how));
        // Retrieve the string array which contains the details of the first category.
        String[] info_what = getResources().getStringArray(R.array.category_what);
        // Retrieve the string array which contains the details of the second category.
        String[] info_why = getResources().getStringArray(R.array.category_why);
        // Retrieve the string array which contains the details of the third category.
        String[] info_how = getResources().getStringArray(R.array.category_how);
        // Add the details to the first category.
        detailInfo.put(detailCategory.get(0), info_what[index]);
        // Add the details to the second category.
        detailInfo.put(detailCategory.get(1), info_why[index]);
        // Add the details to the third category.
        detailInfo.put(detailCategory.get(2), info_how[index]);

        // Notify the adapter, that the data has changed so it can update the ExpandableListView to display the data.
        adapter.notifyDataSetChanged();
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
            i.putExtra("data", data);
            // Start the new activity.
            this.startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
