package com.associate.sbmfa.Fragment.ReportManagment;

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
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Model.Lone_List_Child_model;
import com.associate.sbmfa.Fragment.ReportManagment.Adapter.AssociateMaturityReportDetailsAdapter;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateMaturityReportChild_model;
import com.associate.sbmfa.Fragment.ReportManagment.Model.AssociateMaturityReportParent_model;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Matuirty.AssociateMaturityResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Matuirty.Maturity;
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

public class MaturityReportsFragment extends Fragment {
    View RootView;
    AssociateMaturityReportDetailsAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateMaturityReportParent_model> parent_models = new ArrayList<>();
    ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id = "";
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    GoogleProgressDialog googleProgressDialog;
    int page = 1;
    String langht = "15";
    ImageButton imageButtonPrv, imageButtonNext;
    ImageView pdfdwon;
    Spinner spinnerLanght;
    ArrayList<String> dateStrings = new ArrayList<>();
    TextView textViewNotFound;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewSerach;
    ArrayList<String> dateStringType = new ArrayList<>();
    Spinner spinnerType;
    String type = "2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_maturity_reports, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
       /* spinnerLanght = RootView.findViewById(R.id.spinner26);
        spinnerType = RootView.findViewById(R.id.spinner2);*/
        spinnerType = RootView.findViewById(R.id.spinner2);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        parent_models.clear();
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                }
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
                getAssociateBusinessList(member_id,token,String.valueOf(page),langht,type);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        getAssociateBusinessList(member_id, token, String.valueOf(page), langht, type);
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    page--;
                    getAssociateBusinessList(member_id, token, String.valueOf(page), langht, type);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                getAssociateBusinessList(member_id, token, String.valueOf(page), langht, type);
            }
        });

        dateStringType.add("Select Status");
        dateStringType.add("Pending");
        dateStringType.add("Paid");
        ArrayAdapter<String> adapterselectType = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStringType);
        adapterselectType.setDropDownViewResource(R.layout.spiner_item);
        spinnerType.setAdapter(adapterselectType);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    type = String.valueOf(position);
                    getAssociateBusinessList(member_id, token, String.valueOf(page), langht, type);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageViewSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSerach == false) {
                    imageViewSerach.setImageResource(R.drawable.ic_baseline_close_24);
                    constraintLayoutOptions.setVisibility(View.VISIBLE);
                    isSerach = true;
                    spinnerType.setSelection(2);
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

        return RootView;
    }
    private void getAssociateBusinessList(String member_id, String token, String pages, String langhts, String type) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), type);
            RestHandler.getApiService().ASSOCIATE_MATUIRTY_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _page,
                    _length,
                    _type
            ).enqueue(new Callback<AssociateMaturityResponse>() {
                @Override
                public void onResponse(Call<AssociateMaturityResponse> call, Response<AssociateMaturityResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                            googleProgressDialog.dismiss();
                            imageButtonNext.setEnabled(true);
                            List<Maturity> maturity = response.body().getResult().getMaturity();
                            int totalcount = response.body().getResult().getTotalCount();
                            int lenght = Integer.parseInt(response.body().getResult().getLength());
                            int page = Integer.parseInt(response.body().getResult().getPage());
                            int clickButNxt = lenght * page;
                            if (totalcount > clickButNxt) {
                                imageButtonNext.setEnabled(maturity.size() == 15 ? true : false);
                            } else {
                                imageButtonNext.setEnabled(false);
                            }
                            int i = 0;
                            for (Maturity item : maturity) {
                                i++;
                                ArrayList<AssociateMaturityReportChild_model> associateMaturityReportChild_models = new ArrayList<>();
                                associateMaturityReportChild_models.add(new AssociateMaturityReportChild_model(
                                        item.getId().toString(),
                                        item.getBranch_code().toString(),
                                        item.getBranch_name().toString(),
                                        item.getAccount_no().toString(),
                                        item.getMember_name().toString(),
                                        item.getMember_id().toString(),
                                        item.getPlan_name().toString(),
                                        item.getDeno().toString(),
                                        item.getMaturity_amount().toString(),
                                        item.getAssociate_code().toString(),
                                        item.getAssociate_name().toString(),
                                        item.getOpening_date().toString(),
//                                        item.getDue_amount().toString(),
                                        item.getTotal_amount().toString(),
                                        item.getMaturity_payable_amount().toString(),
                                        item.getTds_amount().toString(),
                                        item.getDeposit_amount().toString(),
                                        item.getInterest().toString(),
//                                        item.getFinal_amount().toString(),
                                        item.getMaturity_type().toString(),
                                        item.getMaturity_date().toString(),
                                        item.getTenure().toString(),
                                        item.getStatus().toString(),
                                        item.getPayment_mode().toString(),
                                        item.getCheque_no().toString(),
                                        item.getSsb_ac().toString(),
                                        item.getBank_name().toString(),
                                        item.getBank_ac().toString(),
                                        item.getRtgs_chrg().toString(),
                                        item.getPayment_date()));

                                parent_models.add(new AssociateMaturityReportParent_model(String.valueOf(i), item.getMember_name().toString(), item.getMember_id().toString(), associateMaturityReportChild_models));
                            }
                            listAdapter = new AssociateMaturityReportDetailsAdapter(getActivity(), parent_models);
                            expListView.setAdapter(listAdapter);
                            DisplayMetrics metrics = new DisplayMetrics();
                            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                            int width = metrics.widthPixels;
                            expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

                        } else {
                            imageButtonNext.setEnabled(false);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            textViewNotFound.setText("Data not found");
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        textViewNotFound.setText("Data not found..");
                        googleProgressDialog.dismiss();
                        // Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<AssociateMaturityResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    imageButtonNext.setEnabled(false);
                    textViewNotFound.setVisibility(View.VISIBLE);
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
        googleProgressDialog.show1("Export data....");
        final ArrayList<AssociateMaturityReportParent_model> parentModels = new ArrayList<>();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), type);
        RestHandler.getApiService().ASSOCIATE_MATUIRTY_RESPONSE_CALL(
                _member_id,
                _token,
                _page,
                _length,
                _type
        ).enqueue(new Callback<AssociateMaturityResponse>() {

            @Override
            public void onResponse(Call<AssociateMaturityResponse> call, Response<AssociateMaturityResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        List<Maturity> maturity = response.body().getResult().getMaturity();
                        int i = 0;
                        for (Maturity item : maturity) {
                            i++;
                            ArrayList<AssociateMaturityReportChild_model> associateMaturityReportChild_models = new ArrayList<>();
                            associateMaturityReportChild_models.add(new AssociateMaturityReportChild_model(
                                    item.getId().toString(),
                                    item.getBranch_code().toString(),
                                    item.getBranch_name().toString(),
                                    item.getAccount_no().toString(),
                                    item.getMember_name().toString(),
                                    item.getMember_id().toString(),
                                    item.getPlan_name().toString(),
                                    item.getDeno().toString(),
                                    item.getMaturity_amount().toString(),
                                    item.getAssociate_code().toString(),
                                    item.getAssociate_name().toString(),
                                    item.getOpening_date().toString(),
//                                        item.getDue_amount().toString(),
                                    item.getTotal_amount().toString(),
                                    item.getMaturity_payable_amount().toString(),
                                    item.getTds_amount().toString(),
                                    item.getDeposit_amount().toString(),
                                    item.getInterest().toString(),
//                                        item.getFinal_amount().toString(),
                                    item.getMaturity_type().toString(),
                                    item.getMaturity_date().toString(),
                                    item.getTenure().toString(),
                                    item.getStatus().toString(),
                                    item.getPayment_mode().toString(),
                                    item.getCheque_no().toString(),
                                    item.getSsb_ac().toString(),
                                    item.getBank_name().toString(),
                                    item.getBank_ac().toString(),
                                    item.getRtgs_chrg().toString(),
                                    item.getPayment_date()));

                            parentModels.add(new AssociateMaturityReportParent_model(String.valueOf(i), item.getMember_name().toString(), item.getMember_id().toString(), associateMaturityReportChild_models));
                        }
                        showOpenExcelSheet(parentModels);
                    }
                }

            }

            @Override
            public void onFailure(Call<AssociateMaturityResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
            }
        });
    }

    private void showOpenExcelSheet(ArrayList<AssociateMaturityReportParent_model> parentModels) {
        try {
            String Fnamexls = "MexcelSheet" + System.currentTimeMillis() + ".xls";
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
                for (int i = 0; i < parentModels.size(); i++) {
                    AssociateMaturityReportChild_model sub = parentModels.get(i).getChild_models().get(0);
                    //Label label0 = new Label(0,i+1,parent_models.get(i).getProgress());
                    Label label0 = new Label(0, i + 1, sub.getMember_id());
                    Label label1 = new Label(1, i + 1, sub.getAccount_no());
                    Label label2 = new Label(2, i + 1, sub.getMember_name());
                    Label label3 = new Label(3, i + 1, sub.getPlan_name());
                    Label label4 = new Label(4, i + 1, sub.getTenure());
                    Label label5 = new Label(5, i + 1, sub.getDeno());
                    Label label6 = new Label(6, i + 1, sub.getDeposit_amount());
                    Label label7 = new Label(7, i + 1, sub.getMaturity_payable_amount());
                    Label label8 = new Label(8, i + 1, sub.getTds_amount());
                    Label label9 = new Label(9, i + 1, sub.getOpening_date());
                    Label label10 = new Label(10, i + 1, sub.getMaturity_date());
                    Label label11 = new Label(11, i + 1, sub.getPayment_date());
                    Label label12 = new Label(12, i + 1, sub.getPayment_mode());
                    Label label13 = new Label(13, i + 1, sub.getCheque_no());
                    Label label14 = new Label(14, i + 1, sub.getRtgs_chrg());
                    Label label15 = new Label(15, i + 1, sub.getSsb_ac());
                    Label label16 = new Label(16, i + 1, sub.getBank_name());
                    Label label17 = new Label(17, i + 1, sub.getBank_ac());
                    Label label18 = new Label(18, i + 1, sub.getAssociate_code());
                    Label label19 = new Label(19, i + 1, sub.getAssociate_name());
                    Label label20 = new Label(20, i + 1, sub.getInterest());

                    Label label31 = new Label(0, 0, "Member ID");
                    Label label32 = new Label(1, 0, "Account No");
                    Label label33 = new Label(2, 0, "Member Name");
                    Label label34 = new Label(3, 0, "Plan");
                    Label label35 = new Label(4, 0, "Tenure");
                    Label label36 = new Label(5, 0, "Deno");
                    Label label37 = new Label(6, 0, "Deposit Amount");
                    Label label38 = new Label(7, 0, "Maturity Payable Amount");
                    Label label39 = new Label(8, 0, "TDS Amount");
                    Label label40 = new Label(9, 0, "Opening Date");
                    Label label41 = new Label(10, 0, "Maturity Date");
                    Label label42 = new Label(11, 0, "Payment Date");
                    Label label43 = new Label(12, 0, "Payment Mode");
                    Label label44 = new Label(13, 0, "Cheque No/RTGS No");
                    Label label45 = new Label(14, 0, "RTGS Charge");
                    Label label46 = new Label(15, 0, "SSB Account No");
                    Label label47 = new Label(16, 0, "Bank Name");
                    Label label48 = new Label(17, 0, "Bank Account Number");
                    Label label49 = new Label(18, 0, "Associate Code");
                    Label label50 = new Label(19, 0, "Associate Name");
                    Label label51 = new Label(20, 0, "Interest");
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
        }catch (Exception e){e.printStackTrace();}
    }

}