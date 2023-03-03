package com.associate.sbmfa.Activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.OTPSendResponse;
import com.associate.sbmfa.Respones.OtpVerifyResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    Button sendbtn;
    OtpTextView otp_view;
    ProgressBar progressBar;
    TextView resend;
    HashMap<String ,String > UserDataToken;
    SessionManager sessionManager;
    String token="";
    GoogleProgressDialog googleProgressDialog;
    private String FCMToken = "";
    String android_id = "";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sendbtn=(Button)findViewById(R.id.sendbtn);
        otp_view=(OtpTextView) findViewById(R.id.otp_view);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        resend=(TextView)findViewById(R.id.textView7);
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        googleProgressDialog = new GoogleProgressDialog(this);
        FCMtoken();
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String member_id = bundle.getString("member_id");
        }
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=otp_view.getOTP();
                if(otp.equals("")){
                    Toast.makeText(OtpActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }else {
                    if (otp.length() == 6){
                        if (bundle != null) {
                            String member_id = bundle.getString("member_id");
                            VerifyOtp(member_id, otp,token);
                        }
                    }else {
                        Toast.makeText(OtpActivity.this, "Sorry! Your OTP Not 6 Digit", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle != null) {
                    String member_id = bundle.getString("member_id");
                    SendOtp(member_id);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
            Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
    }
    ////////////////////// get FCM TOKEN/////////////////
    private void FCMtoken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                // notiCount();
                FCMToken = instanceIdResult.getToken();
                Log.e("tokennew",""+instanceIdResult.getToken());
            }
        });

    }
    ///////////////////// get FCM TOKEN/////////////////
     ////////////////////////VerifyOtp////////////////////////////VerifyOtp////////////////////////////////////////////VerifyOtp////////////
            public void VerifyOtp(final String member_id, String otpValue, String token){
                googleProgressDialog.show1("OTP Verify...");
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _otp = RequestBody.create(MediaType.parse("multipart/form-data"), otpValue);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _FCMToken = RequestBody.create(MediaType.parse("multipart/form-data"), FCMToken);

                Call<OtpVerifyResponse> applicationsListResponesCall = RestHandler.getApiService().Otp_Varified_RESPONES_CALL(_member_id,_otp,_token,_FCMToken);
                applicationsListResponesCall.enqueue(new Callback<OtpVerifyResponse>() {
                    @Override
                    public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                        Log.e("data",""+response.body().getCode());
                        googleProgressDialog.dismiss();
                        if (response.body().getCode()!=null){
                             if (response.body().getCode() == 200 ){
                                 if(response.body().getAssociateStatus() == 0){
                                     AssociateStatusDialog dialog = new AssociateStatusDialog(OtpActivity.this);
                                     dialog.checkStatus();
                                 }else {
                                     Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                     Bundle bundle = new Bundle();
                                     bundle.putString("member_id",member_id);
                                     Intent intent = new Intent(OtpActivity.this, SetPinActivity.class);
                                     intent.putExtras(bundle);
                                     startActivity(intent);
                                     finish();
                                 }

                            }else {
                                Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                        googleProgressDialog.dismiss();
                        Toast.makeText(OtpActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
    ////////////////////////VerifyOtp////////////////////////////VerifyOtp////////////////////////////////////////////VerifyOtp////////////

    ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
    public  void SendOtp(final String member_id) {
        googleProgressDialog.show1("OTP Sending.....");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _deviceId = RequestBody.create(MediaType.parse("multipart/form-data"), android_id);
        Call<OTPSendResponse> applicationsListResponesCall = RestHandler.getApiService().OTP_SEND_RESPONES_CALL(_member_id, _deviceId);
        applicationsListResponesCall.enqueue(new Callback<OTPSendResponse>() {
            @Override
            public void onResponse(Call<OTPSendResponse> call, Response<OTPSendResponse> response) {
                googleProgressDialog.dismiss();
                if (response.body().getCode() != null) {
                     if (response.body().getCode() == 200) {
                          /*if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }*/
                         Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                         sessionManager.TokenSession(response.body().getToken());
                        /* Bundle bundle = new Bundle();
                         bundle.putString("member_id", member_id);
                         Intent intent = new Intent(OtpActivity.this, OtpActivity.class);
                         intent.putExtras(bundle);
                         startActivity(intent);
                         finish();*/
                    } else {
                        Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(OtpActivity.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
    }
}