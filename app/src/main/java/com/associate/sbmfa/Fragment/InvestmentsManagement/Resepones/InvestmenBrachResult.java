package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvestmenBrachResult {
    @SerializedName("branch")
    @Expose
    private List<Branch> branch = null;
    @SerializedName("plan")
    @Expose
    private List<Plan> plan = null;
    @SerializedName("relation")
    @Expose
    private List<Relation> relation = null;
    @SerializedName("ssb_detail")
    @Expose
    private SsbDetail ssbDetail;


    @SerializedName("ssb_fix_detail")
    @Expose
    private SsbFixDetail ssb_fix_detail;

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }

    public List<Plan> getPlan() {
        return plan;
    }

    public void setPlan(List<Plan> plan) {
        this.plan = plan;
    }

    public List<Relation> getRelation() {
        return relation;
    }

    public void setRelation(List<Relation> relation) {
        this.relation = relation;
    }

    public SsbDetail getSsbDetail() {
        return ssbDetail;
    }

    public void setSsbDetail(SsbDetail ssbDetail) {
        this.ssbDetail = ssbDetail;
    }

    public SsbFixDetail getSsb_fix_detail() {
        return ssb_fix_detail;
    }

    public void setSsb_fix_detail(SsbFixDetail ssb_fix_detail) {
        this.ssb_fix_detail = ssb_fix_detail;
    }
}