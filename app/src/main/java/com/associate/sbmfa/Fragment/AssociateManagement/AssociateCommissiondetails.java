package com.associate.sbmfa.Fragment.AssociateManagement;

import android.app.AlertDialog;
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
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateCommissionDetailsAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_Commission_details_Child_Model;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_Commission_details_Parent_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCom;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCommisionDetailsResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Ledger;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.MemberLegerResponse;
import com.associate.sbmfa.Model.Member_Mangement_Parent_models;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import jxl.write.biff.RowsExceededException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociateCommissiondetails extends Fragment implements AssociateCommissionDetailsAdapter.EventListener {
    View RootView;
    AssociateCommissionDetailsAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<Associate_Commission_details_Parent_model> Associate_Commission_details_Parent_models = new ArrayList<>();

    public static Spinner spinner2, length;
    public static ArrayList<String> dateStrings = new ArrayList<>();
    public static ArrayList<String> lager_id_arr = new ArrayList<>();
    public static ImageView imageViewSerach;
    public static boolean isSerach = false;
    public boolean isSerach1 = false;
    public static ConstraintLayout constraintLayoutOptions;
    ImageView pdfdwon, imageViewBack;
    GoogleProgressDialog googleProgressDialog;
    int lengthValue = 15, page = 1;
    SessionManager sessionManager;
    String member_id = "", fromDateval = "", toDateval = "", associateId = "";
    HashMap<String, String> UserDataToken;
    String token = "", leger_id = "";
    HashMap<String, String> UserData;
    ImageButton next, back;
    List<Ledger> ledgers = new ArrayList<>();
    Button apply, reset;
    ArrayList<String> datelengthArr = new ArrayList<>();
    ArrayList<Member_Mangement_Parent_models> parent_models = new ArrayList<>();
    TextView textViewNotFound, name1, count1;
    public static String id;
    public int selction = 0;
    ArrayAdapter<String> adapterselectDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_associate_commissiondetails, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        name1 = RootView.findViewById(R.id.name1);
        count1 = RootView.findViewById(R.id.count1);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        apply = RootView.findViewById(R.id.apply);
        reset = RootView.findViewById(R.id.reset);
        length = RootView.findViewById(R.id.length);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Associate_Commission_details_Parent_models.isEmpty()) {
                    createExcelSheet();
                }
            }
        });
        listAdapter = new AssociateCommissionDetailsAdapter(getActivity(), Associate_Commission_details_Parent_models, AssociateCommissiondetails.this);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

        getMemberList(member_id, lengthValue, page, token, leger_id);
        /*datelengthArr.clear();
        datelengthArr.add(String.valueOf(lengthValue));
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
        adapterselectlength.setDropDownViewResource(R.layout.spiner_item);
        length.setAdapter(adapterselectlength);
        length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(position > 0) {
                   lengthValue = Integer.parseInt(datelengthArr.get(position));
                   getMemberList(member_id,lengthValue,page,token,leger_id);
               }else{
                   lengthValue =10;
                   getMemberList(member_id,lengthValue,page,token,leger_id);
               }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMemberList(member_id, lengthValue, page, token, leger_id);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                spinner2.setSelection(0);
                leger_id = "";
                page = 1;
                getMemberList(member_id, lengthValue, page, token, leger_id);
            }
        });
//        imageViewSerach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isSerach == false){
//                    imageViewSerach.setImageResource(R.drawable.ic_baseline_close_24);
//                    constraintLayoutOptions.setVisibility(View.VISIBLE);
//                    isSerach = true;
//                    isSerach1 = true;
//                }else {
//                    imageViewSerach.setImageResource(R.drawable.magnifyingglass);
//                    constraintLayoutOptions.setVisibility(View.GONE);
//                    isSerach = false;
//                    isSerach1 = false;
//                }
//            }
//        });
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
        getLegerList(member_id, token);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                getMemberList(member_id, lengthValue, page, token, leger_id);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    page--;
                    getMemberList(member_id, lengthValue, page, token, leger_id);
                }
            }
        });
        parent_models.clear();
        listAdapter.notifyDataSetChanged();
        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();

       /* spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    selction = position;
                    leger_id =  lager_id_arr.get(position);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    public void getLegerList(final String assciate_no, String token) {
        lager_id_arr.clear();
        dateStrings.clear();
        dateStrings.add("Select Commission Ledger");
        lager_id_arr.add("00");

        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<MemberLegerResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Leger_List_RESPONES_CALL(_assciate_no, _token);
        applicationsListResponesCall.enqueue(new Callback<MemberLegerResponse>() {
            @Override
            public void onResponse(Call<MemberLegerResponse> call, Response<MemberLegerResponse> response) {
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            sessionManager.logoutUser();
                        }
                        ledgers = response.body().getResult().getLedger();
                        int i = 1;
                        for (Ledger memberItem : ledgers) {
                            dateStrings.add(memberItem.getDate());
                            lager_id_arr.add(memberItem.getLedgerId().toString());
                        }
                        adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
                        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
                        spinner2.setAdapter(adapterselectDate);
                        spinner2.setSelection(selction);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position > 0) {
                                    selction = position;
                                    leger_id = lager_id_arr.get(position);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
//                        if (isSerach1 == true){
//                            imageViewSerach.setImageResource(R.drawable.ic_baseline_close_24);
//                            constraintLayoutOptions.setVisibility(View.VISIBLE);
//                        }else {
//                            imageViewSerach.setImageResource(R.drawable.magnifyingglass);
//                            constraintLayoutOptions.setVisibility(View.GONE);
//                        }

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberLegerResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    int page_data_length;
    public void getMemberList(final String member_id, int length, int page, String token, final String leger_id) {
        googleProgressDialog.show1("Loading ...");
        parent_models.clear();
        if (page <= 0) {
            page = 1;
        }
        if (length <= 0) {
            length = 1;
        }

        page_data_length = length;
        // Toast.makeText(getActivity(), leger_id, Toast.LENGTH_SHORT).show();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _leger_id = RequestBody.create(MediaType.parse("multipart/form-data"), leger_id);
        Call<AssociateCommisionDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Assocaite_Commision_RESPONES_CALL(_assciate_no, _page, _length, _token, _leger_id);
        applicationsListResponesCall.enqueue(new Callback<AssociateCommisionDetailsResponse>() {
            @Override
            public void onResponse(Call<AssociateCommisionDetailsResponse> call, Response<AssociateCommisionDetailsResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        List<AssociateCom> associateCom = response.body().getResult().getAssociateCom();
                        if (response.body().getResult().getTotalCount() > 0) {
                            textViewNotFound.setVisibility(View.GONE);
                            name1.setVisibility(View.VISIBLE);
                            count1.setVisibility(View.VISIBLE);
                        } else {
                            textViewNotFound.setVisibility(View.VISIBLE);
                            name1.setVisibility(View.GONE);
                            count1.setVisibility(View.GONE);
                        }
                        if (associateCom.size() > 0) {
                            next.setEnabled(true);
                        } else {
                            next.setEnabled(false);
                            textViewNotFound.setVisibility(View.VISIBLE);
                        }
//                        int i = 1;
                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt) {
                            next.setEnabled(associateCom.size() == 15 ? true : false);
                        } else {
                            next.setEnabled(false);
                        }
                        Associate_Commission_details_Parent_models.clear();
                        for (AssociateCom associateComItem : associateCom) {
                            int i = (page_data_length * (page - 1)) + (Associate_Commission_details_Parent_models.size() + 1);
                            ArrayList<Associate_Commission_details_Child_Model> Associate_Commission_details_Child_Models = new ArrayList<>();
                            Associate_Commission_details_Child_Models.clear();
                            Associate_Commission_details_Child_Models.add(new Associate_Commission_details_Child_Model(associateComItem.getAssociate_name(), associateComItem.getAssociate_no(), associateComItem.getAssociate_carder(), associateComItem.getBranch_name(), associateComItem.getBranch_code().toString(), associateComItem.getTotal_amount(), associateComItem.getLedger_id().toString(),
                                    associateComItem.getMemberId().toString(),
                                    associateComItem.getPan_no(),
                                    associateComItem.getTotal_amount(),
                                    associateComItem.getTds_amount(),
                                    associateComItem.getFinal_total_amount(),
                                    associateComItem.getTotal_collection(),
                                    associateComItem.getFuel_amount(),
                                    associateComItem.getSsb_account(),
                                    associateComItem.getStatus(),
                                    associateComItem.getCreated_date()));


                            Associate_Commission_details_Parent_models.add(new Associate_Commission_details_Parent_model(String.valueOf(i), associateComItem.getAssociate_name(), associateComItem.getAssociate_no(), "", Associate_Commission_details_Child_Models));
//                            i++;
                        }
                        listAdapter.notifyDataSetChanged();
                    } else {
                        next.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    next.setEnabled(false);
                    textViewNotFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Data not found...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AssociateCommisionDetailsResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                next.setEnabled(false);
                textViewNotFound.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onEvent_View(String lagerid, String memberid, String type) {

        if (type.equals("Loan")) {
            Fragment fragment = new LoanCommsionDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("lagerid", lagerid);
            bundle.putString("memberid", memberid);
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Fragment fragment = new InvestmentCommisionDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("lagerid", lagerid);
            bundle.putString("memberid", memberid);
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

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
        final File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;
        try {
            int a = 1;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //workbook.createSheet("Report", 0);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            for (int i = 0; i < Associate_Commission_details_Parent_models.size(); i++) {
                Associate_Commission_details_Child_Model sub = Associate_Commission_details_Parent_models.get(i).getAssociate_commission_details_child_models().get(0);

                Label label0 = new Label(0, i + 1, sub.getAssociateName());
                Label label1 = new Label(1, i + 1, sub.getAssociateCode());
                Label label2 = new Label(2, i + 1, sub.getAssociateCader());
                Label label3 = new Label(3, i + 1, sub.getBranchName());
                Label label4 = new Label(4, i + 1, sub.getBranchCode());
                Label label5 = new Label(5, i + 1, sub.getCommissionPayment());

                try {
                    sheet.addCell(label0);
                    sheet.addCell(label1);
                    sheet.addCell(label2);
                    sheet.addCell(label3);
                    sheet.addCell(label4);
                    sheet.addCell(label5);

                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
            Label label1 = new Label(0, 0, "Associate Name");
            Label label2 = new Label(1, 0, "Associate Code");
            Label label3 = new Label(2, 0, "Associate Cader");
            Label label4 = new Label(3, 0, "Branch Name");
            Label label5 = new Label(4, 0, "Branch Code");
            Label label6 = new Label(5, 0, "Commisssion Payment");

            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);

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
    }
}
