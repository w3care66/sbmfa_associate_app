package com.associate.sbmfa.Fragment.ReportManagment;

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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.associate.sbmfa.BuildConfig;
import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateCommissionDetailsAdapter;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.MemberMangement.MemberDetailsFragment;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberCheckResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateBusinessCompareReportAdapter;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateCollectionCompareAdapter;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareChildModel;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareParentModel;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateCollectionCompare.AssociateCollectionCompareChildModel;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateCollectionCompare.AssociateCollectionCompareParentModel;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Aassociatebusinesscomparereporp.AssociateBusinessCompareReportBusinessSummaryReport;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Aassociatebusinesscomparereporp.AssociateBusinessCompareReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateCollectionCompare.AssociateCollectionCompareRespones;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateCollectionCompare.BusinessSummaryReport;
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

public class AssociateCollectionCompareReportFragment extends Fragment implements AssociateCommissionDetailsAdapter.EventListener {
    View RootView;
    AssociateCollectionCompareAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateCollectionCompareParentModel> parent_models = new ArrayList<>();
    ArrayList<AssociateCollectionCompareParentModel> parent_models1 = new ArrayList<>();
    List<Associate> associateList = new ArrayList<>();
    Spinner length;
    ArrayList<String> dateStrings = new ArrayList<>();
    ArrayList<String> datelengthArr = new ArrayList<>();
    ImageView imageViewSerach;
    Spinner spinnerBranch;
    TextView fromDate, toDate, datanotfound, comparefromdate, comparetodate;
    private int mYear, mMonth, mDay;
    String dateFrom = "", dateto, comparetodateval = "", comparefromdateval = "";
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView pdfdwon, imageViewBack;
    ArrayAdapter<String> adapterBranch;
    ArrayList<String> dateBranch = new ArrayList<>();
    int lengthValue = 15, page = 1;
    SessionManager sessionManager;
    String member_id = "", fromDateval = "", toDateval = "", associateId = "";
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    ImageButton next, back;
    GoogleProgressDialog googleProgressDialog;
    Button apply, reset;
    EditText editTextTextPersonName;
    ArrayList<String> dateBranchId = new ArrayList<>();
    String stringBranchSpiner = "";
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_associate_business_compare_report, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        fromDate = RootView.findViewById(R.id.fromDate);

        toDate = RootView.findViewById(R.id.toDate);
        comparefromdate = RootView.findViewById(R.id.comparefromdate);
        comparetodate = RootView.findViewById(R.id.comparetodate);
        spinnerBranch = RootView.findViewById(R.id.spinner3);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        length = RootView.findViewById(R.id.length);

