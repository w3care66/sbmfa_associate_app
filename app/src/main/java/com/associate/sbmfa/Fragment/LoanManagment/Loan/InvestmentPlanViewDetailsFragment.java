package com.associate.sbmfa.Fragment.LoanManagment.Loan;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Loan.Model.ApplicantDepositeDetailModel;


import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.ApplicantDepositeDetail;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.BankDetail;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.Details;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.Documents;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.EmploymentDetails;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.InvestmentPlanDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.LoanDetails;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.CustomDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentPlanViewDetailsFragment extends Fragment implements ApplicantDepositeDetailAdapater.EventListener {
    View RootView;
    TextView mTextView2;
    ImageView mImageView;
    ImageView mImageView10;
    ConstraintLayout mConstraintLayout2;
    TextView mTextView13;
    TextView mApplicantIdDetailsTV;
    TextView mLoanAmount;
    TextView mTextView34;
    TextView mNameDetailsTV;
    TextView mTextView35;
    TextView mEmiModeAmount;
    TextView mPurposeLoan;
    TextView mTextView37;
    TextView mAssociateId;
    TextView mApplicantEmailDetailsTV;
    TextView mTextView38;
    TextView mAssocaiteCode;
    TextView mGoinvestmentplanTV;
    TextView mTextView39;
    TextView mCarder;
    TextView mIfscCode;
    TextView mBankName;
    TextView mApplicantId;
    LinearLayout mGuar;
    CardView mCardView1;
    TextView mTextView113;
    TextView mFirst;
    TextView mApplicantIdDetails;
    TextView mFirstName;
    TextView mLast;
    TextView mLastName;
    TextView mNameDetails;
    TextView mEmail;
    TextView mTextView371;
    TextView mGobarnchNameDetails;
    TextView mMobile;
    TextView mTextView38Mobile;
    TextView mApplicantEmailDetails;
    TextView mAge;
    TextView mTextView40age;
    TextView mApplicantMobileDetails;
    TextView mGender;
    TextView mTextView41gender;
    TextView mApplicantAddressParmanet;
    TextView mAccupations;
    TextView mTextView42accupations;
    TextView mApplicantAddressTemp;
    TextView mAnnualIncome;
    TextView mTextView43annualIncome;
    TextView mOccupation;
    TextView mStatus;
    TextView mTextView44status;
    TextView mOrganization;
    TextView mState;
    TextView mTextView44state;
    TextView mDesignation;
    TextView mDistrict;
    TextView mTextView44district;
    TextView mMonthlyIncome;
    TextView mCity;
    TextView mTextView44city;
    TextView mYearForm;
    TextView mVillage;
    TextView mTextView44village;
    TextView mBankName1;
    TextView mPincode;
    TextView mTextView44pincode;
    TextView mBankAccountNumber;
    TextView mApplicantifscCode;
    TextView mChequeNumber1;
    TextView mChequeNumber2;
    TextView mIdproof;
    TextView mIdNumber;
    TextView mUploadFile;
    TextView mAddressProof;
    TextView mAddressIdnumber;
    TextView mAddressuploadfile;
    TextView mIncome;
    TextView mIncomeuploadfile;
    TextView mSecurity;

    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    private RecyclerView recyclerView;
    ArrayList<ApplicantDepositeDetailModel>  applicantDepositeDetailModels=new ArrayList<>();
    private ApplicantDepositeDetailAdapater applicantDepositeDetailAdapater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_loan_view_details, container, false);


        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        getdetails(member_id,token,getArguments().getString("id"));
        mTextView2 = RootView.findViewById(R.id.textView2);
        mImageView = RootView.findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        mImageView10 = RootView.findViewById(R.id.imageView10);
        mConstraintLayout2 = RootView.findViewById(R.id.constraintLayout2);
        mTextView13 = RootView.findViewById(R.id.textView13);
        mApplicantIdDetailsTV = RootView.findViewById(R.id.applicantIdDetailsTV);
        mLoanAmount = RootView.findViewById(R.id.loanAmount);
        mTextView34 = RootView.findViewById(R.id.textView34);
        mNameDetailsTV = RootView.findViewById(R.id.nameDetailsTV);
        mTextView35 = RootView.findViewById(R.id.textView35);
        mEmiModeAmount = RootView.findViewById(R.id.EmiModeAmount);
        mPurposeLoan = RootView.findViewById(R.id.purposeLoan);
        mTextView37 = RootView.findViewById(R.id.textView37);
        mAssociateId = RootView.findViewById(R.id.associate_id);
        mApplicantEmailDetailsTV = RootView.findViewById(R.id.applicantEmailDetailsTV);
        mTextView38 = RootView.findViewById(R.id.textView38);
        mAssocaiteCode = RootView.findViewById(R.id.assocaiteCode);
        mGoinvestmentplanTV = RootView.findViewById(R.id.goinvestmentplanTV);
        mTextView39 = RootView.findViewById(R.id.textView39);
        mCarder = RootView.findViewById(R.id.carder);
        mIfscCode = RootView.findViewById(R.id.ifscCode);
        mBankName = RootView.findViewById(R.id.bankName);
        mApplicantId = RootView.findViewById(R.id.applicantId);
        mGuar = RootView.findViewById(R.id.guar);
        mCardView1 = RootView.findViewById(R.id.cardView1);
        mTextView113 = RootView.findViewById(R.id.textView113);
        mFirst = RootView.findViewById(R.id.first_);
        mApplicantIdDetails = RootView.findViewById(R.id.applicantIdDetails);
        mFirstName = RootView.findViewById(R.id.first_name);
        mLast = RootView.findViewById(R.id.last_);
        mLastName = RootView.findViewById(R.id.last_name);
        mNameDetails = RootView.findViewById(R.id.nameDetails);
        mEmail = RootView.findViewById(R.id.email_);
        mTextView371 = RootView.findViewById(R.id.textView371);
        mGobarnchNameDetails = RootView.findViewById(R.id.gobarnchNameDetails);
        mMobile = RootView.findViewById(R.id.Mobile_);
        mTextView38Mobile = RootView.findViewById(R.id.textView38_Mobile_);
        mApplicantEmailDetails = RootView.findViewById(R.id.applicantEmailDetails);
        mAge = RootView.findViewById(R.id.age_);
        mTextView40age = RootView.findViewById(R.id.textView40age_);
        mApplicantMobileDetails = RootView.findViewById(R.id.applicantMobileDetails);
        mGender = RootView.findViewById(R.id.gender_);
        mTextView41gender = RootView.findViewById(R.id.textView41gender);
        mApplicantAddressParmanet = RootView.findViewById(R.id.applicantAddressParmanet);
        mAccupations = RootView.findViewById(R.id.accupations_);
        mTextView42accupations = RootView.findViewById(R.id.textView42accupations_);
        mApplicantAddressTemp = RootView.findViewById(R.id.applicantAddressTemp);
        mAnnualIncome = RootView.findViewById(R.id.annual_income_);
        mTextView43annualIncome = RootView.findViewById(R.id.textView43annual_income_);
        mOccupation = RootView.findViewById(R.id.occupation);
        mStatus = RootView.findViewById(R.id.status_);
        mTextView44status = RootView.findViewById(R.id.textView44status_);
        mOrganization = RootView.findViewById(R.id.organization);
        mState = RootView.findViewById(R.id.state_);
        mTextView44state = RootView.findViewById(R.id.textView44state_);
        mDesignation = RootView.findViewById(R.id.designation);
        mDistrict = RootView.findViewById(R.id.district_);
        mTextView44district = RootView.findViewById(R.id.textView44district_);
        mMonthlyIncome = RootView.findViewById(R.id.monthly_income);
        mCity = RootView.findViewById(R.id.city_);
        mTextView44city = RootView.findViewById(R.id.textView44city_);
        mYearForm = RootView.findViewById(R.id.year_form);
        mVillage = RootView.findViewById(R.id.village_);
        mTextView44village = RootView.findViewById(R.id.textView44village_);
        mBankName1 = RootView.findViewById(R.id.bankName1);
        mPincode = RootView.findViewById(R.id.pincode_);
        mTextView44pincode = RootView.findViewById(R.id.textView44pincode_);
        mBankAccountNumber = RootView.findViewById(R.id.bankAccountNumber);
        mApplicantifscCode = RootView.findViewById(R.id.applicantifscCode);
        mChequeNumber1 = RootView.findViewById(R.id.chequeNumber1);
        mChequeNumber2 = RootView.findViewById(R.id.chequeNumber2);
        mIdproof = RootView.findViewById(R.id.idproof);
        mIdNumber = RootView.findViewById(R.id.idNumber);
        mUploadFile = RootView.findViewById(R.id.uploadFile);
        mAddressProof = RootView.findViewById(R.id.addressProof);
        mAddressIdnumber = RootView.findViewById(R.id.addressIdnumber);
        mAddressuploadfile = RootView.findViewById(R.id.addressuploadfile);
        mIncome = RootView.findViewById(R.id.income);
        mIncomeuploadfile = RootView.findViewById(R.id.incomeuploadfile);
        mSecurity = RootView.findViewById(R.id.security);
        recyclerView=RootView.findViewById(R.id.recyclerView);






        return RootView;
    }
    private void getdetails(String member_id, String token, String id) {
        try {
            googleProgressDialog.show1("Loading data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
            RestHandler.getApiService().INVESTMENT_LONE_DETAILS_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _id
            ).enqueue(new Callback<InvestmentPlanDetailsResponse>() {
                @Override
                public void onResponse(Call<InvestmentPlanDetailsResponse> call, Response<InvestmentPlanDetailsResponse> response) {
                    if (response!=null){
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200){
                            if (response.body().getAssociateStatus() == 1){
                                LoanDetails loanDetails=response.body().getResult().getDetail().getLoanDetails();
                                mLoanAmount.setText(loanDetails.getLoanAmount()+" ₹");
                                mEmiModeAmount.setText(loanDetails.getEMIModeOption());
                                mAssociateId.setText(loanDetails.getLoanPurpose());
                                mAssocaiteCode.setText(loanDetails.getAssociateCode());
                                mCarder.setText(String.valueOf(loanDetails.getBankAcount()));
                                mIfscCode.setText(String.valueOf(loanDetails.getIfsc()));
                                mBankName.setText(String.valueOf(loanDetails.getBankName()));
                                mApplicantId.setText(loanDetails.getApplicantId());

                                Details applicant=response.body().getResult().getDetail().getApplicant().get(0).getDetails();
                                mApplicantIdDetails.setText(applicant.getApplicantId());
                                mNameDetails.setText(applicant.getName());
                                mGobarnchNameDetails.setText(applicant.getFatherName());
                                mApplicantEmailDetails.setText(String.valueOf(applicant.getEmailId()));
                                mApplicantMobileDetails.setText(applicant.getMobileNo());
                                mApplicantAddressParmanet.setText(applicant.getPermanentAddress());
                                mApplicantAddressTemp.setText(applicant.getTemporaryAddress());

                                EmploymentDetails employmentDetails=response.body().getResult().getDetail().getApplicant().get(0).getEmploymentDetails();
                                mOccupation.setText(employmentDetails.getOccupation());
                                mOrganization.setText(String.valueOf(employmentDetails.getOrganization()));
                               // mDesignation.setText(employmentDetails.getDesignation().toString());
                                mMonthlyIncome.setText(employmentDetails.getMonthlyIncome()+" ₹");
                                mYearForm.setText(employmentDetails.getYearFrom());

                                BankDetail bankDetail=response.body().getResult().getDetail().getApplicant().get(0).getBankDetail();
                                mBankName1.setText(bankDetail.getBankName());
                                mBankAccountNumber.setText(bankDetail.getBankAc());
                                mApplicantifscCode.setText(bankDetail.getIfsc());
//                                mChequeNumber1.setText(bankDetail.getCheque1().toString());
                            //    mChequeNumber2.setText(bankDetail.getCheque2().toString());

                                final Documents documents=response.body().getResult().getDetail().getApplicant().get(0).getDocuments();
                                mIdproof.setText(documents.getIdProof());
                                mIdNumber.setText(documents.getIdNo());



                                if (documents.getUploadFile().size() > 0){
                                    mUploadFile.setText(documents.getUploadFile().get(0).getName());
                                    mUploadFile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mUploadFile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd = new CustomDialog(getActivity(), documents.getUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                });
                            }
                                if (documents.getAddressUploadFile().size() > 0){
                                    mAddressuploadfile.setText(documents.getAddressUploadFile().get(0).getName());
                                    mAddressuploadfile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mAddressuploadfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd = new CustomDialog(getActivity(), documents.getAddressUploadFile().get(0).getPath());
                                            cdd.show();
                                        }

                                });
                            }


                            mAddressProof.setText(documents.getAddressProof());
                                mAddressIdnumber.setText(documents.getAddressIdProof());
                                mIncome.setText(documents.getIncome());

                                if(documents.getIncomeUploadFile().size() > 0) {
                                    //mDocumentuploadFiletvv.setText(documents2.getIncomeUploadFile().get(0).getName());
                                    mIncomeuploadfile.setText(documents.getIncomeUploadFile().get(0).getName());
                                    mIncomeuploadfile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mIncomeuploadfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd = new CustomDialog(getActivity(), documents.getIncomeUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }


                                if (documents.getIncomeUploadFile().size() > 0){

                                }

                                mSecurity.setText(documents.getSecurity());

                                List<ApplicantDepositeDetail> applicantDepositeDetails=response.body().getResult().getDetail().getApplicantDepositeDetail();
                                for(ApplicantDepositeDetail applicantDepositeDetail : applicantDepositeDetails) {
                                    applicantDepositeDetailModels.add(new ApplicantDepositeDetailModel(applicantDepositeDetail.getScheme(), applicantDepositeDetail.getAccountId(), applicantDepositeDetail.getOpenDate(), applicantDepositeDetail.getDueDate(), applicantDepositeDetail.getDeposit(), applicantDepositeDetail.getTenure().toString(), applicantDepositeDetail.getLoanAmount().toString()));
                                }
                                applicantDepositeDetailAdapater= new ApplicantDepositeDetailAdapater(getActivity(),applicantDepositeDetailModels, InvestmentPlanViewDetailsFragment.this);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(applicantDepositeDetailAdapater);
                            }else {

                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
                        }else {

                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {

                        googleProgressDialog.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<InvestmentPlanDetailsResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();

                }
            });
        }catch (Exception ex){
        }
    }
    @Override
    public void onEvent_CardClick(String id, String amount) {

    }
}