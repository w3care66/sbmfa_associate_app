package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_details_Parent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;

public class Associate_Details_ListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<Associate_details_Parent_model> parent_models=new ArrayList<>();
    EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String id, String memberid, String type);
    }
    public Associate_Details_ListAdapter(Context context, ArrayList<Associate_details_Parent_model> parent_models,EventListener eventListener) {
        if (context!=null) {
            this._context = context;
            this.parent_models = parent_models;
            this.eventListener = eventListener;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView name,code,associate_branch,branch_code,secter_namwe,nominee_name,Nominee_relation,mobile_no,nominee_age,status,email_id;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_details_item_child, null);
        }
        ImageView imageView;
        TextView textView65Card;
        imageView = convertView.findViewById(R.id.imageView22);
        nominee_name = convertView.findViewById(R.id.textView69);
        Nominee_relation = convertView.findViewById(R.id.textView71);
        mobile_no = convertView.findViewById(R.id.textView77);
        nominee_age = convertView.findViewById(R.id.textView73);
        status = convertView.findViewById(R.id.textView79);
        email_id = convertView.findViewById(R.id.textView75);

        textView65Card = convertView.findViewById(R.id.textView65);
        name=convertView.findViewById(R.id.textView13);
        code=convertView.findViewById(R.id.textView15);
        associate_branch=convertView.findViewById(R.id.textView37);
        branch_code=convertView.findViewById(R.id.textView39);
        secter_namwe=convertView.findViewById(R.id.textView41);

        status.setText(parent_models.get(groupPosition).getStatus_2());
        nominee_name.setText(parent_models.get(groupPosition).getNominee_name());
        Nominee_relation.setText(parent_models.get(groupPosition).getRelation());
        mobile_no.setText(parent_models.get(groupPosition).getMobile_no());
        nominee_age.setText(parent_models.get(groupPosition).getNominee_age());
        email_id.setText(parent_models.get(groupPosition).getEmail());

        name.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getAssociate_cader());
        code.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getAssociate_code());
        associate_branch.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getAssociate_branch_name());
        branch_code.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getBranch_code());
        secter_namwe.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getSector_name());
        textView65Card.setText(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getAssociate_carder_new());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent_View(parent_models.get(groupPosition).getAssociate_details_child_models().get(childPosition).getAssociate_image(),"","");
            }
        });

        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.parent_models.get(groupPosition).getAssociate_details_child_models().size();
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
        TextView name,count,id;
        ImageView up_dwon_icon;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_details_item_parent, null);
        }
        name=convertView.findViewById(R.id.name);
        count=convertView.findViewById(R.id.count);
        id=convertView.findViewById(R.id.id);
        name.setText(parent_models.get(groupPosition).getName());
        count.setText(parent_models.get(groupPosition).getStatus());
        id.setText(parent_models.get(groupPosition).getId());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
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