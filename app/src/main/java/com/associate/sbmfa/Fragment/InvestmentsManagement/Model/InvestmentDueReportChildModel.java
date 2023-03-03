package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

public class InvestmentDueReportChildModel {

    String branch_name,branch_code,member_id,associate_code,associate_name,account_no,
            plan_name,tenure,deno_amount,due_emi,due_emi_amount;

    public InvestmentDueReportChildModel(String branch_name, String branch_code,String member_id, String associate_code, String associate_name, String account_no, String plan_name, String tenure, String deno_amount, String due_emi, String due_emi_amount) {
        this.branch_name = branch_name;
        this.branch_code = branch_code;
        this.member_id=member_id;
        this.associate_code = associate_code;
        this.associate_name = associate_name;
        this.account_no = account_no;
        this.plan_name = plan_name;
        this.tenure = tenure;
        this.deno_amount = deno_amount;
        this.due_emi = due_emi;
        this.due_emi_amount = due_emi_amount;
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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getDeno_amount() {
        return deno_amount;
    }

    public void setDeno_amount(String deno_amount) {
        this.deno_amount = deno_amount;
    }

    public String getDue_emi() {
        return due_emi;
    }

    public void setDue_emi(String due_emi) {
        this.due_emi = due_emi;
    }

    public String getDue_emi_amount() {
        return due_emi_amount;
    }

    public void setDue_emi_amount(String due_emi_amount) {
        this.due_emi_amount = due_emi_amount;
    }
}
