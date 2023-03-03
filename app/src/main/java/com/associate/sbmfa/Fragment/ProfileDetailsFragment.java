package com.associate.sbmfa.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_DependentslListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.DependentsModel;
import com.associate.sbmfa.Respones.Profile.DependentDetails;
import com.associate.sbmfa.Respones.Profile.ProfileResponse;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileDetailsFragment extends Fragment {
    View view;
    ProgressBar progressBar;
    HashMap<String ,String > UserData;
    String member_id;
    TextView member_full_nameTV,form_noTv,join_dateTv,member_idTv,branch_miTv,member_f_nameTv,member_l_nameTv,emailTv,mobileTv,genderTv,dobTv,
            occuptionTv,annual_incomeTv,mother_nameTv,father_nameTv,marital_statsusTv,anniversary_dateTv,religionTv,categoriesTv,statusTv,aadharcardTv,pancardTv,
            nominee_full_nameTv,textViewDob,relationshipTv,nominee_genderTv,nominee_dobTv,nominee_ageTv,nominee_mobileTv,nominee_minorTv,aadharCardTvv,PanCardTvv,
            addressTX,stateTX,districtTX,cityTX,VillageTx,PinTx,bankNameTX,BranchNameTX,AccountNoTX,IfScTX,bankAddressTx,DocumentType,
            IDNoTX,AddressIdTX,DocumentType2,IDNoTX2,AddressTX2,AssociateCodeTX,AssociateNameTX;;
    CircleImageView secondimag;
    ImageView signture;
    SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    String token="",id="";
    ImageView imageViewBack;
    TextView textViewTitle;
    GoogleProgressDialog googleProgressDialog;
    Associate_DependentslListAdapter associate_dependentslListAdapter;
    ArrayList<DependentsModel> dependentsModels =new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.user_profile, container, false);
