package com.associate.sbmfa.Fragment.LoanManagment.Loan.Model;

public class ApplicantDepositeDetailModel {
  String   Scheme,	AccountID,	OpenDate,	DueDate,	Deposit,Tenure,	LoanAmount;

    public ApplicantDepositeDetailModel(String scheme, String accountID, String openDate, String dueDate, String deposit, String tenure, String loanAmount) {
        Scheme = scheme;
        AccountID = accountID;
        OpenDate = openDate;
        DueDate = dueDate;
        Deposit = deposit;
        Tenure = tenure;
        LoanAmount = loanAmount;
    }

    public String getScheme() {
        return Scheme;
    }

    public void setScheme(String scheme) {
        Scheme = scheme;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getOpenDate() {
        return OpenDate;
    }

    public void setOpenDate(String openDate) {
        OpenDate = openDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getDeposit() {
        return Deposit;
    }

    public void setDeposit(String deposit) {
        Deposit = deposit;
    }

    public String getTenure() {
        return Tenure;
    }

    public void setTenure(String tenure) {
        Tenure = tenure;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }
}

