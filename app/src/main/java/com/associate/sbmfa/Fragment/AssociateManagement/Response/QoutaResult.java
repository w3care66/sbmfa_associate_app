package com.associate.sbmfa.Fragment.AssociateManagement.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class QoutaResult {

    @SerializedName("qouta_report")
    @Expose
    private List<QoutaReport> qoutaReport;

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("length")
    @Expose
    private String length;

    public List<QoutaReport> getQoutaReport() {
        return qoutaReport;
    }

    public void setQoutaReport(List<QoutaReport> qoutaReport) {
        this.qoutaReport = qoutaReport;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

}