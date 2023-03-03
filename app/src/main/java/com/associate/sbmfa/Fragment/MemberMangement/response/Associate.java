package com.associate.sbmfa.Fragment.MemberMangement.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Associate {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("associate_no")
@Expose
private String associateNo;
@SerializedName("name")
@Expose
private String name;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getAssociateNo() {
return associateNo;
}

public void setAssociateNo(String associateNo) {
this.associateNo = associateNo;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

}