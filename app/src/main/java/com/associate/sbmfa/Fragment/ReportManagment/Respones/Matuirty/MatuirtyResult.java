package com.associate.sbmfa.Fragment.ReportManagment.Respones.Matuirty;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatuirtyResult {

    @SerializedName("maturity")
    @Expose
    private List<Maturity> maturity = null;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("record_count")
    @Expose
    private Integer record_count;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("length")
    @Expose
    private String length;

    public List<Maturity> getMaturity() {
        return maturity;
    }

    public void setMaturity(List<Maturity> maturity) {
        this.maturity = maturity;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

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
}
