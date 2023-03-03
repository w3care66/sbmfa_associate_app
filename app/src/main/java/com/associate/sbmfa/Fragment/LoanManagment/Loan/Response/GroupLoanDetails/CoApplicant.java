package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CoApplicant {

    @SerializedName("details")
    @Expose
    private Details__1 details;
    @SerializedName("employment_details")
    @Expose
    private EmploymentDetails__1 employmentDetails;
    @SerializedName("bank_detail")
    @Expose
    private BankDetail__1 bankDetail;
    @SerializedName("documents")
    @Expose
    private Documents__1 documents;

    public Details__1 getDetails() {
        return details;
    }

    public void setDetails(Details__1 details) {
        this.details = details;
    }

    public EmploymentDetails__1 getEmploymentDetails() {
        return employmentDetails;
    }

    public void setEmploymentDetails(EmploymentDetails__1 employmentDetails) {
        this.employmentDetails = employmentDetails;
    }

    public BankDetail__1 getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail__1 bankDetail) {
        this.bankDetail = bankDetail;
    }

    public Documents__1 getDocuments() {
        return documents;
    }

    public void setDocuments(Documents__1 documents) {
        this.documents = documents;
    }

}