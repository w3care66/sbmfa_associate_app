package com.associate.sbmfa.Fragment.InvestmentsManagement.RenewForm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter.DailyRenewalListAdapter;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.DailyRenewalModel;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewalSubmitlResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.ResultDailyRenewalCollector;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.GoogleProgressDialog;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.AssociateDetail;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.CollectorAgentRenewalResponse;
import com.associate.sbmfa.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  RDFRDRenewalFragment extends Fragment implements DailyRenewalListAdapter.EventListener, DailyRenewalListAdapter.EventListener1 {
    View RootView;
    ImageView imageViewBack;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    RecyclerView recyclerViewDailyRenewal;
    DailyRenewalListAdapter dailyRenewalListAdapter;
    ArrayList<DailyRenewalModel> dailyRenewalModels = new ArrayList<>();
    ScrollView scrollView;
    Spinner paymentmode;
    EditText textViewNumberAccountDaily;
    ArrayList<String> paymentmodearr =new ArrayList<>();
    TextView textViewTotalAmountDaily;
    int total = 0;
    Button buttonSubmit;
    EditText editTextCollecorCode,editTextCollector_name_daily,editTextAvailableAmount;
    String balance,ssb_acount,payment_mode ="4",branch_id = "1",collector_associate_code,plan_id="1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_r_d_f_r_d_renewal, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        googleProgressDialog = new GoogleProgressDialog(getContext());
        member_id= UserData.get(SessionManager.KEY_member_id);
        paymentmode=RootView.findViewById(R.id.paymentmoderd_frd);

        textViewNumberAccountDaily = RootView.findViewById(R.id.editTextrd_frdAccount);
        textViewTotalAmountDaily = RootView.findViewById(R.id.editTextTextPersonName11rd_frd);
        recyclerViewDailyRenewal = RootView.findViewById(R.id.recyclerView_rd_frd);
        editTextAvailableAmount = RootView.findViewById(R.id.editTextTextPersonName10);
        buttonSubmit = RootView.findViewById(R.id.button2);
        editTextCollecorCode = RootView.findViewById(R.id.editTextrd_frdAssociateCode);
        editTextCollector_name_daily = RootView.findViewById(R.id.editTextrd_frdAssociateName);
        recyclerViewDailyRenewal.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        dailyRenewalListAdapter = new DailyRenewalListAdapter(getActivity(),"1", dailyRenewalModels, RDFRDRenewalFragment.this, RDFRDRenewalFragment.this, (Activity) getContext());
        recyclerViewDailyRenewal.setAdapter(dailyRenewalListAdapter);


        if (getArguments()!=null){
            editTextAvailableAmount.setText(getArguments().getString("balance"));
        }

        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen)
                            buttonSubmit.setVisibility(View.GONE);
                        else
                            buttonSubmit.setVisibility(View.VISIBLE);
                    }
                });


        editTextCollecorCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!TextUtils.isEmpty(editTextCollecorCode.getText().toString())){
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        getCollectorData(member_id,token,editTextCollecorCode.getText().toString());
                    }else {
                        Toast.makeText(getActivity(), "Text is Empty..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments()!=null){
                    balance = getArguments().getString("balance");
                    ssb_acount = getArguments().getString("ssb_acount");
                    collector_associate_code = editTextCollecorCode.getText().toString();
                    JSONArray jsonArray = new JSONArray();
                    for (int i=0;i<dailyRenewalModels.size();i++){
                        JSONObject formDetailsJson = new JSONObject();
                        try {
                            if (dailyRenewalModels.get(i).getAssociate_name().isEmpty()){
                            }else {
                                if (dailyRenewalModels.get(i).getAmount().isEmpty())
                                {
                                }else {
                                    int amount =Integer.parseInt(dailyRenewalModels.get(i).getAmount());
                                    if (amount > 0){
                                        formDetailsJson.put("account", dailyRenewalModels.get(i).getAccount_no());
                                        formDetailsJson.put("amount", dailyRenewalModels.get(i).getAmount());
                                        jsonArray.put(formDetailsJson);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    RenewalSubmit(member_id,token,branch_id,collector_associate_code,ssb_acount,balance,plan_id,jsonArray);

                }else {

                }

            }
        });







        textViewNumberAccountDaily.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!TextUtils.isEmpty(textViewNumberAccountDaily.getText().toString())){
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        dailyRenewalModels.clear();
                        String size = textViewNumberAccountDaily.getText().toString();
                        int size1 = Integer.parseInt(size);
                        if (size1 > 0){
                            if (size1 <= 7){
                                textViewTotalAmountDaily.setText(null);
                                total = 0;
                                for (int i =0;i < size1;i++){
                                    dailyRenewalModels.add(new DailyRenewalModel("","","","","",""));
                                }
                                dailyRenewalListAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(getActivity(), "Please enter a value less than or equal to 7", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }else {
                        Toast.makeText(getActivity(), "Text is Empty..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }


                return false;
            }
        });



        paymentmodearr.clear();
        paymentmodearr.add("SSB Account");
        ArrayAdapter<String> adapterselectDate22 = new ArrayAdapter<String>(getActivity(), R.layout.spiner_item, paymentmodearr);
        adapterselectDate22.setDropDownViewResource(R.layout.spiner_item);
        paymentmode.setAdapter(adapterselectDate22);
        paymentmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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


    private void getCollectorData(String member_id, String token, String collectorCode) {
        try {
            googleProgressDialog.show1("Loading Data...");
            RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _collectorCode = RequestBody.create(MediaType.parse("multipart/form-data"), collectorCode);
            RestHandler.getApiService().COLLECTOR_AGENT_RENEWAL_RESPONSE_CALL(_assciate_no,_token,_collectorCode).enqueue(new Callback<CollectorAgentRenewalResponse>() {
                @Override
                public void onResponse(Call<CollectorAgentRenewalResponse> call, Response<CollectorAgentRenewalResponse> response) {
                    if (response.isSuccessful()){
                        googleProgressDialog.dismiss();
                        if (response.body().getCode() == 200){
                                if (response.body().getResult()!=null){
                                    ResultDailyRenewalCollector result = response.body().getResult();
                                    AssociateDetail associateDetail = result.getAssociateDetail();
                                    editTextCollector_name_daily.setText(associateDetail.getName());
                                }else {
                                    Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                }
                        }else {
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Data not Found...", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<CollectorAgentRenewalResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });
        }catch (Exception ex){

        }
    }

    @Override
    public void onEvent_DailyRewal(String id, String amount) {
        total =0;
        for (int i = 0; i < dailyRenewalModels.size(); i++){
            if (!dailyRenewalModels.get(i).getAccount_no().isEmpty()){
                if (!dailyRenewalModels.get(i).getAmount().isEmpty()){
                    total = total + Integer.parseInt(dailyRenewalModels.get(i).getAmount());
                }
            }
        }
        if (total > 0){
            textViewTotalAmountDaily.setText(String.valueOf(total));
        }else {
            textViewTotalAmountDaily.setText(null
            );
        }
        dailyRenewalListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEvent_DailyRewalRemove(int pos, String amount) {
        dailyRenewalModels.remove(pos);
        total =0;
        for (int i = 0; i < dailyRenewalModels.size(); i++){
            if (!dailyRenewalModels.get(i).getAccount_no().isEmpty()){
                Log.e("GFH",""+dailyRenewalModels.get(i).getAmount());
                if(dailyRenewalModels.get(i).getAmount().isEmpty()){

                }else {
                    total = total + Integer.parseInt(dailyRenewalModels.get(i).getAmount());
                }
            }
        }
        if (dailyRenewalModels.size() > 0){
            textViewTotalAmountDaily.setText(String.valueOf(total));
            textViewNumberAccountDaily.setText(String.valueOf(dailyRenewalModels.size()));
        }else {
            textViewTotalAmountDaily.setText(null);
            textViewNumberAccountDaily.setText(null);
        }
        dailyRenewalListAdapter.notifyDataSetChanged();
    }


    private void RenewalSubmit(String member_id, String token, String branch_id, String collector_associate_code, String ssb_acount, String balance, String plan_id, JSONArray jsonArray) {
        if (jsonArray.length() > 0){
            try {
                googleProgressDialog.show1("Renewal Plan Register....");
                RequestBody _assciate_no = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _branch_id = RequestBody.create(MediaType.parse("multipart/form-data"), branch_id);
                RequestBody _collector_associate_code = RequestBody.create(MediaType.parse("multipart/form-data"), collector_associate_code);
                RequestBody _ssb_acount = RequestBody.create(MediaType.parse("multipart/form-data"), ssb_acount);
                RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), payment_mode);
                RequestBody _plan_id = RequestBody.create(MediaType.parse("multipart/form-data"), plan_id);
                RequestBody account_info =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), String.valueOf(jsonArray));

                RestHandler.getApiService().SUBMITL_RESPONSE_CALL(_assciate_no,
                        _token,
                        _branch_id,
                        _collector_associate_code,
                        _ssb_acount,
                        _payment_mode,
                        _plan_id,
                        account_info).enqueue(new Callback<RenewalSubmitlResponse>() {
                    @Override
                    public void onResponse(Call<RenewalSubmitlResponse> call, Response<RenewalSubmitlResponse> response) {
                        if (response.body()!=null){
                            googleProgressDialog.dismiss();
                            if (response.body().getCode() == 200){
                                if (response.body().getAssociateStatus() == 1){
                                    Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                    dailyRenewalModels.clear();
                                    dailyRenewalListAdapter.notifyDataSetChanged();
                                    textViewTotalAmountDaily.setText(null);
                                    textViewNumberAccountDaily.setText(null);
                                    editTextCollecorCode.setText(null);
                                    editTextCollector_name_daily.setText(null);
                                    Toast.makeText(getActivity(), "Renewal has been completed successfully!", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            googleProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Something wrong...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RenewalSubmitlResponse> call, Throwable t) {
                        googleProgressDialog.dismiss();
                    }
                });
            }catch (Exception ex){
            }
        }else {
            Toast.makeText(getContext(), "All field are required", Toast.LENGTH_SHORT).show();

        }

        Log.e("jsonArray",""+jsonArray.toString());

    }

}