//        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        form_noTv=(TextView) view.findViewById(R.id.formNotv) ;
        join_dateTv=(TextView) view.findViewById(R.id.joindate) ;
        member_idTv=(TextView) view.findViewById(R.id.memberIdTv) ;
        branch_miTv=(TextView) view.findViewById(R.id.branchMiTv) ;
        member_f_nameTv=(TextView) view.findViewById(R.id.fnametv) ;
        member_l_nameTv=(TextView) view.findViewById(R.id.lnametv) ;
        textViewTitle=(TextView) view.findViewById(R.id.textView2) ;
        emailTv=(TextView) view.findViewById(R.id.emailtv) ;
        mobileTv=(TextView) view.findViewById(R.id.mobileetv) ;
        genderTv=(TextView) view.findViewById(R.id.gendertv) ;
        dobTv=(TextView) view.findViewById(R.id.dobtv) ;
        occuptionTv=(TextView) view.findViewById(R.id.occupationtv) ;
        annual_incomeTv=(TextView) view.findViewById(R.id.annualtv) ;
        mother_nameTv=(TextView) view.findViewById(R.id.mothernametv) ;
        father_nameTv=(TextView) view.findViewById(R.id.fathernametv) ;
        marital_statsusTv=(TextView) view.findViewById(R.id.maritaltv) ;
        anniversary_dateTv=(TextView) view.findViewById(R.id.anniversarytv) ;
        religionTv=(TextView) view.findViewById(R.id.regisiontv) ;
        categoriesTv=(TextView) view.findViewById(R.id.categoriestv) ;
        statusTv=(TextView) view.findViewById(R.id.status) ;
        aadharcardTv=(TextView) view.findViewById(R.id.aadharCardtv) ;
        pancardTv=(TextView) view.findViewById(R.id.pancardtv) ;
        nominee_full_nameTv=(TextView) view.findViewById(R.id.fullnametvv) ;
        relationshipTv=(TextView) view.findViewById(R.id.releationshiptvv) ;
        nominee_genderTv=(TextView) view.findViewById(R.id.gendertvvv) ;
        nominee_dobTv=(TextView) view.findViewById(R.id.dobtvvv) ;
        nominee_ageTv=(TextView) view.findViewById(R.id.agetvvv) ;
        nominee_mobileTv=(TextView) view.findViewById(R.id.mobiletvvv) ;
        nominee_minorTv=(TextView) view.findViewById(R.id.marialv) ;
        aadharCardTvv=(TextView) view.findViewById(R.id.aadharCardtvv) ;
        PanCardTvv=(TextView) view.findViewById(R.id.pancardtvv) ;
        textViewDob=(TextView) view.findViewById(R.id.dob) ;
        recyclerView = view.findViewById(R.id.recyclerView);
        addressTX=(TextView) view.findViewById(R.id.addressTX) ;
        stateTX=(TextView) view.findViewById(R.id.stateTX) ;
        districtTX=(TextView) view.findViewById(R.id.districtTX) ;
        cityTX=(TextView) view.findViewById(R.id.cityTX) ;
        VillageTx=(TextView) view.findViewById(R.id.VillageTx) ;
        PinTx=(TextView) view.findViewById(R.id.PinTx) ;

        bankNameTX=(TextView) view.findViewById(R.id.bankNameTX) ;
        BranchNameTX=(TextView) view.findViewById(R.id.BranchNameTX) ;
        AccountNoTX=(TextView) view.findViewById(R.id.AccountNoTX) ;
        IfScTX=(TextView) view.findViewById(R.id.IfScTX) ;
        bankAddressTx=(TextView) view.findViewById(R.id.bankAddressTx) ;


        DocumentType=(TextView) view.findViewById(R.id.DocumentType) ;
        IDNoTX=(TextView) view.findViewById(R.id.IDNoTX) ;
        AddressIdTX=(TextView) view.findViewById(R.id.AddressIdTX) ;
        DocumentType2=(TextView) view.findViewById(R.id.DocumentType2) ;
        IDNoTX2=(TextView) view.findViewById(R.id.IDNoTX2) ;
        AddressTX2=(TextView) view.findViewById(R.id.AddressTX2) ;

        AssociateCodeTX=(TextView) view.findViewById(R.id.AssociateCodeTX) ;
        AssociateNameTX=(TextView) view.findViewById(R.id.AssociateNameTX) ;

        secondimag=(CircleImageView) view.findViewById(R.id.profile_image) ;
        signture=(ImageView) view.findViewById(R.id.signture) ;
        googleProgressDialog = new GoogleProgressDialog(getContext());


        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);

        textViewTitle.setText("Profile Details");

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
       // id=getArguments().getString("id");
        getMemberDetails(member_id,member_id);
        imageViewBack = view.findViewById(R.id.imageView);
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
        return view;
    }

    private void getMemberDetails(String member_id,String id) {
        googleProgressDialog.show1("Loading data...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        Call<ProfileResponse> applicationsListResponesCall = RestHandler.getApiService().PROFILE_RESPONSE_CALL(_member_id,_id,_token);
        applicationsListResponesCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response!=null){
                    googleProgressDialog.dismiss();
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                       if(response.body().getResult().getUserProfile().getPersonalInformation().getLastName()!=null) {
                           member_full_nameTV.setText(response.body().getResult().getUserProfile().getPersonalInformation().getFirstName() + " " + response.body().getResult().getUserProfile().getPersonalInformation().getLastName());
                        }
                       else{
                             member_full_nameTV.setText(response.body().getResult().getUserProfile().getPersonalInformation().getFirstName());
                       }
                        if(response.body().getResult().getUserProfile().getMemberFormInformation().getFormNo()!=null) {
                            form_noTv.setText(response.body().getResult().getUserProfile().getMemberFormInformation().getFormNo().toString());
                        }
                        if(response.body().getResult().getUserProfile().getMemberFormInformation().getJoinDate()!=null){
//
                            join_dateTv.setText(response.body().getResult().getUserProfile().getMemberFormInformation().getJoinDate().toString());
                        }

                        if(response.body().getResult().getUserProfile().getMemberFormInformation().getMemberId()!=null) {
                            member_idTv.setText(response.body().getResult().getUserProfile().getMemberFormInformation().getMemberId().toString());
                        }

                        if(response.body().getResult().getUserProfile().getMemberFormInformation().getBranchMi()!=null) {
                            branch_miTv.setText(response.body().getResult().getUserProfile().getMemberFormInformation().getBranchMi().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getFirstName()!=null) {
                            member_f_nameTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getFirstName());
                        }

                        if(response.body().getResult().getUserProfile().getPersonalInformation().getLastName()!=null) {
                            member_l_nameTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getLastName().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getEmail()!=null){
                            emailTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getEmail().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getMobileNo()!=null) {
                            mobileTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getMobileNo().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getGender()!=null) {
                            genderTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getGender().toString());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getDob()!=null) {
                            dobTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getDob().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getOccupationId()!=null) {
                            occuptionTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getOccupationId().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getAnnualIncome()!=null) {
                            annual_incomeTv.setText("\u20B9 "+response.body().getResult().getUserProfile().getPersonalInformation().getAnnualIncome().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getMotherName()!=null){
                            mother_nameTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getMotherName().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getFatherHusband()!=null) {
                            father_nameTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getFatherHusband().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getMaritalStatus()!=null) {
                            marital_statsusTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getMaritalStatus().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getAnniversaryDate()!=null) {
                            anniversary_dateTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getAnniversaryDate().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getReligion()!=null) {
                            religionTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getReligion().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getSpecialCategory()!=null) {
                            categoriesTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getSpecialCategory().toString());
                        }
                        if(response.body().getResult().getUserProfile().getPersonalInformation().getStatus()!=null) {
                            statusTv.setText(response.body().getResult().getUserProfile().getPersonalInformation().getStatus().toString());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getName()!=null) {
                            nominee_full_nameTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getName());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getRelation()!=null) {
                            relationshipTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getRelation().toString());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getGender()!=null) {
                            nominee_genderTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getGender().toString());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getDob()!=null) {
                            nominee_dobTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getDob());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getAge()!=null) {
                            nominee_ageTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getAge().toString());
                        }

                        if(response.body().getResult().getUserProfile().getPersonalInformation().getAge()!=null) {
                            textViewDob.setText(response.body().getResult().getUserProfile().getPersonalInformation().getAge().toString());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getMobileNo()!=null) {
                            nominee_mobileTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getMobileNo());
                        }
                        if(response.body().getResult().getUserProfile().getNomineeDetail().getIsMinor()!=null) {
                            nominee_minorTv.setText(response.body().getResult().getUserProfile().getNomineeDetail().getIsMinor().toString());
                        }
                        if(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdNo()!=null) {
                            aadharcardTv.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdNo().toString());
                        }
                        if(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdTypeId()!=null) {
                            aadharCardTvv.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdTypeId().toString());
                        }

                       if (response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdTypeId() != null) {
                                PanCardTvv.setText(response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdTypeId().toString());
                       }

                       if (response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdNo() != null) {
                                pancardTv.setText(response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdNo().toString());
                       }
                        if (response.body().getResult().getUserProfile().getAssociateDetails().getAssociateCode() != null) {
                            AssociateCodeTX.setText(response.body().getResult().getUserProfile().getAssociateDetails().getAssociateCode().toString());
                        }
                        if (response.body().getResult().getUserProfile().getAssociateDetails().getAssociateName() != null) {
                            AssociateNameTX.setText(response.body().getResult().getUserProfile().getAssociateDetails().getAssociateName().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getFirstAddress() != null) {
                            addressTX.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstAddress().toString());
                        }
                        if (response.body().getResult().getUserProfile().getPersonalInformation().getState() != null) {
                            stateTX.setText(response.body().getResult().getUserProfile().getPersonalInformation().getState().toString());
                        }
                        if (response.body().getResult().getUserProfile().getPersonalInformation().getDistrict() != null) {
                            districtTX.setText(response.body().getResult().getUserProfile().getPersonalInformation().getDistrict().toString());
                        }
                        if (response.body().getResult().getUserProfile().getPersonalInformation().getCity() != null) {
                            cityTX.setText(response.body().getResult().getUserProfile().getPersonalInformation().getCity().toString());
                        }
                        if (response.body().getResult().getUserProfile().getPersonalInformation().getVillage() != null) {
                            VillageTx.setText(response.body().getResult().getUserProfile().getPersonalInformation().getVillage().toString());
                        }
                        if (response.body().getResult().getUserProfile().getPersonalInformation().getPinCode() != null) {
                            PinTx.setText(response.body().getResult().getUserProfile().getPersonalInformation().getPinCode().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdNo() != null) {
                            IDNoTX.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdNo().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdTypeId() != null) {
                            DocumentType.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstIdTypeId().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getFirstAddress() != null) {
                            AddressIdTX.setText(response.body().getResult().getUserProfile().getIdProofDetail().getFirstAddress().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getSecondAddress() != null) {
                            AddressTX2.setText(response.body().getResult().getUserProfile().getIdProofDetail().getSecondAddress().toString());
                        }
                        if (response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdTypeId() != null) {
                            DocumentType2.setText(response.body().getResult().getUserProfile().getIdProofDetail().getSecondIdTypeId().toString());
                        }



                        Glide
                                .with(getActivity())
                                .load(response.body().getResult().getUserProfile().getProfileImge())
                                .centerCrop()
                                .into(secondimag);
                        Glide
                                .with(getActivity())
                                .load(response.body().getResult().getUserProfile().getSignatureurl())
                                .centerCrop()
                                .into(signture);
                      //  progressBar.setVisibility(View.GONE);
                       // getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        List<DependentDetails> dependentDetail = response.body().getResult().getUserProfile().getDependentDetail();
                        if (dependentDetail!=null){
                            if (dependentDetail.size() > 0 ){
                                for (DependentDetails detail : dependentDetail){
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

                            }
                        }



                    }else {
                       // progressBar.setVisibility(View.GONE);
                       // getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                   // progressBar.setVisibility(View.GONE);
                  //  getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
              //  progressBar.setVisibility(View.GONE);
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });




    }

}