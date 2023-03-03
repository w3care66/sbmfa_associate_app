package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model.LoanLedger_parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class LoanLedgerList_Adapter extends BaseExpandableListAdapter {

    private Context _context;
    public ArrayList<LoanLedger_parent_model> ledger_parent_models=new ArrayList<>();
    public LoanLedgerList_Adapter(Context context, ArrayList<LoanLedger_parent_model> Member_Mangement_Parent_modelss) {
        if (context!=null) {
            this._context = context;
            this.ledger_parent_models = Member_Mangement_Parent_modelss;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.ledger_parent_models.get(groupPosition).getLoanLedger_child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView jv_Amount1,paymentmode,description,penalty,deposit,roi_amount,principal_amount,opening_balance;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.loan_ledger_item_child, null);
        }
        jv_Amount1= convertView.findViewById(R.id.jv_Amount1);
        paymentmode = convertView.findViewById(R.id.paymentmode);
        description = convertView.findViewById(R.id.description);
        penalty = convertView.findViewById(R.id.penalty);
        deposit = convertView.findViewById(R.id.deposit);
        roi_amount = convertView.findViewById(R.id.roi_amount);
        principal_amount = convertView.findViewById(R.id.principal_amount);
        opening_balance = convertView.findViewById(R.id.opening_balance);
        jv_Amount1.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getJv_amount()+" ₹");
        paymentmode.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getPaymentMode());
        description.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getDescription());
        penalty.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getPenalty());
        if (ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getDeposit().equals("")){
            deposit.setText(ledger_parent_models.get(groupPosition).
                    getLoanLedger_child_models().get(childPosition).getDeposit());
        }
        else{
            deposit.setText(ledger_parent_models.get(groupPosition).
                    getLoanLedger_child_models().get(childPosition).getDeposit()+" ₹");
        }
        roi_amount.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getRoi_amount()+" ₹");
        principal_amount.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getPrincipal_amount()+" ₹");
        opening_balance.setText(ledger_parent_models.get(groupPosition).
                getLoanLedger_child_models().get(childPosition).getOpening_balance()+" ₹");
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.ledger_parent_models.get(groupPosition).getLoanLedger_child_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.ledger_parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ledger_parent_models.size();
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
            convertView = infalInflater.inflate(R.layout.loan_ledger_parent_item, null);
        }
        name=convertView.findViewById(R.id.name);
        id=convertView.findViewById(R.id.id);
        count=convertView.findViewById(R.id.count);
        name.setText(ledger_parent_models.get(groupPosition).getName());
        id.setText(ledger_parent_models.get(groupPosition).getId());
        count.setText(ledger_parent_models.get(groupPosition).getProgress());

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