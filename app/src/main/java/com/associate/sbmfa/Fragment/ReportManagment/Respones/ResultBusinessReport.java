package com.associate.sbmfa.Fragment.ReportManagment.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultBusinessReport {
    @SerializedName("business_report")
    @Expose
    private List<BusinessReport> businessReport = null;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;


    public Integer getRecord_count() {
        return record_count;
    }

    public void setRecord_count(Integer record_count) {
        this.record_count = record_count;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @SerializedName("record_count")
    @Expose
    private Integer record_count;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("length")
    @Expose
    private String length;

    public List<BusinessReport> getBusinessReport() {
        return businessReport;
    }

    public void setBusinessReport(List<BusinessReport> businessReport) {
        this.businessReport = businessReport;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
