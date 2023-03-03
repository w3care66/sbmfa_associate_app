package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName; 

public class Applicant {

@SerializedName("details")
@Expose
private Details details;
@SerializedName("employment_details")
@Expose
private EmploymentDetails employmentDetails;
@SerializedName("bank_detail")
@Expose
private BankDetail bankDetail;
@SerializedName("documents")
@Expose
private Documents documents;

public Details getDetails() {
return details;
}

public void setDetails(Details details) {
this.details = details;
}

public EmploymentDetails getEmploymentDetails() {
return employmentDetails;
}

public void setEmploymentDetails(EmploymentDetails employmentDetails) {
this.employmentDetails = employmentDetails;
}

public BankDetail getBankDetail() {
return bankDetail;
}

public void setBankDetail(BankDetail bankDetail) {
this.bankDetail = bankDetail;
}

public Documents getDocuments() {
return documents;
}

public void setDocuments(Documents documents) {
this.documents = documents;
}

}
 




