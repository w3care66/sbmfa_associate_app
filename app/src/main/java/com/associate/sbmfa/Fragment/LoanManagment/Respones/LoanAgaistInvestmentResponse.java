package com.associate.sbmfa.Fragment.LoanManagment.Respones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoanAgaistInvestmentResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("code")
@Expose
private Integer code;
@SerializedName("messages")
@Expose
private String messages;
@SerializedName("result")
@Expose
private LoanAgainstInvestmentResult result;
@SerializedName("associate_status")
@Expose
private Object associateStatus;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public String getMessages() {
return messages;
}

public void setMessages(String messages) {
this.messages = messages;
}

public LoanAgainstInvestmentResult getResult() {
return result;
}

public void setResult(LoanAgainstInvestmentResult result) {
this.result = result;
}

public Object getAssociateStatus() {
return associateStatus;
}

public void setAssociateStatus(Object associateStatus) {
this.associateStatus = associateStatus;
}

}