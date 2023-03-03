package com.associate.sbmfa.Fragment.ReportManagment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareParentModel;
import com.associate.sbmfa.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AssociateBusinessCompareReportAdapter  extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<AssociateBusinessCompareParentModel> parent_models = new ArrayList<>();
    TextView joinDate, branch,
            branchCode, sectorName,
            regionName, zoneName, memberId,
            name, cadre, CurrentDaily, CurrentDailyCollection, CurrentMonthly, CurrentMonthlyCollection,
    CurrentFd, CurrentSsb, CurrentSsbDeposit, CurrentNCC_M, CurrentNCC, CurrentTcc_M, CurrentTcc, CurrentLoanAmount,
    CurrentLoanRecovery, CurrentTotalAssociate,
            CompareDaily, CompareDailyCollection, CompareMonthly, CompareMonthlyCollection,
            CompareFd, CompareSsb, CompareSsbDeposit, CompareNCC_M, CompareNCC, CompareTcc_M, CompareTcc, CompareLoanAmount,
            CompareLoanRecovery, CompareTotalAssociate,
    ResultDaily, ResultDailyCollection, ResultMonthly, ResultMonthlyCollection,
            ResultFd, ResultSsb,ResultSsbDeposit, ResultNCC_M, ResultNCC, ResultTcc_M, ResultTcc, ResultLoanAmount,
            ResultLoanRecovery, ResultTotalAssociate;

    public AssociateBusinessCompareReportAdapter(Context context, ArrayList<AssociateBusinessCompareParentModel> parent_models) {
        if (context != null) {
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

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_business_compare_report_child, null);
        }


        joinDate = convertView.findViewById(R.id.joinDate);
        branch = convertView.findViewById(R.id.branch);
        branchCode = convertView.findViewById(R.id.branchCode);
        sectorName = convertView.findViewById(R.id.sectorName);
        regionName = convertView.findViewById(R.id.regionName);
        zoneName = convertView.findViewById(R.id.zoneName);
        memberId = convertView.findViewById(R.id.memberId);
        cadre = convertView.findViewById(R.id.cadre);

        CurrentDaily = convertView.findViewById(R.id.currentDaily);
        CurrentDailyCollection = convertView.findViewById(R.id.currentDailyCollection);
        CurrentMonthly = convertView.findViewById(R.id.currentmonthly);
        CurrentMonthlyCollection = convertView.findViewById(R.id.currentMonthlyCollection);
        CurrentFd = convertView.findViewById(R.id.currentFd);
        CurrentSsb = convertView.findViewById(R.id.currentSsb);
        CurrentSsbDeposit = convertView.findViewById(R.id.currentSsbDeposit);
        CurrentNCC_M = convertView.findViewById(R.id.currentNccM);
        CurrentNCC = convertView.findViewById(R.id.currentNcc);
        CurrentTcc_M = convertView.findViewById(R.id.currentTccM);
        CurrentTcc = convertView.findViewById(R.id.currentTcc);
        CurrentLoanAmount = convertView.findViewById(R.id.currentLoanAmount);
        CurrentLoanRecovery = convertView.findViewById(R.id.currentLoanRecovery);
        CurrentTotalAssociate = convertView.findViewById(R.id.currentTotalAssociate);

        CompareDaily = convertView.findViewById(R.id.compareDaily);
        CompareDailyCollection = convertView.findViewById(R.id.compareDailyCollection);
        CompareMonthly = convertView.findViewById(R.id.compareMonthly);
        CompareMonthlyCollection = convertView.findViewById(R.id.compareMonthlyCollection);
        CompareFd = convertView.findViewById(R.id.compareFd);
        CompareSsb = convertView.findViewById(R.id.compareSsb);
        CompareSsbDeposit = convertView.findViewById(R.id.compareSsbDeposit);
        CompareNCC_M = convertView.findViewById(R.id.compareNccM);
        CompareNCC = convertView.findViewById(R.id.compareNcc);
        CompareTcc_M = convertView.findViewById(R.id.compareTccM);
        CompareTcc = convertView.findViewById(R.id.compareTcc);
        CompareLoanAmount = convertView.findViewById(R.id.compareLoanAmount);
        CompareLoanRecovery = convertView.findViewById(R.id.compareLoanRecovery);
        CompareTotalAssociate = convertView.findViewById(R.id.compareTotalAssociate);


        ResultDaily = convertView.findViewById(R.id.resultDaily);
        ResultDailyCollection = convertView.findViewById(R.id.resultDailyCollection);
        ResultMonthly = convertView.findViewById(R.id.resultMonthly);
        ResultMonthlyCollection = convertView.findViewById(R.id.resultMonthlyCollection);
        ResultFd = convertView.findViewById(R.id.resultFd);
        ResultSsb = convertView.findViewById(R.id.resultSsb);
        ResultSsbDeposit = convertView.findViewById(R.id.resultSsbDeposit);
        ResultNCC_M = convertView.findViewById(R.id.resultNccM);
        ResultNCC = convertView.findViewById(R.id.resultNcc);
        ResultTcc_M = convertView.findViewById(R.id.resultTccM);
        ResultTcc = convertView.findViewById(R.id.resultTcc);
        ResultLoanAmount = convertView.findViewById(R.id.resultLoanAmount);
        ResultLoanRecovery = convertView.findViewById(R.id.resultLoanRecovery);
        ResultTotalAssociate = convertView.findViewById(R.id.resultTotalAssociate);


