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

import com.associate.sbmfa.Adapter.MemberMangementAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateCommissionDetailsAdapter;
import com.associate.sbmfa.Fragment.LoanManagment.Model.Lone_List_Child_model;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.MemberMangement.MemberDetailsFragment;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberCheckResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.Member;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateBusinessCompareReportAdapter;

import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareChildModel;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessCompareReport.AssociateBusinessCompareParentModel;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Aassociatebusinesscomparereporp.AssociateBusinessCompareReportBusinessSummaryReport;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Aassociatebusinesscomparereporp.AssociateBusinessCompareReportResponse;
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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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


public class AssociateBusinessCompareReportFragment extends Fragment implements AssociateCommissionDetailsAdapter.EventListener {
    View RootView;
    AssociateBusinessCompareReportAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateBusinessCompareParentModel> parent_models = new ArrayList<>();
    List<Associate> associateList = new ArrayList<>();
    Spinner length;
    ArrayList<String> dateStrings = new ArrayList<>();
    ArrayList<String> datelengthArr = new ArrayList<>();
    ImageView imageViewSerach;
    TextView fromDate, toDate, datanotfound, comparefromdate, comparetodate;
    private int mYear, mMonth, mDay;
    String dateFrom = "", dateto, comparetodateval = "", comparefromdateval = "";
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView pdfdwon, imageViewBack;
    Spinner spinnerBranch;

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
    ArrayAdapter<String> adapterBranch;
    ArrayList<String> dateBranch = new ArrayList<>();
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
        apply = RootView.findViewById(R.id.apply);
        datanotfound = RootView.findViewById(R.id.not_found);
        reset = RootView.findViewById(R.id.reset);
        editTextTextPersonName = RootView.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        title = RootView.findViewById(R.id.textView2);
        title.setText("Associate Business Compare Report");
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

        final Date lastDateOfPreviousMonth = aCalendar.getTime();
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

