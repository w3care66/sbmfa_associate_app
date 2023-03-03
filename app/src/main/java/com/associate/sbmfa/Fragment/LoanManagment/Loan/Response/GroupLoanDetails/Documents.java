package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import java.util.List;
        
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Documents {

    @SerializedName("id_proof")
    @Expose
    private String idProof;
    @SerializedName("id_no")
    @Expose
    private String idNo;
    @SerializedName("upload_file")
    @Expose
    private List<UploadFile> uploadFile = null;
    @SerializedName("address_proof")
    @Expose
    private String addressProof;
    @SerializedName("address_id_proof")
    @Expose
    private String addressIdProof;
    @SerializedName("address_upload_file")
    @Expose
    private List<AddressUploadFile> addressUploadFile = null;
    @SerializedName("income")
    @Expose
    private String income;
    @SerializedName("income_remark")
    @Expose
    private String incomeRemark;
    @SerializedName("income_upload_file")
    @Expose
    private List<IncomeUploadFile> incomeUploadFile = null;
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

    public List<UploadFile> getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(List<UploadFile> uploadFile) {
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

    public List<AddressUploadFile> getAddressUploadFile() {
        return addressUploadFile;
    }

    public void setAddressUploadFile(List<AddressUploadFile> addressUploadFile) {
        this.addressUploadFile = addressUploadFile;
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

    public List<IncomeUploadFile> getIncomeUploadFile() {
        return incomeUploadFile;
    }

    public void setIncomeUploadFile(List<IncomeUploadFile> incomeUploadFile) {
        this.incomeUploadFile = incomeUploadFile;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

}