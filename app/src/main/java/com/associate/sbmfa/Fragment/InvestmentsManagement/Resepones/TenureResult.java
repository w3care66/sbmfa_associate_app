package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TenureResult {

    @SerializedName("tenure")
    @Expose
    private List<Tenure> tenure = null;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    public List<Tenure> getTenure() {
        return tenure;
    }

    public void setTenure(List<Tenure> tenure) {
        this.tenure = tenure;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}