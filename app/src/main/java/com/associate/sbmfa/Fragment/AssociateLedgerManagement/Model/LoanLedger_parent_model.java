package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model;

 

import java.util.ArrayList;

public class LoanLedger_parent_model {
    String id,name,progress,count;
    ArrayList<LoanLedger_child_model> LoanLedger_child_models;

    public LoanLedger_parent_model(String id, String name, String progress, String count, ArrayList<LoanLedger_child_model> LoanLedger_child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.LoanLedger_child_models = LoanLedger_child_models;
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

    public ArrayList<LoanLedger_child_model> getLoanLedger_child_models() {
        return LoanLedger_child_models;
    }

    public void setLoanLedger_child_models(ArrayList<LoanLedger_child_model> LoanLedger_child_models) {
        this.LoanLedger_child_models = LoanLedger_child_models;
    }
}
