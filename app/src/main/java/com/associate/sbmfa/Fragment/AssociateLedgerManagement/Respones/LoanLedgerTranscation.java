package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanLedgerTranscation {

@SerializedName("tranid")
@Expose
private Integer tranid;
@SerializedName("date")
@Expose
private String date;
@SerializedName("paymentMode")
@Expose
private String paymentMode;
@SerializedName("description")
@Expose
private String description;
@SerializedName("penalty")
@Expose
private String penalty;
@SerializedName("reference_no")
@Expose
private String referenceNo;
@SerializedName("deposit")
@Expose
private String deposit;
@SerializedName("roi_amount")
@Expose
private String roiAmount;
@SerializedName("principal_amount")
@Expose
private String principalAmount;
@SerializedName("opening_balance")
@Expose
private String openingBalance;

    public String getJv_amount() {
        return jv_amount;
    }

    public void setJv_amount(String jv_amount) {
        this.jv_amount = jv_amount;
    }

    @SerializedName("jv_amount")
    @Expose
    private String jv_amount;

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

public String getReferenceNo() {
return referenceNo;
}

public void setReferenceNo(String referenceNo) {
this.referenceNo = referenceNo;
}

public String getDeposit() {
return deposit;
}

public void setDeposit(String deposit) {
this.deposit = deposit;
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