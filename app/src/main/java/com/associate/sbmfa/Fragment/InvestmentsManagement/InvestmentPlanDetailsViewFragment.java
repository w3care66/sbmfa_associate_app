package com.associate.sbmfa.Fragment.InvestmentsManagement;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Member;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Result;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentPlanDetailsViewFragment extends Fragment {
    CardView cart_maturity;
    ImageView imageViewBack;
    TextView member_id,member_first_name,member_last_name,branch_id,mobile_no,address,
            id_proof,categories,account_number,
            account_balance,associate_id,associate_name,associate_cader,associate_mobile_no,
            investment_plan,form_number,
            daily_deposit_amount,duration,payment_mode,
            maturity_account_balance,maturity_account_number,
            first_nominee_name, first_nominee_age,
            first_nominee_relationship, first_nominee_gender, first_nominee_dob,
            second_nominee_name, second_nominee_age,
            second_nominee_relationship, second_nominee_gender, second_nominee_dob,nominee_first_percentage;
    SessionManager sessionManager;
    String member_id_="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    String id,type;
    GoogleProgressDialog googleProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView =inflater.inflate(R.layout.fragment_investment_plan_details_view, container, false);
        sessionManager = new SessionManager(getContext());
        googleProgressDialog = new GoogleProgressDialog(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id_= UserData.get(SessionManager.KEY_member_id);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("_plan_id", "0");
            type = bundle.getString("_plan_type");

        }
        cart_maturity=RootView.findViewById(R.id.cart_maturity);
        if (type.equals("Saving Account")){
            cart_maturity.setVisibility(View.GONE);
        }
        else {
            cart_maturity.setVisibility(View.VISIBLE);
        }
        nominee_first_percentage=RootView.findViewById(R.id.Nominee_percentage);

        member_id=RootView.findViewById(R.id.tv_memberid);
        member_first_name=RootView.findViewById(R.id.tv_firstname);
        member_last_name=RootView.findViewById(R.id.tv_secondname);
        branch_id=RootView.findViewById(R.id.tv_branchid);
        mobile_no=RootView.findViewById(R.id.tv_mobileno);
        address=RootView.findViewById(R.id.tv_address);
        id_proof=RootView.findViewById(R.id.tv_idproof);
        categories=RootView.findViewById(R.id.tv_category);
        account_number=RootView.findViewById(R.id.tv_acno);
        account_balance=RootView.findViewById(R.id.tv_acblance);
        associate_id=RootView.findViewById(R.id.agent_id);
        associate_name=RootView.findViewById(R.id.agent_name);
        associate_cader=RootView.findViewById(R.id.agent_Carder);
        associate_mobile_no=RootView.findViewById(R.id.agent_mobile);
        investment_plan=RootView.findViewById(R.id.form_Investment_Plan);
        form_number=RootView.findViewById(R.id.form_number);
        daily_deposit_amount=RootView.findViewById(R.id.form_Deposit_Amount);
        duration=RootView.findViewById(R.id.form_Duration);
        payment_mode=RootView.findViewById(R.id.form_payment_mode);
        maturity_account_balance=RootView.findViewById(R.id.Maturity_acblance);
        maturity_account_number=RootView.findViewById(R.id.Maturity_Accountno);
        first_nominee_name=RootView.findViewById(R.id.Nominee_name);
        first_nominee_age=RootView.findViewById(R.id.Nominee_age);
        first_nominee_relationship=RootView.findViewById(R.id.Nominee_relation);
        first_nominee_gender=RootView.findViewById(R.id.Nominee_gender);
        first_nominee_dob= RootView.findViewById(R.id.Nominee_dob);




        imageViewBack = RootView.findViewById(R.id.imageViewBack);
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
        investment_detail();
        return RootView;
    }
    void investment_detail(){
        googleProgressDialog.show1("Loading...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id_);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _dateFrom = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RestHandler.getApiService().INVESTMENT_DETAILS_RESPONSE_CALL(_member_id,_token,_dateFrom).enqueue(new Callback<InvestmentDetailsResponse>() {
            @Override
            public void onResponse(Call<InvestmentDetailsResponse> call, Response<InvestmentDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("Success")){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        Result result=response.body().getResult();
                        List<Member> member_info=result.getMember();
                        member_id.setText(member_info.get(0).getMemberId());
                        member_first_name.setText(member_info.get(0).getMemberFirstName());
                        member_last_name.setText(member_info.get(0).getMemberLastName());
                        branch_id.setText(member_info.get(0).getBranch());
                        mobile_no.setText(member_info.get(0).getMobileNumber());
                        address.setText(member_info.get(0).getAddress());
                        id_proof.setText(member_info.get(0).getIdProof());
                        categories.setText(member_info.get(0).getCategory());
                        account_number.setText(member_info.get(0).getAccountNumber());
                        account_balance.setText(member_info.get(0).getCurrentBalance()+"₹");
                        associate_id.setText(member_info.get(0).getAssociateCode());
                        associate_name.setText(member_info.get(0).getAssociateName());
                        associate_cader.setText(member_info.get(0).getAssociateCarder());
                        associate_mobile_no.setText(member_info.get(0).getAssociateMobileNumber());
                        investment_plan.setText(member_info.get(0).getPlan());
                        form_number.setText(member_info.get(0).getFormNumber());
                        daily_deposit_amount.setText(member_info.get(0).getDepositeAmount()+"₹");
                        duration.setText(member_info.get(0).getTenure());
                        payment_mode.setText(member_info.get(0).getPaymentMode());
                        if (member_info.get(0).getMaturityAmount()!=null){
                            maturity_account_balance.setText(member_info.get(0).getMaturityAmount()+"₹");
                        }else{
                            maturity_account_balance.setText("");
                        }

                        maturity_account_number.setText(member_info.get(0).getAccountNumber());
                        first_nominee_name.setText(member_info.get(0).getNomineeName());
                        first_nominee_age.setText(String.valueOf(member_info.get(0).getNomineeAge()));
                        first_nominee_relationship.setText(member_info.get(0).getRelation());
                        first_nominee_gender.setText(String.valueOf(member_info.get(0).getNomineeGender()));
                        first_nominee_dob.setText(String.valueOf(member_info.get(0).getNomineeDob()));
                        nominee_first_percentage.setText(String.valueOf(member_info.get(0).getNomineePercentage())+"%");
                        googleProgressDialog.dismiss();
                    }else{
                        googleProgressDialog.dismiss();
                    }
                }else{
                    googleProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InvestmentDetailsResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });

    }

}