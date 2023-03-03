package com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LoanRecovery {
@SerializedName("branch_name")
@Expose
private String branchName;
@SerializedName("branch_code")
@Expose
private Integer branchCode;
@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("loan_type")
@Expose
private String loanType;
@SerializedName("member_name")
@Expose
private String memberName;
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("associate_id")
@Expose
private String associateId;
@SerializedName("payment_date")
@Expose
private String paymentDate;
@SerializedName("payment_mode")
@Expose
private String paymentMode;
@SerializedName("description")
@Expose
private String description;
@SerializedName("penalty")
@Expose
private String penalty;
@SerializedName("deposite")
@Expose
private String deposite;
@SerializedName("roi_amount")
@Expose
private String roiAmount;
@SerializedName("principal_amount")
@Expose
private String principalAmount;
@SerializedName("opening_balance")
@Expose
private String openingBalance;

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

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public String getLoanType() {
return loanType;
}

public void setLoanType(String loanType) {
this.loanType = loanType;
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

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getAssociateId() {
return associateId;
}

public void setAssociateId(String associateId) {
this.associateId = associateId;
}

public String getPaymentDate() {
return paymentDate;
}

public void setPaymentDate(String paymentDate) {
this.paymentDate = paymentDate;
}

public String getPaymentMode() {
return paymentMode;
}

public void setPaymentMode(String paymentMode) {
this.paymentMode = paymentMode;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getPenalty() {
return penalty;
}

public void setPenalty(String penalty) {
this.penalty = penalty;
}

public String getDeposite() {
return deposite;
}

public void setDeposite(String deposite) {
this.deposite = deposite;
}

public String getRoiAmount() {
return roiAmount;
}

public void setRoiAmount(String roiAmount) {
this.roiAmount = roiAmount;
}

public String getPrincipalAmount() {
return principalAmount;
}

public void setPrincipalAmount(String principalAmount) {
this.principalAmount = principalAmount;
}

public String getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(String openingBalance) {
this.openingBalance = openingBalance;
}

}