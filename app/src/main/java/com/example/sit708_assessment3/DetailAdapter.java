package com.example.sit708_assessment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * An Adapter for the ExpandableListView on the detail screen.
 */
public class DetailAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> detailCategory;
    HashMap<String, String> detailInfo;

    // Constructor of DetailAdapter
    public  DetailAdapter(Context context, List<String> detailCategory, HashMap<String, String> detailInfo){
        this.context = context;
        this.detailCategory = detailCategory;
        this.detailInfo = detailInfo;
    }

    /**
     * Returns the total number of groups in the data set held by the adapter.
     *
     * @return The total number of groups in this adapter.
     */
    @Override
    public int getGroupCount() {
        return detailCategory.size();
    }

    /**
     * Returns the total number of children in the group held by the adapter.
     *
     * @return The total number of children in this group.
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * Returns the group at the given position in the data set held by the adapter.
     *
     * @return The group at the given position in the data set.
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this.detailCategory.get(groupPosition);
    }

    /**
     * Returns the child at the given position in the group held by the adapter.
     *
     * @return The child at the given position in the group.
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.detailInfo.get(this.detailCategory.get(groupPosition));
    }

    /**
     * Returns the position of the group in the data set held by the adapter.
     *
     * @return The position of the group in the data set.
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Returns the position of the child in the group held by the adapter.
     *
     * @return The position of the child in the group.
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Returns a boolean indicating if has stable ids.
     *
     * @return false.
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Set a View to represents the task category.
     *
     * @return A view which represents the category.
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // Retrieve the category name.
        String category = (String) getGroup(groupPosition);
        if (convertView == null){
            // Get the layoutInflater.
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Inflate the textView.
            convertView = layoutInflater.inflate(R.layout.details_category, null);
        }
        // Find the TextView of task category.
        TextView textView = convertView.findViewById(R.id.detail_category);
        // Set the category name to textView.
        textView.setText(category);
        return convertView;
    }

    /**
     * Set a View to represents the details of a category.
     *
     * @return A view which represents the details.
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Retrieve the detail string.
        String info = (String) getChild(groupPosition,childPosition);
        if (convertView == null){
            // Get the layoutInflater.
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Inflate the textView.
            convertView = layoutInflater.inflate(R.layout.details_info, null);
        }
        // Find the TextView of the detail.
        TextView textView = convertView.findViewById(R.id.detail_info);
        // Set the detail string to textView.
        textView.setText(info);
        return convertView;
    }

    /**
     * Returns a boolean indicating if children is selectable.
     *
     * @return true.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
