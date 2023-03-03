package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

import java.util.ArrayList;

public class InvestmentDueReportParentModel {
    String id,name,progress,count;
    ArrayList<InvestmentDueReportChildModel> investmentDueReportChildModels;

    public InvestmentDueReportParentModel(String id, String name, String progress, String count, ArrayList<InvestmentDueReportChildModel> investmentDueReportChildModels) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.investmentDueReportChildModels = investmentDueReportChildModels;
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

    public ArrayList<InvestmentDueReportChildModel> getInvestmentDueReportChildModels() {
        return investmentDueReportChildModels;
    }

    public void setInvestmentDueReportChildModels(ArrayList<InvestmentDueReportChildModel> investmentDueReportChildModels) {
        this.investmentDueReportChildModels = investmentDueReportChildModels;
    }
}
