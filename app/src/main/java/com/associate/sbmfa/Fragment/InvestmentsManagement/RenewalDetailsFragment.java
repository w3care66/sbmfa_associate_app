package com.associate.sbmfa.Fragment.InvestmentsManagement;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalDetaildPrentModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalDetailsChlidModel;
import com.associate.sbmfa.Adapter.AssociateListAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.RenewelDetailsAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Plan;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.PlanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.PlanResult;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Renew;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewalDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.ResultRenewalDeatails;
import com.associate.sbmfa.Model.Associate_list_model;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
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

public class RenewalDetailsFragment extends Fragment {
    View RootView;
    RenewelDetailsAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    ArrayList<RenewalDetaildPrentModel> parent_models = new ArrayList<>();
    HashMap<String, List<String>> listDataChild;
    AssociateListAdapter associateListAdapter;
    ArrayList<Associate_list_model> associate_list_models = new ArrayList<>();
    RecyclerView recyclerView;
    Spinner spinner1, spinner2;
    ArrayList<String> typeStrings = new ArrayList<>();
    ArrayList<String> dateStrings = new ArrayList<>();
    ImageView imageViewSerach;
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView pdfdwon, imageViewBack;
    SessionManager sessionManager;
    String member_id = "";
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    GoogleProgressDialog googleProgressDialog;

