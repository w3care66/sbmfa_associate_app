package com.associate.sbmfa.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Activity.login.OtpActivity;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.CommonResponseWithoutData;
import com.associate.sbmfa.Respones.OTPSendResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.HashMap;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOtpVerifyFragment extends Fragment {
    TextView optTitleTextView;
    Button sendbtn;
    OtpTextView otp_view;
    TextView resend;
    HashMap<String, String> UserDataToken;
    HashMap<String, String> UserData;
    SessionManager sessionManager;
    String token = "";
    GoogleProgressDialog googleProgressDialog;
    String OTP = "";
    String type = "";
    String member_id = "";

    String memberID, accountId, emiAmount, plentyAmount, paymentMode, deno_amount, depositAmount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_otp_verify, container, false);
        optTitleTextView = rootView.findViewById(R.id.textView);
        otp_view = rootView.findViewById(R.id.otp_view);
        sendbtn = rootView.findViewById(R.id.sendbtn);
        resend = rootView.findViewById(R.id.textView7);
        sessionManager = new SessionManager(getActivity());
        UserData = sessionManager.getUserDetails();
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        member_id = UserData.get(SessionManager.KEY_member_id);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        final Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            OTP = bundle.getString("otp");
            if (type.equalsIgnoreCase("Group Loan")) {
                memberID = bundle.getString("member_id");
                accountId = bundle.getString("account_id");
                emiAmount = bundle.getString("emi_amount");
                plentyAmount = bundle.getString("plenty_amount");
                paymentMode = bundle.getString("payment_mode");
            } else if (type.equalsIgnoreCase("Loan")) {
                memberID = bundle.getString("member_id");
                accountId = bundle.getString("account_id");
                emiAmount = bundle.getString("emi_amount");
                plentyAmount = bundle.getString("plenty_amount");
                paymentMode = bundle.getString("payment_mode");
            } else {
                memberID = bundle.getString("member_id");
                accountId = bundle.getString("account_id");
                deno_amount = bundle.getString("deno_amount");
                paymentMode = bundle.getString("payment_mode");
                depositAmount = bundle.getString("deposit");
            }
        }

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otp_view.getOTP();
                if (otp.equals("")) {
                    Toast.makeText(getActivity(), "Please Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    if (otp.length() == 6) {
                        if (otp.equalsIgnoreCase(OTP)) {
                            if (type.equalsIgnoreCase("Group Loan")) {
                                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), memberID);
                                RequestBody account_id = RequestBody.create(MediaType.parse("multipart/form-data"), accountId);
                                RequestBody emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), emiAmount);
                                RequestBody plenty_amount = RequestBody.create(MediaType.parse("multipart/form-data"), plentyAmount);
                                RequestBody _paymentMode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
                                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                                RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
                                final Call<CommonResponseWithoutData> submitRenewalPaymentCall = RestHandler.getApiService().SUBMIT_GROUP_LOAN_PAYMENT(_member_id, account_id, emi_amount, plenty_amount, _paymentMode, _token, _type);
                                submitRenewalPaymentCall.enqueue(new Callback<CommonResponseWithoutData>() {
                                    @Override
                                    public void onResponse(Call<CommonResponseWithoutData> call, Response<CommonResponseWithoutData> response) {
                                        if (response.body().getCode() == 200) {

                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_success);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Congratulations..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                            Fragment grouploanFragment = new GroupLoanPaymentFragment();
                                                            FragmentTransaction grouploantransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                            grouploantransaction.replace(R.id.nav_host_fragment, grouploanFragment);
                                                            grouploantransaction.commit();
                                                }
                                            });

                                            dialog.show();

//                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                            builder.setMessage(response.body().getMessage())
//                                                    .setCancelable(false)
//                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int id) {
//                                                            dialog.dismiss();
//                                                            Fragment grouploanFragment = new GroupLoanPaymentFragment();
//                                                            FragmentTransaction grouploantransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                                            grouploantransaction.replace(R.id.nav_host_fragment, grouploanFragment);
//                                                            grouploantransaction.commit();
//                                                        }
//                                                    });
//                                            AlertDialog alert = builder.create();
//                                            alert.show();
                                        } else {
                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_failed);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Failure..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            dialog.show();

