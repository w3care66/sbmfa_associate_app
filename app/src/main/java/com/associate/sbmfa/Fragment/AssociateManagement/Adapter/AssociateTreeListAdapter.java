package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.AssociateTreeViewParent;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

public class AssociateTreeListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    EventListener eventListener;

    public interface EventListener {
        void onEvent_Click(String id, String amount);
    }

    public ArrayList<AssociateTreeViewParent> parent_models = new ArrayList<>();
    public AssociateTreeListAdapter(Context context, ArrayList<AssociateTreeViewParent> parent_models, EventListener eventListener) {
        if (context != null) {
            this._context = context;
            this.parent_models = parent_models;
            this.eventListener = eventListener;
        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.parent_models.get(groupPosition).getAssociateTreeChildModels().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView AssociateCarder, SeniorCode, SeniorName, SeniorCarder, Status, branchName, associateCode;
        ConstraintLayout constraintLayoutAction;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_tree_child, null);
        }
        AssociateCarder = convertView.findViewById(R.id.AssociateCarder);
        SeniorCode = convertView.findViewById(R.id.SeniorCode);
        SeniorName = convertView.findViewById(R.id.SeniorName);
        SeniorCarder = convertView.findViewById(R.id.SeniorCarder);
        Status = convertView.findViewById(R.id.Status);
        branchName = convertView.findViewById(R.id.AssociateBranch);
        associateCode = convertView.findViewById(R.id.AssociateCode);
        constraintLayoutAction = convertView.findViewById(R.id.action);
        Log.e("checking...."," g-- "+groupPosition+" c---"+childPosition+"  "+parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getId());
        associateCode.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getAssociateCode());
        AssociateCarder.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getAssociateCarder());
        SeniorCode.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getSeniorCode());
        SeniorName.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getSeniorName());
        SeniorCarder.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getSeniorCarder());
        Status.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getStatus());
        branchName.setText(parent_models.get(groupPosition).
                getAssociateTreeChildModels().get(childPosition).getBranch_name());
        constraintLayoutAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("zxkls",""+parent_models.get(groupPosition).
                        getAssociateTreeChildModels().get(childPosition).getId());
                eventListener.onEvent_Click( parent_models.get(groupPosition).
                        getAssociateTreeChildModels().get(childPosition).getId(),"");
            }
        });
      return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.parent_models.get(groupPosition).getAssociateTreeChildModels().size();
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
        TextView name, count, des;
        ImageView up_dwon_icon;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.renewal_details_parent, null);
        }
        name = convertView.findViewById(R.id.name);
        count = convertView.findViewById(R.id.id);
        des = convertView.findViewById(R.id.count);
        name.setText(parent_models.get(groupPosition).getName());
        count.setText(parent_models.get(groupPosition).getId());
        des.setText(parent_models.get(groupPosition).getChapter_count());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
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
