package com.associate.sbmfa.Fragment.InvestmentsManagement;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.InvestmentRegisterPlanGridListAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.DailyDepositFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.FixedDepositFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.FlexRecurringDepositeFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.FlexiFixedDepositFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.MoneyBackFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.MonthlyIncomeSchemeFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.RecurringDepositFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.SamraddhBhavhishyaFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.SamraddhJeevanFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.SamraddhKanyadhanYojanaFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.SavingAccountInvestmentPlanFormFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalPlanModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Branch;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.BranchResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmenBrachResult;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberAgentResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberDetailsInvestmentRegisterResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Plan;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InvestmentPlanRegisterFragment extends Fragment implements com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.InvestmentRegisterPlanGridListAdapter.EventListener {
    View Rootview;
    Spinner spinner;
    ArrayList<String> dateStrings =new ArrayList<>();
    Button button;
    Spinner spinner3,spinner4,spinner5;
    ArrayList<String> dateStrings3 =new ArrayList<>();
    ArrayList<String> dateStrings4 =new ArrayList<>();
    ArrayList<String> dateStrings5 =new ArrayList<>();
    ImageView imageViewBack;
    Spinner spinnerr;
    ArrayList<String> dateStringss =new ArrayList<>();
    TextView textViewDoB;
    private int mYear, mMonth, mDay;
    String dateFrom,dateto;
    InvestmentRegisterPlanGridListAdapter InvestmentRegisterPlanGridListAdapter;
    ArrayList<RenewalPlanModel> renewalPlanModels =new ArrayList<>();

    ArrayList<String> branchlist =new ArrayList<>();
    ArrayList<String> branchlistID =new ArrayList<>();

    int lengthValue=10,page=1;
    SessionManager sessionManager;
    String member_id="",branchStr="",toDateval="",ssbaccount="";
    HashMap<String ,String > UserDataToken;
    String token="",savingAmount;
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply;
    EditText memberIded,agentsearch;
    RecyclerView recyclerView;
    TextView nameTv,addressTv,memberidTv,categorytv,memberidprooftv,agnametv,agnumbertv,agcaretv,notfound,phonetv;
    ConstraintLayout memberDetailslv;
    LinearLayout agentlv;
    public static String idd="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Rootview = inflater.inflate(R.layout.fragment_investment_plan_register, container, false);
        spinner = Rootview.findViewById(R.id.spinner2);
        spinnerr = Rootview.findViewById(R.id.spinner6);
        textViewDoB = Rootview.findViewById(R.id.editTextTextPersonName21);
        recyclerView=Rootview.findViewById(R.id.recyclerView);

        nameTv = Rootview.findViewById(R.id.textView44);
        addressTv = Rootview.findViewById(R.id.textView46);
        memberidTv = Rootview.findViewById(R.id.textView47);
        categorytv= Rootview.findViewById(R.id.textView49);
        phonetv= Rootview.findViewById(R.id.textView48);
        memberidprooftv= Rootview.findViewById(R.id.textView50);
        memberIded= Rootview.findViewById(R.id.editTextTextPersonName);
        memberDetailslv= Rootview.findViewById(R.id.memberlv);
        agentlv= Rootview.findViewById(R.id.agentlv);
        agentsearch= Rootview.findViewById(R.id.agentsearch);
        agnametv= Rootview.findViewById(R.id.agname);
        agnumbertv= Rootview.findViewById(R.id.agnumber);
        agcaretv= Rootview.findViewById(R.id.agdesination);
        notfound= Rootview.findViewById(R.id.notfound);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment=new InvestmentPlanRegister2Fragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        recyclerView.setVisibility(View.GONE);
        dateStrings.add("Select Branch");

        spinner3 = Rootview.findViewById(R.id.spinner3);
        spinner4 = Rootview.findViewById(R.id.spinner4);
        spinner5 = Rootview.findViewById(R.id.spinner5);
        button = Rootview.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment=new InvestmentPlanRegister3Fragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        dateStrings3.add("Select Plan");
        dateStrings3.add("Saving");
        dateStrings3.add("Fix Deposit");
        dateStrings3.add("Daily Deposit");
        dateStrings3.add("Renew Deposit");
        ArrayAdapter<String> adapterdeposit = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings3);
        adapterdeposit.setDropDownViewResource(R.layout.spiner_item);
        spinner3.setAdapter(adapterdeposit);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateStrings4.add("Select Tenure*");
        dateStrings4.add("18 months");
        dateStrings4.add("24 months");
        dateStrings4.add("36 months");
        dateStrings4.add("42 months");
        ArrayAdapter<String> adapterdTenure = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings4);
        adapterdTenure.setDropDownViewResource(R.layout.spiner_item);
        spinner4.setAdapter(adapterdTenure);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateStrings5.add("Select Mode*");
        dateStrings5.add("Cash");
        dateStrings5.add("Cheque");
        dateStrings5.add("online transaction");
        dateStrings5.add("SSB Account");
        ArrayAdapter<String> adapterdpayMode = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings5);
        adapterdpayMode.setDropDownViewResource(R.layout.spiner_item);
        spinner5.setAdapter(adapterdpayMode);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        textViewDoB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mYear = 1980;
