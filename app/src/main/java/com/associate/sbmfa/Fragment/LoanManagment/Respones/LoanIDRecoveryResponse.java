package com.associate.sbmfa.Fragment.LoanManagment.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LoanIDRecoveryResponse {
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
private ResultLoan result;

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

public ResultLoan getResult() {
return result;
}

public void setResult(ResultLoan result) {
this.result = result;
}

}