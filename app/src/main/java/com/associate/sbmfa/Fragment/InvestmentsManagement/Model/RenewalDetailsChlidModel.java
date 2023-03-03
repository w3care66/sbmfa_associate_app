package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

public class RenewalDetailsChlidModel {
    String create_date,br_name,br_code,so_name,ro_name,zo_name,member_id,account_number,member_name,plan,tenure,amount,asscoiate_code,addcoiate_name,payment_mode;

    public RenewalDetailsChlidModel(String create_date, String br_name, String br_code, String so_name, String ro_name, String zo_name, String member_id, String account_number, String member_name, String plan, String tenure, String amount, String asscoiate_code, String addcoiate_name, String payment_mode) {
        this.create_date = create_date;
        this.br_name = br_name;
        this.br_code = br_code;
        this.so_name = so_name;
        this.ro_name = ro_name;
        this.zo_name = zo_name;
        this.member_id = member_id;
        this.account_number = account_number;
        this.member_name = member_name;
        this.plan = plan;
        this.tenure = tenure;
        this.amount = amount;
        this.asscoiate_code = asscoiate_code;
        this.addcoiate_name = addcoiate_name;
        this.payment_mode = payment_mode;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
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

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAsscoiate_code() {
        return asscoiate_code;
    }

    public void setAsscoiate_code(String asscoiate_code) {
        this.asscoiate_code = asscoiate_code;
    }

    public String getAddcoiate_name() {
        return addcoiate_name;
    }

    public void setAddcoiate_name(String addcoiate_name) {
        this.addcoiate_name = addcoiate_name;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }
}
