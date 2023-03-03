package com.associate.sbmfa.Fragment.InvestmentsManagement;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.DailyRenewalListAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.DailyRenewalModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Branch;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.PlanRenewType;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewPlanCommanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.ResultRenewPlanComman;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RenewalPlanRegistrationFragment extends Fragment implements DailyRenewalListAdapter.EventListener {
    View RootView;
    Spinner plantype,branch,paymentmode,paymentModeFRD,paymentMofeDeposite;
    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> dateStringstype =new ArrayList<>();
    ArrayList<String> branchlist =new ArrayList<>();
    ArrayList<String> branchlistID =new ArrayList<>();
    ArrayList<String> paymentmodearr =new ArrayList<>();
    ArrayList<String> paymentmodearrId =new ArrayList<>();
    ImageView imageViewBack;
    LinearLayout linearLayoutDaily,linearLayoutFRD,linearLayoutDeposite;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    RecyclerView recyclerViewDailyRenewal,recyclerViewFDRED,recyclerViewDeposite;
    DailyRenewalListAdapter dailyRenewalListAdapter;
    ArrayList<DailyRenewalModel> dailyRenewalModels = new ArrayList<>();
    ScrollView scrollView;

    EditText textViewNumberAccountDaily,textViewNumberAccountFrd;
    TextView textViewTotalAmountDaily;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_renewal_plan_registration, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        plantype = RootView.findViewById(R.id.plantype);
        branch = RootView.findViewById(R.id.branch);
        scrollView = RootView.findViewById(R.id.scrollView3);


        textViewNumberAccountDaily = RootView.findViewById(R.id.editTextTextPersonName9);
        textViewNumberAccountFrd = RootView.findViewById(R.id.editTextrd_frdAccount);
        textViewTotalAmountDaily = RootView.findViewById(R.id.editTextTextPersonName11);
        recyclerViewDailyRenewal = RootView.findViewById(R.id.recyclerView_daily);
        recyclerViewFDRED = RootView.findViewById(R.id.recyclerView_rd_frd);
        recyclerViewDeposite = RootView.findViewById(R.id.recyclerViewdeposite);


        paymentmode=RootView.findViewById(R.id.paymentmode);
        paymentModeFRD=RootView.findViewById(R.id.paymentmoderd_frd);
        paymentMofeDeposite=RootView.findViewById(R.id.paymentmode_deposite);

        linearLayoutDaily=RootView.findViewById(R.id.daily_renewal);
        linearLayoutDeposite=RootView.findViewById(R.id.deposite);
        linearLayoutFRD=RootView.findViewById(R.id.rd_frd);

        dateStrings.add("Select Investment Plan Type");
        dateStringstype.add("");
        branchlist.add("Select branch");
        branchlistID.add("");
        getBranchList(member_id,token);


        imageViewBack = RootView.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        RootView.setFocusableInTouchMode(true);
        RootView.requestFocus();
        RootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return  RootView;
    }

    public  void getBranchList(final String assciate_no, final String token){
        googleProgressDialog.show1("Loading...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<RenewPlanCommanResponse> applicationsListResponesCall = RestHandler.getApiService().Branch_list_RESPONES_CALL(_assciate_no,_token);
        applicationsListResponesCall.enqueue(new Callback<RenewPlanCommanResponse>() {
            @Override
            public void onResponse(Call<RenewPlanCommanResponse> call, Response<RenewPlanCommanResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                        ResultRenewPlanComman result = response.body().getResult();
                        List<Branch> branch1 = result.getBranch();
                        for (Branch Item : branch1) {
                            branchlist.add(Item.getName());
                            branchlistID.add(String.valueOf(Item.getId()));
                        }
                        ArrayAdapter<String> adapterselectDate2 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, branchlist);
                        adapterselectDate2.setDropDownViewResource(R.layout.spiner_item);
                        branch.setAdapter(adapterselectDate2);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.e("Position",""+position);

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        List<PlanRenewType> plan = result.getPlan();
                        for (PlanRenewType type : plan){
                            dateStrings.add(type.getName());
                            dateStringstype.add(String.valueOf(type.getValue()));
                        }
                        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
                        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
                        plantype.setAdapter(adapterselectDate);
                        plantype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position == 0){
                                    scrollView.setVisibility(View.GONE);

                                }else if (position == 1){
                                    scrollView.setVisibility(View.VISIBLE);
                                    linearLayoutDaily.setVisibility(View.VISIBLE);
                                    linearLayoutDeposite.setVisibility(View.GONE);
                                    linearLayoutFRD.setVisibility(View.GONE);
                                    getDaily(position);
                                }else if (position == 2){
                                    scrollView.setVisibility(View.VISIBLE);
                                    linearLayoutDaily.setVisibility(View.GONE);
                                    linearLayoutDeposite.setVisibility(View.GONE);
                                    linearLayoutFRD.setVisibility(View.VISIBLE);
                                    getFRD(position);
                                }else if (position == 3){
                                    scrollView.setVisibility(View.VISIBLE);
                                    linearLayoutDaily.setVisibility(View.GONE);
                                    linearLayoutDeposite.setVisibility(View.VISIBLE);
                                    linearLayoutFRD.setVisibility(View.GONE);
                                    getDeposite(position);
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RenewPlanCommanResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDeposite(int position) {
        paymentmodearr.clear();
        paymentmodearr.add("Select Payment mode");
        paymentmodearr.add("SSB Account");
        ArrayAdapter<String> adapterselectDate22 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, paymentmodearr);
        adapterselectDate22.setDropDownViewResource(R.layout.spiner_item);
        paymentMofeDeposite.setAdapter(adapterselectDate22);
        paymentMofeDeposite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       // dailyRenewalModels.clear();
       // dailyRenewalModels.add(new DailyRenewalModel("","","","",""));
       /* recyclerViewDeposite.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        dailyRenewalListAdapter = new DailyRenewalListAdapter(getActivity(),"3", dailyRenewalModels,RenewalPlanRegistrationFragment.this);
        recyclerViewDeposite.setAdapter(dailyRenewalListAdapter);*/
    }
    private void getFRD(int position) {
        paymentmodearr.clear();
        paymentmodearr.add("Select Payment mode");
        paymentmodearr.add("SSB Account");
        ArrayAdapter<String> adapterselectDate22 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, paymentmodearr);
        adapterselectDate22.setDropDownViewResource(R.layout.spiner_item);
        paymentModeFRD.setAdapter(adapterselectDate22);
        paymentModeFRD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /* dailyRenewalModels.clear();
        dailyRenewalModels.add(new DailyRenewalModel("","","","",""));
        dailyRenewalModels.add(new DailyRenewalModel("","","","",""));
        dailyRenewalModels.add(new DailyRenewalModel("","","","",""));
      /*  recyclerViewFDRED.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        dailyRenewalListAdapter = new DailyRenewalListAdapter(getActivity(),"2", dailyRenewalModels,RenewalPlanRegistrationFragment.this);
        recyclerViewFDRED.setAdapter(dailyRenewalListAdapter);*/
    }

    private void getDaily(int position) {
        textViewNumberAccountDaily.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!TextUtils.isEmpty(textViewNumberAccountDaily.getText().toString())){

                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }

                        dailyRenewalModels.clear();
                        String size = textViewNumberAccountDaily.getText().toString();
                        int size1 = Integer.parseInt(size);
                        if (size1 > 0){
                            textViewTotalAmountDaily.setText(null);
                            for (int i =0;i < size1;i++){
                               // dailyRenewalModels.add(new DailyRenewalModel("","","","",""));
                            }
                          /*  recyclerViewDailyRenewal.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            dailyRenewalListAdapter = new DailyRenewalListAdapter(getActivity(),"1", dailyRenewalModels,RenewalPlanRegistrationFragment.this);
                            recyclerViewDailyRenewal.setAdapter(dailyRenewalListAdapter);*/
                        }

                    }else {
                        Toast.makeText(getActivity(), "Text is Empty..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        paymentmodearr.clear();
        paymentmodearr.add("Select Payment mode");
        paymentmodearr.add("SSB Account");
        ArrayAdapter<String> adapterselectDate22 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, paymentmodearr);
        adapterselectDate22.setDropDownViewResource(R.layout.spiner_item);
        paymentmode.setAdapter(adapterselectDate22);
        paymentmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onEvent_DailyRewal(String id, String amount) {
            if (textViewTotalAmountDaily.getText().toString().isEmpty()){
                textViewTotalAmountDaily.setText(amount);
            }else {
                int total = Integer.parseInt(textViewTotalAmountDaily.getText().toString());
                int amountTotal = total+Integer.parseInt(amount);
                textViewTotalAmountDaily.setText(String.valueOf(amountTotal));
            }


    }
}
