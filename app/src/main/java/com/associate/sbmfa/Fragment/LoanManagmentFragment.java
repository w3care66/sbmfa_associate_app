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

import com.associate.sbmfa.Fragment.LoanManagment.GrouploanDetailsListFragment;
import com.associate.sbmfa.Fragment.LoanManagment.LoanRecoveryDetailsFragment;
import com.associate.sbmfa.Fragment.LoanManagment.loanDetailsFragment;
import com.associate.sbmfa.R;
public class LoanManagmentFragment extends Fragment {
    View RootView;
    ImageView imageViewBack;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_loan_managment, container, false);
        cardView1 = RootView.findViewById(R.id.cardView5);
        cardView2 = RootView.findViewById(R.id.cardView52);
        cardView3 = RootView.findViewById(R.id.cardView56);
        cardView4 = RootView.findViewById(R.id.cardView561);
        cardView5 = RootView.findViewById(R.id.cardView567);
        cardView6 = RootView.findViewById(R.id.cardView5670);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new loanDetailsFragment();
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
                Fragment fragment=new GrouploanDetailsListFragment();
                /*Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        /*cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoanRecoveryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","loan");
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
                Fragment fragment=new LoanRecoveryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","group");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoanRecoveryDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","loan");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoanRecoveryDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cate_id","group");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return RootView;
    }
}