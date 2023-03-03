package com.associate.sbmfa.Fragment.ReportManagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareParentModel;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateCollectionCompare.AssociateCollectionCompareParentModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class AssociateCollectionCompareAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<AssociateCollectionCompareParentModel> parent_models = new ArrayList<>();
    TextView name,
            currentDailyDenoSum,
            currentMonthlyDenoSum,
            currentFdDenoSum,
            currentAllCollection,
            currentTcc,
            currentNcc,
            currentLoanRecoveryAmount,
            compareDailyDenoSum,
            compareMonthlyDenoSum, compareFdDenoSum, compareAllCollection, compareTcc,
            compareLoanRecoveryAmount, resultDailyDenoSum, compareNcc, resultMonthlyDenoSum, resultFdDenoSum,
            resultTcc, resultLoanRecoveryAmount, resultCollectionAll, resultNcc;
    String type = "0";

    public AssociateCollectionCompareAdapter(Context context, ArrayList<AssociateCollectionCompareParentModel> parent_models, String type) {
        if (context != null) {
            this._context = context;
            this.parent_models = parent_models;
            this.type = type;
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
            convertView = infalInflater.inflate(R.layout.associate_collection_compare_report_child, null);
        }

        name = convertView.findViewById(R.id.name);
        compareTcc = convertView.findViewById(R.id.compare_tcc);
       // resultDailyDenoSum = convertView.findViewById(R.id.result_daily_deno_sum);
        compareLoanRecoveryAmount = convertView.findViewById(R.id.compare_loan_recovery_amount);
        currentDailyDenoSum = convertView.findViewById(R.id.current_daily_deno_sumaa);
        currentMonthlyDenoSum = convertView.findViewById(R.id.current_monthly_deno_sum);
        currentFdDenoSum = convertView.findViewById(R.id.current_fd_deno_sum);
        currentAllCollection = convertView.findViewById(R.id.current_all_collection);
        currentTcc = convertView.findViewById(R.id.current_tcc);
        currentNcc = convertView.findViewById(R.id.current_ncc);
        currentLoanRecoveryAmount = convertView.findViewById(R.id.current_loan_recovery_amount);
        compareDailyDenoSum = convertView.findViewById(R.id.compare_daily_deno_sum);
        compareMonthlyDenoSum = convertView.findViewById(R.id.compare_monthly_deno_sum);
        compareFdDenoSum = convertView.findViewById(R.id.compare_fd_deno_sum);
        compareAllCollection = convertView.findViewById(R.id.compare_all_collection);
        compareNcc = convertView.findViewById(R.id.compare_ncc);
        resultMonthlyDenoSum = convertView.findViewById(R.id.result_monthly_deno_sum);
        resultFdDenoSum = convertView.findViewById(R.id.result_fd_deno_sum);
        resultTcc = convertView.findViewById(R.id.result_tcc);
        resultLoanRecoveryAmount = convertView.findViewById(R.id.result_loan_recovery_amount);
        resultCollectionAll = convertView.findViewById(R.id.result_collection_all);
        resultNcc = convertView.findViewById(R.id.result_ncc);

        name.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getName());
        currentDailyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentDailyDenoSum().toString());

        //resultDailyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultDailyDenoSum().toString());
        compareLoanRecoveryAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareLoanRecoveryAmount().toString());
        compareTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTcc().toString());
        currentMonthlyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentMonthlyDenoSum().toString());
        currentFdDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentFdDenoSum().toString());
        currentAllCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentAllCollection().toString());
        currentTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentTcc().toString());
        currentNcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentNcc().toString());
        currentLoanRecoveryAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCurrentLoanRecoveryAmount().toString());
        compareDailyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareDailyDenoSum().toString());
        compareMonthlyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareMonthlyDenoSum().toString());
        compareFdDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareFdDenoSum().toString());
        compareAllCollection.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareAllCollection().toString());
        compareNcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getCompareTcc().toString());
        resultMonthlyDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultMonthlyDenoSum().toString());
        resultFdDenoSum.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultFdDenoSum().toString());
        resultTcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultTcc().toString());
        resultLoanRecoveryAmount.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultLoanRecoveryAmount().toString());
        resultCollectionAll.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultCollectionAll().toString());
        resultNcc.setText(parent_models.get(groupPosition).getChild_models().get(childPosition).getResultNcc().toString());

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
        TextView textViewId, textViewApplicationId, textViewAccount;
        ImageView up_dwon_icon;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_business_compare_report_parent, null);
        }

        textViewId = convertView.findViewById(R.id.id);
        textViewApplicationId = convertView.findViewById(R.id.name);
        textViewAccount = convertView.findViewById(R.id.des);
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
