package com.associate.sbmfa.Fragment.AssociateLedgerManagement;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.PlanDetailsTransaction;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.ResultPlanTransDetails;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.TransactionPlanLedgerResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.HashMap;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class PlanLedgerDetailsFragment extends Fragment {

    View RootView;
    ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    String transId,ssid;
    TextView textViewTranId,textViewName,
            textViewDescription,textViewAmount,
            textViewAssociateName,textViewTransactionDate,
            textViewPaymentMethod,textViewBalance,
            textViewAssociateCode,textViewAccount;
    TextView AmountTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView =  inflater.inflate(R.layout.fragment_plan_ledger_details, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        textViewTranId = RootView.findViewById(R.id.gomRegDate);
        AmountTv = RootView.findViewById(R.id.goSectorNameTV);
        textViewName = RootView.findViewById(R.id.gobranchCode);
        textViewDescription = RootView.findViewById(R.id.gobarnchName);
        textViewAccount = RootView.findViewById(R.id.goassociateId);
        textViewAmount = RootView.findViewById(R.id.gosectorname);
        textViewAssociateName = RootView.findViewById(R.id.gomemberid);
        textViewTransactionDate = RootView.findViewById(R.id.gomemberName);
        textViewPaymentMethod = RootView.findViewById(R.id.goadharNumber);
        textViewBalance = RootView.findViewById(R.id.gopenNumber);
        textViewAssociateCode = RootView.findViewById(R.id.goSeniorName);

        if (getArguments()!=null){
            transId = getArguments().getString("id");
            ssid = getArguments().getString("id_ssid");
        }

        getTransactionsDetails(member_id,token,transId,ssid);

        imageViewBack = RootView.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        RootView.setFocusableInTouchMode(true);
        RootView.requestFocus();
        RootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return RootView;
    }
    private void getTransactionsDetails(String member_id, String token, String transId, String ssid) {
        try {
            googleProgressDialog.show1("Loading Data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _transId = RequestBody.create(MediaType.parse("multipart/form-data"), transId);
            RequestBody _ssid = RequestBody.create(MediaType.parse("multipart/form-data"), ssid);
            RestHandler.getApiService().TRANSACTION_PLAN_LEDGER_RESPONSE_CALL(_member_id,_token,_ssid,_transId).enqueue(new Callback<TransactionPlanLedgerResponse>() {
                @Override
                public void onResponse(Call<TransactionPlanLedgerResponse> call, Response<TransactionPlanLedgerResponse> response) {
                    if (response.isSuccessful()){
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200){
                            if (response.body().getAssociateStatus() == 0){
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                                ResultPlanTransDetails result = response.body().getResult();
                                PlanDetailsTransaction plan = result.getTransaction();
                                textViewTranId.setText(String.valueOf(plan.getTranid()));
                                textViewName.setText(plan.getName());
                                textViewDescription.setText(plan.getDescription());
                                textViewAccount.setText(plan.getAccountNo());
                                if (plan.getWithdrawal().equals("0.00")){
                                    AmountTv.setText("Deposit");
                                    textViewAmount.setText(plan.getDeposit()!=null ? plan.getDeposit()+" ₹" : "N/A");
                                }else {
                                    AmountTv.setText("Withdrawal");
                                    textViewAmount.setText(plan.getWithdrawal()!=null ? plan.getWithdrawal()+" ₹" : "N/A");

                                }
                                textViewAssociateName.setText(plan.getAssociateName());
                                textViewTransactionDate.setText(plan.getDate());
                                textViewPaymentMethod.setText(plan.getPaymentMode());
                                textViewBalance.setText(plan.getOpeningBalance()+ " ₹");
                                textViewAssociateCode.setText(plan.getAssociateCode());
                           /* }else {
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }*/
                        }else {
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data not Found...", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<TransactionPlanLedgerResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });
        }catch (IllegalStateException ex){
        }
    }
}