        listAdapter = new AssociateBusinessCompareReportAdapter(getActivity(), parent_models);
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
                fromDate.setHint("From Date");
                toDate.setHint("To Date");
                fromDate.setText(date);
                fromDateval = date;
                toDate.setText(date1);
                toDateval = date1;
                spinnerBranch.setSelection(0);
                stringBranchSpiner = "";
                comparefromdate.setText(date2);
                comparefromdateval = date2;
                comparetodate.setText(date3);
                comparetodateval = date3;
                getMemberList(member_id, 15, 1, token, fromDateval, toDateval, comparefromdateval, comparetodateval);

            }
        });
        return RootView;
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
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
        Call<AssociateBusinessCompareReportResponse> applicationsListResponesCall = RestHandler.getApiService().AssociateBusinessCompareReportResponse_RESPONES_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _comparefromDate, _comparetoDate, _branch_id);
        applicationsListResponesCall.enqueue(new Callback<AssociateBusinessCompareReportResponse>() {
            @Override
            public void onResponse(Call<AssociateBusinessCompareReportResponse> call, Response<AssociateBusinessCompareReportResponse> response) {
                googleProgressDialog.dismiss();
                if (response.body() != null) {

                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<AssociateBusinessCompareReportBusinessSummaryReport> member = response.body().getResult().getBusinessSummaryReport();
                        if (member.size() == 0) {
                            datanotfound.setVisibility(View.VISIBLE);
                        } else {
                            datanotfound.setVisibility(View.GONE);
                        }
                        int i = 1;
                        next.setEnabled(true);

                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt) {
                            next.setEnabled(member.size() == 15 ? true : false);
                        } else {
                            next.setEnabled(false);
                        }
                        for (AssociateBusinessCompareReportBusinessSummaryReport memberItem : member) {
                            ArrayList<AssociateBusinessCompareChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new AssociateBusinessCompareChildModel(
                                    memberItem.getJoinDate().toString(),
                                    memberItem.getBranch(),
                                    memberItem.getBranchCode().toString(),
                                    memberItem.getSectorName(),
                                    memberItem.getRegionName(),
                                    memberItem.getZoneName(),
                                    memberItem.getMemberId(),
                                    memberItem.getName(),
                                    memberItem.getCadre(),

                                    memberItem.getCurrentDailyNewAc().toString(),
                                    memberItem.getCurrentDailyDenoSum().toString(),
                                    memberItem.getCurrentDailyRenewAc().toString(),
                                    memberItem.getCurrentDailyRenew().toString(),
                                    memberItem.getCurrentMonthlyNewAc().toString(),
                                    memberItem.getCurrentMonthlyDenoSum().toString(),
                                    memberItem.getCurrentMonthlyRenewAc().toString(),
                                    memberItem.getCurrentMonthlyRenew().toString(),
                                    memberItem.getCurrentFdNewAc().toString(),
                                    memberItem.getCurrentFdDenoSum().toString(),
                                    memberItem.getCurrentSsbNewAc().toString(),
                                    memberItem.getCurrentSsbDenoSum().toString(),
                                    memberItem.getCurrentSsbRenewAc().toString(),
                                    memberItem.getCurrentSsbRenew().toString(),
                                    memberItem.getCurrentTotalNiAc().toString(),
                                    memberItem.getCurrentTotalNiAmount().toString(),
                                    memberItem.getCurrentTotalAc().toString(),
                                    memberItem.getCurrentTotalAmount().toString(),
                                    memberItem.getCurrentOtherMt().toString(),
                                    memberItem.getCurrentOtherStn(),
                                    memberItem.getCurrentNiM(),
                                    memberItem.getCurrentNi(),
                                    memberItem.getCurrentTccM(),
                                    memberItem.getCurrentTcc(),
                                    memberItem.getCurrentLoanAc().toString(),
                                    memberItem.getCurrentLoanAmount().toString(),
                                    memberItem.getCurrentLoanRecoveryAc().toString(),
                                    memberItem.getCurrentLoanRecoveryAmount().toString(),
                                    memberItem.getCurrentNewAssociate().toString(),
                                    memberItem.getCurrentTotalAssociate().toString(),
                                    memberItem.getCurrentNewMember().toString(),
                                    memberItem.getCurrentTotalMember().toString(),
                                    memberItem.getCompareDailyNewAc().toString(),
                                    memberItem.getCompareDailyDenoSum().toString(),
                                    memberItem.getCompareDailyRenewAc().toString(),
                                    memberItem.getCompareDailyRenew().toString(),
                                    memberItem.getCompareMonthlyNewAc().toString(),
                                    memberItem.getCompareMonthlyDenoSum().toString(),
                                    memberItem.getCompareMonthlyRenewAc().toString(),
                                    memberItem.getCompareMonthlyRenew().toString(),
                                    memberItem.getCompareFdNewAc().toString(),
                                    memberItem.getCompareFdDenoSum().toString(),
                                    memberItem.getCompareSsbNewAc().toString(),
                                    memberItem.getCompareSsbDenoSum().toString(),
                                    memberItem.getCompareSsbRenewAc().toString(),
                                    memberItem.getCompareSsbRenew().toString(),
                                    memberItem.getCompareTotalNiAc().toString(),
                                    memberItem.getCompareTotalNiAmount().toString(),
                                    memberItem.getCompareTotalAc().toString(),
                                    memberItem.getCompareTotalAmount(),
                                    memberItem.getCompareOtherMt(),
                                    memberItem.getCompareOtherStn(),
                                    memberItem.getCompareNiM(),
                                    memberItem.getCompareNi(),
                                    memberItem.getCompareTccM(),
                                    memberItem.getCompareTcc(),
                                    memberItem.getCompareLoanAc().toString(),
                                    memberItem.getCompareLoanAmount(),
                                    memberItem.getCompareLoanRecoveryAc().toString(),
                                    memberItem.getCompareLoanRecoveryAmount(),
                                    memberItem.getCompareNewAssociate().toString(),
                                    memberItem.getCompareTotalAssociate().toString(),
                                    memberItem.getCompareNewMember().toString(),
                                    memberItem.getCompareTotalMember().toString(),
                                    memberItem.getResultDailyNewAc().toString(),
                                    memberItem.getResultDailyDenoSum().toString(),
                                    memberItem.getResultDailyRenewAc().toString(),
                                    memberItem.getResultDailyRenew().toString(),
                                    memberItem.getResultMonthlyNewAc().toString(),
                                    memberItem.getResultMonthlyDenoSum().toString(),
                                    memberItem.getResultMonthlyRenewAc().toString(),
                                    memberItem.getResultMonthlyRenew().toString(),
                                    memberItem.getResultFdNewAc().toString(),
                                    memberItem.getResultFdDenoSum().toString(),
                                    memberItem.getResultSsbNewAc().toString(),
                                    memberItem.getResultSsbDenoSum().toString(),
                                    memberItem.getResultSsbRenewAc().toString(),
                                    memberItem.getResultSsbRenew().toString(),
                                    memberItem.getResultTotalNiAc().toString(),
                                    memberItem.getResultTotalNiAmount(),
                                    memberItem.getResultTotalAc().toString(),
                                    memberItem.getResultTotalAmount(),
                                    memberItem.getResultOtherMt().toString(),
                                    memberItem.getResultOtherStn().toString(),
                                    memberItem.getResultNiM().toString(),
                                    memberItem.getResultNi().toString(),
                                    memberItem.getResultTccM().toString(),
                                    memberItem.getResultTcc().toString(),
                                    memberItem.getResultLoanAc().toString(),
                                    memberItem.getResultLoanAmount().toString(),
                                    memberItem.getResultLoanRecoveryAc().toString(),
                                    memberItem.getResultLoanRecoveryAmount().toString(),
                                    memberItem.getResultNewAssociate().toString(),
                                    memberItem.getResultTotalAssociate().toString(),
                                    memberItem.getResultNewMember().toString(),
                                    memberItem.getResultTotalMember().toString()
                            ));
                            parent_models.add(new AssociateBusinessCompareParentModel(String.valueOf(i), memberItem.getName(), memberItem.getMemberId(), "", child_models));
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
            public void onFailure(Call<AssociateBusinessCompareReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                datanotfound.setVisibility(View.VISIBLE);
                listAdapter.notifyDataSetChanged();
                next.setEnabled(false);
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
        googleProgressDialog.show1("Export Data...");
        final ArrayList<AssociateBusinessCompareParentModel> parentmodels = new ArrayList<>();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fromDateval));
        RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(toDateval));
        RequestBody _comparefromDate = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(comparefromdateval));
        RequestBody _comparetoDate = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(comparetodateval));
        RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), stringBranchSpiner);
        Call<AssociateBusinessCompareReportResponse> applicationsListResponesCall = RestHandler.getApiService().AssociateBusinessCompareReportResponse_RESPONES_CALL(_assciate_no, _page, _length, _token, _fromDate, _toDate, _comparefromDate, _comparetoDate, _branch_id);
        applicationsListResponesCall.enqueue(new Callback<AssociateBusinessCompareReportResponse>() {

            @Override
            public void onResponse(Call<AssociateBusinessCompareReportResponse> call, Response<AssociateBusinessCompareReportResponse> response) {
                googleProgressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getCode() == 200) {
                        int i = 1;
                        List<AssociateBusinessCompareReportBusinessSummaryReport> member = response.body().getResult().getBusinessSummaryReport();
                        for (AssociateBusinessCompareReportBusinessSummaryReport memberItem : member) {
                            ArrayList<AssociateBusinessCompareChildModel> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new AssociateBusinessCompareChildModel(
                                    memberItem.getJoinDate().toString(),
                                    memberItem.getBranch(),
                                    memberItem.getBranchCode().toString(),
                                    memberItem.getSectorName(),
                                    memberItem.getRegionName(),
                                    memberItem.getZoneName(),
                                    memberItem.getMemberId(),
                                    memberItem.getName(),
                                    memberItem.getCadre(),

                                    memberItem.getCurrentDailyNewAc().toString(),
                                    memberItem.getCurrentDailyDenoSum().toString(),
                                    memberItem.getCurrentDailyRenewAc().toString(),
                                    memberItem.getCurrentDailyRenew().toString(),
                                    memberItem.getCurrentMonthlyNewAc().toString(),
                                    memberItem.getCurrentMonthlyDenoSum().toString(),
                                    memberItem.getCurrentMonthlyRenewAc().toString(),
                                    memberItem.getCurrentMonthlyRenew().toString(),
                                    memberItem.getCurrentFdNewAc().toString(),
                                    memberItem.getCurrentFdDenoSum().toString(),
                                    memberItem.getCurrentSsbNewAc().toString(),
                                    memberItem.getCurrentSsbDenoSum().toString(),
                                    memberItem.getCurrentSsbRenewAc().toString(),
                                    memberItem.getCurrentSsbRenew().toString(),
                                    memberItem.getCurrentTotalNiAc().toString(),
                                    memberItem.getCurrentTotalNiAmount().toString(),
                                    memberItem.getCurrentTotalAc().toString(),
                                    memberItem.getCurrentTotalAmount().toString(),
                                    memberItem.getCurrentOtherMt().toString(),
                                    memberItem.getCurrentOtherStn(),
                                    memberItem.getCurrentNiM(),
                                    memberItem.getCurrentNi(),
                                    memberItem.getCurrentTccM(),
                                    memberItem.getCurrentTcc(),
                                    memberItem.getCurrentLoanAc().toString(),
                                    memberItem.getCurrentLoanAmount().toString(),
                                    memberItem.getCurrentLoanRecoveryAc().toString(),
                                    memberItem.getCurrentLoanRecoveryAmount().toString(),
                                    memberItem.getCurrentNewAssociate().toString(),
                                    memberItem.getCurrentTotalAssociate().toString(),
                                    memberItem.getCurrentNewMember().toString(),
                                    memberItem.getCurrentTotalMember().toString(),
                                    memberItem.getCompareDailyNewAc().toString(),
                                    memberItem.getCompareDailyDenoSum().toString(),
                                    memberItem.getCompareDailyRenewAc().toString(),
                                    memberItem.getCompareDailyRenew().toString(),
                                    memberItem.getCompareMonthlyNewAc().toString(),
                                    memberItem.getCompareMonthlyDenoSum().toString(),
                                    memberItem.getCompareMonthlyRenewAc().toString(),
                                    memberItem.getCompareMonthlyRenew().toString(),
                                    memberItem.getCompareFdNewAc().toString(),
                                    memberItem.getCompareFdDenoSum().toString(),
                                    memberItem.getCompareSsbNewAc().toString(),
                                    memberItem.getCompareSsbDenoSum().toString(),
                                    memberItem.getCompareSsbRenewAc().toString(),
                                    memberItem.getCompareSsbRenew().toString(),
                                    memberItem.getCompareTotalNiAc().toString(),
                                    memberItem.getCompareTotalNiAmount().toString(),
                                    memberItem.getCompareTotalAc().toString(),
                                    memberItem.getCompareTotalAmount(),
                                    memberItem.getCompareOtherMt(),
                                    memberItem.getCompareOtherStn(),
                                    memberItem.getCompareNiM(),
                                    memberItem.getCompareNi(),
                                    memberItem.getCompareTccM(),
                                    memberItem.getCompareTcc(),
                                    memberItem.getCompareLoanAc().toString(),
                                    memberItem.getCompareLoanAmount(),
                                    memberItem.getCompareLoanRecoveryAc().toString(),
                                    memberItem.getCompareLoanRecoveryAmount(),
                                    memberItem.getCompareNewAssociate().toString(),
                                    memberItem.getCompareTotalAssociate().toString(),
                                    memberItem.getCompareNewMember().toString(),
                                    memberItem.getCompareTotalMember().toString(),
                                    memberItem.getResultDailyNewAc().toString(),
                                    memberItem.getResultDailyDenoSum().toString(),
                                    memberItem.getResultDailyRenewAc().toString(),
                                    memberItem.getResultDailyRenew().toString(),
                                    memberItem.getResultMonthlyNewAc().toString(),
                                    memberItem.getResultMonthlyDenoSum().toString(),
                                    memberItem.getResultMonthlyRenewAc().toString(),
                                    memberItem.getResultMonthlyRenew().toString(),
                                    memberItem.getResultFdNewAc().toString(),
                                    memberItem.getResultFdDenoSum().toString(),
                                    memberItem.getResultSsbNewAc().toString(),
                                    memberItem.getResultSsbDenoSum().toString(),
                                    memberItem.getResultSsbRenewAc().toString(),
                                    memberItem.getResultSsbRenew().toString(),
                                    memberItem.getResultTotalNiAc().toString(),
                                    memberItem.getResultTotalNiAmount(),
                                    memberItem.getResultTotalAc().toString(),
                                    memberItem.getResultTotalAmount(),
                                    memberItem.getResultOtherMt().toString(),
                                    memberItem.getResultOtherStn().toString(),
                                    memberItem.getResultNiM().toString(),
                                    memberItem.getResultNi().toString(),
                                    memberItem.getResultTccM().toString(),
                                    memberItem.getResultTcc().toString(),
                                    memberItem.getResultLoanAc().toString(),
                                    memberItem.getResultLoanAmount().toString(),
                                    memberItem.getResultLoanRecoveryAc().toString(),
                                    memberItem.getResultLoanRecoveryAmount().toString(),
                                    memberItem.getResultNewAssociate().toString(),
                                    memberItem.getResultTotalAssociate().toString(),
                                    memberItem.getResultNewMember().toString(),
                                    memberItem.getResultTotalMember().toString()
                            ));
                            parentmodels.add(new AssociateBusinessCompareParentModel(String.valueOf(i), memberItem.getName(), memberItem.getMemberId(), "", child_models));
                            i++;
                        }
                        showExcelOpenDialog(parentmodels);
                    }
                }
            }

            @Override
            public void onFailure(Call<AssociateBusinessCompareReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });
    }

    private void showExcelOpenDialog(ArrayList<AssociateBusinessCompareParentModel> parentmodels) {
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
            for (int i = 0; i < parentmodels.size(); i++) {
                AssociateBusinessCompareChildModel sub = parentmodels.get(i).getChild_models().get(0);
                Label label0 = new Label(0, i + 1, sub.getJoinDate());
                Label label1 = new Label(1, i + 1, sub.getBranch());
                Label label2 = new Label(2, i + 1, sub.getBranchCode());
                Label label3 = new Label(3, i + 1, sub.getSectorName());
                Label label4 = new Label(4, i + 1, sub.getRegionName());
                Label label5 = new Label(5, i + 1, sub.getZoneName());
                Label label6 = new Label(6, i + 1, sub.getMemberId());
                Label label7 = new Label(7, i + 1, sub.getName());
                Label label8 = new Label(8, i + 1, sub.getCadre());

//                Label label221 = new Label(10, 0, "Current Daily");
//                Label label222 = new Label(12, 0, "Current Daily Collection");
//                Label label223 = new Label(14, 0, "Current Monthly");
//                Label label224 = new Label(15, 0, "Current Monthly Collection");
//                Label label225 = new Label(17, 0, "Current Fd");
//                Label label226 = new Label(19, 0, "Current Ssb");
//                Label label227 = new Label(21, 0, "Current Ssb Deposit");
//                Label label228 = new Label(28, 0, "Current NCC_M");
//                Label label229 = new Label(29, 0, "Current NCC");
//                Label label230 = new Label(30, 0, "Current Tcc_M");
//                Label label231 = new Label(31, 0, "Current Tcc");
//                Label label232 = new Label(33, 0, "Current Loan Amount");
//                Label label233 = new Label(35, 0, "Current Loan Recovery");
//                Label label234 = new Label(37, 0, "Current Total Associate");


                Label label10 = new Label(9, i + 1, sub.getCurrentDailyDenoSum());
                Label label11 = new Label(10, i + 1, sub.getCurrentDailyRenew());
                Label label12 = new Label(11, i + 1, sub.getCurrentMonthlyDenoSum());
                Label label13 = new Label(12, i + 1, sub.getCurrentMonthlyRenew());
                Label label14 = new Label(13, i + 1, sub.getCurrentFdDenoSum());
                Label label15 = new Label(14, i + 1, sub.getCurrentSsbDenoSum());
                Label label16 = new Label(15, i + 1, sub.getCurrentSsbRenew());
                Label label17 = new Label(16, i + 1, sub.getCurrentNiM());
                Label label18 = new Label(17, i + 1, sub.getCurrentNi());
                Label label19 = new Label(18, i + 1, sub.getCurrentTccM());
                Label label20 = new Label(19, i + 1, sub.getCurrentTcc());
                Label label21 = new Label(20, i + 1, sub.getCurrentLoanAmount());
                Label label22 = new Label(21, i + 1, sub.getCurrentLoanRecoveryAmount());
                Label label23 = new Label(22, i + 1, sub.getCurrentTotalAssociate());


                Label label24 = new Label(23, i + 1, sub.getCompareDailyDenoSum());
                Label label25 = new Label(24, i + 1, sub.getCompareDailyRenew());
                Label label26 = new Label(25, i + 1, sub.getCompareMonthlyDenoSum());
                Label label27 = new Label(26, i + 1, sub.getCompareMonthlyRenew());
                Label label28 = new Label(27, i + 1, sub.getCompareFdDenoSum());
                Label label29 = new Label(28, i + 1, sub.getCompareSsbDenoSum());
                Label label30 = new Label(29, i + 1, sub.getCompareSsbRenew());
                Label label31 = new Label(30, i + 1, sub.getCompareNiM());
                Label label32 = new Label(31, i + 1, sub.getCompareNi());
                Label label33 = new Label(32, i + 1, sub.getCompareTccM());
                Label label34 = new Label(33, i + 1, sub.getCompareTcc());
                Label label35 = new Label(34, i + 1, sub.getCompareLoanAmount());
                Label label36 = new Label(35, i + 1, sub.getCompareLoanRecoveryAmount());
                Label label37 = new Label(36, i + 1, sub.getCompareTotalAssociate());

                Label label38 = new Label(37, i + 1, sub.getResultDailyDenoSum());
                Label label39 = new Label(38, i + 1, sub.getResultDailyRenew());
                Label label40 = new Label(39, i + 1, sub.getResultMonthlyDenoSum());
                Label label41 = new Label(40, i + 1, sub.getResultMonthlyRenew());
                Label label42 = new Label(41, i + 1, sub.getResultFdDenoSum());
                Label label43 = new Label(42, i + 1, sub.getResultSsbDenoSum());
                Label label44 = new Label(43, i + 1, sub.getResultSsbRenew());
                Label label45 = new Label(44, i + 1, sub.getResultNiM());
                Label label46 = new Label(45, i + 1, sub.getResultNi());
                Label label47 = new Label(46, i + 1, sub.getResultTccM());
                Label label48 = new Label(47, i + 1, sub.getResultTcc());
                Label label49 = new Label(48, i + 1, sub.getResultLoanAmount());
                Label label50 = new Label(49, i + 1, sub.getResultLoanRecoveryAmount());
                Label label51 = new Label(50, i + 1, sub.getResultTotalAssociate());

//                Label label52 = new Label(52, i + 1, sub.getCompareFdNewAc());
//                Label label53 = new Label(53, i + 1, sub.getCompareFdDenoSum());
//                Label label54 = new Label(54, i + 1, sub.getCompareSsbRenewAc());
//                Label label55 = new Label(55, i + 1, sub.getCompareSsbRenew());
//                Label label56 = new Label(56, i + 1, sub.getCompareTotalNiAc());
//                Label label57 = new Label(57, i + 1, sub.getCompareTotalNiAmount());
//                Label label58 = new Label(58, i + 1, sub.getCompareTotalAc());
//                Label label59 = new Label(59, i + 1, sub.getCompareTotalAmount());
//                Label label60 = new Label(60, i + 1, sub.getCompareOtherMt());
//                Label label61 = new Label(61, i + 1, sub.getCompareOtherStn());
//                Label label62 = new Label(62, i + 1, sub.getCompareNiM());
//                Label label63 = new Label(63, i + 1, sub.getCompareNi());
//                Label label64 = new Label(64, i + 1, sub.getCompareTccM());
//                Label label65 = new Label(65, i + 1, sub.getCompareTcc());
//                Label label66 = new Label(66, i + 1, sub.getCompareLoanAc());
//                Label label67 = new Label(67, i + 1, sub.getCompareLoanAmount());
//                Label label68 = new Label(68, i + 1, sub.getCompareLoanRecoveryAc());
//                Label label69 = new Label(69, i + 1, sub.getCompareLoanRecoveryAmount());
//                Label label70 = new Label(70, i + 1, sub.getCompareNewAssociate());
//                Label label71 = new Label(71, i + 1, sub.getCompareTotalAssociate());
//                Label label72 = new Label(72, i + 1, sub.getCompareNewMember());
//                Label label73 = new Label(73, i + 1, sub.getCompareTotalMember());
//                Label label74 = new Label(74, i + 1, sub.getResultDailyNewAc());
//                Label label75 = new Label(75, i + 1, sub.getResultDailyDenoSum());
//                Label label76 = new Label(76, i + 1, sub.getResultDailyRenewAc());
//                Label label77 = new Label(77, i + 1, sub.getResultDailyRenew());
//                Label label78 = new Label(78, i + 1, sub.getResultMonthlyNewAc());
//                Label label79 = new Label(79, i + 1, sub.getResultMonthlyDenoSum());
//                Label label80 = new Label(80, i + 1, sub.getResultMonthlyRenewAc());
//                Label label81 = new Label(81, i + 1, sub.getResultMonthlyRenew());
//                Label label82 = new Label(82, i + 1, sub.getResultFdDenoSum());
//                Label label83 = new Label(83, i + 1, sub.getResultSsbNewAc());
//                Label label84 = new Label(84, i + 1, sub.getResultSsbDenoSum());
//                Label label85 = new Label(85, i + 1, sub.getResultSsbRenewAc());
//                Label label86 = new Label(86, i + 1, sub.getResultSsbRenew());
//                Label label87 = new Label(87, i + 1, sub.getResultTotalNiAc());
//                Label label88 = new Label(88, i + 1, sub.getResultTotalNiAmount());
//                Label label89 = new Label(89, i + 1, sub.getResultTotalAc());
//                Label label90 = new Label(90, i + 1, sub.getResultTotalAmount());
//                Label label91 = new Label(91, i + 1, sub.getResultOtherMt());
//                Label label92 = new Label(92, i + 1, sub.getResultOtherStn());
//                Label label93 = new Label(93, i + 1, sub.getResultNiM());
//                Label label94 = new Label(94, i + 1, sub.getResultNi());
//                Label label95 = new Label(95, i + 1, sub.getResultTccM());
//                Label label96 = new Label(96, i + 1, sub.getResultTcc());
//                Label label97 = new Label(97, i + 1, sub.getResultLoanAc());
//                Label label98 = new Label(98, i + 1, sub.getResultLoanAmount());
//                Label label99 = new Label(99, i + 1, sub.getResultLoanRecoveryAc());
//                Label label100 = new Label(100, i + 1, sub.getResultLoanRecoveryAmount());
//                Label label101 = new Label(101, i + 1, sub.getResultNewAssociate());
//                Label label102 = new Label(102, i + 1, sub.getResultTotalAssociate());
//                Label label103 = new Label(103, i + 1, sub.getResultNewMember());
//                Label label104 = new Label(104, i + 1, sub.getResultTotalMember());


                Label label201 = new Label(0, 0, "Join Date");
                Label label202 = new Label(1, 0, "Branch");
                Label label203 = new Label(2, 0, "Branch Code");
                Label label204 = new Label(3, 0, "Sector Name");
                Label label205 = new Label(4, 0, "Region Name");
                Label label206 = new Label(5, 0, "Zone Name");
                Label label207 = new Label(6, 0, "Associate Code");
                Label label208 = new Label(7, 0, "Name");
                Label label209 = new Label(8, 0, "Cader");
                // current
                Label label221 = new Label(9, 0, "Current Daily");
                Label label222 = new Label(10, 0, "Current Daily Collection");
                Label label223 = new Label(11, 0, "Current Monthly");
                Label label224 = new Label(12, 0, "Current Monthly Collection");
                Label label225 = new Label(13, 0, "Current Fd");
                Label label226 = new Label(14, 0, "Current Ssb");
                Label label227 = new Label(15, 0, "Current Ssb Deposit");
                Label label228 = new Label(16, 0, "Current NCC_M");
                Label label229 = new Label(17, 0, "Current NCC");
                Label label230 = new Label(18, 0, "Current Tcc_M");
                Label label231 = new Label(19, 0, "Current Tcc");
                Label label232 = new Label(20, 0, "Current Loan Amount");
                Label label233 = new Label(21, 0, "Current Loan Recovery");
                Label label234 = new Label(22, 0, "Current Total Associate");
                // compare
                Label label235 = new Label(23, 0, "Compare Daily");
                Label label236 = new Label(24, 0, "Compare Daily Collection");
                Label label237 = new Label(25, 0, "Compare Monthly");
                Label label238 = new Label(26, 0, "Compare Monthly Collection");
                Label label239 = new Label(27, 0, "Compare Fd");
                Label label240 = new Label(28, 0, "Compare Ssb");
                Label label241 = new Label(29, 0, "Compare Ssb Deposit");
                Label label242 = new Label(30, 0, "Compare NCC_M");
                Label label243 = new Label(31, 0, "Compare NCC");
                Label label244 = new Label(32, 0, "Compare Tcc_M");
                Label label245 = new Label(33, 0, "Compare Tcc");
                Label label246 = new Label(34, 0, "Compare Loan Amount");
                Label label247 = new Label(35, 0, "Compare Loan Recovery");
                Label label248 = new Label(36, 0, "Compare Total Associate");
                // Result
                Label label249 = new Label(37, 0, "Result Daily");
                Label label250 = new Label(38, 0, "Result Daily Collection");
                Label label251 = new Label(39, 0, "Result Monthly");
                Label label252 = new Label(40, 0, "Result Monthly Collection");
                Label label253 = new Label(41, 0, "Result Fd");
                Label label254 = new Label(42, 0, "Result Ssb");
                Label label255 = new Label(43, 0, "Result Ssb Deposit");
                Label label256 = new Label(44, 0, "Result NCC_M");
                Label label257 = new Label(45, 0, "Result NCC");
                Label label258 = new Label(46, 0, "Result Tcc_M");
                Label label259 = new Label(47, 0, "Result Tcc");
                Label label260 = new Label(48, 0, "Result Loan Amount");
                Label label261 = new Label(49, 0, "Result Loan Recovery");
                Label label262 = new Label(50, 0, "Result Total Associate");


////                Label label210 = new Label(9, 0, "Current Daily New Ac");
//                Label label211 = new Label(10, 0, "Current Daily");
////                Label label212 = new Label(11, 0, "Current Daily Renew Ac");
//                Label label213 = new Label(12, 0, "Current Daily Collection");
////                Label label214 = new Label(13, 0, "Current Monthly New Ac");
//                Label label215 = new Label(14, 0, "Current Monthly");
//                Label label216 = new Label(15, 0, "Current Monthly Collection");
////                Label label217 = new Label(16, 0, "Current Fd New Ac");
//                Label label218 = new Label(17, 0, "Current Fd");
////                Label label219 = new Label(18, 0, "Current Ssb New Ac");
//                Label label220 = new Label(19, 0, "Current Ssb");
////                Label label221 = new Label(20, 0, "Current Ssb Renew Ac");
//                Label label222 = new Label(21, 0, "Current Ssb Deposit");
////                Label label223 = new Label(22, 0, "Current Total Ni Ac");
////                Label label224 = new Label(23, 0, "Current Total Ni Amount");
////                Label label225 = new Label(24, 0, "Current Total Ac");
////                Label label226 = new Label(25, 0, "Current Total Amount");
////                Label label227 = new Label(26, 0, "Current Other Mt");
////                Label label228 = new Label(27, 0, "Current Other Stn");
//                Label label229 = new Label(28, 0, "Current NCC_M");
//                Label label230 = new Label(29, 0, "Current NCC");
//                Label label231 = new Label(30, 0, "Current Tcc_M");
//                Label label232 = new Label(31, 0, "Current Tcc");
////                Label label233 = new Label(32, 0, "Current Loan Ac");
//                Label label234 = new Label(33, 0, "Current Loan Amount");
////                Label label235 = new Label(34, 0, "Current Loan Recovery Ac");
//                Label label236 = new Label(35, 0, "Current Loan Recovery");
////                Label label237 = new Label(36, 0, "Current New Associate");
//                Label label238 = new Label(37, 0, "Current Total Associate");
////                Label label239 = new Label(38, 0, "Current New Member");
////                Label label240 = new Label(39, 0, "Current Total Member");
////                Label label241 = new Label(40, 0, "Compare Daily New Ac");
//                Label label242 = new Label(41, 0, "Compare Daily");
////                Label label243 = new Label(42, 0, "Compare Daily Renew Ac");
//                Label label244 = new Label(43, 0, "Compare Daily Collection");
////                Label label245 = new Label(44, 0, "Compare Monthly New Ac");
//                Label label246 = new Label(45, 0, "Compare Monthly");
//                Label label247 = new Label(46, 0, "Compare SSb Deno Sum");
//                Label label248 = new Label(47, 0, "Compare SSb New Ac");
//                Label label249 = new Label(48, 0, "Compare Monthly Demo Sum");
//                Label label250 = new Label(49, 0, "Compare Monthly Renew Ac");
//                Label label251 = new Label(50, 0, "Compare Monthly Renew");
//                Label label252 = new Label(51, 0, "Compare Fd New Ac");
//                Label label253 = new Label(52, 0, "Compare Fd Demo Sum");
//                Label label254 = new Label(53, 0, "Compare Ssb Re New Ac");
//                Label label255 = new Label(54, 0, "Compare Ssb Re New");
//                Label label256 = new Label(55, 0, "Compare Total Ni Ac");
//                Label label257 = new Label(56, 0, "Compare Total Ni Amount");
//                Label label258 = new Label(57, 0, "Compare Total Ac");
//                Label label259 = new Label(58, 0, "Compare Total Amount");
//                Label label260 = new Label(59, 0, "Compare Other Mt");
//                Label label261 = new Label(60, 0, "Compare Other Stn");
//                Label label262 = new Label(61, 0, "Compare NiM");
//                Label label263 = new Label(62, 0, "Compare Ni");
//                Label label264 = new Label(63, 0, "Compare TccM");
//                Label label265 = new Label(64, 0, "Compare Tcc");
//                Label label266 = new Label(65, 0, "Compare Loan Ac");
//                Label label267 = new Label(66, 0, "Compare Loan Amount");
//                Label label268 = new Label(67, 0, "Compare Loan Recovery Ac");
//                Label label269 = new Label(68, 0, "Compare Loan Recovery Amount");
//                Label label270 = new Label(69, 0, "Compare New Associate");
//                Label label271 = new Label(70, 0, "Compare Total Associate");
//                Label label272 = new Label(71, 0, "Compare New Member");
//                Label label273 = new Label(72, 0, "Compare Total Member");
//                Label label274 = new Label(73, 0, "Result Daily New Ac");
//                Label label275 = new Label(74, 0, "Result Daily Deno Sum");
//                Label label276 = new Label(75, 0, "Result Daily Renew Ac");
//                Label label277 = new Label(76, 0, "Result Daily Re New");
//                Label label278 = new Label(77, 0, "Result Monthly New Ac");
//                Label label279 = new Label(78, 0, "Result Monthly Deno Sum");
//                Label label280 = new Label(79, 0, "Result Monthly Renew Ac");
//                Label label281 = new Label(80, 0, "Result Monthly Renew");
//                Label label282 = new Label(81, 0, "Result Fd New Ac");
//                Label label283 = new Label(82, 0, "Result Fd Deno Sum");
//                Label label284 = new Label(83, 0, "Result Ssb New");
//                Label label285 = new Label(84, 0, "Result Ssb Deno Sum");
//                Label label286 = new Label(85, 0, "Result Ssb Renew Ac");
//                Label label287 = new Label(86, 0, "Result Ssb Renew");
//                Label label288 = new Label(87, 0, "Result Total Ni Ac");
//                Label label289 = new Label(88, 0, "Result Total Ni Amount");
//                Label label290 = new Label(89, 0, "Result Total Ac");
//                Label label291 = new Label(90, 0, "Result Total Amount");
//                Label label292 = new Label(91, 0, "Result Other Mt");
//                Label label293 = new Label(92, 0, "Result Other Stn");
//                Label label294 = new Label(93, 0, "Result NiM");
//                Label label295 = new Label(94, 0, "Result Ni");
//                Label label296 = new Label(95, 0, "Result Ccm");
//                Label label297 = new Label(96, 0, "Result Tcc");
//                Label label298 = new Label(97, 0, "Result Loan Ac");
//                Label label299 = new Label(98, 0, "Result Loan Amount");
//                Label label200 = new Label(99, 0, "Result Loan Recovery AC");
//                Label label301 = new Label(100, 0, "Result Loan Recovery Amount");
//                Label label302 = new Label(101, 0, "Result New Associate");
//                Label label303 = new Label(102, 0, "Result Total Associate");
//                Label label304 = new Label(103, 0, "Result New Member");
//                Label label305 = new Label(104, 0, "Result Total Member");

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
                    sheet.addCell(label10);
                    sheet.addCell(label11);
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
                    sheet.addCell(label24);
                    sheet.addCell(label25);
                    sheet.addCell(label26);
                    sheet.addCell(label27);
                    sheet.addCell(label28);
                    sheet.addCell(label29);
                    sheet.addCell(label30);
                    sheet.addCell(label31);
                    sheet.addCell(label32);
                    sheet.addCell(label33);
                    sheet.addCell(label34);
                    sheet.addCell(label35);
                    sheet.addCell(label36);
                    sheet.addCell(label37);
                    sheet.addCell(label38);
                    sheet.addCell(label39);
                    sheet.addCell(label40);
                    sheet.addCell(label41);
                    sheet.addCell(label42);
                    sheet.addCell(label43);
                    sheet.addCell(label44);
                    sheet.addCell(label45);
                    sheet.addCell(label46);
                    sheet.addCell(label47);
                    sheet.addCell(label48);
                    sheet.addCell(label49);
                    sheet.addCell(label50);
                    sheet.addCell(label51);
//                    sheet.addCell(label52);
//                    sheet.addCell(label53);
//                    sheet.addCell(label54);
//                    sheet.addCell(label55);
//                    sheet.addCell(label56);
//                    sheet.addCell(label57);
//                    sheet.addCell(label58);
//                    sheet.addCell(label59);
//                    sheet.addCell(label60);
//                    sheet.addCell(label61);
//                    sheet.addCell(label62);
//                    sheet.addCell(label63);
//                    sheet.addCell(label64);
//                    sheet.addCell(label65);
//                    sheet.addCell(label66);
//                    sheet.addCell(label67);
//                    sheet.addCell(label68);
//                    sheet.addCell(label69);
//                    sheet.addCell(label70);
//                    sheet.addCell(label71);
//                    sheet.addCell(label72);
//                    sheet.addCell(label73);
//                    sheet.addCell(label74);
//                    sheet.addCell(label75);
//                    sheet.addCell(label76);
//                    sheet.addCell(label77);
//                    sheet.addCell(label78);
//                    sheet.addCell(label79);
//                    sheet.addCell(label80);
//                    sheet.addCell(label81);
//                    sheet.addCell(label82);
//                    sheet.addCell(label83);
//                    sheet.addCell(label84);
//                    sheet.addCell(label85);
//                    sheet.addCell(label86);
//                    sheet.addCell(label87);
//                    sheet.addCell(label88);
//                    sheet.addCell(label89);
//                    sheet.addCell(label90);
//                    sheet.addCell(label91);
//                    sheet.addCell(label92);
//                    sheet.addCell(label93);
//                    sheet.addCell(label94);
//                    sheet.addCell(label95);
//                    sheet.addCell(label96);
//                    sheet.addCell(label97);
//                    sheet.addCell(label98);
//                    sheet.addCell(label99);
//                    sheet.addCell(label100);
//                    sheet.addCell(label101);
//                    sheet.addCell(label102);
//                    sheet.addCell(label103);
//                    sheet.addCell(label104);


                    sheet.addCell(label201);
                    sheet.addCell(label202);
                    sheet.addCell(label203);
                    sheet.addCell(label204);
                    sheet.addCell(label205);
                    sheet.addCell(label206);
                    sheet.addCell(label207);
                    sheet.addCell(label208);
                    sheet.addCell(label209);
                    sheet.addCell(label221);
                    sheet.addCell(label222);
                    sheet.addCell(label223);
                    sheet.addCell(label224);
                    sheet.addCell(label225);
                    sheet.addCell(label226);
                    sheet.addCell(label227);
                    sheet.addCell(label228);
                    sheet.addCell(label229);
                    sheet.addCell(label230);
                    sheet.addCell(label231);
                    sheet.addCell(label232);
                    sheet.addCell(label233);
                    sheet.addCell(label234);
                    sheet.addCell(label235);
                    sheet.addCell(label236);
                    sheet.addCell(label237);
                    sheet.addCell(label238);
                    sheet.addCell(label239);
                    sheet.addCell(label240);
                    sheet.addCell(label241);
                    sheet.addCell(label242);
                    sheet.addCell(label243);
                    sheet.addCell(label244);
                    sheet.addCell(label245);
                    sheet.addCell(label246);
                    sheet.addCell(label247);
                    sheet.addCell(label248);
                    sheet.addCell(label249);
                    sheet.addCell(label250);
                    sheet.addCell(label251);
                    sheet.addCell(label252);
                    sheet.addCell(label253);
                    sheet.addCell(label254);
                    sheet.addCell(label255);
                    sheet.addCell(label256);
                    sheet.addCell(label257);
                    sheet.addCell(label258);
                    sheet.addCell(label259);
                    sheet.addCell(label260);
                    sheet.addCell(label261);
                    sheet.addCell(label262);

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
                        } catch (Exception exception) {
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
}
