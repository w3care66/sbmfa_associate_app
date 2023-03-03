package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanLedgerTranscation {
@SerializedName("is_ssb")
@Expose
private Integer isSsb;
@SerializedName("tranid")
@Expose
private Integer tranid;
@SerializedName("date")
@Expose
private String date;
@SerializedName("description")
@Expose
private String description;
@SerializedName("reference_no")
@Expose
private String referenceNo;
@SerializedName("withdrawal")
@Expose
private String withdrawal;
@SerializedName("deposit")
@Expose
private String deposit;
@SerializedName("opening_balance")
@Expose
private String openingBalance;

public Integer getIsSsb() {
return isSsb;
}

public void setIsSsb(Integer isSsb) {
this.isSsb = isSsb;
}

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

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getReferenceNo() {
return referenceNo;
}

public void setReferenceNo(String referenceNo) {
this.referenceNo = referenceNo;
}

public String getWithdrawal() {
return withdrawal;
}

public void setWithdrawal(String withdrawal) {
this.withdrawal = withdrawal;
}

public String getDeposit() {
return deposit;
}

public void setDeposit(String deposit) {
this.deposit = deposit;
}

public String getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(String openingBalance) {
this.openingBalance = openingBalance;
}

}