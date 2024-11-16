package com.csecorner.try7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Custom ListView Setup
        ListView customListView = findViewById(R.id.custom_list_view);

        // Data for Custom ListView
        List<String> listItems = new ArrayList<>();
        listItems.add("Item 1");
        listItems.add("Item 2");
        listItems.add("Item 3");

        // Adapter for Custom ListView
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listItems
        );
        customListView.setAdapter(listAdapter);

        // Expandable ListView Setup
        ExpandableListView expandableListView = findViewById(R.id.expandable_list_view);

        // Data for Expandable ListView
        List<String> groupList = new ArrayList<>();
        HashMap<String, List<String>> childMap = new HashMap<>();

        // Adding Groups and Children
        groupList.add("Group 1");
        groupList.add("Group 2");

        List<String> group1Children = new ArrayList<>();
        group1Children.add("Child 1.1");
        group1Children.add("Child 1.2");

        List<String> group2Children = new ArrayList<>();
        group2Children.add("Child 2.1");
        group2Children.add("Child 2.2");

        childMap.put(groupList.get(0), group1Children);
        childMap.put(groupList.get(1), group2Children);

        // Adapter for Expandable ListView
        ExpandableListAdapter expandableAdapter = new ExpandableListAdapter(groupList, childMap);
        expandableListView.setAdapter(expandableAdapter);
    }

    // Inner class for ExpandableListAdapter
    private class ExpandableListAdapter extends android.widget.BaseExpandableListAdapter {
        private final List<String> groupList;
        private final HashMap<String, List<String>> childMap;

        ExpandableListAdapter(List<String> groupList, HashMap<String, List<String>> childMap) {
            this.groupList = groupList;
            this.childMap = childMap;
        }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childMap.get(groupList.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childMap.get(groupList.get(groupPosition)).get(childPosition);
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
        public android.view.View getGroupView(int groupPosition, boolean isExpanded, android.view.View convertView, android.view.ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            }
            android.widget.TextView groupText = convertView.findViewById(android.R.id.text1);
            groupText.setText((String) getGroup(groupPosition));
            return convertView;
        }

        @Override
        public android.view.View getChildView(int groupPosition, int childPosition, boolean isLastChild, android.view.View convertView, android.view.ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            android.widget.TextView childText = convertView.findViewById(android.R.id.text1);
            childText.setText((String) getChild(groupPosition, childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}