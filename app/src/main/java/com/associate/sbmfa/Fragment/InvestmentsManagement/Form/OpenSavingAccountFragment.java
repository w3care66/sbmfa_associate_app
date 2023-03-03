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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberDetailsInvestmentRegisterResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OpenSavingAccountFragment extends Fragment {

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
    String token="",relationf="Select Relationship*",plan_id="";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply;
    EditText memberIded,agentsearch;
    RecyclerView recyclerView;
    TextView nameTv,addressTv,memberidTv,categorytv,memberidprooftv,agnametv,agnumbertv,agcaretv;
    EditText nameed;
    String savingAmount,Serachmemberid;
    ConstraintLayout memberDetailslv;
    LinearLayout agentlv;
    CheckBox yescheck;
    ArrayList<String> ReleationArrr =new ArrayList<>();

    final ArrayList<String> TenureArrr =new ArrayList<>();
    ArrayList<String> branchlist =new ArrayList<>();
    ArrayList<String> branchlistID =new ArrayList<>();
    Spinner branchspinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_open_saving_account, container, false);
        Tenure=view.findViewById(R.id.Tenure);
        Relation=view.findViewById(R.id.spinner6);
        yescheck=view.findViewById(R.id.yescheck);
        nameed= view.findViewById(R.id.editTextTextPersonName20);
        dob= view.findViewById(R.id.editTextTextPersonName21);
        age=view.findViewById(R.id.editTextTextPersonName22);
        radioButtonmale=view.findViewById(R.id.radioButton);
        radioButtonfemale=view.findViewById(R.id.radioButton2);
        sradioButtonmale=view.findViewById(R.id.sradioButton);
        sradioButtonfemale=view.findViewById(R.id.sradioButton2);
        secondLay=view.findViewById(R.id.constraintLayout111);
        srelation=view.findViewById(R.id.srelation);
        sdob= view.findViewById(R.id.sdobtv);
        sage=view.findViewById(R.id.sagetv);
        percentage=view.findViewById(R.id.editTextTextPersonName221);
        sparcentage=view.findViewById(R.id.sPercentage);
        addNominne=view.findViewById(R.id.button);
        branchspinner=view.findViewById(R.id.branchspinner);

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


        plan_id=getArguments().getString("plan_code");
        Serachmemberid=getArguments().getString("Serachmemberid");

//        TenureArrr.add("Select Tenure");
//
//
//        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, TenureArrr);
//        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
//        Tenure.setAdapter(adapterselectDate);
//        Tenure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });




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

        getBranchList(member_id,token);


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
        String ageS = ageInt.toString();

        return ageS;
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
    void getBranchList(String assciate_no,String token) {
//        googleProgressDialog.show1("Loading...");
//
//        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
//        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
//        Call<BranchResponse> applicationsListResponesCall = RestHandler.getApiService().Branch_list_Register_RESPONES_CALL(_assciate_no,_token);
//        applicationsListResponesCall.enqueue(new Callback<BranchResponse>() {
//            @Override
//            public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
//                googleProgressDialog.dismiss();
//                if (response != null) {
//                    if (response.body().getCode() == 200) {
                         /* if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }*/
//                        InvestmenBrachResult result = response.body().getResult();
//                        List<Branch> branch1 = result.getBranch();
//                        savingAmount=response.body().getResult().getSsb_fix_detail().getAmount().toString();
//                        for (Branch Item : branch1) {
//                            branchlist.add(Item.getName());
//                            branchlistID.add(String.valueOf(Item.getId()));
//                        }
//                        ArrayAdapter<String> adapterselectDate2 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, branchlist);
//                        adapterselectDate2.setDropDownViewResource(R.layout.spiner_item);
//                        branchspinner.setAdapter(adapterselectDate2);
//                        branchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                Log.e("Position",""+position);
//                                if (position == 0){
//                                    branchStr = "";
//                                }else {
//                                    branchStr = branchlist.get(position);
//                                }
//
//                            }
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//                            }
//                        });
//
//                        googleProgressDialog.dismiss();
//
//                    } else {
//                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                        googleProgressDialog.dismiss();
//                    }
//                } else {
//                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                    googleProgressDialog.dismiss();
//                }
//            }
//            @Override
//            public void onFailure(Call<BranchResponse> call, Throwable t) {
//                googleProgressDialog.dismiss();
//                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    }
}