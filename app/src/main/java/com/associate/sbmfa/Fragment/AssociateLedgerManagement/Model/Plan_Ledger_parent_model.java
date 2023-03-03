package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model;

import java.util.ArrayList;

public class Plan_Ledger_parent_model {
    String id,name,progress,count;
    ArrayList<Plan_Ledger_child_model> plan_ledger_child_models;
    public Plan_Ledger_parent_model(String id, String name, String progress, String count, ArrayList<Plan_Ledger_child_model> plan_ledger_child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.plan_ledger_child_models = plan_ledger_child_models;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<Plan_Ledger_child_model> getPlan_ledger_child_models() {
        return plan_ledger_child_models;
    }

    public void setPlan_ledger_child_models(ArrayList<Plan_Ledger_child_model> plan_ledger_child_models) {
        this.plan_ledger_child_models = plan_ledger_child_models;
    }
}
