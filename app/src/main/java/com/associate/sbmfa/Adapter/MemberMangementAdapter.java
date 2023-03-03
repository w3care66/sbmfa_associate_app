package com.associate.sbmfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateCommissionDetailsAdapter;
import com.associate.sbmfa.Model.Member_Mangement_Parent_models;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberMangementAdapter extends BaseExpandableListAdapter {
    TextView 	MemberRegisteredDate,BranchCode,BranchName,SectorName,MemberID,MemberName,AssociateID,Associatename,MemberAddress,AadharNumber,PenNumber;
    TextView Mid,Mname,progress,Mcount;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;


    AssociateCommissionDetailsAdapter.EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String lagerid, String memberid, String type);
    }

    public ArrayList<Member_Mangement_Parent_models> Member_Mangement_Parent_modelss=new ArrayList<>();
    public    MemberMangementAdapter(Context context, ArrayList<Member_Mangement_Parent_models> Member_Mangement_Parent_modelss, AssociateCommissionDetailsAdapter.EventListener eventListener) {
        if (context!=null) {
            this._context = context;
            this.Member_Mangement_Parent_modelss = Member_Mangement_Parent_modelss;
            this.eventListener=eventListener;
        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView topic_name,complete_qustions,all_qustions;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.member_magement_item_child, null);
        }
        ImageView imageView;
        MemberRegisteredDate=convertView.findViewById(R.id.mRegDate);
        imageView=convertView.findViewById(R.id.imageView22);
        BranchCode=convertView.findViewById(R.id.branchCode);
        BranchName=convertView.findViewById(R.id.barnchName);
        SectorName=convertView.findViewById(R.id.sectorname);
        MemberID=convertView.findViewById(R.id.memberid);
        MemberName=convertView.findViewById(R.id.memberName);
        AssociateID=convertView.findViewById(R.id.associateId);
        Associatename=convertView.findViewById(R.id.associateName);
        MemberAddress=convertView.findViewById(R.id.memberAddress);
        AadharNumber=convertView.findViewById(R.id.adharNumber);
        PenNumber=convertView.findViewById(R.id.penNumber);

        MemberRegisteredDate.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getMemberRegisteredDate());
        BranchCode.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getBranchCode());
        BranchName.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getBranchName());
        SectorName.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getSectorName());
        MemberID.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getMemberID());
        MemberName.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getMemberName());
        AssociateID.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getAssociateID());
        Associatename.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getAssociatename());
        MemberAddress.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getMemberAddress());
        AadharNumber.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getAadharNumber());
        PenNumber.setText(Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getPenNumber());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(_context, "hi", Toast.LENGTH_SHORT).show();
                eventListener.onEvent_View(
                        Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().get(childPosition).getId(),
                        "",""
                );
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.Member_Mangement_Parent_modelss.get(groupPosition).getMember_Mangemanet_Child_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.Member_Mangement_Parent_modelss.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.Member_Mangement_Parent_modelss.size();
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
            convertView = infalInflater.inflate(R.layout.member_mangement_tab_item, null);
        }
        Mname=convertView.findViewById(R.id.name);
        Mid=convertView.findViewById(R.id.id);
        Mcount=convertView.findViewById(R.id.count);
        Mname.setText(Member_Mangement_Parent_modelss.get(groupPosition).getName());
        Mid.setText(Member_Mangement_Parent_modelss.get(groupPosition).getId());
        Mcount.setText(Member_Mangement_Parent_modelss.get(groupPosition).getProgress());
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