package com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TreeAssociate {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("associate_carder")
@Expose
private String associateCarder;
@SerializedName("status_name")
@Expose
private String statusName;
@SerializedName("is_inactive")
@Expose
private Integer isInactive;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

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

public String getAssociateCarder() {
return associateCarder;
}

public void setAssociateCarder(String associateCarder) {
this.associateCarder = associateCarder;
}

public String getStatusName() {
return statusName;
}

public void setStatusName(String statusName) {
this.statusName = statusName;
}

public Integer getIsInactive() {
return isInactive;
}

public void setIsInactive(Integer isInactive) {
this.isInactive = isInactive;
}

}
