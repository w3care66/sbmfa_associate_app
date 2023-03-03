package com.associate.sbmfa.Fragment.InvestmentsManagement;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalPlanModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.RenewalPlanGridListAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.RenewForm.DailyRenewalFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.RenewForm.DepositeSaveingRenewalFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.RenewForm.RDFRDRenewalFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Branch;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.PlanRenewType;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewPlanCommanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.ResultRenewPlanComman;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.SsbDetail;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RenewalPlanNewFragment extends Fragment implements RenewalPlanGridListAdapter.EventListener {
    View RootView;
    RenewalPlanGridListAdapter renewalPlanGridListAdapter;
    ArrayList<RenewalPlanModel> renewalPlanModels =new ArrayList<>();

    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    RecyclerView recyclerView;
    ImageView imageViewBack;
    ArrayList<String> branchlist =new ArrayList<>();
    ArrayList<String> branchlistID =new ArrayList<>();
    Spinner branch;
    String branchStr = "new";
    String balance,ssb_acount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView =  inflater.inflate(R.layout.fragment_renewal_plan_new, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        recyclerView = RootView.findViewById(R.id.recyclerView);
        branch = RootView.findViewById(R.id.branch);
        /*
         */
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
        return RootView;
    }

    public  void getBranchList(final String assciate_no, final String token){
        try {

            googleProgressDialog.show1("Loading...");
            renewalPlanModels.clear();
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
                            SsbDetail ssbDetail = result.getSsbDetail();
                            balance = ssbDetail.getBalance();
                            ssb_acount = ssbDetail.getAccountNo();
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
                            List<PlanRenewType> plan = result.getPlan();
                            for (int i =0;i< plan.size(); i++){
                                if (i == 0){
                                    renewalPlanModels.add(new RenewalPlanModel(
                                            String.valueOf(plan.get(i).getValue()),
                                            plan.get(i).getName(),
                                            "0",
                                            "0",
                                            ""
                                    ));
                                }else if (i == 1){
                                    renewalPlanModels.add(new RenewalPlanModel(
                                            String.valueOf(plan.get(i).getValue()),
                                            plan.get(i).getName(),
                                            "1",
                                            "1",
                                            ""
                                    ));
                                }else if(i == 2){
                                    renewalPlanModels.add(new RenewalPlanModel(
                                            String.valueOf(plan.get(i).getValue()),
                                            plan.get(i).getName(),
                                            "2",
                                            "2",""

                                    ));
                                }
                            }
                            recyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL, false));
                            renewalPlanGridListAdapter = new RenewalPlanGridListAdapter(getActivity(),renewalPlanModels,RenewalPlanNewFragment.this);
                            recyclerView.setAdapter(renewalPlanGridListAdapter);

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
        }catch (NullPointerException ex){

        }

    }
    @Override
    public void onEvent_CardClick(String id, String amount) {
        if (id.equals("0")){
            if (!branchStr.isEmpty()){
                Fragment fragment=new DailyRenewalFragment();
                Bundle bundle = new Bundle();
                bundle.putString("branch",branchStr);
                bundle.putString("balance",balance);
                bundle.putString("ssb_acount",ssb_acount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }else {
                Toast.makeText(getContext(), "Please Select Branch...", Toast.LENGTH_SHORT).show();
            }

        }else if (id.equals("1")){
            if (!branchStr.isEmpty()){
                Fragment fragment=new RDFRDRenewalFragment();
                Bundle bundle = new Bundle();
                bundle.putString("branch",branchStr);
                bundle.putString("balance",balance);
                bundle.putString("ssb_acount",ssb_acount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }else {
                Toast.makeText(getContext(), "Please Select Branch...", Toast.LENGTH_SHORT).show();
            }

        }else if (id.equals("2")){
            if (!branchStr.isEmpty()){
                Fragment fragment=new DepositeSaveingRenewalFragment();
                Bundle bundle = new Bundle();
                bundle.putString("branch",branchStr);
                bundle.putString("balance",balance);
                bundle.putString("ssb_acount",ssb_acount);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }else {
                Toast.makeText(getContext(), "Please Select Branch...", Toast.LENGTH_SHORT).show();
            }
        }

    }
}