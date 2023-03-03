package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Renew {

@SerializedName("created_at")
@Expose
private String createdAt;

@SerializedName("id")
@Expose
private Integer id;

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
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("member")
@Expose
private String member;
@SerializedName("plan")
@Expose
private String plan;
@SerializedName("tenure")
@Expose
private String tenure;
@SerializedName("amount")
@Expose
private String amount;
@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("payment_mode")
@Expose
private String paymentMode;

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
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

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public String getMember() {
return member;
}

public void setMember(String member) {
this.member = member;
}

public String getPlan() {
return plan;
}

public void setPlan(String plan) {
this.plan = plan;
}

public String getTenure() {
return tenure;
}

public void setTenure(String tenure) {
this.tenure = tenure;
}

public String getAmount() {
return amount;
}

public void setAmount(String amount) {
this.amount = amount;
}

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getPaymentMode() {
return paymentMode;
}

public void setPaymentMode(String paymentMode) {
this.paymentMode = paymentMode;
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}