package com.associate.sbmfa.Fragment.AssociateManagement;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_DependentslListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.DependentsModel;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.AssociateDetails;
import com.associate.sbmfa.Respones.AssociateDetailsViewResponse;
import com.associate.sbmfa.Respones.AssociateFormInformation;
import com.associate.sbmfa.Respones.DependentDetail;
import com.associate.sbmfa.Respones.GuarantorDetail;
import com.associate.sbmfa.Respones.IdProofDetail;
import com.associate.sbmfa.Respones.PersonalInformation;
import com.associate.sbmfa.Respones.ResultAssociateDetailsView;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class AssociateDeatilsViewFragment extends Fragment {
    View RootView;
    ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    Associate_DependentslListAdapter associate_dependentslListAdapter;
    ArrayList<DependentsModel> dependentsModels =new ArrayList<>();
    TextView AssociateFormNo,AssociateJoinDate,AssociateAsscoiateId,AssociatememberId,AssociateCarder;

    TextView PersonalFirstname,PersonalLastName,
            PersonalEmail,PersonalMobile,
            Personaldob,PersonalAge,PersonalGender,
            PersonalOccupations,PersonalAnnualIncome,
            PersonalStatus,PersonalState,
            PersonalDistrict,PersonalCity,
            PersonalVillage,PersonalPinCode,
    textView44address_;

    TextView textView67,textView2,SeniorCode,SeniorName,SeniorMobile,SeniorCader,DobName,FirstAddress,SecoundAddress;
    TextView GuaratorFullname,GuaratorMobile,GuaratorAddress;
    TextView FirstDocType,FirstIdNo,SecoundDocType,SecoundIdNo;
    ImageView Signature,profile;
    TextView textViewFullName2,textViewAddress2,textViewMobile2,head_2ndgurantor,fullname_gurantor_head,mobile_gurantor_head,address_gurantor_head;
    String id,title="";
    RecyclerView recyclerView;
    ImageView imageViewSing;
    CardView cardView1q35;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_associate_deatils_view, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);

        if (getArguments()!=null){
           id = getArguments().getString("id");
            title = getArguments().getString("title");
           getAssocateDetails(member_id,token,id);
        }else {

        }
        textView67=RootView.findViewById(R.id.textView67);
        profile = RootView.findViewById(R.id.profile_image);
        recyclerView = RootView.findViewById(R.id.recyclerView);
        Signature = RootView.findViewById(R.id.imageView29);
        cardView1q35 = RootView.findViewById(R.id.cardView1q35);
        head_2ndgurantor=RootView.findViewById(R.id.full_name_2e);
        AssociateFormNo = RootView.findViewById(R.id.form_id);
        AssociateJoinDate = RootView.findViewById(R.id.JoiningDate);
        AssociateAsscoiateId = RootView.findViewById(R.id.associate_id);
        AssociatememberId = RootView.findViewById(R.id.member_id);
        AssociateCarder = RootView.findViewById(R.id.carder);
        fullname_gurantor_head=RootView.findViewById(R.id.full_name_2);
        mobile_gurantor_head=RootView.findViewById(R.id.mobile_no_2);
        address_gurantor_head=RootView.findViewById(R.id.address_2);
        PersonalFirstname = RootView.findViewById(R.id.gomRegDate);
        PersonalLastName = RootView.findViewById(R.id.gobranchCode);
        PersonalEmail = RootView.findViewById(R.id.gobarnchName);
        PersonalMobile = RootView.findViewById(R.id.gosectorname);
      //  Personaldob = RootView.findViewById(R.id.gomemberid);
        PersonalAge = RootView.findViewById(R.id.gomemberName);
        PersonalGender = RootView.findViewById(R.id.goassociateId);
        PersonalOccupations = RootView.findViewById(R.id.goadharNumber);
        PersonalAnnualIncome = RootView.findViewById(R.id.gopenNumber);
        PersonalStatus = RootView.findViewById(R.id.goSeniorName);
        PersonalState = RootView.findViewById(R.id.state_name);
        PersonalDistrict = RootView.findViewById(R.id.district__name);
        PersonalCity = RootView.findViewById(R.id.city___name);
        PersonalVillage = RootView.findViewById(R.id.village_name);
        PersonalPinCode = RootView.findViewById(R.id.pincode__name);


        SeniorCode = RootView.findViewById(R.id.senior_code_name);
        SeniorName = RootView.findViewById(R.id.name_name);
        SeniorMobile = RootView.findViewById(R.id.mobile_no);
        SeniorCader = RootView.findViewById(R.id.Cader_namee);
        DobName = RootView.findViewById(R.id.dobName);

        GuaratorFullname = RootView.findViewById(R.id.full_name_nam);
        GuaratorMobile = RootView.findViewById(R.id.name_name_mobile_no_);
        GuaratorAddress = RootView.findViewById(R.id.address__);

        FirstDocType = RootView.findViewById(R.id.first_id_name);
        FirstIdNo = RootView.findViewById(R.id.firstid_no);
        FirstAddress = RootView.findViewById(R.id.firstid_add);
        SecoundDocType = RootView.findViewById(R.id.secound_id_type);
        SecoundIdNo = RootView.findViewById(R.id.secound_id_name);
        SecoundAddress = RootView.findViewById(R.id.secound_id_add);

        textViewFullName2 = RootView.findViewById(R.id.full_name_nam1);
        textViewAddress2 = RootView.findViewById(R.id.name_name_mobile_no_2);
        textViewMobile2 = RootView.findViewById(R.id.address__2);


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
    private void getAssocateDetails(String member_id, String token, String id) {
        try {
            googleProgressDialog.show1("Loading Data.....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
            RestHandler.getApiService().ASSOCIATE_DETAILS_VIEW_RESPONSE_CALL(_member_id,_token,_id).enqueue(new Callback<AssociateDetailsViewResponse>() {
                @Override
                public void onResponse(Call<AssociateDetailsViewResponse> call, Response<AssociateDetailsViewResponse> response) {
                    if (response.isSuccessful()){
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200){
                            if (response.body().getAssociateStatus() == 0){
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                          //  if (response.body().getAssociateStatus() == 1){
                                ResultAssociateDetailsView result = response.body().getResult();
                                Glide.with(getActivity())
                                        .load(response.body().getResult().getProfileImge())
                                        .placeholder(R.drawable.profile_user)
                                        .centerCrop()
                                        .error(R.drawable.profile_user)
                                        .into(profile);
                                Glide.with(getActivity())
                                        .load(response.body().getResult().getSignatureurl())
                                        .placeholder(R.drawable.signnature_place)
                                        .centerCrop()
                                        .into(Signature);
                                AssociateFormInformation information = result.getAssociateFormInformation();
                                AssociateFormNo.setText(information.getFormNo());
                                AssociateJoinDate.setText(information.getJoinDate());
                                AssociateAsscoiateId.setText(information.getAssociateNo());
                                AssociatememberId.setText(information.getMemberId());
                                AssociateCarder.setText(information.getCarder());

                                PersonalInformation personalInformation = result.getPersonalInformation();
                                PersonalFirstname.setText(personalInformation.getFirstName());
                                PersonalLastName.setText(personalInformation.getLastName());
                                PersonalEmail.setText(personalInformation.getEmail()!=null ? personalInformation.getEmail() : "N/A");
                                PersonalMobile.setText(personalInformation.getMobileNo());
                                DobName.setText(String.valueOf(personalInformation.getAge()));
                               // Personaldob.setText(personalInformation.getAge());
                                PersonalAge.setText(String.valueOf(personalInformation.getDob()));
                                PersonalGender.setText(personalInformation.getGender());
                                PersonalOccupations.setText(personalInformation.getOccupationId());
                                PersonalAnnualIncome.setText(personalInformation.getAnnualIncome()+" ₹");
                                PersonalStatus.setText(personalInformation.getStatus());
                            textView67.setText(personalInformation.getAddress());
                                PersonalState.setText(personalInformation.getState());
                                PersonalDistrict.setText(personalInformation.getDistrict());
                                PersonalCity.setText(personalInformation.getCity());
                                PersonalVillage.setText(personalInformation.getVillage()!=null ? personalInformation.getVillage() : "N/A");
                                PersonalPinCode.setText(personalInformation.getPinCode());

                                AssociateDetails associateDetails = result.getAssociateDetails();
                                SeniorCode.setText(associateDetails.getAssociateCode());
                                SeniorName.setText(associateDetails.getAssociateName());
                                SeniorMobile.setText(associateDetails.getMobileNo());
                                SeniorCader.setText(associateDetails.getCarder());

                                GuarantorDetail guarantorDetail = result.getGuarantorDetail();
                                if (guarantorDetail!=null){
                                    if (guarantorDetail.getFirstName()!=null){
                                        GuaratorFullname.setText(guarantorDetail.getFirstName()!=null ? guarantorDetail.getFirstName() :"N/A");
                                    }
                                    GuaratorMobile.setText(guarantorDetail.getFirstMobileNo()!=null ? guarantorDetail.getFirstMobileNo() : "N/A");
                                    GuaratorAddress.setText(guarantorDetail.getFirstAddress()!=null ? guarantorDetail.getFirstAddress() : "N/A");
                                    if (guarantorDetail.getSecondName()!=null){
                                        textViewFullName2.setText(guarantorDetail.getSecondName()!=null ? guarantorDetail.getSecondName() : "N/A");
                                        textViewAddress2.setText(guarantorDetail.getSecondAddress()!=null ? guarantorDetail.getSecondAddress() :"N/A");
                                        textViewMobile2.setText(guarantorDetail.getSecondMobileNo()!=null ? guarantorDetail.getSecondMobileNo() :"N/A");
                                    }
                                   else{
                                        head_2ndgurantor.setVisibility(View.GONE);
                                        fullname_gurantor_head.setVisibility(View.GONE);
                                        mobile_gurantor_head.setVisibility(View.GONE);
                                        address_gurantor_head.setVisibility(View.GONE);
                                        textViewFullName2.setVisibility(View.GONE);
                                        textViewAddress2.setVisibility(View.GONE);
                                        textViewMobile2.setVisibility(View.GONE);
                                    }
                                }
                                IdProofDetail idProofDetail  =result.getIdProofDetail();
                                FirstDocType.setText(idProofDetail.getFirstIdTypeId());
                                FirstIdNo.setText(idProofDetail.getFirstIdNo());
                                FirstAddress.setText(idProofDetail.getFirstAddress());
                                SecoundDocType.setText(idProofDetail.getSecondIdTypeId());
                                SecoundIdNo.setText(idProofDetail.getSecondIdNo());
                                SecoundAddress.setText(idProofDetail.getSecondAddress());
                                List<DependentDetail> dependentDetail = result.getDependentDetail();
                                if (dependentDetail!=null){
                                    if (dependentDetail.size() > 0 ){
                                        for (DependentDetail detail : dependentDetail){
                                            dependentsModels.add(new DependentsModel(
                                                    String.valueOf(detail.getId()),
                                                    detail.getName(),
                                                    detail.getDependentType(),
                                                    detail.getGender(),
                                                    detail.getRelation(),
                                                    detail.getMaritalStatus(),
                                                    detail.getLivingWithAssociate(),
                                                    detail.getMonthlyIncome()
                                            ));
                                        }
                                        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                        associate_dependentslListAdapter = new Associate_DependentslListAdapter(getActivity(), dependentsModels);
                                        recyclerView.setAdapter(associate_dependentslListAdapter);
                                    }else {
                                        cardView1q35.setVisibility(View.GONE);
                                    }
                                }



                          /*  }else {
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }*/
                        }else {
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<AssociateDetailsViewResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });
        }catch (Exception ec){

        }

    }
}