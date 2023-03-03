package com.associate.sbmfa.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.associate.sbmfa.Adapter.AssociateListAdapter;
import com.associate.sbmfa.Model.AssociateData;
import com.associate.sbmfa.Model.Associate_list_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AssociateListAdapter associateListAdapter;
    ArrayList<Associate_list_model> associate_list_models = new ArrayList<>();
    RecyclerView recyclerView;
    Spinner spinner1,spinner2;
    ArrayList<String> typeStrings =new ArrayList<>();
    ArrayList<String> dateStrings =new ArrayList<>();
    ImageView imageViewSerach;
    boolean isSerach = false;
    ConstraintLayout constraintLayoutOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView2);
        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        imageViewSerach = findViewById(R.id.imageView10);
        constraintLayoutOptions = findViewById(R.id.constraintLayout8);
        typeStrings.add("Type");
        typeStrings.add("HDFC");
        typeStrings.add("SBBJ");
        typeStrings.add("BOB");
        typeStrings.add("ICICI");

        dateStrings.add("Status");
        dateStrings.add("1 months");
        dateStrings.add("2 months");
        dateStrings.add("3 months");


        ArrayAdapter<String> adapterselectState = new ArrayAdapter<String>(MainActivity.this, R.layout.spiner_item, typeStrings);
        adapterselectState.setDropDownViewResource(R.layout.spiner_item);
        spinner1.setAdapter(adapterselectState);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adapterselectDate = new ArrayAdapter<String>(MainActivity.this, R.layout.spiner_item, dateStrings);
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

        recyclerView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        associateListAdapter = new AssociateListAdapter(this, associate_list_models);
        recyclerView.setAdapter(associateListAdapter);
        associate_list_models.clear();
        getData();

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
    }
    private void getData() {
        AssociateData  associateData = new AssociateData(
               "9982188367",
               "125" ,
                "Ram",
                "true",
                "yes",
                "no",
                "Jaipur 104/253",
                "rajasthan",
                "jaipur",
                "jaipur",
                "Chaksu",
                "302021",
                "#345ER",

                "#454rt",
                "1"
        );
        associate_list_models.add(new Associate_list_model("1","HDFC Bank of india","Ram Thaker","#45564453454","ER343434545ff",associateData,false));
        associate_list_models.add(new Associate_list_model("2","HDFC Bank","Raja Ram chuhan","#455644","ER34543",associateData,false));
        associate_list_models.add(new Associate_list_model("3","HDFC Bank of","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("4","HDFC Bank","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("5","HDFC","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("6","HDFC","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("7","HDFC","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("8","HDFC Bank of india","Ram Thaker","#45564453454","ER343434545ff",associateData,false));
        associate_list_models.add(new Associate_list_model("9","HDFC Bank","Raja Ram chuhan","#455644","ER34543",associateData,false));
        associate_list_models.add(new Associate_list_model("10","HDFC Bank of","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("11","HDFC Bank","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("12","HDFC","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("13","HDFC","Ram","#45564","ER343",associateData,false));
        associate_list_models.add(new Associate_list_model("14","HDFC","Ram","#45564","ER343",associateData,false));
        associateListAdapter.notifyDataSetChanged();
    }
}