package com.associate.sbmfa.Fragment.AssociateManagement.Model;

public class InvestmentCommisionChildModel {
    String date, investmentAccount,planName,TotalAmount,CoommisionAmount,percentage,carderName,EmiNo,emiamount,CommisionType,AssociateExists,PaymentType,paymentDistribute;

    public InvestmentCommisionChildModel(String date, String investmentAccount, String planName, String totalAmount, String coommisionAmount, String percentage, String carderName, String emiNo, String emiamount, String commisionType, String associateExists, String paymentType, String paymentDistribute) {
        this.date = date;
        this.investmentAccount = investmentAccount;
        this.planName = planName;
        TotalAmount = totalAmount;
        CoommisionAmount = coommisionAmount;
        this.percentage = percentage;
        this.carderName = carderName;
        EmiNo = emiNo;
        this.emiamount = emiamount;
        CommisionType = commisionType;
        AssociateExists = associateExists;
        PaymentType = paymentType;
        this.paymentDistribute = paymentDistribute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(String investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getCoommisionAmount() {
        return CoommisionAmount;
    }

    public void setCoommisionAmount(String coommisionAmount) {
        CoommisionAmount = coommisionAmount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getCarderName() {
        return carderName;
    }

    public void setCarderName(String carderName) {
        this.carderName = carderName;
    }

    public String getEmiNo() {
        return EmiNo;
    }

    public void setEmiNo(String emiNo) {
        EmiNo = emiNo;
    }

    public String getEmiamount() {
        return emiamount;
    }

    public void setEmiamount(String emiamount) {
        this.emiamount = emiamount;
    }

    public String getCommisionType() {
        return CommisionType;
    }

    public void setCommisionType(String commisionType) {
        CommisionType = commisionType;
    }

    public String getAssociateExists() {
        return AssociateExists;
    }

    public void setAssociateExists(String associateExists) {
        AssociateExists = associateExists;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getPaymentDistribute() {
        return paymentDistribute;
    }

    public void setPaymentDistribute(String paymentDistribute) {
        this.paymentDistribute = paymentDistribute;
    }
}
