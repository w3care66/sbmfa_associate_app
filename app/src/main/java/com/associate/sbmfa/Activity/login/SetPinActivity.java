package com.associate.sbmfa.Activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.OtpVerifyResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.HashMap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPinActivity extends AppCompatActivity {

    Button sendbtn;
    TextView memberid;
    OtpTextView otp_view,re_otp_view;
    ProgressBar progressBar;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    GoogleProgressDialog googleProgressDialog;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);
        sendbtn=(Button) findViewById(R.id.sendbtn);
        memberid=(TextView) findViewById(R.id.memberid);
        otp_view=(OtpTextView)findViewById(R.id.otp_view);
        re_otp_view=(OtpTextView)findViewById(R.id.otp_view2);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        googleProgressDialog = new GoogleProgressDialog(this);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
              member_id = bundle.getString("member_id");
              memberid.setText(member_id);
        }
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String pin=otp_view.getOTP();
                    String re_pin=re_otp_view.getOTP();
                    if(pin.equals("")) {
                        Toast.makeText(SetPinActivity.this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(re_pin.equals(pin)) {
                            if (pin.length() == 6){
                                pinVerfiy(member_id,pin,token);
                            }else {
                                Toast.makeText(SetPinActivity.this, "Sorry! Your Pin Not 6 Digit", Toast.LENGTH_SHORT).show();
                            }
                        }else  {
                            Toast.makeText(SetPinActivity.this, "Sorry! Your Pin Not Match", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }
    public  void pinVerfiy(final String member_id, final String upi_value, String token){
        googleProgressDialog.show1("Create Security pin....");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _upi = RequestBody.create(MediaType.parse("multipart/form-data"), upi_value);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<OtpVerifyResponse> applicationsListResponesCall = RestHandler.getApiService().SETUP_PIN_RESPONES_CALL(_member_id,_upi,_token);
        applicationsListResponesCall.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                googleProgressDialog.dismiss();
                if (response!=null){
                    if (response.body().getCode() == 200 ){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(SetPinActivity.this);
                            dialog.checkStatus();
                        }else {
                            Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            sessionManager.createLoginSession(member_id,upi_value);
                            Intent intent = new Intent(SetPinActivity.this, PinActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }else {
                        Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(SetPinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}