package com.associate.sbmfa.Fragment.InvestmentsManagement.Form;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.Response.InvestmentSavingAccountPlanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.InvestmentPlanDetailsFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityDetail;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityDetailResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberDetailsInvestmentRegisterResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Tenure;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.TenureResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FlexiFixedDepositFragment extends Fragment {

    View view;
    private int mYear, mMonth, mDay;
    Spinner Relation,srelation,Tenure;
    String dobdate,sdobdate;
    TextView dob,age,sdob,sage;
    EditText percentage,sparcentage;
    Button addNominne;
    ConstraintLayout secondLay;
    int hideShowKey=1;
    RadioButton radioButtonmale,radioButtonfemale,sradioButtonmale,sradioButtonfemale;

    int lengthValue=10,page=1;
    SessionManager sessionManager;
    String member_id="",branchStr="",toDateval="",associateId="";
    HashMap<String ,String > UserDataToken;
    String token="",relationf="Select Relationship*",plan_code="",Serachmemberid="";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply;
    EditText memberIded,agentsearch;
    RecyclerView recyclerView;
    TextView nameTv,addressTv,memberidTv,categorytv,memberidprooftv,agnametv,agnumbertv,agcaretv;
    EditText nameed,savingAmount;
    ConstraintLayout memberDetailslv;
    LinearLayout agentlv;
    CheckBox yescheck;
    ArrayList<String> ReleationArrr =new ArrayList<>();

      ArrayList<String> TenureArrr =new ArrayList<>();
    ArrayList<String> TenureArrrid =new ArrayList<>();

    TextView MaturityAmount,InterestRate;
    EditText depositAmount;
    LinearLayout amountlv;
    String tenureval="0";
    List<Tenure> tenure;
    String branch_id="",plan_id="",searchagent="";
    int firstrelation,secondrelation;
    TextView formNo,snameed;
    Button submit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_flexi_fixed_deposit, container, false);
        formNo= view.findViewById(R.id.FormNumber);
        snameed=view.findViewById(R.id.sfirstnameed);
        submit=view.findViewById(R.id.button2);
        Tenure=view.findViewById(R.id.Tenure);
        Relation=view.findViewById(R.id.spinner6);
        dob= view.findViewById(R.id.editTextTextPersonName21);
        age=view.findViewById(R.id.editTextTextPersonName22);
        radioButtonmale=view.findViewById(R.id.radioButton);
        radioButtonfemale=view.findViewById(R.id.radioButton2);
        sradioButtonmale=view.findViewById(R.id.sradioButton);
        sradioButtonfemale=view.findViewById(R.id.sradioButton2);
        secondLay=view.findViewById(R.id.constraintLayout111);
        srelation=view.findViewById(R.id.srelation);
        yescheck=view.findViewById(R.id.checkBox);
        sdob= view.findViewById(R.id.sdobtv);
        sage=view.findViewById(R.id.sagetv);
        percentage=view.findViewById(R.id.editTextTextPersonName221);
        sparcentage=view.findViewById(R.id.sPercentage);
        addNominne=view.findViewById(R.id.button);
        depositAmount=view.findViewById(R.id.DepositAmount);
        amountlv=view.findViewById(R.id.amountlv);
        MaturityAmount=view.findViewById(R.id.MaturityAmount);
        InterestRate=view.findViewById(R.id.InterestRate);

        Relation=view.findViewById(R.id.spinner6);
        nameed= view.findViewById(R.id.editTextTextPersonName20);
        dob= view.findViewById(R.id.editTextTextPersonName21);
        age=view.findViewById(R.id.editTextTextPersonName22);
        radioButtonmale=view.findViewById(R.id.radioButton);
        radioButtonfemale=view.findViewById(R.id.radioButton2);
        sradioButtonmale=view.findViewById(R.id.sradioButton);
        sradioButtonfemale=view.findViewById(R.id.sradioButton2);

        radioButtonmale.setChecked(true);
        radioButtonfemale.setChecked(false);
        sradioButtonmale.setChecked(true);
        sradioButtonfemale.setChecked(false);
        radioButtonmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonmale.setChecked(true);
                radioButtonfemale.setChecked(false);
            }
        });
        radioButtonfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonmale.setChecked(false);
                radioButtonfemale.setChecked(true);
            }
        });
        sradioButtonmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sradioButtonmale.setChecked(true);
                sradioButtonfemale.setChecked(false);
            }
        });
        sradioButtonfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sradioButtonmale.setChecked(false);
                sradioButtonfemale.setChecked(true);
            }
        });


        plan_code=getArguments().getString("plan_code");
        Serachmemberid=getArguments().getString("Serachmemberid"); ;
        searchagent=getArguments().getString("agentId");
        branch_id=getArguments().getString("branchId");
        plan_id=getArguments().getString("planId");

        //Toast.makeText(getActivity(), ""+plan_id, Toast.LENGTH_SHORT).show();
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

        ReleationArrr.add("Select Relationship*");
        ReleationArrr.add("Son");
        ReleationArrr.add("Daughter");
        ReleationArrr.add("Mother");
        ReleationArrr.add("Father");
        ReleationArrr.add("Brother");
        ReleationArrr.add("Sister");
        ReleationArrr.add("Wife");
        ReleationArrr.add("Daughter In low");
        ReleationArrr.add("Nephew");
        ReleationArrr.add("Aunty");
        ReleationArrr.add("Nice");
        ReleationArrr.add("Uncle");
        ReleationArrr.add("Husband");
        ReleationArrr.add("Sister ln low");
        ArrayAdapter<String> adapterselectDate1 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, ReleationArrr);
        adapterselectDate1.setDropDownViewResource(R.layout.spiner_item);
        Relation.setAdapter(adapterselectDate1);
        Relation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstrelation=position;
                if(ReleationArrr.get(position).equals("Son")
                        || ReleationArrr.get(position).equals("Father")
                        || ReleationArrr.get(position).equals("Brother")
                        || ReleationArrr.get(position).equals("Father")
                        || ReleationArrr.get(position).equals("Uncle")
                        || ReleationArrr.get(position).equals("Husband")
                ){
                    radioButtonmale.setChecked(true);
                    radioButtonfemale.setChecked(false);
                }else{
                    radioButtonfemale.setChecked(true);
                    radioButtonmale.setChecked(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                                dobdate = "" + dayOfMonth + "/" + monthOfYear1 + "/" + year;
                                dob.setText(dobdate);

                                age.setText(getAge(year,monthOfYear1,dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();

            }
        });


        ArrayAdapter<String> adapterselectDatee = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, ReleationArrr);
        adapterselectDatee.setDropDownViewResource(R.layout.spiner_item);
        srelation.setAdapter(adapterselectDatee);
        srelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondrelation=position;
                if(ReleationArrr.get(position).equals("Son")
                        || ReleationArrr.get(position).equals("Father")
                        || ReleationArrr.get(position).equals("Brother")
                        || ReleationArrr.get(position).equals("Father")
                        || ReleationArrr.get(position).equals("Uncle")
                        || ReleationArrr.get(position).equals("Husband")
                ){
                    sradioButtonmale.setChecked(true);
                    sradioButtonfemale.setChecked(false);
                }else{
                    sradioButtonfemale.setChecked(true);
                    sradioButtonmale.setChecked(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sdob.setOnClickListener(new View.OnClickListener() {
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
                                sdob.setText(sdobdate);

                                sage.setText(getAge(year,monthOfYear1,dayOfMonth));
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();

            }
        });



        addNominne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hideShowKey == 1) {
                    secondLay.setVisibility(View.VISIBLE);
                    addNominne.setText("Remove");
                    hideShowKey=0;
                }else{
                    secondLay.setVisibility(View.GONE);
                    addNominne.setText("Add Nominee");
                    hideShowKey=1;
                }
            }
        });
        percentage.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    submit_btn.performClick();