    Spinner spinnerLanght;
    String PlanType = "", PlanCode = "";
    int page = 1;
    String langht = "15";
    String dateFrom = "", dateto = "";
    ImageButton imageButtonPrv, imageButtonNext;
    ArrayList<String> dateStrings1 = new ArrayList<>();
    TextView textViewNotFound;
    Button button_apply, button_reset;
    String screenType = "renewal";
    TextView textViewTitle;
    LinearLayout linearLayoutPlan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_renewal_details, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);

        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                    Toast.makeText(getContext(), "pdf download successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewTitle = RootView.findViewById(R.id.textView2);
        linearLayoutPlan = RootView.findViewById(R.id.line2);
        button_apply = RootView.findViewById(R.id.apply);
        button_reset = RootView.findViewById(R.id.reset);
        //Toast.makeText(getContext(), "sdfsdf", Toast.LENGTH_SHORT).show();
        listAdapter = new RenewelDetailsAdapter(getActivity(), parent_models, "renewal");
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

        if (getArguments() != null) {
            screenType = getArguments().getString("type");
            if (screenType.endsWith("renewal")) {
                textViewTitle.setText("Renewal Details");
                linearLayoutPlan.setVisibility(View.VISIBLE);
            } else {
                textViewTitle.setText("SSB Deposit List");
                linearLayoutPlan.setVisibility(View.GONE);
            }
        }
        dateStrings.add("Select investment plan");
        typeStrings.add("0");
        getPlanType(member_id, token);
       /* dateStrings1.add("10");
        dateStrings1.add("20");
        dateStrings1.add("30");
        dateStrings1.add("40");
        dateStrings1.add("50");
        dateStrings1.add("60");
        dateStrings1.add("70");
        dateStrings1.add("80");
        dateStrings1.add("90");
        dateStrings1.add("100");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings1);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinnerLanght.setAdapter(adapterselectDate);
        spinnerLanght.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                langht = dateStrings1.get(position);
                parent_models.clear();
                //listAdapter.notifyDataSetChanged();
                getRenewalDetailsList(member_id,token,String.valueOf(page),langht,dateFrom,dateto,PlanCode);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);
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
                                toDate.setText(null);
                                dateto = "";
                                fromDate.setText(dateFrom);
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
                if (!dateFrom.isEmpty()) {
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
                                    dateto = "" + dayOfMonth + "-" + monthOfYear1 + "-" + year;
                                    toDate.setText(dateto);
                                }
                            }, mYear, mMonth, mDay);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
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
                } else {
                    Toast.makeText(getActivity(), "Please Select From Date", Toast.LENGTH_SHORT).show();
                }

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
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page--;
                    getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page++;
                getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);
            }
        });
        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screenType.endsWith("renewal")) {
                    /*if (!PlanCode.isEmpty()){*/
                    page = 1;
                    langht = "15";
                    // spinnerLanght.setSelection(0);
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);
                        /*}else {
                            Toast.makeText(getContext(), "Plan field are required", Toast.LENGTH_SHORT).show();
                        }*/
                } else {
                    if (!dateFrom.isEmpty() && !dateto.isEmpty()) {
                        page = 1;
                        langht = "15";
                        //  spinnerLanght.setSelection(0);
                        parent_models.clear();
                        listAdapter.notifyDataSetChanged();
                        getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);
                    } else {
                        Toast.makeText(getContext(), "Date field are required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanType = "";
                PlanCode = "";
                dateFrom = "";
                dateto = "";
                fromDate.setText(null);
                toDate.setText(null);
                spinner2.setSelection(0);
                getRenewalDetailsList(member_id, token, String.valueOf(page), langht, dateFrom, dateto, PlanCode);

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

    private void getRenewalDetailsList(String member_id, String token, String page, String langht, String dateFrom, String dateto, String planCode) {
        if (screenType.equals("renewal")) {
            try {
                parent_models.clear();
                googleProgressDialog.show1("Loading Data....");
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _dateFrom = RequestBody.create(MediaType.parse("multipart/form-data"), dateFrom);
                RequestBody _dateto = RequestBody.create(MediaType.parse("multipart/form-data"), dateto);
                RequestBody _planCode = RequestBody.create(MediaType.parse("multipart/form-data"), planCode);
                RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), page);
                RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langht);
                RestHandler.getApiService().RENEWAL_DETAILS_RESPONSE_CALL(
                        _member_id, _token,
                        _dateFrom, _dateto,
                        _planCode, _page, _length).enqueue(new Callback<RenewalDetailsResponse>() {
                    @Override
                    public void onResponse(Call<RenewalDetailsResponse> call, Response<RenewalDetailsResponse> response) {
                        if (response.isSuccessful()) {
                            googleProgressDialog.dismiss();
                            if (response.body().getCode() == 200) {
                                textViewNotFound.setVisibility(View.GONE);
                                String id = null, name = null, member_id = null;
                                ResultRenewalDeatails result = response.body().getResult();
                                List<Renew> renewList = result.getRenew();
                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt) {
                                    imageButtonNext.setEnabled(true);
                                } else {
                                    imageButtonNext.setEnabled(false);
                                }
                                if (renewList.size() > 0) {
                                    for (int i = 0; i < renewList.size(); i++) {

                                        ArrayList<RenewalDetailsChlidModel> child_models = new ArrayList<>();
                                        id = String.valueOf(i + 1);
                                        name = renewList.get(i).getMember();
                                        member_id = renewList.get(i).getCreatedAt();
                                        child_models.add(new RenewalDetailsChlidModel(

                                                renewList.get(i).getCreatedAt(),
                                                renewList.get(i).getBranch(),
                                                String.valueOf(renewList.get(i).getBranchCode()),
                                                renewList.get(i).getSectorName(),
                                                renewList.get(i).getRegionName(),
                                                renewList.get(i).getZoneName(),
                                                renewList.get(i).getMemberId(),
                                                renewList.get(i).getAccountNumber(),
                                                renewList.get(i).getMember(),
                                                renewList.get(i).getPlan(),
                                                renewList.get(i).getTenure(),
                                                renewList.get(i).getAmount(),
                                                renewList.get(i).getAssociateCode(),
                                                renewList.get(i).getAssociateName(),
                                                renewList.get(i).getPaymentMode())

                                        );

                                        parent_models.add(new RenewalDetaildPrentModel(id, name, member_id, "", child_models));
                                    }
                                    listAdapter.notifyDataSetChanged();
                                } else {
                                    imageButtonNext.setEnabled(false);
                                    listAdapter.notifyDataSetChanged();
                                    textViewNotFound.setVisibility(View.VISIBLE);
                                }
                              /* }else {
                                   imageButtonNext.setEnabled(false);
                                   textViewNotFound.setVisibility(View.VISIBLE);
                                   Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                               }*/
                            } else {
                                imageButtonNext.setEnabled(false);
                                textViewNotFound.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            imageButtonNext.setEnabled(false);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            googleProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RenewalDetailsResponse> call, Throwable t) {
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                    }
                });

            } catch (Exception ex) {

            }

        } else {
            try {
                parent_models.clear();
                googleProgressDialog.show1("Loading Data....");
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _dateFrom = RequestBody.create(MediaType.parse("multipart/form-data"), dateFrom);
                RequestBody _dateto = RequestBody.create(MediaType.parse("multipart/form-data"), dateto);
                RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), page);
                RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langht);
                RestHandler.getApiService().SSB_RENEWAL_DETAILS_RESPONSE_CALL(
                        _member_id, _token,
                        _dateFrom, _dateto,
                        _page, _length).enqueue(new Callback<RenewalDetailsResponse>() {
                    @Override
                    public void onResponse(Call<RenewalDetailsResponse> call, Response<RenewalDetailsResponse> response) {
                        if (response.isSuccessful()) {
                            googleProgressDialog.dismiss();
                            if (response.body().getCode() == 200) {
                                textViewNotFound.setVisibility(View.GONE);
                                String id = null, name = null, member_id = null;
                                ResultRenewalDeatails result = response.body().getResult();
                                List<Renew> renewList = result.getRenew();

                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt) {
                                    imageButtonNext.setEnabled(true);
                                } else {
                                    imageButtonNext.setEnabled(false);
                                }
                                if (renewList.size() > 0) {
                                    for (int i = 0; i < renewList.size(); i++) {
                                        ArrayList<RenewalDetailsChlidModel> child_models = new ArrayList<>();
                                        id = String.valueOf(i + 1);
                                        name = renewList.get(i).getAssociateName();
                                        member_id = String.valueOf(renewList.get(i).getCreatedAt());
                                        child_models.add(new RenewalDetailsChlidModel(
                                                renewList.get(i).getCreatedAt(),
                                                renewList.get(i).getBranch(),
                                                String.valueOf(renewList.get(i).getBranchCode()),
                                                renewList.get(i).getSectorName(),
                                                renewList.get(i).getRegionName(),
                                                renewList.get(i).getZoneName(),
                                                renewList.get(i).getMemberId(),
                                                renewList.get(i).getAccountNumber(),
                                                renewList.get(i).getMember(),
                                                renewList.get(i).getPlan(),
                                                renewList.get(i).getTenure(),
                                                renewList.get(i).getAmount(),
                                                renewList.get(i).getAssociateCode(),
                                                renewList.get(i).getAssociateName(),
                                                renewList.get(i).getPaymentMode()
                                        ));
                                        parent_models.add(new RenewalDetaildPrentModel(id, name, member_id, "", child_models));

                                        listAdapter = new RenewelDetailsAdapter(getActivity(), parent_models, "ssb");
                                        expListView.setAdapter(listAdapter);
                                        DisplayMetrics metrics = new DisplayMetrics();
                                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                        int width = metrics.widthPixels;
                                        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

                                    }
                                    listAdapter.notifyDataSetChanged();
                                } else {
                                    textViewNotFound.setVisibility(View.VISIBLE);
                                }
                              /* }else {
                                   textViewNotFound.setVisibility(View.VISIBLE);
                                   Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                               }*/
                            } else {
                                textViewNotFound.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            textViewNotFound.setVisibility(View.VISIBLE);
                            googleProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RenewalDetailsResponse> call, Throwable t) {
                        textViewNotFound.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                    }
                });

            } catch (Exception ex) {

            }

        }

    }

    private void getPlanType(String member_id, String token) {
        try {
            googleProgressDialog.show1("Loading Data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RestHandler.getApiService().Plan_list_RESPONES_CALL(_member_id, _token).enqueue(new Callback<PlanResponse>() {
                @Override
                public void onResponse(Call<PlanResponse> call, final Response<PlanResponse> response) {
                    if (response.isSuccessful()) {
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200) {
                            PlanResult result = response.body().getResult();
                            List<Plan> plan = result.getPlan();
                            for (Plan plan1 : plan) {
                                dateStrings.add(plan1.getName());
                                typeStrings.add(String.valueOf(plan1.getId()));
                            }
                            ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
                            adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
                            spinner2.setAdapter(adapterselectDate);
                            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position > 0) {
                                        PlanType = dateStrings.get(position);
                                        PlanCode = typeStrings.get(position);
                                    } else {

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });


                        } else {
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data Not Found..", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PlanResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    Toast.makeText(getContext(), "" + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {

        }
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    private void createExcelSheet() {
        String Fnamexls = "excelSheet" + System.currentTimeMillis() + ".xls";
        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File directory = new File(sdCard.getAbsolutePath() + "/SBMFA");
        if (directory.isDirectory()) {
            Log.e("isDirectory", "" + directory.isDirectory());
        } else {
            Log.e("isDirectory", "" + directory.isDirectory());
            directory.mkdirs();
        }
        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;
        try {
            int a = 1;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //workbook.createSheet("Report", 0);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            for (int i = 0; i < parent_models.size(); i++) {
                RenewalDetailsChlidModel sub = parent_models.get(i).getChlidModels().get(0);
                Label label0 = new Label(0, i + 1, sub.getCreate_date());
                Label label1 = new Label(1, i + 1, sub.getBr_code());
                Label label2 = new Label(2, i + 1, sub.getBr_name());
                Label label3 = new Label(3, i + 1, sub.getSo_name());
                Label label4 = new Label(4, i + 1, sub.getMember_id());
                Label label5 = new Label(5, i + 1, sub.getRo_name());
                Label label6 = new Label(6, i + 1, sub.getZo_name());
                Label label7 = new Label(7, i + 1, sub.getAccount_number());
                Label label8 = new Label(8, i + 1, sub.getPlan());
                Label label9 = new Label(9, i + 1, sub.getTenure());
                Label label10 = new Label(10, i + 1, sub.getAmount());
                Label label11 = new Label(11, i + 1, sub.getAsscoiate_code());
                Label label12 = new Label(11, i + 1, sub.getAddcoiate_name());
                Label label13 = new Label(12, i + 1, sub.getPayment_mode());


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


                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
            Label label1 = new Label(0, 0, "Created Date");
            Label label2 = new Label(1, 0, "Branch Code");
            Label label3 = new Label(2, 0, "Branch Name");
            Label label4 = new Label(3, 0, "Sector Name");
            Label label5 = new Label(4, 0, "Member ID");
            Label label6 = new Label(5, 0, "RO Name");
            Label label7 = new Label(6, 0, "ZO Name");
            Label label8 = new Label(7, 0, "Account number");
            Label label9 = new Label(8, 0, "Plan Name");
            Label label10 = new Label(9, 0, "Tenure");
            Label label11 = new Label(10, 0, "Renewal Amount");
            Label label12 = new Label(11, 0, "Member Code");
            Label label13 = new Label(12, 0, "Member Name");
            Label label14 = new Label(13, 0, "Payment Mode");


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
