package com.associate.sbmfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.associate.sbmfa.Model.Associate_list_model;
import com.associate.sbmfa.R;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

//import android.widget.RatingBar;

public class AssociateListAdapter extends RecyclerView.Adapter<AssociateListAdapter.ViewHolder> {
    private Context context;
    ArrayList<Associate_list_model>group_models=new ArrayList<>();
    EventListener eventListener;
    public interface EventListener {
        void onEvent(String name);
    }
    public AssociateListAdapter(Context context, ArrayList<Associate_list_model> group_models) {
        this.context = context;
        this.group_models = group_models;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.associate_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.SNId.setText(group_models.get(position).getId());
         holder.Branch_name.setText(group_models.get(position).getBranch_name());
         holder.Associate_name.setText(group_models.get(position).getAssociate_name());
         holder.Br_code.setText(group_models.get(position).getBr_code());
         holder.Associate_id.setText(group_models.get(position).getAssociate_id());


    }
    @Override
    public int getItemCount() {
        return group_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Branch_name,Associate_name,Br_code,Associate_id,SNId;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SNId = itemView.findViewById(R.id.s_n_id);
            Branch_name = itemView.findViewById(R.id.textView);
            Associate_name = itemView.findViewById(R.id.textView3);
            Br_code = itemView.findViewById(R.id.textView4);
            Associate_id = itemView.findViewById(R.id.textView5);

        }
    }
}
