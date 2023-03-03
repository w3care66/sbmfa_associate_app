package com.associate.sbmfa.Fragment;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.associate.sbmfa.Adapter.AssociateListAdapter;
import com.associate.sbmfa.Adapter.ExpandableListAdapter;
import com.associate.sbmfa.Model.Associate_list_model;
import com.associate.sbmfa.Model.Child_model;
import com.associate.sbmfa.Model.Parent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class TableViewFragment extends Fragment {
    View RootView;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    ArrayList<Parent_model> parent_models=new ArrayList<>();
    HashMap<String, List<String>> listDataChild;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_practice_tab, container, false);
        expListView = (ExpandableListView)RootView. findViewById(R.id.lvExp);


        spinner1 = RootView.findViewById(R.id.spinner);
        spinner2 = RootView.findViewById(R.id.spinner2);
        imageViewSerach = RootView.findViewById(R.id.imageView10);
        constraintLayoutOptions = RootView.findViewById(R.id.constraintLayout8);
        typeStrings.add("Type");
        typeStrings.add("HDFC");
        typeStrings.add("SBBJ");
        typeStrings.add("BOB");
        typeStrings.add("ICICI");

        dateStrings.add("Status");
        dateStrings.add("1 months");
        dateStrings.add("2 months");
        dateStrings.add("3 months");


        ArrayAdapter<String> adapterselectState = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, typeStrings);
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
       /* expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(), "sdfsdfsdf", Toast.LENGTH_SHORT).show();

                return false;
            }
        });*/
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               /* Log.e("TimeCount",""+parent_models.get(groupPosition).getChild_models().get(childPosition).getTime());
                Intent intent=new Intent(getActivity(), ChapterQustionsActivity.class);
                intent.putExtra("id",parent_models.get(groupPosition).getChild_models().get(childPosition).getId());
                intent.putExtra("name",parent_models.get(groupPosition).getChild_models().get(childPosition).getName());
                intent.putExtra("time",parent_models.get(groupPosition).getChild_models().get(childPosition).getTime());
                intent.putExtra("score",parent_models.get(groupPosition).getChild_models().get(childPosition).getScore());
                startActivity(intent);*/
                return false;
            }
        });
        parent_models.clear();
        ArrayList<Child_model> child_models=new ArrayList<>();
        child_models.add(new Child_model("1","Thaker","dfgdfg","dfgdfg","dfgdfg","dfgdfg"));
        parent_models.add(new Parent_model("1","Ram","14320","32211",child_models));
        parent_models.add(new Parent_model("2","Gopal","1430","321313",child_models));
        parent_models.add(new Parent_model("3","Mukesh","34210","32131",child_models));
        listAdapter = new ExpandableListAdapter(getActivity(), parent_models);
        expListView.setAdapter(listAdapter);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expListView.setIndicatorBounds(width - GetPixelFromDips(40), width - GetPixelFromDips(10));
        return  RootView;
    }
    public int GetPixelFromDips(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
}
