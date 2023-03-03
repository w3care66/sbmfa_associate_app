package com.associate.sbmfa.Fragment.LoanManagment.Loan;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.BankDetail;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.BankDetail__1;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.BankDetail__2;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Details;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Details__1;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Details__2;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Documents;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Documents__1;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.Documents__2;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.EmploymentDetails;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.EmploymentDetails__1;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.EmploymentDetails__2;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.LoanDetails;

import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.PersonalLoanResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.CustomDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.associate.sbmfa.Utils.Underlinetext;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonalLoanViewFragment extends Fragment {
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
        LinearLayout mGuar1;
        CardView mCardView12;
        TextView mDsdsds;
        TextView mSeniorCode;
        TextView mCoapplicantid;
        TextView mTextView34seniorCode;
        TextView mName;
        TextView mTextView35name;
        TextView mNameName;
        TextView mMobile1;
        TextView mTextView37mobile;
        TextView mMobileNo;
        TextView mCader;
        TextView mCoapplicantEmail;
        TextView mCaderNamee;
        TextView mCoapplicantMobile;
        TextView mCoapplicantaddressPermanet;
        TextView mCoapplicanttempAddress;
        TextView mCoapplicantOccupation;
        TextView mCoapplicantOrganization;
        TextView mCoapplicantDesignation;
        TextView mCoApplicantMonthlyIncome;
        TextView mCoApplicantYearFrom;
        TextView mBankBankName;
        TextView mBankBankAccountNumber;
        TextView mBankBankIfscCode;
        TextView mBankChequeeNumber1;
        TextView mBankChequeeNumber2;
        TextView mBankIdProof;
        TextView mBankIdNumber;
        TextView mBankStampr;
        TextView mBankUploadFile;
        TextView mBankAddressProof;
        TextView mBankIdNumberProof;
        TextView mBankUploadFile2;
        TextView mBankUnderTakingDoc;
        TextView mBankincome;
        TextView mBankUploadFile3;
        TextView mBankSecurity;
        LinearLayout mGuar3;
        CardView mCardView1q3;
        TextView mDsdsdssd;
        TextView mFullName;
        TextView mGuarantorId;
        TextView mTextView34fullName;
        TextView mMobileNo1;
        TextView mTextView35mobileNo;
        TextView mGuarantorName;
        TextView mAddress;
        TextView mTextView37address;
        TextView mGuarantorFatherName;
        TextView mFullName2;
        TextView mGuarantorId1;
        TextView mGuarantorEmail;
        TextView mMobileNo2;
        TextView mTextView35mobileNo2;
        TextView mGuarantorDob;
        TextView mAddress2;
        TextView mTextView37address2;
        TextView mGuarantorAddressPermanent;
        TextView mGuarantorAddressTemp;
        TextView mEmployeeOccuption;
        TextView mEmployeeOranization;
        TextView mEmployeeDesignation;
        TextView mEmployeemonthlyIncome;
        TextView mEmployeeYearFrom;
        TextView mEmployeeBankName;
        TextView mEmployeeBankAccountNumber;
        TextView mEmployeeBankIfscCode;
        TextView mEmployeeBankchquee1;
        TextView mEmployeeBankchquee2;
        TextView mDocumentIdProof;
        TextView mDocumentIdNumber;
        TextView mDocumentStampr;
        TextView mDocumentuploadFiletvv,mDocumentuploadFiletvvv;
        TextView mDocumentAddressProof;
        TextView mDocumentIdNumbertv;
        TextView mDocumentIdUploadFile;
        TextView mDocumentUnderTakingDoc;
        TextView mDocumentIncome;
        TextView mDocumentSecurity,remark,application_remark;
        LinearLayout mGuar4;
        CardView mCardView1q34;
        LinearLayout mMainCon;

    Underlinetext underlinetext;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    int page = 1;
    String langht = "15";
    ImageButton imageButtonPrv,imageButtonNext;
    Spinner spinnerLanght;
    ArrayList<String> dateStrings =new ArrayList<>();
    TextView textViewNotFound;
    ImageView imageViewBack;
    String id;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            RootView = inflater.inflate(R.layout.fragment_loan_view, container, false);
            sessionManager = new SessionManager(getContext());
            underlinetext = new Underlinetext();
            UserDataToken =sessionManager.getUserDetailsToken();
            token=UserDataToken.get(SessionManager.KEY_TOKEN);
            UserData =sessionManager.getUserDetails();
            member_id= UserData.get(SessionManager.KEY_member_id);
            googleProgressDialog = new GoogleProgressDialog(getContext());
            remark= RootView.findViewById(R.id.remark);
            application_remark= RootView.findViewById(R.id.application_remark);
            mTextView2 = RootView.findViewById(R.id.textView2);
            mImageView = RootView.findViewById(R.id.imageView);
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
            mGuar1 = RootView.findViewById(R.id.guar1);
            mCardView12 = RootView.findViewById(R.id.cardView12);
            mDsdsds = RootView.findViewById(R.id.dsdsds);
            mSeniorCode = RootView.findViewById(R.id.senior_code_);
            mCoapplicantid = RootView.findViewById(R.id.coapplicantid);
            mTextView34seniorCode = RootView.findViewById(R.id.textView34senior_code_);
            mName = RootView.findViewById(R.id.name_);
            mTextView35name = RootView.findViewById(R.id.textView35name_);
            mNameName = RootView.findViewById(R.id.name_name);
            mMobile = RootView.findViewById(R.id.mobile_);
            mTextView37mobile = RootView.findViewById(R.id.textView37mobile_);
            mMobileNo = RootView.findViewById(R.id.mobile_no);
            mCader = RootView.findViewById(R.id.Cader_);
            mCoapplicantEmail = RootView.findViewById(R.id.coapplicantEmail);
            mCaderNamee = RootView.findViewById(R.id.Cader_namee);
            mCoapplicantMobile = RootView.findViewById(R.id.coapplicantMobile);
            mCoapplicantaddressPermanet = RootView.findViewById(R.id.coapplicantaddressPermanet);
            mCoapplicanttempAddress = RootView.findViewById(R.id.coapplicanttempAddress);
            mCoapplicantOccupation = RootView.findViewById(R.id.coapplicantOccupation);
            mCoapplicantOrganization = RootView.findViewById(R.id.coapplicantOrganization);
            mCoapplicantDesignation = RootView.findViewById(R.id.coapplicantDesignation);
            mCoApplicantMonthlyIncome = RootView.findViewById(R.id.coApplicantMonthlyIncome);
            mCoApplicantYearFrom = RootView.findViewById(R.id.coApplicantYearFrom);
            mBankBankName = RootView.findViewById(R.id.bankBankName);
            mBankBankAccountNumber = RootView.findViewById(R.id.bankBankAccountNumber);
            mBankBankIfscCode = RootView.findViewById(R.id.bankBankIfscCode);
            mBankChequeeNumber1 = RootView.findViewById(R.id.bankChequeeNumber1);
            mBankChequeeNumber2 = RootView.findViewById(R.id.bankChequeeNumber2);
            mBankIdProof = RootView.findViewById(R.id.bankIdProof);
            mBankIdNumber = RootView.findViewById(R.id.bankIdNumber);
            mBankStampr = RootView.findViewById(R.id.bankStampr);
            mBankUploadFile = RootView.findViewById(R.id.bankUploadFile);
            mBankAddressProof = RootView.findViewById(R.id.bankAddressProof);
            mBankIdNumberProof = RootView.findViewById(R.id.bankIdNumberProof);
            mBankUploadFile2 = RootView.findViewById(R.id.bankUploadFile2);
            mBankUnderTakingDoc = RootView.findViewById(R.id.bankUnderTakingDoc);
            mBankincome = RootView.findViewById(R.id.bankincome);
            mBankUploadFile3 = RootView.findViewById(R.id.bankUploadFile3);
            mBankSecurity = RootView.findViewById(R.id.bankSecurity);
            mGuar3 = RootView.findViewById(R.id.guar3);
            mCardView1q3 = RootView.findViewById(R.id.cardView1q3);
            mDsdsdssd = RootView.findViewById(R.id.dsdsdssd);
            mFullName = RootView.findViewById(R.id.full_name_);
            mGuarantorId = RootView.findViewById(R.id.GuarantorId);
            mTextView34fullName = RootView.findViewById(R.id.textView34full_name_);
           // mMobileNo = RootView.findViewById(R.id.mobile_no_);
            mTextView35mobileNo = RootView.findViewById(R.id.textView35mobile_no_);
            mGuarantorName = RootView.findViewById(R.id.GuarantorName);
            mAddress = RootView.findViewById(R.id.address_);
            mTextView37address = RootView.findViewById(R.id.textView37address_);
            mGuarantorFatherName = RootView.findViewById(R.id.GuarantorFatherName);
            mFullName2 = RootView.findViewById(R.id.full_name_2);
            mGuarantorId1 = RootView.findViewById(R.id.GuarantorId1);
            mGuarantorEmail = RootView.findViewById(R.id.GuarantorEmail);
            mMobileNo2 = RootView.findViewById(R.id.mobile_no_2);
            mTextView35mobileNo2 = RootView.findViewById(R.id.textView35mobile_no_2);
            mGuarantorDob = RootView.findViewById(R.id.GuarantorDob);
            mAddress2 = RootView.findViewById(R.id.address_2);
            mTextView37address2 = RootView.findViewById(R.id.textView37address_2);
            mGuarantorAddressPermanent = RootView.findViewById(R.id.GuarantorAddressPermanent);
            mGuarantorAddressTemp = RootView.findViewById(R.id.GuarantorAddressTemp);
            mEmployeeOccuption = RootView.findViewById(R.id.employeeOccuption);
            mEmployeeOranization = RootView.findViewById(R.id.employeeOranization);
            mEmployeeDesignation = RootView.findViewById(R.id.employeeDesignation);
            mEmployeemonthlyIncome = RootView.findViewById(R.id.EmployeemonthlyIncome);
            mEmployeeYearFrom = RootView.findViewById(R.id.employeeYearFrom);
            mEmployeeBankName = RootView.findViewById(R.id.EmployeeBankName);
            mEmployeeBankAccountNumber = RootView.findViewById(R.id.EmployeeBankAccountNumber);
            mEmployeeBankIfscCode = RootView.findViewById(R.id.EmployeeBankIfscCode);
            mEmployeeBankchquee1 = RootView.findViewById(R.id.EmployeeBankchquee1);
            mEmployeeBankchquee2 = RootView.findViewById(R.id.EmployeeBankchquee2);
            mDocumentIdProof = RootView.findViewById(R.id.documentIdProof);
            mDocumentIdNumber = RootView.findViewById(R.id.documentIdNumber);
            mDocumentStampr = RootView.findViewById(R.id.documentStampr);
            mDocumentuploadFiletvvv = RootView.findViewById(R.id.documentuploadFiletvvv);
            mDocumentuploadFiletvv = RootView.findViewById(R.id.documentuploadFiletvv);
            mDocumentAddressProof = RootView.findViewById(R.id.documentAddressProof);
            mDocumentIdNumbertv = RootView.findViewById(R.id.documentIdNumbertv);
            mDocumentIdUploadFile = RootView.findViewById(R.id.documentIdUploadFile);
            mDocumentUnderTakingDoc = RootView.findViewById(R.id.documentUnderTakingDoc);
            mDocumentIncome = RootView.findViewById(R.id.documentIncome);
            mDocumentSecurity = RootView.findViewById(R.id.documentSecurity);
            mGuar4 = RootView.findViewById(R.id.guar4);
            mCardView1q34 = RootView.findViewById(R.id.cardView1q34);
            mMainCon = RootView.findViewById(R.id.mainCon);
            sessionManager = new SessionManager(getContext());
            UserDataToken =sessionManager.getUserDetailsToken();
            token=UserDataToken.get(SessionManager.KEY_TOKEN);
            UserData =sessionManager.getUserDetails();
            member_id= UserData.get(SessionManager.KEY_member_id);

            if (getArguments()!=null){
                id = getArguments().getString("id");
                getdetails(member_id,token,id);
            }else {

            }
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

    private void getdetails(String member_id, String token, String id) {
        try {
            googleProgressDialog.show1("Loading data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
            RestHandler.getApiService().PERSONAL_LONE_DETAILS_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _id
            ).enqueue(new Callback<PersonalLoanResponse>() {
                @Override
                public void onResponse(Call<PersonalLoanResponse> call, Response<PersonalLoanResponse> response) {
                    if (response.isSuccessful()){
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200){
                           // if (response.body().getAssociateStatus() == 1){
                              //  Toast.makeText(getActivity(), "hiii", Toast.LENGTH_SHORT).show();
                                LoanDetails   loanDetails=response.body().getResult().getDetail().getLoanDetails();
                                mLoanAmount.setText(loanDetails.getLoanAmount()+" ₹");
                                mEmiModeAmount.setText(loanDetails.getEMIModeOption());
                                mAssociateId.setText(loanDetails.getLoanPurpose());
                                mAssocaiteCode.setText(loanDetails.getAssociateCode());
                                mCarder.setText(String.valueOf(loanDetails.getBankAcount()!=null ? loanDetails.getBankAcount() : "N/A"));
                                mIfscCode.setText(String.valueOf(loanDetails.getIfsc()!=null ? loanDetails.getIfsc() : "N/A"));
                                mBankName.setText(String.valueOf(loanDetails.getBankName()!=null ? loanDetails.getBankName() : "N/A"));
                                mApplicantId.setText(loanDetails.getApplicantId());
                                Details applicant=response.body().getResult().getDetail().getApplicant().get(0).getDetails();
                                mApplicantIdDetails.setText(applicant.getApplicantId());
                                mNameDetails.setText(applicant.getName());
                                mGobarnchNameDetails.setText(applicant.getFatherName());
                                mApplicantEmailDetails.setText(String.valueOf(applicant.getEmailId()!=null ? applicant.getEmailId() : "N/A"));
                                mApplicantMobileDetails.setText(applicant.getMobileNo());
                                mApplicantAddressParmanet.setText(applicant.getPermanentAddress());
                                mApplicantAddressTemp.setText(applicant.getTemporaryAddress());

                                EmploymentDetails  employmentDetails=response.body().getResult().getDetail().getApplicant().get(0).getEmploymentDetails();
                                mOccupation.setText(employmentDetails.getOccupation());
                                mOrganization.setText(String.valueOf(employmentDetails.getOrganization()!=null ? employmentDetails.getOrganization() : "N/A"));
                                mDesignation.setText(employmentDetails.getDesignation());
                                mMonthlyIncome.setText(employmentDetails.getMonthlyIncome()+" ₹");
                                mYearForm.setText(employmentDetails.getYearFrom());

                                BankDetail  bankDetail=response.body().getResult().getDetail().getApplicant().get(0).getBankDetail();
                                mBankName1.setText(bankDetail.getBankName());
                                mBankAccountNumber.setText(bankDetail.getBankAc());
                                mApplicantifscCode.setText(bankDetail.getIfsc());
                                mChequeNumber1.setText(bankDetail.getCheque1());
                                mChequeNumber2.setText(bankDetail.getCheque2());


                                final Documents  documents=response.body().getResult().getDetail().getApplicant().get(0).getDocuments();
                                mIdproof.setText(documents.getIdProof());
                                mIdNumber.setText(documents.getIdNo());
                                 application_remark.setText(documents.getIncomeRemark());
                                if (documents.getUploadFile().size() > 0){
                                    // mUploadFile.setText(documents.getUploadFile().get(0).getName());
                                    mUploadFile.setText(underlinetext.getUnderline(documents.getUploadFile().get(0).getName()));
                                    mUploadFile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mUploadFile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents.getUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }
                                if (documents.getAddressUploadFile().size() > 0){
                                   // mAddressuploadfile.setText(documents.getAddressUploadFile().get(0).getName());
                                    mAddressuploadfile.setText(underlinetext.getUnderline(documents.getAddressUploadFile().get(0).getName()));
                                    mAddressuploadfile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mAddressuploadfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents.getAddressUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }
                                mAddressProof.setText(documents.getAddressProof());
                                mAddressIdnumber.setText(documents.getAddressIdProof());
                                mIncome.setText(documents.getIncome());
                                if (documents.getIncomeUploadFile().size() > 0){
                                   // mIncomeuploadfile.setText(documents.getIncomeUploadFile().get(0).getName());
                                    mIncomeuploadfile.setText(underlinetext.getUnderline(documents.getIncomeUploadFile().get(0).getName()));
                                    mIncomeuploadfile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mIncomeuploadfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents.getIncomeUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }

                                mSecurity.setText(documents.getSecurity());


                                Details__1  details__1=response.body().getResult().getDetail().getCoApplicant().get(0).getDetails();
                                mCoapplicantid.setText(details__1.getApplicantId());
                                mNameName.setText(details__1.getName());
                                mMobileNo.setText(details__1.getFatherName());
                                mCaderNamee.setText(String.valueOf(details__1.getEmailId()!=null ? details__1.getEmailId() :"N/A"));
                                mCoapplicantMobile.setText(details__1.getMobileNo());
                                mCoapplicantaddressPermanet.setText(details__1.getPermanentAddress());
                                mCoapplicanttempAddress.setText(details__1.getTemporaryAddress());

                                EmploymentDetails__1 employmentDetails1=response.body().getResult().getDetail().getCoApplicant().get(0).getEmploymentDetails();
                                mCoapplicantOccupation.setText(employmentDetails1.getOccupation()!=null ? employmentDetails1.getOccupation() : "N/A");
                                mCoapplicantOrganization.setText(String.valueOf(employmentDetails1.getOrganization() !=null ? employmentDetails1.getOrganization() :"N/A"));
                                mCoapplicantDesignation.setText(employmentDetails1.getDesignation());
                                mCoApplicantMonthlyIncome.setText(employmentDetails1.getMonthlyIncome()+" ₹");
                                mCoApplicantYearFrom.setText(employmentDetails1.getYearFrom());

                                BankDetail__1 bankDetail1=response.body().getResult().getDetail().getCoApplicant().get(0).getBankDetail();
                                mBankBankName.setText(bankDetail1.getBankName());
                                mBankBankAccountNumber.setText(bankDetail1.getBankAc());
                                mBankBankIfscCode.setText(bankDetail1.getIfsc());
                                mBankChequeeNumber1.setText(bankDetail1.getCheque1());
                                mBankChequeeNumber2.setText(bankDetail1.getCheque2());
                                final Documents__1  documents1=response.body().getResult().getDetail().getCoApplicant().get(0).getDocuments();
                                mBankIdProof.setText(documents1.getIdProof());
                                mBankIdNumber.setText(documents1.getIdNo());
                                remark.setText(documents1.getIncomeRemark());

                                if(documents1.getUploadFile().size() > 0) {
                                    mBankUploadFile.setText(underlinetext.getUnderline(documents1.getUploadFile().get(0).getName()));
                                    mBankUploadFile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mBankUploadFile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents1.getUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }


                            if(documents1.getAddressUploadFile().size() > 0) {
                                //mDocumentIdUploadFile.setText(documents2.getAddressUploadFile().get(0).getName());
                                mBankUploadFile2.setText(String.valueOf(underlinetext.getUnderline(documents1.getAddressUploadFile().get(0).getName())));

                                mBankUploadFile2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                mBankUploadFile2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CustomDialog cdd=new CustomDialog(getActivity(),documents1.getAddressUploadFile().get(0).getPath());
                                        cdd.show();
                                    }
                                });
                            }
                            try {
                                if (documents1.getUnderTakingDoc().size() > 0 && documents1.getUnderTakingDoc()!=null){
                                    mBankUnderTakingDoc.setText(documents1.getUnderTakingDoc().get(0).getName());
                                    mBankUnderTakingDoc.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mBankUnderTakingDoc.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents1.getUnderTakingDoc().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }
                            }catch (Exception exception){

                            }
                                mBankAddressProof.setText(documents1.getAddressProof());
                                mBankIdNumber.setText(documents1.getIdNo());
                                mBankincome.setText(documents1.getIncome());
                                 mBankIdNumberProof.setText(documents1.getAddressIdProof());


                                if (documents1.getIncomeUploadFile().size() > 0){
                                    mBankUploadFile3.setText(underlinetext.getUnderline(documents1.getIncomeUploadFile().get(0).getName()));
                                    mBankUploadFile3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mBankUploadFile3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents1.getIncomeUploadFile().get(0).getName());
                                            cdd.show();
                                        }
                                    });
                                }

                                mBankSecurity.setText(documents1.getSecurity());


                                Details__2 details__2=response.body().getResult().getDetail().getGuarantorApplicant().get(0).getDetails();
                                mGuarantorId.setText(details__2.getApplicantId());
                                mGuarantorName.setText(details__2.getName());
                                mGuarantorFatherName.setText(details__2.getFatherName());
                                mGuarantorId1.setText(String.valueOf(details__2.getEmailId()));
                                mGuarantorDob.setText(details__2.getDob());
                                mGuarantorAddressPermanent.setText(details__2.getPermanentAddress());
                                mGuarantorAddressTemp.setText(details__2.getTemporaryAddress());

                                EmploymentDetails__2 employmentDetails2=response.body().getResult().getDetail().getGuarantorApplicant().get(0).getEmploymentDetails();
                                mEmployeeOccuption.setText(employmentDetails2.getOccupation());
                                mEmployeeOranization.setText(String.valueOf(employmentDetails2.getOrganization()));
                                mEmployeeDesignation.setText(String.valueOf(employmentDetails2.getDesignation()));
                                mEmployeemonthlyIncome.setText(employmentDetails2.getMonthlyIncome());
                                mEmployeeYearFrom.setText(employmentDetails2.getYearFrom());

                                BankDetail__2 bankDetail2=response.body().getResult().getDetail().getGuarantorApplicant().get(0).getBankDetail();
                                mEmployeeBankName.setText(bankDetail2.getBankName());
                                mEmployeeBankAccountNumber.setText(bankDetail2.getBankAc());
                                mEmployeeBankIfscCode.setText(bankDetail2.getIfsc());
                                mEmployeeBankchquee1.setText(bankDetail2.getCheque1());
                                mEmployeeBankchquee2.setText(bankDetail2.getCheque2());


                                final Documents__2 documents2=response.body().getResult().getDetail().getGuarantorApplicant().get(0).getDocuments();

                                mDocumentIdProof.setText(documents2.getIdProof());
                                mDocumentIdNumber.setText(documents2.getIdNo());




                            if(documents2.getAddressUploadFile().size() > 0) {
                                //mDocumentIdUploadFile.setText(documents2.getAddressUploadFile().get(0).getName());
                                mDocumentuploadFiletvvv.setText(String.valueOf(documents2.getUploadFile().get(0).getName()));
                                mDocumentuploadFiletvvv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                mDocumentuploadFiletvvv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CustomDialog cdd=new CustomDialog(getActivity(),documents2.getUploadFile().get(0).getPath());
                                        cdd.show();
                                    }
                                });
                            }
                                 mDocumentAddressProof.setText(documents2.getAddressProof());
                                 mDocumentIdNumbertv.setText(documents2.getAddressIdProof());
