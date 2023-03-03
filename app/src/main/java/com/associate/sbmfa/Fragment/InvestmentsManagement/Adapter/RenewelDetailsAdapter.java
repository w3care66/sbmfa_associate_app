package com.associate.sbmfa.Fragment.InvestmentsManagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.associate.sbmfa.Fragment.InvestmentsManagement.Model.RenewalDetaildPrentModel;
import com.associate.sbmfa.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

public class RenewelDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<RenewalDetaildPrentModel> parent_models = new ArrayList<>();
    String type;

    public RenewelDetailsAdapter(Context context, ArrayList<RenewalDetaildPrentModel> parent_models,String type) {
        if (context != null) {
            this._context = context;
            this.parent_models = parent_models;
            this.type = type;
        }
        //this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.parent_models.get(groupPosition).getChlidModels().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        TextView date,br_code,br_name,so_name,member_id,PenNumber1,
                member_name,account_no,accountholder,plan,tenure,amount,asso_code,assco_name,paymode,zo_name;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.renewel_details_child, null);
        }
        TextView textViewDate;
        ConstraintLayout conptenure,conplan;
        textViewDate=convertView.findViewById(R.id.mRegDateTV);
        conptenure=convertView.findViewById(R.id.conptenure);
        zo_name=convertView.findViewById(R.id.zo_name);
        conplan=convertView.findViewById(R.id.conplan);
        date=convertView.findViewById(R.id.mRegDate);
        br_code=convertView.findViewById(R.id.branchCode);
        PenNumber1=convertView.findViewById(R.id.PenNumber1);
        br_name=convertView.findViewById(R.id.barnchName);
        so_name=convertView.findViewById(R.id.sectorname);
        member_id=convertView.findViewById(R.id.memberid);
        member_name=convertView.findViewById(R.id.memberName);
        account_no=convertView.findViewById(R.id.associateId);
       // accountholder=convertView.findViewById(R.id.associateName);
        plan=convertView.findViewById(R.id.memberAddress);
        tenure=convertView.findViewById(R.id.adharNumber);
        amount=convertView.findViewById(R.id.penNumber);
        asso_code=convertView.findViewById(R.id.penNumdssaber);
        assco_name=convertView.findViewById(R.id.penNumdsaber);
        paymode=convertView.findViewById(R.id.PaymodeT);
        date.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getCreate_date());
        br_code.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getBr_code());
        br_name.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getBr_name());
        so_name.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getSo_name());
        member_id.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getMember_id());
        member_name.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getRo_name());
        account_no.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getAccount_number());
       /* accountholder.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getAddcoiate_name());*/
        if (type.equals("ssb")){
            conptenure.setVisibility(View.GONE);
            conplan.setVisibility(View.GONE);
            PenNumber1.setText("Amount");
            textViewDate.setText("Created Date");
        }else {
            PenNumber1.setText("Renewal Amount");
            textViewDate.setText("Member Registered Date");
            conptenure.setVisibility(View.VISIBLE);
            conplan.setVisibility(View.VISIBLE);
            plan.setText(parent_models.get(groupPosition).
                    getChlidModels().get(childPosition).getPlan());
            tenure.setText(parent_models.get(groupPosition).
                    getChlidModels().get(childPosition).getTenure());
        }
        amount.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getAmount()+" ₹");
        asso_code.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getAsscoiate_code());

        assco_name.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getAddcoiate_name());

        zo_name.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getZo_name());

        paymode.setText(parent_models.get(groupPosition).
                getChlidModels().get(childPosition).getPayment_mode());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.parent_models.get(groupPosition).getChlidModels().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parent_models.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        TextView name, count, des;
        ImageView up_dwon_icon;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.renewal_details_parent, null);
        }
        name = convertView.findViewById(R.id.name);
        count = convertView.findViewById(R.id.id);
        des = convertView.findViewById(R.id.count);
        name.setText(parent_models.get(groupPosition).getName());
        count.setText(parent_models.get(groupPosition).getId());
        des.setText(parent_models.get(groupPosition).getProgress());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        //   chapter_count.setText(parent_models.get(groupPosition).getChapter_count()+"  Chapter");
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
