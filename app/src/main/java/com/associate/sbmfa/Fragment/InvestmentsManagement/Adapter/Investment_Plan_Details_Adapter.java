package com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.associate.sbmfa.Fragment.AssociateManagement.Adapter.Associate_Details_ListAdapter;
import com.associate.sbmfa.Fragment.AssociateManagement.AssociateDeatilsViewFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.InvestmentPlanDetailsViewFragment;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.Investment_Plan_Details_parent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;
public class Investment_Plan_Details_Adapter extends BaseExpandableListAdapter {
    ImageView imageView_action;
    TextView plan_name1,Opening_date1,form_no1,associateName1,MemberRegisteredDate,memberI_d,membermobilenumber,associateCode,accountNumber,tenture,balance,eliAmount,depositeAmount,state,district,
            city,village,pin,first,second,address;
    TextView Mid,Mname,Mcount;
    private Context _context;
    public ArrayList<Investment_Plan_Details_parent_model> investment_plan_details_parent_models=new ArrayList<>();
     EventListener eventListener;
    public interface EventListener {
        void onEvent_View(String id, String memberid, String type);
    }
    public Investment_Plan_Details_Adapter(Context context, ArrayList<Investment_Plan_Details_parent_model> Member_Mangement_Parent_modelss) {
        if (context!=null) {
            this._context = context;
            this.investment_plan_details_parent_models = Member_Mangement_Parent_modelss;
            this.eventListener=eventListener;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView topic_name,complete_qustions,all_qustions;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout._investment_child_layout_view, null);
        }
        plan_name1=convertView.findViewById(R.id.plan_name1);
        Opening_date1=convertView.findViewById(R.id.Opening_date1);
        form_no1=convertView.findViewById(R.id.Form_No1);
        associateName1=convertView.findViewById(R.id.associateName1);

        imageView_action=convertView.findViewById(R.id.imageView_action);
        MemberRegisteredDate=convertView.findViewById(R.id.mRegDate);
        memberI_d=convertView.findViewById(R.id.memberId);
        membermobilenumber=convertView.findViewById(R.id.membermobilenumber);
        associateCode=convertView.findViewById(R.id.associateCode);
        accountNumber=convertView.findViewById(R.id.accountNumber);
        tenture=convertView.findViewById(R.id.tenture);
        balance=convertView.findViewById(R.id.balance);
        eliAmount=convertView.findViewById(R.id.eliAmount);
        depositeAmount=convertView.findViewById(R.id.depositeAmount);
        address=convertView.findViewById(R.id.address);
        state=convertView.findViewById(R.id.state);
        district=convertView.findViewById(R.id.district);
        city=convertView.findViewById(R.id.city);
        village=convertView.findViewById(R.id.village);
        pin=convertView.findViewById(R.id.pin);
        first=convertView.findViewById(R.id.first);
        second=convertView.findViewById(R.id.second);
        //tv_action=convertView.findViewById(R.id.tv_action);
        form_no1.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getForm_number());
        Opening_date1.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getCreated_at());
        associateName1.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getAssociate_name());
        plan_name1.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getPlan());
        MemberRegisteredDate.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getMemberRegisteredDate());
        memberI_d.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getMemberI_d());
        membermobilenumber.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getMembermobilenumber());
        associateCode.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getAssociateCode());
        accountNumber.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getMemberI_d());
        tenture.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getTenture());
        balance.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getBalance()+ "₹");
        if (investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getBalance().equals("")){
            balance.setText("N/A");
        }else {
            balance.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getBalance()+ "₹");
        }

        if (investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getEliAmount().equals("N/A")){
            eliAmount.setText("N/A");
        }else {
            eliAmount.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getEliAmount()+ "₹");
        }



        depositeAmount.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getDepositeAmount()+ "₹");
        address.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getAddress());
        state.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getState());
        district.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getDistrict());
        city.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getCity());
        village.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getVillage());
        pin.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getPin());
        first.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getFirst());
        second.setText(investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getSecond());
        imageView_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("_plan_id",investment_plan_details_parent_models.get(groupPosition).getCount()); // Put anything what you want
                bundle.putString("_plan_type",investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().get(childPosition).getPlan()); //

                InvestmentPlanDetailsViewFragment fragment2 = new InvestmentPlanDetailsViewFragment();
                fragment2.setArguments(bundle);
                ((AppCompatActivity)_context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment2)
                        .commit();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.investment_plan_details_parent_models.get(groupPosition).getInvestment_plan_details_child_models().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.investment_plan_details_parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.investment_plan_details_parent_models.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        TextView name,count,des;
        ImageView up_dwon_icon;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.assciate_collection_parent_item, null);
        }
        Mname=convertView.findViewById(R.id.name);
        Mid=convertView.findViewById(R.id.id);
        Mcount=convertView.findViewById(R.id.count);

        Mname.setText(investment_plan_details_parent_models.get(groupPosition).getName());
        Mid.setText(investment_plan_details_parent_models.get(groupPosition).getId());
        Mcount.setText(investment_plan_details_parent_models.get(groupPosition).getProgress()) ;


        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(Member_Mangement_Parent_modelss.get(groupPosition).getChapter_count()+"  Chapter");
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}