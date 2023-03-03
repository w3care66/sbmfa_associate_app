package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class AssociateDetail {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("associate_no")
@Expose
private String associateNo;
@SerializedName("name")
@Expose
private String name;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("carder")
@Expose
private String carder;

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

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public String getCarder() {
return carder;
}

public void setCarder(String carder) {
this.carder = carder;
}

}