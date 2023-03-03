package com.associate.sbmfa.Fragment.ReportManagment.Model;

import java.util.ArrayList;

public class AssociateBusinessSummaryReportParent {
    String id,name,progress,chapter_count;
    ArrayList<AssociateBusinessSummaryReportChild> associateBusinessSummaryReportChildren;
    public AssociateBusinessSummaryReportParent(String id, String name, String progress, String chapter_count, ArrayList<AssociateBusinessSummaryReportChild> associateBusinessSummaryReportChildren) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.chapter_count = chapter_count;
        this.associateBusinessSummaryReportChildren = associateBusinessSummaryReportChildren;
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

    public String getChapter_count() {
        return chapter_count;
    }

    public void setChapter_count(String chapter_count) {
        this.chapter_count = chapter_count;
    }

    public ArrayList<AssociateBusinessSummaryReportChild> getAssociateBusinessSummaryReportChildren() {
        return associateBusinessSummaryReportChildren;
    }

    public void setAssociateBusinessSummaryReportChildren(ArrayList<AssociateBusinessSummaryReportChild> associateBusinessSummaryReportChildren) {
        this.associateBusinessSummaryReportChildren = associateBusinessSummaryReportChildren;
    }
}
