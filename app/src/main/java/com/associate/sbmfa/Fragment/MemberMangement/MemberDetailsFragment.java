package com.associate.sbmfa.Fragment.MemberMangement;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.MemberMangement.response.BankDetail;
import com.associate.sbmfa.Fragment.MemberMangement.response.MemberDetailsResponse;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.HashMap;

public class MemberDetailsFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    HashMap<String ,String > UserData;
    String member_id;
    TextView member_full_nameTV,form_noTv,join_dateTv,member_idTv,branch_miTv,member_f_nameTv,member_l_nameTv,emailTv,mobileTv,genderTv,dobTv,
            occuptionTv,annual_incomeTv,mother_nameTv,father_nameTv,marital_statsusTv,anniversary_dateTv,religionTv,categoriesTv,statusTv,aadharcardTv,pancardTv,
            nominee_full_nameTv,textViewDob,relationshipTv,nominee_genderTv,nominee_dobTv,nominee_ageTv,nominee_mobileTv,nominee_minorTv,aadharCardTvv,PanCardTvv,
            addressTX,stateTX,districtTX,cityTX,VillageTx,PinTx,bankNameTX,BranchNameTX,AccountNoTX,IfScTX,bankAddressTx,DocumentType,
            IDNoTX,AddressIdTX,DocumentType2,IDNoTX2,AddressTX2,AssociateCodeTX,AssociateNameTX;
    ;
    CircleImageView secondimag;
    ImageView signture;

    SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    String token="",id="";
    ImageView imageViewBack;
    CardView bank_info_card;
    GoogleProgressDialog googleProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_member_details, container, false);
