package com.associate.sbmfa.Fragment.LoanManagment.Loan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Model.ApplicantDepositeDetailModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApplicantDepositeDetailAdapater extends RecyclerView.Adapter<ApplicantDepositeDetailAdapater.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<ApplicantDepositeDetailModel> mData;
    Context context;
    boolean flag = false;
    ApplicantDepositeDetailAdapater.EventListener eventListener;
    String check;
    public interface EventListener {
        void onEvent_CardClick(String id, String amount);
    }
    public ApplicantDepositeDetailAdapater(Context context,  ArrayList<ApplicantDepositeDetailModel> data, ApplicantDepositeDetailAdapater.EventListener eventListener) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.eventListener = eventListener;
        }
    }
    @NonNull
    @Override
    public ApplicantDepositeDetailAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = mInflater.inflate(R.layout.applicant_deposite_detail_item, viewGroup, false);
        return new ApplicantDepositeDetailAdapater.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ApplicantDepositeDetailAdapater.ViewHolder viewHolder, final int i) {
         viewHolder.Scheme.setText(mData.get(i).getScheme());
         viewHolder.AccountID.setText(mData.get(i).getAccountID());
         viewHolder.OpenDate.setText(mData.get(i).getOpenDate());
         viewHolder.DueDate.setText(mData.get(i).getDueDate());
         viewHolder.Deposit.setText(mData.get(i).getDeposit());
         viewHolder.Tenure.setText(mData.get(i).getTenure());
         viewHolder.LoanAmount.setText(mData.get(i).getLoanAmount()+" ₹");

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView Scheme,	AccountID,	OpenDate,	DueDate,	Deposit,Tenure,	LoanAmount;
         
        ViewHolder(View itemView) {
            super(itemView);
            Scheme = itemView.findViewById(R.id.Scheme);
            AccountID = itemView.findViewById(R.id.AccountID);
            OpenDate = itemView.findViewById(R.id.OpenDate);
            DueDate = itemView.findViewById(R.id.DueDate);
            Deposit = itemView.findViewById(R.id.Deposit);
            Tenure = itemView.findViewById(R.id.Tenure);
            LoanAmount = itemView.findViewById(R.id.LoanAmount);
        }
    }
}
