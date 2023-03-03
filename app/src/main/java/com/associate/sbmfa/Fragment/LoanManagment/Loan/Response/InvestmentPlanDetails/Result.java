package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

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