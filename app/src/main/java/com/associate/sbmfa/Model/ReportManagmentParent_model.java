package com.associate.sbmfa.Model;

import java.util.ArrayList;

public class ReportManagmentParent_model {
    String id,name,progress,count;
    ArrayList<ReportManagmentChild> reportManagmentChildren;

    public ReportManagmentParent_model(String id, String name, String progress, String count, ArrayList<ReportManagmentChild> reportManagmentChildren) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.reportManagmentChildren = reportManagmentChildren;
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

    public ArrayList<ReportManagmentChild> getReportManagmentChildren() {
        return reportManagmentChildren;
    }

    public void setReportManagmentChildren(ArrayList<ReportManagmentChild> reportManagmentChildren) {
        this.reportManagmentChildren = reportManagmentChildren;
    }
}
