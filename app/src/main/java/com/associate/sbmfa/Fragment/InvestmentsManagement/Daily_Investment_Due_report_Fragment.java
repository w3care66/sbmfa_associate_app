package com.associate.sbmfa.Fragment.InvestmentsManagement;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.BuildConfig;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.DailyInvestmentDuereportAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.Investment_Plan_Details_Adapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.InvestmentDueReportChildModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.InvestmentDueReportParentModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_child_model;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_parent_model;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentDueReportData;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentDueReportResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentPlanDetails.InvestmentlistingPlanDetailsMember;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentPlanDetails.InvestmentlistingPlanDetailsResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberCheckResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daily_Investment_Due_report_Fragment extends Fragment {
    View view;
    DailyInvestmentDuereportAdapter dailyInvestmentDuereportAdapter;
    ArrayList<InvestmentDueReportParentModel> parent_models= new ArrayList<>();
    List<Associate> associateList = new ArrayList<>();
    ExpandableListView expListView;
    Spinner spinner2,length;
    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> datelengthArr =new ArrayList<>();
    ArrayList<String> planArr =new ArrayList<>();
    ArrayList<String> planArrID =new ArrayList<>();
    ImageView pdfdwon,imageViewSerach;
    TextView count1,name1,fromDate, toDate,datanotfound;
    private int mYear, mMonth, mDay;
    String dateFrom="",dateto;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewBack;

    int lengthValue=15,page=1;
    String planId ="";
    SessionManager sessionManager;
    String member_id="",fromDateval="",toDateval="",associateId="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply,reset;
    EditText editTextTextPersonName;
    File file;
    EditText scheme_account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_daily__investment__due_report_, container, false);
        expListView = (ExpandableListView)view. findViewById(R.id.lvExp);
        name1= view.findViewById(R.id.name1);
        count1= view.findViewById(R.id.count1);
        fromDate = view.findViewById(R.id.fromDate);
        toDate = view.findViewById(R.id.toDate);
        spinner2 = view.findViewById(R.id.spinner2);
        imageViewSerach = view.findViewById(R.id.imageView110);
        next = view.findViewById(R.id.imageButton2);
        back = view.findViewById(R.id.imageButton);
        length= view.findViewById(R.id.length);
        apply=view.findViewById(R.id.apply);
        scheme_account=view.findViewById(R.id.line22);
        datanotfound=view.findViewById(R.id.not_found);
        reset=view.findViewById(R.id.reset);
        editTextTextPersonName=view.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = view.findViewById(R.id.constraintLayout8);
        pdfdwon=view.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()){
                    createExcelSheet();
                }

            }
        });


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
                                dateFrom = "" + dayOfMonth + "/" + monthOfYear1 + "/" + year;
                                fromDate.setText(dateFrom);
                                fromDateval=fromDate.getText().toString();
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();

            }
        });
        dateStrings.add("Select Associate");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinner2.setAdapter(adapterselectDate);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    associateId = associateList.get(position - 1).getId().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageViewSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSerach == false){
                    imageViewSerach.setImageResource(R.drawable.ic_baseline_close_24);
                    constraintLayoutOptions.setVisibility(View.VISIBLE);
                    isSerach = true;
                }else {
                    imageViewSerach.setImageResource(R.drawable.magnifyingglass);
                    constraintLayoutOptions.setVisibility(View.GONE);
                    isSerach = false;
                }
            }
        });

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


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                dailyInvestmentDuereportAdapter.notifyDataSetChanged();
                page=page+1;
                // lengthValue=lengthValue * page;
                getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,"","");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1) {
                    parent_models.clear();
                    dailyInvestmentDuereportAdapter.notifyDataSetChanged();
                    page = page - 1;
                    // lengthValue=lengthValue * page;
                    getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,"","");
                }
            }
        });

        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        getAssociateActiveList(member_id,token);
       getMemberList(member_id,15,page,token,"","","daily","");
        dailyInvestmentDuereportAdapter = new DailyInvestmentDuereportAdapter(getActivity(), parent_models);
        expListView.setAdapter(dailyInvestmentDuereportAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                page =1;
              getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,"daily",scheme_account.getText().toString());
            }
        });
        editTextTextPersonName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getMemberCheck(member_id,token,editTextTextPersonName.getText().toString());
                    return true;
                }
                return false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTextPersonName.setText("");
                associateId = "";
                fromDate.setHint("From Date");
                toDate.setHint("To Date");
                fromDate.setText("");
                toDate.setText("");
                dateFrom="";
                fromDateval = "";
                toDateval = "";
                dateto="";
                page = 1;
                planId = "";
                spinner2.setSelection(0);
                scheme_account.getText().clear();
                getMemberList(member_id,15,page,token,"","","",scheme_account.getText().toString());

            }
        });
       // getBranchList(member_id,token);

        return view;
    }

    private void createExcelSheet() {
        getMemberList2(member_id,lengthValue,page,token,associateId,fromDateval,"daily","");
    }

    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
    public  void getMemberCheck(final String assciate_no,  String token,String memberId){
        googleProgressDialog.show1("Loading...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody  _member_id= RequestBody.create(MediaType.parse("multipart/form-data"), memberId);
        Call<AssociateMemberCheckResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Check_RESPONES_CALL(_assciate_no,_member_id,_token);
        applicationsListResponesCall.enqueue(new Callback<AssociateMemberCheckResponse>() {
            @Override
            public void onResponse(Call<AssociateMemberCheckResponse> call, Response<AssociateMemberCheckResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0){
                            Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            sessionManager.logoutUser();
                        }
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateMemberCheckResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public  void getAssociateActiveList(final String assciate_no,String token){
        googleProgressDialog.show1("Loading...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<AssociateMemberListResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Associate_active_List_RESPONES_CALL(_assciate_no,_token);
        applicationsListResponesCall.enqueue(new Callback<AssociateMemberListResponse>() {
            @Override
            public void onResponse(Call<AssociateMemberListResponse> call, Response<AssociateMemberListResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0){
                            Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            sessionManager.logoutUser();
                        }
                        // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        associateList = response.body().getResult().getAssociateList();
                        Collections.sort(associateList, new Comparator<Associate>() {
                            @Override
                            public int compare(Associate o1, Associate o2) {
                                return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
                            }
                        });
                        int i=1;
                        for (Associate memberItem : associateList) {
                            dateStrings.add(memberItem.getName());
                        }

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateMemberListResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void getMemberList(final String member_id, int length, int page, String token, String assciateId, String fromDate, String slug,String account_number){
        googleProgressDialog.show1("Loading...");
        parent_models.clear();
//       listAdapter.notifyDataSetChanged();

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
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
        RequestBody _slug = RequestBody.create(MediaType.parse("multipart/form-data"), "daily");
        RequestBody  _assciateId= RequestBody.create(MediaType.parse("multipart/form-data"), assciateId);
        RequestBody  _scheme_account_number= RequestBody.create(MediaType.parse("multipart/form-data"), account_number);
        RequestBody  plan_id= RequestBody.create(MediaType.parse("multipart/form-data"), "0");
        Call<InvestmentDueReportResponse> applicationsListResponesCall = RestHandler.getApiService().INVESTMENT_DUE_REPORT_RESPONSE_CALL(_assciate_no,_page,_length,_token,_fromDate,_slug,_assciateId,_scheme_account_number,plan_id);
        applicationsListResponesCall.enqueue(new Callback<InvestmentDueReportResponse>() {
            @Override
            public void onResponse(Call<InvestmentDueReportResponse> call, Response<InvestmentDueReportResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Log.e("ressss---",""+response.body().getMessages());
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<InvestmentDueReportData> member = response.body().getResult().getData();
                        if(member.size() == 0 ){
                            datanotfound.setVisibility(View.VISIBLE);
                            name1.setVisibility(View.GONE);
                            count1.setVisibility(View.GONE);
                            next.setEnabled(false);
                        }else{
                            datanotfound.setVisibility(View.GONE);
                            name1.setVisibility(View.VISIBLE);
                            count1.setVisibility(View.VISIBLE);
                            next.setEnabled(true);
                        }
                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt){
                            next.setEnabled(member.size() == 15 ? true : false);
                        }else {
                            next.setEnabled(false);
                        }
                        int i=1;
                        for (InvestmentDueReportData memberItem : member) {
                            ArrayList<InvestmentDueReportChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new InvestmentDueReportChildModel(memberItem.getBranchName(),String.valueOf(memberItem.getBranchCode()),memberItem.getMemberId(),memberItem.getAssociateCode(),
                                    memberItem.getAssociateName(),memberItem.getAccountNo(),memberItem.getPlanName(),memberItem.getTenure()
                            ,memberItem.getDenoAmount(),String.valueOf(memberItem.getDueEmi()),String.valueOf(memberItem.getDueEmiAmount())));
                            parent_models.add(new InvestmentDueReportParentModel(String.valueOf(i), memberItem.getMember(), memberItem.getAccountNo(), String.valueOf(memberItem.getId()), child_models));
                            i++;
                        }
                        dailyInvestmentDuereportAdapter.notifyDataSetChanged();
                    } else {
                        datanotfound.setVisibility(View.VISIBLE);
                        next.setEnabled(false);
                        // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    datanotfound.setVisibility(View.VISIBLE);
                    next.setEnabled(false);
                    // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvestmentDueReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                datanotfound.setVisibility(View.VISIBLE);
                next.setEnabled(false);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void getMemberList2(final String member_id, int length, int page, String token, String assciateId, String fromDate, String slug,String account_number){
        googleProgressDialog.show1("Loading...");
        parent_models.clear();
//       listAdapter.notifyDataSetChanged();

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
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
        RequestBody _slug = RequestBody.create(MediaType.parse("multipart/form-data"), "daily");
        RequestBody  _assciateId= RequestBody.create(MediaType.parse("multipart/form-data"), assciateId);
        RequestBody  _scheme_account_number= RequestBody.create(MediaType.parse("multipart/form-data"), account_number);
        RequestBody  plan_id= RequestBody.create(MediaType.parse("multipart/form-data"), "0");
        Call<InvestmentDueReportResponse> applicationsListResponesCall = RestHandler.getApiService().INVESTMENT_DUE_REPORT_RESPONSE_CALL(_assciate_no,_page,_length,_token,_fromDate,_slug,_assciateId,_scheme_account_number,plan_id);
        applicationsListResponesCall.enqueue(new Callback<InvestmentDueReportResponse>() {
            @Override
            public void onResponse(Call<InvestmentDueReportResponse> call, Response<InvestmentDueReportResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Log.e("ressss---",""+response.body().getMessages());
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<InvestmentDueReportData> member = response.body().getResult().getData();

                        int ii=1;
                        for (InvestmentDueReportData memberItem : member) {
                            ArrayList<InvestmentDueReportChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new InvestmentDueReportChildModel(memberItem.getBranchName(),String.valueOf(memberItem.getBranchCode()),memberItem.getMemberId(),memberItem.getAssociateCode(),
                                    memberItem.getAssociateName(),memberItem.getAccountNo(),memberItem.getPlanName(),memberItem.getTenure()
                                    ,memberItem.getDenoAmount(),String.valueOf(memberItem.getDueEmi()),String.valueOf(memberItem.getDueEmiAmount())));
                            parent_models.add(new InvestmentDueReportParentModel(String.valueOf(ii), memberItem.getMember(), memberItem.getAccountNo(), String.valueOf(memberItem.getId()), child_models));
                            ii++;
                        }
                        String Fnamexls="excelSheet"+System.currentTimeMillis()+ ".xls";
                        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File directory = new File (sdCard.getAbsolutePath() + "/SBMFA");
                        if (directory.isDirectory()){
                            Log.e("isDirectory",""+directory.isDirectory());
                        }else {
                            Log.e("isDirectory",""+directory.isDirectory());
                            directory.mkdirs();
                        }
                        file = new File(directory, Fnamexls);

                        WorkbookSettings wbSettings = new WorkbookSettings();

                        wbSettings.setLocale(new Locale("en", "EN"));

                        WritableWorkbook workbook;
                        try {
                            int a = 1;
                            workbook = Workbook.createWorkbook(file, wbSettings);
                            //workbook.createSheet("Report", 0);
                            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                            for (int i=0; i < parent_models.size();i++){
                                InvestmentDueReportChildModel sub = parent_models.get(i).getInvestmentDueReportChildModels().get(0);
                                Label label0 = new Label(0,i+1,sub.getMember_id());
                                Label label1 = new Label(1,i+1,sub.getBranch_name());
                                Label label2 = new Label(2,i+1,sub.getBranch_code());
                                Label label3 = new Label(3,i+1,sub.getMember_id());
                                Label label4 = new Label(4,i+1,sub.getAssociate_code());
                                Label label5 = new Label(5,i+1,sub.getAssociate_name());
                                Label label6 = new Label(6,i+1,sub.getAccount_no());
                                Label label7 = new Label(7,i+1,sub.getPlan_name());
                                Label label8 = new Label(8,i+1,sub.getTenure());
                                Label label9 = new Label(9,i+1,sub.getDeno_amount());
                                Label label10 = new Label(10,i+1,sub.getDue_emi());
                                Label label11 = new Label(11,i+1,sub.getDue_emi_amount());



                                try {
                                    sheet.addCell(label0);
                                    sheet.addCell(label1);
                                    sheet.addCell(label2);
                                    sheet.addCell(label3);
                                    sheet.addCell(label4);
                                    sheet.addCell(label5);
                                    sheet.addCell(label6);
                                    sheet.addCell(label7);
                                    sheet.addCell(label8);
                                    sheet.addCell(label9);
                                    sheet.addCell(label10);
                                    sheet.addCell(label11);
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                            }
                            Label label1 = new Label(0,0,"id");
                            Label label2 = new Label(1, 0, "Branch Name");
                            Label label3 = new Label(2,0,"Branch Code");
                            Label label4 = new Label(3,0,"Member");
                            Label label5 = new Label(4,0,"Associate Code");
                            Label label6 = new Label(5,0,"Associate Name");
                            Label label7 = new Label(6,0,"Account Number");
                            Label label8 = new Label(7,0,"Plan Name");
                            Label label9 = new Label(8,0,"Tenure");
                            Label label10 = new Label(9,0,"Deno amount");
                            Label label11= new Label(10,0,"Due EMI");
                            Label label12 = new Label(11,0,"Due EMI amount");



                            sheet.addCell(label1);
                            sheet.addCell(label2);
                            sheet.addCell(label3);
                            sheet.addCell(label4);
                            sheet.addCell(label5);
                            sheet.addCell(label6);
                            sheet.addCell(label7);
                            sheet.addCell(label8);
                            sheet.addCell(label9);
                            sheet.addCell(label10);
                            sheet.addCell(label11);
                            sheet.addCell(label12);


                            workbook.write();
                            workbook.close();
                            //createExcel(excelSheet);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (RowsExceededException e) {
                            e.printStackTrace();
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                    }
                }
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Sheet Downloaded Successfully");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Share",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", file);
                                    Intent shareIntent = new Intent();
                                    shareIntent.setAction(Intent.ACTION_SEND);
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    shareIntent.setDataAndType(uri, getActivity().getContentResolver().getType(uri));
                                    shareIntent.setType("application/document");
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                    shareIntent.setPackage("com.whatsapp");
                                    getActivity().startActivity(shareIntent);
                                } catch (Exception exception){
                                    dialog.dismiss();
                                }
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Open",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                Uri uri = Uri.parse(sdCard.getAbsolutePath() + "/SBMFA");
                                intent.setDataAndType(uri, "*/*");
                                getActivity().startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }






            @Override
            public void onFailure(Call<InvestmentDueReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                datanotfound.setVisibility(View.VISIBLE);
                next.setEnabled(false);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}