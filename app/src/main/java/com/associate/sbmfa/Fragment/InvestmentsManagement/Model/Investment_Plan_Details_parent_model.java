package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

import java.util.ArrayList;

public class Investment_Plan_Details_parent_model {
    String id,name,progress,count;
    ArrayList<Investment_Plan_Details_child_model> investment_plan_details_child_models;

    public Investment_Plan_Details_parent_model(String id, String name, String progress, String count, ArrayList<Investment_Plan_Details_child_model> investment_plan_details_child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.investment_plan_details_child_models = investment_plan_details_child_models;
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

    public ArrayList<Investment_Plan_Details_child_model> getInvestment_plan_details_child_models() {
        return investment_plan_details_child_models;
    }

    public void setInvestment_plan_details_child_models(ArrayList<Investment_Plan_Details_child_model> investment_plan_details_child_models) {
        this.investment_plan_details_child_models = investment_plan_details_child_models;
    }
}
