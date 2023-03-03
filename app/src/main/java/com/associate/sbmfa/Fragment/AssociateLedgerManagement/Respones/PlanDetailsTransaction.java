package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanDetailsTransaction {

@SerializedName("tranid")
@Expose
private Integer tranid;
@SerializedName("date")
@Expose
private String date;
@SerializedName("name")
@Expose
private String name;
@SerializedName("account_no")
@Expose
private String accountNo;
@SerializedName("description")
@Expose
private String description;
@SerializedName("payment_mode")
@Expose
private String paymentMode;
@SerializedName("deposit")
@Expose
private Object deposit;
@SerializedName("withdrawal")
@Expose
private String withdrawal;
@SerializedName("opening_balance")
@Expose
private String openingBalance;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("associate_code")
@Expose
private String associateCode;

public Integer getTranid() {
return tranid;
}

public void setTranid(Integer tranid) {
this.tranid = tranid;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAccountNo() {
return accountNo;
}

public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getPaymentMode() {
return paymentMode;
}

public void setPaymentMode(String paymentMode) {
this.paymentMode = paymentMode;
}

public Object getDeposit() {
return deposit;
}

public void setDeposit(Object deposit) {
this.deposit = deposit;
}

public String getWithdrawal() {
return withdrawal;
}

public void setWithdrawal(String withdrawal) {
this.withdrawal = withdrawal;
}

public String getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(String openingBalance) {
this.openingBalance = openingBalance;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

}