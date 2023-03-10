package com.associate.sbmfa.Fragment.AssociateManagement;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.QuotaBusinessReportAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.QuotaBusiness_Report_Child_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.QuotaBusiness_Report_Parent_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.QoutaReport;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.QoutaReportResponse;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.associate.sbmfa.BuildConfig;

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


public class QuotaBusinessReportFragment extends Fragment {
    View RootView;
    ImageView pdfdwon1;
    QuotaBusinessReportAdapter quotaBusinessReportAdapter;
    ExpandableListView expListView;
    ArrayList<QuotaBusiness_Report_Parent_model> quotaBusiness_Report_Parent_model=new ArrayList<>();
    List<Associate>  associateList = new ArrayList<>();
    Spinner spinner2,length;
    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> datelengthArr =new ArrayList<>();
    ImageView imageViewSerach;
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay;
    String dateFrom,dateto;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewBack;
    int lengthValue=15,page=1;
    SessionManager sessionManager;
    String member_id="",fromDateval="",toDateval="",associateId="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    ImageButton next,back;
    GoogleProgressDialog googleProgressDialog;
    Button apply,reset;
    EditText editTextTextPersonName;
    TextView textViewNotfound;
    File file;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_quota_business_report, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        pdfdwon1=RootView.findViewById(R.id.pdfdwon);
        pdfdwon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 createExcelSheet();

            }
        });
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        length= RootView.findViewById(R.id.length);
        apply=RootView.findViewById(R.id.apply);
        reset=RootView.findViewById(R.id.reset);
        editTextTextPersonName=RootView.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        textViewNotfound = RootView.findViewById(R.id.textView60);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);

        quotaBusinessReportAdapter = new QuotaBusinessReportAdapter(getActivity(), quotaBusiness_Report_Parent_model);
        expListView.setAdapter(quotaBusinessReportAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        getMemberList(member_id,lengthValue,page,token,"","","");
        quotaBusinessReportAdapter.notifyDataSetChanged();
        /*datelengthArr.add(String.valueOf(lengthValue));
        datelengthArr.add("20");
        datelengthArr.add("30");
        datelengthArr.add("40");
        datelengthArr.add("50");
        datelengthArr.add("60");
        datelengthArr.add("70");
        datelengthArr.add("80");
        datelengthArr.add("90");
        datelengthArr.add("100");
        ArrayAdapter<String> adapterselectlength = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, datelengthArr);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        length.setAdapter(adapterselectlength);
        length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    lengthValue = position * 10;
                }else{
                    lengthValue =10;
                }
                getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quotaBusiness_Report_Parent_model.clear();
                quotaBusinessReportAdapter.notifyDataSetChanged();
                page++;
                lengthValue=15;
               getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quotaBusiness_Report_Parent_model.clear();
                quotaBusinessReportAdapter.notifyDataSetChanged();
                if(page > 1) {
                    page--;
                    lengthValue=15;
                    getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTextPersonName.setText("");
                fromDate.setHint("From Date");
                toDate.setHint("To Date");
                fromDate.setText("");
                toDate.setText("");
                dateFrom="";
                dateto="";
                spinner2.setSelection(0);
                quotaBusiness_Report_Parent_model.clear();
                quotaBusinessReportAdapter.notifyDataSetChanged();
                getMemberList(member_id,lengthValue,page,token,"","","");

            }
        });
        getAssociateActiveList(member_id,token);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quotaBusiness_Report_Parent_model.clear();
                quotaBusinessReportAdapter.notifyDataSetChanged();
               getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
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

        return  RootView;
    }

    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    public  void getAssociateActiveList(final String assciate_no,String token){
        googleProgressDialog.show1("Loading...");
        quotaBusiness_Report_Parent_model.clear();
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

    int page_data_length;
    public  void getMemberList(final String member_id, int length, int page, String token, String assciateId, String fromDate, String toDate) {
            googleProgressDialog.show1("Loading...");
            quotaBusiness_Report_Parent_model.clear();
            if (page <= 0) {
                page = 1;
            }
            if (length <= 0) {
                length = 1;
            }
            page_data_length = length;
            RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
            RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
            RequestBody _assciateId = RequestBody.create(MediaType.parse("multipart/form-data"), assciateId);

            Call<QoutaReportResponse> applicationsListResponesCall = RestHandler.getApiService().Quota_Busniess_Mangement_RESPONES_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _assciateId);
            applicationsListResponesCall.enqueue(new Callback<QoutaReportResponse>() {
                @Override
                public void onResponse(Call<QoutaReportResponse> call, Response<QoutaReportResponse> response) {
                    googleProgressDialog.dismiss();
                    if (response != null) {
                        if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                            List<QoutaReport> qoutaReport = response.body().getResult().getQoutaReport();
                            if (qoutaReport.size() > 0){
                                textViewNotfound.setVisibility(View.GONE);
                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt){
                                    if (qoutaReport.size() == 15){
                                        next.setEnabled(true);
                                    }else {
                                        next.setEnabled(false);
                                    }
                                }else {
                                    next.setEnabled(false);
                                }
//                                int i = 1;
                                quotaBusiness_Report_Parent_model.clear();

                                for (QoutaReport qoutaReportItem : qoutaReport) {
                                    int i = (page_data_length * (page - 1)) + (quotaBusiness_Report_Parent_model.size() + 1);
                                    ArrayList<QuotaBusiness_Report_Child_model> child_models = new ArrayList<>();
                                    child_models.add(new QuotaBusiness_Report_Child_model(qoutaReportItem.getSector(),
                                            qoutaReportItem.getRegan(),
                                            qoutaReportItem.getZone(),
                                            qoutaReportItem.getAssociateCode(),
                                            qoutaReportItem.getAssociateName(),
                                            qoutaReportItem.getAssociateCarder(),
                                            qoutaReportItem.getQuotaBusinessTargetSelfAmt(),
                                            qoutaReportItem.getAchievedTargetSelfAmt().toString(),
                                            qoutaReportItem.getSeniorCode(),
                                            qoutaReportItem.getSeniorName(),
                                            qoutaReportItem.getSeniorCarder(),
                                            qoutaReportItem.getJoiningDate(),
                                            qoutaReportItem.getMobileNumber(),
                                            qoutaReportItem.getStatus(),
                                            qoutaReportItem.getQuotaBusinessTargetSelfPercentage().toString(),
                                            qoutaReportItem.getAchievedTargetSelfPercentage().toString(),
                                            qoutaReportItem.getQuotaBusinessTargetTeamAmt(),
                                            qoutaReportItem.getAchievedTargetTeamAmt(),
                                            qoutaReportItem.getQuotaBusinessTargetTeamPercentage(),
                                            qoutaReportItem.getAchievedTargetTeamPercentage()));
                                    quotaBusiness_Report_Parent_model.add(new QuotaBusiness_Report_Parent_model(String.valueOf(i), qoutaReportItem.getAssociateName(), qoutaReportItem.getAssociateCode(), "", child_models));
//                                    i++;
                                }
                                quotaBusinessReportAdapter.notifyDataSetChanged();
                            }else {
                                next.setEnabled(false);
                                textViewNotfound.setVisibility(View.VISIBLE);
                            }

                        } else {
                            next.setEnabled(false);
                            textViewNotfound.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        next.setEnabled(false);
                        textViewNotfound.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<QoutaReportResponse> call, Throwable t) {
                    textViewNotfound.setVisibility(View.VISIBLE);
                    googleProgressDialog.dismiss();
                    next.setEnabled(false);
                }
            });

    }

    @SuppressLint("LongLogTag")
    private void createExcelSheet() {
        getMemberList2(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);

    }
    public  void getMemberList2(final String member_id, int length, int page, String token, String assciateId, String fromDate, String toDate) {
        try {
            googleProgressDialog.show1("Loading...");

            RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf("0"));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RequestBody _assciateId = RequestBody.create(MediaType.parse("multipart/form-data"), "");

            Call<QoutaReportResponse> applicationsListResponesCall = RestHandler.getApiService().Quota_Busniess_Mangement_RESPONES_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _assciateId);
            applicationsListResponesCall.enqueue(new Callback<QoutaReportResponse>() {
                @Override
                public void onResponse(Call<QoutaReportResponse> call, Response<QoutaReportResponse> response) {
                    googleProgressDialog.dismiss();
                    ArrayList<QuotaBusiness_Report_Parent_model> quotaBusiness_Report_Parent_model=new ArrayList<>();

                    if (response != null) {
                        if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }
                            List<QoutaReport> qoutaReport = response.body().getResult().getQoutaReport();
                            if (qoutaReport.size() > 0){
                                textViewNotfound.setVisibility(View.GONE);

                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt){
                                    next.setEnabled(true);
                                }else {
                                    next.setEnabled(false);
                                }
                                int i = 1;

                                for (QoutaReport qoutaReportItem : qoutaReport) {
                                    ArrayList<QuotaBusiness_Report_Child_model> child_models = new ArrayList<>();
                                    child_models.add(new QuotaBusiness_Report_Child_model(qoutaReportItem.getSector(),
                                            qoutaReportItem.getRegan(),
                                            qoutaReportItem.getZone(),
                                            qoutaReportItem.getAssociateCode(),
                                            qoutaReportItem.getAssociateName(),
                                            qoutaReportItem.getAssociateCarder(),
                                            qoutaReportItem.getQuotaBusinessTargetSelfAmt(),
                                            qoutaReportItem.getAchievedTargetSelfAmt().toString(),
                                            qoutaReportItem.getSeniorCode(),
                                            qoutaReportItem.getSeniorName(),
                                            qoutaReportItem.getSeniorCarder(),
                                            qoutaReportItem.getJoiningDate(),
                                            qoutaReportItem.getMobileNumber(),
                                            qoutaReportItem.getStatus(),
                                            qoutaReportItem.getQuotaBusinessTargetSelfPercentage().toString(),
                                            qoutaReportItem.getAchievedTargetSelfPercentage().toString(),
                                            qoutaReportItem.getQuotaBusinessTargetTeamAmt(),
                                            qoutaReportItem.getAchievedTargetTeamAmt(),
                                            qoutaReportItem.getQuotaBusinessTargetTeamPercentage(),
                                            qoutaReportItem.getAchievedTargetTeamPercentage()));
                                    quotaBusiness_Report_Parent_model.add(new QuotaBusiness_Report_Parent_model(String.valueOf(i), qoutaReportItem.getAssociateName(), qoutaReportItem.getAssociateCode(), "", child_models));
                                    i++;
                                }

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
                                for (int i=0; i<quotaBusiness_Report_Parent_model.size();i++){

                                    QuotaBusiness_Report_Child_model sub = quotaBusiness_Report_Parent_model.get(i).getQuotaBusiness_Report_Child_models().get(0);
                                    Label label0 = new Label(0,i,sub.getAssociateName());
//                for (int j=0;j<quotaBusiness_Report_Parent_model.get(i).getQuotaBusiness_Report_Child_models().size();j++){
//                    Log.e("getQuotaBusiness_Report_Child_models",quotaBusiness_Report_Parent_model.get(i).getQuotaBusiness_Report_Child_models().get(j).getAssociateName());
//                    Label label1 = new Label(0,i,quotaBusiness_Report_Parent_model.get(i).getQuotaBusiness_Report_Child_models().get(j).getAssociateName());
                                    try {
                                        sheet.addCell(label0);
                                    } catch (WriteException e) {
                                        e.printStackTrace();
                                    }
//                }
                                }
                                Label label1 = new Label(0,0,"So Name");
                                Label label2 = new Label(1, 0, "Ro Name");
                                Label label3 = new Label(2,0,"Zo Name");
                                Label label4 = new Label(3,0,"Associate Code");
                                Label label5 = new Label(4,0,"Associate Name");
                                Label label6 = new Label(5,0,"Associate Carder");
                                Label label7 = new Label(6,0,"Quota Business Target");
                                Label label8 = new Label(7,0,"Achieved Target");
                                Label label9 = new Label(8,0,"Senior Code");
                                Label label10 = new Label(9,0,"Senior Name");
                                Label label11 = new Label(10,0,"Senior Carder");
                                Label label12 = new Label(11,0,"Joining Date");
                                Label label13 = new Label(12,0,"Mobile Number");
                                Label label14 = new Label(13,0,"Status");
                                try {
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
                                    sheet.addCell(label13);
                                    sheet.addCell(label14);
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                                workbook.write();
                                workbook.close();
                                //createExcel(excelSheet);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
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
                public void onFailure(Call<QoutaReportResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();

                }
            });
        }catch (Exception e){

        }
    }
}

