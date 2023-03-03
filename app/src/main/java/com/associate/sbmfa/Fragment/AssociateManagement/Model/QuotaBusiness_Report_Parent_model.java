package com.associate.sbmfa.Fragment.AssociateManagement.Model;


import java.util.List;

public class QuotaBusiness_Report_Parent_model {
    String id,name,progress,count;

    List<QuotaBusiness_Report_Child_model> QuotaBusiness_Report_Child_models;

    public QuotaBusiness_Report_Parent_model(String id, String name, String progress, String count, List<QuotaBusiness_Report_Child_model> quotaBusiness_Report_Child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        QuotaBusiness_Report_Child_models = quotaBusiness_Report_Child_models;
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

    public List<QuotaBusiness_Report_Child_model> getQuotaBusiness_Report_Child_models() {
        return QuotaBusiness_Report_Child_models;
    }

    public void setQuotaBusiness_Report_Child_models(List<QuotaBusiness_Report_Child_model> quotaBusiness_Report_Child_models) {
        QuotaBusiness_Report_Child_models = quotaBusiness_Report_Child_models;
    }
}
