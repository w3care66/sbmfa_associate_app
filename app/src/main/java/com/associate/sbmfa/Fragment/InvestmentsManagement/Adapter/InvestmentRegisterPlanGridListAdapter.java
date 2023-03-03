package com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalPlanModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class InvestmentRegisterPlanGridListAdapter extends RecyclerView.Adapter<InvestmentRegisterPlanGridListAdapter.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<RenewalPlanModel> mData;
    Context context;
    boolean flag = false;
    EventListener eventListener;
    String check;
    public interface EventListener {
        void onEvent_CardClick(String id, String amount,String plan_code);
    }
    public InvestmentRegisterPlanGridListAdapter(Context context,  ArrayList<RenewalPlanModel> data, EventListener eventListener) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.eventListener = eventListener;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = mInflater.inflate(R.layout.renewal_plan_grid_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(mData.get(i).getName());
        if (mData.get(i).getIcon().equals("0")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_one));
        }else if (mData.get(i).getIcon().equals("1"))
        {
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_two));
        }else if (mData.get(i).getIcon().equals("2")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_three));
        }else if (mData.get(i).getIcon().equals("3")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_one));
        }else if (mData.get(i).getIcon().equals("4"))
        {
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_two));
        }else if (mData.get(i).getIcon().equals("5")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_three));
        }else if (mData.get(i).getIcon().equals("6")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_one));
        }else if (mData.get(i).getIcon().equals("7"))
        {
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_two));
        }else if (mData.get(i).getIcon().equals("8")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_three));
        }else if (mData.get(i).getIcon().equals("9")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_three));
        }else if (mData.get(i).getIcon().equals("10")){
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_one));
        }else if (mData.get(i).getIcon().equals("11"))
        {
            viewHolder.constraintLayout.setBackground(context.getDrawable(R.drawable.card_two));
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               eventListener.onEvent_CardClick(
                       String.valueOf(i),
                       mData.get(i).getName(),
                       mData.get(i).getPlan_code()

               );
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        EditText editText_AccountNumber,Name,Amount,DueAmount,AssociateName;
        Button buttonAdd,buttonRemove;

        CardView cardView;
        ConstraintLayout constraintLayout,constraintLayout5;
        TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            constraintLayout=itemView.findViewById(R.id.constraintLayout);
            name=itemView.findViewById(R.id.textView17);
        }
    }
}
