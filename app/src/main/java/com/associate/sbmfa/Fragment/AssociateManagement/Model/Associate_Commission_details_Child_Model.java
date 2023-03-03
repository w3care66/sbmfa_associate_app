package com.associate.sbmfa.Fragment.AssociateManagement.Model;

 
public class Associate_Commission_details_Child_Model {

    String AssociateName, AssociateCode, AssociateCader, BranchName, BranchCode, CommissionPayment, lagderId, memberId,PANNo,TotalAmount,TDSAmount,FinalPayableAmount,TotalCollection,FuelAmount,SSBAccountNo,Status,CreatedDate;

    public Associate_Commission_details_Child_Model(String associateName, String associateCode, String associateCader, String branchName, String branchCode, String commissionPayment, String lagderId, String memberId, String PANNo, String totalAmount, String TDSAmount, String finalPayableAmount, String totalCollection, String fuelAmount, String SSBAccountNo, String status, String createdDate) {
        this.AssociateName = associateName;
        this.AssociateCode = associateCode;
        this.AssociateCader = associateCader;
        this.BranchName = branchName;
        this.BranchCode = branchCode;
        this.CommissionPayment = commissionPayment;
        this.lagderId = lagderId;
        this.memberId = memberId;
        this.PANNo = PANNo;
        this.TotalAmount = totalAmount;
        this.TDSAmount = TDSAmount;
        this.FinalPayableAmount = finalPayableAmount;
        this.TotalCollection = totalCollection;
        this.FuelAmount = fuelAmount;
        this.SSBAccountNo = SSBAccountNo;
        this.Status = status;
        this.CreatedDate = createdDate;
    }

    public String getAssociateName() {
        return AssociateName;
    }

    public void setAssociateName(String associateName) {
        AssociateName = associateName;
    }

    public String getAssociateCode() {
        return AssociateCode;
    }

    public void setAssociateCode(String associateCode) {
        AssociateCode = associateCode;
    }

    public String getAssociateCader() {
        return AssociateCader;
    }

    public void setAssociateCader(String associateCader) {
        AssociateCader = associateCader;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getCommissionPayment() {
        return CommissionPayment;
    }

    public void setCommissionPayment(String commissionPayment) {
        CommissionPayment = commissionPayment;
    }

    public String getLagderId() {
        return lagderId;
    }

    public void setLagderId(String lagderId) {
        this.lagderId = lagderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPANNo() {
        return PANNo;
    }

    public void setPANNo(String PANNo) {
        this.PANNo = PANNo;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getTDSAmount() {
        return TDSAmount;
    }

    public void setTDSAmount(String TDSAmount) {
        this.TDSAmount = TDSAmount;
    }

    public String getFinalPayableAmount() {
        return FinalPayableAmount;
    }

    public void setFinalPayableAmount(String finalPayableAmount) {
        FinalPayableAmount = finalPayableAmount;
    }

    public String getTotalCollection() {
        return TotalCollection;
    }

    public void setTotalCollection(String totalCollection) {
        TotalCollection = totalCollection;
    }

    public String getFuelAmount() {
        return FuelAmount;
    }

    public void setFuelAmount(String fuelAmount) {
        FuelAmount = fuelAmount;
    }

    public String getSSBAccountNo() {
        return SSBAccountNo;
    }

    public void setSSBAccountNo(String SSBAccountNo) {
        this.SSBAccountNo = SSBAccountNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}