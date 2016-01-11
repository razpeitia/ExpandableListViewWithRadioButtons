package com.raz.testapi21;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter{

    static class GroupViewHolder {
        TextView textView;
    }

    static class ItemViewHolder {
        TextView textView;
        RadioGroup radioGroup;
    }

    HashMap<Item, List<Item>> itemList;
    Context context;
    static final String TAG = "CustomAdapter";

    public CustomAdapter(Context context, HashMap<Item, List<Item>> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getGroupCount() {
        return itemList.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Item parent = (Item)getGroup(groupPosition);
        return itemList.get(parent).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        for (Item parent: itemList.keySet()) {
            if(parent.id == groupPosition) {
                return parent;
            }
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Item parent = (Item)getGroup(groupPosition);
        return itemList.get(parent).get(childPosition);
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
        Item item = (Item)getGroup(groupPosition);
        GroupViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
            viewHolder = new GroupViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.groupText);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (GroupViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(item.name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Item item = (Item)getChild(groupPosition, childPosition);
        ItemViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ItemViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.listItemText);
            viewHolder.radioGroup = (RadioGroup)convertView.findViewById(R.id.radioGroup);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ItemViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(item.name);
        viewHolder.radioGroup.setOnCheckedChangeListener(null);
        viewHolder.radioGroup.clearCheck();
        switch (item.option) {
            case 1:
                viewHolder.radioGroup.check(R.id.radioAlto);
                break;
            case 2:
                viewHolder.radioGroup.check(R.id.radioMedio);
                break;
            case 3:
                viewHolder.radioGroup.check(R.id.radioBajo);
                break;
        }
        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioAlto:
                        item.option = 1;
                        Log.i(TAG, "ALTO " + item.toString());
                        break;
                    case R.id.radioMedio:
                        item.option = 2;
                        Log.i(TAG, "MEDIO " + item.toString());
                        break;
                    case R.id.radioBajo:
                        item.option = 3;
                        Log.i(TAG, "BAJO " + item.toString());
                        break;
                    default:
                        Log.i(TAG, "OTRO " + item.toString());
                        break;
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
