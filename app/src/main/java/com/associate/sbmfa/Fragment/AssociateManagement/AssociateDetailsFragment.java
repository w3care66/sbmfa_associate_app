package com.associate.sbmfa.Fragment.AssociateManagement;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_Details_ListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_details_Child_model;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_details_Parent_model;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Respones.AssociateListResponse;
import com.associate.sbmfa.Respones.AssociateDetailsMember;
import com.associate.sbmfa.Respones.ResultAssociateDetails;
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

public class AssociateDetailsFragment extends Fragment implements Associate_Details_ListAdapter.EventListener {
    View Rootview;
    ExpandableListView expListView;
    ArrayList<Associate_details_Parent_model> parent_models = new ArrayList<>();
    Associate_Details_ListAdapter listAdapter;
    ImageView im_download, imageViewBack;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Rootview = inflater.inflate(R.layout.fragment_associate_details, container, false);
        expListView = (ExpandableListView) Rootview.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        im_download = Rootview.findViewById(R.id.im_download);
        im_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                }

            }
        });
        spinnerLanght = Rootview.findViewById(R.id.spinner2);
        imageButtonPrv = Rootview.findViewById(R.id.imageButton);
        imageButtonNext = Rootview.findViewById(R.id.imageButton2);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        parent_models.clear();

        /*dateStrings.clear();
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
                parent_models.clear();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        getAssociateList(member_id, token, String.valueOf(page), langht);


        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 1) {
                    page--;
                    parent_models.clear();
                    getAssociateList(member_id, token, String.valueOf(page), langht);
                } else {
                }
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                parent_models.clear();
                getAssociateList(member_id, token, String.valueOf(page), langht);
            }
        });

        imageViewBack = Rootview.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        Rootview.setFocusableInTouchMode(true);
        Rootview.requestFocus();
        Rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return Rootview;
    }

    private void getAssociateList(final String member_id, String token, String page, String length) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), page);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), length);
            RestHandler.getApiService().ASSOCIATE_LIST_RESPONSE_CALL(_member_id, _token, _page, _length).enqueue(new Callback<AssociateListResponse>() {
                @Override
                public void onResponse(Call<AssociateListResponse> call, Response<AssociateListResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            googleProgressDialog.dismiss();
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
                            // if (response.body().getAssociateStatus() == 1){
                            String id = null, name = null, member_id = null;
                            ResultAssociateDetails resultAssociateDetails = response.body().getResult();
                            List<AssociateDetailsMember> memberList = resultAssociateDetails.getMember();
                            if (memberList.size() > 0) {
                                imageButtonNext.setEnabled(true);

                                int totalcount = response.body().getResult().getTotalCount();
                                int lenght = Integer.parseInt(response.body().getResult().getLength());
                                int page = Integer.parseInt(response.body().getResult().getPage());
                                int clickButNxt = lenght * page;
                                if (totalcount > clickButNxt) {
                                    imageButtonNext.setEnabled(memberList.size() == 15 ? true : false);
                                } else {
                                    imageButtonNext.setEnabled(false);
                                }
                                for (int i = 0; i < memberList.size(); i++) {
                                    ArrayList<Associate_details_Child_model> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = memberList.get(i).getAssociateName();
                                    member_id = memberList.get(i).getAssociateCode();
                                    child_models.add(new Associate_details_Child_model(
                                            memberList.get(i).getAssociateCode(),
                                            memberList.get(i).getAssociateName(),
                                            memberList.get(i).getBranchName(),
                                            String.valueOf(memberList.get(i).getBranchCode()),
                                            memberList.get(i).getSectorName(),
                                            String.valueOf(memberList.get(i).getId()),
                                            memberList.get(i).getAssociate_carder(),
                                            memberList.get(i).getMemberJoinDate()
                                    ));
                                    parent_models.add(new Associate_details_Parent_model(id, name, member_id,
                                            memberList.get(i).getStatus(),
                                            memberList.get(i).getNominee_name(),
                                            memberList.get(i).getRelation(),
                                            memberList.get(i).getNominee_age(),
                                            memberList.get(i).getEmail(),
                                            memberList.get(i).getMobile_no(),


                                            child_models));
                                }
                                listAdapter = new Associate_Details_ListAdapter(getActivity(), parent_models, AssociateDetailsFragment.this);
                                expListView.setAdapter(listAdapter);
                                DisplayMetrics metrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                int width = metrics.widthPixels;
                                expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
                            } else {
                                imageButtonNext.setEnabled(false);
                                Toast.makeText(getContext(), "Data not found.", Toast.LENGTH_SHORT).show();
                            }
                           /* }else {
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }*/
                        } else {
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data not found.", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AssociateListResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });

        } catch (Exception ex) {
            Log.e("EXX", "" + ex.toString());
        }
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onEvent_View(String id, String memberid, String type) {
        Log.e("ID", "" + id);
        Fragment fragment = new AssociateDeatilsViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void createExcelSheet() {
        getAssociateList2(member_id, token, String.valueOf(page), langht);
    }

    private void getAssociateList2(final String member_id, String token, String page, String length) {
        try {
            googleProgressDialog.show1("Loading data....");

            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            RestHandler.getApiService().ASSOCIATE_LIST_RESPONSE_CALL(_member_id, _token, _page, _length).enqueue(new Callback<AssociateListResponse>() {
                @Override
                public void onResponse(Call<AssociateListResponse> call, Response<AssociateListResponse> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            googleProgressDialog.dismiss();
                            // if (response.body().getAssociateStatus() == 1){
                            String id = null, name = null, member_id = null;
                            ResultAssociateDetails resultAssociateDetails = response.body().getResult();
                            List<AssociateDetailsMember> memberList = resultAssociateDetails.getMember();
                            ArrayList<Associate_details_Parent_model> parent_models = new ArrayList<>();
                            if (memberList.size() > 0) {
                                for (int i = 0; i < memberList.size(); i++) {
                                    ArrayList<Associate_details_Child_model> child_models = new ArrayList<>();
                                    id = String.valueOf(i + 1);
                                    name = memberList.get(i).getAssociateName();
                                    member_id = memberList.get(i).getAssociateCode();
                                    child_models.add(new Associate_details_Child_model(
                                            memberList.get(i).getAssociateCode(),
                                            memberList.get(i).getAssociateName(),
                                            memberList.get(i).getBranchName(),
                                            String.valueOf(memberList.get(i).getBranchCode()),
                                            memberList.get(i).getSectorName(),
                                            String.valueOf(memberList.get(i).getId()),
                                            memberList.get(i).getAssociate_carder(),
                                            memberList.get(i).getMemberJoinDate()
                                    ));
                                    parent_models.add(new Associate_details_Parent_model(id, name, member_id,
                                            memberList.get(i).getStatus(),
                                            memberList.get(i).getNominee_name(),
                                            memberList.get(i).getRelation(),
                                            memberList.get(i).getNominee_age(),
                                            memberList.get(i).getEmail(),
                                            memberList.get(i).getMobile_no(),
                                            child_models));
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
                                        Associate_details_Child_model sub = parent_models.get(i).getAssociate_details_child_models().get(0);



                                        Label label0 = new Label(0, i + 1, sub.getBranch_code());
                                        Label label1 = new Label(1, i + 1, sub.getAssociate_branch_name());
                                        Label label2 = new Label(2, i + 1, sub.getSector_name());
                                        Label label3 = new Label(3, i + 1, sub.getAssociate_cader());
                                        Label label4 = new Label(4, i + 1, sub.getAssociate_code());
                                        Label label5 = new Label(5, i + 1, sub.getAssociate_carder_new());
                                        Label label6 = new Label(6, i + 1, sub.getAssociate_join_date());

                                        try {
                                            sheet.addCell(label0);
                                            sheet.addCell(label1);
                                            sheet.addCell(label2);
                                            sheet.addCell(label3);
                                            sheet.addCell(label4);
                                            sheet.addCell(label5);
                                            sheet.addCell(label6);

                                        } catch (WriteException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    Label label21 = new Label(0, 0, "Branch Code");
                                    Label label22 = new Label(1, 0, "Branch Name");
                                    Label label23 = new Label(2, 0, "Sector Name");
                                    Label label24 = new Label(3, 0, "Associate Name");
                                    Label label25 = new Label(4, 0, "Associate Code");
                                    Label label26 = new Label(5, 0, "Associate Cader");
                                    Label label27 = new Label(6, 0, "Joining Date");

                                    sheet.addCell(label21);
                                    sheet.addCell(label22);
                                    sheet.addCell(label23);
                                    sheet.addCell(label24);
                                    sheet.addCell(label25);
                                    sheet.addCell(label26);
                                    sheet.addCell(label27);

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

                           /* }else {
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }*/

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

//                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                    File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                                    Uri uri = Uri.parse(sdCard.getAbsolutePath() + "/SBMFA");
//                                    intent.setDataAndType(uri, "*/*");
//                                    getActivity().startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }

                @Override
                public void onFailure(Call<AssociateListResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });

        } catch (Exception ex) {
            Log.e("EXX", "" + ex.toString());
        }
    }
}