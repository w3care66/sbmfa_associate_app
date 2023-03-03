package com.associate.sbmfa.Fragment.LoanManagment;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Adapter.LoanRecoveryAdapter;
import com.associate.sbmfa.Fragment.LoanManagment.Model.Loan_Recovery_List_Parent_model;
import com.associate.sbmfa.Fragment.LoanManagment.Model.Loan_recovery_List_Child_model;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Recovery.LoanRecovery;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Recovery.LoanRecoveryResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class LoanRecoveryFragment extends Fragment implements LoanRecoveryAdapter.EventListener {
    View RootView;
    LoanRecoveryAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<Loan_Recovery_List_Parent_model> parent_models=new ArrayList<>();
    Spinner length;
    ArrayList<String> datelengthArr =new ArrayList<>();
    TextView  datanotfound;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewBack;
    int lengthValue=15,page=1;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    EditText editTextTextPersonName;
    String cat_id = "loan";
    TextView textViewTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_loan_recovery, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        datanotfound=RootView.findViewById(R.id.not_found);
        editTextTextPersonName=RootView.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        textViewTitle = RootView.findViewById(R.id.textView2);
        if (getArguments()!=null){

            cat_id =getArguments().getString("cate_id");
            if (cat_id.equals("loan")){
                textViewTitle.setText("Loan Recovery");
            }else {
                textViewTitle.setText("Group Loan Recovery");
            }
        }else {
            textViewTitle.setText("Loan Recovery");
        }
        datelengthArr.clear();

        /*datelengthArr.add("10");
        datelengthArr.add("20");
        datelengthArr.add("30");
        datelengthArr.add("40");
        datelengthArr.add("50");
        datelengthArr.add("60");
        datelengthArr.add("70");
        datelengthArr.add("80");
        datelengthArr.add("90");
        datelengthArr.add("100");
        ArrayAdapter<String> adapterselectlength = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, datelengthArr);
        adapterselectlength.setDropDownViewResource(R.layout.spiner_item);
        length.setAdapter(adapterselectlength);
        length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    lengthValue = Integer.parseInt(datelengthArr.get(position));
                    getMemberList(member_id,lengthValue,page,token);
                }else{
                    lengthValue =10;
                    getMemberList(member_id,10,1,token);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        getMemberList(member_id,lengthValue,page,token);
        imageViewBack = RootView.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page=page+1;
                getMemberList(member_id,lengthValue,page,token);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page = page - 1;
                    getMemberList(member_id,lengthValue,page,token);
                }
            }
        });

        listAdapter = new LoanRecoveryAdapter(getActivity(), parent_models, LoanRecoveryFragment.this,cat_id);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
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
        parent_models.clear();
        listAdapter.notifyDataSetChanged();
        return  RootView;
    }
    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }



    public  void getMemberList(final String member_id, int length, int page, String token){
        if (cat_id.equals("loan")){
            googleProgressDialog.show1("Loading...");
            parent_models.clear();
            if(page <= 0){
                page=1;
            }
            if(length <= 0){
                length=1;
            }
            RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            Call<LoanRecoveryResponse> applicationsListResponesCall = RestHandler.getApiService().
                    LOAN_RECOVERY_RESPONES_CALL(_assciate_no,_page,_length,_token);
            applicationsListResponesCall.enqueue(new Callback<LoanRecoveryResponse>() {
                @Override
                public void onResponse(Call<LoanRecoveryResponse> call, Response<LoanRecoveryResponse> response) {
                    googleProgressDialog.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                            List<LoanRecovery> member = response.body().getResult().getLoanRecovery();
                            int totalcount = response.body().getResult().getTotalCount();
                            int lenght = Integer.parseInt(response.body().getResult().getLength());
                            int page = Integer.parseInt(response.body().getResult().getPage());
                            int clickButNxt = lenght * page;
                            if (totalcount > clickButNxt){
                                next.setEnabled(true);
                            }else {
                                next.setEnabled(false);
                            }
                            if(member.size() == 0 ){
                                datanotfound.setVisibility(View.VISIBLE);
                            }else{
                                datanotfound.setVisibility(View.GONE);
                            }
                            int i=1;
                            for (LoanRecovery memberItem : member) {
                                ArrayList<Loan_recovery_List_Child_model> child_models = new ArrayList<>();
                                child_models.clear();
                                child_models.add(new Loan_recovery_List_Child_model(memberItem.getMemberId(),memberItem.getAccountNumber(),"",memberItem.getBranchName(),memberItem.getBranchCode().toString(),memberItem.getSectorName(),memberItem.getRegionName(),memberItem.getZoneName(),memberItem.getMemberId(),memberItem.getMemberName(),memberItem.getLastRecoveryDate(),memberItem.getAssociateCode(),memberItem.getAssociateName(),memberItem.getLoanType(),memberItem.getLoanAmount(),memberItem.getStatus(),memberItem.getApproveDate(),memberItem.getApplicationDate(),memberItem.getId(),memberItem.getLoanAmount().equals("N/A")?memberItem.getLoanAmount():memberItem.getLoanAmount()+" ₹",memberItem.getFileCharge().equals("N/A")?memberItem.getFileCharge():memberItem.getFileCharge()+" ₹",memberItem.getFileChargesPaymentMode(),memberItem.getOutstandingAmount().equals("N/A")?memberItem.getOutstandingAmount():memberItem.getOutstandingAmount()+" ₹",memberItem.getTotalPayment().equals("N/A")? memberItem.getTotalPayment():memberItem.getTotalPayment()+" ₹",memberItem.getTenure(),memberItem.getTransfer_amount()+" ₹"));
                                parent_models.add(new Loan_Recovery_List_Parent_model(String.valueOf(i), memberItem.getAssociateName(), memberItem.getMemberId(), "", child_models));
                                i++;
                            }
                            listAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        next.setEnabled(false);
                        Toast.makeText(getActivity(),"Data not found.." , Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LoanRecoveryResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    next.setEnabled(false);
                }
            });

        }else {
            googleProgressDialog.show1("Loading...");
            parent_models.clear();
            if(page <= 0){
                page=1;
            }
            if(length <= 0){
                length=1;
            }
            RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            Call<LoanRecoveryResponse> applicationsListResponesCall = RestHandler.getApiService().
                    GROUP_LOAN_RECOVERY_RESPONES_CALL(_assciate_no,_page,_length,_token);
            applicationsListResponesCall.enqueue(new Callback<LoanRecoveryResponse>() {
                @Override
                public void onResponse(Call<LoanRecoveryResponse> call, Response<LoanRecoveryResponse> response) {
                    googleProgressDialog.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }

                            List<LoanRecovery> member = response.body().getResult().getLoanRecovery1();
                            int totalcount = response.body().getResult().getTotalCount();
                            int lenght = Integer.parseInt(response.body().getResult().getLength());
                            int page = Integer.parseInt(response.body().getResult().getPage());
                            int clickButNxt = lenght * page;
                            if (totalcount > clickButNxt){
                                next.setEnabled(true);
                            }else {
                                next.setEnabled(false);
                            }
                            if(member.size() == 0 ){
                                datanotfound.setVisibility(View.VISIBLE);
                            }else{
                                datanotfound.setVisibility(View.GONE);
                            }
                            int i=1;
                            for (LoanRecovery memberItem : member) {
                                ArrayList<Loan_recovery_List_Child_model> child_models = new ArrayList<>();
                                child_models.clear();
                                child_models.add(new Loan_recovery_List_Child_model(memberItem.getMemberId(),memberItem.getAccountNumber(),"",memberItem.getBranchName(),memberItem.getBranchCode().toString(),memberItem.getSectorName(),memberItem.getRegionName(),memberItem.getZoneName(),memberItem.getMemberId(),memberItem.getMemberName(),memberItem.getLastRecoveryDate(),memberItem.getAssociateCode(),memberItem.getAssociateName(),memberItem.getLoanType(),memberItem.getLoanAmount(),memberItem.getStatus(),memberItem.getApproveDate(),memberItem.getApplicationDate(),memberItem.getId(),memberItem.getLoanAmount().equals("N/A")?memberItem.getLoanAmount():memberItem.getLoanAmount()+" ₹",memberItem.getFileCharge().equals("N/A")?memberItem.getFileCharge():memberItem.getFileCharge()+" ₹",memberItem.getFileChargesPaymentMode(),memberItem.getOutstandingAmount().equals("N/A")? "":memberItem.getOutstandingAmount()+" ₹",memberItem.getTotalPayment().endsWith("N/A")?"":memberItem.getTotalPayment()+" ₹",memberItem.getTenure(),memberItem.getTransfer_amount()));
                                parent_models.add(new Loan_Recovery_List_Parent_model(String.valueOf(i), memberItem.getAssociateName(), memberItem.getMemberId(), "", child_models));

                                i++;

                            }
                            listAdapter.notifyDataSetChanged();
                        } else {
                            next.setEnabled(false);
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        next.setEnabled(false);
                        Toast.makeText(getActivity(), "Data not found..", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoanRecoveryResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    next.setEnabled(false);
                }
            });
        }
    }

    @Override
    public void onEvent_View(String id, String memberid, String type) {
        Log.e("loonid",""+id);
        Fragment fragment=new LoanRecoveryEmiFormFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
