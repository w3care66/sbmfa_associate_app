package com.associate.sbmfa.Fragment.LoanManagment;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.EMIFormSubmitResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanAssociateDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanIDRecoveryResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultAssociateDetails;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultLoan;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class LoanRecoveryEmiFormFragment extends Fragment {
    View Rootview;
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay;
    String dateFrom,dateto;
    boolean isSerach = false;
    Spinner spinner2,spinnerBranch,spinnerMode,spinnerBank,spinnerBankAc;
    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> dateBranch =new ArrayList<>();
    ArrayList<String> dateBranchId =new ArrayList<>();
    ArrayList<String> dateMode =new ArrayList<>();
    ArrayList<String> dateBank =new ArrayList<>();
    ArrayList<String> dateBankAc =new ArrayList<>();
    private ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="",loanId="",loanEmiPaymentMode="0";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    ArrayAdapter<String> adapterBranch;
    ConstraintLayout constraintLayoutSSb,constraintLayoutBank,constraintLayoutBank2;
    ArrayAdapter<String> adapterBankAc;
    TextInputLayout textInputLayoutNumber,textInputLayoutAmount;
    TextInputEditText EmIAmount,DueAmount,AsscoiateCode,
            DepostieAmount,CloserAmount,PenltyAmount,
            RecoveredAmount,LastRecoveredAmount,
            AssociateName,Avilable,SSBAcount,AmountMode,BankName,BankNo,BranchName,IFSC,Cheque;
    String  stringDepostieDate,stringPaymentMode,
            stringBranchSpiner,stringMode,stringBankSpiner,stringBankAc;
   Button submit;
    String AsscoiateCodeStr;
//   String associate_no,deposiDate,ssbId,loanEmiPaymentMode,depositAmount,loanId,bankTransferMode,
//           customerCheque, utrTransactionNumber, chequeNumber,  chequeDate, bankName, accountNumber,
//           customerBankName,  customerBankAccountNumber,  customerIfscCode, customerBranchName, companyBank,
//           bankAccountNumber,penaltyAmount;

String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Rootview =  inflater.inflate(R.layout.fragment_loan_emi_form_recovery, container, false);
        fromDate = Rootview.findViewById(R.id.editTextDeposit_date);
        spinner2 = Rootview.findViewById(R.id.spinner2);
        spinnerBranch = Rootview.findViewById(R.id.spinner3);
        spinnerMode = Rootview.findViewById(R.id.spinnerMode);
        spinnerBank = Rootview.findViewById(R.id.spinnerBank);
        spinnerBankAc = Rootview.findViewById(R.id.spinnerBankAc);
        imageViewBack = Rootview.findViewById(R.id.imageView);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        textInputLayoutNumber = Rootview.findViewById(R.id.textInputNane15);
        textInputLayoutAmount = Rootview.findViewById(R.id.textInputNane16);

        constraintLayoutSSb = Rootview.findViewById(R.id.ssbacount);
        constraintLayoutBank = Rootview.findViewById(R.id.bank);
        constraintLayoutBank2 = Rootview.findViewById(R.id.bank2);

        EmIAmount = Rootview.findViewById(R.id.editTextEmiAmount);
        DueAmount = Rootview.findViewById(R.id.editTextAmount);
        AsscoiateCode = Rootview.findViewById(R.id.editTextAssoicate);
        AssociateName = Rootview.findViewById(R.id.editTextAssoicateName);
        DepostieAmount = Rootview.findViewById(R.id.editTextDepositAmount);
        CloserAmount = Rootview.findViewById(R.id.editTextCloserAmount);
        PenltyAmount = Rootview.findViewById(R.id.editTextPenltyAmount);
        RecoveredAmount = Rootview.findViewById(R.id.editTextRecoverdAmount);
        LastRecoveredAmount = Rootview.findViewById(R.id.editTextLastRecoverdAmount);
        Avilable = Rootview.findViewById(R.id.editTextLastavilable);
        SSBAcount = Rootview.findViewById(R.id.editTextSSbAcount);
        AmountMode = Rootview.findViewById(R.id.editTextAmountMode);
        BankName = Rootview.findViewById(R.id.editTextBankName);
        BankNo = Rootview.findViewById(R.id.editTextBankNo);
        BranchName = Rootview.findViewById(R.id.editTextBranchName);
        IFSC = Rootview.findViewById(R.id.editTextIFSC);
        Cheque = Rootview.findViewById(R.id.editTextCheque);
        submit = Rootview.findViewById(R.id.button3);

        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        if(getArguments()!=null) {
            loanId = getArguments().getString("id");
            type = getArguments().getString("type");
        }
        getLoanDetails(loanId);

        DepostieAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().isEmpty()){

                    }else {
                        Log.e("DEE",""+s);
                        int penltyAmount = Integer.parseInt( PenltyAmount.getText().toString() == "0" ? "0" : PenltyAmount.getText().toString());
                        int depostieAmount = Integer.parseInt(DepostieAmount.getText().toString());
                        int totalamount  = penltyAmount + depostieAmount ;
                        AmountMode.setText(String.valueOf(totalamount));
                    }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        AsscoiateCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!TextUtils.isEmpty(AsscoiateCode.getText().toString())){
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        AssociateName.setText(null);
                        Avilable.setText(null);
                        SSBAcount.setText(null);
                        AsscoiateCodeStr = AsscoiateCode.getText().toString();
                        getAssociaterData(member_id,token,AsscoiateCodeStr);
                    }else {
                        Toast.makeText(getActivity(), "Text is Empty..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        dateBranch.add("Select Branch");
        dateBranchId.add("0");
        adapterBranch = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateBranch);
        adapterBranch.setDropDownViewResource(R.layout.spiner_item);
        spinnerBranch.setAdapter(adapterBranch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    stringBranchSpiner = dateBranchId.get(position);
                }else {
                    stringBranchSpiner = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getBranch(member_id,token);

       // dateStrings.add("Select Payment");
//        dateStrings.add("Cash");
        dateStrings.add("SSB Account");
//        dateStrings.add("Bank");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinner2.setAdapter(adapterselectDate);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0){
//                    stringPaymentMode = "";
//                    constraintLayoutSSb.setVisibility(View.GONE);
//                    constraintLayoutBank.setVisibility(View.GONE);
//                    constraintLayoutBank2.setVisibility(View.GONE);
//                }else {
//                    if (position == 1){
//                        stringPaymentMode = dateStrings.get(position).toString();
//                        constraintLayoutSSb.setVisibility(View.GONE);
//                        constraintLayoutBank.setVisibility(View.GONE);
//                        constraintLayoutBank2.setVisibility(View.GONE);
//                    }else if (position == 2){
                        stringPaymentMode = dateStrings.get(position).toString();
                        constraintLayoutSSb.setVisibility(View.VISIBLE);
                        constraintLayoutBank.setVisibility(View.GONE);
                        constraintLayoutBank2.setVisibility(View.GONE);
//                    }else if(position == 3){
//                        stringPaymentMode = dateStrings.get(position).toString();
//                        constraintLayoutSSb.setVisibility(View.GONE);
//                        constraintLayoutBank.setVisibility(View.VISIBLE);
//                        constraintLayoutBank2.setVisibility(View.VISIBLE);
//                    }
               // }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///Bank...
        dateMode.add("Select Mode");
        dateMode.add("Cheque");
        dateMode.add("Online");
        ArrayAdapter<String> adapterMode = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateMode);
        adapterMode.setDropDownViewResource(R.layout.spiner_item);
        spinnerMode.setAdapter(adapterMode);
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   if (position == 0){
                       stringMode = "";
                       textInputLayoutNumber.setVisibility(View.GONE);
                       textInputLayoutAmount.setVisibility(View.GONE);
                   }else if (position == 1){
                       stringMode = dateMode.get(position);
                       textInputLayoutNumber.setVisibility(View.VISIBLE);
                       textInputLayoutNumber.setHint("Cheque No.");
                       textInputLayoutAmount.setVisibility(View.VISIBLE);
                   }else if (position == 2){
                       stringMode = dateMode.get(position);
                       textInputLayoutNumber.setVisibility(View.VISIBLE);
                       textInputLayoutNumber.setHint("UTR number / Transaction Number");
                       textInputLayoutAmount.setVisibility(View.VISIBLE);
                   }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterBankAc = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateBankAc);
        adapterBankAc.setDropDownViewResource(R.layout.spiner_item);
        spinnerBankAc.setAdapter(adapterBankAc);
        spinnerBankAc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0){
                stringBankAc = dateBankAc.get(position);
            }else {
                stringBankAc = "";
            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dateBank.add("Select Bank");
        dateBank.add("AXIS BANK");
        dateBank.add("ICICI BANK");
        dateBank.add("HDFC BANK");
        dateBank.add("AU SMALL FINANCE BANK");
        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateBank);
        adapterBank.setDropDownViewResource(R.layout.spiner_item);
        spinnerBank.setAdapter(adapterBank);
        spinnerBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     dateBankAc.clear();
                     if (position > 0){
                         stringBankSpiner = dateBank.get(position);
                         if (position ==1){
                             dateBankAc.add("Select Bank A/C");
                             dateBankAc.add("920020034178187");
                             adapterBankAc.notifyDataSetChanged();
                         }else if (position ==2){
                             dateBankAc.add("Select Bank A/C");
                             dateBankAc.add("675105601216");
                             adapterBankAc.notifyDataSetChanged();
                         }else if (position ==3){
                             dateBankAc.add("Select Bank A/C");
                             dateBankAc.add("50200053569400");
                             adapterBankAc.notifyDataSetChanged();
                         }else if (position ==4){
                             dateBankAc.add("Select Bank A/C");
                             dateBankAc.add("2021221629583222");
                             adapterBankAc.notifyDataSetChanged();
                         }
                     }else {
                         stringBankSpiner = "";
                         dateBankAc.add("Select Bank A/C");
                         adapterBankAc.notifyDataSetChanged();
                     }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ///Bank...


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                             Calendar mCalender = Calendar.getInstance();
                mYear = mCalender.get(Calendar.YEAR);
                mMonth = mCalender.get(Calendar.MONTH);
                mDay = mCalender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                int monthOfYear1 = monthOfYear + 1;
                                dateFrom = "" + dayOfMonth + "-" + monthOfYear1 + "-" + year;
                                stringDepostieDate = dateFrom;
                                fromDate.setText(dateFrom);
                                getAssociaterData(member_id,token,AsscoiateCodeStr);
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();

            }
        });

        imageViewBack = Rootview.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        Rootview.setFocusableInTouchMode(true);
        Rootview.requestFocus();
        Rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DepostieAmount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter Deposite Amount", Toast.LENGTH_SHORT).show();

                }else {
                    String depo = DepostieAmount.getText().toString();
                    String avll = Avilable.getText().toString();
                    int dep = Integer.parseInt(depo);
                    int avl = Integer.parseInt(avll);
                   /* if (dep <= avl) {*/
                        formSubmit(
                                member_id,
                                token,
                                fromDate.getText().toString(),
                                loanEmiPaymentMode,
                                DepostieAmount.getText().toString(),
                                loanId,
                                PenltyAmount.getText().toString());
                    /*} else {
                        Toast.makeText(getActivity(), "Your Avilable Balance Not Sufficent", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }
        });

        return Rootview;
    }/**/

    private void getAssociaterData(String member_id, String token, String code) {
        try {
            googleProgressDialog.show1("Loading Associate Data..");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _associate_code = RequestBody.create(MediaType.parse("multipart/form-data"), code);
            RestHandler.getApiService().LOAN_ASSOCIATE_DETAILS_RESPONSE_CALL(_member_id,_token,_associate_code).enqueue(new Callback<LoanAssociateDetailsResponse>() {
                @Override
                public void onResponse(Call<LoanAssociateDetailsResponse> call, Response<LoanAssociateDetailsResponse> response) {
                    if (response.body()!=null){
                        googleProgressDialog.dismiss();
                           if (response.body().getCode() == 200){
                               /*if (response.body().getAssociateStatus() == 0){
                                   sessionManager.logoutUser();
                               }*/
                               ResultAssociateDetails result  =response.body().getResult();
                               AssociateName.setText(result.getName());
                               Avilable.setText(String.valueOf(result.getSsbAmount()));
                               SSBAcount.setText(String.valueOf(result.getSsbAccountNumber()));

                           }else {
                               Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                           }
                    }else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<LoanAssociateDetailsResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });
        }catch (Exception ex){
            AssociateName.setText(null);
            Avilable.setText(null);
            SSBAcount.setText(null);
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLoanDetails(String s) {
        try {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody loanId = RequestBody.create(MediaType.parse("multipart/form-data"), s);
            RestHandler.getApiService().LOAN_ID_RECOVERY_RESPONSE_CALL(_member_id,_token,loanId).enqueue(new Callback<LoanIDRecoveryResponse>() {
                @Override
                public void onResponse(Call<LoanIDRecoveryResponse> call, Response<LoanIDRecoveryResponse> response) {
                   if (response.body()!=null){
                       if (response.body().getCode() == 200){
                           ResultLoan result = response.body().getResult();
                           EmIAmount.setText(result.getEmiAmount());
                           DueAmount.setText(String.valueOf(result.getDueAmount()));
                           CloserAmount.setText(String.valueOf(result.getClosingAmount()));
                           if (result.getPenaltyAmount().isEmpty()){
                               PenltyAmount.setText("0");
                           }else {
                               PenltyAmount.setText(String.valueOf(result.getPenaltyAmount()));
                           }
                           RecoveredAmount.setText(String.valueOf(result.getRecoveredAmount()));
                           LastRecoveredAmount.setText(String.valueOf(result.getLastRecoveredAmount()));

                       }else {
                           Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                       }

                   }else {
                       Toast.makeText(getContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                   }
                }

                @Override
                public void onFailure(Call<LoanIDRecoveryResponse> call, Throwable t) {

                }
            });
        }catch (Exception ex){

        }

    }

    private void getBranch(String member_id, String token) {
        try {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RestHandler.getApiService().BRANCH_LIST_RESPONSE_CALL(_member_id,_token).enqueue(new Callback<BranchListResponse>() {
                @Override
                public void onResponse(Call<BranchListResponse> call, Response<BranchListResponse> response) {
                    if (response.body()!=null){
                              if (response.body().getCode() == 200){
                                  if (response.body().getAssociateStatus() == 1){
                                      ResultBranchList result = response.body().getResult();
                                      List<Branch> branchList = result.getBranch();
                                      if (branchList.size() > 0){
                                          for (Branch branch : branchList){
                                              dateBranch.add(branch.getName());
                                              dateBranchId.add(String.valueOf(branch.getId()));
                                          }
                                          adapterBranch.notifyDataSetChanged();
                                      }else{

                                      }
                                  }else {
                                      Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                  }
                              }else {
                                  Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                              }
                    }else {
                        Toast.makeText(getContext(), "Branch Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BranchListResponse> call, Throwable t) {

                }
            });
        }catch (Exception ex){

        }
    }


    void formSubmit(String associate_nom,String token,String deposiDate,String loanEmiPaymentMode,String depositAmount,String loanId,String  penaltyAmount){
       try{
           if (type.equals("loan")){
               googleProgressDialog.show1("Loading Associate Data..");
               RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), associate_nom);
               RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
               RequestBody _deposiDate= RequestBody.create(MediaType.parse("multipart/form-data"), deposiDate);
               RequestBody _loanEmiPaymentMode = RequestBody.create(MediaType.parse("multipart/form-data"), loanEmiPaymentMode);
               RequestBody _depositAmount = RequestBody.create(MediaType.parse("multipart/form-data"), depositAmount);
               RequestBody _loanId= RequestBody.create(MediaType.parse("multipart/form-data"), loanId);
               RequestBody _penaltyAmount= RequestBody.create(MediaType.parse("multipart/form-data"), penaltyAmount);
               RestHandler.getApiService().LOAN_EMI_SUBMIT_RESPONSE_CALL(_member_id,_token,_deposiDate,_loanEmiPaymentMode,_depositAmount,_loanId,_penaltyAmount).enqueue(new Callback<EMIFormSubmitResponse>() {
                   @Override
                   public void onResponse(Call<EMIFormSubmitResponse> call, Response<EMIFormSubmitResponse> response) {
                       if (response.body()!=null){
                           googleProgressDialog.dismiss();
                           if (response.body().getCode() == 200){
                               Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();

                           }else {
                               Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                           }
                       }else {
                           googleProgressDialog.dismiss();
                           Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                       }
                   }
                   @Override
                   public void onFailure(Call<EMIFormSubmitResponse> call, Throwable t) {
                       googleProgressDialog.dismiss();
                   }
               });
           }else {

               googleProgressDialog.show1("Loading Associate Data..");
               RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), associate_nom);
               RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
               RequestBody _deposiDate= RequestBody.create(MediaType.parse("multipart/form-data"), deposiDate);
               RequestBody _loanEmiPaymentMode = RequestBody.create(MediaType.parse("multipart/form-data"), loanEmiPaymentMode);
               RequestBody _depositAmount = RequestBody.create(MediaType.parse("multipart/form-data"), depositAmount);
               RequestBody _loanId= RequestBody.create(MediaType.parse("multipart/form-data"), loanId);
               RequestBody _penaltyAmount= RequestBody.create(MediaType.parse("multipart/form-data"), penaltyAmount);
               RestHandler.getApiService().GROUP_LOAN_EMI_SUBMIT_RESPONSE_CALL(_member_id,_token,_deposiDate,_loanEmiPaymentMode,_depositAmount,_loanId,_penaltyAmount).enqueue(new Callback<EMIFormSubmitResponse>() {
                   @Override
                   public void onResponse(Call<EMIFormSubmitResponse> call, Response<EMIFormSubmitResponse> response) {
                       if (response.body()!=null){
                           googleProgressDialog.dismiss();
                           if (response.body().getCode() == 200){
                               Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();

                           }else {
                               Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                           }
                       }else {
                           googleProgressDialog.dismiss();
                           Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                       }
                   }
                   @Override
                   public void onFailure(Call<EMIFormSubmitResponse> call, Throwable t) {
                       googleProgressDialog.dismiss();
                   }
               });
           }

    }catch (Exception ex){
           googleProgressDialog.dismiss();
        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }
    }
}