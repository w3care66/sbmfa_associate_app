package com.associate.sbmfa.Fragment.ReportManagment.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessSummaryReport {
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
    @SerializedName("kanyadhan_new_ac")
    @Expose
    private Integer kanyadhanNewAc;
    @SerializedName("kanyadhan_deno_sum")
    @Expose
    private Integer kanyadhanDenoSum;
    @SerializedName("kanyadhan_renew_ac")
    @Expose
    private Integer kanyadhanRenewAc;
    @SerializedName("kanyadhan_renew")
    @Expose
    private Integer kanyadhanRenew;
    @SerializedName("mb_new_ac")
    @Expose
    private Integer mbNewAc;
    @SerializedName("mb_deno_sum")
    @Expose
    private Integer mbDenoSum;
    @SerializedName("mb_renew_ac")
    @Expose
    private Integer mbRenewAc;
    @SerializedName("mb_renew")
    @Expose
    private Integer mbRenew;
    @SerializedName("ffd_new_ac")
    @Expose
    private Integer ffdNewAc;
    @SerializedName("ffd_deno_sum")
    @Expose
    private Integer ffdDenoSum;
    @SerializedName("frd_new_ac")
    @Expose
    private Integer frdNewAc;
    @SerializedName("frd_deno_sum")
    @Expose
    private Integer frdDenoSum;
    @SerializedName("frd_renew_ac")
    @Expose
    private Integer frdRenewAc;
    @SerializedName("frd_renew")
    @Expose
    private Integer frdRenew;
    @SerializedName("jeevan_new_ac")
    @Expose
    private Integer jeevanNewAc;
    @SerializedName("jeevan_deno_sum")
    @Expose
    private Integer jeevanDenoSum;
    @SerializedName("jeevan_renew_ac")
    @Expose
    private Integer jeevanRenewAc;
    @SerializedName("jeevan_renew")
    @Expose
    private Integer jeevanRenew;
    @SerializedName("mi_new_ac")
    @Expose
    private Integer miNewAc;
    @SerializedName("mi_deno_sum")
    @Expose
    private Integer miDenoSum;
    @SerializedName("mi_renew_ac")
    @Expose
    private Integer miRenewAc;
    @SerializedName("mi_renew")
    @Expose
    private Integer miRenew;
    @SerializedName("fd_new_ac")
    @Expose
    private Integer fdNewAc;
    @SerializedName("fd_deno_sum")
    @Expose
    private Integer fdDenoSum;
    @SerializedName("rd_new_ac")
    @Expose
    private Integer rdNewAc;
    @SerializedName("rd_deno_sum")
    @Expose
    private Integer rdDenoSum;
    @SerializedName("rd_renew_ac")
    @Expose
    private Integer rdRenewAc;
    @SerializedName("rd_renew")
    @Expose
    private Integer rdRenew;
    @SerializedName("bhavhishya_new_ac")
    @Expose
    private Integer bhavhishyaNewAc;
    @SerializedName("bhavhishya_deno_sum")
    @Expose
    private Integer bhavhishyaDenoSum;
    @SerializedName("bhavhishya_renew_ac")
    @Expose
    private Integer bhavhishyaRenewAc;
    @SerializedName("bhavhishya_renew")
    @Expose
    private Integer bhavhishyaRenew;
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
    @SerializedName("st_loan_ac")
    @Expose
    private Integer stLoanAc;
    @SerializedName("st_loan_amount")
    @Expose
    private String stLoanAmount;
    @SerializedName("pl_loan_ac")
    @Expose
    private Integer plLoanAc;
    @SerializedName("pl_loan_amount")
    @Expose
    private String plLoanAmount;
    @SerializedName("la_loan_ac")
    @Expose
    private Integer laLoanAc;
    @SerializedName("la_loan_amount")
    @Expose
    private String laLoanAmount;
    @SerializedName("gp_loan_ac")
    @Expose
    private Integer gpLoanAc;
    @SerializedName("gp_loan_amount")
    @Expose
    private String gpLoanAmount;
    @SerializedName("loan_ac")
    @Expose
    private Integer loanAc;
    @SerializedName("loan_amount")
    @Expose
    private Integer loanAmount;
    @SerializedName("st_loan_recovery_ac")
    @Expose
    private Integer stLoanRecoveryAc;
    @SerializedName("st_loan_recovery_amount")
    @Expose
    private String stLoanRecoveryAmount;
    @SerializedName("pl_loan_recovery_ac")
    @Expose
    private Integer plLoanRecoveryAc;
    @SerializedName("pl_loan_recovery_amount")
    @Expose
    private String plLoanRecoveryAmount;
    @SerializedName("la_loan_recovery_ac")
    @Expose
    private Integer laLoanRecoveryAc;
    @SerializedName("la_loan_recovery_amount")
    @Expose
    private String laLoanRecoveryAmount;
    @SerializedName("gp_loan_recovery_ac")
    @Expose
    private Integer gpLoanRecoveryAc;
    @SerializedName("gp_loan_recovery_amount")
    @Expose
    private String gpLoanRecoveryAmount;
    @SerializedName("loan_recovery_ac")
    @Expose
    private Integer loanRecoveryAc;
    @SerializedName("loan_recovery_amount")
    @Expose
    private Integer loanRecoveryAmount;
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

    public Integer getKanyadhanNewAc() {
        return kanyadhanNewAc;
    }

    public void setKanyadhanNewAc(Integer kanyadhanNewAc) {
        this.kanyadhanNewAc = kanyadhanNewAc;
    }

    public Integer getKanyadhanDenoSum() {
        return kanyadhanDenoSum;
    }

    public void setKanyadhanDenoSum(Integer kanyadhanDenoSum) {
        this.kanyadhanDenoSum = kanyadhanDenoSum;
    }

    public Integer getKanyadhanRenewAc() {
        return kanyadhanRenewAc;
    }

    public void setKanyadhanRenewAc(Integer kanyadhanRenewAc) {
        this.kanyadhanRenewAc = kanyadhanRenewAc;
    }

    public Integer getKanyadhanRenew() {
        return kanyadhanRenew;
    }

    public void setKanyadhanRenew(Integer kanyadhanRenew) {
        this.kanyadhanRenew = kanyadhanRenew;
    }

    public Integer getMbNewAc() {
        return mbNewAc;
    }

    public void setMbNewAc(Integer mbNewAc) {
        this.mbNewAc = mbNewAc;
    }

    public Integer getMbDenoSum() {
        return mbDenoSum;
    }

    public void setMbDenoSum(Integer mbDenoSum) {
        this.mbDenoSum = mbDenoSum;
    }

    public Integer getMbRenewAc() {
        return mbRenewAc;
    }

    public void setMbRenewAc(Integer mbRenewAc) {
        this.mbRenewAc = mbRenewAc;
    }

    public Integer getMbRenew() {
        return mbRenew;
    }

    public void setMbRenew(Integer mbRenew) {
        this.mbRenew = mbRenew;
    }

    public Integer getFfdNewAc() {
        return ffdNewAc;
    }

    public void setFfdNewAc(Integer ffdNewAc) {
        this.ffdNewAc = ffdNewAc;
    }

    public Integer getFfdDenoSum() {
        return ffdDenoSum;
    }

    public void setFfdDenoSum(Integer ffdDenoSum) {
        this.ffdDenoSum = ffdDenoSum;
    }

    public Integer getFrdNewAc() {
        return frdNewAc;
    }

    public void setFrdNewAc(Integer frdNewAc) {
        this.frdNewAc = frdNewAc;
    }

    public Integer getFrdDenoSum() {
        return frdDenoSum;
    }

    public void setFrdDenoSum(Integer frdDenoSum) {
        this.frdDenoSum = frdDenoSum;
    }

    public Integer getFrdRenewAc() {
        return frdRenewAc;
    }

    public void setFrdRenewAc(Integer frdRenewAc) {
        this.frdRenewAc = frdRenewAc;
    }

    public Integer getFrdRenew() {
        return frdRenew;
    }

    public void setFrdRenew(Integer frdRenew) {
        this.frdRenew = frdRenew;
    }

    public Integer getJeevanNewAc() {
        return jeevanNewAc;
    }

    public void setJeevanNewAc(Integer jeevanNewAc) {
        this.jeevanNewAc = jeevanNewAc;
    }

    public Integer getJeevanDenoSum() {
        return jeevanDenoSum;
    }

    public void setJeevanDenoSum(Integer jeevanDenoSum) {
        this.jeevanDenoSum = jeevanDenoSum;
    }

    public Integer getJeevanRenewAc() {
        return jeevanRenewAc;
    }

    public void setJeevanRenewAc(Integer jeevanRenewAc) {
        this.jeevanRenewAc = jeevanRenewAc;
    }

    public Integer getJeevanRenew() {
        return jeevanRenew;
    }

    public void setJeevanRenew(Integer jeevanRenew) {
        this.jeevanRenew = jeevanRenew;
    }

    public Integer getMiNewAc() {
        return miNewAc;
    }

    public void setMiNewAc(Integer miNewAc) {
        this.miNewAc = miNewAc;
    }

    public Integer getMiDenoSum() {
        return miDenoSum;
    }

    public void setMiDenoSum(Integer miDenoSum) {
        this.miDenoSum = miDenoSum;
    }

    public Integer getMiRenewAc() {
        return miRenewAc;
    }

    public void setMiRenewAc(Integer miRenewAc) {
        this.miRenewAc = miRenewAc;
    }

    public Integer getMiRenew() {
        return miRenew;
    }

    public void setMiRenew(Integer miRenew) {
        this.miRenew = miRenew;
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

    public Integer getRdNewAc() {
        return rdNewAc;
    }

    public void setRdNewAc(Integer rdNewAc) {
        this.rdNewAc = rdNewAc;
    }

    public Integer getRdDenoSum() {
        return rdDenoSum;
    }

    public void setRdDenoSum(Integer rdDenoSum) {
        this.rdDenoSum = rdDenoSum;
    }

    public Integer getRdRenewAc() {
        return rdRenewAc;
    }

    public void setRdRenewAc(Integer rdRenewAc) {
        this.rdRenewAc = rdRenewAc;
    }

    public Integer getRdRenew() {
        return rdRenew;
    }

    public void setRdRenew(Integer rdRenew) {
        this.rdRenew = rdRenew;
    }

    public Integer getBhavhishyaNewAc() {
        return bhavhishyaNewAc;
    }

    public void setBhavhishyaNewAc(Integer bhavhishyaNewAc) {
        this.bhavhishyaNewAc = bhavhishyaNewAc;
    }

    public Integer getBhavhishyaDenoSum() {
        return bhavhishyaDenoSum;
    }

    public void setBhavhishyaDenoSum(Integer bhavhishyaDenoSum) {
        this.bhavhishyaDenoSum = bhavhishyaDenoSum;
    }

    public Integer getBhavhishyaRenewAc() {
        return bhavhishyaRenewAc;
    }

    public void setBhavhishyaRenewAc(Integer bhavhishyaRenewAc) {
        this.bhavhishyaRenewAc = bhavhishyaRenewAc;
    }

    public Integer getBhavhishyaRenew() {
        return bhavhishyaRenew;
    }

    public void setBhavhishyaRenew(Integer bhavhishyaRenew) {
        this.bhavhishyaRenew = bhavhishyaRenew;
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

    public Integer getStLoanAc() {
        return stLoanAc;
    }

    public void setStLoanAc(Integer stLoanAc) {
        this.stLoanAc = stLoanAc;
    }

    public String getStLoanAmount() {
        return stLoanAmount;
    }

    public void setStLoanAmount(String stLoanAmount) {
        this.stLoanAmount = stLoanAmount;
    }

    public Integer getPlLoanAc() {
        return plLoanAc;
    }

    public void setPlLoanAc(Integer plLoanAc) {
        this.plLoanAc = plLoanAc;
    }

    public String getPlLoanAmount() {
        return plLoanAmount;
    }

    public void setPlLoanAmount(String plLoanAmount) {
        this.plLoanAmount = plLoanAmount;
    }

    public Integer getLaLoanAc() {
        return laLoanAc;
    }

    public void setLaLoanAc(Integer laLoanAc) {
        this.laLoanAc = laLoanAc;
    }

    public String getLaLoanAmount() {
        return laLoanAmount;
    }

    public void setLaLoanAmount(String laLoanAmount) {
        this.laLoanAmount = laLoanAmount;
    }

    public Integer getGpLoanAc() {
        return gpLoanAc;
    }

    public void setGpLoanAc(Integer gpLoanAc) {
        this.gpLoanAc = gpLoanAc;
    }

    public String getGpLoanAmount() {
        return gpLoanAmount;
    }

    public void setGpLoanAmount(String gpLoanAmount) {
        this.gpLoanAmount = gpLoanAmount;
    }

    public Integer getLoanAc() {
        return loanAc;
    }

    public void setLoanAc(Integer loanAc) {
        this.loanAc = loanAc;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getStLoanRecoveryAc() {
        return stLoanRecoveryAc;
    }

    public void setStLoanRecoveryAc(Integer stLoanRecoveryAc) {
        this.stLoanRecoveryAc = stLoanRecoveryAc;
    }

    public String getStLoanRecoveryAmount() {
        return stLoanRecoveryAmount;
    }

    public void setStLoanRecoveryAmount(String stLoanRecoveryAmount) {
        this.stLoanRecoveryAmount = stLoanRecoveryAmount;
    }

    public Integer getPlLoanRecoveryAc() {
        return plLoanRecoveryAc;
    }

    public void setPlLoanRecoveryAc(Integer plLoanRecoveryAc) {
        this.plLoanRecoveryAc = plLoanRecoveryAc;
    }

    public String getPlLoanRecoveryAmount() {
        return plLoanRecoveryAmount;
    }

    public void setPlLoanRecoveryAmount(String plLoanRecoveryAmount) {
        this.plLoanRecoveryAmount = plLoanRecoveryAmount;
    }

    public Integer getLaLoanRecoveryAc() {
        return laLoanRecoveryAc;
    }

    public void setLaLoanRecoveryAc(Integer laLoanRecoveryAc) {
        this.laLoanRecoveryAc = laLoanRecoveryAc;
    }

    public String getLaLoanRecoveryAmount() {
        return laLoanRecoveryAmount;
    }

    public void setLaLoanRecoveryAmount(String laLoanRecoveryAmount) {
        this.laLoanRecoveryAmount = laLoanRecoveryAmount;
    }

    public Integer getGpLoanRecoveryAc() {
        return gpLoanRecoveryAc;
    }

    public void setGpLoanRecoveryAc(Integer gpLoanRecoveryAc) {
        this.gpLoanRecoveryAc = gpLoanRecoveryAc;
    }

    public String getGpLoanRecoveryAmount() {
        return gpLoanRecoveryAmount;
    }

    public void setGpLoanRecoveryAmount(String gpLoanRecoveryAmount) {
        this.gpLoanRecoveryAmount = gpLoanRecoveryAmount;
    }

    public Integer getLoanRecoveryAc() {
        return loanRecoveryAc;
    }

    public void setLoanRecoveryAc(Integer loanRecoveryAc) {
        this.loanRecoveryAc = loanRecoveryAc;
    }

    public Integer getLoanRecoveryAmount() {
        return loanRecoveryAmount;
    }

    public void setLoanRecoveryAmount(Integer loanRecoveryAmount) {
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

}