//                mMonth = Calendar.JANUARY;
//                mDay = 1;
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                int monthOfYear1 = monthOfYear + 1;
//                                dateFrom = "" + dayOfMonth + "-" + monthOfYear1 + "-" + year;
//                                textViewDoB.setText(dateFrom);
//                            }
//                        }, mYear, mMonth, mDay);
//
//                final Calendar cd = Calendar.getInstance();
//                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
//                datePickerDialog.show ();
//            }
//        });

//        dateStringss.add("Select Relation");
//        dateStringss.add("Son");
//        dateStringss.add("Brother");
//        dateStringss.add("Mother");
//        dateStringss.add("Father");
//
//        ArrayAdapter<String> adapterdepositt = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
//        adapterdepositt.setDropDownViewResource(R.layout.spiner_item);
//        spinnerr.setAdapter(adapterdepositt);
//        spinnerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



        imageViewBack = Rootview.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        Rootview.setFocusableInTouchMode(true);
        Rootview.requestFocus();
        Rootview.setOnKeyListener(new View.OnKeyListener() {
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



      //  getMemberDetails(member_id,memberIded.getText().toString());
        memberIded.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                idd = memberIded.getText().toString();
                getMemberDetails(member_id,memberIded.getText().toString());

                return  true;
            }


        });
        agentsearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // Toast.makeText(getActivity(), agentsearch.getText().toString(), Toast.LENGTH_SHORT).show();
                getAgentDetails(member_id,agentsearch.getText().toString());

                return  true;
            }


        });
        recyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL, false));
        InvestmentRegisterPlanGridListAdapter = new InvestmentRegisterPlanGridListAdapter(getActivity(),renewalPlanModels,InvestmentPlanRegisterFragment.this);
        recyclerView.setAdapter(InvestmentRegisterPlanGridListAdapter);
        getBranchList(member_id,token);
        return Rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (idd.isEmpty()){

        }else {
            getMemberDetails(member_id,idd);
        }

      /*  recyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL, false));
        InvestmentRegisterPlanGridListAdapter = new InvestmentRegisterPlanGridListAdapter(getActivity(),renewalPlanModels,InvestmentPlanRegisterFragment.this);
        recyclerView.setAdapter(InvestmentRegisterPlanGridListAdapter);
        getBranchList(member_id,token);*/
    }

    public  void getBranchList(final String assciate_no, final String token){
        googleProgressDialog.show1("Loading data...");
        renewalPlanModels.clear();
        branchlist.clear();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<BranchResponse> applicationsListResponesCall = RestHandler.getApiService().Branch_list_Register_RESPONES_CALL(_assciate_no,_token);
        applicationsListResponesCall.enqueue(new Callback<BranchResponse>() {
            @Override
            public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                        InvestmenBrachResult result = response.body().getResult();
                        List<Branch> branch1 = result.getBranch();
                        savingAmount=response.body().getResult().getSsb_fix_detail().getAmount().toString();
                        branchlist.add("Select branch");
                        for (Branch Item : branch1) {
                            branchlist.add(Item.getName());
                            branchlistID.add(String.valueOf(Item.getId()));
                        }
                        ArrayAdapter<String> adapterselectDate2 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, branchlist);
                        adapterselectDate2.setDropDownViewResource(R.layout.spiner_item);
                        spinner.setAdapter(adapterselectDate2);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.e("Position",""+position);
                                if (position == 0){
                                    branchStr = "";
                                }else {
                                    branchStr = branchlist.get(position);
                                }

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        ssbaccount=result.getSsbDetail().getAccountNo();
                        List<Plan> plan = result.getPlan();
                        for (int i =0;i< plan.size(); i++){

                            renewalPlanModels.add(new RenewalPlanModel(
                                    String.valueOf(plan.get(i).getId()),
                                    plan.get(i).getName(),
                                    String.valueOf(plan.get(i).getId()),
                                    String.valueOf(plan.get(i).getId()),
                                    String.valueOf(plan.get(i).getPlanCode())
                            ));

                        }

                        InvestmentRegisterPlanGridListAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BranchResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEvent_CardClick(String id, String amount,String plan_code) {
        //  Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
        if(id.equals("0")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new SavingAccountInvestmentPlanFormFragment();
                Bundle bundle = new Bundle();
                bundle.putString("memberId", memberIded.getText().toString());
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);

                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("1")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new SamraddhKanyadhanYojanaFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("id", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("2")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new MoneyBackFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("3")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new FlexiFixedDepositFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("4")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new FlexRecurringDepositeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("5")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new SamraddhJeevanFragment();
                Bundle bundle = new Bundle();
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("6")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new DailyDepositFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }

        if(id.equals("7")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new MonthlyIncomeSchemeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("8")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new FixedDepositFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("9")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new RecurringDepositFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        if(id.equals("10")){
            if(agentsearch.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Please enter agent code", Toast.LENGTH_SHORT).show();
            }else  if(branchStr.equals("")){
                Toast.makeText(getActivity(), "Please select branch", Toast.LENGTH_SHORT).show();
            }else {
                Fragment fragment = new SamraddhBhavhishyaFragment();
                Bundle bundle = new Bundle();
                bundle.putString("agentId", agentsearch.getText().toString());
                bundle.putString("branchId", branchStr);
                bundle.putString("planId", id);
                bundle.putString("savingAmount", savingAmount);
                bundle.putString("plan_code", plan_code);
                bundle.putString("Serachmemberid", memberIded.getText().toString());
                bundle.putString("ssbaccount", ssbaccount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }


    }

    private void getMemberDetails(String member_id,String id) {
        memberDetailslv.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
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
                        memberDetailslv.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        notfound.setVisibility(View.GONE);
                        googleProgressDialog.dismiss();
                        if(response.body().getResult().getMemberDetail().getName()!=null) {
                            nameTv.setText(response.body().getResult().getMemberDetail().getName());
                        }else{
                            nameTv.setText("");
                        }
                        if(response.body().getResult().getMemberDetail().getMemberId()!=null) {
                            memberidTv.setText(response.body().getResult().getMemberDetail().getMemberId());
                        }else{
                            memberidTv.setText("");
                        }
                        if(response.body().getResult().getMemberDetail().getAddress()!=null) {
                            addressTv.setText(response.body().getResult().getMemberDetail().getAddress());
                        }else{
                            addressTv.setText("");
                        }
                        if(response.body().getResult().getMemberDetail().getCategory()!=null) {
                            categorytv.setText(response.body().getResult().getMemberDetail().getCategory().toString());
                        }else{
                            categorytv.setText("");
                        }
                        if(response.body().getResult().getMemberDetail().getMobileNo()!=null) {
                            phonetv.setText(response.body().getResult().getMemberDetail().getMobileNo().toString());
                        }else{
                            phonetv.setText("");
                        }

                        if(response.body().getResult().getMemberDetail().getMemberIdProof()!=null) {
                            memberidprooftv.setText(response.body().getResult().getMemberDetail().getMemberIdProof().toString());
                        }else{
                            memberidprooftv.setText("");
                        }

                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                    else if(response.body().getCode() == 201){
                        notfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        notfound.setText("Member Not Found!");
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        googleProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                    else {
                        notfound.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        googleProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                }else {
                    googleProgressDialog.dismiss();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberDetailsInvestmentRegisterResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }

    private void getAgentDetails(String member_id,String associate_code) {
        googleProgressDialog.show1("Loading...");
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _associate_code = RequestBody.create(MediaType.parse("multipart/form-data"), associate_code);
        Call<MemberAgentResponse> applicationsListResponesCall = RestHandler.getApiService().MEMBER_DETAILS_Agent_RESPONES_CALL(_member_id,_associate_code,_token);
        applicationsListResponesCall.enqueue(new Callback<MemberAgentResponse>() {
            @Override
            public void onResponse(Call<MemberAgentResponse> call, Response<MemberAgentResponse> response) {


                if (response!=null){
                    if (response.body().getCode() == 200){
                        agentlv.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                        if(response.body().getResult().getAssociateDetail().getName()!=null) {
                            agnametv.setText(response.body().getResult().getAssociateDetail().getName());
                        }else{
                            agnametv.setText("");
                        }
                        if(response.body().getResult().getAssociateDetail().getMobileNo()!=null) {
                            agnumbertv.setText(response.body().getResult().getAssociateDetail().getMobileNo());
                        }else{
                            agnumbertv.setText("");
                        }
                        if(response.body().getResult().getAssociateDetail().getCarder()!=null) {
                            agcaretv.setText(response.body().getResult().getAssociateDetail().getCarder());
                        }else{
                            agcaretv.setText("");
                        }


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
            public void onFailure(Call<MemberAgentResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }
}