//                                if(documents2.getAddressUploadFile().size() > 0) {
//                                    //mDocumentIdUploadFile.setText(documents2.getAddressUploadFile().get(0).getName());
//                                    mDocumentIdUploadFile.setText(underlinetext.getUnderline(documents2.getAddressUploadFile().get(0).getName()));
//                                    mDocumentIdUploadFile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                                    mDocumentIdUploadFile.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            CustomDialog cdd=new CustomDialog(getActivity(),documents2.getAddressUploadFile().get(0).getPath());
//                                            cdd.show();
//                                        }
//                                    });
//                                }
                                if(documents2.getUnderTakingDoc().size() > 0) {
                                   // mDocumentUnderTakingDoc.setText(documents2.getUnderTakingDoc().get(0).getName());
                                    mDocumentIdUploadFile.setText(underlinetext.getUnderline(documents2.getUnderTakingDoc().get(0).getName()));
                                    mDocumentIdUploadFile.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mDocumentIdUploadFile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents2.getUnderTakingDoc().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }


                            if (documents2.getUnderTakingDoc().size() > 0){
                                mDocumentUnderTakingDoc.setText(documents2.getUnderTakingDoc().get(0).getName());
                                Log.e("doc",documents2.getUnderTakingDoc().get(0).getPath());
                                mDocumentUnderTakingDoc.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                mDocumentUnderTakingDoc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CustomDialog cdd=new CustomDialog(getActivity(),documents2.getUnderTakingDoc().get(0).getPath());
                                        cdd.show();
                                    }
                                });
                            }
                                if(documents2.getIncomeUploadFile().size() > 0) {
                                    //mDocumentuploadFiletvv.setText(documents2.getIncomeUploadFile().get(0).getName());
                                    mDocumentuploadFiletvv.setText(underlinetext.getUnderline(documents2.getIncomeUploadFile().get(0).getName()));
                                    mDocumentuploadFiletvv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                    mDocumentuploadFiletvv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            CustomDialog cdd=new CustomDialog(getActivity(),documents2.getIncomeUploadFile().get(0).getPath());
                                            cdd.show();
                                        }
                                    });
                                }
                                mDocumentSecurity.setText(documents2.getSecurity());
                                mDocumentIncome.setText(documents2.getIncome());
                            /*}else {
                                textViewNotFound.setVisibility(View.VISIBLE);
                                textViewNotFound.setText(response.body().getMessages());
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }*/
                        }else {
                            textViewNotFound.setVisibility(View.VISIBLE);
                            textViewNotFound.setText(response.body().getMessages());
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        textViewNotFound.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<PersonalLoanResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    textViewNotFound.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception ex){
        }
    }
    }

