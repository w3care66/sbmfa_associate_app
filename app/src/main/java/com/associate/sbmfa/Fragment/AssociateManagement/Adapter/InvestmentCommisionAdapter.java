package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.InvestmentCommisionParentModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvestmentCommisionAdapter extends BaseExpandableListAdapter {



    TextView AssociateName,AssociateCode,AssociateCader,BranchName,BranchCode,CommissionPayment;;
    TextView Mid,Mname,progress,Mcount;
    Button investmentCommisionDetails,loanCommisionDetais;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    String type ="0";
    public ArrayList<InvestmentCommisionParentModel> InvestmentCommisionParentModels=new ArrayList<>();
    public InvestmentCommisionAdapter(Context context, ArrayList<InvestmentCommisionParentModel> InvestmentCommisionParentModels,String type) {
        if (context!=null) {
            this._context = context;
            this.type = type;
            this.InvestmentCommisionParentModels = InvestmentCommisionParentModels;

        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.investment_commission_child, null);
        }




TextView datetv,investmentAmount,planname,commisionAmount,percentage,cardername,emino,emiamount,commissiontype,associateexist,paymentType,paymentDistribute;
       TextView plannameTV = convertView.findViewById(R.id.plannameTV);
        ConstraintLayout cmds=convertView.findViewById(R.id.cmds);
       TextView emi_no=convertView.findViewById(R.id.emino2);
        datetv=convertView.findViewById(R.id.mRegDate);
        investmentAmount=convertView.findViewById(R.id.investmentAmount);
        planname=convertView.findViewById(R.id.planname);
        commisionAmount=convertView.findViewById(R.id.commisionAmount);
        percentage=convertView.findViewById(R.id.percentage);
        cardername=convertView.findViewById(R.id.cardername);
        emino=convertView.findViewById(R.id.emino);
        emiamount=convertView.findViewById(R.id.emiamount);
        commissiontype=convertView.findViewById(R.id.commissiontype);
        associateexist=convertView.findViewById(R.id.associateexist);
        paymentType=convertView.findViewById(R.id.paymentType);
        paymentDistribute=convertView.findViewById(R.id.paymentDistribute);
        plannameTV.setText(type == "1" ? "Loan Type" : "Plan Name");
        if (type.equals("0")){
            cmds.setVisibility(View.VISIBLE);
            emi_no.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getEmiNo());
        }else{
            cmds.setVisibility(View.GONE);

            emi_no.setText("");

        }
        datetv.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getDate());
        investmentAmount.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getInvestmentAccount());
        planname.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getPlanName());
        commisionAmount.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getCoommisionAmount()+" ₹");
        percentage.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getPercentage()+" %");
        cardername.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getCarderName());
        emino.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getEmiNo());
        emiamount.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getEmiamount()+" ₹");
        commissiontype.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getCommisionType());
        associateexist.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getAssociateExists());
        paymentType.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getPaymentType());
        paymentDistribute.setText(InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().get(childPosition).getPaymentDistribute());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.InvestmentCommisionParentModels.get(groupPosition).get_investment_commision_child_model().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.InvestmentCommisionParentModels.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.InvestmentCommisionParentModels.size();
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
            convertView = infalInflater.inflate(R.layout.investment_commission_parent, null);
        }
        Mname=convertView.findViewById(R.id.name);
        Mid=convertView.findViewById(R.id.id);
        Mcount=convertView.findViewById(R.id.count);
        Mname.setText(InvestmentCommisionParentModels.get(groupPosition).getInvestmentAccount());
        Mid.setText(InvestmentCommisionParentModels.get(groupPosition).getId());
        Mcount.setText(InvestmentCommisionParentModels.get(groupPosition).getDate());

        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(InvestmentCommisionParentModels.get(groupPosition).getChapter_count()+"  Chapter");
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