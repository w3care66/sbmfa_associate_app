package com.associate.sbmfa.Fragment;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.associate.sbmfa.Adapter.ReportManagment_Adapter;
import com.associate.sbmfa.Model.ReportManagmentChild;
import com.associate.sbmfa.Model.ReportManagmentParent_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ReportManagmentFragment extends Fragment {
    View RootView;
    ReportManagment_Adapter listAdapter;
    ExpandableListView expListView;
    ArrayList<ReportManagmentParent_model> parent_models=new ArrayList<>();
    Spinner spinner2,spinner3;
    ArrayList<String> dateStrings =new ArrayList<>();
    ArrayList<String> dateStrings3 =new ArrayList<>();
    ImageView imageViewSerach,imageViewBack;
    TextView fromDate, toDate;
    private int mYear, mMonth, mDay;
    String dateFrom,dateto;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_report_managment, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);
        fromDate = RootView.findViewById(R.id.fromDate);
        toDate = RootView.findViewById(R.id.toDate);
        imageViewBack = RootView.findViewById(R.id.imageView);
        spinner2 = RootView.findViewById(R.id.spinner2);
        spinner3 = RootView.findViewById(R.id.spinner3);
        imageViewSerach = RootView.findViewById(R.id.imageView110);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);

        dateStrings.add("Select Associate");
        dateStrings.add("Ram");
        dateStrings.add("Raju");
        dateStrings.add("Ramu");

        dateStrings3.add("Select Investment Plan");
        dateStrings3.add("Saving Account");
        dateStrings3.add("Daily Deposit");
        dateStrings3.add("Renew Deposit");

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYear = 1980;
                mMonth = Calendar.JANUARY;
                mDay = 1;
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                int monthOfYear1 = monthOfYear + 1;
                                dateFrom = "" + dayOfMonth + "-" + monthOfYear1 + "-" + year;
                                fromDate.setText(dateFrom);
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
                mYear = 1980;
                mMonth = Calendar.JANUARY;
                mDay = 1;

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

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();
            }
        });



        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterselectDate.setDropDownViewResource(R.layout.spiner_item);
        spinner2.setAdapter(adapterselectDate);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> adapters = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings3);
        adapters.setDropDownViewResource(R.layout.spiner_item);
        spinner3.setAdapter(adapters);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
        parent_models.clear();
        ArrayList<ReportManagmentChild> child_models=new ArrayList<>();
        child_models.add(new ReportManagmentChild("1","Thaker","dfgdfg","dfgdfg","dfgdfg","dfgdfg","","","","",""));
        parent_models.add(new ReportManagmentParent_model("1","Ram","14320","32211",child_models));
        parent_models.add(new ReportManagmentParent_model("2","Gopal","1430","321313",child_models));
        parent_models.add(new ReportManagmentParent_model("3","Mukesh","34210","32131",child_models));
        listAdapter = new ReportManagment_Adapter(getActivity(), parent_models);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));

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



        return RootView;
    }

    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
}