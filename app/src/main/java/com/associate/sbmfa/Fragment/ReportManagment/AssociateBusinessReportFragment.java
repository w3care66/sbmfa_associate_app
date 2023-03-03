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

import com.associate.sbmfa.Fragment.LoanManagment.Model.Lone_List_Child_model;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Branch;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultBranchList;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateBusinessReportDetailsAdapter;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessReportChild_model;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateBusinessReportParent_model;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateBusinessReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.BusinessReport;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.ResultBusinessReport;
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

public class AssociateBusinessReportFragment extends Fragment {
    View RootView;
    ImageView pdfdwon;
    AssociateBusinessReportDetailsAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateBusinessReportParent_model> parent_models = new ArrayList<>();
    ImageView imageViewBack, imageViewSerach;
    SessionManager sessionManager;
    String member_id = "";
    HashMap<String, String> UserDataToken;
    ConstraintLayout constraintLayoutOptions;
    String token = "";
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay;
    String dateFrom = "", dateto = "";
    HashMap<String, String> UserData;
    GoogleProgressDialog googleProgressDialog;
    int page = 1;
    String langht = "15";
    ImageButton imageButtonPrv, imageButtonNext;
    Spinner spinner2, spinner3;
    String stringBranchSpiner = "", associateId = "0", fromDateval = "0", toDateval = "0";
    TextView name1, count1, textViewNotFound;
    boolean isSerach = false;

    List<Associate> associateList = new ArrayList<>();
    ArrayAdapter<String> adapterBranch;
    ArrayList<String> dateStrings = new ArrayList<>();
    ArrayList<String> branch_name = new ArrayList<>();
    ArrayList<String> dateBranchId = new ArrayList<>();
    Button apply, reset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_associate_business_report, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        UserData = sessionManager.getUserDetails();
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        member_id = UserData.get(SessionManager.KEY_member_id);
        apply = RootView.findViewById(R.id.apply);
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        reset = RootView.findViewById(R.id.reset);
        spinner2 = RootView.findViewById(R.id.spinner2);
        spinner3 = RootView.findViewById(R.id.spinner3);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        count1 = RootView.findViewById(R.id.count1);
        name1 = RootView.findViewById(R.id.name1);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        parent_models.clear();
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    getAssociateBusinessList1(member_id, token,"1", "", stringBranchSpiner, dateFrom, dateto, associateId);

                }
            }
        });
        getAssociateActiveList(member_id, token);
        getBranch(member_id, token);
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
        branch_name.add("Select Branch");
        dateBranchId.add("0");
        adapterBranch = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, branch_name);
        adapterBranch.setDropDownViewResource(R.layout.spiner_item);
        spinner3.setAdapter(adapterBranch);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    associateId = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                Log.e("CheckApply", "asdasd");
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, stringBranchSpiner, dateFrom, dateto, associateId);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fromDate.setHint("From Date");
                toDate.setHint("To Date");
