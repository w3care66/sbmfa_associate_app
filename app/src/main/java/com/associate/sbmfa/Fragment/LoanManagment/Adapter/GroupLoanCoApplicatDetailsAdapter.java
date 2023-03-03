package com.associate.sbmfa.Fragment.LoanManagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.LoanManagment.Loan.Model.GroupLoanCoApplicateModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupLoanCoApplicatDetailsAdapter extends RecyclerView.Adapter<GroupLoanCoApplicatDetailsAdapter.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<GroupLoanCoApplicateModel> mData;
    Context context;
    boolean flag = false;
    GroupLoanCoApplicatDetailsAdapter.EventListener eventListener;
    String check;
    public interface EventListener {
        void onEvent_CardClick(String id, String amount);
    }
    public GroupLoanCoApplicatDetailsAdapter(Context context,  ArrayList<GroupLoanCoApplicateModel> data) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;

        }
    }
    @NonNull
    @Override
    public GroupLoanCoApplicatDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = mInflater.inflate(R.layout.group_loan_co_applicanet_item, viewGroup, false);
        return new GroupLoanCoApplicatDetailsAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final GroupLoanCoApplicatDetailsAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.MemberID.setText(mData.get(i).getMemberID());
        viewHolder.MemberName.setText(mData.get(i).getMemberName());
        viewHolder.FatherName.setText(mData.get(i).getFatherName());
        viewHolder.IFSCCode.setText(mData.get(i).getIFSCCode());
        viewHolder.BankName.setText(mData.get(i).getBankName());
        viewHolder.SSBAccount.setText(mData.get(i).getSSBAccount());
        viewHolder.Amount.setText(mData.get(i).getAmount()+" ₹");

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView MemberID,	MemberName,	FatherName,	Amount,	BankName,	SSBAccount,	IFSCCode;

        ViewHolder(View itemView) {
            super(itemView);
            MemberID = itemView.findViewById(R.id.MemberId);
            MemberName = itemView.findViewById(R.id.MemberName);
            FatherName = itemView.findViewById(R.id.FatherName);
            Amount = itemView.findViewById(R.id.Amount);
            BankName = itemView.findViewById(R.id.BAnkName);
            SSBAccount = itemView.findViewById(R.id.SSBAccount);
            IFSCCode = itemView.findViewById(R.id.IFSCCode);
        }
    }
}
