package com.associate.sbmfa.Fragment.RegistionForm;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Model.AssociateLoanDetail;
import com.associate.sbmfa.Model.StateCityModel;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.MemberRegisterDetails;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberRegisterFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    SessionManager sessionManager;
    String member_id = "", token = "";
    HashMap<String, String> UserData;
    HashMap<String, String> UserDataToken;
    ProgressDialog progressDialog;
    int pageIndex = 0;
    // permission code
    private int CAMERA_PERMISSION = 100;
    private int PICK_PERMISSION = 101;
    private int REQUEST_IMAGE_CAPTURE = 1;
    private int REQUEST_PICK_IMAGE = 2;
    String selectImageType = "";
    File photoFile;
    File sigFile;

    ImageView backImageView;
    ConstraintLayout firstPageConstraintLayout, secondPageConstraintLayout, thirdPageConstraintLayout, forthPageConstraintLayout;

    TextView branchNameTextView,applicationDateTextView, formNoTextView;
    EditText firstNameEditText, lastNameEditText, emailIdEditText, mobileNoEditText, dobEditText, annualIncomeEditText, motherNameEditText, fatherNameEditText, anniversaryDateEditText;
    EditText bankNameEditText, branchNaEditText, accountNoEditText, IFSCCodeEditText, branchAddressEditText, nomineeFullNameEditText, nomineeDOBEditText, nomineeMobileNoEditText;
    EditText residenceAddressEditText, residenceVillageNameEditText, residencePinCodeEditText, idProofIDNoEditText, idProofAddressEditText, addressProofIDNo, addressProofAddress;
    String branchId, gender, materialStatus, dateOfBirth, anniversaryDate, nomineeGender, nomineeDOB;

    /*occupation Data*/
    Spinner occupationSpinner;
    ArrayList<MemberRegisterDetails.Occupation> occupationList = new ArrayList<>();
    ArrayList<String> occupationNameList = new ArrayList<>();
    String occupationId;

    /*religion Data*/
    Spinner religionSpinner;
    ArrayList<MemberRegisterDetails.Religion> religionList = new ArrayList<>();
    ArrayList<String> religionNameList = new ArrayList<>();
    String religionId;

    /*Category Data*/
    Spinner categoriesSpinner;
    ArrayList<MemberRegisterDetails.Category> categoriesList = new ArrayList<>();
    ArrayList<String> categoriesNameList = new ArrayList<>();
    String categoriesId;

    /*Relationship Data*/
    Spinner relationshipSpinner;
    ArrayList<MemberRegisterDetails.Relation> relationshipList = new ArrayList<>();
    ArrayList<String> relationshipNameList = new ArrayList<>();
    String relationshipId;

    /*State Data*/
    Spinner stateSpinner;
    ArrayList<MemberRegisterDetails.State> stateList = new ArrayList<>();
    ArrayList<String> stateNameList = new ArrayList<>();
    String stateId;

    /*Distrct Data*/
    Spinner distrctSpinner;
    ArrayList<StateCityModel.Result> distrctList = new ArrayList<>();
    ArrayList<String> distrctNameList = new ArrayList<>();
    String distrctId;

    /*City Data*/
    Spinner citySpinner;
    ArrayList<StateCityModel.Result> cityList = new ArrayList<>();
    ArrayList<String> cityNameList = new ArrayList<>();
    String cityId;

    /*Id Proof Data*/
    Spinner idProofSpinner;
    Spinner selectIdSpinner;
    ArrayList<MemberRegisterDetails.IdProoff> idProofList = new ArrayList<>();
    ArrayList<String> idProofNameList = new ArrayList<>();
    String idProofId;
    String addressSelectedId;

    Button firstPageSubmitButton, secondPagePreviousButton, secondPageNextButton, thirdPagePreviousButton, thirdPageNextButton;
    RadioButton genderRadioButton, materialStatusRadioButton, nomineeGenderRadioButton;
    RadioGroup genderRadioGroup, materialStatusRadioGroup, nomineeGenderRadioGroup;
    ImageView memberPhotoImageView,memberPhotoCancelImageView, memberSignatureImageView, memberSignatureCancelImageView;
    TextView memberPhotoNameTextView, memberSignatureNameTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_investment_registration, container, false);
        backImageView = rootView.findViewById(R.id.iv_member_back);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        requestStoragePermission();
        sessionManager = new SessionManager(getActivity());
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        UserData = sessionManager.getUserDetails();
        UserDataToken = sessionManager.getUserDetailsToken();
        member_id = UserData.get(SessionManager.KEY_member_id);
        token = UserDataToken.get(SessionManager.KEY_TOKEN);

        firstPageConstraintLayout = rootView.findViewById(R.id.cl_first_page);
        secondPageConstraintLayout = rootView.findViewById(R.id.cl_second_page);
        thirdPageConstraintLayout = rootView.findViewById(R.id.cl_third_page);
        forthPageConstraintLayout = rootView.findViewById(R.id.cl_forth_page);
        /*First page Views*/
        branchNameTextView = rootView.findViewById(R.id.txt_branch_name);
        applicationDateTextView = rootView.findViewById(R.id.edt_application_date);
        formNoTextView = rootView.findViewById(R.id.edt_form_no);

        occupationSpinner = rootView.findViewById(R.id.spinner_occupation);
        religionSpinner = rootView.findViewById(R.id.spinner_religion);
        categoriesSpinner = rootView.findViewById(R.id.spinner_categories);

        firstNameEditText = rootView.findViewById(R.id.edt_firstname);
        lastNameEditText = rootView.findViewById(R.id.edt_last_name);
        emailIdEditText = rootView.findViewById(R.id.edt_email);
        mobileNoEditText = rootView.findViewById(R.id.edt_mobile);
        dobEditText = rootView.findViewById(R.id.edt_dob);
        annualIncomeEditText = rootView.findViewById(R.id.edt_annual_income);
        motherNameEditText = rootView.findViewById(R.id.edt_mother_name);
        fatherNameEditText = rootView.findViewById(R.id.edt_father_name);
        anniversaryDateEditText = rootView.findViewById(R.id.edt_anniversary_date);
        firstPageSubmitButton = rootView.findViewById(R.id.btn_first_next);

        genderRadioGroup = rootView.findViewById(R.id.radioGroup);
        materialStatusRadioGroup = rootView.findViewById(R.id.radioGroup_material);
        genderRadioGroup.setOnCheckedChangeListener(this);
        materialStatusRadioGroup.setOnCheckedChangeListener(this);
        dobEditText.setOnClickListener(this);
        anniversaryDateEditText.setOnClickListener(this);
        firstPageSubmitButton.setOnClickListener(this);

        occupationSpinner.setOnItemSelectedListener(this);
        religionSpinner.setOnItemSelectedListener(this);
        categoriesSpinner.setOnItemSelectedListener(this);

        /*Second Page View*/
        relationshipSpinner = rootView.findViewById(R.id.nominee_relationship);
        bankNameEditText = rootView.findViewById(R.id.edt_bank_name);
        branchNaEditText = rootView.findViewById(R.id.edt_branch_name);
        accountNoEditText = rootView.findViewById(R.id.edt_account_no);
        IFSCCodeEditText = rootView.findViewById(R.id.edt_ifsc_code);
        branchAddressEditText = rootView.findViewById(R.id.edt_branch_address);
        nomineeFullNameEditText = rootView.findViewById(R.id.edt_nominee_full_name);
        nomineeDOBEditText = rootView.findViewById(R.id.edt_nominee_dob);
        nomineeMobileNoEditText = rootView.findViewById(R.id.edt_nominee_mobile_no);
        nomineeGenderRadioGroup = rootView.findViewById(R.id.rg_nominee_gender);
        secondPagePreviousButton = rootView.findViewById(R.id.btn_second_pre);
        secondPageNextButton = rootView.findViewById(R.id.btn_second_next);
        nomineeGenderRadioGroup.setOnCheckedChangeListener(this);
        relationshipSpinner.setOnItemSelectedListener(this);
        nomineeDOBEditText.setOnClickListener(this);
        secondPagePreviousButton.setOnClickListener(this);
        secondPageNextButton.setOnClickListener(this);

        /*Third Page View*/
        stateSpinner = rootView.findViewById(R.id.spinner_residence_state);
        distrctSpinner = rootView.findViewById(R.id.spinner_residence_district);
        citySpinner = rootView.findViewById(R.id.spinner_residence_city);
        idProofSpinner = rootView.findViewById(R.id.spinner_idproof_document);
        selectIdSpinner = rootView.findViewById(R.id.spinner_address_id);
        stateSpinner.setOnItemSelectedListener(this);
        distrctSpinner.setOnItemSelectedListener(this);
        citySpinner.setOnItemSelectedListener(this);
        idProofSpinner.setOnItemSelectedListener(this);
        selectIdSpinner.setOnItemSelectedListener(this);

        residenceAddressEditText = rootView.findViewById(R.id.edt_residence_address);
        residenceVillageNameEditText = rootView.findViewById(R.id.edt_residence_village_name);
        residencePinCodeEditText = rootView.findViewById(R.id.edt_residence_pincode);
        idProofIDNoEditText = rootView.findViewById(R.id.edt_id_proof_no);
        idProofAddressEditText = rootView.findViewById(R.id.edt_id_proof_address);
        addressProofIDNo = rootView.findViewById(R.id.edt_address_id_number);
        addressProofAddress = rootView.findViewById(R.id.edt_address_address);
        thirdPagePreviousButton = rootView.findViewById(R.id.btn_third_pre);
        thirdPageNextButton = rootView.findViewById(R.id.btn_third_next);
        thirdPagePreviousButton.setOnClickListener(this);
        thirdPageNextButton.setOnClickListener(this);

        /*Forth Page View*/
        memberPhotoImageView = rootView.findViewById(R.id.iv_user_photo);
        memberPhotoCancelImageView = rootView.findViewById(R.id.iv_cancel_user_image);
        memberSignatureImageView = rootView.findViewById(R.id.iv_user_sig);
        memberSignatureCancelImageView = rootView.findViewById(R.id.iv_cancel_user_sig);

        memberPhotoNameTextView = rootView.findViewById(R.id.txt_user_photo_name);
        memberSignatureNameTextView = rootView.findViewById(R.id.txt_user_sig_name);
        memberPhotoImageView.setOnClickListener(this);
        memberPhotoCancelImageView.setOnClickListener(this);
        memberSignatureImageView.setOnClickListener(this);
        memberSignatureCancelImageView.setOnClickListener(this);

        getMemberRegisterData();
        setPageVisibleShowORGone(0);
        return rootView;
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 2000);
        }
    }



    private void getMemberRegisterData() {
        progressDialog.show();
        occupationList.clear();
        religionList.clear();
        categoriesList.clear();
        relationshipNameList.clear();
        stateList.clear();
        distrctList.clear();
        cityList.clear();
        idProofList.clear();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        final Call<MemberRegisterDetails> getMemberRegisterDataResponse = RestHandler.getApiService().MEMBER_REGISTER_DATA(_member_id, _token);
        getMemberRegisterDataResponse.enqueue(new Callback<MemberRegisterDetails>() {
            @Override
            public void onResponse(Call<MemberRegisterDetails> call, Response<MemberRegisterDetails> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getCode() == 200) {
                        setPageVisibleShowORGone(4);
                        branchNameTextView.setText(response.body().getBranchName());
                        try {
                            applicationDateTextView.setText(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(response.body().getApplicationDate())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        formNoTextView.setText(response.body().getFormNo());
                        branchId = String.valueOf(response.body().getBranchId());
                        occupationNameList.add("Select Occupation");
                        religionNameList.add("Select Religion");
                        categoriesNameList.add("Select Category");
                        relationshipNameList.add("Select RelationShip");
                        stateNameList.add("Select State");
                        distrctNameList.add("Select District");
                        cityNameList.add("Select City");
                        idProofNameList.add("Select Id Proof");

                        for (MemberRegisterDetails.Occupation occupation : response.body().getResult().getOccupations()) {
                            occupationList.add(occupation);
                            occupationNameList.add(occupation.getName());
                        }
                        for (MemberRegisterDetails.Religion religion : response.body().getResult().getReligions()) {
                            religionList.add(religion);
                            religionNameList.add(religion.getName());
                        }
                        for (MemberRegisterDetails.Category category : response.body().getResult().getCategories()) {
                            categoriesList.add(category);
                            categoriesNameList.add(category.getName());
                        }

                        for (MemberRegisterDetails.Relation relation : response.body().getResult().getRelations()) {
                            relationshipList.add(relation);
                            relationshipNameList.add(relation.getName());
                        }

                        for (MemberRegisterDetails.State state : response.body().getResult().getStates()) {
                            stateList.add(state);
                            stateNameList.add(state.getName());
                        }

                        for (MemberRegisterDetails.IdProoff idProoff : response.body().getResult().getIdProoffs()) {
                            idProofList.add(idProoff);
                            idProofNameList.add(idProoff.getName());
                        }

                        ArrayAdapter<String> occupationArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, occupationNameList);
                        occupationArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        occupationSpinner.setAdapter(occupationArrayAdapter);

                        ArrayAdapter<String> religionArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, religionNameList);
                        religionArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        religionSpinner.setAdapter(occupationArrayAdapter);

                        ArrayAdapter<String> categoriesArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, categoriesNameList);
                        categoriesArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        categoriesSpinner.setAdapter(categoriesArrayAdapter);

                        ArrayAdapter<String> relationshipArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, relationshipNameList);
                        relationshipArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        relationshipSpinner.setAdapter(relationshipArrayAdapter);

                        ArrayAdapter<String> stateArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, stateNameList);
                        stateArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        stateSpinner.setAdapter(stateArrayAdapter);

                        ArrayAdapter<String> idProofArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, idProofNameList);
                        idProofArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                        idProofSpinner.setAdapter(idProofArrayAdapter);
                        selectIdSpinner.setAdapter(idProofArrayAdapter);
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberRegisterDetails> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPageVisibleShowORGone(int index) {
        pageIndex = index;
        if(index == 1){
            firstPageConstraintLayout.setVisibility(View.VISIBLE);
            secondPageConstraintLayout.setVisibility(View.GONE);
            thirdPageConstraintLayout.setVisibility(View.GONE);
            forthPageConstraintLayout.setVisibility(View.GONE);
        }else if(index == 2){
            firstPageConstraintLayout.setVisibility(View.GONE);
            secondPageConstraintLayout.setVisibility(View.VISIBLE);
            thirdPageConstraintLayout.setVisibility(View.GONE);
            forthPageConstraintLayout.setVisibility(View.GONE);
        }else if(index == 3){
            firstPageConstraintLayout.setVisibility(View.GONE);
            secondPageConstraintLayout.setVisibility(View.GONE);
            thirdPageConstraintLayout.setVisibility(View.VISIBLE);
            forthPageConstraintLayout.setVisibility(View.GONE);
        }else if(index == 4){
            firstPageConstraintLayout.setVisibility(View.GONE);
            secondPageConstraintLayout.setVisibility(View.GONE);
            thirdPageConstraintLayout.setVisibility(View.GONE);
            forthPageConstraintLayout.setVisibility(View.VISIBLE);
        }else {
            firstPageConstraintLayout.setVisibility(View.GONE);
            secondPageConstraintLayout.setVisibility(View.GONE);
            thirdPageConstraintLayout.setVisibility(View.GONE);
            forthPageConstraintLayout.setVisibility(View.GONE);
        }
    }

    void showDatePickerWithUpdate(final String type){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(type.equalsIgnoreCase("DOB")){
                            dobEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            dateOfBirth = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }else if(type.equalsIgnoreCase("Anniversary")){
                            anniversaryDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            anniversaryDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }else if(type.equalsIgnoreCase("Nominee_DOB")){
                            nomineeDOBEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            nomineeDOB = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }
                    }
                },
                year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
    }

