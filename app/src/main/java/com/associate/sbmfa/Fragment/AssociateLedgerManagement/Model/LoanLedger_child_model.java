package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model;

public class LoanLedger_child_model {
    String tranid,date,paymentMode,description,penalty,reference_no,deposit,roi_amount,principal_amount,opening_balance,jv_amount;
    public String getJv_amount() {
        return jv_amount;
    }
    String balance;
    public void setJv_amount(String jv_amount) {
        this.jv_amount = jv_amount;
    }

    public LoanLedger_child_model(String tranid, String date, String paymentMode, String description, String penalty, String reference_no, String deposit, String roi_amount, String principal_amount, String opening_balance, String jv_amount) {
        this.tranid = tranid;
        this.date = date;
        this.paymentMode = paymentMode;
        this.description = description;
        this.penalty = penalty;
        this.reference_no = reference_no;
        this.deposit = deposit;
        this.roi_amount = roi_amount;
        this.principal_amount = principal_amount;
        this.opening_balance = opening_balance;
        this.jv_amount=jv_amount;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getRoi_amount() {
        return roi_amount;
    }

    public void setRoi_amount(String roi_amount) {
        this.roi_amount = roi_amount;
    }

    public String getPrincipal_amount() {
        return principal_amount;
    }

    public void setPrincipal_amount(String principal_amount) {
        this.principal_amount = principal_amount;
    }

    public String getOpening_balance() {
        return opening_balance;
    }

    public void setOpening_balance(String opening_balance) {
        this.opening_balance = opening_balance;
    }
}
