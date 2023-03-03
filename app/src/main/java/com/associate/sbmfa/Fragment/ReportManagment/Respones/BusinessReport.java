package com.associate.sbmfa.Fragment.ReportManagment.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessReport {
@SerializedName("join_date")
@Expose
private String joinDate;
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
@SerializedName("associate_code")
@Expose
private String associate_code;
@SerializedName("name")
@Expose
private String name;
@SerializedName("cadre")
@Expose
private String cadre;

@SerializedName("daily_new_ac")
@Expose
private Integer dailyNewAc;

@SerializedName("daily_deno_sum")
@Expose
private String dailyDenoSum;
@SerializedName("daily_renew_ac")
@Expose
private Integer dailyRenewAc;
@SerializedName("daily_renew")
@Expose
private String dailyRenew;
@SerializedName("monthly_new_ac")
@Expose
private Integer monthlyNewAc;
@SerializedName("monthly_deno_sum")
@Expose
private Integer monthlyDenoSum;
@SerializedName("monthly_renew_ac")
@Expose
private Integer monthlyRenewAc;
@SerializedName("monthly_renew")
@Expose
private Integer monthlyRenew;
@SerializedName("fd_new_ac")
@Expose
private Integer fdNewAc;
@SerializedName("fd_deno_sum")
@Expose
private Integer fdDenoSum;
@SerializedName("ssb_new_ac")
@Expose
private Integer ssbNewAc;
@SerializedName("ssb_deno_sum")
@Expose
private Integer ssbDenoSum;
@SerializedName("ssb_renew_ac")
@Expose
private Integer ssbRenewAc;
@SerializedName("ssb_renew")
@Expose
private Integer ssbRenew;
@SerializedName("total_ni_ac")
@Expose
private Integer totalNiAc;
@SerializedName("total_ni_amount")
@Expose
private String totalNiAmount;
@SerializedName("total_ac")
@Expose
private Integer totalAc;
@SerializedName("total_amount")
@Expose
private String totalAmount;
@SerializedName("other_mt")
@Expose
private String otherMt;
@SerializedName("other_stn")
@Expose
private String otherStn;
@SerializedName("ni_m")
@Expose
private String niM;
@SerializedName("ni")
@Expose
private String ni;
@SerializedName("tcc_m")
@Expose
private String tccM;
@SerializedName("tcc")
@Expose
private String tcc;
@SerializedName("loan_ac")
@Expose
private Integer loanAc;
@SerializedName("loan_amount")
@Expose
private String loanAmount;
@SerializedName("loan_recovery_ac")
@Expose
private Integer loanRecoveryAc;
@SerializedName("loan_recovery_amount")
@Expose
private String loanRecoveryAmount;
@SerializedName("new_associate")
@Expose
private Integer newAssociate;
@SerializedName("total_associate")
@Expose
private Integer totalAssociate;
@SerializedName("new_member")
@Expose
private Integer newMember;
@SerializedName("total_member")
@Expose
private Integer totalMember;

public String getJoinDate() {
return joinDate;
}

public void setJoinDate(String joinDate) {
this.joinDate = joinDate;
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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getCadre() {
return cadre;
}

public void setCadre(String cadre) {
this.cadre = cadre;
}

public Integer getDailyNewAc() {
return dailyNewAc;
}

public void setDailyNewAc(Integer dailyNewAc) {
this.dailyNewAc = dailyNewAc;
}

public String getDailyDenoSum() {
return dailyDenoSum;
}

public void setDailyDenoSum(String dailyDenoSum) {
this.dailyDenoSum = dailyDenoSum;
}

public Integer getDailyRenewAc() {
return dailyRenewAc;
}

public void setDailyRenewAc(Integer dailyRenewAc) {
this.dailyRenewAc = dailyRenewAc;
}

public String getDailyRenew() {
return dailyRenew;
}

public void setDailyRenew(String dailyRenew) {
this.dailyRenew = dailyRenew;
}

public Integer getMonthlyNewAc() {
return monthlyNewAc;
}

public void setMonthlyNewAc(Integer monthlyNewAc) {
this.monthlyNewAc = monthlyNewAc;
}

public Integer getMonthlyDenoSum() {
return monthlyDenoSum;
}

public void setMonthlyDenoSum(Integer monthlyDenoSum) {
this.monthlyDenoSum = monthlyDenoSum;
}

public Integer getMonthlyRenewAc() {
return monthlyRenewAc;
}

public void setMonthlyRenewAc(Integer monthlyRenewAc) {
this.monthlyRenewAc = monthlyRenewAc;
}

public Integer getMonthlyRenew() {
return monthlyRenew;
}

public void setMonthlyRenew(Integer monthlyRenew) {
this.monthlyRenew = monthlyRenew;
}

public Integer getFdNewAc() {
return fdNewAc;
}

public void setFdNewAc(Integer fdNewAc) {
this.fdNewAc = fdNewAc;
}

public Integer getFdDenoSum() {
return fdDenoSum;
}

public void setFdDenoSum(Integer fdDenoSum) {
this.fdDenoSum = fdDenoSum;
}

public Integer getSsbNewAc() {
return ssbNewAc;
}

public void setSsbNewAc(Integer ssbNewAc) {
this.ssbNewAc = ssbNewAc;
}

public Integer getSsbDenoSum() {
return ssbDenoSum;
}

public void setSsbDenoSum(Integer ssbDenoSum) {
this.ssbDenoSum = ssbDenoSum;
}

public Integer getSsbRenewAc() {
return ssbRenewAc;
}

public void setSsbRenewAc(Integer ssbRenewAc) {
this.ssbRenewAc = ssbRenewAc;
}

public Integer getSsbRenew() {
return ssbRenew;
}

public void setSsbRenew(Integer ssbRenew) {
this.ssbRenew = ssbRenew;
}

public Integer getTotalNiAc() {
return totalNiAc;
}

public void setTotalNiAc(Integer totalNiAc) {
this.totalNiAc = totalNiAc;
}

public String getTotalNiAmount() {
return totalNiAmount;
}

public void setTotalNiAmount(String totalNiAmount) {
this.totalNiAmount = totalNiAmount;
}

public Integer getTotalAc() {
return totalAc;
}

public void setTotalAc(Integer totalAc) {
this.totalAc = totalAc;
}

public String getTotalAmount() {
return totalAmount;
}

public void setTotalAmount(String totalAmount) {
this.totalAmount = totalAmount;
}

public String getOtherMt() {
return otherMt;
}

public void setOtherMt(String otherMt) {
this.otherMt = otherMt;
}

public String getOtherStn() {
return otherStn;
}

public void setOtherStn(String otherStn) {
this.otherStn = otherStn;
}

public String getNiM() {
return niM;
}

public void setNiM(String niM) {
this.niM = niM;
}

public String getNi() {
return ni;
}

public void setNi(String ni) {
this.ni = ni;
}

public String getTccM() {
return tccM;
}

public void setTccM(String tccM) {
this.tccM = tccM;
}

public String getTcc() {
return tcc;
}

public void setTcc(String tcc) {
this.tcc = tcc;
}

public Integer getLoanAc() {
return loanAc;
}

public void setLoanAc(Integer loanAc) {
this.loanAc = loanAc;
}

public String getLoanAmount() {
return loanAmount;
}

public void setLoanAmount(String loanAmount) {
this.loanAmount = loanAmount;
}

public Integer getLoanRecoveryAc() {
return loanRecoveryAc;
}

public void setLoanRecoveryAc(Integer loanRecoveryAc) {
this.loanRecoveryAc = loanRecoveryAc;
}

public String getLoanRecoveryAmount() {
return loanRecoveryAmount;
}

public void setLoanRecoveryAmount(String loanRecoveryAmount) {
this.loanRecoveryAmount = loanRecoveryAmount;
}

public Integer getNewAssociate() {
return newAssociate;
}

public void setNewAssociate(Integer newAssociate) {
this.newAssociate = newAssociate;
}

public Integer getTotalAssociate() {
return totalAssociate;
}

public void setTotalAssociate(Integer totalAssociate) {
this.totalAssociate = totalAssociate;
}

public Integer getNewMember() {
return newMember;
}

public void setNewMember(Integer newMember) {
this.newMember = newMember;
}

public Integer getTotalMember() {
return totalMember;
}

public void setTotalMember(Integer totalMember) {
this.totalMember = totalMember;
}

    public String getAssociate_code() {
        return associate_code;
    }

    public void setAssociate_code(String associate_code) {
        this.associate_code = associate_code;
    }
}