package com.associate.sbmfa.Fragment.AssociateManagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateCollectionReportData {
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("associate_name")
    @Expose
    private String associateName;
    @SerializedName("daily_deno_sum")
    @Expose
    private Integer dailyDenoSum;
    @SerializedName("monthly_deno_sum")
    @Expose
    private Integer monthlyDenoSum;
    @SerializedName("fd_deno_sum")
    @Expose
    private Integer fdDenoSum;
    @SerializedName("tcc")
    @Expose
    private String tcc;
    @SerializedName("loan_recovery_amount")
    @Expose
    private String loanRecoveryAmount;
    @SerializedName("collection_all")
    @Expose
    private String collectionAll;
    @SerializedName("ncc")
    @Expose
    private String ncc;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(String associateCode) {
        this.associateCode = associateCode;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public Integer getDailyDenoSum() {
        return dailyDenoSum;
    }

    public void setDailyDenoSum(Integer dailyDenoSum) {
        this.dailyDenoSum = dailyDenoSum;
    }

    public Integer getMonthlyDenoSum() {
        return monthlyDenoSum;
    }

    public void setMonthlyDenoSum(Integer monthlyDenoSum) {
        this.monthlyDenoSum = monthlyDenoSum;
    }

    public Integer getFdDenoSum() {
        return fdDenoSum;
    }

    public void setFdDenoSum(Integer fdDenoSum) {
        this.fdDenoSum = fdDenoSum;
    }

    public String getTcc() {
        return tcc;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public String getLoanRecoveryAmount() {
        return loanRecoveryAmount;
    }

    public void setLoanRecoveryAmount(String loanRecoveryAmount) {
        this.loanRecoveryAmount = loanRecoveryAmount;
    }

    public String getCollectionAll() {
        return collectionAll;
    }

    public void setCollectionAll(String collectionAll) {
        this.collectionAll = collectionAll;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }
}
