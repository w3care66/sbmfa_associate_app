package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Model;

public class Plan_Ledger_child_model {
    String is_ssb,tranid,date,reference_no,withdrawal,deposit,opening_balance,description;

    public Plan_Ledger_child_model(String is_ssb, String tranid, String date, String reference_no, String withdrawal, String deposit, String opening_balance,String description) {
        this.is_ssb = is_ssb;
        this.tranid = tranid;
        this.date = date;
        this.reference_no = reference_no;
        this.withdrawal = withdrawal;
        this.deposit = deposit;
        this.opening_balance = opening_balance;
        this.description = description;
    }
    public String getIs_ssb() {
        return is_ssb;
    }
    public void setIs_ssb(String is_ssb) {
        this.is_ssb = is_ssb;
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

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getOpening_balance() {
        return opening_balance;
    }

    public void setOpening_balance(String opening_balance) {
        this.opening_balance = opening_balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
