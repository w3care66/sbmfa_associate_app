package com.associate.sbmfa.Fragment.InvestmentsManagement;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.associate.sbmfa.R;

import java.util.ArrayList;
import java.util.Calendar;

public class InvestmentPlanRegister3Fragment extends Fragment {

    View RootView;

    Spinner spinner;
    ArrayList<String> dateStrings =new ArrayList<>();
    TextView textViewDoB;
    private int mYear, mMonth, mDay;
    String dateFrom,dateto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_investment_plan_register3, container, false);
        spinner = RootView.findViewById(R.id.spinner6);
        textViewDoB = RootView.findViewById(R.id.editTextTextPersonName21);

        textViewDoB.setOnClickListener(new View.OnClickListener() {
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
                                textViewDoB.setText(dateFrom);
                            }
                        }, mYear, mMonth, mDay);

                final Calendar cd = Calendar.getInstance();
                datePickerDialog.getDatePicker().setMaxDate(cd.getTimeInMillis());
                datePickerDialog.show ();
            }
        });

        dateStrings.add("Select Relation");
        dateStrings.add("Son");
        dateStrings.add("Brother");
        dateStrings.add("Mother");
        dateStrings.add("Father");

        ArrayAdapter<String> adapterdeposit = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings);
        adapterdeposit.setDropDownViewResource(R.layout.spiner_item);
        spinner.setAdapter(adapterdeposit);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return RootView;
    }
}