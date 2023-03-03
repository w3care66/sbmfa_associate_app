package com.associate.sbmfa.Fragment.LoanManagment.Loan.Model;

public class GroupLoanCoApplicateModel {

    String MemberID,	MemberName,	FatherName,	Amount,	BankName,	SSBAccount,	IFSCCode;

    public GroupLoanCoApplicateModel(String memberID, String memberName, String fatherName, String amount, String bankName, String SSBAccount, String IFSCCode) {
        MemberID = memberID;
        MemberName = memberName;
        FatherName = fatherName;
        Amount = amount;
        BankName = bankName;
        this.SSBAccount = SSBAccount;
        this.IFSCCode = IFSCCode;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getSSBAccount() {
        return SSBAccount;
    }

    public void setSSBAccount(String SSBAccount) {
        this.SSBAccount = SSBAccount;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }
}
