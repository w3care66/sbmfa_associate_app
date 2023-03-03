package com.associate.sbmfa.Fragment.ReportManagment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessSummaryReportParent;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class AssociateBusinessSummaryReportDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<AssociateBusinessSummaryReportParent> parent_models=new ArrayList<>();
    public AssociateBusinessSummaryReportDetailsAdapter(Context context, ArrayList<AssociateBusinessSummaryReportParent> parent_models) {
        if (context!=null) {
            this._context = context;
            this.parent_models = parent_models;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosititon);
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

        TextView mb_new_ac,mb_deno_sum,mb_renew_ac,mb_renew;
        TextView frd_new_ac,frd_deno_sum,frd_renew_ac,frd_renew,ffd_new,ffd_demo;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.asscoiate_business_summary_report_child, null);
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
        textViewSSBDepositNoAC = convertView.findViewById(R.id.SSBDepositTotalAmt);
        textViewSSBDepositTotalAmt = convertView.findViewById(R.id.SSBDepositNoAC);
        textViewOtherMI = convertView.findViewById(R.id.OtherMI);
        textViewOtherSTN = convertView.findViewById(R.id.OtherSTN);
        textViewNCC_M = convertView.findViewById(R.id.NCC_M);
        textViewNCC = convertView.findViewById(R.id.NCC);
        textViewTCC_M = convertView.findViewById(R.id.TCC_M);
        textViewTCC = convertView.findViewById(R.id.TCC);
        textViewLoanNoA = convertView.findViewById(R.id.LoanNoA);
        textViewLoanTotalAmt = convertView.findViewById(R.id.LoanTotalAmt);
        textViewLoanNoA.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_ac());
        textViewLoanTotalAmt.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_amount());
        textViewLoanRecoveryNoAC = convertView.findViewById(R.id.LoanRecoveryNoAC);
        textViewRecoveryTotal = convertView.findViewById(R.id.RecoveryTotal);
        textViewNewAssociateJoiningNo = convertView.findViewById(R.id.NewAssociateJoiningNo);
        textViewTotalAssociateJoiningNo = convertView.findViewById(R.id.TotalAssociateJoiningNo);
        textViewNewMemberJoiningNo = convertView.findViewById(R.id.NewMemberJoiningNo);
        textViewTotalMemberJoiningNo = convertView.findViewById(R.id.TotalMemberJoiningNo);


        mb_new_ac = convertView.findViewById(R.id.mb_new_ac);
        mb_deno_sum = convertView.findViewById(R.id.mb_deno_sum);
        mb_renew_ac = convertView.findViewById(R.id.mb_renew_ac);
        mb_renew = convertView.findViewById(R.id.mb_renew);


        frd_new_ac = convertView.findViewById(R.id.frd_new_ac);
        frd_deno_sum = convertView.findViewById(R.id.ffd_deno_sum);
        frd_renew_ac = convertView.findViewById(R.id.frd_renew_ac);
        frd_renew = convertView.findViewById(R.id.frd_deno_sum);

        ffd_new = convertView.findViewById(R.id.ffd_new_ac);
        ffd_new.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFfd_new_ac());
        ffd_demo = convertView.findViewById(R.id.ffd_deno_sum1);
        ffd_demo.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFfd_deno_sum());

        mb_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMb_new_ac());
        mb_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMb_deno_sum());
        mb_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMb_renew_ac());
        mb_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMb_renew());


        frd_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFrd_new_ac());
        frd_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFrd_deno_sum());
        frd_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFrd_renew());
        frd_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFrd_renew());

        frd_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFfd_new_ac());
        frd_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getFfd_deno_sum());

        TextView jeevan_new_ac,jeevan_deno_sum,jeevan_renew_ac,jeevan_renew;
        jeevan_new_ac = convertView.findViewById(R.id.jeevan_new_ac);
        jeevan_deno_sum = convertView.findViewById(R.id.jeevan_deno_sum);
        jeevan_renew_ac = convertView.findViewById(R.id.jeevan_renew_ac);
        jeevan_renew = convertView.findViewById(R.id.jeevan_renew);
        jeevan_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getJeevan_new_ac());
        jeevan_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getJeevan_deno_sum());
        jeevan_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getJeevan_renew_ac());
        jeevan_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getJeevan_renew());


        TextView mi_new_ac,TV1mi_deno_sum,mi_renew_ac,mi_renew;
        mi_new_ac = convertView.findViewById(R.id.mi_new_ac);
        TV1mi_deno_sum = convertView.findViewById(R.id.mi_deno_sum);
        mi_renew_ac = convertView.findViewById(R.id.mi_renew_ac);
        mi_renew = convertView.findViewById(R.id.mi_renew);
        mi_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMi_new_ac());
        TV1mi_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMi_deno_sum());
        mi_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMi_renew_ac());
        mi_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getMi_renew());

        TextView rd_new_ac,rd_deno_sum,rd_renew_ac,rd_renew;
        rd_new_ac = convertView.findViewById(R.id.rd_new_ac);
        rd_deno_sum = convertView.findViewById(R.id.rd_deno_sum);
        rd_renew_ac = convertView.findViewById(R.id.rd_renew_ac);
        rd_renew = convertView.findViewById(R.id.rd_renew);
        rd_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getRd_new_ac());
        rd_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getRd_deno_sum());
        rd_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getRd_renew_ac());
        rd_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getRd_renew());


        TextView bhavhishya_new_ac,bhavhishya_deno_sum,bhavhishya_renew_ac,bhavhishya_renew;
        bhavhishya_new_ac = convertView.findViewById(R.id.bhavhishya_new_ac);
        bhavhishya_deno_sum = convertView.findViewById(R.id.bhavhishya_deno_sum);
        bhavhishya_renew_ac = convertView.findViewById(R.id.bhavhishya_renew_ac);
        bhavhishya_renew = convertView.findViewById(R.id.bhavhishya_renew);

        bhavhishya_new_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getBhavhishya_new_ac());
        bhavhishya_deno_sum.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getBhavhishya_deno_sum());
        bhavhishya_renew_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getBhavhishya_renew_ac());
        bhavhishya_renew.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getBhavhishya_renew());


        TextView total_ni_ac,total_ni_amount;
        total_ni_ac = convertView.findViewById(R.id.total_ni_ac);
        total_ni_amount = convertView.findViewById(R.id.total_ni_amount);
        total_ni_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_ni_ac());
        total_ni_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_ni_amount());

        TextView total_ac,total_amount;
        total_ac = convertView.findViewById(R.id.total_ac);
        total_amount = convertView.findViewById(R.id.total_amount);
        total_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_ac());
        total_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_amount());


        TextView st_loan_ac,st_loan_amount;
        st_loan_ac = convertView.findViewById(R.id.st_loan_ac);
        st_loan_amount = convertView.findViewById(R.id.st_loan_amount);
        st_loan_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_ac());
        st_loan_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_amount());

        TextView pl_loan_ac,pl_loan_amount;
        pl_loan_ac = convertView.findViewById(R.id.la_loan_ac);
        pl_loan_amount = convertView.findViewById(R.id.la_loan_amount);
        pl_loan_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getPl_loan_ac());
        pl_loan_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getPl_loan_amount());

        TextView la_loan_ac,la_loan_amount;
        la_loan_ac = convertView.findViewById(R.id.pl_loan_ac);
        la_loan_amount = convertView.findViewById(R.id.pl_loan_amount);
        la_loan_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_ac());
        la_loan_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_amount());


        TextView gp_loan_ac,gp_loan_amount;
        gp_loan_ac = convertView.findViewById(R.id.gp_loan_ac);
        gp_loan_amount = convertView.findViewById(R.id.gp_loan_amount);
        gp_loan_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_ac());
        gp_loan_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_amount());

        TextView st_loan_recovery_ac,st_loan_recovery_amount;
        st_loan_recovery_ac = convertView.findViewById(R.id.st_loan_recovery_ac);
        st_loan_recovery_amount = convertView.findViewById(R.id.st_loan_recovery_amount);
        st_loan_recovery_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_recovery_ac());
        st_loan_recovery_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getSt_loan_recovery_amount());

        TextView pl_loan_recovery_ac,pl_loan_recovery_amount;
        pl_loan_recovery_ac = convertView.findViewById(R.id.pl_loan_recovery_ac);
        pl_loan_recovery_amount = convertView.findViewById(R.id.pl_loan_recovery_amount);
        pl_loan_recovery_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getPl_loan_recovery_ac());
        pl_loan_recovery_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getPl_loan_recovery_amount());

        TextView la_loan_recovery_ac,la_loan_recovery_amount;
        la_loan_recovery_ac = convertView.findViewById(R.id.la_loan_recovery_ac);
        la_loan_recovery_amount = convertView.findViewById(R.id.la_loan_recovery_amount);
        la_loan_recovery_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_recovery_ac());
        la_loan_recovery_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLa_loan_recovery_amount());

        TextView gp_loan_recovery_ac,gp_loan_recovery_amount;
        gp_loan_recovery_ac = convertView.findViewById(R.id.gp_loan_recovery_ac);
        gp_loan_recovery_amount = convertView.findViewById(R.id.gp_loan_recovery_amount);
        gp_loan_recovery_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getGp_loan_recovery_ac());
        gp_loan_recovery_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getGp_loan_recovery_amount());

        TextView loan_recovery_ac,loan_recovery_amount;
        loan_recovery_ac = convertView.findViewById(R.id.loan_recovery_ac);
        loan_recovery_amount = convertView.findViewById(R.id.loan_recovery_amount);
        loan_recovery_ac.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLoan_recovery_ac());
        loan_recovery_amount.setText(parent_models.get(groupPosition).
                getAssociateBusinessSummaryReportChildren().get(childPosition).getLoan_recovery_amount());




        textViewFDNINoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getFd_new_ac());
        textViewFDNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getFd_deno_sum());

        textViewSoname.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSector_name());
        textViewRoname.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getRegion_name());
        textViewZoname.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getZone_name());
        textViewtextCode.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getMember_id());
        Log.e("member_idmember_id",parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getMember_id());
        textViewName.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getName());
        textViewtextCarder.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getCadre());

        textViewDailyNI.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getDaily_new_ac());
        textViewDailyNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getDaily_deno_sum());
        textViewDailyRenewNoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getDaily_renew_ac());
        textViewDailyRenewTotalAmt.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getDaily_renew());

        textViewSSBNINoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_new_ac());
        textViewSSBNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_deno_sum());
        textViewSSBDepositNoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_renew());
        textViewSSBDepositTotalAmt.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_renew_ac());

        textViewMonthlyNINoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getKanyadhan_new_ac());
        textMonthlyNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getKanyadhan_deno_sum());
        textViewMonthlyRenewNoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getKanyadhan_renew_ac());
        textViewMonthlyRenewTotalAmt.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getKanyadhan_renew());


        textViewSSBNINoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_new_ac());
        textViewSSBNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_deno_sum());
        textViewSSBDepositNoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_renew());
        textViewSSBDepositTotalAmt.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getSsb_renew_ac());
        textViewFDNINoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getFd_new_ac());
        textViewFDNITotalDeno.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getFd_deno_sum());
        textViewNCC_M.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getNi_m());
        textViewNCC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getNi());
        textViewTCC_M.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getTcc_m());
        textViewTCC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getTcc());
        textViewOtherMI.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getOther_mt());
        textViewOtherSTN.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getOther_stn());

        textViewLoanRecoveryNoAC.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getLoan_recovery_ac());
        textViewRecoveryTotal.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getLoan_recovery_amount());
        textViewNewAssociateJoiningNo.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getNew_associate());
        textViewTotalAssociateJoiningNo.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_associate());
        textViewNewMemberJoiningNo.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getNew_member());
        textViewTotalMemberJoiningNo.setText(parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().get(childPosition).getTotal_member());




        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.parent_models.get(groupPosition).getAssociateBusinessSummaryReportChildren().size();
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
        Log.e("namenmae",parent_models.get(groupPosition).getName());
        Log.e("getProgress",parent_models.get(groupPosition).getProgress());
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
