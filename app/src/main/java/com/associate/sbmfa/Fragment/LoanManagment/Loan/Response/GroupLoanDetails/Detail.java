package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import java.util.List;
        
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Detail {

    @SerializedName("loan_details")
    @Expose
    private LoanDetails loanDetails;
    @SerializedName("group_member_detail")
    @Expose
    private List<GroupMemberDetail> groupMemberDetail = null;
    @SerializedName("applicant")
    @Expose
    private List<Applicant> applicant = null;
    @SerializedName("co_applicant")
    @Expose
    private List<CoApplicant> coApplicant = null;
    @SerializedName("guarantor_applicant")
    @Expose
    private List<GuarantorApplicant> guarantorApplicant = null;
    @SerializedName("other_doc")
    @Expose
    private List<OtherDoc> otherDoc = null;

    public LoanDetails getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
    }

    public List<GroupMemberDetail> getGroupMemberDetail() {
        return groupMemberDetail;
    }

    public void setGroupMemberDetail(List<GroupMemberDetail> groupMemberDetail) {
        this.groupMemberDetail = groupMemberDetail;
    }

    public List<Applicant> getApplicant() {
        return applicant;
    }

    public void setApplicant(List<Applicant> applicant) {
        this.applicant = applicant;
    }

    public List<CoApplicant> getCoApplicant() {
        return coApplicant;
    }

    public void setCoApplicant(List<CoApplicant> coApplicant) {
        this.coApplicant = coApplicant;
    }

    public List<GuarantorApplicant> getGuarantorApplicant() {
        return guarantorApplicant;
    }

    public void setGuarantorApplicant(List<GuarantorApplicant> guarantorApplicant) {
        this.guarantorApplicant = guarantorApplicant;
    }

    public List<OtherDoc> getOtherDoc() {
        return otherDoc;
    }

    public void setOtherDoc(List<OtherDoc> otherDoc) {
        this.otherDoc = otherDoc;
    }

}