//                    return true;
//                }
//                return false;
                //              if(Integer.parseInt(percentage.getText().toString()) > 100){
//                  percentage.setText(0);
//              }else {
                String spercentageval=percentage.getText().toString();
                int val=100 - Integer.parseInt(spercentageval);
                if(101 > Integer.parseInt(spercentageval)) {
                    sparcentage.setText(String.valueOf(val));
                }else{
                    percentage.setText("0");
                    sparcentage.setText("100");
                }

                return  true;
            }


        });

        sparcentage.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    submit_btn.performClick();
//                    return true;
//                }
//                return false;
                //              if(Integer.parseInt(percentage.getText().toString()) > 100){
//                  percentage.setText(0);
//              }else {
                String percentageval=sparcentage.getText().toString();
                int val=100 - Integer.parseInt(percentageval);
                if(101 > Integer.parseInt(percentageval)) {
                    percentage.setText(String.valueOf(val));
                }else{
                    sparcentage.setText("0");
                    percentage.setText("100");
                }
                // }
                return  true;
            }


        });


        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);

        getTenureDetails(member_id,plan_code);
        yescheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Relation.setSelection(0);
                if(b == true){
                    getMemberDetails(member_id,Serachmemberid);
                }else {
                    nameed.setText("");
                    age.setText("");
                    dob.setText("");
                }
            }
        });


        TenureArrr.add("Select Tenure");

        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, TenureArrr);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        Tenure.setAdapter(adapterselectDate);
        Tenure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    tenureval = TenureArrrid.get(position - 1);
                    getMadiriydata(plan_code, depositAmount.getText().toString(),tenureval, "");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        depositAmount.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // Toast.makeText(getActivity(), agentsearch.getText().toString(), Toast.LENGTH_SHORT).show();
                getMadiriydata(plan_code,depositAmount.getText().toString(),tenureval,"");

                return  true;
            }


        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstgender,secondgender;
                if(radioButtonmale.isChecked() == true){
                    firstgender="1";
                }else{
                    firstgender="0";
                }

                if(sradioButtonmale.isChecked() == true){
                    secondgender="1";
                }else{
                    secondgender="0";
                }
                formSubmit(member_id,token,searchagent,Serachmemberid,branch_id,formNo.getText().toString(),plan_id,depositAmount.getText().toString(),
                        nameed.getText().toString(),
                        String.valueOf(firstrelation),
                        dob.getText().toString(),
                        age.getText().toString(),
                        firstgender,
                        percentage.getText().toString(),
                        snameed.getText().toString(),
                        String.valueOf(secondrelation),
                        sdob.getText().toString(),
                        sage.getText().toString(),
                        secondgender,
                        sparcentage.getText().toString()

                );
            }
        });
        
        return  view;
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


    private void getTenureDetails(String member_id,String plan_code) {
        googleProgressDialog.show1("Loading...");
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _plan_code = RequestBody.create(MediaType.parse("multipart/form-data"), plan_code);
        Call<TenureResponse> applicationsListResponesCall = RestHandler.getApiService().GET_TENURE_RESPONES_CALL(_member_id,_plan_code,_token);
        applicationsListResponesCall.enqueue(new Callback<TenureResponse>() {
            @Override
            public void onResponse(Call<TenureResponse> call, Response<TenureResponse> response) {
                if (response != null) {

                    List<Tenure> getTenure =response.body().getResult().getTenure();
                    for (Tenure Item : getTenure) {
                        TenureArrr.add(Item.getName());
                        TenureArrrid.add(Item.getValue().toString());
                    }
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    googleProgressDialog.dismiss();

                }else {
                    googleProgressDialog.dismiss();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TenureResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }
    private void getMemberDetails(String member_id,String id) {
        googleProgressDialog.show1("Loading...");
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        Call<MemberDetailsInvestmentRegisterResponse> applicationsListResponesCall = RestHandler.getApiService().MEMBER_DETAILS_Investment_Register_RESPONES_CALL(_member_id,_id,_token);
        applicationsListResponesCall.enqueue(new Callback<MemberDetailsInvestmentRegisterResponse>() {
            @Override
            public void onResponse(Call<MemberDetailsInvestmentRegisterResponse> call, Response<MemberDetailsInvestmentRegisterResponse> response) {
                if (response!=null){
                    if (response.body().getCode() == 200){
                        googleProgressDialog.dismiss();

                        if(response.body().getResult().getMemberNominees().getName()!=null) {
                            nameed.setText(response.body().getResult().getMemberNominees().getName());
                        }else{
                            nameed.setText("");
                        }
                        if(response.body().getResult().getMemberNominees().getAge()!=null) {
                            age.setText(response.body().getResult().getMemberNominees().getAge().toString());
                        }else{
                            age.setText("");
                        }
                        if(response.body().getResult().getMemberNominees().getDob()!=null) {
                            dob.setText(response.body().getResult().getMemberNominees().getDob());
                        }else{
                            dob.setText("");
                        }
                        if(response.body().getResult().getMemberNominees().getGenderName().equals("Male")) {
                            radioButtonmale.setChecked(true);
                            radioButtonfemale.setChecked(false);
                        }else{
                            radioButtonfemale.setChecked(true);
                            radioButtonmale.setChecked(false);
                        }
                        relationf=ReleationArrr.get(response.body().getResult().getMemberNominees().getRelation());
                        int relationPosssition = 0;
                        for(int i = 0; i<ReleationArrr.size(); i++){
                            if(ReleationArrr.get(i).equals(relationf)){
                                relationPosssition=i;
                            }
                        }
                        Relation.setSelection(relationPosssition);
//                        if(response.body().getResult().getMemberDetail().getCategory()!=null) {
//                            categorytv.setText(response.body().getResult().getMemberDetail().getCategory().toString());
//                        }else{
//                            categorytv.setText("");
//                        }
//                        if(response.body().getResult().getMemberDetail().getMemberIdProof()!=null) {
//                            memberidprooftv.setText(response.body().getResult().getMemberDetail().getMemberIdProof().toString());
//                        }else{
//                            memberidprooftv.setText("");
//                        }

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
            public void onFailure(Call<MemberDetailsInvestmentRegisterResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }

    private void getMadiriydata(String plan_code,String amount,String tenure,String category) {
        googleProgressDialog.show1("Loading...");
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _plan_code = RequestBody.create(MediaType.parse("multipart/form-data"), plan_code);
        RequestBody _amount = RequestBody.create(MediaType.parse("multipart/form-data"), amount);
        RequestBody _tenure = RequestBody.create(MediaType.parse("multipart/form-data"), tenure);
        RequestBody _category = RequestBody.create(MediaType.parse("multipart/form-data"), category);
        Call<MaturityDetailResponse> applicationsListResponesCall = RestHandler.getApiService().Maduirty_Filed_Investment_Register_RESPONES_CALL(_member_id,_token,_plan_code,_amount,_tenure,_category);
        applicationsListResponesCall.enqueue(new Callback<MaturityDetailResponse>() {
            @Override
            public void onResponse(Call<MaturityDetailResponse> call, Response<MaturityDetailResponse> response) {
                if (response!=null){
                    if (response.body().getCode() == 200){
                        amountlv.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                        MaturityDetail maturityResult=response.body().getResult().getMaturityDetail();
                        MaturityAmount.setText(maturityResult.getMaturityAmount().toString());
                      //  InterestRate.setText(maturityResult.getInterestRate().toString());

                        //  relationf=ReleationArrr.get(response.body().getResult().getMemberNominees().getRelation());
//                        if(response.body().getResult().getMemberDetail().getCategory()!=null) {
//                            categorytv.setText(response.body().getResult().getMemberDetail().getCategory().toString());
//                        }else{
//                            categorytv.setText("");
//                        }
//                        if(response.body().getResult().getMemberDetail().getMemberIdProof()!=null) {
//                            memberidprooftv.setText(response.body().getResult().getMemberDetail().getMemberIdProof().toString());
//                        }else{
//                            memberidprooftv.setText("");
//                        }

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
            public void onFailure(Call<MaturityDetailResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }
    void formSubmit(String associate_no, String token, String  agent_code,  String member_id, String branch_id,
                    String form_number,
                    String plan_code,
                    String amount,
                    String first_nominee_name,
                    String first_nominee_relation,
                    String  first_nominee_dob,
                    String  first_nominee_age,
                    String   first_nominee_gender,
                    String  first_nominee_percentage,
                    String  second_nominee_name,
                    String  second_nominee_relation,
                    String  second_nominee_dob,
                    String  second_nominee_age,
                    String  second_nominee_gender,
                    String second_nominee_percentage

    ){
        try {
            if(Integer.parseInt(first_nominee_age) < 0){
                first_nominee_age="0";

            }
            if(Integer.parseInt(second_nominee_age) < 0){
                second_nominee_age="0";
            }
            googleProgressDialog.show1("Loading...");
            RequestBody _associate_no = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(associate_no));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _agent_code = RequestBody.create(MediaType.parse("multipart/form-data"), agent_code);
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
            RequestBody _form_number = RequestBody.create(MediaType.parse("multipart/form-data"), form_number);
            RequestBody _plan_code = RequestBody.create(MediaType.parse("multipart/form-data"), plan_code);
            RequestBody _amount = RequestBody.create(MediaType.parse("multipart/form-data"), amount);
            RequestBody _first_nominee_name = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_name);
            RequestBody _first_nominee_relation = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_relation);
            RequestBody _first_nominee_dob = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_dob);
            RequestBody _first_nominee_age = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_age);
            RequestBody _first_nominee_gender = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_gender);
            RequestBody _first_nominee_percentage = RequestBody.create(MediaType.parse("multipart/form-data"), first_nominee_percentage);
            RequestBody _second_nominee_name = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_name);
            RequestBody _second_nominee_relation = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_relation);
            RequestBody _second_nominee_dob = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_dob);
            RequestBody _second_nominee_age = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_age);
            RequestBody _second_nominee_gender = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_gender);
            RequestBody _second_nominee_percentage = RequestBody.create(MediaType.parse("multipart/form-data"), second_nominee_percentage);
            RequestBody _tenure= RequestBody.create(MediaType.parse("multipart/form-data"), tenureval);
            Call<InvestmentSavingAccountPlanResponse> applicationsListResponesCall = RestHandler.getApiService().SUMBIT_FLEXI_FIXED_Investment_Register_RESPONES_CALL(_associate_no, _token, _agent_code, _member_id, _branch_id, _form_number, _plan_code, _amount, _first_nominee_name
                    , _first_nominee_relation, _first_nominee_dob, _first_nominee_age, _first_nominee_gender, _first_nominee_percentage, _second_nominee_name,
                    _second_nominee_relation, _second_nominee_dob, _second_nominee_age, _second_nominee_gender, _second_nominee_percentage,_tenure
            );
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
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        googleProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InvestmentSavingAccountPlanResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            });

        }catch (Exception e){
            googleProgressDialog.dismiss();
            Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}