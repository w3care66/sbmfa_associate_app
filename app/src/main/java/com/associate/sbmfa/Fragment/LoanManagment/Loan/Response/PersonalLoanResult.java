package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PersonalLoanResult {

@SerializedName("detail")
@Expose
private Detail detail;

public Detail getDetail() {
return detail;
}

public void setDetail(Detail detail) {
this.detail = detail;
}

}