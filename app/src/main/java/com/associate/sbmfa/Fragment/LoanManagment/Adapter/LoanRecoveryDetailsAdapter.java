package com.associate.sbmfa.Fragment.LoanManagment.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.associate.sbmfa.Fragment.LoanManagment.Model.LoanRecoveryDetailsModelPraent;
import com.associate.sbmfa.R;
import java.util.ArrayList;
public class LoanRecoveryDetailsAdapter extends BaseExpandableListAdapter {
    private Context _context;
    public ArrayList<LoanRecoveryDetailsModelPraent> LoneListParent_models=new ArrayList<>();
    LoanRecoveryDetailsAdapter.EventListener eventListener;
    LinearLayout guar;
    public interface EventListener {
        void onEvent_View(String lagerid, String memberid, String type);
    }
    public LoanRecoveryDetailsAdapter(Context context, ArrayList<LoanRecoveryDetailsModelPraent> LoneListParent_models) {
        if (context!=null) {
            this._context = context;
            this.LoneListParent_models = LoneListParent_models;
            this.eventListener=eventListener;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.LoneListParent_models.get(groupPosition).getLoanRecoveryDetailsModelChildren().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView br_code,br_name,member_id,
                member_name,account_no,asso_code,assco_name,loan_type,
                payment_date,payment_mode,description,depostie,
                penalty,roi_amount,principal_amount,opening_balance;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.loan_recovery_details_child1, null);
        }

        br_code=convertView.findViewById(R.id.branchCode);
        br_name=convertView.findViewById(R.id.barnchName);
        account_no=convertView.findViewById(R.id.account_number);
        member_id=convertView.findViewById(R.id.memberid);
        member_name=convertView.findViewById(R.id.memberName);
        loan_type=convertView.findViewById(R.id.loan_type);
        payment_date=convertView.findViewById(R.id.payment_date);
        payment_mode=convertView.findViewById(R.id.payment_mode);
        description=convertView.findViewById(R.id.description);
        asso_code=convertView.findViewById(R.id.penNumdssaber);
        assco_name=convertView.findViewById(R.id.penNumdsaber);
        penalty=convertView.findViewById(R.id.penalty);
        roi_amount=convertView.findViewById(R.id.roi_amount);
        principal_amount=convertView.findViewById(R.id.principal_amount);
        opening_balance=convertView.findViewById(R.id.opening_balance);
        depostie=convertView.findViewById(R.id.deposite);

        br_code.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getBranch_code());
        br_name.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getBranch_name());
        account_no.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getAccount_number());
        member_id.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getMember_id());
        member_name.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getMember_name());
        payment_date.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getPayment_date());
        payment_mode.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getPayment_mode());
        loan_type.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getLoan_type());
        description.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getDescription());
        asso_code.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getAssociate_id());
        assco_name.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getAssociate_name());
        penalty.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getPenalty()+" ₹");
        roi_amount.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getRoi_amount() +" ₹");
        principal_amount.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getPrincipal_amount()+" ₹");
        opening_balance.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getOpening_balance()+" ₹");

        depostie.setText(LoneListParent_models.get(groupPosition).
                getLoanRecoveryDetailsModelChildren().get(childPosition).getDeposite()+" ₹");
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.LoneListParent_models.get(groupPosition).getLoanRecoveryDetailsModelChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.LoneListParent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.LoneListParent_models.size();
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
            convertView = infalInflater.inflate(R.layout.practice_tab_item, null);
        }

        TextView id=convertView.findViewById(R.id.id);
        name=convertView.findViewById(R.id.name);
        count=convertView.findViewById(R.id.id);
        des=convertView.findViewById(R.id.count);
        name.setText(LoneListParent_models.get(groupPosition).getName());
        id.setText(LoneListParent_models.get(groupPosition).getId());
        up_dwon_icon = convertView.findViewById(R.id.imageView20);
        if (isExpanded) {
            up_dwon_icon.setImageResource(R.drawable.up_arrow);
        } else {
            up_dwon_icon.setImageResource(R.drawable.down_arrow);
        }
        count.setText(LoneListParent_models.get(groupPosition).getId());
        name.setText(LoneListParent_models.get(groupPosition).getName());
        des.setText(LoneListParent_models.get(groupPosition).getProgress());
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
