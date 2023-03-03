package com.associate.sbmfa.Fragment.AssociateManagement.Response.InvestmentLoanPlan;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoanAssociateCom {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("account")
@Expose
private String account;
@SerializedName("loan_type")
@Expose
private String loanType;
@SerializedName("total_amount")
@Expose
private String totalAmount;
@SerializedName("commission_amount")
@Expose
private String commissionAmount;
@SerializedName("percentage")
@Expose
private String percentage;
@SerializedName("carder_name")
@Expose
private String carderName;
@SerializedName("commission_type")
@Expose
private String commissionType;
@SerializedName("pay_type")
@Expose
private String payType;
@SerializedName("is_distribute")
@Expose
private String isDistribute;
@SerializedName("created_at")
@Expose
private String createdAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getAccount() {
return account;
}

public void setAccount(String account) {
this.account = account;
}

public String getLoanType() {
return loanType;
}

public void setLoanType(String loanType) {
this.loanType = loanType;
}

public String getTotalAmount() {
return totalAmount;
}

public void setTotalAmount(String totalAmount) {
this.totalAmount = totalAmount;
}

public String getCommissionAmount() {
return commissionAmount;
}

public void setCommissionAmount(String commissionAmount) {
this.commissionAmount = commissionAmount;
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

public String getCommissionType() {
return commissionType;
}

public void setCommissionType(String commissionType) {
this.commissionType = commissionType;
}

public String getPayType() {
return payType;
}

public void setPayType(String payType) {
this.payType = payType;
}

public String getIsDistribute() {
return isDistribute;
}

public void setIsDistribute(String isDistribute) {
this.isDistribute = isDistribute;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

}
