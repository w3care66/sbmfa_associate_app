package com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.DailyRenewalModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.AccountDetail;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewAccountDetailResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.ResultRenewalAccountDatails;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
public class DailyRenewalListAdapter extends RecyclerView.Adapter<DailyRenewalListAdapter.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<DailyRenewalModel> mData;
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
    public DailyRenewalListAdapter(Context context, String check, ArrayList<DailyRenewalModel> data, EventListener eventListener,EventListener1 eventListener1,Activity activity) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.eventListener = eventListener;
            this.eventListener1 = eventListener1;
            this.check = check;
            this.activity =activity;
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
        if(check.equals("0")){
             view = mInflater.inflate(R.layout.renewal_plan_register_daily_renewal, viewGroup, false);
        }else if(check.equals("1")){
            view = mInflater.inflate(R.layout.renewal_plan_register_daily_renewal, viewGroup, false);
        }else if(check.equals("2")){
            view = mInflater.inflate(R.layout.renewal_plan_register_daily_renewal, viewGroup, false);
        }
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (check.equals("0")){
            Log.e("Data",""+mData.get(i).getAccount_no());
            viewHolder.editText_AccountNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        if (!TextUtils.isEmpty(viewHolder.editText_AccountNumber.getText().toString())){
                            View view = activity.getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                          //  getCollectorData(member_id,token,editTextCollecorCode.getText().toString());
                         getAccountNumber(member_id,token, viewHolder.editText_AccountNumber.getText().toString(),check,i,viewHolder);
                        }else {
                            Toast.makeText(context, "Text is Empty..", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                    return false;
                }
            });
            viewHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!viewHolder.Name.getText().toString().isEmpty()){
                        if (!viewHolder.Amount.getText().toString().isEmpty()){
                            int due_amount = Integer.valueOf(mData.get(i).getTotalAmount());
                            int amount = Integer.parseInt(viewHolder.Amount.getText().toString());
                            int due_amount_total = due_amount + amount;
                            Toast.makeText(context, "Details added successfully!", Toast.LENGTH_SHORT).show();
                            DailyRenewalModel dailyRenewalModel = new DailyRenewalModel(
                                    mData.get(i).getAccount_no(),
                                    mData.get(i).getName(),
                                    viewHolder.Amount.getText().toString(),
                                    String.valueOf(due_amount_total),
                                    mData.get(i).getAssociate_name(),
                                    mData.get(i).getTotalAmount()
                            );
                            mData.set(i,dailyRenewalModel);
                            //viewHolder.DueAmount.setText(String.valueOf(due_amount_total));
                            viewHolder.Amount.setText(null);
                            eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                        }else {
                            Toast.makeText(context, "Amount does not exists!!", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "Account Number does not exists!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            viewHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mData.remove(i);
                     viewHolder.Amount.setText(null);
                     eventListener1.onEvent_DailyRewalRemove(i,"");
                    //notifyDataSetChanged();
                }
            });
            viewHolder.editText_AccountNumber.setText(mData.get(i).getAccount_no());
            viewHolder.Name.setText(mData.get(i).getName());
            viewHolder.AssociateName.setText(mData.get(i).getAssociate_name());
            viewHolder.DueAmount.setText(String.valueOf(mData.get(i).getDue_amount()));
            viewHolder.Amount.setText(String.valueOf(mData.get(i).getAmount()));

        }else  if (check.equals("1")){

            viewHolder.constraintLayout5.setVisibility(View.GONE);
            viewHolder.textViewDueAmount.setText("Due Amount");
            viewHolder.editText_AccountNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        if (!TextUtils.isEmpty(viewHolder.editText_AccountNumber.getText().toString())){
                            View view = activity.getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            //  getCollectorData(member_id,token,editTextCollecorCode.getText().toString());
                            getAccountNumberRDFRD(member_id,token, viewHolder.editText_AccountNumber.getText().toString(),check,i,viewHolder);
                        }else {
                            Toast.makeText(context, "Text is Empty..", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                    return false;
                }
            });
            viewHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!viewHolder.Name.getText().toString().isEmpty()) {
                        if (!viewHolder.Amount.getText().toString().isEmpty()) {
                            int amou_ = Integer.parseInt(viewHolder.Amount.getText().toString());
                            int desposite_amount = Integer.parseInt(mData.get(i).getAssociate_name());
                            Log.e("DEPos",""+amou_+ " / "+desposite_amount);
                            if (amou_ >= desposite_amount){
                                int div_amount = amou_ / desposite_amount;
                                int multi_amount = div_amount * desposite_amount;
                                Log.e("Equel",""+multi_amount+" = "+desposite_amount);
                                if (multi_amount == amou_) {
                                    int due_amount = Integer.valueOf(mData.get(i).getTotalAmount());
                                    int amount = Integer.parseInt(viewHolder.Amount.getText().toString());
                                    int due_amount_total = due_amount + amount;
                                    DailyRenewalModel dailyRenewalModel = new DailyRenewalModel(
                                            mData.get(i).getAccount_no(),
                                            mData.get(i).getName(),
                                            viewHolder.Amount.getText().toString(),
                                            String.valueOf(due_amount_total),
                                            mData.get(i).getAssociate_name(),
                                            mData.get(i).getTotalAmount()
                                    );
                                    mData.set(i,dailyRenewalModel);
                                    //viewHolder.DueAmount.setText(String.valueOf(due_amount_total));
                                    viewHolder.Amount.setText(null);
                                    eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                                }else {
                                    Toast.makeText(context, "Amount should be multiply deno amount!", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(context, "Amount should be multiply deno amount!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "Amount does not exists!!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "Account Number does not exists!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            viewHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mData.remove(i);
                    viewHolder.Amount.setText(null);
                    eventListener1.onEvent_DailyRewalRemove(i,"");
                    //notifyDataSetChanged();
                }
            });

            viewHolder.editText_AccountNumber.setText(mData.get(i).getAccount_no());
            viewHolder.Name.setText(mData.get(i).getName());
            //viewHolder.AssociateName.setText(mData.get(i).getAssociate_name());
            viewHolder.DueAmount.setText(String.valueOf(mData.get(i).getDue_amount()));
            viewHolder.Amount.setText(String.valueOf(mData.get(i).getAmount()));

        }else  if (check.equals("2")){

            viewHolder.constraintLayout5.setVisibility(View.GONE);
            viewHolder.buttonRemove.setVisibility(View.GONE);
            viewHolder.textViewDueAmount.setText("Total Amount");
            Log.e("Data",""+mData.get(i).getAccount_no());
            viewHolder.editText_AccountNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        if (!TextUtils.isEmpty(viewHolder.editText_AccountNumber.getText().toString())){
                            View view = activity.getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            //  getCollectorData(member_id,token,editTextCollecorCode.getText().toString());
                            getAccountNumber(member_id,token, viewHolder.editText_AccountNumber.getText().toString(),check,i,viewHolder);
                        }else {
                            Toast.makeText(context, "Text is Empty..", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                    return false;
                }
            });


            viewHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!viewHolder.Name.getText().toString().isEmpty()){
                        Log.e("Amount",""+viewHolder.Amount.getText().toString());
                        if (!viewHolder.Amount.getText().toString().isEmpty()){
                            int amount = Integer.parseInt(viewHolder.Amount.getText().toString());

                           /* int due_amount = Integer.valueOf(mData.get(i).getTotalAmount());

                            int due_amount_total = due_amount + amount;*/
                            DailyRenewalModel dailyRenewalModel = new DailyRenewalModel(
                                    mData.get(i).getAccount_no(),
                                    mData.get(i).getName(),
                                    viewHolder.Amount.getText().toString(),
                                    String.valueOf(amount),
                                    mData.get(i).getAssociate_name(),
                                    mData.get(i).getTotalAmount()
                            );
                            mData.set(i,dailyRenewalModel);
                            //viewHolder.DueAmount.setText(String.valueOf(due_amount_total));
                            viewHolder.Amount.setText(null);
                            eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                        }else {
                            Toast.makeText(context, "Amount does not exists!!", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "Account Number does not exists!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            viewHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mData.remove(i);
                    viewHolder.Amount.setText(null);
                    eventListener1.onEvent_DailyRewalRemove(i,"");
                    //notifyDataSetChanged();
                }
            });
            viewHolder.editText_AccountNumber.setText(mData.get(i).getAccount_no());
            viewHolder.Name.setText(mData.get(i).getName());
          //  viewHolder.AssociateName.setText(mData.get(i).getAssociate_name());
            viewHolder.DueAmount.setText(String.valueOf(mData.get(i).getAmount()));
            viewHolder.Amount.setText(String.valueOf(mData.get(i).getAmount()));
        }
    }
    private void getAccountNumberRDFRD(String member_id, String token, String account, String check, final int i, final ViewHolder viewHolder) {
        googleProgressDialog.show1("Loading Data...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _account_ = RequestBody.create(MediaType.parse("multipart/form-data"), viewHolder.editText_AccountNumber.getText().toString());
        RequestBody _plan_id = RequestBody.create(MediaType.parse("multipart/form-data"), check);
        RestHandler.getApiService().RENEW_ACCOUNT_DETAIL_RESPONSE_CALL(_assciate_no,_token,_plan_id,_account_).enqueue(new Callback<RenewAccountDetailResponse>() {
            @Override
            public void onResponse(Call<RenewAccountDetailResponse> call, Response<RenewAccountDetailResponse> response) {
                if (response!=null){
                    googleProgressDialog.dismiss();
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 1){
                            if (response.body().getResult()!=null){
                                int total = 0;
                                ResultRenewalAccountDatails result = response.body().getResult();
                                AccountDetail accountDetail = result.getAccountDetail();
                                int Due_amount = accountDetail.getDue_amount()!= null ? accountDetail.getDue_amount() : 0;
                                Log.e("DFGR",""+Due_amount);
                                DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                        accountDetail.getAccountNumber(),
                                        accountDetail.getMemberName(),
                                        "",
                                        String.valueOf(Due_amount),
                                        String.valueOf(accountDetail.getDeposite_amount()),
                                        String.valueOf(Due_amount)
                                );
                                mData.set(i,dailyRenewalModelq);
                                notifyDataSetChanged();
                               /* viewHolder.Name.setText(accountDetail.getMemberName());
                                viewHolder.AssociateName.setText(accountDetail.getAssociateName());
                                viewHolder.DueAmount.setText(String.valueOf(accountDetail.getDue_amount()));*/
                               /* total = accountDetail.getDue_amount();
                                final int finalTotal = total;*/
                            }
                        }else {
                            DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""
                            );
                            mData.set(i,dailyRenewalModelq);
                            notifyDataSetChanged();
                            eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                            Toast.makeText(context, ""+response.body().getMessages(), Toast.LENGTH_LONG).show();
                        }
                    }else {
                        DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                        );
                        mData.set(i,dailyRenewalModelq);
                        notifyDataSetChanged();
                        eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                        Toast.makeText(context, ""+response.body().getMessages(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                    Toast.makeText(context, "Data Not Found..", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<RenewAccountDetailResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });
    }


    private void getAccountNumber(String member_id, String token, String account, String check, final int i, final ViewHolder viewHolder) {
        googleProgressDialog.show1("Loading Data...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _account_ = RequestBody.create(MediaType.parse("multipart/form-data"), viewHolder.editText_AccountNumber.getText().toString());
        RequestBody _plan_id = RequestBody.create(MediaType.parse("multipart/form-data"), check);
        RestHandler.getApiService().RENEW_ACCOUNT_DETAIL_RESPONSE_CALL(_assciate_no,_token,_plan_id,_account_).enqueue(new Callback<RenewAccountDetailResponse>() {
            @Override
            public void onResponse(Call<RenewAccountDetailResponse> call, Response<RenewAccountDetailResponse> response) {
                if (response!=null){
                    googleProgressDialog.dismiss();
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 1){
                            if (response.body().getResult()!=null){
                                int total = 0;
                                ResultRenewalAccountDatails result = response.body().getResult();
                                AccountDetail accountDetail = result.getAccountDetail();
                                int Due_amount = accountDetail.getDue_amount()!= null ? accountDetail.getDue_amount() : 0;
                                Log.e("DFGR",""+Due_amount);
                                DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                        accountDetail.getAccountNumber(),
                                        accountDetail.getMemberName(),
                                        "",
                                        String.valueOf(Due_amount),
                                        accountDetail.getAssociateName(),
                                        String.valueOf(Due_amount)
                                );
                                mData.set(i,dailyRenewalModelq);
                                notifyDataSetChanged();
                               /* viewHolder.Name.setText(accountDetail.getMemberName());
                                viewHolder.AssociateName.setText(accountDetail.getAssociateName());
                                viewHolder.DueAmount.setText(String.valueOf(accountDetail.getDue_amount()));*/
                               /* total = accountDetail.getDue_amount();
                                final int finalTotal = total;*/
                            }
                        }else {
                            DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""
                            );
                            mData.set(i,dailyRenewalModelq);
                            notifyDataSetChanged();
                            eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                            Toast.makeText(context, ""+response.body().getMessages(), Toast.LENGTH_LONG).show();
                        }
                    }else {
                        DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                        );
                        mData.set(i,dailyRenewalModelq);
                        notifyDataSetChanged();
                        eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                        Toast.makeText(context, ""+response.body().getMessages(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                    DailyRenewalModel dailyRenewalModelq = new DailyRenewalModel(
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
                    mData.set(i,dailyRenewalModelq);
                    notifyDataSetChanged();
                    eventListener.onEvent_DailyRewal(String.valueOf(i), viewHolder.Amount.getText().toString());
                    Toast.makeText(context, "Data Not Found..", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<RenewAccountDetailResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
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
        LinearLayout constraintLayout;
        ConstraintLayout constraintLayout4,constraintLayout5;
        TextView textViewDueAmount;
        ViewHolder(View itemView) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.constraintLayout);
            editText_AccountNumber = itemView.findViewById(R.id.editText_AccountNumber);
            Name = itemView.findViewById(R.id.Name);
            Amount = itemView.findViewById(R.id.Amount);
            DueAmount = itemView.findViewById(R.id.DueAmount);
            AssociateName = itemView.findViewById(R.id.AssociateName);
            buttonAdd = itemView.findViewById(R.id.button4);
            buttonRemove = itemView.findViewById(R.id.button5);
            textViewDueAmount = itemView.findViewById(R.id.DueAmount_);
            constraintLayout4 = itemView.findViewById(R.id.constraintLayout4);
            constraintLayout5 = itemView.findViewById(R.id.constraintLayout5);
        }
    }
}
