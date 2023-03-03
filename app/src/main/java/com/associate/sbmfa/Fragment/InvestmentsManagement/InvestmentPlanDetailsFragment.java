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
import androidx.fragment.app.FragmentTransaction;


import com.associate.sbmfa.BuildConfig;

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

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_Details_ListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.AssociateDeatilsViewFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.Investment_Plan_Details_Adapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_child_model;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_parent_model;
import com.associate.sbmfa.Fragment.MemberMangement.response.Associate;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberCheckResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.BranchResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmenBrachResult;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentPlanDetails.InvestmentlistingPlanDetailsMember;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentPlanDetails.InvestmentlistingPlanDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.Plan;
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

public class InvestmentPlanDetailsFragment extends Fragment  {
    View RootView;
    Investment_Plan_Details_Adapter listAdapter;
    ExpandableListView expListView;
    ArrayList<Investment_Plan_Details_parent_model> parent_models=new ArrayList<>();
    List<Associate> associateList = new ArrayList<>();
    Spinner spinner2,length,plansp;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_investment_plan_details, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        name1= RootView.findViewById(R.id.name1);
        count1= RootView.findViewById(R.id.count1);
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        length= RootView.findViewById(R.id.length);
        apply=RootView.findViewById(R.id.apply);
        plansp=RootView.findViewById(R.id.plan);
        datanotfound=RootView.findViewById(R.id.not_found);
        reset=RootView.findViewById(R.id.reset);
        editTextTextPersonName=RootView.findViewById(R.id.editTextTextPersonName);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        pdfdwon=RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()){
                    createExcelSheet();
                }

            }
        });


      /*  datelengthArr.clear();
        datelengthArr.add("10");
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
                    lengthValue = Integer.parseInt(datelengthArr.get(position));
                    getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
                }else{
                    lengthValue =10;
                    getMemberList(member_id,10,1,token,"","","");
                }
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

        planArr.add("Select Plan");
        final ArrayAdapter<String> plan = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, planArr);
        plan.setDropDownViewResource(R.layout.spiner_item);
        plansp.setAdapter(plan);
        plansp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    planId = String.valueOf(planArrID.get(position-1));
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


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page=page+1;
                // lengthValue=lengthValue * page;
                getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page = page - 1;
                    // lengthValue=lengthValue * page;
                    getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
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
        getMemberList(member_id,15,page,token,"","","");
        listAdapter = new Investment_Plan_Details_Adapter(getActivity(), parent_models);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                page =1;
                getMemberList(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);
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
                plansp.setSelection(0);
                getMemberList(member_id,15,page,token,"","","");

            }
        });
        getBranchList(member_id,token);
        return  RootView;
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


    public  void getMemberList(final String member_id, int length, int page, String token, String assciateId, String fromDate, String toDate){
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
        RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
        RequestBody  _assciateId= RequestBody.create(MediaType.parse("multipart/form-data"), assciateId);
        RequestBody  _planId= RequestBody.create(MediaType.parse("multipart/form-data"), planId);
        Call<InvestmentlistingPlanDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Investment_Details_RESPONES_CALL(_assciate_no,_page,_length,_token,_fromDate,_toDate,_assciateId,_planId);
        applicationsListResponesCall.enqueue(new Callback<InvestmentlistingPlanDetailsResponse>() {
            @Override
            public void onResponse(Call<InvestmentlistingPlanDetailsResponse> call, Response<InvestmentlistingPlanDetailsResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Log.e("ressss---",""+response.body().getMessages());
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                        List<InvestmentlistingPlanDetailsMember> member = response.body().getResult().getMember();
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
                        for (InvestmentlistingPlanDetailsMember memberItem : member) {
                            ArrayList<Investment_Plan_Details_child_model> child_models = new ArrayList<>();
                            child_models.clear();
                            child_models.add(new Investment_Plan_Details_child_model(memberItem.getId().toString(),memberItem.getMemberId(),memberItem.getMobileNumber(),memberItem.getAssociateCode(),memberItem.getAccountNumber(),memberItem.getTenure(),memberItem.getCurrentBalance(),memberItem.getEliAmount(),memberItem.getDepositeAmount(),memberItem.getState(),memberItem.getDistrict(),memberItem.getCity(),memberItem.getVillage(),memberItem.getPinCode(),memberItem.getFirstId(),memberItem.getSecondId(),memberItem.getAddress(),memberItem.getAssociateName(),memberItem.getPlan(),memberItem.getFormNumber(),memberItem.getCreatedAt()));
                            parent_models.add(new Investment_Plan_Details_parent_model(String.valueOf(i), memberItem.getMember(), memberItem.getAccountNumber(), String.valueOf(memberItem.getId()), child_models));
                            i++;
                        }
                        listAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<InvestmentlistingPlanDetailsResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                datanotfound.setVisibility(View.VISIBLE);
                next.setEnabled(false);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void getBranchList(final String assciate_no, final String token){
        googleProgressDialog.show1("Loading...");

        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<BranchResponse> applicationsListResponesCall = RestHandler.getApiService().Branch_list_Register_RESPONES_CALL(_assciate_no,_token);
        applicationsListResponesCall.enqueue(new Callback<BranchResponse>() {
            @Override
            public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
                googleProgressDialog.dismiss();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                               Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                               sessionManager.logoutUser();
                           }
                        InvestmenBrachResult result = response.body().getResult();


                        List<Plan> plan = result.getPlan();
                        for (int i =0;i< plan.size(); i++) {
                            planArr.add(plan.get(i).getName());
                            planArrID.add(plan.get(i).getId().toString());
                        }

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BranchResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void getMemberList2(final String member_id, int length, int page, String token, String assciateId, String fromDate, String toDate){
        googleProgressDialog.show1("Loading...");
//       listAdapter.notifyDataSetChanged();
        final ArrayList<Investment_Plan_Details_parent_model> parent_models=new ArrayList<>();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf("0"));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _fromDate = RequestBody.create(MediaType.parse("multipart/form-data"), fromDate);
        RequestBody _toDate = RequestBody.create(MediaType.parse("multipart/form-data"), toDate);
        RequestBody  _assciateId= RequestBody.create(MediaType.parse("multipart/form-data"), assciateId);
        RequestBody  _planId= RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(planId));
//        RequestBody  _member_id= RequestBody.create(MediaType.parse("multipart/form-data"), editTextTextPersonName.getText().toString());

        Call<InvestmentlistingPlanDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Investment_Details_RESPONES_CALL(_assciate_no,_page,_length,_token,_fromDate,_toDate,_assciateId,_planId);
        applicationsListResponesCall.enqueue(new Callback<InvestmentlistingPlanDetailsResponse>() {
            @Override
            public void onResponse(Call<InvestmentlistingPlanDetailsResponse> call, Response<InvestmentlistingPlanDetailsResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }
                        List<InvestmentlistingPlanDetailsMember> member = response.body().getResult().getMember();
                        int ii=1;
                        for (InvestmentlistingPlanDetailsMember memberItem : member) {
                            ArrayList<Investment_Plan_Details_child_model> child_models = new ArrayList<>();
                            child_models.add(new Investment_Plan_Details_child_model(memberItem.getId().toString(),memberItem.getMemberId(),memberItem.getMobileNumber(),memberItem.getAssociateCode(),memberItem.getAccountNumber(),memberItem.getTenure(),memberItem.getCurrentBalance(),memberItem.getEliAmount(),memberItem.getDepositeAmount(),memberItem.getState(),memberItem.getDistrict(),memberItem.getCity(),memberItem.getVillage(),memberItem.getPinCode(),memberItem.getFirstId(),memberItem.getSecondId(),memberItem.getAddress(),memberItem.getAssociateName(),memberItem.getPlan(),memberItem.getFormNumber(),memberItem.getCreatedAt()));
                            parent_models.add(new Investment_Plan_Details_parent_model(String.valueOf(ii), memberItem.getMember(), memberItem.getAccountNumber(), String.valueOf(memberItem.getId()), child_models));
                            ii++;
                        }

                        Log.e("testsize",""+parent_models.size());
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
                                Investment_Plan_Details_child_model sub = parent_models.get(i).getInvestment_plan_details_child_models().get(0);
                                Label label0 = new Label(0,i+1,sub.getMemberI_d());
                                Label label1 = new Label(1,i+1,sub.getMembermobilenumber());
                                Label label2 = new Label(2,i+1,sub.getAssociateCode());
                                Label label3 = new Label(3,i+1,sub.getAccountNumber());
                                Label label4 = new Label(4,i+1,sub.getTenture());
                                Label label5 = new Label(5,i+1,sub.getBalance());
//                                Label label6 = new Label(6,i+1,sub.getEliAmount());
                                Label label7 = new Label(7,i+1,sub.getDepositeAmount());
                                Label label8 = new Label(8,i+1,sub.getAddress());
                                Label label9 = new Label(9,i+1,sub.getState());
                                Label label10 = new Label(10,i+1,sub.getDistrict());
                                Label label11 = new Label(11,i+1,sub.getCity());
                                Label label12 = new Label(12,i+1,sub.getVillage());
                                Label label13 = new Label(13,i+1,sub.getPin());
                                Label label14 = new Label(14,i+1,sub.getFirst());
                                Label label15 = new Label(15,i+1,sub.getSecond());


                                try {
                                    sheet.addCell(label0);
                                    sheet.addCell(label1);
                                    sheet.addCell(label2);
                                    sheet.addCell(label3);
                                    sheet.addCell(label4);
                                    sheet.addCell(label5);
//                                    sheet.addCell(label6);
                                    sheet.addCell(label7);
                                    sheet.addCell(label8);
                                    sheet.addCell(label9);
                                    sheet.addCell(label10);
                                    sheet.addCell(label11);
                                    sheet.addCell(label12);
                                    sheet.addCell(label13);
                                    sheet.addCell(label14);
                                    sheet.addCell(label15);
                                } catch (WriteException e) {
                                    e.printStackTrace();
                                }
                            }
                            Label label1 = new Label(0,0,"id");
                            Label label2 = new Label(1, 0, "Member Mobile Number");
                            Label label3 = new Label(2,0,"Associate Code");
                            Label label4 = new Label(3,0,"Account Number");
                            Label label5 = new Label(4,0,"Tenure");
                            Label label6 = new Label(5,0,"Balance");
//                            Label label7 = new Label(6,0,"ELI Amount");
                            Label label8 = new Label(7,0,"Deposite Amount");
                            Label label9 = new Label(8,0,"Address");
                            Label label10 = new Label(9,0,"State");
                            Label label11= new Label(10,0,"District");
                            Label label12 = new Label(11,0,"City");
                            Label label13 = new Label(12,0,"Village Name");
                            Label label14 = new Label(13,0,"Pin Code");
                            Label label15 = new Label(14,0,"First ID Proof");
                            Label label16 = new Label(15,0,"Second ID Proof");


                            sheet.addCell(label1);
                            sheet.addCell(label2);
                            sheet.addCell(label3);
                            sheet.addCell(label4);
                            sheet.addCell(label5);
                            sheet.addCell(label6);
//                            sheet.addCell(label7);
                            sheet.addCell(label8);
                            sheet.addCell(label9);
                            sheet.addCell(label10);
                            sheet.addCell(label11);
                            sheet.addCell(label12);
                            sheet.addCell(label13);
                            sheet.addCell(label14);
                            sheet.addCell(label15);
                            sheet.addCell(label16);

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
            public void onFailure(Call<InvestmentlistingPlanDetailsResponse> call, Throwable t) {
                googleProgressDialog.dismiss();

            }
        });
    }

    private void createExcelSheet() {
        getMemberList2(member_id,lengthValue,page,token,associateId,fromDateval,toDateval);

    }



//    @Override
//    public void onEvent_View(String id, String memberid, String type) {
////        Fragment fragment=new MemberDetailsFragment();
////        Bundle bundle = new Bundle();
////        bundle.putString("id",id);
////        fragment.setArguments(bundle);
////        FragmentTransaction transaction = getFragmentManager().beginTransaction();
////        transaction.replace(R.id.nav_host_fragment, fragment);
////        transaction.addToBackStack(null);
////        transaction.commit();
//    }
}