//        currentOtherMI = convertView.findViewById(R.id.currentOtherMI);
//        currentSTN = convertView.findViewById(R.id.currentSTN);
//        Current_NCC_M= convertView.findViewById(R.id.Current_NCC_M);
//        current_nCC= convertView.findViewById(R.id.current_nCC);
//        current_tCC_M= convertView.findViewById(R.id.current_tCC_M);
//        current_tCC= convertView.findViewById(R.id.current_tCC);
//        current_loan_amount= convertView.findViewById(R.id.current_loan_amount);
//        current_loan_recovery_ac= convertView.findViewById(R.id.current_loan_recovery_ac);
//        current_loan_noac= convertView.findViewById(R.id.current_loan_noac);
//        current_loan_recovery_amount=convertView.findViewById(R.id.current_loan_recovery_amount);
//        current_new_associate= convertView.findViewById(R.id.current_new_associate);
//        current_total_associate= convertView.findViewById(R.id.current_total_associate);
//        current_new_member= convertView.findViewById(R.id.current_new_member);
//        current_total_member= convertView.findViewById(R.id.current_total_member);
//        compare_daily_new_ac= convertView.findViewById(R.id.compare_daily_new_ac);
//        compare_daily_deno_sum= convertView.findViewById(R.id.compare_daily_deno_sum);
//        compare_daily_renew_ac= convertView.findViewById(R.id.compare_daily_renew_ac);
//        compare_daily_renew= convertView.findViewById(R.id.compare_daily_renew);
//        compare_monthly_new_ac= convertView.findViewById(R.id.compare_monthly_new_ac);
//        compare_monthly_deno_sum= convertView.findViewById(R.id.compare_monthly_deno_sum);
//        compare_monthly_renew_ac= convertView.findViewById(R.id.compare_monthly_renew_ac);
//        compare_monthly_renew=convertView.findViewById(R.id.compare_monthly_renew);
//        compare_fd_new_ac=convertView.findViewById(R.id.compare_fd_new_ac);
//        compare_fd_deno_sum=convertView.findViewById(R.id.compare_fd_deno_sum);
//        compare_ssb_new_ac=convertView.findViewById(R.id.compare_ssb_new_ac);
//        compare_ssb_deno_sum=convertView.findViewById(R.id.compare_ssb_deno_sum);
//        compare_ssb_renew_ac=convertView.findViewById(R.id.compare_ssb_renew_ac);
//        compare_ssb_renew=convertView.findViewById(R.id.compare_ssb_renew);
//        compare_other_mt=convertView.findViewById(R.id.compare_other_mt);
//        compare_other_stn=convertView.findViewById(R.id.compare_other_stn);
//        compare_ni_m=convertView.findViewById(R.id.compare_ni_m);
//        compare_ni=convertView.findViewById(R.id.compare_ni);
//        compare_tcc_m=convertView.findViewById(R.id.compare_tcc_m);
//        compare_tcc=convertView.findViewById(R.id.compare_tcc);
//        compare_loan_ac=convertView.findViewById(R.id.compare_loan_ac);
//        compare_loan_amount=convertView.findViewById(R.id.compare_loan_amount);
//        compare_loan_recovery_ac=convertView.findViewById(R.id.compare_loan_recovery_ac);
//        compare_loan_recovery_amount=convertView.findViewById(R.id.compare_loan_recovery_amount);
//        compare_new_associate=convertView.findViewById(R.id.compare_new_associate);
//        compare_total_associate=convertView.findViewById(R.id.compare_total_associate);
//        compare_new_member=convertView.findViewById(R.id.compare_new_member);
//        compare_total_member=convertView.findViewById(R.id.compare_total_member);
//
//        result_daily_new_ac=convertView.findViewById(R.id.result_daily_new_ac);
//        result_daily_deno_sum=convertView.findViewById(R.id.result_daily_deno_sum);
//        result_daily_renew_ac=convertView.findViewById(R.id.result_daily_renew_ac);
//        result_daily_renew=convertView.findViewById(R.id.result_daily_renew);
//        result_monthly_new_ac=convertView.findViewById(R.id.result_monthly_new_ac);
//        result_monthly_deno_sum=convertView.findViewById(R.id.result_monthly_deno_sum);
//        result_monthly_renew_ac=convertView.findViewById(R.id.result_monthly_renew_ac);
//        result_monthly_renew=convertView.findViewById(R.id.result_monthly_renew);
//        result_fd_new_ac=convertView.findViewById(R.id.result_fd_new_ac);
//        result_fd_deno_sum=convertView.findViewById(R.id.result_fd_deno_sum);
//        result_ssb_new_ac=convertView.findViewById(R.id.result_ssb_new_ac);
//        result_ssb_deno_sum=convertView.findViewById(R.id.result_ssb_deno_sum);
//        result_ssb_renew_ac=convertView.findViewById(R.id.result_ssb_renew_ac);
//        result_ssb_renew=convertView.findViewById(R.id.result_ssb_renew);
//        result_other_mt=convertView.findViewById(R.id.result_other_mt);
//        result_other_stn=convertView.findViewById(R.id.result_other_stn);
//        result_ni_m=convertView.findViewById(R.id.result_ni_m);
//        result_ni=convertView.findViewById(R.id.result_ni);
//        result_tcc_m=convertView.findViewById(R.id.result_tcc_m);
//        result_tcc=convertView.findViewById(R.id.result_tcc);
//        result_loan_ac=convertView.findViewById(R.id.result_loan_ac);
//        result_loan_amount=convertView.findViewById(R.id.result_loan_amount);
//        result_loan_recovery_ac=convertView.findViewById(R.id.result_loan_recovery_ac);
//        result_loan_recovery_amount=convertView.findViewById(R.id.result_loan_recovery_amount);
//        result_new_associate=convertView.findViewById(R.id.result_new_associate);
//        result_total_associate=convertView.findViewById(R.id.result_total_associate);
//        result_new_member=convertView.findViewById(R.id.result_new_member);
//        result_total_member=convertView.findViewById(R.id.result_total_member);

        joinDate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getJoinDate());
        branch.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getBranch());
        branchCode.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getBranchCode());
        sectorName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getSectorName());
        regionName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getRegionName());
        zoneName.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getZoneName());
        memberId.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getMemberId());
        cadre.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCadre());


        CurrentDaily.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentDailyDenoSum());
        CurrentDailyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentDailyRenew());
        CurrentMonthly.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentMonthlyDenoSum());
        CurrentMonthlyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentMonthlyRenew());
        CurrentFd.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentFdDenoSum());
        CurrentSsb.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentSsbDenoSum());
        CurrentSsbDeposit.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentSsbRenew());
        CurrentNCC_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentNiM());
        CurrentNCC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentNi());
        CurrentTcc_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentTccM());
        CurrentTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentTcc());
        CurrentLoanAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentLoanAmount());
        CurrentLoanRecovery.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentLoanRecoveryAmount());
        CurrentTotalAssociate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentTotalAssociate());

        CompareDaily.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareDailyDenoSum());
        CompareDailyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareDailyRenew());
        CompareMonthly.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareMonthlyDenoSum());
        CompareMonthlyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareMonthlyRenew());
        CompareFd.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareFdDenoSum());
        CompareSsb.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareSsbDenoSum());
        CompareSsbDeposit.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareSsbRenew());
        CompareNCC_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNiM());
        CompareNCC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNi());
        CompareTcc_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTccM());
        CompareTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTcc());
        CompareLoanAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanAmount());
        CompareLoanRecovery.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanRecoveryAmount());
        CompareTotalAssociate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTotalAssociate());

        ResultDaily.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyDenoSum());
        ResultDailyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyRenew());
        ResultMonthly.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyDenoSum());
        ResultMonthlyCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyRenew());
        ResultFd.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultFdDenoSum());
        ResultSsb.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbDenoSum());
        ResultSsbDeposit.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbRenew());
        ResultNCC_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNiM());
        ResultNCC.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNi());
        ResultTcc_M.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTccM());
        ResultTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTcc());
        ResultLoanAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanAmount());
        ResultLoanRecovery.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanRecoveryAmount());
        ResultTotalAssociate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTotalAssociate());
