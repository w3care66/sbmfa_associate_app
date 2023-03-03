package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Detail {

@SerializedName("loan_details")
@Expose
private LoanDetails loanDetails;
@SerializedName("applicant_deposite_detail")
@Expose
private List<ApplicantDepositeDetail> applicantDepositeDetail = null;
@SerializedName("applicant")
@Expose
private List<Applicant> applicant = null;

public LoanDetails getLoanDetails() {
return loanDetails;
}

public void setLoanDetails(LoanDetails loanDetails) {
this.loanDetails = loanDetails;
}

public List<ApplicantDepositeDetail> getApplicantDepositeDetail() {
return applicantDepositeDetail;
}

public void setApplicantDepositeDetail(List<ApplicantDepositeDetail> applicantDepositeDetail) {
this.applicantDepositeDetail = applicantDepositeDetail;
}

public List<Applicant> getApplicant() {
return applicant;
}

public void setApplicant(List<Applicant> applicant) {
this.applicant = applicant;
}

}