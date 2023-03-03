package com.associate.sbmfa.Fragment.LoanManagment.Model;

public class Loan_recovery_List_Child_model {
    String  id ,
            account_number,
            applicant_id ,
            br_name,br_code,so_name,
            ro_name,zo_name,
            member_id,member_name,
            last_recovery_date,associate_code,
            associate_name,loan_type,amount,
            status,approved_date,application_date,
            group_loan_id,loan_amount,filecharge,filechargepaymentmode,outstandingAmount,totalamount,tenure,transfer_amount;


    public Loan_recovery_List_Child_model(String id, String account_number, String applicant_id, String br_name, String br_code, String so_name, String ro_name, String zo_name, String member_id, String member_name, String last_recovery_date, String associate_code, String associate_name, String loan_type, String amount, String status, String approved_date, String application_date, String group_loan_id, String loan_amount, String filecharge, String filechargepaymentmode, String outstandingAmount, String totalamount, String tenure,String transfer_amount) {
        this.id = id;
        this.account_number = account_number;
        this.applicant_id = applicant_id;
        this.br_name = br_name;
        this.br_code = br_code;
        this.so_name = so_name;
        this.ro_name = ro_name;
        this.zo_name = zo_name;
        this.member_id = member_id;
        this.member_name = member_name;
        this.last_recovery_date = last_recovery_date;
        this.associate_code = associate_code;
        this.associate_name = associate_name;
        this.loan_type = loan_type;
        this.amount = amount;
        this.status = status;
        this.approved_date = approved_date;
        this.application_date = application_date;
        this.group_loan_id = group_loan_id;
        this.loan_amount = loan_amount;
        this.filecharge = filecharge;
        this.filechargepaymentmode = filechargepaymentmode;
        this.outstandingAmount = outstandingAmount;
        this.totalamount = totalamount;
        this.tenure = tenure;
        this.transfer_amount = transfer_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
    }

    public String getBr_name() {
        return br_name;
    }

    public void setBr_name(String br_name) {
        this.br_name = br_name;
    }

    public String getBr_code() {
        return br_code;
    }

    public void setBr_code(String br_code) {
        this.br_code = br_code;
    }

    public String getSo_name() {
        return so_name;
    }

    public void setSo_name(String so_name) {
        this.so_name = so_name;
    }

    public String getRo_name() {
        return ro_name;
    }

    public void setRo_name(String ro_name) {
        this.ro_name = ro_name;
    }

    public String getZo_name() {
        return zo_name;
    }

    public void setZo_name(String zo_name) {
        this.zo_name = zo_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getLast_recovery_date() {
        return last_recovery_date;
    }

    public void setLast_recovery_date(String last_recovery_date) {
        this.last_recovery_date = last_recovery_date;
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

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(String approved_date) {
        this.approved_date = approved_date;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }

    public String getGroup_loan_id() {
        return group_loan_id;
    }

    public void setGroup_loan_id(String group_loan_id) {
        this.group_loan_id = group_loan_id;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getFilecharge() {
        return filecharge;
    }

    public void setFilecharge(String filecharge) {
        this.filecharge = filecharge;
    }

    public String getFilechargepaymentmode() {
        return filechargepaymentmode;
    }

    public void setFilechargepaymentmode(String filechargepaymentmode) {
        this.filechargepaymentmode = filechargepaymentmode;
    }

    public String getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(String outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }


    public String getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(String transfer_amount) {
        this.transfer_amount = transfer_amount;
    }
}
