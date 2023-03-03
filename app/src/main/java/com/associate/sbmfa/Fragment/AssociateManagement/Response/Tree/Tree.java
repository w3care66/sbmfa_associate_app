package com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tree {

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
@SerializedName("senior_name")
@Expose
private String seniorName;
@SerializedName("senior_code")
@Expose
private String seniorCode;
@SerializedName("senior_carder")
@Expose
private String seniorCarder;
@SerializedName("status_name")
@Expose
private String statusName;
@SerializedName("is_inactive")
@Expose
private Integer isInactive;

    @SerializedName("branch_name")
    @Expose
    private String branch_name;

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

public String getSeniorName() {
return seniorName;
}

public void setSeniorName(String seniorName) {
this.seniorName = seniorName;
}

public String getSeniorCode() {
return seniorCode;
}

public void setSeniorCode(String seniorCode) {
this.seniorCode = seniorCode;
}

public String getSeniorCarder() {
return seniorCarder;
}

public void setSeniorCarder(String seniorCarder) {
this.seniorCarder = seniorCarder;
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

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}