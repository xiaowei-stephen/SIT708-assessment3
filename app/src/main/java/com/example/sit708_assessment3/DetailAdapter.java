package com.example.sit708_assessment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class DetailAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> detailCategory;
    HashMap<String, String> detailInfo;

    public  DetailAdapter(Context context, List<String> detailCategory, HashMap<String, String> detailInfo){
        this.context = context;
        this.detailCategory = detailCategory;
        this.detailInfo = detailInfo;
    }

    @Override
    public int getGroupCount() {
        return detailCategory.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.detailCategory.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.detailInfo.get(this.detailCategory.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String category = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.details_category, null);
        }

        TextView textView = convertView.findViewById(R.id.detail_category);
        textView.setText(category);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String info = (String) getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.details_info, null);
        }

        TextView textView = convertView.findViewById(R.id.detail_info);
        textView.setText(info);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
