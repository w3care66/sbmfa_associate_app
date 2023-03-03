package com.associate.sbmfa.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Model.ReportManagmentParent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;
public class ReportManagment_Adapter extends BaseExpandableListAdapter {
    TextView 	MemberRegisteredDate,BranchCode,BranchName,SectorName,MemberID,MemberName,AssociateID,Associatename,MemberAddress,AadharNumber,PenNumber;
    TextView Mid,Mname,Mcount;
    private Context _context;
    public ArrayList<ReportManagmentParent_model> reportManagmentParent_models=new ArrayList<>();
    public ReportManagment_Adapter(Context context, ArrayList<ReportManagmentParent_model> Member_Mangement_Parent_modelss) {
        if (context!=null) {
            this._context = context;
            this.reportManagmentParent_models = Member_Mangement_Parent_modelss;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.reportManagmentParent_models.get(groupPosition).getReportManagmentChildren().get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.associate_collection_details_item_child, null);
        }
       /* MemberRegisteredDate=convertView.findViewById(R.id.mRegDate);
        BranchCode=convertView.findViewById(R.id.branchCode);
        BranchName=convertView.findViewById(R.id.barnchName);
        SectorName=convertView.findViewById(R.id.sectorname);
        MemberID=convertView.findViewById(R.id.memberid);
        MemberName=convertView.findViewById(R.id.memberName);
        AssociateID=convertView.findViewById(R.id.associateId);
        Associatename=convertView.findViewById(R.id.associateName);
        MemberAddress=convertView.findViewById(R.id.memberAddress);
        AadharNumber=convertView.findViewById(R.id.adharNumber);
        PenNumber=convertView.findViewById(R.id.penNumber);*/
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.reportManagmentParent_models.get(groupPosition).getReportManagmentChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.reportManagmentParent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.reportManagmentParent_models.size();
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
            convertView = infalInflater.inflate(R.layout.assciate_collection_parent_item, null);
        }
        Mname=convertView.findViewById(R.id.name);
        Mid=convertView.findViewById(R.id.id);
        Mcount=convertView.findViewById(R.id.count);

       /* Mname.setText(associate_collection_parent_models.get(groupPosition).getName());
        Mid.setText(associate_collection_parent_models.get(groupPosition).getId());
        Mcount.setText(associate_collection_parent_models.get(groupPosition).getcount());*/

        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(Member_Mangement_Parent_modelss.get(groupPosition).getChapter_count()+"  Chapter");
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