package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import java.util.List;
        
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Documents__1 {

    @SerializedName("id_proof")
    @Expose
    private String idProof;

    @SerializedName("id_no")
    @Expose
    private String idNo;

    @SerializedName("upload_file")
    @Expose
    private List<UploadFile__1> uploadFile = null;
    @SerializedName("address_proof")
    @Expose
    private String addressProof;
    @SerializedName("address_id_proof")
    @Expose
    private String addressIdProof;
    @SerializedName("address_upload_file")
    @Expose
    private List<AddressUploadFile__1> addressUploadFile = null;
    @SerializedName("under_taking")
    @Expose
    private List<UnderTaking> underTaking = null;
    @SerializedName("under_taking _doc")
    @Expose
    private List<UnderTakingDoc> underTakingDoc = null;
    @SerializedName("income")
    @Expose
    private String income;
    @SerializedName("income_remark")
    @Expose
    private String incomeRemark;
    @SerializedName("income_upload_file")
    @Expose
    private List<IncomeUploadFile__1> incomeUploadFile = null;
    @SerializedName("security")
    @Expose
    private String security;

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public List<UploadFile__1> getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(List<UploadFile__1> uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(String addressProof) {
        this.addressProof = addressProof;
    }

    public String getAddressIdProof() {
        return addressIdProof;
    }

    public void setAddressIdProof(String addressIdProof) {
        this.addressIdProof = addressIdProof;
    }

    public List<AddressUploadFile__1> getAddressUploadFile() {
        return addressUploadFile;
    }

    public void setAddressUploadFile(List<AddressUploadFile__1> addressUploadFile) {
        this.addressUploadFile = addressUploadFile;
    }

    public List<UnderTaking> getUnderTaking() {
        return underTaking;
    }

    public void setUnderTaking(List<UnderTaking> underTaking) {
        this.underTaking = underTaking;
    }

    public List<UnderTakingDoc> getUnderTakingDoc() {
        return underTakingDoc;
    }

    public void setUnderTakingDoc(List<UnderTakingDoc> underTakingDoc) {
        this.underTakingDoc = underTakingDoc;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncomeRemark() {
        return incomeRemark;
    }

    public void setIncomeRemark(String incomeRemark) {
        this.incomeRemark = incomeRemark;
    }

    public List<IncomeUploadFile__1> getIncomeUploadFile() {
        return incomeUploadFile;
    }

    public void setIncomeUploadFile(List<IncomeUploadFile__1> incomeUploadFile) {
        this.incomeUploadFile = incomeUploadFile;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

}