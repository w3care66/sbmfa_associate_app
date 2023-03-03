package com.associate.sbmfa.Fragment.LoanManagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.LoanManagment.Model.Loan_Recovery_List_Parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class loanDetailsAdapter  extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    public ArrayList<Loan_Recovery_List_Parent_model> parent_models=new ArrayList<>();
    public loanDetailsAdapter(Context context, ArrayList<Loan_Recovery_List_Parent_model> parent_models) {
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
             convertView = infalInflater.inflate(R.layout.personal_loan_details_child, null);

       }

        LinearLayout LinearLayout1=convertView.findViewById(R.id.pers);
        LinearLayout LinearLayout2=convertView.findViewById(R.id.appli);
        LinearLayout LinearLayout3=convertView.findViewById(R.id.coappli);
        LinearLayout LinearLayout4=convertView.findViewById(R.id.guar);
        if(groupPosition == 0) {
            LinearLayout1.setVisibility(View.VISIBLE);
            LinearLayout2.setVisibility(View.GONE);
            LinearLayout3.setVisibility(View.GONE);
            LinearLayout4.setVisibility(View.GONE);
        }
        if(groupPosition == 1) {
            LinearLayout2.setVisibility(View.VISIBLE);
            LinearLayout1.setVisibility(View.GONE);
            LinearLayout3.setVisibility(View.GONE);
            LinearLayout4.setVisibility(View.GONE);
        }
        if(groupPosition == 2) {
            LinearLayout3.setVisibility(View.VISIBLE);
            LinearLayout1.setVisibility(View.GONE);
            LinearLayout2.setVisibility(View.GONE);
            LinearLayout4.setVisibility(View.GONE);
        }
        if(groupPosition == 3) {
            LinearLayout4.setVisibility(View.VISIBLE);
            LinearLayout3.setVisibility(View.GONE);
            LinearLayout1.setVisibility(View.GONE);
            LinearLayout2.setVisibility(View.GONE);
        }
//        all_qustions=convertView.findViewById(R.id.zoName);

//        topic_name.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getName());
//        complete_qustions.setText("jaipur");
//        all_qustions.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getQuations());
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
        des=convertView.findViewById(R.id.count);
        name.setText(parent_models.get(groupPosition).getName());
        count.setText(parent_models.get(groupPosition).getId());
//        des.setText(parent_models.get(groupPosition).getChapter_count());
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
