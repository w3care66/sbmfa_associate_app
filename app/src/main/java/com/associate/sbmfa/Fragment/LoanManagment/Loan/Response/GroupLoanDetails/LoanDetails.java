package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class LoanDetails {

    @SerializedName("lable")
    @Expose
    private String lable;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("group_activity")
    @Expose
    private String groupActivity;
    @SerializedName("group_leader")
    @Expose
    private String groupLeader;
    @SerializedName("no_of_member")
    @Expose
    private Integer noOfMember;
    @SerializedName("loan_amount")
    @Expose
    private String loanAmount;
    @SerializedName("EMI_mode_option")
    @Expose
    private String eMIModeOption;
    @SerializedName("associate_code")
    @Expose
    private Object associateCode;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGroupActivity() {
        return groupActivity;
    }

    public void setGroupActivity(String groupActivity) {
        this.groupActivity = groupActivity;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public Integer getNoOfMember() {
        return noOfMember;
    }

    public void setNoOfMember(Integer noOfMember) {
        this.noOfMember = noOfMember;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getEMIModeOption() {
        return eMIModeOption;
    }

    public void setEMIModeOption(String eMIModeOption) {
        this.eMIModeOption = eMIModeOption;
    }

    public Object getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(Object associateCode) {
        this.associateCode = associateCode;
    }

}