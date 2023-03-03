package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanDetails {

@SerializedName("lable")
@Expose
private String lable;
@SerializedName("type")
@Expose
private Integer type;
@SerializedName("loan_amount")
@Expose
private String loanAmount;
@SerializedName("EMI_mode_option")
@Expose
private String eMIModeOption;
@SerializedName("loan_purpose")
@Expose
private String loanPurpose;
@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("bank_acount")
@Expose
private Object bankAcount;
@SerializedName("ifsc")
@Expose
private Object ifsc;
@SerializedName("bank_name")
@Expose
private Object bankName;
@SerializedName("applicant_id")
@Expose
private String applicantId;

public String getLable() {
return lable;
}

public void setLable(String lable) {
this.lable = lable;
}

public Integer getType() {
return type;
}

public void setType(Integer type) {
this.type = type;
}

public String getLoanAmount() {
return loanAmount;
}

public void setLoanAmount(String loanAmount) {
this.loanAmount = loanAmount;
}

public String getEMIModeOption() {
return eMIModeOption;
}

public void setEMIModeOption(String eMIModeOption) {
this.eMIModeOption = eMIModeOption;
}

public String getLoanPurpose() {
return loanPurpose;
}

public void setLoanPurpose(String loanPurpose) {
this.loanPurpose = loanPurpose;
}

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public Object getBankAcount() {
return bankAcount;
}

public void setBankAcount(Object bankAcount) {
this.bankAcount = bankAcount;
}

public Object getIfsc() {
return ifsc;
}

public void setIfsc(Object ifsc) {
this.ifsc = ifsc;
}

public Object getBankName() {
return bankName;
}

public void setBankName(Object bankName) {
this.bankName = bankName;
}

public String getApplicantId() {
return applicantId;
}

public void setApplicantId(String applicantId) {
this.applicantId = applicantId;
}

}