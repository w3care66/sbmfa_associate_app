package com.associate.sbmfa.Fragment.LoanManagment;

import android.app.AlertDialog;
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

import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.LoanManagment.Adapter.GrouploanListDetailsAdapter;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.GroupLoanDetailsFragment;
import com.associate.sbmfa.Fragment.LoanManagment.Model.LoneListParent_model;
import com.associate.sbmfa.Fragment.LoanManagment.Model.Lone_List_Child_model;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.GroupLoanDetailResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.MemberGroupLoan;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.ResultGroupLoan;
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

public class GrouploanDetailsListFragment extends Fragment implements GrouploanListDetailsAdapter.EventListener {
    View RootView;
    GrouploanListDetailsAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<LoneListParent_model> parent_models = new ArrayList<>();
    ImageView imageViewBack, pdfdwon;
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
    File file;
    TextView count1, name1, textViewNotFound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_group_loan_details, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        count1 = RootView.findViewById(R.id.count1);
        name1 = RootView.findViewById(R.id.name1);

        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                }

            }
        });
        spinnerLanght = RootView.findViewById(R.id.spinner26);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        parent_models.clear();
       /*
        dateStrings.add("10");
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
                getLoneList(member_id,token,String.valueOf(page),langht);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        getLoneList(member_id, token, String.valueOf(page), langht);
        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    page--;
                    getLoneList(member_id, token, String.valueOf(page), langht);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                getLoneList(member_id, token, String.valueOf(page), langht);
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


    private void getLoneList(String member_id, String token, String pages, String langhts) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RestHandler.getApiService().GROUP_LONE_LIST_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _page,
                    _length
            ).enqueue(new Callback<GroupLoanDetailResponse>() {
                @Override
                public void onResponse(Call<GroupLoanDetailResponse> call, Response<GroupLoanDetailResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                            googleProgressDialog.dismiss();
                            String id = null, name = null, member_id = null;
                            ResultGroupLoan result = response.body().getResult();
                            List<MemberGroupLoan> members = result.getMember();
                            int totalcount = response.body().getResult().getTotalCount();
                            int lenght = Integer.parseInt(response.body().getResult().getLength());
                            int page = Integer.parseInt(response.body().getResult().getPage());
                            int clickButNxt = lenght * page;
                            if (totalcount > clickButNxt) {
                                if (members.size() == 15) {
                                    imageButtonNext.setEnabled(true);
                                } else {
                                    imageButtonNext.setEnabled(false);
                                }
                            } else {
                                imageButtonNext.setEnabled(false);
                            }
                            if (members.size() > 0) {
                                textViewNotFound.setVisibility(View.GONE);
                                expListView.setVisibility(View.VISIBLE);
                                name1.setVisibility(View.VISIBLE);
                                count1.setVisibility(View.VISIBLE);
                                for (int i = 0; i < members.size(); i++) {
                                    ArrayList<Lone_List_Child_model> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = members.get(i).getAccountNumber();
                                    member_id = String.valueOf(members.get(i).getMemberName());
                                    child_models.add(new Lone_List_Child_model(
                                            String.valueOf(members.get(i).getId()),
                                            members.get(i).getAccountNumber(),
                                            members.get(i).getApplicantId(),
                                            members.get(i).getBrName(),
                                            String.valueOf(members.get(i).getBrCode()),
                                            members.get(i).getSoName(),
                                            members.get(i).getRoName(),
                                            members.get(i).getZoName(),
                                            members.get(i).getMemberId(),
                                            members.get(i).getAccountNumber(),
                                            members.get(i).getLastRecoveryDate(),
                                            members.get(i).getAssociateCode(),
                                            members.get(i).getAssociateName(),
                                            members.get(i).getLoanType(),
                                            members.get(i).getAmount(),
                                            members.get(i).getStatus(),
                                            members.get(i).getApprovedDate(),
                                            members.get(i).getApplicationDate(),
                                            "",
                                            members.get(i).getTenure(),
                                            members.get(i).getOut_standing_amount().toString(),
                                            members.get(i).getTransfer_amount(),
                                            members.get(i).getLoan_amount(),
                                            members.get(i).getFile_charge(),
                                            members.get(i).getPayment_mode(),
                                            String.valueOf(members.get(i).getMember_loan_id()),
                                            members.get(i).getTotal_payment().toString())
                                    );
                                    parent_models.add(new LoneListParent_model(id, name, member_id, "", child_models));
                                }
                                listAdapter = new GrouploanListDetailsAdapter(getActivity(), parent_models, GrouploanDetailsListFragment.this);
                                expListView.setAdapter(listAdapter);
                                DisplayMetrics metrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                int width = metrics.widthPixels;
                                expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                            } else {
                                listAdapter = new GrouploanListDetailsAdapter(getActivity(), parent_models, GrouploanDetailsListFragment.this);
                                expListView.setAdapter(listAdapter);
                                DisplayMetrics metrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                int width = metrics.widthPixels;
                                expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                                textViewNotFound.setVisibility(View.VISIBLE);
                                name1.setVisibility(View.GONE);
                                count1.setVisibility(View.GONE);
                            }
                        } else {
                            imageButtonNext.setEnabled(false);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            name1.setVisibility(View.GONE);
                            count1.setVisibility(View.GONE);
                            textViewNotFound.setText(response.body().getMessages());
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        imageButtonNext.setEnabled(false);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        name1.setVisibility(View.GONE);
                        count1.setVisibility(View.GONE);
                        googleProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<GroupLoanDetailResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    textViewNotFound.setVisibility(View.VISIBLE);
                    name1.setVisibility(View.GONE);
                    count1.setVisibility(View.GONE);
                }
            });
        } catch (Exception ex) {

        }

    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onEvent_View(String id, String memberid, String type) {
        //  Toast.makeText(getContext(), "Work in progresss...", Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        fragment = new GroupLoanDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void createExcelSheet() {
        Log.e("cdjkfpog", parent_models.size() + "");
        getLoneList2(member_id, token, String.valueOf(page), langht);

    }

    private void getLoneList2(String member_id, String token, String pages, String langhts) {
        try {
            googleProgressDialog.show1("Loading data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RestHandler.getApiService().GROUP_LONE_LIST_RESPONSE_CALL(
                    _member_id,
                    _token,
                    _page,
                    _length
            ).enqueue(new Callback<GroupLoanDetailResponse>() {
                @Override
                public void onResponse(Call<GroupLoanDetailResponse> call, Response<GroupLoanDetailResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                            googleProgressDialog.dismiss();
                            String id = null, name = null, member_id = null;
                            ResultGroupLoan result = response.body().getResult();
                            List<MemberGroupLoan> members = result.getMember();
                            ArrayList<LoneListParent_model> parent_models = new ArrayList<>();
                            if (members.size() > 0) {
                                textViewNotFound.setVisibility(View.GONE);
                                expListView.setVisibility(View.VISIBLE);
                                name1.setVisibility(View.VISIBLE);
                                count1.setVisibility(View.VISIBLE);
                                for (int i = 0; i < members.size(); i++) {
                                    ArrayList<Lone_List_Child_model> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = members.get(i).getAccountNumber();
                                    member_id = String.valueOf(members.get(i).getMemberName());
                                    child_models.add(new Lone_List_Child_model(
                                            String.valueOf(members.get(i).getId()),
                                            String.valueOf(members.get(i).getGroupLoanId()),
                                            members.get(i).getApplicantId(),
                                            members.get(i).getBrName(),
                                            String.valueOf(members.get(i).getBrCode()),
                                            members.get(i).getSoName(),
                                            members.get(i).getRoName(),
                                            members.get(i).getZoName(),
                                            members.get(i).getMemberId(),
                                            members.get(i).getMemberName(),
                                            members.get(i).getLastRecoveryDate(),
                                            members.get(i).getAssociateCode(),
                                            members.get(i).getAssociateName(),
                                            members.get(i).getLoanType(),
                                            members.get(i).getAmount(),
                                            members.get(i).getStatus(),
                                            members.get(i).getApprovedDate(),
                                            members.get(i).getApplicationDate(),
                                            String.valueOf(members.get(i).getGroupLoanId()),
                                            members.get(i).getTransfer_amount(),
                                            members.get(i).getLoan_amount(),
                                            members.get(i).getFile_charge(),
                                            members.get(i).getTenure(),
                                            members.get(i).getOut_standing_amount().toString(),
                                            members.get(i).getPayment_mode(),
                                            String.valueOf(members.get(i).getMember_loan_id()),
                                            members.get(i).getPayment_mode().toString()
                                    ));
                                    parent_models.add(new LoneListParent_model(id, name, member_id, "", child_models));
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
                                file = new File(directory, Fnamexls);

                                WorkbookSettings wbSettings = new WorkbookSettings();

                                wbSettings.setLocale(new Locale("en", "EN"));

                                WritableWorkbook workbook;
                                try {
                                    int a = 1;
                                    workbook = Workbook.createWorkbook(file, wbSettings);
                                    //workbook.createSheet("Report", 0);
                                    WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                                    for (int i = 0; i < parent_models.size(); i++) {
                                        Lone_List_Child_model sub = parent_models.get(i).getChild_models().get(0);
                                        Label label0 = new Label(0, i + 1, sub.getBr_name());
                                        Label label1 = new Label(1, i + 1, sub.getBr_code());
                                        Label label2 = new Label(2, i + 1, sub.getSo_name());
                                        Label label3 = new Label(3, i + 1, sub.getRo_name());
                                        Label label4 = new Label(4, i + 1, sub.getZo_name());
                                        Label label5 = new Label(5, i + 1, sub.getMember_id());
                                        Label label6 = new Label(6, i + 1, sub.getAccount_number());
                                        Label label7 = new Label(7, i + 1, sub.getLast_recovery_date());
                                        Label label8 = new Label(8, i + 1, sub.getAssociate_code());
                                        Label label9 = new Label(9, i + 1, sub.getAssociate_name());
                                        Label label10 = new Label(10, i + 1, sub.getLoan_type());
                                        Label label11 = new Label(11, i + 1, sub.getTransfer_amount());
                                        Label label12 = new Label(12, i + 1, sub.getFile_charge());
                                        Label label13 = new Label(13, i + 1, sub.getLoan_amount());
                                        Label label14 = new Label(14, i + 1, sub.getStatus());
                                        Label label15 = new Label(15, i + 1, sub.getApproved_date());
                                        Label label16 = new Label(16, i + 1, sub.getApplication_date());

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

                                        } catch (WriteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Label label1 = new Label(0, 0, "BR Name");
                                    Label label2 = new Label(1, 0, "BR code");
                                    Label label3 = new Label(2, 0, "SO Name");
                                    Label label4 = new Label(3, 0, "RO Name");
                                    Label label5 = new Label(4, 0, "ZO Name");
                                    Label label6 = new Label(5, 0, "Member Id");
                                    Label label7 = new Label(6, 0, "Account Number");
                                    Label label8 = new Label(7, 0, "Last Recovery Date");
                                    Label label9 = new Label(8, 0, "Associate Code");
                                    Label label10 = new Label(9, 0, "Associate Name");
                                    Label label11 = new Label(10, 0, "Loan Type");
                                    Label label12 = new Label(11, 0, "Transfer Amount");
                                    Label label13 = new Label(12, 0, "File charges");
                                    Label label14 = new Label(13, 0, "Loan Amount");
                                    Label label15 = new Label(14, 0, "Status");
                                    Label label16 = new Label(15, 0, "Approved Date");
                                    Label label17 = new Label(16, 0, "Application Date");

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
                public void onFailure(Call<GroupLoanDetailResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();

                }
            });
        } catch (Exception ex) {

        }

    }
}
