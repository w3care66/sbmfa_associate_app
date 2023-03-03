package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.associate.sbmfa.Fragment.AssociateManagement.Model.DependentsModel;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
public class Associate_DependentslListAdapter extends RecyclerView.Adapter<Associate_DependentslListAdapter.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<DependentsModel> mData;
    Context context;
    boolean flag = false;
    EventListener eventListener;
    EventListener1 eventListener1;
    String check;
    Activity activity;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    public interface EventListener {
        void onEvent_DailyRewal(String id,String amount);
    }
    public interface EventListener1 {
        void onEvent_DailyRewalRemove(int pos,String amount);
    }
    public Associate_DependentslListAdapter(Context context, ArrayList<DependentsModel> data) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;

            sessionManager = new SessionManager(context);
            UserDataToken =sessionManager.getUserDetailsToken();
            token=UserDataToken.get(SessionManager.KEY_TOKEN);
            UserData =sessionManager.getUserDetails();
            googleProgressDialog = new GoogleProgressDialog(context);
            member_id= UserData.get(SessionManager.KEY_member_id);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = mInflater.inflate(R.layout.associate_dependents_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.full_name.setText(mData.get(i).getName());
        viewHolder.DependentType.setText(mData.get(i).getDependent_type());
        viewHolder.Gender.setText(mData.get(i).getGender());
        viewHolder.Relation.setText(mData.get(i).getRelation());
        viewHolder.Marital.setText(mData.get(i).getMarital_status());
        viewHolder.LivingName.setText(mData.get(i).getLiving_with_associate());
        viewHolder.month_income.setText(mData.get(i).getMonthly_income()+" ₹");
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        EditText full_name,DependentType,Gender,Age,Relation,Marital,LivingName,month_income;
        Button buttonAdd,buttonRemove;
        LinearLayout constraintLayout;
        ConstraintLayout constraintLayout4,constraintLayout5;
        TextView textViewDueAmount;
        ViewHolder(View itemView) {
            super(itemView);
            full_name = itemView.findViewById(R.id.editText_AccountNumber);
            DependentType = itemView.findViewById(R.id.Name);
            Gender = itemView.findViewById(R.id.Amount);
            Relation = itemView.findViewById(R.id.AssociateName);
            Marital = itemView.findViewById(R.id.Maritalstatus);
            LivingName = itemView.findViewById(R.id.LivingName);
            month_income = itemView.findViewById(R.id.month_income);
            /*constraintLayout=itemView.findViewById(R.id.constraintLayout);
            editText_AccountNumber = itemView.findViewById(R.id.editText_AccountNumber);
            Name = itemView.findViewById(R.id.Name);
            Amount = itemView.findViewById(R.id.Amount);
            DueAmount = itemView.findViewById(R.id.DueAmount);
            AssociateName = itemView.findViewById(R.id.AssociateName);
            buttonAdd = itemView.findViewById(R.id.button4);
            buttonRemove = itemView.findViewById(R.id.button5);
            textViewDueAmount = itemView.findViewById(R.id.DueAmount_);
            constraintLayout4 = itemView.findViewById(R.id.constraintLayout4);
            constraintLayout5 = itemView.findViewById(R.id.constraintLayout5);*/
        }
    }
}
