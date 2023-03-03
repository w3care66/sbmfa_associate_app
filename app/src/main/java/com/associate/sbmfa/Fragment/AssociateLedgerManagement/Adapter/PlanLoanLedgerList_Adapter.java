package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model.Plan_Ledger_parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PlanLoanLedgerList_Adapter extends BaseExpandableListAdapter {
    TextView Mid,Mname,Mcount;
    private Context _context;
    View view;
    EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String id, String ssid);
    }
    public ArrayList<Plan_Ledger_parent_model> ledger_parent_models=new ArrayList<>();
    public PlanLoanLedgerList_Adapter(Context context, ArrayList<Plan_Ledger_parent_model> Member_Mangement_Parent_modelss , EventListener eventListener) {
        if (context!=null) {
            this._context = context;
            this.ledger_parent_models = Member_Mangement_Parent_modelss;
            this.view = view;
            this.eventListener =eventListener;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView description,reference_no,withdrawal,deposit,opening_balance;
        ImageView imageView;
        ConstraintLayout constraintLayoutView;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.plan_ledger_item_child, null);
        }
        description=convertView.findViewById(R.id.description);
        reference_no=convertView.findViewById(R.id.reference_no);
        withdrawal=convertView.findViewById(R.id.withdrawal);
        deposit=convertView.findViewById(R.id.deposit);
        opening_balance=convertView.findViewById(R.id.opening_balance);
        constraintLayoutView=convertView.findViewById(R.id.action);
        description.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getDescription());
        reference_no.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getReference_no());
        if (ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getWithdrawal().equals(""))
        {
            withdrawal.setVisibility(View.GONE);
        }
        else {
            withdrawal.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getWithdrawal()+" ₹");
        }
        if (ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getDeposit().equals("")){
            deposit.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getDeposit());

        }else{
            deposit.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getDeposit()+" ₹");

        }
        opening_balance.setText(ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getOpening_balance()+" ₹");

        constraintLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent_View(
                        ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getTranid(),
                        ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().get(childPosition).getIs_ssb()
                        );

            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.ledger_parent_models.get(groupPosition).getPlan_ledger_child_models().size();
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
        TextView id,name,cont;
        ImageView up_dwon_icon;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.plan_ledger_parent_item, null);
        }
        id=convertView.findViewById(R.id.id);
        name=convertView.findViewById(R.id.name);
        cont=convertView.findViewById(R.id.count);
        name.setText(ledger_parent_models.get(groupPosition).getName());
        id.setText(ledger_parent_models.get(groupPosition).getId());
        cont.setText(ledger_parent_models.get(groupPosition).getProgress());
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