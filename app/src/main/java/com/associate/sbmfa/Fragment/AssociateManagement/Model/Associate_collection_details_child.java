package com.associate.sbmfa.Fragment.AssociateManagement.Model;

public class Associate_collection_details_child {

    String associate_name,associate_code, 	branch, branch_code,daily_deno_sum,monthly_deno_sum,fd_deno_sum,
            tcc,loan_recovery_amount,collection_all,ncc;


    public Associate_collection_details_child(String branch, String branch_code, String daily_deno_sum, String monthly_deno_sum, String fd_deno_sum, String tcc, String loan_recovery_amount, String collection_all, String ncc,String associate_name,String associate_code) {


        this.branch = branch;
        this.branch_code = branch_code;

        this.daily_deno_sum = daily_deno_sum;
        this.monthly_deno_sum = monthly_deno_sum;
        this.fd_deno_sum = fd_deno_sum;
        this.tcc = tcc;
        this.loan_recovery_amount = loan_recovery_amount;
        this.collection_all = collection_all;
        this.ncc = ncc;
        this.associate_name=associate_name;
        this.associate_code=associate_code;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getAssociate_code() {
        return associate_code;
    }

    public void setAssociate_code(String associate_code) {
        this.associate_code = associate_code;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getDaily_deno_sum() {
        return daily_deno_sum;
    }

    public void setDaily_deno_sum(String daily_deno_sum) {
        this.daily_deno_sum = daily_deno_sum;
    }

    public String getMonthly_deno_sum() {
        return monthly_deno_sum;
    }

    public void setMonthly_deno_sum(String monthly_deno_sum) {
        this.monthly_deno_sum = monthly_deno_sum;
    }

    public String getFd_deno_sum() {
        return fd_deno_sum;
    }

    public void setFd_deno_sum(String fd_deno_sum) {
        this.fd_deno_sum = fd_deno_sum;
    }

    public String getTcc() {
        return tcc;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }

    public String getLoan_recovery_amount() {
        return loan_recovery_amount;
    }

    public void setLoan_recovery_amount(String loan_recovery_amount) {
        this.loan_recovery_amount = loan_recovery_amount;
    }

    public String getCollection_all() {
        return collection_all;
    }

    public void setCollection_all(String collection_all) {
        this.collection_all = collection_all;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }
}

