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

import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateBusinessSummaryReportDetailsAdapter;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessSummaryReportChild;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessSummaryReportParent;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateBusinessSummaryReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.BusinessSummaryReport;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.ResultBusinessSummaryReport;
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

public class BusinessSummaryReportFragment extends Fragment {
    View RootView;
    AssociateBusinessSummaryReportDetailsAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateBusinessSummaryReportParent> parent_models = new ArrayList<>();
    ImageView pdfdwon, imageViewBack;
    SessionManager sessionManager;
    String member_id = "";
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    GoogleProgressDialog googleProgressDialog;
    int page = 1;
    String langht = "15";
    ImageButton imageButtonPrv, imageButtonNext;
    Spinner spinnerLanght;
    ArrayList<String> dateStrings = new ArrayList<>();
    TextView fromDate, toDate;
    TextView count1, name1, textViewNotFound;
    List<Associate> associateList = new ArrayList<>();
    Spinner spinner2, length;
    ArrayList<String> dateStrings1 = new ArrayList<>();
    String associateId = "", fromDateval = "", toDateval = "";
    private int mYear, mMonth, mDay;
    String dateFrom = "", dateto, comparetodateval = "", comparefromdateval = "";
    Spinner spinnerBranch;
    ArrayList<String> dateBranchId = new ArrayList<>();
    String stringBranchSpiner = "";
    ArrayAdapter<String> adapterBranch;
    ArrayList<String> dateBranch = new ArrayList<>();
    Button apply, reset;
    ImageView imageViewSerach;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_business_summary_report, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        member_id = UserData.get(SessionManager.KEY_member_id);
        spinnerLanght = RootView.findViewById(R.id.spinner26);
        spinner2 = RootView.findViewById(R.id.spinner2);
        spinnerBranch = RootView.findViewById(R.id.spinner3);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        name1 = RootView.findViewById(R.id.name1);
        count1 = RootView.findViewById(R.id.count1);
        spinnerBranch = RootView.findViewById(R.id.spinner3);
        apply = RootView.findViewById(R.id.apply);
        reset = RootView.findViewById(R.id.reset);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);

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

        googleProgressDialog = new GoogleProgressDialog(getContext());
        parent_models.clear();
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        dateStrings.clear();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, fromDateval, toDateval, stringBranchSpiner, associateId);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDate.setHint("From Date");
                toDate.setHint("To Date");
                fromDate.setText("");
                toDate.setText("");
                dateFrom = "";
                dateto = "";
                fromDateval = "";
                toDateval = "";
                spinnerBranch.setSelection(0);
                spinner2.setSelection(0);
                associateId = "";
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, fromDateval, toDateval, stringBranchSpiner, associateId);
            }
        });

        getAssociateActiveList(member_id, token);
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

        dateStrings.add("Select Associate");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinner2.setAdapter(adapterselectDate);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    associateId = associateList.get(position - 1).getId().toString();
                }else {
                    associateId = "" ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dateBranch.clear();
        getBranch(member_id, token);
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

        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    getAssociateBusinessList1(member_id, token, "1", "", fromDateval, toDateval, stringBranchSpiner, associateId);
                }
            }
        });
        getAssociateBusinessList(member_id, token, String.valueOf(page), langht, fromDateval, toDateval, stringBranchSpiner, associateId);
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    page--;
                    getAssociateBusinessList(member_id, token, String.valueOf(page), langht, fromDateval, toDateval, stringBranchSpiner, associateId);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, fromDateval, toDateval, stringBranchSpiner, associateId);
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
        return RootView;
    }

    private void getAssociateBusinessList(String member_id, String token, String pages, String langhts, String fromDate, String toDate, String branch_id, String associateId) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
            RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
            RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
            RequestBody _associateId = RequestBody.create(MediaType.parse("multipart/form-data"), associateId);
            RestHandler.getApiService().ASSOCIATE_BUSINESS_SUMMARY_REPORT_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _page,
                    _length,
                    _fromDate,
                    _toDate,
                    _associateId,
                    _branch_id
            ).enqueue(new Callback<AssociateBusinessSummaryReportResponse>() {
                @Override
                public void onResponse(Call<AssociateBusinessSummaryReportResponse> call, Response<AssociateBusinessSummaryReportResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                            googleProgressDialog.dismiss();
                            String id = null, name = null, member_id = null;
                            ResultBusinessSummaryReport result = response.body().getResult();
                            List<BusinessSummaryReport> members = result.getBusinessSummaryReport();
                            if (members.size() > 0) {
                                imageButtonNext.setEnabled(true);
                                textViewNotFound.setVisibility(View.GONE);
                                count1.setVisibility(View.VISIBLE);
                                name1.setVisibility(View.VISIBLE);
                                expListView.setVisibility(View.VISIBLE);
                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt) {
                                    imageButtonNext.setEnabled(members.size() == 15 ? true : false);
                                } else {
                                    imageButtonNext.setEnabled(false);
                                }
                                for (int i = 0; i < members.size(); i++) {
                                    ArrayList<AssociateBusinessSummaryReportChild> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = members.get(i).getName();
                                    member_id = String.valueOf(members.get(i).getMemberId());
                                    child_models.add(new AssociateBusinessSummaryReportChild(
                                            members.get(i).getJoinDate(),
                                            members.get(i).getBranch(),
                                            String.valueOf(members.get(i).getBranchCode()),
                                            members.get(i).getSectorName(),
                                            members.get(i).getRegionName(),
                                            members.get(i).getZoneName(),
                                            members.get(i).getMemberId(),
                                            members.get(i).getName(),
                                            members.get(i).getCadre(),
                                            String.valueOf(members.get(i).getDailyNewAc()),
                                            members.get(i).getDailyDenoSum(),
                                            String.valueOf(members.get(i).getDailyRenewAc()),
                                            members.get(i).getDailyRenew(),
                                            String.valueOf(members.get(i).getSsbNewAc()),
                                            String.valueOf(members.get(i).getSsbDenoSum()),
                                            String.valueOf(members.get(i).getSsbRenewAc()),
                                            String.valueOf(members.get(i).getSsbRenew()),
                                            String.valueOf(members.get(i).getKanyadhanNewAc()),
                                            String.valueOf(members.get(i).getKanyadhanDenoSum()),
                                            String.valueOf(members.get(i).getKanyadhanRenewAc()),
                                            String.valueOf(members.get(i).getKanyadhanRenewAc()),
                                            String.valueOf(members.get(i).getMbNewAc()),
                                            String.valueOf(members.get(i).getMbDenoSum()),
                                            String.valueOf(members.get(i).getMbRenewAc()),
                                            String.valueOf(members.get(i).getMbRenew()),
                                            String.valueOf(members.get(i).getFfdNewAc()),
                                            String.valueOf(members.get(i).getFfdDenoSum()),
                                            String.valueOf(members.get(i).getFrdNewAc()),
                                            String.valueOf(members.get(i).getFrdDenoSum()),
                                            String.valueOf(members.get(i).getFrdRenewAc()),
                                            String.valueOf(members.get(i).getFrdRenew()),
                                            String.valueOf(members.get(i).getJeevanNewAc()),
                                            String.valueOf(members.get(i).getJeevanDenoSum()),
                                            String.valueOf(members.get(i).getJeevanRenewAc()),
                                            String.valueOf(members.get(i).getJeevanRenew()),
                                            String.valueOf(members.get(i).getMiNewAc()),
                                            String.valueOf(members.get(i).getMiDenoSum()),
                                            String.valueOf(members.get(i).getMiRenewAc()),
                                            String.valueOf(members.get(i).getMiRenew()),
                                            String.valueOf(members.get(i).getFdNewAc()),
                                            String.valueOf(members.get(i).getFdDenoSum()),
                                            String.valueOf(members.get(i).getRdNewAc()),
                                            String.valueOf(members.get(i).getRdDenoSum()),
                                            String.valueOf(members.get(i).getRdRenewAc()),
                                            String.valueOf(members.get(i).getRdRenew()),
                                            String.valueOf(members.get(i).getBhavhishyaNewAc()),
                                            String.valueOf(members.get(i).getBhavhishyaDenoSum()),
                                            String.valueOf(members.get(i).getBhavhishyaRenewAc()),
                                            String.valueOf(members.get(i).getBhavhishyaRenew()),
                                            String.valueOf(members.get(i).getTotalNiAc()),
                                            String.valueOf(members.get(i).getTotalNiAmount()),
                                            String.valueOf(members.get(i).getTotalAc()),
                                            String.valueOf(members.get(i).getTotalAmount()),
                                            String.valueOf(members.get(i).getOtherMt()),
                                            String.valueOf(members.get(i).getOtherStn()),
                                            String.valueOf(members.get(i).getNiM()),
                                            String.valueOf(members.get(i).getNi()),
                                            String.valueOf(members.get(i).getTccM()),
                                            String.valueOf(members.get(i).getTcc()),
                                            String.valueOf(members.get(i).getStLoanAc()),
                                            String.valueOf(members.get(i).getStLoanAmount()),
                                            String.valueOf(members.get(i).getPlLoanAc()),
                                            String.valueOf(members.get(i).getPlLoanAmount()),
                                            String.valueOf(members.get(i).getLaLoanAc()),
                                            String.valueOf(members.get(i).getLaLoanAmount()),
                                            String.valueOf(members.get(i).getStLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getStLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getPlLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getPlLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getLaLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getLaLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getGpLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getGpLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getNewAssociate()),
                                            String.valueOf(members.get(i).getTotalAssociate()),
                                            String.valueOf(members.get(i).getNewMember()),
                                            String.valueOf(members.get(i).getTotalMember())

                                    ));
                                    parent_models.add(new AssociateBusinessSummaryReportParent(id, name, member_id, "", child_models));
                                }
                                listAdapter = new AssociateBusinessSummaryReportDetailsAdapter(getActivity(), parent_models);
                                expListView.setAdapter(listAdapter);
                                DisplayMetrics metrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                int width = metrics.widthPixels;
                                expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                                listAdapter.notifyDataSetChanged();
                            } else {
                                listAdapter = new AssociateBusinessSummaryReportDetailsAdapter(getActivity(), parent_models);
                                expListView.setAdapter(listAdapter);
                                DisplayMetrics metrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                int width = metrics.widthPixels;
                                expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                                textViewNotFound.setVisibility(View.VISIBLE);
                                count1.setVisibility(View.GONE);
                                name1.setVisibility(View.GONE);
                                listAdapter.notifyDataSetChanged();
                            }
                        } else {
                            imageButtonNext.setEnabled(false);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            count1.setVisibility(View.GONE);
                            name1.setVisibility(View.GONE);
                            textViewNotFound.setText(response.body().getMessages());
                            googleProgressDialog.dismiss();
                            parent_models.clear();
                            listAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        count1.setVisibility(View.VISIBLE);
                        name1.setVisibility(View.VISIBLE);
                        parent_models.clear();
                        listAdapter.notifyDataSetChanged();
                        googleProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<AssociateBusinessSummaryReportResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    imageButtonNext.setEnabled(false);
                    textViewNotFound.setVisibility(View.VISIBLE);
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception ex) {

        }

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
                            if (response.body().getAssociateStatus() == 0) {
                                Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                                sessionManager.logoutUser();
                            }
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


    public void getAssociateActiveList(final String assciate_no, String token) {
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<AssociateMemberListResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Associate_active_List_RESPONES_CALL(_assciate_no, _token);
        applicationsListResponesCall.enqueue(new Callback<AssociateMemberListResponse>() {
            @Override
            public void onResponse(Call<AssociateMemberListResponse> call, Response<AssociateMemberListResponse> response) {
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
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
                        int i = 1;
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
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }


    private void getAssociateBusinessList1(String member_id, String token, String pages, String langhts, String fromDate, String toDate, String branch_id, String associateId) {
        try {
            googleProgressDialog.show1("Loading data....");
            final ArrayList<AssociateBusinessSummaryReportParent> parentmodels = new ArrayList<>();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
            RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
            RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
            RequestBody _associateId = RequestBody.create(MediaType.parse("multipart/form-data"), associateId);
            RestHandler.getApiService().ASSOCIATE_BUSINESS_SUMMARY_REPORT_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _page,
                    _length,
                    _fromDate,
                    _toDate,
                    _associateId,
                    _branch_id
            ).enqueue(new Callback<AssociateBusinessSummaryReportResponse>() {
                @Override
                public void onResponse(Call<AssociateBusinessSummaryReportResponse> call, Response<AssociateBusinessSummaryReportResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            googleProgressDialog.dismiss();
                            String id = null, name = null, member_id = null;
                            ResultBusinessSummaryReport result = response.body().getResult();
                            List<BusinessSummaryReport> members = result.getBusinessSummaryReport();
                            if (members.size() > 0) {
                                for (int i = 0; i < members.size(); i++) {
                                    ArrayList<AssociateBusinessSummaryReportChild> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = members.get(i).getName();
                                    member_id = String.valueOf(members.get(i).getMemberId());
                                    child_models.add(new AssociateBusinessSummaryReportChild(
                                            members.get(i).getJoinDate(),
                                            members.get(i).getBranch(),
                                            String.valueOf(members.get(i).getBranchCode()),
                                            members.get(i).getSectorName(),
                                            members.get(i).getRegionName(),
                                            members.get(i).getZoneName(),
                                            members.get(i).getMemberId(),
                                            members.get(i).getName(),
                                            members.get(i).getCadre(),
                                            String.valueOf(members.get(i).getDailyNewAc()),
                                            members.get(i).getDailyDenoSum(),
                                            String.valueOf(members.get(i).getDailyRenewAc()),
                                            members.get(i).getDailyRenew(),
                                            String.valueOf(members.get(i).getSsbNewAc()),
                                            String.valueOf(members.get(i).getSsbDenoSum()),
                                            String.valueOf(members.get(i).getSsbRenewAc()),
                                            String.valueOf(members.get(i).getSsbRenew()),
                                            String.valueOf(members.get(i).getKanyadhanNewAc()),
                                            String.valueOf(members.get(i).getKanyadhanDenoSum()),
                                            String.valueOf(members.get(i).getKanyadhanRenewAc()),
                                            String.valueOf(members.get(i).getKanyadhanRenewAc()),
                                            String.valueOf(members.get(i).getMbNewAc()),
                                            String.valueOf(members.get(i).getMbDenoSum()),
                                            String.valueOf(members.get(i).getMbRenewAc()),
                                            String.valueOf(members.get(i).getMbRenew()),
                                            String.valueOf(members.get(i).getFfdNewAc()),
                                            String.valueOf(members.get(i).getFfdDenoSum()),
                                            String.valueOf(members.get(i).getFrdNewAc()),
                                            String.valueOf(members.get(i).getFrdDenoSum()),
                                            String.valueOf(members.get(i).getFrdRenewAc()),
                                            String.valueOf(members.get(i).getFrdRenew()),
                                            String.valueOf(members.get(i).getJeevanNewAc()),
                                            String.valueOf(members.get(i).getJeevanDenoSum()),
                                            String.valueOf(members.get(i).getJeevanRenewAc()),
                                            String.valueOf(members.get(i).getJeevanRenew()),
                                            String.valueOf(members.get(i).getMiNewAc()),
                                            String.valueOf(members.get(i).getMiDenoSum()),
                                            String.valueOf(members.get(i).getMiRenewAc()),
                                            String.valueOf(members.get(i).getMiRenew()),
                                            String.valueOf(members.get(i).getFdNewAc()),
                                            String.valueOf(members.get(i).getFdDenoSum()),
                                            String.valueOf(members.get(i).getRdNewAc()),
                                            String.valueOf(members.get(i).getRdDenoSum()),
                                            String.valueOf(members.get(i).getRdRenewAc()),
                                            String.valueOf(members.get(i).getRdRenew()),
                                            String.valueOf(members.get(i).getBhavhishyaNewAc()),
                                            String.valueOf(members.get(i).getBhavhishyaDenoSum()),
                                            String.valueOf(members.get(i).getBhavhishyaRenewAc()),
                                            String.valueOf(members.get(i).getBhavhishyaRenew()),
                                            String.valueOf(members.get(i).getTotalNiAc()),
                                            String.valueOf(members.get(i).getTotalNiAmount()),
                                            String.valueOf(members.get(i).getTotalAc()),
                                            String.valueOf(members.get(i).getTotalAmount()),
                                            String.valueOf(members.get(i).getOtherMt()),
                                            String.valueOf(members.get(i).getOtherStn()),
                                            String.valueOf(members.get(i).getNiM()),
                                            String.valueOf(members.get(i).getNi()),
                                            String.valueOf(members.get(i).getTccM()),
                                            String.valueOf(members.get(i).getTcc()),
                                            String.valueOf(members.get(i).getStLoanAc()),
                                            String.valueOf(members.get(i).getStLoanAmount()),
                                            String.valueOf(members.get(i).getPlLoanAc()),
                                            String.valueOf(members.get(i).getPlLoanAmount()),
                                            String.valueOf(members.get(i).getLaLoanAc()),
                                            String.valueOf(members.get(i).getLaLoanAmount()),
                                            String.valueOf(members.get(i).getStLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getStLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getPlLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getPlLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getLaLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getLaLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getGpLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getGpLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getLoanRecoveryAc()),
                                            String.valueOf(members.get(i).getLoanRecoveryAmount()),
                                            String.valueOf(members.get(i).getNewAssociate()),
                                            String.valueOf(members.get(i).getTotalAssociate()),
                                            String.valueOf(members.get(i).getNewMember()),
                                            String.valueOf(members.get(i).getTotalMember())

                                    ));
                                    parentmodels.add(new AssociateBusinessSummaryReportParent(id, name, member_id, "", child_models));
                                }
                                createExcelSheet(parentmodels);
                            }
                        } else {
                            googleProgressDialog.dismiss();
                        }
                    } else {
                        googleProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<AssociateBusinessSummaryReportResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void createExcelSheet(ArrayList<AssociateBusinessSummaryReportParent> parentmodels) {
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
                AssociateBusinessSummaryReportChild sub = parentmodels.get(i).getAssociateBusinessSummaryReportChildren().get(0);
                //Label label0 = new Label(0,i+1,parent_models.get(i).getProgress());
                Label label0 = new Label(0, i + 1, sub.getSector_name());
                Label label1 = new Label(1, i + 1, sub.getRegion_name());
                Label label2 = new Label(2, i + 1, sub.getZone_name());
                Label label3 = new Label(3, i + 1, sub.getMember_id());
                Label label4 = new Label(4, i + 1, sub.getName());
                Label label5 = new Label(5, i + 1, sub.getCadre());
                Label label6 = new Label(6, i + 1, sub.getDaily_new_ac());
                Label label7 = new Label(7, i + 1, sub.getDaily_deno_sum());
                Label label8 = new Label(8, i + 1, sub.getDaily_renew_ac());
                Label label9 = new Label(9, i + 1, sub.getDaily_renew());
                Label label10 = new Label(10, i + 1, sub.getKanyadhan_new_ac());
                Label label11 = new Label(11, i + 1, sub.getKanyadhan_deno_sum());
                Label label12 = new Label(12, i + 1, sub.getKanyadhan_renew_ac());
                Label label13 = new Label(13, i + 1, sub.getKanyadhan_renew());
                Label label14 = new Label(14, i + 1, sub.getMb_new_ac());
                Label label15 = new Label(15, i + 1, sub.getMb_deno_sum());
                Label label16 = new Label(16, i + 1, sub.getMb_renew_ac());
                Label label17 = new Label(17, i + 1, sub.getMb_renew());
                Label label18 = new Label(18, i + 1, sub.getFrd_new_ac());
                Label label19 = new Label(19, i + 1, sub.getFrd_deno_sum());
                Label label20 = new Label(20, i + 1, sub.getFrd_renew_ac());
                Label label21 = new Label(21, i + 1, sub.getFrd_renew());
                Label label22 = new Label(22, i + 1, sub.getFfd_new_ac());
                Label label23 = new Label(23, i + 1, sub.getFfd_deno_sum());
                Label label24 = new Label(24, i + 1, sub.getJeevan_new_ac());
                Label label25 = new Label(25, i + 1, sub.getJeevan_deno_sum());
                Label label26 = new Label(26, i + 1, sub.getJeevan_renew_ac());
                Label label27 = new Label(27, i + 1, sub.getJeevan_renew());
                Label label28 = new Label(28, i + 1, sub.getMi_new_ac());
                Label label29 = new Label(29, i + 1, sub.getMi_deno_sum());
                Label label32 = new Label(30, i + 1, sub.getFd_new_ac());
                Label label33 = new Label(31, i + 1, sub.getFd_deno_sum());
                Label label34 = new Label(32, i + 1, sub.getRd_new_ac());
                Label label35 = new Label(33, i + 1, sub.getRd_deno_sum());
                Label label36 = new Label(34, i + 1, sub.getRd_renew_ac());
                Label label37 = new Label(35, i + 1, sub.getRd_renew());
                Label label38 = new Label(36, i + 1, sub.getBhavhishya_new_ac());
                Label label39 = new Label(37, i + 1, sub.getBhavhishya_deno_sum());
                Label label40 = new Label(38, i + 1, sub.getBhavhishya_renew_ac());
                Label label41 = new Label(39, i + 1, sub.getBhavhishya_renew());
                Label label42 = new Label(40, i + 1, sub.getSsb_new_ac());
                Label label43 = new Label(41, i + 1, sub.getSsb_deno_sum());
                Label label44 = new Label(42, i + 1, sub.getSsb_renew_ac());
                Label label45 = new Label(43, i + 1, sub.getSsb_renew());
                Label label46 = new Label(44, i + 1, sub.getOther_mt());
                Label label47 = new Label(45, i + 1, sub.getOther_stn());
                Label label48 = new Label(46, i + 1, sub.getNi_m());
                Label label49 = new Label(47, i + 1, sub.getNi());
                Label label50 = new Label(48, i + 1, sub.getTcc_m());
                Label label51 = new Label(49, i + 1, sub.getTcc());
                Label label52 = new Label(50, i + 1, sub.getNew_associate());
                Label label53 = new Label(51, i + 1, sub.getTotal_associate());
                Label label54 = new Label(52, i + 1, sub.getNew_member());
                Label label55 = new Label(53, i + 1, sub.getTotal_member());



                Label label72 = new Label(0, 0, "SO Name");
                Label label73 = new Label(1, 0, "RO Name");
                Label label74 = new Label(2, 0, "ZO Name");
                Label label75 = new Label(3, 0, "Associate Code");
                Label label76 = new Label(4, 0, "Associate Name");
                Label label77 = new Label(5, 0, "Carder");
                Label label78 = new Label(6, 0, "Daily N.I. - No. A/C");
                Label label79 = new Label(7, 0, "Daily N.I. - Total Deno");
                Label label80 = new Label(8, 0, "Daily Renew - No. A/C");
                Label label81 = new Label(9, 0, "Daily Renew - Total Amt");
                Label label82 = new Label(10, 0, "Smaraddh Kanyadhan N.I. - No. A/C");
                Label label83 = new Label(11, 0, "Smaraddh Kanyadhan N.I. - Total Deno");
                Label label84 = new Label(12, 0, "Smaraddh Kanyadhan Renew - No. A/C");
                Label label85 = new Label(13, 0, "Smaraddh Kanyadhan Renew - Total Amt");
                Label label86 = new Label(14, 0, "MB N.I. - No. A/C");
                Label label87 = new Label(15, 0, "MB N.I. - Total Deno");
                Label label88 = new Label(16, 0, "MB Renew - No. A/C");
                Label label89 = new Label(17, 0, "MB Renew - Total Amt");
                Label label90 = new Label(18, 0, "FRD N.I. - No. A/C");
                Label label91 = new Label(19, 0, "FRD N.I. - Total Deno");
                Label label92 = new Label(20, 0, "FRD Renew - No. A/C");
                Label label93 = new Label(21, 0, "FRD Renew - Total Amt");
                Label label94 = new Label(22, 0, "FFD N.I. - No. A/C");
                Label label95 = new Label(23, 0, "FFD N.I. - Total Deno");
                Label label96 = new Label(24, 0, "Smaraddh Jeevan N.I. - No. A/C");
                Label label97 = new Label(25, 0, "Smaraddh Jeevan N.I. - Total Deno");
                Label label98 = new Label(26, 0, "Smaraddh Jeevan Renew - No. A/C");
                Label label99 = new Label(27, 0, "Smaraddh Jeevan Renew - No. A/C");
                Label label100 = new Label(28, 0, "MIS N.I. - No. A/C");
                Label label101 = new Label(29, 0, "MIS N.I. - Total Deno");
                Label label104 = new Label(30, 0, "FD N.I. - No. A/C");
                Label label105 = new Label(31, 0, "FD N.I. - Total Deno");
                Label label106 = new Label(32, 0, "RD N.I. - No. A/C");
                Label label107 = new Label(33, 0, "RD N.I. - Total Deno");
                Label label108 = new Label(34, 0, "RD Renew - No. A/C");
                Label label109 = new Label(35, 0, "RD Renew - Total Deno");
                Label label110 = new Label(36, 0, "Smaraddh Bhavishya N.I. - No. A/C");
                Label label111 = new Label(37, 0, "Smaraddh Bhavishya N.I. - Total Deno");
                Label label112 = new Label(38, 0, "Smaraddh Bhavishya Renew - No. A/C");
                Label label113 = new Label(39, 0, "Smaraddh Bhavishya Renew - Total Deno");
                Label label114 = new Label(40, 0, "SSB NI - No. A/C");
                Label label115 = new Label(41, 0, "SSB NI - Total Deno");
                Label label116 = new Label(42, 0, "SSB Deposit - No. A/C");
                Label label117 = new Label(43, 0, "SSB Deposit - Total Amt");
                Label label118 = new Label(44, 0, "Other MI");
                Label label119 = new Label(45, 0, "Other STN");
                Label label120 = new Label(46, 0, "NCC_M");
                Label label121 = new Label(47, 0, "NCC");
                Label label122 = new Label(48, 0, "TCC_M");
                Label label123 = new Label(49, 0, "TCC");
                Label label124 = new Label(50, 0, "New Associate Joining No");
                Label label125 = new Label(51, 0, "Total Associate Joining No");
                Label label126 = new Label(52, 0, "New Member Joining No");
                Label label127 = new Label(53, 0, "Total Member Joining No");


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
//                    sheet.addCell(label30);
//                    sheet.addCell(label31);
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
                    sheet.addCell(label52);
                    sheet.addCell(label53);
                    sheet.addCell(label54);
                    sheet.addCell(label55);
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
                    sheet.addCell(label72);
                    sheet.addCell(label73);
                    sheet.addCell(label74);
                    sheet.addCell(label75);
                    sheet.addCell(label76);
                    sheet.addCell(label77);
                    sheet.addCell(label78);
                    sheet.addCell(label79);
                    sheet.addCell(label80);
                    sheet.addCell(label81);
                    sheet.addCell(label82);
                    sheet.addCell(label83);
                    sheet.addCell(label84);
                    sheet.addCell(label85);
                    sheet.addCell(label86);
                    sheet.addCell(label87);
                    sheet.addCell(label88);
                    sheet.addCell(label89);
                    sheet.addCell(label90);
                    sheet.addCell(label91);
                    sheet.addCell(label92);
                    sheet.addCell(label93);
                    sheet.addCell(label94);
                    sheet.addCell(label95);
                    sheet.addCell(label96);
                    sheet.addCell(label97);
                    sheet.addCell(label98);
                    sheet.addCell(label99);
                    sheet.addCell(label100);
                    sheet.addCell(label101);
//                    sheet.addCell(label102);
//                    sheet.addCell(label103);
                    sheet.addCell(label104);
                    sheet.addCell(label105);
                    sheet.addCell(label106);
                    sheet.addCell(label107);
                    sheet.addCell(label108);
                    sheet.addCell(label109);
                    sheet.addCell(label110);
                    sheet.addCell(label111);
                    sheet.addCell(label112);
                    sheet.addCell(label113);
                    sheet.addCell(label114);
                    sheet.addCell(label115);
                    sheet.addCell(label116);
                    sheet.addCell(label117);
                    sheet.addCell(label118);
                    sheet.addCell(label119);
                    sheet.addCell(label120);
                    sheet.addCell(label121);
                    sheet.addCell(label122);
                    sheet.addCell(label123);
                    sheet.addCell(label124);
                    sheet.addCell(label125);
                    sheet.addCell(label126);
                    sheet.addCell(label127);
//                    sheet.addCell(label128);
//                    sheet.addCell(label129);
//                    sheet.addCell(label130);
//                    sheet.addCell(label131);
//                    sheet.addCell(label132);
//                    sheet.addCell(label133);
//                    sheet.addCell(label134);
//                    sheet.addCell(label135);
//                    sheet.addCell(label136);
//                    sheet.addCell(label137);
//                    sheet.addCell(label138);
//                    sheet.addCell(label139);
//                    sheet.addCell(label140);
//                    sheet.addCell(label141);
//                    sheet.addCell(label142);
//                    sheet.addCell(label143);
//                    sheet.addCell(label144);
//                    sheet.addCell(label145);
//                    sheet.addCell(label146);
//                    sheet.addCell(label147);
//                    sheet.addCell(label148);
//                    sheet.addCell(label149);


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
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                        Uri uri = Uri.parse(sdCard.getAbsolutePath() + "/SBMFA");
//                        intent.setDataAndType(uri, "*/*");
//                        getActivity().startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
//        } catch (RowsExceededException e) {
//            e.printStackTrace();
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
    }
}