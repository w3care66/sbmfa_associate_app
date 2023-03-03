package com.associate.sbmfa.Fragment.AssociateManagement.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.associate.sbmfa.Fragment.AssociateManagement.Model.Associate_Collection_parent_model;
import com.associate.sbmfa.R;
import java.util.ArrayList;
public class AssociateCollection_Details_Adapter extends BaseExpandableListAdapter {
    TextView 	MemberRegisteredDate,BranchCode,BranchName,SectorName,MemberID,MemberName,AssociateID,Associatename,MemberAddress,AadharNumber,PenNumber;
    TextView Mid,Mname,Mcount;
    private Context _context;
    public ArrayList<Associate_Collection_parent_model> associate_collection_parent_models=new ArrayList<>();
    public AssociateCollection_Details_Adapter(Context context, ArrayList<Associate_Collection_parent_model> Member_Mangement_Parent_modelss) {
        if (context!=null) {
            this._context = context;
            this.associate_collection_parent_models = Member_Mangement_Parent_modelss;
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosititon);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView topic_name,complete_qustions,all_qustions;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.associate_collection_details_item_child, null);
        }

        BranchCode=convertView.findViewById(R.id.balance);
        BranchName=convertView.findViewById(R.id.tenture);
        SectorName=convertView.findViewById(R.id.eliAmount);
        MemberID=convertView.findViewById(R.id.depositeAmount);
        MemberName=convertView.findViewById(R.id.address);
        AssociateID=convertView.findViewById(R.id.state);
        Associatename=convertView.findViewById(R.id.district);
        MemberAddress=convertView.findViewById(R.id.city);
        AadharNumber=convertView.findViewById(R.id.village);


        BranchCode.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getBranch_code());
        BranchName.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getBranch());
        SectorName.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getDaily_deno_sum());
        MemberID.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getMonthly_deno_sum());
        MemberName.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getFd_deno_sum());
        AssociateID.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getCollection_all());
        Associatename.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getLoan_recovery_amount());
        MemberAddress.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getNcc());
        AadharNumber.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getTcc());
//        PenNumber.setText(associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().get(childPosition).getPenNumber());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.associate_collection_parent_models.get(groupPosition).getAssociate_collection_details_children().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.associate_collection_parent_models.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.associate_collection_parent_models.size();
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

        Mname.setText(associate_collection_parent_models.get(groupPosition).getName());
        Mid.setText(associate_collection_parent_models.get(groupPosition).getId());
        Mcount.setText(associate_collection_parent_models.get(groupPosition).getCount());

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