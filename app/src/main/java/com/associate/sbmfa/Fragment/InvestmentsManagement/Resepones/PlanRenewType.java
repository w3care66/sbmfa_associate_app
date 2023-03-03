package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class PlanRenewType {
@SerializedName("value")
@Expose
private Integer value;
@SerializedName("name")
@Expose
private String name;

public Integer getValue() {
return value;
}

public void setValue(Integer value) {
this.value = value;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

}