package com.associate.sbmfa.Fragment.AssociateManagement;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.InvestmentCommisionAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.InvestmentCommisionChildModel;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.InvestmentCommisionParentModel;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Ledger;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.MemberLegerResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.InvestmentLoanPlan.LoanAssociateCom;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.InvestmentLoanPlan.LoanCommsionResponse;
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


public class LoanCommsionDetailsFragment extends Fragment {
    View RootView;

    InvestmentCommisionAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<InvestmentCommisionParentModel> investmentCommisionParentModels = new ArrayList<>();
    Spinner spinner2, length;
    ArrayList<String> dateStrings = new ArrayList<>();
    ArrayList<String> lager_id_arr = new ArrayList<>();

    ImageView pdfdwon, imageViewSerach;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewBack;
    GoogleProgressDialog googleProgressDialog;
    int lengthValue = 15, page = 1;
    SessionManager sessionManager;
    String member_id = "", fromDateval = "", toDateval = "", associateId = "";
    HashMap<String, String> UserDataToken;
    String token = "", leger_id = "", member_idd = "";
    HashMap<String, String> UserData;
    ImageButton next, back;
    List<Ledger> ledgers = new ArrayList<>();
    Button apply;
    ArrayList<String> datelengthArr = new ArrayList<>();
    ArrayList<Member_Mangement_Parent_models> parent_models = new ArrayList<>();
    TextView textViewNotfound;
    File file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_loan_commsion_details, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!investmentCommisionParentModels.isEmpty()) {
                    createExcelSheet();
                }
            }
        });
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        next = RootView.findViewById(R.id.imageButton2);
        back = RootView.findViewById(R.id.imageButton);
        apply = RootView.findViewById(R.id.apply);
        length = RootView.findViewById(R.id.length);
        textViewNotfound = RootView.findViewById(R.id.textView60);
        googleProgressDialog = new GoogleProgressDialog(getActivity());
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        leger_id = getArguments().getString("lagerid");
        member_idd = getArguments().getString("memberid");
        getMemberList(member_id, lengthValue, page, token, leger_id, member_idd);
        /*datelengthArr.add(String.valueOf(lengthValue));
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
                    lengthValue = position * 10;
                }else{
                    lengthValue =10;
                }
                getMemberList(member_id,lengthValue,page,token,leger_id,member_idd);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                getMemberList(member_id, lengthValue, page, token, leger_id, member_idd);
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
                getMemberList(member_id, lengthValue, page, token, associateId, member_idd);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    page--;
                    getMemberList(member_id, lengthValue, page, token, leger_id, member_idd);
                }
            }
        });

        dateStrings.add("Select Commission Ledger");
        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinner2.setAdapter(adapterselectDate);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    leger_id = ledgers.get(position - 1).getLedgerId().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return RootView;
    }

    public void getLegerList(final String assciate_no, String token) {
        googleProgressDialog.show1("Loading...");
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), assciate_no);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<MemberLegerResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Leger_List_RESPONES_CALL(_assciate_no, _token);
        applicationsListResponesCall.enqueue(new Callback<MemberLegerResponse>() {
            @Override
            public void onResponse(Call<MemberLegerResponse> call, Response<MemberLegerResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            sessionManager.logoutUser();
                        }
                        // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        ledgers = response.body().getResult().getLedger();
                        int i = 1;
                        for (Ledger memberItem : ledgers) {
                            dateStrings.add(memberItem.getDate());
                            lager_id_arr.add(memberItem.getLedgerId().toString());
                        }

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberLegerResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getMemberList(final String member_id, int length, int page, String token, String leger_id, String member_idd) {
        googleProgressDialog.show1("Loading...");
        parent_models.clear();
        if (page <= 0) {
            page = 1;
        }
        if (length <= 0) {
            length = 1;
        }
        // Toast.makeText(getActivity(), leger_id, Toast.LENGTH_SHORT).show();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(page));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _leger_id = RequestBody.create(MediaType.parse("multipart/form-data"), leger_id);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_idd);
        Call<LoanCommsionResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Loan_Commision_RESPONES_CALL(_assciate_no, _page, _length, _token, _leger_id, _member_id);
        applicationsListResponesCall.enqueue(new Callback<LoanCommsionResponse>() {
            @Override
            public void onResponse(Call<LoanCommsionResponse> call, Response<LoanCommsionResponse> response) {
                googleProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        textViewNotfound.setVisibility(View.GONE);
                        List<LoanAssociateCom> associateCom = response.body().getResult().getAssociateCom();
                        next.setEnabled(true);
                        int totalcount = response.body().getResult().getTotalCount();
                        int lenght = Integer.parseInt(response.body().getResult().getLength());
                        int page = Integer.parseInt(response.body().getResult().getPage());
                        int clickButNxt = lenght * page;
                        if (totalcount > clickButNxt) {
                            next.setEnabled(associateCom.size() == 15 ? true : false);
                        } else {
                            next.setEnabled(false);
                        }

                        if (associateCom.size() > 0) {
                            int i = 1;
                            investmentCommisionParentModels.clear();

                            for (LoanAssociateCom item : associateCom) {
                                ArrayList<InvestmentCommisionChildModel> investmentCommisionChildModels = new ArrayList<>();
                                investmentCommisionChildModels.clear();
                                investmentCommisionChildModels.add(new InvestmentCommisionChildModel(item.getCreatedAt(), item.getAccount(), item.getLoanType(), item.getTotalAmount(), item.getCommissionAmount(), item.getPercentage(), item.getCarderName(), "", item.getTotalAmount(), item.getCommissionType(), "", item.getPayType(), item.getIsDistribute()));
                                investmentCommisionParentModels.add(new InvestmentCommisionParentModel(String.valueOf(i), item.getCreatedAt(), item.getAccount(), item.getPayType(), investmentCommisionChildModels));

                                i++;

                            }
                            listAdapter = new InvestmentCommisionAdapter(getActivity(), investmentCommisionParentModels, "1");
                            expListView.setAdapter(listAdapter);
                            DisplayMetrics metrics = new DisplayMetrics();
                            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                            int width = metrics.widthPixels;
                            expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

                        } else {
                            textViewNotfound.setVisibility(View.VISIBLE);
                            next.setEnabled(false);
                        }
                    } else {
                        next.setEnabled(false);
                        textViewNotfound.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    next.setEnabled(false);
                    textViewNotfound.setVisibility(View.VISIBLE);
                    // Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoanCommsionResponse> call, Throwable t) {
                next.setEnabled(false);
                googleProgressDialog.dismiss();
                textViewNotfound.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    private void createExcelSheet() {
        getMemberList2(member_id, lengthValue, page, token, leger_id, member_idd);
    }

    public void getMemberList2(final String member_id, int length, int page, String token, String leger_id, String member_idd) {
        googleProgressDialog.show1("Loading...");
        parent_models.clear();
        if (page <= 0) {
            page = 1;
        }
        if (length <= 0) {
            length = 1;
        }
        // Toast.makeText(getActivity(), leger_id, Toast.LENGTH_SHORT).show();
        RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(length));
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf("0"));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _leger_id = RequestBody.create(MediaType.parse("multipart/form-data"), leger_id);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_idd);
        Call<LoanCommsionResponse> applicationsListResponesCall = RestHandler.getApiService().Member_Loan_Commision_RESPONES_CALL(_assciate_no, _page, _length, _token, _leger_id, _member_id);
        applicationsListResponesCall.enqueue(new Callback<LoanCommsionResponse>() {
            @Override
            public void onResponse(Call<LoanCommsionResponse> call, Response<LoanCommsionResponse> response) {
                googleProgressDialog.dismiss();
                ArrayList<InvestmentCommisionParentModel> investmentCommisionParentModels = new ArrayList<>();
                if (response != null) {
                    if (response.body().getCode() == 200) {
                        if (response.body().getAssociateStatus() == 0) {
                            Toast.makeText(getActivity(), "You don't have permission to access this app. Please contact admin", Toast.LENGTH_SHORT).show();
                            sessionManager.logoutUser();
                        }
                        textViewNotfound.setVisibility(View.GONE);
                        List<LoanAssociateCom> associateCom = response.body().getResult().getAssociateCom();
                        if (associateCom.size() > 0) {
                            int ii = 1;
                            for (LoanAssociateCom item : associateCom) {
                                ArrayList<InvestmentCommisionChildModel> investmentCommisionChildModels = new ArrayList<>();
                                investmentCommisionChildModels.clear();
                                investmentCommisionChildModels.add(new InvestmentCommisionChildModel(item.getCreatedAt(), item.getAccount(), item.getPayType(), item.getTotalAmount(), item.getCommissionAmount(), item.getPercentage(), item.getCarderName(), "", item.getTotalAmount(), item.getCommissionType(), "", item.getPayType(), item.getIsDistribute()));
                                investmentCommisionParentModels.add(new InvestmentCommisionParentModel(String.valueOf(ii), item.getCreatedAt(), item.getAccount(), item.getPayType(), investmentCommisionChildModels));

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
                            file = new File(directory, Fnamexls);

                            WorkbookSettings wbSettings = new WorkbookSettings();
                            wbSettings.setLocale(new Locale("en", "EN"));
                            WritableWorkbook workbook;
                            try {
                                int a = 1;
                                workbook = Workbook.createWorkbook(file, wbSettings);
                                //workbook.createSheet("Report", 0);
                                WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                                for (int i = 0; i < investmentCommisionParentModels.size(); i++) {
                                    InvestmentCommisionChildModel sub = investmentCommisionParentModels.get(i).get_investment_commision_child_model().get(0);

                                    Label label0 = new Label(0, i + 1, sub.getPlanName());
                                    Label label1 = new Label(1, i + 1, sub.getCoommisionAmount());
                                    Label label2 = new Label(2, i + 1, sub.getPercentage());
                                    Label label3 = new Label(3, i + 1, sub.getCarderName());
                                    Label label4 = new Label(4, i + 1, sub.getTotalAmount());
                                    Label label5 = new Label(5, i + 1, sub.getCommisionType());
                                    Label label6 = new Label(6, i + 1, sub.getPaymentType());
                                    Label label7 = new Label(7, i + 1, sub.getPaymentDistribute());


                                    try {
                                        sheet.addCell(label0);
                                        sheet.addCell(label1);
                                        sheet.addCell(label2);
                                        sheet.addCell(label3);
                                        sheet.addCell(label4);
                                        sheet.addCell(label5);
                                        sheet.addCell(label6);
                                        sheet.addCell(label7);

                                    } catch (WriteException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Label label1 = new Label(0, 0, "Plan Name");
                                Label label2 = new Label(1, 0, "Commission Amount");
                                Label label3 = new Label(2, 0, "Percentage");
                                Label label4 = new Label(3, 0, "Carder Name");
                                Label label5 = new Label(4, 0, "Total Amount");
                                Label label6 = new Label(5, 0, "Commission Type");
                                Label label7 = new Label(6, 0, "Payment Type");
                                Label label8 = new Label(7, 0, "Payment Distribute");


                                sheet.addCell(label1);
                                sheet.addCell(label2);
                                sheet.addCell(label3);
                                sheet.addCell(label4);
                                sheet.addCell(label5);
                                sheet.addCell(label6);
                                sheet.addCell(label7);
                                sheet.addCell(label8);


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
            public void onFailure(Call<LoanCommsionResponse> call, Throwable t) {
                next.setEnabled(false);
                googleProgressDialog.dismiss();
                textViewNotfound.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
