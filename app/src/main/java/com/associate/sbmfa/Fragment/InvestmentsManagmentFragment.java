package com.associate.sbmfa.Fragment;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Daily_Investment_Due_report_Fragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.InvestmentPlanDetailsFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Monthly_Investment_Due_report;
import com.associate.sbmfa.Fragment.InvestmentsManagement.RenewalDetailsFragment;
import com.associate.sbmfa.R;


public class InvestmentsManagmentFragment extends Fragment {
    View Rootview;
    ImageView imageViewBack;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Rootview = inflater.inflate(R.layout.fragment_investments_managment, container, false);
        cardView1 = Rootview.findViewById(R.id.cardView5);
        cardView2 = Rootview.findViewById(R.id.cardView56);
        cardView3 = Rootview.findViewById(R.id.cardView567);
        cardView4 = Rootview.findViewById(R.id.cardView5678);
        cardView5 = Rootview.findViewById(R.id.cardView52);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Daily_Investment_Due_report_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Monthly_Investment_Due_report();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new Monthly_Investment_Due_report();
                Bundle bundle = new Bundle();
                bundle.putString("type","renewal");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new RenewalDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type","ssb");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new InvestmentPlanDetailsFragment();
                /*Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return Rootview;
    }
}