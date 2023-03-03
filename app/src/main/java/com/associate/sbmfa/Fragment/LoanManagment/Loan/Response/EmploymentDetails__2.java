package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmploymentDetails__2 {

@SerializedName("occupation")
@Expose
private String occupation;
@SerializedName("organization")
@Expose
private Object organization;
@SerializedName("designation")
@Expose
private Object designation;
@SerializedName("monthly_income")
@Expose
private String monthlyIncome;
@SerializedName("year_from")
@Expose
private String yearFrom;

public String getOccupation() {
return occupation;
}

public void setOccupation(String occupation) {
this.occupation = occupation;
}

public Object getOrganization() {
return organization;
}

public void setOrganization(Object organization) {
this.organization = organization;
}

public Object getDesignation() {
return designation;
}

public void setDesignation(Object designation) {
this.designation = designation;
}

public String getMonthlyIncome() {
return monthlyIncome;
}

public void setMonthlyIncome(String monthlyIncome) {
this.monthlyIncome = monthlyIncome;
}

public String getYearFrom() {
return yearFrom;
}

public void setYearFrom(String yearFrom) {
this.yearFrom = yearFrom;
}

}