package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.Datum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvestmentDueResult {

    @SerializedName("data")
    @Expose
    private List<InvestmentDueReportData> data = null;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("record_count")
    @Expose
    private Integer recordCount;

    public List<InvestmentDueReportData> getData() {
        return data;
    }

    public void setData(List<InvestmentDueReportData> data) {
        this.data = data;
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

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }
}
