package com.associate.sbmfa.Fragment.LoanManagment.Respones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAssociateDetails {

@SerializedName("name")
@Expose
private String name;
@SerializedName("memberId")
@Expose
private Integer memberId;
@SerializedName("ssbAccountNumber")
@Expose
private String ssbAccountNumber;
@SerializedName("ssbAmount")
@Expose
private Integer ssbAmount;
@SerializedName("ssbId")
@Expose
private Integer ssbId;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getMemberId() {
return memberId;
}

public void setMemberId(Integer memberId) {
this.memberId = memberId;
}

public String getSsbAccountNumber() {
return ssbAccountNumber;
}

public void setSsbAccountNumber(String ssbAccountNumber) {
this.ssbAccountNumber = ssbAccountNumber;
}

public Integer getSsbAmount() {
return ssbAmount;
}

public void setSsbAmount(Integer ssbAmount) {
this.ssbAmount = ssbAmount;
}

public Integer getSsbId() {
return ssbId;
}

public void setSsbId(Integer ssbId) {
this.ssbId = ssbId;
}

}