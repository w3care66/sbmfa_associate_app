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

public class LoanRecoveryAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    public ArrayList<Loan_Recovery_List_Parent_model> LoneListParent_models=new ArrayList<>();
    TextView id ,
            account_number,
            applicant_id ,
            br_name,br_code,so_name,
            ro_name,zo_name,
            member_id,member_name,
            last_recovery_date,associate_code,
            associate_name,loan_type,amount,
            status,approved_date,application_date,

            group_loan_id,loan_amount,filecharge,filechargepaymentmode,outstandingAmount,totalamount,tenure;
    LoanRecoveryAdapter.EventListener eventListener;
    LinearLayout guar;
    String cate_id;
    public interface EventListener {
        void onEvent_View(String lagerid, String memberid, String type);
    }

    
    public LoanRecoveryAdapter(Context context, ArrayList<Loan_Recovery_List_Parent_model> LoneListParent_models, LoanRecoveryAdapter.EventListener eventListener,String cate_id) {
        if (context!=null) {
            this._context = context;
            this.LoneListParent_models = LoneListParent_models;
            this.eventListener=eventListener;
            this.cate_id = cate_id;
        }
        //this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.LoneListParent_models.get(groupPosition).getChild_models().get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.loan_recovery_details_child, null);
        }

                 ImageView imageView;
                TextView textViewTransferAmount;
              //  id=convertView.findViewById(R.id.soName);
                account_number=convertView.findViewById(R.id.gosectorname);
                textViewTransferAmount=convertView.findViewById(R.id.gopenNumbe11);
                imageView=convertView.findViewById(R.id.imageView22);
//                br_name=convertView.findViewById(R.id.branchName);
                guar=convertView.findViewById(R.id.guar);
                so_name=convertView.findViewById(R.id.gomRegDate);
                ro_name=convertView.findViewById(R.id.gobranchCode);
                zo_name=convertView.findViewById(R.id.gobarnchName);
                member_id=convertView.findViewById(R.id.gomemberName);
                member_name=convertView.findViewById(R.id.gomemberid);
                last_recovery_date=convertView.findViewById(R.id.goAchievedTarget);
                associate_code=convertView.findViewById(R.id.goQuotaBusinessTargetv);
                associate_name=convertView.findViewById(R.id.goAchievedTarge);
                loan_type=convertView.findViewById(R.id.goassociateId);
                loan_amount=convertView.findViewById(R.id.gopenNumber);
                filecharge=convertView.findViewById(R.id.goSeniorName);
                filechargepaymentmode=convertView.findViewById(R.id.goSeniorCarder);
                outstandingAmount=convertView.findViewById(R.id.goQuotaBusinessTarget);
                 associate_code=convertView.findViewById(R.id.goQuotaBusinessTargetv);
                totalamount=convertView.findViewById(R.id.goJoiningDate);
                tenure=convertView.findViewById(R.id.goadharNumber);
                status=convertView.findViewById(R.id.goAchievedTdsdaadsargec);
                approved_date=convertView.findViewById(R.id.goAchievedTadsarge);
                application_date=convertView.findViewById(R.id.goAchievedTdsdaadsarge);
//                group_loan_id=convertView.findViewById(R.id.soName);

        account_number.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getAccount_number());
//        br_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getBr_name());
//        br_code.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getBr_code());
        so_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getSo_name());
        ro_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getRo_name());
        zo_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getZo_name());
        member_id.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getMember_id());
        member_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getMember_name());
        last_recovery_date.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getLast_recovery_date());
        associate_code.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getAssociate_code());
        //associate_name.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getAssociate_name());
        loan_type.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_type());
        loan_amount.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_amount());
        filecharge.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getFilecharge());
        filechargepaymentmode.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getFilechargepaymentmode());
        outstandingAmount.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getOutstandingAmount());
        associate_code.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getAssociate_code());
        totalamount.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getTotalamount());
        tenure.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getTenure());
        status.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getStatus());
        approved_date.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getApproved_date());
        application_date.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getApplication_date());
        textViewTransferAmount.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getTransfer_amount()+"");
//        group_loan_id.setText(LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getGroup_loan_id());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Toast.makeText(_context, "hi "+LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getGroup_loan_id(), Toast.LENGTH_SHORT).show();
                eventListener.onEvent_View(
                        LoneListParent_models.get(groupPosition).getChild_models().get(childPosition).getGroup_loan_id(),
                        "",
                        cate_id
                );
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.LoneListParent_models.get(groupPosition).getChild_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.LoneListParent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.LoneListParent_models.size();
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

       TextView id=convertView.findViewById(R.id.id);
        name=convertView.findViewById(R.id.name);
        count=convertView.findViewById(R.id.id);
        des=convertView.findViewById(R.id.count);

        name.setText(LoneListParent_models.get(groupPosition).getName());
        id.setText(LoneListParent_models.get(groupPosition).getId());

        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        count.setText(LoneListParent_models.get(groupPosition).getId());
        name.setText("Name:- "+LoneListParent_models.get(groupPosition).getName());
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
