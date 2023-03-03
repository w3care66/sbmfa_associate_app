package com.associate.sbmfa.Fragment.LoanManagment.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLoan {

@SerializedName("loanId")
@Expose
private String loanId;
@SerializedName("emiAmount")
@Expose
private String emiAmount;
@SerializedName("ssbBalance")
@Expose
private Integer ssbBalance;
@SerializedName("ssbId")
@Expose
private Integer ssbId;
@SerializedName("recoveredAmount")
@Expose
private Integer recoveredAmount;
@SerializedName("lastRecoveredAmount")
@Expose
private Integer lastRecoveredAmount;
@SerializedName("closingAmount")
@Expose
private Integer closingAmount;
@SerializedName("dueAmount")
@Expose
private Integer dueAmount;
@SerializedName("penaltyAmount")
@Expose
private String penaltyAmount;
@SerializedName("penaltyTime")
@Expose
private String penaltyTime;

public String getLoanId() {
return loanId;
}

public void setLoanId(String loanId) {
this.loanId = loanId;
}

public String getEmiAmount() {
return emiAmount;
}

public void setEmiAmount(String emiAmount) {
this.emiAmount = emiAmount;
}

public Integer getSsbBalance() {
return ssbBalance;
}

public void setSsbBalance(Integer ssbBalance) {
this.ssbBalance = ssbBalance;
}

public Integer getSsbId() {
return ssbId;
}

public void setSsbId(Integer ssbId) {
this.ssbId = ssbId;
}

public Integer getRecoveredAmount() {
return recoveredAmount;
}

public void setRecoveredAmount(Integer recoveredAmount) {
this.recoveredAmount = recoveredAmount;
}

public Integer getLastRecoveredAmount() {
return lastRecoveredAmount;
}

public void setLastRecoveredAmount(Integer lastRecoveredAmount) {
this.lastRecoveredAmount = lastRecoveredAmount;
}

public Integer getClosingAmount() {
return closingAmount;
}

public void setClosingAmount(Integer closingAmount) {
this.closingAmount = closingAmount;
}

public Integer getDueAmount() {
return dueAmount;
}

public void setDueAmount(Integer dueAmount) {
this.dueAmount = dueAmount;
}

public String getPenaltyAmount() {
return penaltyAmount;
}

public void setPenaltyAmount(String penaltyAmount) {
this.penaltyAmount = penaltyAmount;
}

public String getPenaltyTime() {
return penaltyTime;
}

public void setPenaltyTime(String penaltyTime) {
this.penaltyTime = penaltyTime;
}

}