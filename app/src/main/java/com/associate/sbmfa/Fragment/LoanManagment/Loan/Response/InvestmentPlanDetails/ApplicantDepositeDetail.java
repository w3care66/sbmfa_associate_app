package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ApplicantDepositeDetail {

@SerializedName("scheme")
@Expose
private String scheme;
@SerializedName("account_id")
@Expose
private String accountId;
@SerializedName("open_date")
@Expose
private String openDate;
@SerializedName("due_date")
@Expose
private String dueDate;
@SerializedName("deposit")
@Expose
private String deposit;
@SerializedName("tenure")
@Expose
private Integer tenure;
@SerializedName("loan_amount")
@Expose
private Integer loanAmount;

public String getScheme() {
return scheme;
}

public void setScheme(String scheme) {
this.scheme = scheme;
}

public String getAccountId() {
return accountId;
}

public void setAccountId(String accountId) {
this.accountId = accountId;
}

public String getOpenDate() {
return openDate;
}

public void setOpenDate(String openDate) {
this.openDate = openDate;
}

public String getDueDate() {
return dueDate;
}

public void setDueDate(String dueDate) {
this.dueDate = dueDate;
}

public String getDeposit() {
return deposit;
}

public void setDeposit(String deposit) {
this.deposit = deposit;
}

public Integer getTenure() {
return tenure;
}

public void setTenure(Integer tenure) {
this.tenure = tenure;
}

public Integer getLoanAmount() {
return loanAmount;
}

public void setLoanAmount(Integer loanAmount) {
this.loanAmount = loanAmount;
}

}