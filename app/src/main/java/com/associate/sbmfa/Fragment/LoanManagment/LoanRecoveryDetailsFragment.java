package com.associate.sbmfa.Fragment.LoanManagment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.LoanManagment.Adapter.LoanRecoveryDetailsAdapter;
import com.associate.sbmfa.Fragment.LoanManagment.Model.LoanRecoveryDetailsModelChild;
import com.associate.sbmfa.Fragment.LoanManagment.Model.LoanRecoveryDetailsModelPraent;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails.LoanRecovery;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails.LoanRecoveryDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails.ResultLoanRecoveryDetails;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanRecoveryDetailsFragment extends Fragment {
    View RootView;
    ExpandableListView expListView;
    LoanRecoveryDetailsAdapter listAdapter;
    ArrayList<LoanRecoveryDetailsModelPraent> parent_models=new ArrayList<>();
    ImageView imageViewBack;
    ImageButton imageButtonPrv,imageButtonNext;
    ArrayList<String> dateStrings1 =new ArrayList<>();
    Spinner spinner1,spinnerLanght;
    int page = 1;
    String langht = "15";
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    TextView textViewNotFound;
    String cat_id = "loan";
    TextView textViewTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_loan_details_recovery, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        textViewTitle = RootView.findViewById(R.id.textView2);

        if (getArguments()!=null){
            cat_id =getArguments().getString("cate_id");
            if (cat_id.equals("loan")){
                textViewTitle.setText("Loan Recovery Details");
            }else {
                textViewTitle.setText("Group Loan Recovery Details");
            }
        }else {
            textViewTitle.setText("Loan Recovery Details");
        }
        
        listAdapter = new LoanRecoveryDetailsAdapter(getActivity(), parent_models);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

        getLoanRecoveryDetailsList(member_id,token,String.valueOf(page),langht);
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1){
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page--;
                    getLoanRecoveryDetailsList(member_id,token,String.valueOf(page),langht);
                }else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page++;
                getLoanRecoveryDetailsList(member_id,token,String.valueOf(page),langht);
            }
        });

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

    private void getLoanRecoveryDetailsList(String member_id, String token, String page, String langht) {
        try {
            googleProgressDialog.show1("Loading Data....");
            if (cat_id.equals("loan")){
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), page);
                RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langht);
                RestHandler.getApiService().LOAN_RECOVERY_DETAILS_RESPONSE_CALL(_member_id,_token,_page,_length).enqueue(new Callback<LoanRecoveryDetailsResponse>() {
                    @Override
                    public void onResponse(Call<LoanRecoveryDetailsResponse> call, Response<LoanRecoveryDetailsResponse> response) {
                        if (response.isSuccessful()){
                            googleProgressDialog.dismiss();
                            if (response.body().getCode() == 200){
                                if (response.body().getAssociateStatus() == 0){
                                    sessionManager.logoutUser();
                                }
                                    textViewNotFound.setVisibility(View.GONE);
                                    ResultLoanRecoveryDetails result = response.body().getResult();
                                    List<LoanRecovery> loanRecovery = result.getLoanRecovery();
                                    int totalcount = response.body().getResult().getTotalCount();
                                    int lenght = Integer.parseInt(response.body().getResult().getLength());
                                    int page = Integer.parseInt(response.body().getResult().getPage());
                                    int clickButNxt = lenght * page;
                                    if (totalcount > clickButNxt){
                                        imageButtonNext.setEnabled(true);
                                    }else {
                                        imageButtonNext.setEnabled(false);
                                    }
                                    String id = null,name = null,member_id = null;
                                    if (loanRecovery.size() > 0){
                                        for (int i = 0; i < loanRecovery.size(); i++) {
                                            ArrayList<LoanRecoveryDetailsModelChild> child_models = new ArrayList<>();
                                            id = String.valueOf(i + 1);
                                            name = loanRecovery.get(i).getBranchName();
                                            member_id = String.valueOf(loanRecovery.get(i).getBranchCode());
                                            child_models.add(new LoanRecoveryDetailsModelChild(
                                                    loanRecovery.get(i).getBranchName(),
                                                    String.valueOf(loanRecovery.get(i).getBranchCode()),
                                                    String.valueOf(loanRecovery.get(i).getAccountNumber()),
                                                    loanRecovery.get(i).getLoanType(),
                                                    loanRecovery.get(i).getMemberName(),
                                                    loanRecovery.get(i).getMemberId(),
                                                    loanRecovery.get(i).getAssociateName(),
                                                    loanRecovery.get(i).getAssociateId(),
                                                    loanRecovery.get(i).getPaymentDate(),
                                                    loanRecovery.get(i).getPaymentMode(),
                                                    loanRecovery.get(i).getDescription(),
                                                    loanRecovery.get(i).getPenalty(),
                                                    loanRecovery.get(i).getDeposite(),
                                                    loanRecovery.get(i).getRoiAmount(),
                                                    loanRecovery.get(i).getPrincipalAmount(),
                                                    loanRecovery.get(i).getOpeningBalance()
                                            ));
                                            parent_models.add(new LoanRecoveryDetailsModelPraent(id, name, member_id, "", child_models));
                                        }
                                        listAdapter.notifyDataSetChanged();
                                    }else {
                                        imageButtonNext.setEnabled(false);
                                        textViewNotFound.setVisibility(View.VISIBLE);
                                    }

                            }else {
                                imageButtonNext.setEnabled(false);
                                textViewNotFound.setVisibility(View.VISIBLE);
                            }
                        }else {
                            imageButtonNext.setEnabled(false);
                            googleProgressDialog.dismiss();
                            textViewNotFound.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(Call<LoanRecoveryDetailsResponse> call, Throwable t) {
                        googleProgressDialog.dismiss();
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                    }
                });
            }else {
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), page);
                RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langht);
                RestHandler.getApiService().GROUP_LOAN_RECOVERY_DETAILS_RESPONSE_CALL(_member_id,_token,_page,_length).enqueue(new Callback<LoanRecoveryDetailsResponse>() {
                    @Override
                    public void onResponse(Call<LoanRecoveryDetailsResponse> call, Response<LoanRecoveryDetailsResponse> response) {
                        if (response.isSuccessful()){
                            googleProgressDialog.dismiss();
                            if (response.body().getCode() == 200){
                                    textViewNotFound.setVisibility(View.GONE);
                                    ResultLoanRecoveryDetails result = response.body().getResult();
                                    List<LoanRecovery> loanRecovery = result.getLoanRecovery1();
                                    String id = null,name = null,member_id = null;
                                    int totalcount = response.body().getResult().getTotalCount();
                                    int lenght = Integer.parseInt(response.body().getResult().getLength());
                                    int page = Integer.parseInt(response.body().getResult().getPage());
                                    int clickButNxt = lenght * page;
                                    if (totalcount > clickButNxt){
                                        imageButtonNext.setEnabled(true);
                                    }else {
                                        imageButtonNext.setEnabled(false);
                                    }
                                    if (loanRecovery.size() > 0){
                                        for (int i = 0; i < loanRecovery.size(); i++) {
                                            ArrayList<LoanRecoveryDetailsModelChild> child_models = new ArrayList<>();
                                            id = String.valueOf(i + 1);
                                            name = loanRecovery.get(i).getBranchName();
                                            member_id = String.valueOf(loanRecovery.get(i).getBranchCode());
                                            child_models.add(new LoanRecoveryDetailsModelChild(
                                                    loanRecovery.get(i).getBranchName(),
                                                    String.valueOf(loanRecovery.get(i).getBranchCode()),
                                                    String.valueOf(loanRecovery.get(i).getAccountNumber()),
                                                    loanRecovery.get(i).getLoanType(),
                                                    loanRecovery.get(i).getMemberName(),
                                                    loanRecovery.get(i).getMemberId(),
                                                    loanRecovery.get(i).getAssociateName(),
                                                    loanRecovery.get(i).getAssociateId(),
                                                    loanRecovery.get(i).getPaymentDate(),
                                                    loanRecovery.get(i).getPaymentMode(),
                                                    loanRecovery.get(i).getDescription(),
                                                    loanRecovery.get(i).getPenalty(),
                                                    loanRecovery.get(i).getDeposite(),
                                                    loanRecovery.get(i).getRoiAmount(),
                                                    loanRecovery.get(i).getPrincipalAmount(),
                                                    loanRecovery.get(i).getOpeningBalance()
                                            ));
                                            parent_models.add(new LoanRecoveryDetailsModelPraent(id, name, member_id, "", child_models));
                                        }
                                        listAdapter.notifyDataSetChanged();
                                    }else {
                                        imageButtonNext.setEnabled(false);
                                        textViewNotFound.setVisibility(View.VISIBLE);
                                    }
                            }else {
                                imageButtonNext.setEnabled(false);
                                textViewNotFound.setVisibility(View.VISIBLE);
                            }
                        }else {
                            imageButtonNext.setEnabled(false);
                            googleProgressDialog.dismiss();
                            textViewNotFound.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(Call<LoanRecoveryDetailsResponse> call, Throwable t) {
                        googleProgressDialog.dismiss();
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                    }
                });
            }
        }catch (Exception e){
        }
    }
    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
}