//        compare_other_mt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareOtherMt());
//        compare_other_stn.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareOtherStn());
//        compare_ni_m.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNiM());
//        compare_ni.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNi());
//        compare_tcc_m.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTccM());
//        compare_tcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTcc());
//        compare_loan_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanAc());
//        compare_loan_amount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanAmount());
//        compare_loan_recovery_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanRecoveryAc());
//        compare_loan_recovery_amount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanRecoveryAmount());
//        compare_new_associate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNewAssociate());
//        compare_total_associate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTotalAssociate());
//        compare_new_member.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareNewMember());
//        compare_total_member.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTotalMember());
//        result_daily_new_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyNewAc());
//        result_daily_deno_sum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyDenoSum());
//        result_daily_renew_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyRenewAc());
//        result_daily_renew.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyRenew());
//        result_monthly_new_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyNewAc());
//        result_monthly_deno_sum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyDenoSum());
//        result_monthly_renew_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyRenewAc());
//        result_monthly_renew.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyRenew());
//        result_fd_new_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultFdNewAc());
//        result_fd_deno_sum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultFdDenoSum());
//        result_ssb_new_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbNewAc());
//        result_ssb_deno_sum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbDenoSum());
//        result_ssb_renew_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbRenewAc());
//        result_ssb_renew.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultSsbRenew());
//        result_other_mt.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultOtherMt());
//        result_other_stn.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultOtherStn());
//        result_ni_m.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNiM());
//        result_ni.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNi());
//        result_tcc_m.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTccM());
//        result_tcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTcc());
//        result_loan_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanAc());
//        result_loan_amount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanAmount());
//        result_loan_recovery_ac.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanRecoveryAc());
//        result_loan_recovery_amount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanRecoveryAmount());
//        result_new_associate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNewAssociate());
//        result_total_associate.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTotalAssociate());
//        result_new_member.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNewMember());
//        result_total_member.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTotalMember());

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
            convertView = infalInflater.inflate(R.layout.associate_business_compare_report_parent, null);
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
