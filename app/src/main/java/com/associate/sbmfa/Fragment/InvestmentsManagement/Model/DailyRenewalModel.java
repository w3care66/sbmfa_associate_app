package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

public class DailyRenewalModel {
    String account_no,name,amount,due_amount,associate_name,totalAmount;

    public DailyRenewalModel(String account_no, String name, String amount, String due_amount, String associate_name, String totalAmount) {
        this.account_no = account_no;
        this.name = name;
        this.amount = amount;
        this.due_amount = due_amount;
        this.associate_name = associate_name;
        this.totalAmount = totalAmount;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(String due_amount) {
        this.due_amount = due_amount;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
