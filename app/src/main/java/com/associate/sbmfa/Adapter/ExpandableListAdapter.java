package com.associate.sbmfa.Adapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Model.Parent_model;
import com.associate.sbmfa.R;
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<Parent_model> parent_models=new ArrayList<>();
    public ExpandableListAdapter(Context context, ArrayList<Parent_model> parent_models) {
        if (context!=null) {
            this._context = context;
            this.parent_models = parent_models;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.parent_models.get(groupPosition).getChild_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        TextView topic_name,complete_qustions,all_qustions;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.practice_tab_item_child, null);
        }

        topic_name=convertView.findViewById(R.id.soName);
        complete_qustions=convertView.findViewById(R.id.roName);
        all_qustions=convertView.findViewById(R.id.zoName);

        topic_name.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getName());
        complete_qustions.setText("jaipur");
        all_qustions.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getQuations());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.parent_models.get(groupPosition).getChild_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parent_models.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        TextView name,count,des;
        ImageView up_dwon_icon;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.practice_tab_item, null);
        }
        name=convertView.findViewById(R.id.name);
        count=convertView.findViewById(R.id.id);
        des=convertView.findViewById(R.id.des);
        name.setText(parent_models.get(groupPosition).getName());
        count.setText(parent_models.get(groupPosition).getId());
        des.setText(parent_models.get(groupPosition).getChapter_count());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
           up_dwon_icon.setImageResource(R.drawable.ic_baseline_indeterminate_check_box_24);
        } else {
            up_dwon_icon.setImageResource(R.drawable.ic_baseline_add_box_24);
        }
     //   chapter_count.setText(parent_models.get(groupPosition).getChapter_count()+"  Chapter");
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}