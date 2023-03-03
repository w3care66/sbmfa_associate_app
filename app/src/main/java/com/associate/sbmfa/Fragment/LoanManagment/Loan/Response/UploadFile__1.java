package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UploadFile__1 {

@SerializedName("path")
@Expose
private String path;
@SerializedName("name")
@Expose
private String name;

public String getPath() {
return path;
}

public void setPath(String path) {
this.path = path;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

}