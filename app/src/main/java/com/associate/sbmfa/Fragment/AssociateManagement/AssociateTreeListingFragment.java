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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.AssociateTreeListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.AssociateTreeChildModel;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.AssociateTreeViewParent;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree.Tree;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree.TreeListResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree.TreeResult;
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

public class AssociateTreeListingFragment extends Fragment implements AssociateTreeListAdapter.EventListener {
    View RootView;
    AssociateTreeListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<AssociateTreeViewParent> parent_models = new ArrayList<>();
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
    Spinner spinnerLanght;
    ArrayList<String> dateStrings = new ArrayList<>();
    TextView name1, textViewNotFound;
    EditText editTextTextPersonName;
    Button buttonApply, buttonReset;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    ImageView imageViewSerach;
    ImageView pdfdwon, imageViewTreeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_associate_tree_listing, container, false);
        expListView = (ExpandableListView) RootView.findViewById(R.id.lvExp);
        sessionManager = new SessionManager(getContext());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData = sessionManager.getUserDetails();
        member_id = UserData.get(SessionManager.KEY_member_id);
        name1 = RootView.findViewById(R.id.name1);
        spinnerLanght = RootView.findViewById(R.id.spinner26);
        imageButtonPrv = RootView.findViewById(R.id.imageButton);
        imageButtonNext = RootView.findViewById(R.id.imageButton2);
        textViewNotFound = RootView.findViewById(R.id.not_found);
        buttonApply = RootView.findViewById(R.id.apply);
        buttonReset = RootView.findViewById(R.id.reset);

        editTextTextPersonName = RootView.findViewById(R.id.editTextTextPersonName);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        imageViewTreeView = RootView.findViewById(R.id.imageView1101);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        pdfdwon = RootView.findViewById(R.id.pdfdwon);
        pdfdwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parent_models.isEmpty()) {
                    createExcelSheet();
                }

            }
        });

        parent_models.clear();
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
                getAssociateTreeList(member_id, token, String.valueOf(page), String.valueOf(spinnerLanght), editTextTextPersonName.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        parent_models.clear();
        listAdapter = new AssociateTreeListAdapter(getActivity(), parent_models, AssociateTreeListingFragment.this);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));


        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                page = page + 1;
                // lengthValue=lengthValue * page;
                getAssociateTreeList(member_id, token, String.valueOf(page), String.valueOf(spinnerLanght), editTextTextPersonName.getText().toString());
            }
        });

        imageButtonPrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1) {
                    parent_models.clear();
                    listAdapter.notifyDataSetChanged();
                    page = page - 1;
                    // lengthValue=lengthValue * page;
                    getAssociateTreeList(member_id, token, String.valueOf(page), String.valueOf(spinnerLanght), editTextTextPersonName.getText().toString());
                }
            }
        });
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAssociateTreeList(member_id, token, String.valueOf(page), String.valueOf(spinnerLanght), editTextTextPersonName.getText().toString());
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent_models.clear();
                listAdapter.notifyDataSetChanged();
                textViewNotFound.setVisibility(View.VISIBLE);
                editTextTextPersonName.setText(null);
                name1.setVisibility(View.GONE);
            }
        });
        imageViewTreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AssociateTreeViewFragment();
                /*Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
        return RootView;
    }

    private void getAssociateTreeList(String member_id, String token, String pages, String langhts, String id) {
        try {
            googleProgressDialog.show1("Loading data....");
            parent_models.clear();
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), pages);
            RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), langhts);
            RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
            RestHandler.getApiService().ASSOCIATE_TREE_LIST_RESPONSE_CALL(_member_id, _token, _page, _length, _id).enqueue(new Callback<TreeListResponse>() {
                @Override
                public void onResponse(Call<TreeListResponse> call, Response<TreeListResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            textViewNotFound.setVisibility(View.GONE);
                            name1.setVisibility(View.VISIBLE);
                            googleProgressDialog.dismiss();
                            if (response.body().getAssociateStatus() == 0) {
                                AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                dialog.checkStatus();
                            }
//                            if (response.body().getAssociateStatus() == 1){
                            String id = null, name = null, member_id = null;
                            TreeResult treeResult = response.body().getResult();
                            List<Tree> memberList = treeResult.getTreeList();


                            if (memberList.size() > 0) {
                                int i = 1;
                                for (Tree tree : memberList) {
                                    ArrayList<AssociateTreeChildModel> child_models = new ArrayList<>();
                                    Log.e("check_id", "" + tree.toString());
                                    child_models.add(new AssociateTreeChildModel(
                                            tree.getAssociateCode(),
                                            tree.getAssociateName(),
                                            tree.getAssociateCarder(),
                                            tree.getSeniorCode(),
                                            tree.getSeniorName(),
                                            tree.getSeniorCarder(),
                                            tree.getStatusName(),
                                            "",
                                            tree.getId().toString(),
                                            tree.getBranch_name()
                                    ));
                                    parent_models.add(new AssociateTreeViewParent(String.valueOf(i++), tree.getAssociateName(), "", "", child_models));
                                }
                                listAdapter.notifyDataSetChanged();
                            } else {
                                googleProgressDialog.dismiss();
                                textViewNotFound.setVisibility(View.VISIBLE);
                                name1.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
//                            }else {
//                                googleProgressDialog.dismiss();
//                                textViewNotFound.setVisibility(View.VISIBLE);
//                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
//                            }
                        } else {
                            googleProgressDialog.dismiss();
                            name1.setVisibility(View.GONE);
                            textViewNotFound.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "" + response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        name1.setVisibility(View.GONE);
                        textViewNotFound.setVisibility(View.VISIBLE);
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TreeListResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                    name1.setVisibility(View.GONE);
                    textViewNotFound.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception ex) {
            googleProgressDialog.dismiss();
            name1.setVisibility(View.GONE);
            textViewNotFound.setVisibility(View.VISIBLE);
        }
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onEvent_Click(String id, String sss) {
        Fragment fragment = new AssociateTreeViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void createExcelSheet() {
        final ArrayList<AssociateTreeViewParent> parentModels = new ArrayList<>();
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _page = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
        RequestBody _length = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), editTextTextPersonName.getText().toString());
        RestHandler.getApiService().ASSOCIATE_TREE_LIST_RESPONSE_CALL(_member_id, _token, _page, _length, _id).enqueue(new Callback<TreeListResponse>() {
            @Override
            public void onResponse(Call<TreeListResponse> call, Response<TreeListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        TreeResult treeResult = response.body().getResult();
                        List<Tree> memberList = treeResult.getTreeList();
                        if (memberList.size() > 0) {
                            int i = 1;
                            for (Tree tree : memberList) {
                                ArrayList<AssociateTreeChildModel> child_models = new ArrayList<>();
                                Log.e("check_id", "" + tree.toString());
                                child_models.add(new AssociateTreeChildModel(
                                        tree.getAssociateCode(),
                                        tree.getAssociateName(),
                                        tree.getAssociateCarder(),
                                        tree.getSeniorCode(),
                                        tree.getSeniorName(),
                                        tree.getSeniorCarder(),
                                        tree.getStatusName(),
                                        "",
                                        tree.getId().toString(),
                                        tree.getBranch_name()
                                ));
                                parentModels.add(new AssociateTreeViewParent(String.valueOf(i++), tree.getAssociateName(), "", "", child_models));
                            }
                            listAdapter.notifyDataSetChanged();
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
                        final File file = new File(directory, Fnamexls);

                        WorkbookSettings wbSettings = new WorkbookSettings();

                        wbSettings.setLocale(new Locale("en", "EN"));

                        WritableWorkbook workbook;
                        try {
                            int a = 1;
                            workbook = Workbook.createWorkbook(file, wbSettings);
                            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                            for (int i = 0; i < parentModels.size(); i++) {
                                AssociateTreeChildModel sub = parentModels.get(i).getAssociateTreeChildModels().get(0);

                                Label label0 = new Label(0, i + 1, sub.getAssociateCode());
                                Label label1 = new Label(1, i + 1, sub.getAssociateName());
                                Label label2 = new Label(2, i + 1, sub.getAssociateCarder());
                                Label label3 = new Label(3, i + 1, sub.getSeniorCode());
                                Label label4 = new Label(4, i + 1, sub.getSeniorName());
                                Label label5 = new Label(5, i + 1, sub.getSeniorCarder());
                                Label label6 = new Label(6, i + 1, sub.getStatus());



//                                Label label0 = new Label(0, i + 1, sub.getAssociateCarder());
//                                Label label1 = new Label(1, i + 1, sub.getSeniorCode());
//                                Label label2 = new Label(2, i + 1, sub.getSeniorName());
//                                Label label3 = new Label(3, i + 1, sub.getSeniorCarder());
//                                Label label4 = new Label(4, i + 1, sub.getStatus());
//                                Label label5 = new Label(5, i + 1, sub.getBranch_name());


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

                            Label label1 = new Label(0, 0, "Associate Code");
                            Label label2 = new Label(1, 0, "Associate Name");
                            Label label3 = new Label(2, 0, "Associate Carder");
                            Label label4 = new Label(3, 0, "Senior Code");
                            Label label5 = new Label(4, 0, "Senior Name");
                            Label label6 = new Label(5, 0, "Senior Carder");
                            Label label7 = new Label(6, 0, "Status");


//                            Label label1 = new Label(0, 0, "Associate Carder");
//                            Label label2 = new Label(1, 0, "Senior Code");
//                            Label label3 = new Label(2, 0, "Senior Name");
//                            Label label4 = new Label(3, 0, "Senior Carder");
//                            Label label5 = new Label(4, 0, "Status");
//                            Label label6 = new Label(5, 0, "Branch Name");


                            sheet.addCell(label1);
                            sheet.addCell(label2);
                            sheet.addCell(label3);
                            sheet.addCell(label4);
                            sheet.addCell(label5);
                            sheet.addCell(label6);
                            sheet.addCell(label7);

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
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TreeListResponse> call, Throwable t) {

            }
        });


    }

}