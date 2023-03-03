package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

public class Investment_Plan_Details_child_model {
    String MemberRegisteredDate, memberI_d, membermobilenumber, associateCode, accountNumber, tenture, balance, eliAmount, depositeAmount, state, district,
            city, village, pin, first, second,address,associate_name,plan,form_number,created_at;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getForm_number() {
        return form_number;
    }

    public void setForm_number(String form_number) {
        this.form_number = form_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Investment_Plan_Details_child_model(String memberRegisteredDate, String memberI_d, String membermobilenumber, String associateCode, String accountNumber, String tenture, String balance, String eliAmount, String depositeAmount, String state, String district, String city, String village, String pin, String first, String second, String address, String associate_name, String plan, String form_number, String created_at) {
        MemberRegisteredDate = memberRegisteredDate;
        this.memberI_d = memberI_d;
        this.membermobilenumber = membermobilenumber;
        this.associateCode = associateCode;
        this.accountNumber = accountNumber;
        this.tenture = tenture;
        this.balance = balance;
        this.eliAmount = eliAmount;
        this.depositeAmount = depositeAmount;
        this.address = address;
        this.state = state;
        this.district = district;
        this.city = city;
        this.village = village;
        this.pin = pin;
        this.first = first;
        this.second = second;
        this.associate_name=associate_name;
        this.plan=plan;
        this.form_number=form_number;
        this.created_at=created_at;

    }

    public String getMemberRegisteredDate() {
        return MemberRegisteredDate;
    }

    public void setMemberRegisteredDate(String memberRegisteredDate) {
        MemberRegisteredDate = memberRegisteredDate;
    }

    public String getMemberI_d() {
        return memberI_d;
    }

    public void setMemberI_d(String memberI_d) {
        this.memberI_d = memberI_d;
    }

    public String getMembermobilenumber() {
        return membermobilenumber;
    }

    public void setMembermobilenumber(String membermobilenumber) {
        this.membermobilenumber = membermobilenumber;
    }

    public String getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(String associateCode) {
        this.associateCode = associateCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTenture() {
        return tenture;
    }

    public void setTenture(String tenture) {
        this.tenture = tenture;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEliAmount() {
        return eliAmount;
    }

    public void setEliAmount(String eliAmount) {
        this.eliAmount = eliAmount;
    }

    public String getDepositeAmount() {
        return depositeAmount;
    }

    public void setDepositeAmount(String depositeAmount) {
        this.depositeAmount = depositeAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}