/*Radio Button Action*/
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       if (group == genderRadioGroup){
           int selectedId = group.getCheckedRadioButtonId();
           genderRadioButton = (RadioButton) getActivity().findViewById(selectedId);
           gender = genderRadioButton.getText().toString();
       }else if(group == materialStatusRadioGroup){
           int selectedId = group.getCheckedRadioButtonId();
           materialStatusRadioButton = (RadioButton) getActivity().findViewById(selectedId);
           materialStatus = materialStatusRadioButton.getText().toString();
       }else if(group == nomineeGenderRadioGroup){
           int selectedId = group.getCheckedRadioButtonId();
           nomineeGenderRadioButton = (RadioButton) getActivity().findViewById(selectedId);
           nomineeGender = materialStatusRadioButton.getText().toString();
       }
    }

    /*Click Action*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edt_dob:
                showDatePickerWithUpdate("DOB");
                break;
            case R.id.edt_anniversary_date:
                showDatePickerWithUpdate("Anniversary");
                break;
            case R.id.edt_nominee_dob:
                showDatePickerWithUpdate("Nominee_DOB");
                break;
            case R.id.iv_user_photo:
                selectImageType = "Photo";
                selectImage();
                break;
            case R.id.iv_cancel_user_image:
                photoFile = null;
                memberPhotoImageView.setImageResource(0);
                memberPhotoCancelImageView.setVisibility(View.GONE);
                break;
            case R.id.iv_user_sig:
                selectImageType = "Signature";
                selectImage();
                break;
            case R.id.iv_cancel_user_sig:
                sigFile = null;
                memberSignatureImageView.setImageResource(0);
                memberSignatureCancelImageView.setVisibility(View.GONE);
                break;
            case R.id.btn_first_next:
                if(firstNameEditText.getText().toString().equalsIgnoreCase("")){
//                    firstNameEditText.setError("Enter First Name");
                    Toast.makeText(getActivity(), "Enter First Name", Toast.LENGTH_SHORT).show();
                }else if(mobileNoEditText.getText().toString().equalsIgnoreCase("")) {
//                    mobileNoEditText.setError("Enter Mobile Number");
                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }else if(mobileNoEditText.getText().toString().length() < 10) {
//                    mobileNoEditText.setError("Enter Valid Mobile Number");
                    Toast.makeText(getActivity(), "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }else if(dateOfBirth == null) {
                    Toast.makeText(getActivity(), "Select DOB", Toast.LENGTH_SHORT).show();
                }else if(gender == null) {
                    Toast.makeText(getActivity(), "Select Gender", Toast.LENGTH_SHORT).show();
                }else if(annualIncomeEditText.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Annual Income", Toast.LENGTH_SHORT).show();
                }else if(fatherNameEditText.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Father/Husband Name", Toast.LENGTH_SHORT).show();
                }else if(materialStatus == null) {
                    Toast.makeText(getActivity(), "Select Marital Status", Toast.LENGTH_SHORT).show();
                }else if(anniversaryDateEditText.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Select Anniversary Date", Toast.LENGTH_SHORT).show();
                }else {
                    setPageVisibleShowORGone(2);
                }
                break;
            case R.id.btn_second_pre:
            case R.id.btn_third_pre:
                setPageVisibleShowORGone(pageIndex - 1);
                break;
            case R.id.btn_second_next:
                if(nomineeFullNameEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Nominee Full Name", Toast.LENGTH_SHORT).show();
                }else if(relationshipId == null) {
                    Toast.makeText(getActivity(), "Select Relationship", Toast.LENGTH_SHORT).show();
                }else if(nomineeMobileNoEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Nominee Mobile No", Toast.LENGTH_SHORT).show();
                }else if(nomineeMobileNoEditText.getText().toString().length() < 10){
                    Toast.makeText(getActivity(), "Enter Valid Nominee Mobile No", Toast.LENGTH_SHORT).show();
                }else {
                    setPageVisibleShowORGone(3);
                }
                break;
            case R.id.btn_third_next:
                if(residenceAddressEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Residence Address", Toast.LENGTH_SHORT).show();
                }else if(stateId == null) {
                    Toast.makeText(getActivity(), "Select State", Toast.LENGTH_SHORT).show();
                }else if(distrctId == null) {
                    Toast.makeText(getActivity(), "Select District", Toast.LENGTH_SHORT).show();
                }else if(cityId == null) {
                    Toast.makeText(getActivity(), "Select City", Toast.LENGTH_SHORT).show();
                }else if(residencePinCodeEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Pin Code", Toast.LENGTH_SHORT).show();
                }else if(residencePinCodeEditText.getText().toString().length() < 6){
                    Toast.makeText(getActivity(), "Enter Valid Pin Code", Toast.LENGTH_SHORT).show();
                }else if(idProofId == null) {
                    Toast.makeText(getActivity(), "Select Document Type of Id Proof ", Toast.LENGTH_SHORT).show();
                }else if(idProofIDNoEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter ID No of Id Proof", Toast.LENGTH_SHORT).show();
                }else if(idProofAddressEditText.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Address of Id Proof", Toast.LENGTH_SHORT).show();
                }else if(addressSelectedId == null) {
                    Toast.makeText(getActivity(), "Select Id of Address Proof", Toast.LENGTH_SHORT).show();
                }else if(addressProofIDNo.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter ID No of Address Proof", Toast.LENGTH_SHORT).show();
                }else if(addressProofAddress.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Enter Address of Address Proof", Toast.LENGTH_SHORT).show();
                } else {
                    setPageVisibleShowORGone(4);
                }
                break;
            default:break;
        }
    }

    /*Spinner Item Select*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.spinner_occupation:
                if(position > 0){
                    occupationId = String.valueOf(occupationList.get(position - 1).getId());
                }else {
                    occupationId = null;
                }
                break;
            case R.id.spinner_religion:
                if(position > 0){
                    religionId = String.valueOf(religionList.get(position - 1).getId());
                }else {
                    religionId = null;
                }
                break;
            case R.id.spinner_categories:
                if(position > 0){
                    categoriesId = String.valueOf(categoriesList.get(position - 1).getId());
                }else {
                    categoriesId = null;
                }
                break;
            case R.id.nominee_relationship:
                if(position > 0){
                    relationshipId = String.valueOf(relationshipList.get(position - 1).getId());
                }else {
                    categoriesId = null;
                }
                break;
            case R.id.spinner_residence_state:
                if(position > 0){
                    stateId = String.valueOf(stateList.get(position - 1).getId());
                    getDistrictCityData(stateId, "District");
                }else {
                    stateId = null;
                    distrctId = null;
                    cityId = null;
                    distrctNameList.clear();
                    cityNameList.clear();
                }
                break;
            case R.id.spinner_residence_district:
                if(position > 0){
                    distrctId = String.valueOf(distrctList.get(position - 1).getId());
                    getDistrictCityData(stateId, "City");
                }else {
                    distrctId = null;
                    cityId = null;
                    cityNameList.clear();
                }
                break;
            case R.id.spinner_residence_city:
                if(position > 0){
                    cityId = String.valueOf(cityList.get(position - 1).getId());
                }else {
                    cityId = null;
                }
                break;
            case R.id.spinner_idproof_document:
                if(position > 0){
                    idProofId = String.valueOf(idProofList.get(position - 1).getId());
                }else {
                    idProofId = null;
                }
                break;
            case R.id.spinner_address_id:
                if(position > 0){
                    addressSelectedId = String.valueOf(idProofList.get(position - 1).getId());
                }else {
                    addressSelectedId = null;
                }
                break;
            default:break;
        }
    }

    private void getDistrictCityData(String stateID, final String type) {
        progressDialog.show();
        distrctList.clear();
        distrctNameList.clear();
        cityList.clear();
        cityNameList.clear();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _stateId = RequestBody.create(MediaType.parse("multipart/form-data"), stateID);
        final Call<StateCityModel> getDistrictCityData = RestHandler.getApiService().GET_DISTRICT_CITY_DATA(_member_id, _token, _stateId);
        getDistrictCityData.enqueue(new Callback<StateCityModel>() {
            @Override
            public void onResponse(Call<StateCityModel> call, Response<StateCityModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();

                    if (response.body().getCode() == 200) {
                        if(type.equalsIgnoreCase("District")){
                            distrctNameList.add("Select District");
                            for (StateCityModel.Result data : response.body().getResult()) {
                                distrctList.add(data);
                                distrctNameList.add(data.getName());
                            }

                            ArrayAdapter<String> districtArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, distrctNameList);
                            districtArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                            distrctSpinner.setAdapter(districtArrayAdapter);
                        }else if(type.equalsIgnoreCase("City")){
                            cityNameList.add("Select City");
                            for (StateCityModel.Result data : response.body().getResult()) {
                                cityList.add(data);
                                cityNameList.add(data.getName());
                            }
                            ArrayAdapter<String> cityArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, cityNameList);
                            cityArrayAdapter.setDropDownViewResource(R.layout.spiner_item);
                            citySpinner.setAdapter(cityArrayAdapter);
                        }
                    }else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StateCityModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
                    }else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    }
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , REQUEST_PICK_IMAGE);
                    }else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_PERMISSION);
                    }
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        } else if (requestCode == PICK_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickPhoto.setType("image/*");
            startActivityForResult(pickPhoto , REQUEST_PICK_IMAGE);
        }
    }

    public File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "sbmfa");
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d("App", "failed to create directory");
        }
        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Bitmap photo = (Bitmap) (data != null ? data.getExtras().get("data") : null);
            if(selectImageType.equals("Photo")){
                photoFile = new File(convertImageUriToFile(data.getData(), getActivity()));
                memberPhotoImageView.setImageBitmap(photo);
                memberPhotoCancelImageView.setVisibility(View.VISIBLE);
            }else{
                sigFile = new File(convertImageUriToFile(data.getData(), getActivity()));
                memberSignatureImageView.setImageBitmap(photo);
                memberSignatureCancelImageView.setVisibility(View.VISIBLE);
            }

        }else if(requestCode == REQUEST_PICK_IMAGE){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream;
                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                if(selectImageType.equals("Photo")){
                    photoFile = new File(convertImageUriToFile(imageUri, getActivity()));
                    memberPhotoImageView.setImageBitmap(selectedImage);
                    memberPhotoCancelImageView.setVisibility(View.VISIBLE);
                }else{
                    sigFile = new File(convertImageUriToFile(imageUri, getActivity()));
                    memberSignatureImageView.setImageBitmap(selectedImage);
                    memberSignatureCancelImageView.setVisibility(View.VISIBLE);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static String convertImageUriToFile ( Uri imageUri, Activity activity )  {

        Cursor cursor = null;
        String Path = null;

        try {
            String [] proj={
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };

            cursor = activity.managedQuery(
                    imageUri,         //  Get data for specific image URI
                    proj,             //  Which columns to return
                    null,             //  WHERE clause; which rows to return (all rows)
                    null,             //  WHERE clause selection arguments (none)
                    null              //  Order-by clause (ascending by name)
            );
            int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int size = cursor.getCount();
            if (size == 0) {
                Path = null;
            } else {
                if (cursor.moveToFirst()) {
                    Path = cursor.getString(file_ColumnIndex);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return Path;
    }
}