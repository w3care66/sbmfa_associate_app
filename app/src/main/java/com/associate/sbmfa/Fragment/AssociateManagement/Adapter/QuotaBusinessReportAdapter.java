package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.QuotaBusiness_Report_Parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuotaBusinessReportAdapter  extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
     private HashMap<String, List<String>> _listDataChild;
    public ArrayList<QuotaBusiness_Report_Parent_model> QuotaBusiness_Report_Parent_models=new ArrayList<>();
    public QuotaBusinessReportAdapter(Context context, ArrayList<QuotaBusiness_Report_Parent_model> QuotaBusiness_Report_Parent_models) {
        if (context!=null) {
            this._context = context;
            this.QuotaBusiness_Report_Parent_models = QuotaBusiness_Report_Parent_models;
        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView qBusinessteam1,q_bussinessteam,q_bussinessteamamt1,qBusinesstamt1,qBusinessself1,qBusinesstself1,AchievedTadsarge,soNametv,roNametv,zoNametv,associateCode,associateName,associateCarder,quotabusinessTarget,achievedTarget,seniorCode,seniorName,seniorCarder,joiningDate,mobileNumber,status;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.quota_business_report_child, null);
        }
        qBusinessteam1=convertView.findViewById(R.id.qBusinessteam1);
        q_bussinessteam=convertView.findViewById(R.id.q_bussinessteam1);
        q_bussinessteamamt1=convertView.findViewById(R.id.q_bussinessteamamt1);
        qBusinesstamt1=convertView.findViewById(R.id.qBusinesstamt1);
        qBusinesstself1=convertView.findViewById(R.id.qBusinesstself1);
        qBusinessself1=convertView.findViewById(R.id.qBusinessself1);
        AchievedTadsarge=convertView.findViewById(R.id.AchievedTadsarge);
        soNametv=convertView.findViewById(R.id.soName);
        roNametv=convertView.findViewById(R.id.roName);
        zoNametv=convertView.findViewById(R.id.zoName);
        associateCode=convertView.findViewById(R.id.associateCode);
        associateName=convertView.findViewById(R.id.associateName);
        associateCarder=convertView.findViewById(R.id.associateCader);
        quotabusinessTarget=convertView.findViewById(R.id.quotaBusinessTarget);
        achievedTarget=convertView.findViewById(R.id.achievedTarget);
        seniorCode=convertView.findViewById(R.id.seniorCode);
        seniorName=convertView.findViewById(R.id.SeniorName);
        seniorCarder=convertView.findViewById(R.id.SeniorCarder);
        joiningDate=convertView.findViewById(R.id.JoiningDate);
        mobileNumber=convertView.findViewById(R.id.mobileNumber);
        status=convertView.findViewById(R.id.status);

        qBusinesstamt1.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAchieved_target_team_amt());
        q_bussinessteamamt1.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getQuota_business_target_team_amt());
        qBusinessteam1.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getQuota_business_target_team_percentage());
        q_bussinessteam.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAchieved_target_team_percentage());

        soNametv.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getSoNametv());
        roNametv.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getRoNametv());
        zoNametv.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getZoNametv());
        associateCode.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAssociateCode());
        associateName.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAssociateName());
        associateCarder.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAssociateCarder());
        quotabusinessTarget.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getQuotabusinessTarget()+ "  ₹");
        achievedTarget.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAchievedTarget()+ "  ₹");

        qBusinessself1.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getQuota_business_target_self_percentage()+ "  ₹");
        qBusinesstself1.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getAchieved_target_self_percentage()+ "  ₹");

        seniorCode.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getSeniorCode());
        seniorName.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getSeniorName());
        seniorCarder.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getSeniorCarder());
        joiningDate.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getJoiningDate());
        AchievedTadsarge.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getMobileNumber());
        status.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().get(childPosition).getStatus());
//        complete_qustions.setText("jaipur");
//        all_qustions.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getChild_models().get(childPosition).getQuations());
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.QuotaBusiness_Report_Parent_models.get(groupPosition).getQuotaBusiness_Report_Child_models().size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return this.QuotaBusiness_Report_Parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.QuotaBusiness_Report_Parent_models.size();
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
            convertView = infalInflater.inflate(R.layout.quota_business_report_parent, null);
        }
        name=convertView.findViewById(R.id.name);
        count=convertView.findViewById(R.id.count);
        des=convertView.findViewById(R.id.id);
        name.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getName());
        count.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getProgress());
        des.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getId());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(QuotaBusiness_Report_Parent_models.get(groupPosition).getChapter_count()+"  Chapter");
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