//                                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CommonResponseWithoutData> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (type.equalsIgnoreCase("Loan")) {
                                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), memberID);
                                RequestBody account_id = RequestBody.create(MediaType.parse("multipart/form-data"), accountId);
                                RequestBody emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), emiAmount);
                                RequestBody plenty_amount = RequestBody.create(MediaType.parse("multipart/form-data"), plentyAmount);
                                RequestBody _paymentMode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
                                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                                RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
                                final Call<CommonResponseWithoutData> submitRenewalPaymentCall = RestHandler.getApiService().SUBMIT_LOAN_PAYMENT(_member_id, account_id, emi_amount, plenty_amount, _paymentMode, _token, _type);
                                submitRenewalPaymentCall.enqueue(new Callback<CommonResponseWithoutData>() {
                                    @Override
                                    public void onResponse(Call<CommonResponseWithoutData> call, Response<CommonResponseWithoutData> response) {
                                        if (response.body().getCode() == 200) {

                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_success);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Congratulations..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    Fragment loanFragment = new MakeLoanPaymentFragement();
                                                    FragmentTransaction loantransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                    loantransaction.replace(R.id.nav_host_fragment, loanFragment);
                                                    loantransaction.commit();
                                                }
                                            });

                                            dialog.show();

//                                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                            Fragment loanFragment = new MakeLoanPaymentFragement();
//                                            FragmentTransaction loantransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                            loantransaction.replace(R.id.nav_host_fragment, loanFragment);
//                                            loantransaction.commit();
                                        } else {

                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_failed);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Failure..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            dialog.show();

//                                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CommonResponseWithoutData> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
                                RequestBody account_id = RequestBody.create(MediaType.parse("multipart/form-data"), accountId);
                                RequestBody denoAmount = RequestBody.create(MediaType.parse("multipart/form-data"), deno_amount);
                                RequestBody _depositAmount = RequestBody.create(MediaType.parse("multipart/form-data"), depositAmount);
                                RequestBody _paymentMode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
                                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                                final Call<CommonResponseWithoutData> submitRenewalPaymentCall = RestHandler.getApiService().SUBMIT_RENEWAL_PAYMENT(_member_id, account_id, denoAmount, _paymentMode, _token, _depositAmount);
                                submitRenewalPaymentCall.enqueue(new Callback<CommonResponseWithoutData>() {
                                    @Override
                                    public void onResponse(Call<CommonResponseWithoutData> call, Response<CommonResponseWithoutData> response) {
                                        if (response.body().getCode() == 200) {

                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_success);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Congratulations..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    Fragment renewalfragment = new RenewalPaymentFragment();
                                                    FragmentTransaction renewaltransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                    renewaltransaction.replace(R.id.nav_host_fragment, renewalfragment);
                                                    renewaltransaction.commit();
                                                }
                                            });

                                            dialog.show();
                                        } else {
                                            final Dialog dialog = new Dialog(getActivity());
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                            dialog.setContentView(R.layout.dialog_success_failure);
                                            TextView body = dialog.findViewById(R.id.textview_message);
                                            body.setText(response.body().getMessage());
                                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                            typeImageView.setImageResource(R.drawable.ic_failed);
                                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                            messageTitle.setText("Failure..!!");
                                            TextView okButton = dialog.findViewById(R.id.okay_text);
                                            okButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            dialog.show();

//                                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CommonResponseWithoutData> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(getActivity(), "Please Enter valid OTP.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Sorry! Your OTP Not 6 Digit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _type;
                if (type.equalsIgnoreCase("Loan")) {
                    _type = RequestBody.create(MediaType.parse("multipart/form-data"), "2");
                } else if (type.equalsIgnoreCase("Group Loan")) {
                    _type = RequestBody.create(MediaType.parse("multipart/form-data"), "3");
                } else {
                    _type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
                }
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
                                OTP = response.body().getResult();
                            }
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                    }
                });
            }
        });
        return rootView;
    }



}