package com.associate.sbmfa.Fragment.LoanManagment.Respones.Recovery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanRecovery {


    @SerializedName("id")
    @Expose
    private String id;

@SerializedName("branch_name")
@Expose
private String branchName;
@SerializedName("branch_code")
@Expose
private Integer branchCode;
@SerializedName("sector_name")
@Expose
private String sectorName;
@SerializedName("region_name")
@Expose
private String regionName;
@SerializedName("zone_name")
@Expose
private String zoneName;
@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("member_name")
@Expose
private String memberName;
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("loan_type")
@Expose
private String loanType;
@SerializedName("tenure")
@Expose
private String tenure;
@SerializedName("file_charge")
@Expose
private String fileCharge;
@SerializedName("file_charges_payment_mode")
@Expose
private String fileChargesPaymentMode;
@SerializedName("loan_amount")
@Expose
private String loanAmount;
@SerializedName("outstanding_amount")
@Expose
private String outstandingAmount;

@SerializedName("transfer_amount")
@Expose
private String transfer_amount;

@SerializedName("last_recovery_date")
@Expose
private String lastRecoveryDate;
@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("total_payment")
@Expose
private String totalPayment;
@SerializedName("approve_date")
@Expose
private String approveDate;
@SerializedName("application_date")
@Expose
private String applicationDate;
@SerializedName("status")
@Expose
private String status;

public String getBranchName() {
return branchName;
}

public void setBranchName(String branchName) {
this.branchName = branchName;
}

public Integer getBranchCode() {
return branchCode;
}

public void setBranchCode(Integer branchCode) {
this.branchCode = branchCode;
}

public String getSectorName() {
return sectorName;
}

public void setSectorName(String sectorName) {
this.sectorName = sectorName;
}

public String getRegionName() {
return regionName;
}

public void setRegionName(String regionName) {
this.regionName = regionName;
}

public String getZoneName() {
return zoneName;
}

public void setZoneName(String zoneName) {
this.zoneName = zoneName;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public String getMemberName() {
return memberName;
}

public void setMemberName(String memberName) {
this.memberName = memberName;
}

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

public String getLoanType() {
return loanType;
}

public void setLoanType(String loanType) {
this.loanType = loanType;
}

public String getTenure() {
return tenure;
}

public void setTenure(String tenure) {
this.tenure = tenure;
}

public String getFileCharge() {
return fileCharge;
}

public void setFileCharge(String fileCharge) {
this.fileCharge = fileCharge;
}

public String getFileChargesPaymentMode() {
return fileChargesPaymentMode;
}

public void setFileChargesPaymentMode(String fileChargesPaymentMode) {
this.fileChargesPaymentMode = fileChargesPaymentMode;
}

public String getLoanAmount() {
return loanAmount;
}

public void setLoanAmount(String loanAmount) {
this.loanAmount = loanAmount;
}

public String getOutstandingAmount() {
return outstandingAmount;
}

public void setOutstandingAmount(String outstandingAmount) {
this.outstandingAmount = outstandingAmount;
}

public String getLastRecoveryDate() {
return lastRecoveryDate;
}

public void setLastRecoveryDate(String lastRecoveryDate) {
this.lastRecoveryDate = lastRecoveryDate;
}

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getTotalPayment() {
return totalPayment;
}

public void setTotalPayment(String totalPayment) {
this.totalPayment = totalPayment;
}

public String getApproveDate() {
return approveDate;
}

public void setApproveDate(String approveDate) {
this.approveDate = approveDate;
}

public String getApplicationDate() {
return applicationDate;
}

public void setApplicationDate(String applicationDate) {
this.applicationDate = applicationDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(String transfer_amount) {
        this.transfer_amount = transfer_amount;
    }
}