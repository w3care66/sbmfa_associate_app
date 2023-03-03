package com.associate.sbmfa.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.AssociateManagement.AssociateDeatilsViewFragment;
import com.associate.sbmfa.Fragment.DashbordFragment;
import com.associate.sbmfa.Fragment.GroupLoanPaymentFragment;
import com.associate.sbmfa.Fragment.RegistionForm.MemberRegisterFragment;
import com.associate.sbmfa.Fragment.MakeLoanPaymentFragement;
import com.associate.sbmfa.Fragment.RenewalPaymentFragment;
import com.associate.sbmfa.Respones.Member;
import com.associate.sbmfa.Session.SessionManager;
import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;
import com.google.android.material.navigation.NavigationView;

public class DashBordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    SessionManager sessionManager;
    TextView textViewUserName,textViewEmail;
    CircleImageView imageViewUserImage;
    Member memberDetails;
    int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        setContentView(R.layout.activity_dash_bord);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_view);
        navigationView.setNavigationItemSelectedListener(this);
        sessionManager = new SessionManager(this);
        memberDetails = sessionManager.getUserAllDetails();
        View headerView = navigationView.getHeaderView(0);


        textViewUserName=headerView.findViewById(R.id.nav_name);
        textViewEmail=headerView.findViewById(R.id.nav_email);
        imageViewUserImage=headerView.findViewById(R.id.imageView_nav);
        textViewUserName.setText(memberDetails.getName());
        textViewEmail.setText(memberDetails.getAssociateNumber());
        Glide.with(this)
                .load(memberDetails.getProfileImge())
                .placeholder(R.drawable.profile_user)
                .centerCrop()
                .into(imageViewUserImage);
        loadFragment(new DashbordFragment());

    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {
        switch (itemId) {
            case R.id.profile:
                Fragment fragment=new AssociateDeatilsViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",memberDetails.getId()+"");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
//            case R.id.loan_payment:
//                Fragment loanFragment=new MakeLoanPaymentFragement();
//                FragmentTransaction loantransaction = getSupportFragmentManager().beginTransaction();
//                loantransaction.replace(R.id.nav_host_fragment, loanFragment);
//                loantransaction.addToBackStack(null);
//                loantransaction.commit();
//                break;
//            case R.id.grouploan_payment:
//                Fragment grouploanFragment=new GroupLoanPaymentFragment();
//                FragmentTransaction grouploantransaction = getSupportFragmentManager().beginTransaction();
//                grouploantransaction.replace(R.id.nav_host_fragment, grouploanFragment);
//                grouploantransaction.addToBackStack(null);
//                grouploantransaction.commit();
//                break;
//            case R.id.renewal_payment:
//                Fragment renewalfragment=new RenewalPaymentFragment();
//                FragmentTransaction renewaltransaction = getSupportFragmentManager().beginTransaction();
//                renewaltransaction.replace(R.id.nav_host_fragment, renewalfragment);
//                renewaltransaction.addToBackStack(null);
//                renewaltransaction.commit();
//                break;
//                case R.id.investment_registration:
//                Fragment investmentRegisterFragment=new MemberRegisterFragment();
//                FragmentTransaction investmentRegistrationTransaction = getSupportFragmentManager().beginTransaction();
//                investmentRegistrationTransaction.replace(R.id.nav_host_fragment, investmentRegisterFragment);
//                investmentRegistrationTransaction.addToBackStack(null);
//                investmentRegistrationTransaction.commit();
//                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                // builder.setIcon(R.drawable.logo_new2);
                builder.setMessage("Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sessionManager.logoutUser();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


}