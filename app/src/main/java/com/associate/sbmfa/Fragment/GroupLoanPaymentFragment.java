package com.associate.sbmfa.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import com.associate.sbmfa.Activity.login.LoginActivity;
import com.associate.sbmfa.Activity.login.OtpActivity;
import com.associate.sbmfa.Fragment.AssociateManagement.AssociateDeatilsViewFragment;
import com.associate.sbmfa.Model.AssociateLoanDetail;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.CommonResponseWithoutData;
import com.associate.sbmfa.Respones.GSTAmountResponse;
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

public class GroupLoanPaymentFragment extends Fragment {
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
    public TextView accountNoTextView, memberNameTextView, dueAmountTextView, planNameTextView, recoverAmountTextView, outstandingAmountTextView;
    EditText emiAmountTextView, plentyAmountTextView;
    public Button payNowButton;
    ConstraintLayout emiamountConstraintLayout, plentyAmountConstraintLayout, dueAmountConstraintLayout;
    ConstraintLayout SGSTConstraintLayout, CGSTConstraintLayout, IGSTConstraintLayout;
    TextView SGSTTitleTextView, SGSAmountTextView, CGSTTitleTextView, CGSTAmountTextView, IGSTTitleTextView, IGSTAmountTextView;
    int selectedPosition = 0;
    ConstraintLayout groupLoanDetailsConstraintLayout;
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_loan_payment, container, false);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        sessionManager = new SessionManager(getActivity());
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
        emiAmountTextView = rootView.findViewById(R.id.emi_amount);
        plentyAmountTextView = rootView.findViewById(R.id.plenty_amount);
        payNowButton = rootView.findViewById(R.id.btn_paynow);
        dueAmountTextView = rootView.findViewById(R.id.due_amount);
        planNameTextView = rootView.findViewById(R.id.plan_name);
        recoverAmountTextView = rootView.findViewById(R.id.recover_amount);
        outstandingAmountTextView = rootView.findViewById(R.id.outstand_amount);
        emiamountConstraintLayout = rootView.findViewById(R.id.cl_emiamount);
        plentyAmountConstraintLayout = rootView.findViewById(R.id.cl_plentyamount);
        dueAmountConstraintLayout = rootView.findViewById(R.id.cl_dueamount);
        groupLoanDetailsConstraintLayout = rootView.findViewById(R.id.cl_group_loan);
        noDataFoundTextView = rootView.findViewById(R.id.no_data_found);

        SGSTConstraintLayout = rootView.findViewById(R.id.cl_sgst);
        SGSTTitleTextView = rootView.findViewById(R.id.sght_title);
        SGSAmountTextView = rootView.findViewById(R.id.sgst_amount);
        CGSTConstraintLayout = rootView.findViewById(R.id.cl_cgst);
        CGSTTitleTextView = rootView.findViewById(R.id.cght_title);
        CGSTAmountTextView = rootView.findViewById(R.id.cgst_amount);
        IGSTConstraintLayout = rootView.findViewById(R.id.cl_igst);
        IGSTTitleTextView = rootView.findViewById(R.id.ight_title);
        IGSTAmountTextView = rootView.findViewById(R.id.igst_amount);


        getAssociateLoanData(member_id, token);

        associateNoListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectedPosition = position - 1;
                    associateDetailsConstraintLayout.setVisibility(View.VISIBLE);
                    detailNotFoundTextView.setVisibility(View.GONE);
                    accountNoTextView.setText(associateAccountLoanDetails.get(position - 1).getAccountNo());
                    memberNameTextView.setText(associateAccountLoanDetails.get(position - 1).getMemberName());
                    dueAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getDueAmount());
                    emiAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getEmiAmount());
                    planNameTextView.setText(associateAccountLoanDetails.get(position - 1).getPlanName());
                    recoverAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getRecoverAmount());
                    outstandingAmountTextView.setText(associateAccountLoanDetails.get(position - 1).getOutStandingAmount());

                    if (associateAccountLoanDetails.get(position - 1).getIsPenlty().equalsIgnoreCase("0")){
                        plentyAmountTextView.setEnabled(false);
                        planNameTextView.setFocusable(false);
                        planNameTextView.setClickable(false);
                    }else{
                        plentyAmountTextView.setEnabled(true);
                        planNameTextView.setFocusable(true);
                        planNameTextView.setClickable(true);
                    }

                } else {
                    selectedPosition = 0;
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
                if (!emiAmountTextView.getText().toString().equalsIgnoreCase("") && Double.parseDouble(emiAmountTextView.getText().toString()) > 0) {
                    if (associateAccountLoanDetails.get(selectedPosition).getIsPenlty().equalsIgnoreCase("0")){
                        Double totalAmount = Double.parseDouble(emiAmountTextView.getText().toString()) + Double.parseDouble(plentyAmountTextView.getText().toString());
                        if (totalAmount <= Double.parseDouble(currentBalance)){
                            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "3");
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
                                            renewalbundle.putString("type", "Group Loan");
                                            renewalbundle.putString("otp", response.body().getResult());
                                            renewalbundle.putString("member_id", String.valueOf(member_id));
                                            renewalbundle.putString("account_id", associateAccountLoanDetails.get(selectedPosition).getAccountId());
                                            renewalbundle.putString("emi_amount", emiAmountTextView.getText().toString());
                                            renewalbundle.putString("plenty_amount", plentyAmountTextView.getText().toString());
                                            renewalbundle.putString("payment_mode", "SSB");
                                            Fragment renewalfragment = new PaymentOtpVerifyFragment();
                                            renewalfragment.setArguments(renewalbundle);
                                            FragmentTransaction renewaltransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            renewaltransaction.replace(R.id.nav_host_fragment, renewalfragment);
                                            renewaltransaction.commit();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                                }
                            });
                        }else {
                            Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                        }
                    }else if (!plentyAmountTextView.getText().toString().equalsIgnoreCase("") && Integer.parseInt(plentyAmountTextView.getText().toString()) >= 0) {
                        Double totalAmount = Double.parseDouble(emiAmountTextView.getText().toString()) + Double.parseDouble(plentyAmountTextView.getText().toString());
                        if (totalAmount <= Double.parseDouble(currentBalance)){
                            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "3");
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
                                            renewalbundle.putString("type", "Group Loan");
                                            renewalbundle.putString("otp", response.body().getResult());
                                            renewalbundle.putString("member_id", String.valueOf(member_id));
                                            renewalbundle.putString("account_id", associateAccountLoanDetails.get(selectedPosition).getAccountId());
                                            renewalbundle.putString("emi_amount", emiAmountTextView.getText().toString());
                                            renewalbundle.putString("plenty_amount", plentyAmountTextView.getText().toString());
                                            renewalbundle.putString("payment_mode", "SSB");
                                            Fragment renewalfragment = new PaymentOtpVerifyFragment();
                                            renewalfragment.setArguments(renewalbundle);
                                            FragmentTransaction renewaltransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            renewaltransaction.replace(R.id.nav_host_fragment, renewalfragment);
                                            renewaltransaction.commit();
                                        }
                                    } else {
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
                        Toast.makeText(getActivity(), "Enter Valid Penalty Amount", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter Valid EMI Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        plentyAmountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equalsIgnoreCase("") && !s.toString().equalsIgnoreCase("0")){
                    getAccountGSTData(s.toString());
                }else {
                    SGSTConstraintLayout.setVisibility(View.GONE);
                    CGSTConstraintLayout.setVisibility(View.GONE);
                    IGSTConstraintLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return rootView;
    }

    private void getAccountGSTData(String amount) {
        progressDialog.show();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group");
        RequestBody _loanId = RequestBody.create(MediaType.parse("multipart/form-data"), associateAccountLoanDetails.get(selectedPosition).getAccountId());
        RequestBody _penaltyAmount = RequestBody.create(MediaType.parse("multipart/form-data"), amount);
        Call<GSTAmountResponse> gstAmountResponseCall = RestHandler.getApiService().GET_GST_AMOUNT(_member_id, _token, _type, _loanId, _penaltyAmount);
        gstAmountResponseCall.enqueue(new Callback<GSTAmountResponse>() {
            @Override
            public void onResponse(Call<GSTAmountResponse> call, Response<GSTAmountResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        if (response.body().getGstData().getGstLabel().size() > 0) {
                            for (int i = 0; i < response.body().getGstData().getGstLabel().size(); i++) {
                                if (response.body().getGstData().getGstLabel().get(i).equalsIgnoreCase("SGST")){
                                    SGSTConstraintLayout.setVisibility(View.VISIBLE);
                                }else if (response.body().getGstData().getGstLabel().get(i).equalsIgnoreCase("CGST")){
                                    CGSTConstraintLayout.setVisibility(View.VISIBLE);
                                }else if (response.body().getGstData().getGstLabel().get(i).equalsIgnoreCase("IGST")){
                                    IGSTConstraintLayout.setVisibility(View.VISIBLE);
                                }
                            }
                            if (response.body().getGstData().getGstLabel().size() == 1) {
                                IGSTTitleTextView.setText(response.body().getGstData().getLabelName());
                                IGSTAmountTextView.setText(response.body().getGstData().getLabelValue());
                            }else if (response.body().getGstData().getGstLabel().size() == 2) {
                                SGSTTitleTextView.setText(response.body().getGstData().getLabelName().split(",")[0]);
                                SGSAmountTextView.setText(response.body().getGstData().getLabelValue().split(",")[0]);
                                CGSTTitleTextView.setText(response.body().getGstData().getLabelName().split(",")[1]);
                                CGSTAmountTextView.setText(response.body().getGstData().getLabelValue().split(",")[1]);
                            }
                        }else {
                            SGSTConstraintLayout.setVisibility(View.GONE);
                            CGSTConstraintLayout.setVisibility(View.GONE);
                            IGSTConstraintLayout.setVisibility(View.GONE);
                        }
                    }else {
                        SGSTConstraintLayout.setVisibility(View.GONE);
                        CGSTConstraintLayout.setVisibility(View.GONE);
                        IGSTConstraintLayout.setVisibility(View.GONE);
                    }
                }else {
                    progressDialog.dismiss();
                    SGSTConstraintLayout.setVisibility(View.GONE);
                    CGSTConstraintLayout.setVisibility(View.GONE);
                    IGSTConstraintLayout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GSTAmountResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAssociateLoanData(String member_id, String token) {
        progressDialog.show();
        AccountNoList.clear();
        associateAccountLoanDetails.clear();
        AccountNoList.add("Select Account");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "3");
        final Call<AssociateLoanDetail> getAssociateLoanCall = RestHandler.getApiService().MEMBER_LOAN_DATA(_member_id, _token, _type);
        getAssociateLoanCall.enqueue(new Callback<AssociateLoanDetail>() {
            @Override
            public void onResponse(Call<AssociateLoanDetail> call, Response<AssociateLoanDetail> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getCode() == 200) {
                        groupLoanDetailsConstraintLayout.setVisibility(View.VISIBLE);
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
                                accountDetails.setRecoverAmount(response.body().getAccountDetail().get(i).getRecoverAmount());
                                accountDetails.setIsPenlty(response.body().getAccountDetail().get(i).getIsPenlty());
                                accountDetails.setOutStandingAmount(response.body().getAccountDetail().get(i).getOutStandingAmount());
                                associateAccountLoanDetails.add(accountDetails);
                                AccountNoList.add(response.body().getAccountDetail().get(i).getAccountNo() + " (" +response.body().getAccountDetail().get(i).getMemberName() +")");
                            }
                        }

                        ArrayAdapter<String> adapterselectState = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, AccountNoList);
                        adapterselectState.setDropDownViewResource(R.layout.spiner_item);
                        associateNoListSpinner.setAdapter(adapterselectState);
                    } else {
                        groupLoanDetailsConstraintLayout.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    groupLoanDetailsConstraintLayout.setVisibility(View.GONE);
                    noDataFoundTextView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateLoanDetail> call, Throwable t) {
                progressDialog.dismiss();
                groupLoanDetailsConstraintLayout.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}