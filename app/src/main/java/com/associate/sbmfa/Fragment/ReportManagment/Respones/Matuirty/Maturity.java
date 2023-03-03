package com.associate.sbmfa.Fragment.ReportManagment.Respones.Matuirty;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Maturity {

    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("branch_code")
    @Expose
    private Integer branch_code;


    @SerializedName("branch_name")
    @Expose
    private String branch_name;


    @SerializedName("account_no")
    @Expose
    private String account_no;

    @SerializedName("member_name")
    @Expose
    private String member_name;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("plan_name")
    @Expose
    private String plan_name;

    @SerializedName("deno")
    @Expose
    private String deno;

    @SerializedName("maturity_amount")
    @Expose
    private String maturity_amount;

    @SerializedName("associate_code")
    @Expose
    private String associate_code;

    @SerializedName("associate_name")
    @Expose
    private String associate_name;

    @SerializedName("opening_date")
    @Expose
    private String opening_date;


    @SerializedName("due_amount")
    @Expose
    private String due_amount;

    @SerializedName("total_amount")
    @Expose
    private String total_amount;

    @SerializedName("maturity_payable_amount")
    @Expose
    private String maturity_payable_amount;

    @SerializedName("tds_amount")
    @Expose
    private String tds_amount;

    @SerializedName("deposit_amount")
    @Expose
    private String deposit_amount;


    @SerializedName("interest")
    @Expose
    private String interest;

    @SerializedName("final_amount")
    @Expose
    private String final_amount;

    @SerializedName("maturity_type")
    @Expose
    private String maturity_type;


    @SerializedName("maturity_date")
    @Expose
    private String maturity_date;

    @SerializedName("tenure")
    @Expose
    private String tenure;

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("payment_mode")
    @Expose
    private String payment_mode;

    @SerializedName("cheque_no")
    @Expose
    private String cheque_no;


    @SerializedName("ssb_ac")
    @Expose
    private String ssb_ac;

    @SerializedName("bank_name")
    @Expose
    private String bank_name;

    @SerializedName("bank_ac")
    @Expose
    private String bank_ac;

    @SerializedName("rtgs_chrg")
    @Expose
    private String rtgs_chrg;


    @SerializedName("payment_date")
    @Expose
    private String payment_date;

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(Integer branch_code) {
        this.branch_code = branch_code;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getDeno() {
        return deno;
    }

    public void setDeno(String deno) {
        this.deno = deno;
    }

    public String getMaturity_amount() {
        return maturity_amount;
    }

    public void setMaturity_amount(String maturity_amount) {
        this.maturity_amount = maturity_amount;
    }

    public String getAssociate_code() {
        return associate_code;
    }

    public void setAssociate_code(String associate_code) {
        this.associate_code = associate_code;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getOpening_date() {
        return opening_date;
    }

    public void setOpening_date(String opening_date) {
        this.opening_date = opening_date;
    }

    public String getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(String due_amount) {
        this.due_amount = due_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getMaturity_payable_amount() {
        return maturity_payable_amount;
    }

    public void setMaturity_payable_amount(String maturity_payable_amount) {
        this.maturity_payable_amount = maturity_payable_amount;
    }

    public String getTds_amount() {
        return tds_amount;
    }

    public void setTds_amount(String tds_amount) {
        this.tds_amount = tds_amount;
    }

    public String getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(String deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(String final_amount) {
        this.final_amount = final_amount;
    }

    public String getMaturity_type() {
        return maturity_type;
    }

    public void setMaturity_type(String maturity_type) {
        this.maturity_type = maturity_type;
    }

    public String getMaturity_date() {
        return maturity_date;
    }

    public void setMaturity_date(String maturity_date) {
        this.maturity_date = maturity_date;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getCheque_no() {
        return cheque_no;
    }

    public void setCheque_no(String cheque_no) {
        this.cheque_no = cheque_no;
    }

    public String getSsb_ac() {
        return ssb_ac;
    }

    public void setSsb_ac(String ssb_ac) {
        this.ssb_ac = ssb_ac;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_ac() {
        return bank_ac;
    }

    public void setBank_ac(String bank_ac) {
        this.bank_ac = bank_ac;
    }

    public String getRtgs_chrg() {
        return rtgs_chrg;
    }

    public void setRtgs_chrg(String rtgs_chrg) {
        this.rtgs_chrg = rtgs_chrg;
    }
}