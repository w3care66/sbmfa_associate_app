package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_Commission_details_Parent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AssociateCommissionDetailsAdapter extends BaseExpandableListAdapter {

    EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String lagerid, String memberid, String type);
    }

    TextView pannotv1,totalamounttv1,createdDate1,SSBac1,status1,fuelamount1,totalcollection1,payableamount1,tDSamount1,AssociateName,AssociateCode,AssociateCader,BranchName,BranchCode,CommissionPayment;;
    TextView Mid,Mname,progress,Mcount;
    ImageView investmentCommisionDetails,loanCommisionDetais;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    public ArrayList<Associate_Commission_details_Parent_model> Associate_Commission_details_Parent_models=new ArrayList<>();
    public AssociateCommissionDetailsAdapter(Context context, ArrayList<Associate_Commission_details_Parent_model> Associate_Commission_details_Parent_models, EventListener eventListener) {
        if (context!=null) {
            this._context = context;
            this.Associate_Commission_details_Parent_models = Associate_Commission_details_Parent_models;
            this.eventListener=eventListener;
        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.associate_commission_details_tab_item_child, null);
        }
        AssociateName=convertView.findViewById(R.id.associateName);
        BranchCode=convertView.findViewById(R.id.branchCode);
        BranchName=convertView.findViewById(R.id.branchName);
        AssociateCode=convertView.findViewById(R.id.associateCode);
        AssociateCader=convertView.findViewById(R.id.associateCader);
        CommissionPayment=convertView.findViewById(R.id.commissionpayement);
        investmentCommisionDetails=convertView.findViewById(R.id.imageView27);
        loanCommisionDetais=convertView.findViewById(R.id.imageView28);
        pannotv1=convertView.findViewById(R.id.pannotv1);
        totalamounttv1=convertView.findViewById(R.id.totalamounttv1);
        createdDate1=convertView.findViewById(R.id.createdDate1);
        SSBac1=convertView.findViewById(R.id.SSBac1);
        status1=convertView.findViewById(R.id.status1);
        fuelamount1=convertView.findViewById(R.id.fuelamount1);
        totalcollection1=convertView.findViewById(R.id.totalcollection1);
        payableamount1=convertView.findViewById(R.id.payableamount1);
        tDSamount1=convertView.findViewById(R.id.tDSamount1);

        tDSamount1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getTDSAmount()+" ₹");
        payableamount1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getFinalPayableAmount()+" ₹");
        totalcollection1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getTotalCollection());
        fuelamount1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getFuelAmount()+" ₹");
        status1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getStatus());
        SSBac1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getSSBAccountNo());
        createdDate1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getCreatedDate());
        pannotv1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getPANNo());
        totalamounttv1.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getTotalAmount()+" ₹");


        investmentCommisionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent_View(
                        Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getLagderId(),
                        Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getMemberId(),
                        "Investment"
                );

            }
        });
        loanCommisionDetais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent_View(
                        Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getLagderId(),
                        Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getMemberId(),
                        "Loan"
                );

            }
        });

        AssociateName.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getAssociateName());
        BranchCode.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getBranchCode());
        BranchName.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getBranchName());
        AssociateCode.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getAssociateCode());
        AssociateCader.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getAssociateCader());
        CommissionPayment.setText(Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().get(childPosition).getCommissionPayment()+" ₹");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.Associate_Commission_details_Parent_models.get(groupPosition).getAssociate_commission_details_child_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.Associate_Commission_details_Parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.Associate_Commission_details_Parent_models.size();
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
            convertView = infalInflater.inflate(R.layout.associate_commission_parent, null);
        }
        Mname=convertView.findViewById(R.id.name);
        Mid=convertView.findViewById(R.id.id);
        Mcount=convertView.findViewById(R.id.count);
        Mname.setText(Associate_Commission_details_Parent_models.get(groupPosition).getName());
        Mid.setText(Associate_Commission_details_Parent_models.get(groupPosition).getId());
        Mcount.setText(Associate_Commission_details_Parent_models.get(groupPosition).getProgress());

        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(Associate_Commission_details_Parent_models.get(groupPosition).getChapter_count()+"  Chapter");
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