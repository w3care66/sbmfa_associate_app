package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Details {

@SerializedName("applicant_id")
@Expose
private String applicantId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("father_name")
@Expose
private String fatherName;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("email_id")
@Expose
private Object emailId;
@SerializedName("permanent_address")
@Expose
private String permanentAddress;
@SerializedName("temporary_address")
@Expose
private String temporaryAddress;

public String getApplicantId() {
return applicantId;
}

public void setApplicantId(String applicantId) {
this.applicantId = applicantId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getFatherName() {
return fatherName;
}

public void setFatherName(String fatherName) {
this.fatherName = fatherName;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public Object getEmailId() {
return emailId;
}

public void setEmailId(Object emailId) {
this.emailId = emailId;
}

public String getPermanentAddress() {
return permanentAddress;
}

public void setPermanentAddress(String permanentAddress) {
this.permanentAddress = permanentAddress;
}

public String getTemporaryAddress() {
return temporaryAddress;
}

public void setTemporaryAddress(String temporaryAddress) {
this.temporaryAddress = temporaryAddress;
}

}