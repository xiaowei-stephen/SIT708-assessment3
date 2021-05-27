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

public class TaskDetails extends AppCompatActivity {
    ExpandableListView details;
    List<String> detailCategory;
    HashMap<String, String> detailInfo;
    DetailAdapter adapter;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        details = findViewById(R.id.details);
        detailCategory = new ArrayList<>();
        detailInfo = new HashMap<>();
        adapter = new DetailAdapter(this, detailCategory, detailInfo);
        details.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        String task_name = extras.getString("title");
        index = extras.getInt("index");
        this.setTitle(task_name);
        initListData();
    }

    private void initListData() {
        detailCategory.add(getString(R.string.category_what));
        detailCategory.add(getString(R.string.category_why));
        detailCategory.add(getString(R.string.category_how));

        String[] info_what = getResources().getStringArray(R.array.category_what);
        String[] info_why = getResources().getStringArray(R.array.category_why);
        String[] info_how = getResources().getStringArray(R.array.category_how);

        detailInfo.put(detailCategory.get(0), info_what[index]);
        detailInfo.put(detailCategory.get(1), info_why[index]);
        detailInfo.put(detailCategory.get(2), info_how[index]);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_checklist) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
