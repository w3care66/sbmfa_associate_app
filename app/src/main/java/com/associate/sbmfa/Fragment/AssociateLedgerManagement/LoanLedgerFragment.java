package com.associate.sbmfa.Fragment.AssociateLedgerManagement;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Activity.login.LoginActivity;
import com.associate.sbmfa.BuildConfig;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Adapter.LoanLedgerList_Adapter;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model.LoanLedger_child_model;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model.LoanLedger_parent_model;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model.Plan_Ledger_child_model;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.LoanLedgerResponse;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.LoanLedgerResult;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.LoanLedgerTranscation;
import com.associate.sbmfa.Fragment.LoanManagment.Model.Lone_List_Child_model;
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


public class LoanLedgerFragment extends Fragment {
    View RootView;
    LoanLedgerList_Adapter listAdapter;
    ExpandableListView expListView;
    ArrayList<LoanLedger_parent_model> parent_models=new ArrayList<>();
    ArrayList<LoanLedger_parent_model> exportParent_models=new ArrayList<>();
    ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    int page = 1;
    String langht ="15";
    GoogleProgressDialog googleProgressDialog;
    String account_no;
    Button buttonSubmit;
    EditText editTextAc;
    ImageButton imageButtonPrv,imageButtonNext;
    Spinner spinnerLanght;
    TextView name1,count1,textViewNotFound,textView81;
    ConstraintLayout constraintLayoutFooter,conhead;
    boolean firstCase = false;
    ImageButton next,back;
    ImageView pdfdwon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_loan_ledger, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        conhead=RootView.findViewById(R.id.conhead);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        buttonSubmit = RootView.findViewById(R.id.imageView26);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        editTextAc = RootView.findViewById(R.id.editTextTextPersonName);
        spinnerLanght = RootView.findViewById(R.id.spinner2);
        textView81=RootView.findViewById(R.id.textView81);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        name1= RootView.findViewById(R.id.name1);
        count1= RootView.findViewById(R.id.count1);
        constraintLayoutFooter = RootView.findViewById(R.id.constraintLayout3);
        listAdapter = new LoanLedgerList_Adapter(getActivity(), parent_models);

        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        conhead.setVisibility(View.GONE);
        textView81.setVisibility(View.VISIBLE);
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page=page+1;
                // lengthValue=lengthValue * page;
                getLoanLegder(member_id,token,page,langht,account_no);
            }
        });

        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page = page - 1;
                    // lengthValue=lengthValue * page;
                    getLoanLegder(member_id,token,page,langht,account_no);
                }
            }
        });


       // getLoanLegder(member_id,token,page,langht,account_no);
        /*imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1){
                    page--;
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    getLoanLegder(member_id,token,page,langht,account_no);
                }else {
                }
            }
        });

        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                getLoanLegder(member_id,token,page,langht,account_no);
            }
        });*/

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account_no = editTextAc.getText().toString();

                if(account_no.equals("")){
                    conhead.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Please enter Account Number", Toast.LENGTH_SHORT).show();
                }else {
                    textView81.setVisibility(View.GONE);
                    conhead.setVisibility(View.VISIBLE);
                     getLoanLegder(member_id,token,page,langht,account_no);
                }

                page = 1;
                langht ="15";
                firstCase = false;
               // spinnerLanght.setSelection(0);
                parent_models.clear();
                listAdapter.notifyDataSetChanged();

            }
        });


       // getLoanLegder(member_id,token,page,langht,account_no);
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

        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    callLoanExportApi();
                }
            }
        });
        return RootView;
    }

    private void callLoanExportApi() {
        exportParent_models.clear();
        try {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf("1"));
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RequestBody _account_no = RequestBody.create(MediaType.parse("multipart/form-data"), account_no);
            RestHandler.getApiService().LOAN_LEDGER_RESPONSE_CALL(_member_id,_token,_page,_length,_account_no).enqueue(new Callback<LoanLedgerResponse>() {
                @Override
                public void onResponse(Call<LoanLedgerResponse> call, Response<LoanLedgerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            String id = null,trans = null,date = null;
                            LoanLedgerResult result  = response.body().getResult();
                            List<LoanLedgerTranscation> list = result.getTranscation();

                            if (list.size() > 0){
                                for (int i = 0;i<list.size(); i++){
                                    ArrayList<LoanLedger_child_model> child_models=new ArrayList<>();
                                    id = String.valueOf(i+1);
                                    trans = String.valueOf(list.get(i).getTranid());
                                    date = list.get(i).getDate();
                                    child_models.add(new LoanLedger_child_model(
                                            String.valueOf(list.get(i).getTranid()),
                                            list.get(i).getDate(),
                                            list.get(i).getPaymentMode(),
                                            list.get(i).getDescription(),
                                            list.get(i).getPenalty(),
                                            list.get(i).getReferenceNo(),
                                            list.get(i).getDeposit(),
                                            list.get(i).getRoiAmount(),
                                            list.get(i).getPrincipalAmount(),
                                            list.get(i).getOpeningBalance(),
                                            list.get(i).getJv_amount()
                                    ));
                                    exportParent_models.add(new LoanLedger_parent_model(String.valueOf(id),trans,date,"32211",child_models));
                                }
                                createExcelSheet();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoanLedgerResponse> call, Throwable t) {
                }
            });
        }catch (Exception e){e.printStackTrace();}
    }


    private void getLoanLegder(String member_id, String token, int page, String langht, String account_no) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langht);
            RequestBody _account_no = RequestBody.create(MediaType.parse("multipart/form-data"), account_no);
            RestHandler.getApiService().LOAN_LEDGER_RESPONSE_CALL(_member_id,_token,_page,_length,_account_no).enqueue(new Callback<LoanLedgerResponse>() {
                @Override
                public void onResponse(Call<LoanLedgerResponse> call, Response<LoanLedgerResponse> response) {
                    if (response.isSuccessful()) {
                        googleProgressDialog.dismiss();
                         if (response.body().getCode() == 200) {
                          if (response.body().getAssociateStatus() == 0){
                              AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                              dialog.checkStatus();
                           }

                            count1.setVisibility(View.VISIBLE);
                            name1.setVisibility(View.VISIBLE);
                                constraintLayoutFooter.setVisibility(View.VISIBLE);
                                textViewNotFound.setVisibility(View.GONE);
                                String id = null,trans = null,date = null;
                                LoanLedgerResult result  = response.body().getResult();
                                List<LoanLedgerTranscation> list = result.getTranscation();
                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt){
                                    imageButtonNext.setEnabled(list.size() == 15 ? true : false);
                                }else {
                                    imageButtonNext.setEnabled(false);
                                }
                                if (list.size() > 0){
                                    for (int i = 0;i<list.size(); i++){
                                        ArrayList<LoanLedger_child_model> child_models=new ArrayList<>();
                                        id = String.valueOf(i+1);
                                        trans = String.valueOf(list.get(i).getTranid());
                                        date = list.get(i).getDate();
                                        child_models.add(new LoanLedger_child_model(
                                                String.valueOf(list.get(i).getTranid()),
                                                list.get(i).getDate(),
                                                list.get(i).getPaymentMode(),
                                                list.get(i).getDescription(),
                                                list.get(i).getPenalty(),
                                                list.get(i).getReferenceNo(),
                                                list.get(i).getDeposit(),
                                                list.get(i).getRoiAmount(),
                                                list.get(i).getPrincipalAmount(),
                                                list.get(i).getOpeningBalance(),
                                                list.get(i).getJv_amount()
                                        ));
                                        parent_models.add(new LoanLedger_parent_model(String.valueOf(id),trans,date,"32211",child_models));
                                    }
                                    listAdapter.notifyDataSetChanged();
                                }else {
                                    constraintLayoutFooter.setVisibility(View.GONE);
                                    textViewNotFound.setVisibility(View.VISIBLE);
                                    count1.setVisibility(View.GONE);
                                    name1.setVisibility(View.GONE);

                                }
                            /*}else {
                                constraintLayoutFooter.setVisibility(View.GONE);
                                textViewNotFound.setVisibility(View.VISIBLE);
                            }*/
                        }else {
                            constraintLayoutFooter.setVisibility(View.GONE);
                            count1.setVisibility(View.GONE);
                            name1.setVisibility(View.GONE);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        count1.setVisibility(View.GONE);
                        name1.setVisibility(View.GONE);
                        constraintLayoutFooter.setVisibility(View.GONE);
                        googleProgressDialog.dismiss();
                        textViewNotFound.setVisibility(View.VISIBLE);

                    }
                }
                @Override
                public void onFailure(Call<LoanLedgerResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    constraintLayoutFooter.setVisibility(View.GONE);
                    count1.setVisibility(View.GONE);
                    name1.setVisibility(View.GONE);
                    textViewNotFound.setVisibility(View.VISIBLE);
                }
            });

        }catch (Exception ec){
            count1.setVisibility(View.GONE);
            name1.setVisibility(View.GONE);
            constraintLayoutFooter.setVisibility(View.GONE);
            textViewNotFound.setVisibility(View.VISIBLE);
            googleProgressDialog.dismiss();
        }
    }

    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }


    private void createExcelSheet() {
        String Fnamexls = "LoanAccount" + System.currentTimeMillis() + ".xls";
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
            workbook = Workbook.createWorkbook(file, wbSettings);
            //workbook.createSheet("Report", 0);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            for (int i = 0; i < exportParent_models.size(); i++) {
                LoanLedger_child_model sub = exportParent_models.get(i).getLoanLedger_child_models().get(0);
                //Label label0 = new Label(0,i+1,parent_models.get(i).getProgress());
                Label label0 = new Label(0, i + 1, sub.getTranid());
                Label label1 = new Label(1, i + 1, sub.getDate());
                Label label2 = new Label(2, i + 1, sub.getPaymentMode());
                Label label3 = new Label(3, i + 1, sub.getDescription());
                Label label4 = new Label(4, i + 1, sub.getPenalty());
                Label label5 = new Label(5, i + 1, sub.getDeposit());
                Label label8 = new Label(8, i + 1, sub.getOpening_balance());
                Label label9 = new Label(9, i + 1, sub.getJv_amount());

                Label label17 = new Label(0, 0, "Transaction ID");
                Label label18 = new Label(1, 0, "Date");
                Label label19 = new Label(2, 0, "Payment Mode");
                Label label20 = new Label(3, 0, "Description");
                Label label21 = new Label(4, 0, "Penalty");
                Label label22 = new Label(5, 0, "Deposit");
                Label label25 = new Label(8, 0, "Opening Balance");
                Label label26 = new Label(9, 0, "JV Amount");

                try {
                    sheet.addCell(label0);
                    sheet.addCell(label1);
                    sheet.addCell(label2);
                    sheet.addCell(label3);
                    sheet.addCell(label4);
                    sheet.addCell(label5);
//                    sheet.addCell(label6);
//                    sheet.addCell(label7);
                    sheet.addCell(label8);
                    sheet.addCell(label9);

                    sheet.addCell(label17);
                    sheet.addCell(label18);
                    sheet.addCell(label19);
                    sheet.addCell(label20);
                    sheet.addCell(label21);
                    sheet.addCell(label22);
//                    sheet.addCell(label23);
//                    sheet.addCell(label24);
                    sheet.addCell(label25);
                    sheet.addCell(label26);

                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }

            workbook.write();
            workbook.close();
            showExcelSheetDialog(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showExcelSheetDialog(final File file) {
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
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                        }

//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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