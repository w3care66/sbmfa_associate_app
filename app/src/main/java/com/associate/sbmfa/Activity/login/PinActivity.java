package com.associate.sbmfa.Activity.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Respones.ResultLogin;
import com.associate.sbmfa.Activity.DashBordActivity;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.LoginResponse;
import com.associate.sbmfa.Respones.Member;
import com.associate.sbmfa.Respones.OTPSendResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.CheckIsUpdateReady;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.associate.sbmfa.Utils.UrlResponce;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinActivity extends AppCompatActivity {
    Button sendbtn;
//    TextView memberid;
    OtpTextView otp_view,re_otp_view;
    ProgressBar progressBar;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    TextView textViewForgot;
    private String currentVersion = "";
    private String FCMToken = "";
    String android_id = "";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        sendbtn=(Button) findViewById(R.id.sendbtn);
//        memberid=(TextView) findViewById(R.id.memberid);
        otp_view=(OtpTextView)findViewById(R.id.otp_view);
        textViewForgot= findViewById(R.id.textView48);
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        googleProgressDialog = new GoogleProgressDialog(this);
        FCMtoken();
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        textViewForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOtp(member_id);
            }
        });
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin=otp_view.getOTP();
                if(pin.equals("")) {
                    Toast.makeText(PinActivity.this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                }else {
                    if (pin.length() == 6){
                        pinVerfiy(member_id,pin,token);
                    }else {
                        Toast.makeText(PinActivity.this, "Sorry! Your Pin Not 6 Digit", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
    public void SendOtp(final String member_id){
        googleProgressDialog.show1("OTP Sending.....");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _deviceId = RequestBody.create(MediaType.parse("multipart/form-data"), android_id);
        Call<OTPSendResponse> applicationsListResponesCall = RestHandler.getApiService().OTP_SEND_RESPONES_CALL(_member_id, _deviceId);
        applicationsListResponesCall.enqueue(new Callback<OTPSendResponse>() {
            @Override
            public void onResponse(Call<OTPSendResponse> call, Response<OTPSendResponse> response) {
                googleProgressDialog.dismiss();
                if (response.body().getCode()!=null){
                    if (response.body().getCode() == 200 ){
                        Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        sessionManager.TokenSession(response.body().getToken());
                        Bundle bundle = new Bundle();
                        bundle.putString("member_id",member_id);
                        Intent intent = new Intent(PinActivity.this, OtpActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(PinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////

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
    public  void pinVerfiy(final String member_id, final String upi_value, String token){
        googleProgressDialog.show1("Pin Verify...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _upi = RequestBody.create(MediaType.parse("multipart/form-data"), upi_value);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _FCMToken = RequestBody.create(MediaType.parse("multipart/form-data"), FCMToken);
        RequestBody _currentVersion = RequestBody.create(MediaType.parse("multipart/form-data"), currentVersion);
        Call<LoginResponse> applicationsListResponesCall = RestHandler.getApiService().VERIFY_PIN_RESPONES_CALL(_member_id,_upi,_token,_FCMToken,_currentVersion);
        applicationsListResponesCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                googleProgressDialog.dismiss();
                if (response!=null){
                    if (response.body().getCode() == 200 ){
                        if(response.body().getAssociateStatus() == 0){
                            Toast.makeText(PinActivity.this, "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            AssociateStatusDialog dialog = new AssociateStatusDialog(PinActivity.this);
                            dialog.checkStatus();
                        }else {
                            Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            ResultLogin result = response.body().getResult();
                            Member member = result.getMember();
                            sessionManager.setUserDetails(member);
                            sessionManager.createLoginSession(member_id,upi_value);

                            Intent intent = new Intent(PinActivity.this, DashBordActivity.class);
                            startActivity(intent);
                            finish();
//                            checkUpdate();
                        }
                    }else {
                        Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(PinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //////////////////// Uodate app on play store /////////////////
    private void checkUpdate() {
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        new CheckIsUpdateReady("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en", new UrlResponce() {
            @Override
            public void onReceived(String resposeStr) {
                if (currentVersion.equals(resposeStr)){
                    Intent intent = new Intent(PinActivity.this, DashBordActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PinActivity.this).create();
                    alertDialog.setTitle("Update");
                    alertDialog.setIcon(R.drawable.logo_icon);
                    alertDialog.setMessage("New version is available on Play store. Please update.");
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                            }
                        }
                    });
                    /*alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/
                    alertDialog.show();
                }
            }
        }).execute();
    }
    //////////////////// Uodate app on play store /////////////////
}