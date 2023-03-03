package com.associate.sbmfa.Fragment.AssociateManagement;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.BuildConfig;
import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateCollection_Details_Adapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_Collection_parent_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_collection_details_child;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.QuotaBusiness_Report_Child_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCollectionReport;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCollectionReportData;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociateCollectionFragment extends Fragment {
    View RootView;
    AssociateCollection_Details_Adapter listAdapter;
    ExpandableListView expListView;
    ArrayList<Associate_Collection_parent_model> parent_models=new ArrayList<>();
    ArrayList<Associate_Collection_parent_model> exportParent_models=new ArrayList<>();
    Spinner spinner2,spinner3;

    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> branch_name =new ArrayList<>();
    ArrayList<String> dateBranchId =new ArrayList<>();


    ImageView imageViewSerach,pdfdwon1;
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay;
    String dateFrom = "",dateto = "";
    int lengthValue=15,page=1;

    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    private ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="",fromDateval="0",toDateval="0";
    HashMap<String ,String > UserDataToken;
    String token="",stringBranchSpiner = "",associateId="0";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply,reset;
    List<Associate>  associateList = new ArrayList<>();
    ArrayAdapter<String> adapterBranch;
    File file;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView =  inflater.inflate(R.layout.fragment_associate_collection, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);

        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        spinner3=RootView.findViewById(R.id.spinner3);
        apply=RootView.findViewById(R.id.apply);
        reset=RootView.findViewById(R.id.reset);
        pdfdwon1=RootView.findViewById(R.id.pdfdwon);



        branch_name.add("Select Branch");
        dateBranchId.add("0");
        adapterBranch = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, branch_name);
        adapterBranch.setDropDownViewResource(R.layout.spiner_item);
        spinner3.setAdapter(adapterBranch);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        pdfdwon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExcelSheet();

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
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateFrom.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select From Date!!!", Toast.LENGTH_SHORT).show();
                }else{
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
                                    dateto = "" + dayOfMonth + "/" + monthOfYear1 + "/" + year;
                                    toDate.setText(dateto);
                                    toDateval = toDate.getText().toString();
                                }
                            }, mYear, mMonth, mDay);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                    Date date = null;
                    try {
                        date = sdf.parse(dateFrom);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                    datePickerDialog.show();
                }
            }
        });
        getAssociateActiveList(member_id,token);
        getBranch(member_id,token);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                associate_data(page,lengthValue,member_id,stringBranchSpiner,dateFrom,dateto,associateId);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                spinner2.setSelection(0);
                spinner3.setSelection(0);
                stringBranchSpiner = "";
                associateId = "";
                page = 1;
                dateFrom = "";
                dateto = "";
                fromDate.setText("From Date");
                toDate.setText("To Date");
                associate_data(page,lengthValue,member_id,"","","",associateId);
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
        associate_data(page,lengthValue,member_id,stringBranchSpiner,dateFrom,dateto,associateId);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                associate_data(page,lengthValue,member_id,stringBranchSpiner,dateFrom,dateto,associateId);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1) {
                    page--;
                    associate_data(page,lengthValue,member_id,stringBranchSpiner,dateFrom,dateto,associateId);
                }
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
        return RootView;
    }

    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
    void associate_data(int page,int length,String member_id,String branch_id,String startdate,String enddate,String associateId){
        googleProgressDialog.show1("Loading...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
        RequestBody start_date = RequestBody.create(MediaType.parse("multipart/form-data"), startdate);
        RequestBody end_date = RequestBody.create(MediaType.parse("multipart/form-data"), enddate);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
        RequestBody _associateId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(associateId));
        RestHandler.getApiService().ASSOCIATE_COLLECTION_REPORT_CALL(_member_id,_token,_branch_id,start_date,end_date,_page,_length,_associateId).enqueue(new Callback<AssociateCollectionReport>() {
            @Override
            public void onResponse(Call<AssociateCollectionReport> call, Response<AssociateCollectionReport> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        parent_models.clear();
                        googleProgressDialog.dismiss();
                        List<AssociateCollectionReportData> associateCollectionReportData=response.body().getResult().getBusinessReport();
                        int i=1;


                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt){
                            next.setEnabled(associateCollectionReportData.size() == 15 ? true : false);
                        }else {
                            next.setEnabled(false);
                        }

                        for (AssociateCollectionReportData associateCollectionReportData1:associateCollectionReportData){
                            ArrayList<Associate_collection_details_child> child_models=new ArrayList<>();
                            child_models.clear();
                            child_models.add(new Associate_collection_details_child(associateCollectionReportData1.getBranch(),
                                    String.valueOf(associateCollectionReportData1.getBranchCode()),
                                    String.valueOf(associateCollectionReportData1.getDailyDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getMonthlyDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getFdDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getTcc()),
                                    String.valueOf(associateCollectionReportData1.getLoanRecoveryAmount()),
                                    String.valueOf(associateCollectionReportData1.getCollectionAll()),
                                    String.valueOf(associateCollectionReportData1.getNcc()),
                                    associateCollectionReportData1.getAssociateName(),
                                    associateCollectionReportData1.getAssociateCode()
                            ));
                            parent_models.add(new Associate_Collection_parent_model(String.valueOf(i),associateCollectionReportData1.getAssociateName(),associateCollectionReportData1.getAssociateCode(),associateCollectionReportData1.getAssociateCode(),child_models));
                            i++;
                        }
                        listAdapter = new AssociateCollection_Details_Adapter(getActivity(), parent_models);
                        expListView.setAdapter(listAdapter);
                        DisplayMetrics metrics = new DisplayMetrics();
                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                        int width = metrics.widthPixels;
                        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

                    }else{
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        googleProgressDialog.dismiss();
                    }
                }else{
                    googleProgressDialog.dismiss();
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<AssociateCollectionReport> call, Throwable t) {
                googleProgressDialog.dismiss();
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
                                        branch_name.add(branch.getName());
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
    @SuppressLint("LongLogTag")
    private void createExcelSheet() {
        associate_data2(0,lengthValue,member_id,"","","");

    }
    void associate_data2(int page,int length,String member_id,String branch_id,String startdate,String enddate){
        googleProgressDialog.show1("Loading...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
        RequestBody start_date = RequestBody.create(MediaType.parse("multipart/form-data"), startdate);
        RequestBody end_date = RequestBody.create(MediaType.parse("multipart/form-data"), enddate);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf("1"));
        RequestBody _associateId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(associateId));
        RestHandler.getApiService().ASSOCIATE_COLLECTION_REPORT_CALL(_member_id,_token,_branch_id,start_date,end_date,_page,_length,_associateId).enqueue(new Callback<AssociateCollectionReport>() {
            @Override
            public void onResponse(Call<AssociateCollectionReport> call, Response<AssociateCollectionReport> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        googleProgressDialog.dismiss();
                        exportParent_models.clear();
                        List<AssociateCollectionReportData> associateCollectionReportData=response.body().getResult().getBusinessReport();
                        int ii=1;
                        ArrayList<Associate_Collection_parent_model> parent_models=new ArrayList<>();
                        for (AssociateCollectionReportData associateCollectionReportData1:associateCollectionReportData){
                            ArrayList<Associate_collection_details_child> child_models=new ArrayList<>();
                            child_models.clear();
                            child_models.add(new Associate_collection_details_child(associateCollectionReportData1.getBranch(),
                                    String.valueOf(associateCollectionReportData1.getBranchCode()),
                                    associateCollectionReportData1.getAssociateName(),
                                    associateCollectionReportData1.getAssociateCode(),
                                    String.valueOf(associateCollectionReportData1.getDailyDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getMonthlyDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getFdDenoSum()),
                                    String.valueOf(associateCollectionReportData1.getTcc()),
                                    String.valueOf(associateCollectionReportData1.getLoanRecoveryAmount()),
                                    String.valueOf(associateCollectionReportData1.getCollectionAll()),
                                    String.valueOf(associateCollectionReportData1.getNcc())


                            ));
                            exportParent_models.add(new Associate_Collection_parent_model(String.valueOf(ii),associateCollectionReportData1.getAssociateName(),associateCollectionReportData1.getAssociateCode(),associateCollectionReportData1.getAssociateCode(),child_models));
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
                            workbook = Workbook.createWorkbook(file, wbSettings);
                            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                            for (int i=0; i<exportParent_models.size();i++){
                                Associate_collection_details_child sub = exportParent_models.get(i).getAssociate_collection_details_children().get(0);
                                Label label0 = new Label(0,i,sub.getBranch());
                                Label label1 = new Label(0,i,sub.getBranch_code());
                                Label label2 = new Label(0,i,sub.getAssociate_code());
                                Label label3 = new Label(0,i,sub.getAssociate_name());
                                Label label4 = new Label(0,i,sub.getDaily_deno_sum());
                                Label label5 = new Label(0,i,sub.getMonthly_deno_sum());
                                Label label6 = new Label(0,i,sub.getFd_deno_sum());
                                Label label7 = new Label(0,i,sub.getCollection_all());
                                Label label8 = new Label(0,i,sub.getLoan_recovery_amount());
                                Label label9 = new Label(0,i,sub.getNcc());
                                Label label10 = new Label(0,i,sub.getTcc());
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
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                            }
                            Label label21 = new Label(0,0,"Branch Name");
                            Label label22 = new Label(1, 0, "Branch Code");
                            Label label23 = new Label(2,0,"Associate Code");
                            Label label24 = new Label(3,0,"Associate Name");
                            Label label25 = new Label(4,0,"DAILY");
                            Label label26 = new Label(5,0,"MONTHLY");
                            Label label27 = new Label(6,0,"FD");
                            Label label28 = new Label(7,0,"Collection");
                            Label label29 = new Label(8,0,"Loan_REC");
                            Label label30 = new Label(10,0,"Ncc");
                            Label label31 = new Label(11,0,"Tcc");

                            try {
                                sheet.addCell(label21);
                                sheet.addCell(label22);
                                sheet.addCell(label23);
                                sheet.addCell(label24);
                                sheet.addCell(label25);
                                sheet.addCell(label26);
                                sheet.addCell(label27);
                                sheet.addCell(label28);
                                sheet.addCell(label29);
                                sheet.addCell(label30);
                                sheet.addCell(label31);
                            } catch (WriteException e) {
                                e.printStackTrace();
                            }
                            workbook.write();
                            workbook.close();
                        } catch (IOException e) {
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

                                Uri path = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", file);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setDataAndType(path, "application/vnd.ms-excel");
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                try {
                                    getActivity().startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<AssociateCollectionReport> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });

    }



}