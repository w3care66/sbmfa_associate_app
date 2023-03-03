package com.associate.sbmfa.Fragment;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_DependentslListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.DependentsModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.InvestmentPlanDetailsFragment;
import com.associate.sbmfa.Fragment.MemberMangement.MemberMangementFragment;
import com.associate.sbmfa.Respones.Member;
import com.associate.sbmfa.Respones.Profile.DependentDetails;
import com.associate.sbmfa.Respones.Profile.ProfileResponse;
import com.associate.sbmfa.Respones.SSBAmountBalanceResponse;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.bumptech.glide.Glide;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Session.SessionManager;

import static com.associate.sbmfa.Activity.DashBordActivity.drawerLayout;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;


public class DashbordFragment extends Fragment {
    View rootView;
    CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6;
    ImageView notication_ball;
    ImageView imageViewDrawer;
    SessionManager sessionManager;
    Member memberDetails;
    TextView textViewName;
    CircleImageView circleImageView;
    String token="";
    String member_id;
    HashMap<String ,String > UserDataToken;
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    TextView ssbAmountTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_dashbord, container, false);
        cardView  = rootView.findViewById(R.id.cardView);
        cardView2 = rootView.findViewById(R.id.cardView2);
        cardView3 = rootView.findViewById(R.id.cardView3);
        cardView4 = rootView.findViewById(R.id.cardView4);
        cardView5 = rootView.findViewById(R.id.cardView55);
        cardView6 = rootView.findViewById(R.id.cardView556);
        textViewName = rootView.findViewById(R.id.textView16);
        ssbAmountTextView = rootView.findViewById(R.id.textView_ssbAmount);
        circleImageView = rootView.findViewById(R.id.imageView_profile);
        sessionManager = new SessionManager(getContext());
        imageViewDrawer = rootView.findViewById(R.id.imageView);
        memberDetails = sessionManager.getUserAllDetails();
        textViewName.setText(memberDetails.getName());
        googleProgressDialog = new GoogleProgressDialog(getContext());
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);

        getSSBAmountData(member_id);

        Glide.with(getActivity())
                .load(memberDetails.getProfileImge())
                .placeholder(R.drawable.profile_user)
                .centerCrop()
                .into(circleImageView);

        imageViewDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDrawer();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new MemberMangementFragment();
                getMemberDetails(member_id,member_id,fragment);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new AssociateManagementFragment();
                getMemberDetails(member_id,member_id,fragment);
               /* FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new InvestmentsManagmentFragment();
                getMemberDetails(member_id,member_id,fragment);
               /* FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new AssociateLedgerManagementFragment();
                getMemberDetails(member_id,member_id,fragment);
                /*FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoanManagmentFragment();
                getMemberDetails(member_id,member_id,fragment);
               /* FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new ReportManagmentTabFragment();
                getMemberDetails(member_id,member_id,fragment);
                /*FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.app_name);
                    // builder.setIcon(R.drawable.logo_new2);
                    builder.setMessage("Do you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                    getActivity().finishAffinity();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void getSSBAmountData(String member_id) {
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<SSBAmountBalanceResponse> ssbAmountResponesCall = RestHandler.getApiService().SSB_AMOUNT_BALANCE(_member_id, _token);
        ssbAmountResponesCall.enqueue(new Callback<SSBAmountBalanceResponse>() {
            @Override
            public void onResponse(Call<SSBAmountBalanceResponse> call, Response<SSBAmountBalanceResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                        ssbAmountTextView.setText(response.body().getCurrentbalance());
                    }else {
                        ssbAmountTextView.setText("0.00");
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SSBAmountBalanceResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
            }
        });
    }

/*
    private boolean getMemberDetails(String member_id,String id) {
        final boolean[] d = new boolean[1];
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        Call<ProfileResponse> applicationsListResponesCall = RestHandler.getApiService().PROFILE_RESPONSE_CALL(_member_id,_id,_token);
        applicationsListResponesCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }
                    }else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
        return  d[0];
    }
*/

    /// action_DrawerMenu...
    @SuppressLint("WrongConstant")
    public void OpenDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            drawerLayout.openDrawer(Gravity.START);
        }
    }


    public void getMemberDetails(String member_id, String id, final Fragment fragment) {
        googleProgressDialog.show1("Loading data...");
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _id = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        Call<ProfileResponse> applicationsListResponesCall = RestHandler.getApiService().PROFILE_RESPONSE_CALL(_member_id,_id,_token);
        applicationsListResponesCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
                    googleProgressDialog.dismiss();
                    if (response.body().getCode() == 200){
                        if (response.body().getAssociateStatus() == 0){
                            AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                            dialog.checkStatus();
                        }else {
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_host_fragment, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    }else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    googleProgressDialog.dismiss();
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                googleProgressDialog.dismiss();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
    }


}