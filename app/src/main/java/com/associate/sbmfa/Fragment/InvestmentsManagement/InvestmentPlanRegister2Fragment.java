package com.associate.sbmfa.Fragment.InvestmentsManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.associate.sbmfa.R;

import java.util.ArrayList;

public class InvestmentPlanRegister2Fragment extends Fragment {

    View Rootview;
    Spinner spinner3,spinner4,spinner5;
    ArrayList<String> dateStrings3 =new ArrayList<>();
    ArrayList<String> dateStrings4 =new ArrayList<>();
    ArrayList<String> dateStrings5 =new ArrayList<>();
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Rootview = inflater.inflate(R.layout.fragment_investment_plan_register2, container, false);
        spinner3 = Rootview.findViewById(R.id.spinner3);
        spinner4 = Rootview.findViewById(R.id.spinner4);
        spinner5 = Rootview.findViewById(R.id.spinner5);
        button = Rootview.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new InvestmentPlanRegister3Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        dateStrings3.add("Select Plan");
        dateStrings3.add("Saving");
        dateStrings3.add("Fix Deposit");
        dateStrings3.add("Daily Deposit");
        dateStrings3.add("Renew Deposit");
        ArrayAdapter<String> adapterdeposit = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings3);
        adapterdeposit.setDropDownViewResource(R.layout.spiner_item);
        spinner3.setAdapter(adapterdeposit);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateStrings4.add("Select Tenure*");
        dateStrings4.add("18 months");
        dateStrings4.add("24 months");
        dateStrings4.add("36 months");
        dateStrings4.add("42 months");
        ArrayAdapter<String> adapterdTenure = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings4);
        adapterdTenure.setDropDownViewResource(R.layout.spiner_item);
        spinner4.setAdapter(adapterdTenure);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateStrings5.add("Select Mode*");
        dateStrings5.add("Cash");
        dateStrings5.add("Cheque");
        dateStrings5.add("online transaction");
        dateStrings5.add("SSB Account");
        ArrayAdapter<String> adapterdpayMode = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, dateStrings5);
        adapterdpayMode.setDropDownViewResource(R.layout.spiner_item);
        spinner5.setAdapter(adapterdpayMode);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return Rootview;

    }
}