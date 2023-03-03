package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class GroupMemberDetail {

    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ssb_account")
    @Expose
    private String ssbAccount;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSsbAccount() {
        return ssbAccount;
    }

    public void setSsbAccount(String ssbAccount) {
        this.ssbAccount = ssbAccount;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

}