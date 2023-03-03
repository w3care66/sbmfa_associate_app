package com.associate.sbmfa.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class OTPSendResponse {
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
private String result;
@SerializedName("token")
@Expose
private String token;
@SerializedName("associate_status")
@Expose
private Integer associateStatus;
    @SerializedName("versionCode")
    @Expose
    private String versionCode;

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

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMessages() {
return messages;
}

public void setMessages(String messages) {
this.messages = messages;
}

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public Integer getAssociateStatus() {
return associateStatus;
}

public void setAssociateStatus(Integer associateStatus) {
this.associateStatus = associateStatus;
}

}