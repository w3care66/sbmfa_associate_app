package com.associate.sbmfa.Fragment.MemberMangement.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateDetails {

@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

}