package com.associate.sbmfa.Fragment.InvestmentsManagement.Form;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.Response.InvestmentSavingAccountPlanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.InvestmentPlanDetailsFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MatuirtyKanyadhanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityKanyadhanYojanaResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.Calendar;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SamraddhKanyadhanYojanaFragment extends Fragment {



View view;
TextView dob,age;
private int mYear, mMonth, mDay;
String sdobdate;

    String Serachmemberid="",searchagent="";


    SessionManager sessionManager;
    String member_id="" ;
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;

    GoogleProgressDialog googleProgressDialog;

    TextView tenunre,monthlyAmount;
    EditText formno,relguar,daughter,phone;
    String savingAmount,plan_code,ssbaccount;
    Button submit;
    LinearLayout matuirtylv;
    private String branch_id,plan_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_samraddh_kanyadhan_yojana, container, false);

        Serachmemberid=getArguments().getString("memberId");
        searchagent=getArguments().getString("agentId");

        plan_code=getArguments().getString("plan_code");
        savingAmount=getArguments().getString("savingAmount");
        ssbaccount=getArguments().getString("ssbaccount");
        Serachmemberid=getArguments().getString("Serachmemberid");


        branch_id=getArguments().getString("branchId");
        plan_id=getArguments().getString("planId");
        dob= view.findViewById(R.id.dob);

        formno= view.findViewById(R.id.FormNumber);
        relguar= view.findViewById(R.id.Relationwithguardians);
        daughter= view.findViewById(R.id.Daughtername);
        phone= view.findViewById(R.id.PhoneNumber);

        monthlyAmount= view.findViewById(R.id.MonthlyDepositAmount);
        tenunre= view.findViewById(R.id.Tenure);
        age=view.findViewById(R.id.age);
        submit=view.findViewById(R.id.submit);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCalender = Calendar.getInstance();
                mYear = mCalender.get(Calendar.YEAR);
                mMonth = mCalender.get(Calendar.MONTH);
                mDay = mCalender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                int monthOfYear1 = monthOfYear + 1;
                                sdobdate = "" + dayOfMonth + "/" + monthOfYear1 + "/" + year;
                                dob.setText(sdobdate);

                                age.setText(getAge(year,monthOfYear1,dayOfMonth));
                                getMadiriydata(plan_code,age.getText().toString());
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();

            }
        });
        ImageView imageViewBack = view.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });


        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        //final String plan_code=getArguments().getString("plan_code");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formSubmit(member_id,
                        token,
                        searchagent,
                        Serachmemberid,
                        branch_id,
                        plan_id,
                        dob.getText().toString(),
                        age.getText().toString(),
                        formno.getText().toString(),
                        relguar.getText().toString(),
                        daughter.getText().toString(),
                        phone.getText().toString()

                );
            }


        });

        return view;
    }
    private void getMadiriydata(String plan_code,String age) {
        if(Integer.parseInt(age) < 0){
            age="0";
        }
        googleProgressDialog.show1("Loading...");
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _plan_code = RequestBody.create(MediaType.parse("multipart/form-data"), plan_code);
        RequestBody _age = RequestBody.create(MediaType.parse("multipart/form-data"), age);
        Call<MaturityKanyadhanYojanaResponse> applicationsListResponesCall = RestHandler.getApiService().Maduirty_Kanyana_Dhyan_RESPONES_CALL(_member_id,_token,_plan_code,_age);
        applicationsListResponesCall.enqueue(new Callback<MaturityKanyadhanYojanaResponse>() {
            @Override
            public void onResponse(Call<MaturityKanyadhanYojanaResponse> call, Response<MaturityKanyadhanYojanaResponse> response) {
                if (response!=null){

                    if (response.body().getCode() == 200){
//                        matuirtylv.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                        MatuirtyKanyadhanResponse maturityResult=response.body().getResult().getMaturityDetail();
                        monthlyAmount.setText(maturityResult.getMaturityAmount().toString());
                        tenunre.setText(maturityResult.getTenure().toString());


                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }else {
                        googleProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MaturityKanyadhanYojanaResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), ""+t.toString(), Toast.LENGTH_SHORT).show();

                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS="";
        if(ageInt >= 0){
            ageS = ageInt.toString();
        }else{
            ageS = "0";
        }

        return ageS;
    }
     void formSubmit(String member_id, String token, String searchagent, String serachmemberid, String branch_id, String plan_id, String dob,String age, String form_number, String relgouar, String daughterName, String phone) {
     try{
        googleProgressDialog.show1("Loading...");
        RequestBody _associate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _agent_code = RequestBody.create(MediaType.parse("multipart/form-data"), searchagent);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), serachmemberid);
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
        RequestBody _form_number = RequestBody.create(MediaType.parse("multipart/form-data"), form_number);
        RequestBody _plan_id = RequestBody.create(MediaType.parse("multipart/form-data"), plan_id);
        RequestBody _dob = RequestBody.create(MediaType.parse("multipart/form-data"), dob);
        RequestBody _age = RequestBody.create(MediaType.parse("multipart/form-data"), age);
        RequestBody _relgouar = RequestBody.create(MediaType.parse("multipart/form-data"), relgouar);
        RequestBody _daughterName = RequestBody.create(MediaType.parse("multipart/form-data"), daughterName);
        RequestBody _phone = RequestBody.create(MediaType.parse("multipart/form-data"), phone);

         Log.e("eeeeeeee","eeeeeeee");
        Call<InvestmentSavingAccountPlanResponse> applicationsListResponesCall = RestHandler.getApiService().
                SUMBIT_SAMRADHHKANYAYOJANA_Investment_Register_RESPONES_CALL(
                        _associate_no,
                        _token,
                        _agent_code,
                        _member_id,
                        _branch_id,
                        _form_number,
                        _plan_id,
                         _dob,
                        _age,
                        _relgouar,
                        _daughterName,
                        _phone);
        applicationsListResponesCall.enqueue(new Callback<InvestmentSavingAccountPlanResponse>() {
            @Override
            public void onResponse(Call<InvestmentSavingAccountPlanResponse> call, Response<InvestmentSavingAccountPlanResponse> response) {
                if (response != null) {
                    if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }
                        Fragment fragment = new InvestmentPlanDetailsFragment();
                        Bundle bundle = new Bundle();
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        googleProgressDialog.dismiss();
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    } else {
                        googleProgressDialog.dismiss();

                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    googleProgressDialog.dismiss();

                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvestmentSavingAccountPlanResponse> call, Throwable t) {
                googleProgressDialog.dismiss();

            }
        });

        }catch (Exception e){
         googleProgressDialog.dismiss();
         Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}