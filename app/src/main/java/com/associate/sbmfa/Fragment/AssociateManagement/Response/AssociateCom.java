package com.associate.sbmfa.Fragment.AssociateManagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AssociateCom {


    @SerializedName("ledger_id")
    @Expose
    private Integer ledger_id;

    @SerializedName("member_id")
    @Expose
    private Integer memberId;

    @SerializedName("associate_name")
    @Expose
    private String associate_name;

    @SerializedName("associate_no")
    @Expose
    private String associate_no;

    @SerializedName("associate_carder")
    @Expose
    private String associate_carder;

    @SerializedName("branch_code")
    @Expose
    private Integer branch_code;


    @SerializedName("branch_name")
    @Expose
    private String branch_name;


    @SerializedName("total_amount")
    @Expose
    private String total_amount;


    @SerializedName("final_total_amount")
    @Expose
    private String final_total_amount;



    @SerializedName("fuel_amount")
    @Expose
    private String fuel_amount;


    @SerializedName("tds_amount")
    @Expose
    private String tds_amount;


    @SerializedName("total_collection")
    @Expose
    private String total_collection;


    @SerializedName("commission_payment")
    @Expose
    private String commission_payment;



    @SerializedName("pan_no")
    @Expose
    private String pan_no;

    @SerializedName("ssb_account")
    @Expose
    private String ssb_account;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    public Integer getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(Integer ledger_id) {
        this.ledger_id = ledger_id;
    }


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getAssociate_no() {
        return associate_no;
    }

    public void setAssociate_no(String associate_no) {
        this.associate_no = associate_no;
    }

    public String getAssociate_carder() {
        return associate_carder;
    }

    public void setAssociate_carder(String associate_carder) {
        this.associate_carder = associate_carder;
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

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getFinal_total_amount() {
        return final_total_amount;
    }

    public void setFinal_total_amount(String final_total_amount) {
        this.final_total_amount = final_total_amount;
    }

    public String getFuel_amount() {
        return fuel_amount;
    }

    public void setFuel_amount(String fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public String getTds_amount() {
        return tds_amount;
    }

    public void setTds_amount(String tds_amount) {
        this.tds_amount = tds_amount;
    }

    public String getTotal_collection() {
        return total_collection;
    }

    public void setTotal_collection(String total_collection) {
        this.total_collection = total_collection;
    }

    public String getCommission_payment() {
        return commission_payment;
    }

    public void setCommission_payment(String commission_payment) {
        this.commission_payment = commission_payment;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getSsb_account() {
        return ssb_account;
    }

    public void setSsb_account(String ssb_account) {
        this.ssb_account = ssb_account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}