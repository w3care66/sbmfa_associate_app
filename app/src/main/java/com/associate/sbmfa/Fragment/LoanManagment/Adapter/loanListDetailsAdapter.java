package com.associate.sbmfa.Fragment.LoanManagment.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.LoanManagment.Model.LoneListParent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;

public class loanListDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    public ArrayList<LoneListParent_model> parent_models=new ArrayList<>();
    EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String id, String memberid, String type);
    }
    public loanListDetailsAdapter(Context context, ArrayList<LoneListParent_model> parent_models,EventListener eventListener) {
        if (context!=null) {
            this._context = context;
            this.parent_models = parent_models;
            this.eventListener = eventListener;
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
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView payment_mode1,out_standing_amount,tender,textViewBRname,textViewBrCode,
                textViewSoname,textViewRoname,
                textViewZoname,textViewMemberId,
                textViewMemberName,textViewRecovery,
                textViewtextCode,
                textViewName,
                textViewtLoanType,
                textViewtextAmount,
                textViewStatus,
                textViewApprovedDate,
                textApplicationDate,totalpayment1;
        ConstraintLayout constraintLayoutView;
        ImageView imageView;
        if (convertView == null) {
             LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = infalInflater.inflate(R.layout.loan_list_details_child, null);
       }
        TextView transfer_amoutn,file,loan_amoutn;
        transfer_amoutn = convertView.findViewById(R.id.texttranxm);
        file = convertView.findViewById(R.id.textfile);
/*
        loan_amoutn = convertView.findViewById(R.id.textloanamoutn);
*/
        totalpayment1= convertView.findViewById(R.id.totalpayment1);
        constraintLayoutView = convertView.findViewById(R.id.action);
        payment_mode1=convertView.findViewById(R.id.payment_mode1);
        out_standing_amount=convertView.findViewById(R.id.out_standing_amount);
        tender=convertView.findViewById(R.id.tender);
        textViewBRname = convertView.findViewById(R.id.textBRName);
        textViewBrCode = convertView.findViewById(R.id.BRcode);
        textViewSoname = convertView.findViewById(R.id.SOName);
        textViewRoname = convertView.findViewById(R.id.ROName);
        textViewZoname = convertView.findViewById(R.id.ZOName);
        textViewMemberId = convertView.findViewById(R.id.MemberId);
        textViewMemberName = convertView.findViewById(R.id.MemberName);
        textViewRecovery = convertView.findViewById(R.id.dateRecovery);
        textViewtextCode = convertView.findViewById(R.id.textCode);
        textViewName = convertView.findViewById(R.id.textTV1Name);
        textViewtLoanType = convertView.findViewById(R.id.textLoanType);
        textViewtextAmount = convertView.findViewById(R.id.textAmount);
        textViewStatus = convertView.findViewById(R.id.textStatus);
        imageView = convertView.findViewById(R.id.imageView22);
        textViewApprovedDate = convertView.findViewById(R.id.textTVApprovedDate);
        textApplicationDate = convertView.findViewById(R.id.textApplicationDate);
        totalpayment1.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTotal_payment()+" ₹");
        payment_mode1.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getPayment_mode());
        out_standing_amount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getOut_standing_amount()+" ₹ ");
        tender.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTenure());
        textViewBRname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getBr_name());
        textViewBrCode.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getBr_code());
        textViewSoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSo_name());
        textViewRoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getRo_name());
        textViewZoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getZo_name());
        textViewMemberId.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMember_id());
        textViewMemberName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMember_name());
        textViewRecovery.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLast_recovery_date());
        textViewtextCode.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getAssociate_code());
        textViewName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getAssociate_name());
        textViewtLoanType.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_type());
        textViewtextAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getAmount()+" ₹");
        textViewStatus.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getStatus());
        textViewApprovedDate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getApproved_date());
        textApplicationDate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getApplication_date());
        transfer_amoutn.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTransfer_amount()+" ₹");
        if (parent_models.get(groupPosition).getChild_models().get(childPosition).getFile_charge()!=null){
            file.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getFile_charge()+" ₹");

        }else{
            file.setText(" ");

        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent_View(parent_models.get(groupPosition).getChild_models().get(childPosition).getId(),"",parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_type());

            }
        });
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
