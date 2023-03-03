package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Member {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("form_number")
    @Expose
    private String formNumber;
    @SerializedName("tenure")
    @Expose
    private String tenure;
    @SerializedName("current_balance")
    @Expose
    private String currentBalance;
    @SerializedName("eli_amount")
    @Expose
    private String eliAmount;
    @SerializedName("deposite_amount")
    @Expose
    private String depositeAmount;
    @SerializedName("member_first_name")
    @Expose
    private String memberFirstName;
    @SerializedName("member_last_name")
    @Expose
    private String memberLastName;
    @SerializedName("id_proof")
    @Expose
    private String idProof;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("associate_mobile_number")
    @Expose
    private String associateMobileNumber;
    @SerializedName("associate_carder")
    @Expose
    private String associateCarder;
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("associate_name")
    @Expose
    private String associateName;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("sector_name")
    @Expose
    private String sectorName;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("zone_name")
    @Expose
    private String zoneName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("maturity_amount")
    @Expose
    private String maturityAmount;
    @SerializedName("firstId")
    @Expose
    private String firstId;
    @SerializedName("secondId")
    @Expose
    private String secondId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("village")
    @Expose
    private Object village;
    @SerializedName("pin_code")
    @Expose
    private String pinCode;
    @SerializedName("nominee_name")
    @Expose
    private String nomineeName;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("nominee_dob")
    @Expose
    private String nomineeDob;
    @SerializedName("nominee_age")
    @Expose
    private Integer nomineeAge;
    @SerializedName("nominee_gender")
    @Expose
    private String nomineeGender;
    @SerializedName("nominee_percentage")
    @Expose
    private Integer nomineePercentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getEliAmount() {
        return eliAmount;
    }

    public void setEliAmount(String eliAmount) {
        this.eliAmount = eliAmount;
    }

    public String getDepositeAmount() {
        return depositeAmount;
    }

    public void setDepositeAmount(String depositeAmount) {
        this.depositeAmount = depositeAmount;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(String associateCode) {
        this.associateCode = associateCode;
    }

    public String getAssociateMobileNumber() {
        return associateMobileNumber;
    }

    public void setAssociateMobileNumber(String associateMobileNumber) {
        this.associateMobileNumber = associateMobileNumber;
    }

    public String getAssociateCarder() {
        return associateCarder;
    }

    public void setAssociateCarder(String associateCarder) {
        this.associateCarder = associateCarder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(String maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public String getSecondId() {
        return secondId;
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getVillage() {
        return village;
    }

    public void setVillage(Object village) {
        this.village = village;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNomineeDob() {
        return nomineeDob;
    }

    public void setNomineeDob(String nomineeDob) {
        this.nomineeDob = nomineeDob;
    }

    public Integer getNomineeAge() {
        return nomineeAge;
    }

    public void setNomineeAge(Integer nomineeAge) {
        this.nomineeAge = nomineeAge;
    }

    public String getNomineeGender() {
        return nomineeGender;
    }

    public void setNomineeGender(String nomineeGender) {
        this.nomineeGender = nomineeGender;
    }

    public Integer getNomineePercentage() {
        return nomineePercentage;
    }

    public void setNomineePercentage(Integer nomineePercentage) {
        this.nomineePercentage = nomineePercentage;
    }
}