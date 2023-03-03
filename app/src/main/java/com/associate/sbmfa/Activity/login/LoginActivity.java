package com.associate.sbmfa.Activity.login;

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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.associate.sbmfa.R;
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

import java.util.HashMap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button sendbtn;
    EditText editTextPhone;
//    ProgressBar progressBar;
    SessionManager sessionManager;
    GoogleProgressDialog googleProgressDialog;
    HashMap<String, String> UserDataToken;
    String currentVersion = "";
    String android_id = "";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        googleProgressDialog = new GoogleProgressDialog(this);
        sendbtn = (Button) findViewById(R.id.sendbtn);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
//        progressBar = (ProgressBar) findViewById(R.id.spin_kit);

        FCMtoken();
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String member_id = editTextPhone.getText().toString();
                if (member_id.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Client ID ", Toast.LENGTH_SHORT).show();
                } else {
                    SendOtp(member_id);
                }
            }
        });
        sessionManager = new SessionManager(this);
        UserDataToken = sessionManager.getUserDetails();
        sessionManager.logoutToken();
    }

    ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
    public void SendOtp(final String member_id) {
        googleProgressDialog.show1("OTP Sending.....");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _deviceId = RequestBody.create(MediaType.parse("multipart/form-data"), android_id);
        Call<OTPSendResponse> applicationsListResponesCall = RestHandler.getApiService().OTP_SEND_RESPONES_CALL(_member_id,_deviceId);
        applicationsListResponesCall.enqueue(new Callback<OTPSendResponse>() {
            @Override
            public void onResponse(Call<OTPSendResponse> call, Response<OTPSendResponse> response) {
                googleProgressDialog.dismiss();
                if (response.body().getCode() != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(LoginActivity.this);
                            dialog.checkStatus();
                        } else {
                            try {
                                currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (currentVersion.equals(response.body().getVersionCode())) {
                                Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                sessionManager.TokenSession(response.body().getToken());
                                Bundle bundle = new Bundle();
                                bundle.putString("member_id", member_id);
                                Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                                alertDialog.show();
                            }

//                             checkUpdate(member_id,response.body().getToken(),response.body().getMessages());
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OTPSendResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "" + t, Toast.LENGTH_SHORT).show();
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
                String newToken = instanceIdResult.getToken();
                Log.e("tokennew", "" + instanceIdResult.getToken());
            }
        });

    }
    ///////////////////// get FCM TOKEN/////////////////

    //////////////////// Uodate app on play store /////////////////
    private void checkUpdate(final String memberId, final String token, final String messages) {
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        new CheckIsUpdateReady("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en", new UrlResponce() {
            @Override
            public void onReceived(String resposeStr) {
                if (currentVersion.equals(resposeStr)) {
                    Toast.makeText(LoginActivity.this, messages, Toast.LENGTH_SHORT).show();
                    sessionManager.TokenSession(token);
                    Bundle bundle = new Bundle();
                    bundle.putString("member_id", memberId);
                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                    alertDialog.show();
                }
            }
        }).execute();

    }
    //////////////////// Uodate app on play store /////////////////

}