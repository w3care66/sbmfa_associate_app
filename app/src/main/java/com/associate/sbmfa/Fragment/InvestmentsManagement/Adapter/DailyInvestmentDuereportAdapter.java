package com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.InvestmentDueReportParentModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class DailyInvestmentDuereportAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<InvestmentDueReportParentModel> investmentDueReportParentModels=new ArrayList<>();

    public DailyInvestmentDuereportAdapter(Context _context, ArrayList<InvestmentDueReportParentModel> investmentDueReportParentModels) {
        this._context = _context;
        this.investmentDueReportParentModels = investmentDueReportParentModels;
    }

    @Override
    public int getGroupCount() {
        return this.investmentDueReportParentModels.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition);

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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.assciate_collection_parent_item, null);
        }
        TextView name,count,des;
        ImageView up_dwon_icon;
        name=convertView.findViewById(R.id.name);
        des=convertView.findViewById(R.id.id);
        count=convertView.findViewById(R.id.count);

        name.setText(investmentDueReportParentModels.get(groupPosition).getName());
        des.setText(investmentDueReportParentModels.get(groupPosition).getId());
        count.setText(investmentDueReportParentModels.get(groupPosition).getProgress()) ;


        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.investment_daily_due_report_child, null);
        }
        TextView branch_name,branch_code,member_id,associate_code,associate_name,plan_name,tenure,deno_amount,
                due_emi,due_emi_amount;
        branch_name=convertView.findViewById(R.id.branch_name_text);
        branch_code=convertView.findViewById(R.id.branch_code_text);
        member_id=convertView.findViewById(R.id.accountNumber);
        associate_code=convertView.findViewById(R.id.associate_code_text);
        associate_name=convertView.findViewById(R.id.associate_name_text);
        plan_name=convertView.findViewById(R.id.plan_name_text);
        tenure=convertView.findViewById(R.id.tenure_text);
        deno_amount=convertView.findViewById(R.id.deno_amount_text);
        due_emi=convertView.findViewById(R.id.due_emi_text);
        due_emi_amount=convertView.findViewById(R.id.due_emi_amount_text);


        branch_name.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getBranch_name());
        branch_code.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getBranch_code());
        member_id.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getMember_id());
        associate_code.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getAssociate_code());
        associate_name.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getAssociate_name());
        plan_name.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getPlan_name());
        tenure.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getTenure());
        deno_amount.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getDeno_amount());
        due_emi.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getDue_emi());
        due_emi_amount.setText(investmentDueReportParentModels.get(groupPosition).getInvestmentDueReportChildModels().get(childPosition).getDue_emi_amount());





        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
