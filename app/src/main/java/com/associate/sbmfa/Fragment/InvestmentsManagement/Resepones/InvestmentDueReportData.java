package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvestmentDueReportData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("associate_name")
    @Expose
    private String associateName;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("tenure")
    @Expose
    private String tenure;
    @SerializedName("deno_amount")
    @Expose
    private String denoAmount;
    @SerializedName("due_emi")
    @Expose
    private Integer dueEmi;
    @SerializedName("due_emi_amount")
    @Expose
    private Integer dueEmiAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getDenoAmount() {
        return denoAmount;
    }

    public void setDenoAmount(String denoAmount) {
        this.denoAmount = denoAmount;
    }

    public Integer getDueEmi() {
        return dueEmi;
    }

    public void setDueEmi(Integer dueEmi) {
        this.dueEmi = dueEmi;
    }

    public Integer getDueEmiAmount() {
        return dueEmiAmount;
    }

    public void setDueEmiAmount(Integer dueEmiAmount) {
        this.dueEmiAmount = dueEmiAmount;
    }
}
