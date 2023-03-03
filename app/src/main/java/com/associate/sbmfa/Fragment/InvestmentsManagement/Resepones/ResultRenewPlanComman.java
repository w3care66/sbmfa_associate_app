package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResultRenewPlanComman {

    @SerializedName("branch")
    @Expose
    private List<Branch> branch = null;
    @SerializedName("plan")
    @Expose
    private List<PlanRenewType> plan = null;

    @SerializedName("ssb_detail")
    @Expose
    private SsbDetail ssbDetail;

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }

    public List<PlanRenewType> getPlan() {
        return plan;
    }

    public void setPlan(List<PlanRenewType> plan) {
        this.plan = plan;
    }

    public SsbDetail getSsbDetail() {
        return ssbDetail;
    }

    public void setSsbDetail(SsbDetail ssbDetail) {
        this.ssbDetail = ssbDetail;
    }
}