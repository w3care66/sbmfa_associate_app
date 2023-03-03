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

import com.associate.sbmfa.Fragment.AssociateLedgerManagement.LoanLedgerFragment;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.PlanLoanFragment;
import com.associate.sbmfa.R;

public class AssociateLedgerManagementFragment extends Fragment {

    View RootView;
    ImageView imageViewBack;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_associate_ledger_management, container, false);
        imageViewBack = RootView.findViewById(R.id.imageView);
        cardView1 = RootView.findViewById(R.id.cardView5);
        cardView2 = RootView.findViewById(R.id.cardView56);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new PlanLoanFragment();
                /*Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoanLedgerFragment();
                /*Bundle bundle = new Bundle();
                bundle.putString("cate_id","all");
                fragment.setArguments(bundle);*/
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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