//        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        googleProgressDialog = new GoogleProgressDialog(getContext());
        bank_info_card=view.findViewById(R.id.bank_info_card);
        form_noTv=(TextView) view.findViewById(R.id.formNotv) ;
        join_dateTv=(TextView) view.findViewById(R.id.joindate) ;
        member_idTv=(TextView) view.findViewById(R.id.memberIdTv) ;
        branch_miTv=(TextView) view.findViewById(R.id.branchMiTv) ;
        member_f_nameTv=(TextView) view.findViewById(R.id.fnametv) ;
        member_l_nameTv=(TextView) view.findViewById(R.id.lnametv) ;
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
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
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

        id=getArguments().getString("id");

        getMemberDetails(member_id,id);

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
        googleProgressDialog.show1("Loading Data....");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        Call<MemberDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().MEMBER_DETAILS_RESPONES_CALL(_member_id,_id,_token);
        applicationsListResponesCall.enqueue(new Callback<MemberDetailsResponse>() {
            @Override
            public void onResponse(Call<MemberDetailsResponse> call, Response<MemberDetailsResponse> response) {
                if (response!=null){
                    googleProgressDialog.dismiss();
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        if(response.body().getResult().getPersonalInformation().getLastName()!=null) {
                            member_full_nameTV.setText(response.body().getResult().getPersonalInformation().getFirstName() + " " + response.body().getResult().getPersonalInformation().getLastName());
                        }else{
                            member_full_nameTV.setText(response.body().getResult().getPersonalInformation().getFirstName());

                        }
                        if(response.body().getResult().getMemberFormInformation().getFormNo()!=null) {
                            form_noTv.setText(response.body().getResult().getMemberFormInformation().getFormNo().toString());
                        }




                        if(response.body().getResult().getMemberFormInformation().getJoinDate()!=null){
//
                            join_dateTv.setText(response.body().getResult().getMemberFormInformation().getJoinDate().toString());
                        }

                        if(response.body().getResult().getMemberFormInformation().getMemberId()!=null) {
                            member_idTv.setText(response.body().getResult().getMemberFormInformation().getMemberId().toString());
                        }
                        if(response.body().getResult().getMemberFormInformation().getBranchMi()!=null) {
                            branch_miTv.setText(response.body().getResult().getMemberFormInformation().getBranchMi().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getFirstName()!=null) {
                            member_f_nameTv.setText(response.body().getResult().getPersonalInformation().getFirstName());
                        }

                        if(response.body().getResult().getPersonalInformation().getLastName()!=null) {
                            member_l_nameTv.setText(response.body().getResult().getPersonalInformation().getLastName().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getEmail()!=null){
                            emailTv.setText(response.body().getResult().getPersonalInformation().getEmail().toString());
                        }

                        if(response.body().getResult().getPersonalInformation().getDob()!=null) {
                            textViewDob.setText(response.body().getResult().getPersonalInformation().getAge().toString());
                            //textViewDob.setText(response.body().getResult().getPersonalInformation().getDob().toString());
                        }

                        if(response.body().getResult().getPersonalInformation().getMobileNo()!=null) {
                            mobileTv.setText(response.body().getResult().getPersonalInformation().getMobileNo().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getGender()!=null) {
                            genderTv.setText(response.body().getResult().getPersonalInformation().getGender().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getAge()!=null) {
                            dobTv.setText(response.body().getResult().getPersonalInformation().getDob()!=null ? response.body().getResult().getPersonalInformation().getDob() : "N/A");
                           // dobTv.setText(response.body().getResult().getPersonalInformation().getAge() > 0 ? response.body().getResult().getPersonalInformation().getAge().toString()+" Years":"");
                        }
                        if(response.body().getResult().getPersonalInformation().getOccupationId()!=null) {
                            occuptionTv.setText(response.body().getResult().getPersonalInformation().getOccupationId().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getAnnualIncome()!=null) {
                            annual_incomeTv.setText(response.body().getResult().getPersonalInformation().getAnnualIncome().toString()+" \u20B9");
                        }
                        if(response.body().getResult().getPersonalInformation().getMotherName()!=null){
                            mother_nameTv.setText(response.body().getResult().getPersonalInformation().getMotherName().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getFatherHusband()!=null) {
                            father_nameTv.setText(response.body().getResult().getPersonalInformation().getFatherHusband().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getMaritalStatus()!=null) {
                            marital_statsusTv.setText(response.body().getResult().getPersonalInformation().getMaritalStatus().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getAnniversaryDate()!=null) {
                            anniversary_dateTv.setText(response.body().getResult().getPersonalInformation().getAnniversaryDate().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getReligion()!=null) {
                            religionTv.setText(response.body().getResult().getPersonalInformation().getReligion().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getSpecialCategory()!=null) {
                            categoriesTv.setText(response.body().getResult().getPersonalInformation().getSpecialCategory().toString());
                        }
                        if(response.body().getResult().getPersonalInformation().getStatus()!=null) {
                            statusTv.setText(response.body().getResult().getPersonalInformation().getStatus().toString());
                        }
                        if(response.body().getResult().getNomineeDetail().getName()!=null) {
                            nominee_full_nameTv.setText(response.body().getResult().getNomineeDetail().getName());
                        }
                        if(response.body().getResult().getNomineeDetail().getRelation()!=null) {
                            relationshipTv.setText(response.body().getResult().getNomineeDetail().getRelation().toString());
                        }
                        if(response.body().getResult().getNomineeDetail().getGender()!=null) {
                            nominee_genderTv.setText(response.body().getResult().getNomineeDetail().getGender().toString());
                        }
                        if(response.body().getResult().getNomineeDetail().getDob()!=null) {
                            nominee_dobTv.setText(response.body().getResult().getNomineeDetail().getDob());
                        }
                        if(response.body().getResult().getNomineeDetail().getAge()!=null) {
                            nominee_ageTv.setText(response.body().getResult().getNomineeDetail().getAge() > 0 ? response.body().getResult().getNomineeDetail().getAge().toString()+" Years ":"");
                        }
                        if(response.body().getResult().getNomineeDetail().getMobileNo()!=null) {
                            nominee_mobileTv.setText(response.body().getResult().getNomineeDetail().getMobileNo());
                        }
                        if(response.body().getResult().getNomineeDetail().getIsMinor()!=null) {
                            nominee_minorTv.setText(response.body().getResult().getNomineeDetail().getIsMinor().toString());
                        }
                        if(response.body().getResult().getIdProofDetail().getFirstIdNo()!=null) {
                            aadharcardTv.setText(response.body().getResult().getIdProofDetail().getFirstIdNo().toString());
                        }
                        if(response.body().getResult().getIdProofDetail().getFirstIdTypeId()!=null) {
                            aadharCardTvv.setText(response.body().getResult().getIdProofDetail().getFirstIdTypeId().toString());
                        }

                        if (response.body().getResult().getIdProofDetail().getSecondIdTypeId() != null) {
                            PanCardTvv.setText(response.body().getResult().getIdProofDetail().getSecondIdTypeId().toString());
                        }

                        if (response.body().getResult().getIdProofDetail().getSecondIdNo() != null) {
                            pancardTv.setText(response.body().getResult().getIdProofDetail().getSecondIdNo().toString());
                        }
                        if (response.body().getResult().getAssociateDetails().getAssociateCode() != null) {
                            AssociateCodeTX.setText(response.body().getResult().getAssociateDetails().getAssociateCode().toString());
                        }
                        if (response.body().getResult().getAssociateDetails().getAssociateName() != null) {
                            AssociateNameTX.setText(response.body().getResult().getAssociateDetails().getAssociateName().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getFirstAddress() != null) {
                            addressTX.setText(response.body().getResult().getIdProofDetail().getFirstAddress().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getSecondIdNo() != null) {
                            IDNoTX2.setText(response.body().getResult().getIdProofDetail().getSecondIdNo().toString());
                        }
                        if (response.body().getResult().getPersonalInformation().getState() != null) {
                            stateTX.setText(response.body().getResult().getPersonalInformation().getState().toString());
                        }
                        if (response.body().getResult().getPersonalInformation().getDistrict() != null) {
                            districtTX.setText(response.body().getResult().getPersonalInformation().getDistrict().toString());
                        }
                        if (response.body().getResult().getPersonalInformation().getCity() != null) {
                            cityTX.setText(response.body().getResult().getPersonalInformation().getCity().toString());
                        }
                        if (response.body().getResult().getPersonalInformation().getVillage() != null) {
                            VillageTx.setText(response.body().getResult().getPersonalInformation().getVillage().toString());
                        }
                        if (response.body().getResult().getPersonalInformation().getPinCode() != null) {
                            PinTx.setText(response.body().getResult().getPersonalInformation().getPinCode().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getFirstIdNo() != null) {
                            IDNoTX.setText(response.body().getResult().getIdProofDetail().getFirstIdNo().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getFirstIdTypeId() != null) {
                            DocumentType.setText(response.body().getResult().getIdProofDetail().getFirstIdTypeId().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getFirstAddress() != null) {
                            AddressIdTX.setText(response.body().getResult().getIdProofDetail().getFirstAddress().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getSecondAddress() != null) {
                            AddressTX2.setText(response.body().getResult().getIdProofDetail().getSecondAddress().toString());
                        }
                        if (response.body().getResult().getIdProofDetail().getSecondIdTypeId() != null) {
                            DocumentType2.setText(response.body().getResult().getIdProofDetail().getSecondIdTypeId().toString());
                        }
                        BankDetail bankDetail=response.body().getResult().getBankDetail();


                        if (bankDetail.getAccountNo().equals("")){
                            bank_info_card.setVisibility(View.GONE);
                        }else{
                            bank_info_card.setVisibility(View.VISIBLE);
                            if (bankDetail.getAccountNo() != null) {

                                AccountNoTX.setText(bankDetail.getAccountNo().toString());
                            }
                            if (bankDetail.getBankName() != null) {
                                bankNameTX.setText(bankDetail.getBankName());
                            } if (bankDetail.getIfscCode() != null) {
                                IfScTX.setText(bankDetail.getIfscCode().toString());
                            } if (bankDetail.getBranchName() != null) {
                                BranchNameTX.setText(bankDetail.getBranchName().toString());
                            }  if (bankDetail.getAddress() != null) {
                                bankAddressTx.setText(bankDetail.getAddress());
                            }
                        }
                        Glide
                                .with(getActivity())
                                .load(response.body().getResult().getProfileImge())
                                .centerCrop()
                                .placeholder(R.drawable.profile_user)
                                .into(secondimag);
                        Glide
                                .with(getActivity())
                                .load(response.body().getResult().getSignatureurl())
                                .placeholder(R.drawable.signnature_place)
                                .error(R.drawable.signnature_place)
                                .centerCrop()
                                .into(signture);
                    }else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MemberDetailsResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });




    }

}