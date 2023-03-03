package com.associate.sbmfa.Fragment.LoanManagment.Model;

public class LoanRecoveryDetailsModelChild {
    String branch_name,branch_code,account_number,loan_type,
            member_name,member_id,associate_name,
            associate_id,payment_date,payment_mode,
            description,penalty,deposite,roi_amount,principal_amount,opening_balance;

    public LoanRecoveryDetailsModelChild(String branch_name, String branch_code, String account_number, String loan_type, String member_name, String member_id, String associate_name, String associate_id, String payment_date, String payment_mode, String description, String penalty, String deposite, String roi_amount, String principal_amount, String opening_balance) {
        this.branch_name = branch_name;
        this.branch_code = branch_code;
        this.account_number = account_number;
        this.loan_type = loan_type;
        this.member_name = member_name;
        this.member_id = member_id;
        this.associate_name = associate_name;
        this.associate_id = associate_id;
        this.payment_date = payment_date;
        this.payment_mode = payment_mode;
        this.description = description;
        this.penalty = penalty;
        this.deposite = deposite;
        this.roi_amount = roi_amount;
        this.principal_amount = principal_amount;
        this.opening_balance = opening_balance;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
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

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getAssociate_id() {
        return associate_id;
    }

    public void setAssociate_id(String associate_id) {
        this.associate_id = associate_id;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
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

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
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
