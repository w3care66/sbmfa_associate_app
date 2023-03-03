package com.associate.sbmfa.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.associate.sbmfa.Fragment.AssociateManagement.AssociateDeatilsViewFragment;
import com.associate.sbmfa.Model.AssociateLoanDetail;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.CommonResponseWithoutData;
import com.associate.sbmfa.Respones.OTPSendResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenewalPaymentFragment extends Fragment {
    ConstraintLayout associateDetailsConstraintLayout;
    TextView detailNotFoundTextView;
    Spinner associateNoListSpinner;
    ImageView backButtonImageView;
    SessionManager sessionManager;
    String member_id = "", token = "";
    HashMap<String, String> UserData;
    HashMap<String, String> UserDataToken;
    public static ArrayList<String> AccountNoList = new ArrayList<>();
    public static List<AssociateLoanDetail.AccountDetails> associateAccountLoanDetails = new ArrayList<>();
    public TextView accountNoTextView, memberNameTextView, plannameTextView, denoAmountTextView, balanceAmountTextView, dueAmountTextView;
    EditText depositAmountEditText;
    public Button payNowButton;
    ConstraintLayout denoAmountConstraintLayout;
    int selectPosition = 0;
    ConstraintLayout renewalDetailsConstraintLayout;
    TextView noDataFoundTextView;
    String currentBalance= "";
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_renewal_payment, container, false);
        sessionManager = new SessionManager(getActivity());
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        UserData = sessionManager.getUserDetails();
        UserDataToken = sessionManager.getUserDetailsToken();
        member_id = UserData.get(SessionManager.KEY_member_id);
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        associateDetailsConstraintLayout = rootView.findViewById(R.id.cl_associate_details);
        detailNotFoundTextView = rootView.findViewById(R.id.detailnot_found);
        associateNoListSpinner = rootView.findViewById(R.id.spinner_associate);
        backButtonImageView = rootView.findViewById(R.id.iv_loan_back);
        backButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        accountNoTextView = rootView.findViewById(R.id.account_no);
        memberNameTextView = rootView.findViewById(R.id.member_name);
        denoAmountTextView = rootView.findViewById(R.id.deno_amount);
        plannameTextView = rootView.findViewById(R.id.plan_name);
        depositAmountEditText = rootView.findViewById(R.id.deposit_amount);
        payNowButton = rootView.findViewById(R.id.btn_paynow);
        denoAmountConstraintLayout = rootView.findViewById(R.id.cl_denoamount);
        renewalDetailsConstraintLayout = rootView.findViewById(R.id.cl_re_newel);
        noDataFoundTextView = rootView.findViewById(R.id.no_data_found);
        balanceAmountTextView = rootView.findViewById(R.id.balance_amount);
        dueAmountTextView = rootView.findViewById(R.id.due_amount);

        getAssociateLoanData(member_id, token);

        associateNoListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectPosition = position - 1;
                    associateDetailsConstraintLayout.setVisibility(View.VISIBLE);
                    detailNotFoundTextView.setVisibility(View.GONE);
                    accountNoTextView.setText(associateAccountLoanDetails.get(position - 1).getAccountNo());
                    memberNameTextView.setText(associateAccountLoanDetails.get(position - 1).getMemberName());
                    denoAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getDenoAmount());
                    plannameTextView.setText(associateAccountLoanDetails.get(position - 1).getPlanName());
                    balanceAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getBalanceAmount());
                    dueAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getDueAmount());
                } else {
                    selectPosition = 0;
                    associateDetailsConstraintLayout.setVisibility(View.GONE);
                    detailNotFoundTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!depositAmountEditText.getText().toString().equalsIgnoreCase("")) {
                    if (Double.parseDouble(depositAmountEditText.getText().toString()) <= Double.parseDouble(currentBalance)){
                        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
                        Call<OTPSendResponse> applicationsListResponesCall = RestHandler.getApiService().SEND_PAYMENT_OTP(_member_id, _type);
                        applicationsListResponesCall.enqueue(new Callback<OTPSendResponse>() {
                            @Override
                            public void onResponse(Call<OTPSendResponse> call, Response<OTPSendResponse> response) {
                                if (response.body().getCode() != null && response.body().getCode() == 200) {
                                    if (response.body().getAssociateStatus() == 0) {
                                        AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                        dialog.checkStatus();
                                    } else {
                                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                        Bundle renewalbundle = new Bundle();
                                        renewalbundle.putString("type", "Renewal");
                                        renewalbundle.putString("otp",response.body().getResult());
                                        renewalbundle.putString("member_id", String.valueOf(member_id));
                                        renewalbundle.putString("account_id", associateAccountLoanDetails.get(selectPosition).getAccountId());
                                        renewalbundle.putString("deno_amount", associateAccountLoanDetails.get(selectPosition).getDenoAmount());
                                        renewalbundle.putString("deposit", depositAmountEditText.getText().toString());
                                        renewalbundle.putString("payment_mode", "SSB");
                                        Fragment renewalfragment = new PaymentOtpVerifyFragment();
                                        renewalfragment.setArguments(renewalbundle);
                                        FragmentTransaction renewaltransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        renewaltransaction.replace(R.id.nav_host_fragment, renewalfragment);
                                        renewaltransaction.commit();

                                    }
                                }else {
                                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                            }
                        });
                    }else {
                        Toast.makeText(getActivity(), "Insufficient balance in your SSB account", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Add Deposit Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    private void getAssociateLoanData(String member_id, String token) {
        progressDialog.show();
        AccountNoList.clear();
        associateAccountLoanDetails.clear();
        AccountNoList.add("Select Account");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        final Call<AssociateLoanDetail> getAssociateLoanCall = RestHandler.getApiService().MEMBER_LOAN_DATA(_member_id, _token, _type);
        getAssociateLoanCall.enqueue(new Callback<AssociateLoanDetail>() {
            @Override
            public void onResponse(Call<AssociateLoanDetail> call, Response<AssociateLoanDetail> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getCode() == 200) {
                        renewalDetailsConstraintLayout.setVisibility(View.VISIBLE);
                        noDataFoundTextView.setVisibility(View.GONE);
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        currentBalance = response.body().getCurrentBalance();
                        if (response.body().getAccountDetail().size() > 0) {
                            for (int i = 0; i < response.body().getAccountDetail().size(); i++) {
                                AssociateLoanDetail.AccountDetails accountDetails = new AssociateLoanDetail.AccountDetails();
                                accountDetails.setAccountId(response.body().getAccountDetail().get(i).getAccountId());
                                accountDetails.setAccountNo(response.body().getAccountDetail().get(i).getAccountNo());
                                accountDetails.setPlanName(response.body().getAccountDetail().get(i).getPlanName());
                                accountDetails.setMemberName(response.body().getAccountDetail().get(i).getMemberName());
                                accountDetails.setDueAmount(response.body().getAccountDetail().get(i).getDueAmount());
                                accountDetails.setDenoAmount(response.body().getAccountDetail().get(i).getDenoAmount());
                                accountDetails.setEmiAmount(response.body().getAccountDetail().get(i).getEmiAmount());
                                accountDetails.setIsPay(response.body().getAccountDetail().get(i).getIsPay());
                                accountDetails.setBalanceAmount(response.body().getAccountDetail().get(i).getBalanceAmount());
                                associateAccountLoanDetails.add(accountDetails);
                                AccountNoList.add(response.body().getAccountDetail().get(i).getAccountNo());
                            }
                        }

                        ArrayAdapter<String> adapterselectState = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, AccountNoList);
                        adapterselectState.setDropDownViewResource(R.layout.spiner_item);
                        associateNoListSpinner.setAdapter(adapterselectState);
                    } else {
                        renewalDetailsConstraintLayout.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    renewalDetailsConstraintLayout.setVisibility(View.GONE);
                    noDataFoundTextView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateLoanDetail> call, Throwable t) {
                progressDialog.dismiss();
                renewalDetailsConstraintLayout.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}