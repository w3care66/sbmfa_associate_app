package com.associate.sbmfa.Fragment.ReportManagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessReportParent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class AssociateBusinessReportDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<AssociateBusinessReportParent_model> parent_models=new ArrayList<>();
    public AssociateBusinessReportDetailsAdapter(Context context, ArrayList<AssociateBusinessReportParent_model> parent_models) {
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
        TextView
                textViewSoname,textViewRoname, textViewZoname,
                textViewtextCode,
                textViewName,textViewtextCarder,
                textViewDailyNI,textViewDailyNITotalDeno,
                textViewDailyRenewNoAC,textViewDailyRenewTotalAmt,
                textViewMonthlyNINoAC,textMonthlyNITotalDeno,
                textViewMonthlyRenewNoAC,textViewMonthlyRenewTotalAmt,
                textViewFDNINoAC,textViewFDNITotalDeno,textViewSSBNINoAC,
                textViewSSBNITotalDeno,textViewSSBDepositNoAC,textViewSSBDepositTotalAmt,
                textViewOtherMI,textViewOtherSTN,textViewNCC_M,textViewNCC,
                textViewTCC_M,
                textViewTCC,
                textViewLoanNoA,
                textViewLoanTotalAmt,
                textViewLoanRecoveryNoAC,
                textViewRecoveryTotal,
                textViewNewAssociateJoiningNo,
                textViewTotalAssociateJoiningNo,
                textViewNewMemberJoiningNo,
                textViewTotalMemberJoiningNo;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.asscoiate_business_report_child, null);
        }
        textViewSoname = convertView.findViewById(R.id.textBRName);
        textViewRoname = convertView.findViewById(R.id.ROName);
        textViewZoname = convertView.findViewById(R.id.ZOName);
        textViewtextCode = convertView.findViewById(R.id.textCode);
        textViewName = convertView.findViewById(R.id.textName);
        textViewtextCarder = convertView.findViewById(R.id.textCarder);
        textViewDailyNI = convertView.findViewById(R.id.DailyNI);
        textViewDailyNITotalDeno = convertView.findViewById(R.id.DailyNITotalDeno);
        textViewDailyRenewNoAC = convertView.findViewById(R.id.DailyRenewNoAC);
        textViewDailyRenewTotalAmt = convertView.findViewById(R.id.DailyRenewTotalAmt);
        textViewMonthlyNINoAC = convertView.findViewById(R.id.MonthlyNINoAC);
        textMonthlyNITotalDeno = convertView.findViewById(R.id.MonthlyNITotalDeno);
        textViewMonthlyRenewNoAC = convertView.findViewById(R.id.MonthlyRenewNoAC);
        textViewMonthlyRenewTotalAmt = convertView.findViewById(R.id.MonthlyRenewTotalAmt);
        textViewFDNINoAC = convertView.findViewById(R.id.FDNINoAC);
        textViewFDNITotalDeno = convertView.findViewById(R.id.FDNITotalDeno);
        textViewSSBNINoAC = convertView.findViewById(R.id.SSBNINoAC);
        textViewSSBNITotalDeno = convertView.findViewById(R.id.SSBNITotalDeno);
        textViewSSBDepositNoAC = convertView.findViewById(R.id.SSBDepositNoAC);
        textViewSSBDepositTotalAmt = convertView.findViewById(R.id.SSBDepositTotalAmt);
        textViewOtherMI = convertView.findViewById(R.id.OtherMI);
        textViewOtherSTN = convertView.findViewById(R.id.OtherSTN);
        textViewNCC_M = convertView.findViewById(R.id.NCC_M);
        textViewNCC = convertView.findViewById(R.id.NCC);
        textViewTCC_M = convertView.findViewById(R.id.TCC_M);
        textViewTCC = convertView.findViewById(R.id.TCC);
        textViewLoanNoA = convertView.findViewById(R.id.LoanNoA);
        textViewLoanTotalAmt = convertView.findViewById(R.id.LoanTotalAmt);
        textViewLoanRecoveryNoAC = convertView.findViewById(R.id.LoanRecoveryNoAC);
        textViewRecoveryTotal = convertView.findViewById(R.id.RecoveryTotal);
        textViewNewAssociateJoiningNo = convertView.findViewById(R.id.NewAssociateJoiningNo);
        textViewTotalAssociateJoiningNo = convertView.findViewById(R.id.TotalAssociateJoiningNo);
        textViewNewMemberJoiningNo = convertView.findViewById(R.id.NewMemberJoiningNo);
        textViewTotalMemberJoiningNo = convertView.findViewById(R.id.TotalMemberJoiningNo);
        textViewSoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSector_name());
        textViewRoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getRegion_name());
        textViewZoname.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getZone_name());
        textViewtextCode.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMember_id());

        textViewName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getName());
        textViewtextCarder.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCadre());
        textViewDailyNI.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getDaily_new_ac());
        textViewDailyNITotalDeno.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getDaily_deno_sum());
        textViewDailyRenewNoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getDaily_renew_ac());

        textViewDailyRenewTotalAmt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getDaily_renew());
        textViewMonthlyNINoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMonthly_new_ac());
        textMonthlyNITotalDeno.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMonthly_deno_sum());
        textViewMonthlyRenewNoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMonthly_renew_ac());
        textViewMonthlyRenewTotalAmt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMonthly_renew());


        textViewFDNINoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getFd_new_ac());
        textViewFDNITotalDeno.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getFd_deno_sum());


        textViewSSBNINoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSsb_new_ac());
        textViewSSBNITotalDeno.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSsb_deno_sum());

        textViewSSBDepositNoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSsb_renew_ac());
        textViewSSBDepositTotalAmt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSsb_renew());

        textViewOtherMI.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getOther_mt());
        textViewOtherSTN.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getOther_stn());


        textViewNCC_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getNi_m());
        textViewNCC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getNi());
        textViewTCC_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTcc_m());
        textViewTCC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTcc());
        textViewLoanNoA.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_ac());
        textViewLoanTotalAmt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_amount());

        textViewLoanRecoveryNoAC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_recovery_ac());

        textViewRecoveryTotal.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getLoan_recovery_amount());
        textViewNewAssociateJoiningNo.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getNew_associate());
        textViewTotalAssociateJoiningNo.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTotal_associate());
        textViewNewMemberJoiningNo.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getNew_member());
        textViewTotalMemberJoiningNo.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getTotal_member());

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
