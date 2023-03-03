package com.associate.sbmfa.Fragment.LoanManagment.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Branch {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("branch_code")
@Expose
private Integer branchCode;
@SerializedName("name")
@Expose
private String name;
@SerializedName("sector")
@Expose
private String sector;
@SerializedName("name_code")
@Expose
private String nameCode;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getBranchCode() {
return branchCode;
}

public void setBranchCode(Integer branchCode) {
this.branchCode = branchCode;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getSector() {
return sector;
}

public void setSector(String sector) {
this.sector = sector;
}

public String getNameCode() {
return nameCode;
}

public void setNameCode(String nameCode) {
this.nameCode = nameCode;
}

}