//                parent_models.clear();
//                listAdapter.notifyDataSetChanged();
                spinner2.setSelection(0);
                spinner3.setSelection(0);
                page = 1;
                fromDate.setText("");
                toDate.setText("");
                associateId = "0";
                stringBranchSpiner = "";
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, "", "", "", associateId);
            }
        });


        /*dateStrings.add("10");
        dateStrings.add("20");
        dateStrings.add("30");
        dateStrings.add("40");
        dateStrings.add("50");
        dateStrings.add("60");
        dateStrings.add("70");
        dateStrings.add("80");
        dateStrings.add("90");
        dateStrings.add("100");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinnerLanght.setAdapter(adapterselectDate);
        spinnerLanght.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                langht = dateStrings.get(position);
                getAssociateBusinessList(member_id,token,String.valueOf(page),langht);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        getAssociateBusinessList(member_id, token, String.valueOf(page), langht, "", "", "", associateId);
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    page--;
                    getAssociateBusinessList(member_id, token, String.valueOf(page), langht, stringBranchSpiner, dateFrom, dateto, associateId);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, stringBranchSpiner, dateFrom, dateto, associateId);
            }
        });

        imageViewBack = RootView.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
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

    private void getAssociateBusinessList(String member_id, String token, String pages, String langhts, String branch_id, String start_date, String end_date, String associateId) {
        googleProgressDialog.show1("Loading data....");
        parent_models.clear();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
        RequestBody branch_id_ = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
        RequestBody _associate_id = RequestBody.create(MediaType.parse("multipart/form-data"), associateId);
        RequestBody start_date_ = RequestBody.create(MediaType.parse("multipart/form-data"), start_date);
        RequestBody end_date_ = RequestBody.create(MediaType.parse("multipart/form-data"), end_date);
        RestHandler.getApiService().ASSOCIATE_BUSINESS_REPORT_RESPONSE_CALL(
                _member_id,
                _token,
                _page,
                _length, branch_id_,
                start_date_,
                end_date_,
                _associate_id
        ).enqueue(new Callback<AssociateBusinessReportResponse>() {
            @Override
            public void onResponse(Call<AssociateBusinessReportResponse> call, Response<AssociateBusinessReportResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        parent_models.clear();
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        String id = null, name = null, member_id = null;
                        ResultBusinessReport result = response.body().getResult();
                        List<BusinessReport> members = result.getBusinessReport();
                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt) {
                            imageButtonNext.setEnabled(members.size() == 15 ? true : false);
                        } else {
                            imageButtonNext.setEnabled(false);
                        }
                        if (members.size() > 0) {
                            textViewNotFound.setVisibility(View.GONE);
                            name1.setVisibility(View.VISIBLE);
                            count1.setVisibility(View.VISIBLE);
                            expListView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < members.size(); i++) {
                                ArrayList<AssociateBusinessReportChild_model> child_models = new ArrayList<>();
                                id = String.valueOf(i + 1);
                                name = members.get(i).getName();
                                member_id = String.valueOf(members.get(i).getMemberId());
                                child_models.add(new AssociateBusinessReportChild_model(
                                        members.get(i).getJoinDate(),
                                        members.get(i).getBranch(),
                                        members.get(i).getAssociate_code(),
                                        members.get(i).getSectorName(),
                                        members.get(i).getRegionName(),
                                        members.get(i).getZoneName(),
                                        members.get(i).getMemberId(),
                                        members.get(i).getName(),
                                        members.get(i).getCadre(),
                                        members.get(i).getDailyNewAc().toString(),
                                        members.get(i).getDailyDenoSum(),
                                        members.get(i).getDailyRenewAc().toString(),
                                        members.get(i).getDailyRenew(),
                                        members.get(i).getMonthlyNewAc().toString(),
                                        members.get(i).getMonthlyDenoSum().toString(),
                                        members.get(i).getMonthlyRenewAc().toString(),
                                        members.get(i).getMonthlyRenew().toString(),
                                        String.valueOf(members.get(i).getFdNewAc()),
                                        String.valueOf(members.get(i).getFdDenoSum()),
                                        members.get(i).getSsbNewAc().toString(),
                                        members.get(i).getSsbDenoSum().toString(),
                                        members.get(i).getSsbRenewAc().toString(),
                                        members.get(i).getSsbRenew().toString(),
                                        "",
                                        "",
                                        "",
                                        "",
                                        String.valueOf(members.get(i).getOtherMt()),
                                        String.valueOf(members.get(i).getOtherStn()),
                                        String.valueOf(members.get(i).getNiM()),
                                        String.valueOf(members.get(i).getNi()),
                                        String.valueOf(members.get(i).getTccM()),
                                        String.valueOf(members.get(i).getTcc()),
                                        String.valueOf(members.get(i).getLoanAc()),
                                        String.valueOf(members.get(i).getLoanAmount()),
                                        String.valueOf(members.get(i).getLoanRecoveryAc()),
                                        String.valueOf(members.get(i).getLoanRecoveryAmount()),
                                        String.valueOf(members.get(i).getNewAssociate()),
                                        String.valueOf(members.get(i).getTotalAssociate()),
                                        String.valueOf(members.get(i).getNewMember()),
                                        String.valueOf(members.get(i).getTotalMember())));


//                                            members.get(i).getJoinDate(),
//                                            members.get(i).getBranch(),
//                                            String.valueOf(members.get(i).getBranchCode()),
//                                            members.get(i).getSectorName(),
//                                            members.get(i).getRegionName(),
//                                            members.get(i).getZoneName(),
//                                            members.get(i).getMemberId(),
//                                            members.get(i).getName(),
//                                            members.get(i).getCadre(),
//                                            String.valueOf(members.get(i).getDailyNewAc()),
//                                            members.get(i).getDailyDenoSum(),
//                                            String.valueOf(members.get(i).getDailyRenewAc()),
//                                            members.get(i).getDailyRenew(),
//                                            String.valueOf(members.get(i).getMonthlyNewAc()),
//                                            String.valueOf(members.get(i).getMonthlyDenoSum()),
//                                            String.valueOf(members.get(i).getMonthlyRenewAc()),
//                                            members.get(i).getDailyRenew(),
//                                            String.valueOf(members.get(i).getFdNewAc()),
//                                            String.valueOf(members.get(i).getFdDenoSum()),
//                                            String.valueOf(members.get(i).getSsbNewAc()),
//                                            String.valueOf(members.get(i).getSsbDenoSum()),
//                                            String.valueOf(members.get(i).getSsbRenewAc()),
//                                            String.valueOf(members.get(i).getSsbRenew()),
//                                            String.valueOf(members.get(i).getTotalNiAc()),
//                                            String.valueOf(members.get(i).getTotalNiAmount()),
//                                            String.valueOf(members.get(i).getTotalAc()),
//                                            String.valueOf(members.get(i).getTotalAmount()),
//                                            String.valueOf(members.get(i).getOtherMt()),
//                                            String.valueOf(members.get(i).getOtherStn()),
//                                            String.valueOf(members.get(i).getNiM()),
//                                            String.valueOf(members.get(i).getNi()),
//                                            String.valueOf(members.get(i).getTccM()),
//                                            String.valueOf(members.get(i).getTcc()),
//                                            String.valueOf(members.get(i).getLoanAc()),
//                                            String.valueOf(members.get(i).getLoanAmount()),
//                                            String.valueOf(members.get(i).getLoanRecoveryAc()),
//                                            String.valueOf(members.get(i).getLoanRecoveryAmount()),
//                                            String.valueOf(members.get(i).getNewAssociate()),
//                                            String.valueOf(members.get(i).getTotalAssociate()),
//                                            String.valueOf(members.get(i).getNewMember()),
//                                            String.valueOf(members.get(i).getTotalMember())

                                parent_models.add(new AssociateBusinessReportParent_model(id, name, member_id, "", child_models));
                            }
                            listAdapter = new AssociateBusinessReportDetailsAdapter(getActivity(), parent_models);
                            expListView.setAdapter(listAdapter);
                            googleProgressDialog.dismiss();
                        } else {
                            googleProgressDialog.dismiss();
                            parent_models.clear();
                            listAdapter = new AssociateBusinessReportDetailsAdapter(getActivity(), parent_models);
                            expListView.setAdapter(listAdapter);
                            DisplayMetrics metrics = new DisplayMetrics();
                            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                            int width = metrics.widthPixels;
                            expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                            textViewNotFound.setVisibility(View.VISIBLE);
                            name1.setVisibility(View.GONE);
                            count1.setVisibility(View.GONE);
                            listAdapter.notifyDataSetChanged();
                        }

                    } else {
                        parent_models.clear();
                        listAdapter.notifyDataSetChanged();
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        textViewNotFound.setText(response.body().getMessages());
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    imageButtonNext.setEnabled(false);
                    textViewNotFound.setVisibility(View.VISIBLE);
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    googleProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AssociateBusinessReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                imageButtonNext.setEnabled(false);
                textViewNotFound.setVisibility(View.VISIBLE);
            }
        });
        /*}catch (Exception ex){
            googleProgressDialog.dismiss();
            imageButtonNext.setEnabled(false);
            textViewNotFound.setVisibility(View.VISIBLE);
        }*/

    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    private void createExcelSheet(ArrayList<AssociateBusinessReportParent_model> parentmodels) {
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
                AssociateBusinessReportChild_model sub = parentmodels.get(i).getChild_models().get(0);
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
                Label label10 = new Label(10, i + 1, sub.getMonthly_new_ac());
                Label label11 = new Label(11, i + 1, sub.getMonthly_deno_sum());
                Label label12 = new Label(12, i + 1, sub.getMonthly_renew_ac());
                Label label13 = new Label(13, i + 1, sub.getMonthly_renew());
                Label label14 = new Label(14, i + 1, sub.getFd_new_ac());
                Label label15 = new Label(15, i + 1, sub.getFd_deno_sum());
                Label label16 = new Label(16, i + 1, sub.getSsb_new_ac());
                Label label17 = new Label(17, i + 1, sub.getSsb_deno_sum());
                Label label18 = new Label(18, i + 1, sub.getSsb_renew_ac());
                Label label19 = new Label(19, i + 1, sub.getSsb_renew());
                Label label20 = new Label(20, i + 1, sub.getOther_mt());
                Label label21 = new Label(21, i + 1, sub.getOther_stn());
                Label label22 = new Label(22, i + 1, sub.getNi_m());
                Label label23 = new Label(23, i + 1, sub.getNi());
                Label label24 = new Label(24, i + 1, sub.getTcc_m());
                Label label25 = new Label(25, i + 1, sub.getTcc());
//                Label label26 = new Label(26, i + 1, sub.getLoan_ac());
                Label label27 = new Label(26, i + 1, sub.getLoan_ac());
                Label label28 = new Label(27, i + 1, sub.getLoan_amount());
                Label label29 = new Label(28, i + 1, sub.getLoan_recovery_ac());
                Label label30 = new Label(29, i + 1, sub.getLoan_recovery_amount());
                Label label31 = new Label(30, i + 1, sub.getNew_associate());
                Label label32 = new Label(31, i + 1, sub.getTotal_associate());
                Label label33 = new Label(32, i + 1, sub.getNew_member());
                Label label34 = new Label(33, i + 1, sub.getTotal_member());

                Label label35 = new Label(0, 0, "SO Name");
                Label label36 = new Label(1, 0, "RO Name");
                Label label37 = new Label(2, 0, "ZO Name");
                Label label38 = new Label(3, 0, "Associate Code");
                Label label39 = new Label(4, 0, "Associate Name");
                Label label40 = new Label(5, 0, "Carder");
                Label label41 = new Label(6, 0, "Daily N.I. - No. A/C");
                Label label42 = new Label(7, 0, "Daily N.I. - Total Deno");
                Label label43 = new Label(8, 0, "Daily Renew - No. A/C");
                Label label44 = new Label(9, 0, "Daily Renew - Total Amt");
                Label label45 = new Label(10, 0, "Monthly N.I. - No. A/C");
                Label label46 = new Label(11, 0, "Monthly N.I. - Total Deno");
                Label label47 = new Label(12, 0, "Monthly Renew - No. A/C");
                Label label48 = new Label(13, 0, "Monthly Renew - Total Amt");
                Label label49 = new Label(14, 0, "FD N.I. - No. A/C");
                Label label50 = new Label(15, 0, "FD N.I. - Total Deno");
//                Label label51 = new Label(16, 0, "FD N.I. - Total Deno");
                Label label52 = new Label(16, 0, "SSB N.I. - No. A/C");
                Label label53 = new Label(17, 0, "SSB N.I. - Total Deno");
                Label label54 = new Label(18, 0, "SSB Deposit - No. A/C");
                Label label55 = new Label(19, 0, "SSB Deposit - Total Amt");
                Label label56 = new Label(20, 0, "Other MI");
                Label label57 = new Label(21, 0, "Other STN");
                Label label58 = new Label(22, 0, "NCC_M");
                Label label59 = new Label(23, 0, "NCC");
                Label label60 = new Label(24, 0, "TCC_M");
                Label label61 = new Label(25, 0, "TCC");
                Label label62 = new Label(26, 0, "Loan - No. A/C");
                Label label63 = new Label(27, 0, "Loan - Total Amt");
                Label label64 = new Label(28, 0, "Loan Recovery - No. A/C");
                Label label65 = new Label(29, 0, "Loan Recovery - Total Amt");
                Label label66 = new Label(30, 0, "New Associate Joining No");
                Label label67 = new Label(31, 0, "Total Associate Joining No");
                Label label68 = new Label(32, 0, "New Member Joining No");
                Label label69 = new Label(33, 0, "Total Member Joining No");


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
//                    sheet.addCell(label26);
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
//                    sheet.addCell(label51);
                    sheet.addCell(label52);
                    sheet.addCell(label53);
                    sheet.addCell(label54);
                    sheet.addCell(label55);
                    sheet.addCell(label56);
                    sheet.addCell(label57);
                    sheet.addCell(label58);
                    sheet.addCell(label59);
                    sheet.addCell(label60);
                    sheet.addCell(label61);
                    sheet.addCell(label62);
                    sheet.addCell(label63);
                    sheet.addCell(label64);
                    sheet.addCell(label65);
                    sheet.addCell(label66);
                    sheet.addCell(label67);
                    sheet.addCell(label68);
                    sheet.addCell(label69);


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

    public void getAssociateActiveList(final String assciate_no, String token) {
        googleProgressDialog.show1("Loading...");

        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<AssociateMemberListResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Associate_active_List_RESPONES_CALL(_assciate_no, _token);
        applicationsListResponesCall.enqueue(new Callback<AssociateMemberListResponse>() {
            @Override
            public void onResponse(Call<AssociateMemberListResponse> call, Response<AssociateMemberListResponse> response) {
                googleProgressDialog.dismiss();
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
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {

                            ResultBranchList result = response.body().getResult();
                            List<Branch> branchList = result.getBranch();
                            if (branchList.size() > 0) {
                                for (Branch branch : branchList) {
                                    branch_name.add(branch.getName());
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


    private void getAssociateBusinessList1(String member_id, String token, String pages, String langhts, String branch_id, String start_date, String end_date, String associateId) {
        googleProgressDialog.show1("Loading data....");
        final ArrayList<AssociateBusinessReportParent_model> parentmodels = new ArrayList<>();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
        RequestBody branch_id_ = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
        RequestBody _associate_id = RequestBody.create(MediaType.parse("multipart/form-data"), associateId);
        RequestBody start_date_ = RequestBody.create(MediaType.parse("multipart/form-data"), start_date);
        RequestBody end_date_ = RequestBody.create(MediaType.parse("multipart/form-data"), end_date);
        RestHandler.getApiService().ASSOCIATE_BUSINESS_REPORT_RESPONSE_CALL(
                _member_id,
                _token,
                _page,
                _length, branch_id_,
                start_date_,
                end_date_,
                _associate_id
        ).enqueue(new Callback<AssociateBusinessReportResponse>() {
            @Override
            public void onResponse(Call<AssociateBusinessReportResponse> call, Response<AssociateBusinessReportResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        String id = null, name = null, member_id = null;
                        ResultBusinessReport result = response.body().getResult();
                        List<BusinessReport> members = result.getBusinessReport();
                        if (members.size() > 0) {
                            textViewNotFound.setVisibility(View.GONE);
                            name1.setVisibility(View.VISIBLE);
                            count1.setVisibility(View.VISIBLE);
                            expListView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < members.size(); i++) {
                                ArrayList<AssociateBusinessReportChild_model> child_models = new ArrayList<>();
                                id = String.valueOf(i + 1);
                                name = members.get(i).getName();
                                member_id = String.valueOf(members.get(i).getMemberId());
                                child_models.add(new AssociateBusinessReportChild_model(
                                        members.get(i).getJoinDate(),
                                        members.get(i).getBranch(),
                                        members.get(i).getAssociate_code(),
                                        members.get(i).getSectorName(),
                                        members.get(i).getRegionName(),
                                        members.get(i).getZoneName(),
                                        members.get(i).getMemberId(),
                                        members.get(i).getName(),
                                        members.get(i).getCadre(),
                                        members.get(i).getDailyNewAc().toString(),
                                        members.get(i).getDailyDenoSum(),
                                        members.get(i).getDailyRenewAc().toString(),
                                        members.get(i).getDailyRenew(),
                                        members.get(i).getMonthlyNewAc().toString(),
                                        members.get(i).getMonthlyDenoSum().toString(),
                                        members.get(i).getMonthlyRenewAc().toString(),
                                        members.get(i).getMonthlyRenew().toString(),
                                        String.valueOf(members.get(i).getFdNewAc()),
                                        String.valueOf(members.get(i).getFdDenoSum()),
                                        members.get(i).getSsbNewAc().toString(),
                                        members.get(i).getSsbDenoSum().toString(),
                                        members.get(i).getSsbRenewAc().toString(),
                                        members.get(i).getSsbRenew().toString(),
                                        "",
                                        "",
                                        "",
                                        "",
                                        String.valueOf(members.get(i).getOtherMt()),
                                        String.valueOf(members.get(i).getOtherStn()),
                                        String.valueOf(members.get(i).getNiM()),
                                        String.valueOf(members.get(i).getNi()),
                                        String.valueOf(members.get(i).getTccM()),
                                        String.valueOf(members.get(i).getTcc()),
                                        String.valueOf(members.get(i).getLoanAc()),
                                        String.valueOf(members.get(i).getLoanAmount()),
                                        String.valueOf(members.get(i).getLoanRecoveryAc()),
                                        String.valueOf(members.get(i).getLoanRecoveryAmount()),
                                        String.valueOf(members.get(i).getNewAssociate()),
                                        String.valueOf(members.get(i).getTotalAssociate()),
                                        String.valueOf(members.get(i).getNewMember()),
                                        String.valueOf(members.get(i).getTotalMember())));


//                                            members.get(i).getJoinDate(),
//                                            members.get(i).getBranch(),
//                                            String.valueOf(members.get(i).getBranchCode()),
//                                            members.get(i).getSectorName(),
//                                            members.get(i).getRegionName(),
//                                            members.get(i).getZoneName(),
//                                            members.get(i).getMemberId(),
//                                            members.get(i).getName(),
//                                            members.get(i).getCadre(),
//                                            String.valueOf(members.get(i).getDailyNewAc()),
//                                            members.get(i).getDailyDenoSum(),
//                                            String.valueOf(members.get(i).getDailyRenewAc()),
//                                            members.get(i).getDailyRenew(),
//                                            String.valueOf(members.get(i).getMonthlyNewAc()),
//                                            String.valueOf(members.get(i).getMonthlyDenoSum()),
//                                            String.valueOf(members.get(i).getMonthlyRenewAc()),
//                                            members.get(i).getDailyRenew(),
//                                            String.valueOf(members.get(i).getFdNewAc()),
//                                            String.valueOf(members.get(i).getFdDenoSum()),
//                                            String.valueOf(members.get(i).getSsbNewAc()),
//                                            String.valueOf(members.get(i).getSsbDenoSum()),
//                                            String.valueOf(members.get(i).getSsbRenewAc()),
//                                            String.valueOf(members.get(i).getSsbRenew()),
//                                            String.valueOf(members.get(i).getTotalNiAc()),
//                                            String.valueOf(members.get(i).getTotalNiAmount()),
//                                            String.valueOf(members.get(i).getTotalAc()),
//                                            String.valueOf(members.get(i).getTotalAmount()),
//                                            String.valueOf(members.get(i).getOtherMt()),
//                                            String.valueOf(members.get(i).getOtherStn()),
//                                            String.valueOf(members.get(i).getNiM()),
//                                            String.valueOf(members.get(i).getNi()),
//                                            String.valueOf(members.get(i).getTccM()),
//                                            String.valueOf(members.get(i).getTcc()),
//                                            String.valueOf(members.get(i).getLoanAc()),
//                                            String.valueOf(members.get(i).getLoanAmount()),
//                                            String.valueOf(members.get(i).getLoanRecoveryAc()),
//                                            String.valueOf(members.get(i).getLoanRecoveryAmount()),
//                                            String.valueOf(members.get(i).getNewAssociate()),
//                                            String.valueOf(members.get(i).getTotalAssociate()),
//                                            String.valueOf(members.get(i).getNewMember()),
//                                            String.valueOf(members.get(i).getTotalMember())

                                parentmodels.add(new AssociateBusinessReportParent_model(id, name, member_id, "", child_models));
                            }
                            googleProgressDialog.dismiss();
                        } else {
                            googleProgressDialog.dismiss();
                        }
                        createExcelSheet(parentmodels);
                    } else {
                        googleProgressDialog.dismiss();
                    }
                } else {
                    googleProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AssociateBusinessReportResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });
        /*}catch (Exception ex){
            googleProgressDialog.dismiss();
            imageButtonNext.setEnabled(false);
            textViewNotFound.setVisibility(View.VISIBLE);
        }*/

    }

}