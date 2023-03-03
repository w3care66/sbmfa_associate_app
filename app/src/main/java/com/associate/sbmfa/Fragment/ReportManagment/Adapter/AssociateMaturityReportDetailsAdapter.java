package com.associate.sbmfa.Fragment.ReportManagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateMaturityReportParent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class AssociateMaturityReportDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<AssociateMaturityReportParent_model> parent_models=new ArrayList<>();
    public AssociateMaturityReportDetailsAdapter(Context context, ArrayList<AssociateMaturityReportParent_model> parent_models) {
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
        TextView payment_date1,textmemberId,plan_name, TenureName,
                textCode,
                textName,textdeno,
                Maturity,MaturityAmount,
                MaturityDate,OpeningDate,
                DueAmount,TotalAmount,bank_account_number1,bank_name1,SSB_account_no1,
                rTGS_charge1,cheque_no1,payment_mode1,final_payable_amount1,tDS_amount1,maturity_payable_mount1,
                deposita_mount1,account_no1,intrest1;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.asscoiate_maturity_report_child, null);
        }
        payment_date1= convertView.findViewById(R.id.payment_date1);
        intrest1= convertView.findViewById(R.id.intrest1);
        textmemberId = convertView.findViewById(R.id.textmemberId);
        plan_name = convertView.findViewById(R.id.plan_name);
        TenureName = convertView.findViewById(R.id.TenureName);
        textCode = convertView.findViewById(R.id.textCode);
        textName = convertView.findViewById(R.id.textName);
        textdeno = convertView.findViewById(R.id.textdeno);
        Maturity = convertView.findViewById(R.id.Maturity);
        MaturityAmount = convertView.findViewById(R.id.MaturityAmount);
        MaturityDate = convertView.findViewById(R.id.MaturityDate);
        OpeningDate = convertView.findViewById(R.id.OpeningDate);
//        DueAmount = convertView.findViewById(R.id.DueAmount);
        TotalAmount = convertView.findViewById(R.id.TotalAmount);
        bank_account_number1=convertView.findViewById(R.id.bank_account_number1);
        bank_name1=convertView.findViewById(R.id.bank_name1);
        SSB_account_no1=convertView.findViewById(R.id.SSB_account_no1);
        rTGS_charge1=convertView.findViewById(R.id.rTGS_charge1);
        cheque_no1=convertView.findViewById(R.id.cheque_no1);
        maturity_payable_mount1=convertView.findViewById(R.id.maturity_payable_mount1);
        tDS_amount1=convertView.findViewById(R.id.tDS_amount1);
//        final_payable_amount1=convertView.findViewById(R.id.final_payable_amount1);
        payment_mode1=convertView.findViewById(R.id.payment_mode1);
        deposita_mount1=convertView.findViewById(R.id.deposita_mount1);
        account_no1=convertView.findViewById(R.id.account_no1);

        payment_date1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getPayment_date());
        intrest1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getInterest());
        account_no1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getAccount_no());
        deposita_mount1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getDeposit_amount()+" ₹");
        payment_mode1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getPayment_mode());
//        final_payable_amount1.setText(parent_models.get(groupPosition).
//                getChild_models().get(childPosition).getFinal_amount()+" ₹");
        tDS_amount1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getTds_amount());
        maturity_payable_mount1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getMaturity_payable_amount()+" ₹");
        cheque_no1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getCheque_no());
        rTGS_charge1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getRtgs_chrg());
        SSB_account_no1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getSsb_ac());
        bank_name1.setText(parent_models.get(groupPosition).
                        getChild_models().get(childPosition).getBank_name());
        bank_account_number1.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getBank_ac());
        textmemberId.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getMember_id());
        plan_name.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getPlan_name());
        TenureName.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getTenure());
        textCode.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getAssociate_code());
        textName.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getAssociate_name());
        textdeno.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getDeno());
        Maturity.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getMaturity_type());
        MaturityAmount.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getMaturity_amount() + "₹");
        MaturityDate.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getMaturity_date());
         OpeningDate.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getOpening_date());
//         DueAmount.setText(parent_models.get(groupPosition).
//                getChild_models().get(childPosition).getDue_amount()+ "₹");
        TotalAmount.setText(parent_models.get(groupPosition).
                getChild_models().get(childPosition).getTotal_amount()+ "₹");
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
        TextView textViewId,textViewApplicationId,textViewAccount;
        ImageView up_dwon_icon;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.practice_tab_item, null);
        }

        textViewId=convertView.findViewById(R.id.id);
        textViewApplicationId=convertView.findViewById(R.id.name);
        textViewAccount=convertView.findViewById(R.id.des);
        textViewId.setText(parent_models.get(groupPosition).getId());
        textViewApplicationId.setText(parent_models.get(groupPosition).getName());
        textViewAccount.setText(parent_models.get(groupPosition).getProgress());
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
