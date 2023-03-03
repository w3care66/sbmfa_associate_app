package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class GuarantorApplicant {

    @SerializedName("details")
    @Expose
    private Details__2 details;
    @SerializedName("employment_details")
    @Expose
    private EmploymentDetails__2 employmentDetails;
    @SerializedName("bank_detail")
    @Expose
    private BankDetail__2 bankDetail;
    @SerializedName("documents")
    @Expose
    private Documents__2 documents;

    public Details__2 getDetails() {
        return details;
    }

    public void setDetails(Details__2 details) {
        this.details = details;
    }

    public EmploymentDetails__2 getEmploymentDetails() {
        return employmentDetails;
    }

    public void setEmploymentDetails(EmploymentDetails__2 employmentDetails) {
        this.employmentDetails = employmentDetails;
    }

    public BankDetail__2 getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail__2 bankDetail) {
        this.bankDetail = bankDetail;
    }

    public Documents__2 getDocuments() {
        return documents;
    }

    public void setDocuments(Documents__2 documents) {
        this.documents = documents;
    }

}