        datanotfound = RootView.findViewById(R.id.not_found);
        apply = RootView.findViewById(R.id.apply);
        reset = RootView.findViewById(R.id.reset);
        editTextTextPersonName = RootView.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        title = RootView.findViewById(R.id.textView2);
        title.setText("Associate Collection Compare Report");
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                }
            }
        });
        Calendar aCalendar = Calendar.getInstance();

        aCalendar.add(Calendar.MONTH, -1);

        aCalendar.set(Calendar.DATE, 1);

        final Date firstDateOfPreviousMonth = aCalendar.getTime();


        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date lastDateOfPreviousMonth = aCalendar.getTime();
        // current end date
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
        final String date = format.format(Date.parse(firstDateOfPreviousMonth.toString()));
        fromDate.setText(date);
        fromDateval = date;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/M/yyyy");
        final String date1 = format1.format(Date.parse(lastDateOfPreviousMonth.toString()));
        toDate.setText(date1);
        toDateval = date1;
        datelengthArr.clear();
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
                                fromDateval = fromDate.getText().toString();
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show();

            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateFrom.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select From Date!!!", Toast.LENGTH_SHORT).show();
                } else {
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
        Calendar aCalendar1 = Calendar.getInstance();

        aCalendar1.add(Calendar.MONTH, -2);

        aCalendar1.set(Calendar.DATE, 1);

        final Date firstDateOfPreviousMonth1 = aCalendar1.getTime();


        aCalendar1.set(Calendar.DATE,
                aCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date lastDateOfPreviousMonth1 = aCalendar1.getTime();
        SimpleDateFormat format2 = new SimpleDateFormat("dd/M/yyyy");
        final String date2 = format2.format(Date.parse(firstDateOfPreviousMonth1.toString()));
        comparefromdate.setText(date2);
        comparefromdateval = date2;
        SimpleDateFormat format3 = new SimpleDateFormat("dd/M/yyyy");
        final String date3 = format3.format(Date.parse(lastDateOfPreviousMonth1.toString()));
        comparetodate.setText(date3);
        comparetodateval = date3;
        comparefromdate.setOnClickListener(new View.OnClickListener() {
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

                                comparefromdate.setText("" + dayOfMonth + "/" + monthOfYear1 + "/" + year);
                                comparefromdateval = comparefromdate.getText().toString();
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show();

            }
        });

        comparetodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comparefromdateval.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Select Compare From Date!!!", Toast.LENGTH_SHORT).show();
                } else {
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

                                    comparetodate.setText("" + dayOfMonth + "/" + monthOfYear1 + "/" + year);
                                    comparetodateval = comparetodate.getText().toString();
                                }
                            }, mYear, mMonth, mDay);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                    Date date = null;
                    try {
                        date = sdf.parse(comparefromdateval);
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

        dateBranch.add("Select Branch");
        dateBranchId.add("0");
        adapterBranch = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateBranch);
        adapterBranch.setDropDownViewResource(R.layout.spiner_item);
        spinnerBranch.setAdapter(adapterBranch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    stringBranchSpiner = dateBranchId.get(position);
                } else {
                    stringBranchSpiner = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        /*ArrayAdapter<String> adapterselectlength = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, datelengthArr);
        adapterselectlength.setDropDownViewResource(R.layout.spiner_item);
        length.setAdapter(adapterselectlength);
        length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    lengthValue = Integer.parseInt(datelengthArr.get(position));
                    getMemberList(member_id,lengthValue,page,token,fromDateval,toDateval,comparefromdateval,comparetodateval);
                }else{
                    lengthValue =10;
                    getMemberList(member_id,10,1,token,"","","","");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        imageViewSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSerach == false) {
                    imageViewSerach.setImageResource(R.drawable.ic_baseline_close_24);
                    constraintLayoutOptions.setVisibility(View.VISIBLE);
                    isSerach = true;
                } else {
                    imageViewSerach.setImageResource(R.drawable.magnifyingglass);
                    constraintLayoutOptions.setVisibility(View.GONE);
                    isSerach = false;
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
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
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
                listAdapter.notifyDataSetChanged();
                page = page + 1;
                // lengthValue=lengthValue * page;
                getMemberList(member_id, lengthValue, page, token, fromDateval, toDateval, comparefromdateval, comparetodateval);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page = page - 1;
                    // lengthValue=lengthValue * page;
                    getMemberList(member_id, lengthValue, page, token, fromDateval, toDateval, comparefromdateval, comparetodateval);
                }
            }
        });
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        getBranch(member_id, token);
        //getAssociateActiveList(member_id,token);
        // getMemberList(member_id,10,1,token,"","","","");
        //  getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
        getMemberList(member_id, lengthValue, page, token, fromDateval, toDateval, comparefromdateval, comparetodateval);

        listAdapter = new AssociateCollectionCompareAdapter(getActivity(), parent_models, "1");
        expListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                getMemberList(member_id, lengthValue, page, token, fromDateval, toDateval, comparefromdateval, comparetodateval);
            }
        });
        editTextTextPersonName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getMemberCheck(member_id, token, editTextTextPersonName.getText().toString());
                    return true;
                }
                return false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDate.setText(date);
                fromDateval = date;
                toDate.setText(date1);
                toDateval = date1;
                fromDate.setHint("From Date");
                toDate.setHint("To Date");
                comparefromdate.setText(date2);
                comparefromdateval = date2;
                comparetodate.setText(date3);
                comparetodateval = date3;
                spinnerBranch.setSelection(0);
                stringBranchSpiner = "";
                getMemberList(member_id, 15, 1, token, "", "", "", "");

            }
        });
        return RootView;
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    public void getMemberCheck(final String assciate_no, String token, String memberId) {
        googleProgressDialog.show1("Loading...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), memberId);
        Call<AssociateMemberCheckResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Check_RESPONES_CALL(_assciate_no, _member_id, _token);
        applicationsListResponesCall.enqueue(new Callback<AssociateMemberCheckResponse>() {
            @Override
            public void onResponse(Call<AssociateMemberCheckResponse> call, Response<AssociateMemberCheckResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
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


    private void getBranch(String member_id, String token) {
        try {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RestHandler.getApiService().BRANCH_LIST_RESPONSE_CALL(_member_id, _token).enqueue(new Callback<BranchListResponse>() {
                @Override
                public void onResponse(Call<BranchListResponse> call, Response<BranchListResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getCode() == 200) {
                            ResultBranchList result = response.body().getResult();
                            List<Branch> branchList = result.getBranch();
                            if (branchList.size() > 0) {
                                for (Branch branch : branchList) {
                                    dateBranch.add(branch.getName());
                                    dateBranchId.add(String.valueOf(branch.getId()));
                                }
                                adapterBranch.notifyDataSetChanged();
                            } else {

                            }

                        } else {
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Branch Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BranchListResponse> call, Throwable t) {

                }
            });
        } catch (Exception ex) {

        }
    }

//    public  void getAssociateActiveList(final String assciate_no,String token){
//        googleProgressDialog.show1("Loading...");
//        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
//        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
//        Call<AssociateMemberListResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Associate_active_List_RESPONES_CALL(_assciate_no,_token);
//        applicationsListResponesCall.enqueue(new Callback<AssociateMemberListResponse>() {
//            @Override
//            public void onResponse(Call<AssociateMemberListResponse> call, Response<AssociateMemberListResponse> response) {
//                googleProgressDialog.dismiss();
//                if (response != null) {
//                    if (response.body().getCode() == 200) {
                         /* if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }*/
//                       // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                        associateList = response.body().getResult().getAssociateList();
//                        int i=1;
//                        for (Associate memberItem : associateList) {
//
//                            dateStrings.add(memberItem.getName());
//                        }
//
//                    } else {
//                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AssociateMemberListResponse> call, Throwable t) {
//                googleProgressDialog.dismiss();
//                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
//            }
//        });
//}


    public void getMemberList(final String member_id, int length, int page, String token, String fromDate, String toDate, String comparefromDate, String comparetoDate) {
        googleProgressDialog.show1("Loading...");
        parent_models.clear();
        //listAdapter.notifyDataSetChanged();
        if (page <= 0) {
            page = 1;
        }
        if (length <= 0) {
            length = 1;
        }
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
        RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
        RequestBody _comparefromDate = RequestBody.create(MediaType.parse("multipart/form-data"), comparefromDate);
        RequestBody _comparetoDate = RequestBody.create(MediaType.parse("multipart/form-data"), comparetoDate);
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), stringBranchSpiner);
        Call<AssociateCollectionCompareRespones> applicationsListResponesCall = RestHandler.getApiService().BUSINESS_COMPARE_REPORT_RESPONSE_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _comparefromDate, _comparetoDate, _branch_id);
        applicationsListResponesCall.enqueue(new Callback<AssociateCollectionCompareRespones>() {
            @Override
            public void onResponse(Call<AssociateCollectionCompareRespones> call, Response<AssociateCollectionCompareRespones> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<BusinessSummaryReport> member = response.body().getResult().getBusinessSummaryReport();
                        if (member.size() == 0) {
                            datanotfound.setVisibility(View.VISIBLE);
                        } else {
                            datanotfound.setVisibility(View.GONE);
                        }
                        next.setEnabled(true);
                        int i = 1;
                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt) {
                            next.setEnabled(member.size() == 15 ? true : false);
                        } else {
                            next.setEnabled(false);
                        }

                        for (BusinessSummaryReport memberItem : member) {
                            ArrayList<AssociateCollectionCompareChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new AssociateCollectionCompareChildModel(
                                    memberItem.getName(),
                                    memberItem.getCurrentDailyDenoSum(),
                                    memberItem.getCurrentMonthlyDenoSum(),
                                    memberItem.getCurrentFdDenoSum(),
                                    memberItem.getCurrentAllCollection(),
                                    memberItem.getCurrentTcc(),
                                    memberItem.getCurrentNcc(),
                                    memberItem.getCurrentLoanRecoveryAmount(),
                                    memberItem.getCompareDailyDenoSum(),
                                    memberItem.getCompareMonthlyDenoSum(),
                                    memberItem.getCompareFdDenoSum(),
                                    memberItem.getCompareAllCollection(),
                                    memberItem.getCompareTcc(),
                                    memberItem.getCompareLoanRecoveryAmount(),
                                    memberItem.getResultDailyDenoSum(),
                                    memberItem.getCompareNcc(),
                                    memberItem.getResultMonthlyDenoSum(),
                                    memberItem.getResultFdDenoSum(),
                                    memberItem.getResultTcc(),
                                    memberItem.getResultLoanRecoveryAmount(),
                                    memberItem.getResultCollectionAll(),
                                    memberItem.getResultNcc()
                            ));
                            parent_models.add(new AssociateCollectionCompareParentModel(String.valueOf(i), memberItem.getName(), memberItem.getAssociate_code(), "", child_models));
                            i++;
                        }
                        listAdapter.notifyDataSetChanged();
                    } else {
                        datanotfound.setVisibility(View.VISIBLE);
                        listAdapter.notifyDataSetChanged();
                        next.setEnabled(false);
                        googleProgressDialog.dismiss();
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    datanotfound.setVisibility(View.VISIBLE);
                    listAdapter.notifyDataSetChanged();
                    next.setEnabled(false);
                    googleProgressDialog.dismiss();
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateCollectionCompareRespones> call, Throwable t) {
                googleProgressDialog.dismiss();
                datanotfound.setVisibility(View.VISIBLE);
                listAdapter.notifyDataSetChanged();
                next.setEnabled(false);
                // Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void getMemberList1(final String member_id, int length, int page, String token, String fromDate, String toDate, String comparefromDate, String comparetoDate) {
        googleProgressDialog.show1("Loading...");
        parent_models1.clear();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(1));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
        RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
        RequestBody _comparefromDate = RequestBody.create(MediaType.parse("multipart/form-data"), comparefromDate);
        RequestBody _comparetoDate = RequestBody.create(MediaType.parse("multipart/form-data"), comparetoDate);
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), stringBranchSpiner);
        Call<AssociateCollectionCompareRespones> applicationsListResponesCall = RestHandler.getApiService().BUSINESS_COMPARE_REPORT_RESPONSE_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _comparefromDate, _comparetoDate, _branch_id);
        applicationsListResponesCall.enqueue(new Callback<AssociateCollectionCompareRespones>() {
            @Override
            public void onResponse(Call<AssociateCollectionCompareRespones> call, Response<AssociateCollectionCompareRespones> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<BusinessSummaryReport> member = response.body().getResult().getBusinessSummaryReport();
                        int ii = 1;
                        for (BusinessSummaryReport memberItem : member) {
                            ArrayList<AssociateCollectionCompareChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new AssociateCollectionCompareChildModel(
                                    memberItem.getName(),
                                    memberItem.getCurrentDailyDenoSum(),
                                    memberItem.getCurrentMonthlyDenoSum(),
                                    memberItem.getCurrentFdDenoSum(),
                                    memberItem.getCurrentAllCollection(),
                                    memberItem.getCurrentTcc(),
                                    memberItem.getCurrentNcc(),
                                    memberItem.getCurrentLoanRecoveryAmount(),
                                    memberItem.getCompareDailyDenoSum(),
                                    memberItem.getCompareMonthlyDenoSum(),
                                    memberItem.getCompareFdDenoSum(),
                                    memberItem.getCompareAllCollection(),
                                    memberItem.getCompareTcc(),
                                    memberItem.getCompareLoanRecoveryAmount(),
                                    memberItem.getResultDailyDenoSum(),
                                    memberItem.getCompareNcc(),
                                    memberItem.getResultMonthlyDenoSum(),
                                    memberItem.getResultFdDenoSum(),
                                    memberItem.getResultTcc(),
                                    memberItem.getResultLoanRecoveryAmount(),
                                    memberItem.getResultCollectionAll(),
                                    memberItem.getResultNcc()
                            ));
                            parent_models1.add(new AssociateCollectionCompareParentModel(String.valueOf(ii), memberItem.getName(), memberItem.getAssociate_code(), "", child_models));
                            ii++;
                        }


                        String Fnamexls = "excelSheet" + System.currentTimeMillis() + ".xls";
                        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File directory = new File(sdCard.getAbsolutePath() + "/SBMFA");
                        if (directory.isDirectory()) {
                            Log.e("isDirectory", "" + directory.isDirectory());
                        } else {
                            Log.e("isDirectory", "" + directory.isDirectory());
                            directory.mkdirs();
                        }
                        final File file = new File(directory, Fnamexls);
                        WorkbookSettings wbSettings = new WorkbookSettings();
                        wbSettings.setLocale(new Locale("en", "EN"));
                        WritableWorkbook workbook;
                        try {
                            workbook = Workbook.createWorkbook(file, wbSettings);
                            //workbook.createSheet("Report", 0);
                            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                            for (int i = 0; i < parent_models1.size(); i++) {
                                AssociateCollectionCompareChildModel sub = parent_models1.get(i).getChild_models().get(0);
                                Label label0 = new Label(0, i + 1, parent_models1.get(i).getProgress());
                                Label label1 = new Label(1, i + 1, sub.getName());
                                Label label2 = new Label(2, i + 1, sub.getCurrentDailyDenoSum().toString());
                                Label label3 = new Label(3, i + 1, sub.getCurrentMonthlyDenoSum().toString());
                                Label label4 = new Label(4, i + 1, sub.getCurrentFdDenoSum().toString());
                                Label label5 = new Label(5, i + 1, sub.getCurrentAllCollection());
                                Label label6 = new Label(6, i + 1, sub.getCurrentTcc());
                                Label label7 = new Label(7, i + 1, sub.getCurrentNcc().toString());
                                Label label8 = new Label(8, i + 1, sub.getCurrentLoanRecoveryAmount());
                                Label label9 = new Label(9, i + 1, sub.getCompareDailyDenoSum().toString());
                                Label label10 = new Label(10, i + 1, sub.getCompareMonthlyDenoSum().toString());
                                Label label12 = new Label(11, i + 1, sub.getCompareFdDenoSum().toString());
                                Label label13 = new Label(12, i + 1, sub.getCompareAllCollection());
                                Label label14 = new Label(13, i + 1, sub.getCompareTcc());
                                Label label15 = new Label(14, i + 1, sub.getCompareNcc().toString());
                                Label label16 = new Label(15, i + 1, sub.getCompareLoanRecoveryAmount().toString());
                                Label label17 = new Label(16, i + 1, sub.getResultDailyDenoSum().toString());
                                Label label18 = new Label(17, i + 1, sub.getResultMonthlyDenoSum().toString());
                                Label label19 = new Label(18, i + 1, sub.getResultFdDenoSum().toString());
                                Label label20 = new Label(19, i + 1, sub.getResultCollectionAll().toString());
                                Label label21 = new Label(20, i + 1, sub.getResultTcc().toString());
                                Label label22 = new Label(21, i + 1, sub.getResultNcc().toString());
                                Label label23 = new Label(22, i + 1, sub.getResultLoanRecoveryAmount().toString());
//                                Label label23 = new Label(23, i + 1, sub.getResultNcc().toString());


                                Label label201 = new Label(0, 0, "Associate Code");
                                Label label202 = new Label(1, 0, "Name");
                                Label label203 = new Label(2, 0, "Current Daily");
                                Label label204 = new Label(3, 0, "Current Monthly");
                                Label label205 = new Label(4, 0, "Current Fd");
                                Label label206 = new Label(5, 0, "Current Collection");
                                Label label207 = new Label(6, 0, "Current Tcc");
                                Label label208 = new Label(7, 0, "Current Ncc");
                                Label label209 = new Label(8, 0, "Current Loan Rec. Amt.");
                                Label label210 = new Label(9, 0, "Compare Daily");
                                Label label211 = new Label(10, 0, "Compare Monthly");
                                Label label212 = new Label(11, 0, "Compare Fd");
                                Label label213 = new Label(12, 0, "Compare Collection");
                                Label label214 = new Label(13, 0, "Compare Tcc");
                                Label label215 = new Label(14, 0, "Compare Ncc");
                                Label label216 = new Label(15, 0, "Compare Loan Rec. Amt.");
                                Label label217 = new Label(16, 0, "Result Daily");
                                Label label218 = new Label(17, 0, "Result Monthly");
                                Label label219 = new Label(18, 0, "Result Fd");
                                Label label220 = new Label(19, 0, "Result Collection");
                                Label label221 = new Label(20, 0, "Result Tcc");
                                Label label222 = new Label(21, 0, "Result Ncc");
                                Label label223 = new Label(22, 0, "Result Loan Rec. Amt.");

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
                                    sheet.addCell(label12);
                                    sheet.addCell(label13);
                                    sheet.addCell(label14);
                                    sheet.addCell(label15);
                                    sheet.addCell(label16);
                                    sheet.addCell(label17);
                                    sheet.addCell(label18);
                                    sheet.addCell(label19);
                                    sheet.addCell(label20);
                                    sheet.addCell(label21);
                                    sheet.addCell(label22);
                                    sheet.addCell(label23);


                                    sheet.addCell(label201);
                                    sheet.addCell(label202);
                                    sheet.addCell(label203);
                                    sheet.addCell(label204);
                                    sheet.addCell(label205);
                                    sheet.addCell(label206);
                                    sheet.addCell(label207);
                                    sheet.addCell(label208);
                                    sheet.addCell(label209);
                                    sheet.addCell(label210);
                                    sheet.addCell(label211);
                                    sheet.addCell(label212);
                                    sheet.addCell(label213);
                                    sheet.addCell(label214);
                                    sheet.addCell(label215);
                                    sheet.addCell(label216);
                                    sheet.addCell(label217);
                                    sheet.addCell(label218);
                                    sheet.addCell(label219);
                                    sheet.addCell(label220);
                                    sheet.addCell(label221);
                                    sheet.addCell(label222);
                                    sheet.addCell(label223);
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                            }


                            workbook.write();
                            workbook.close();

                            //createExcel(excelSheet);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
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

//                                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                                        Uri uri = Uri.parse(sdCard.getAbsolutePath() + "/SBMFA");
//                                        intent.setDataAndType(uri, "*/*");
//                                        getActivity().startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    } else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    googleProgressDialog.dismiss();
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateCollectionCompareRespones> call, Throwable t) {
                googleProgressDialog.dismiss();
                // Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onEvent_View(String id, String memberid, String type) {
        Fragment fragment = new MemberDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void createExcelSheet() {
        getMemberList1(member_id, 15, 1, token, fromDateval, toDateval, comparefromdateval, comparetodateval);
    }
}
