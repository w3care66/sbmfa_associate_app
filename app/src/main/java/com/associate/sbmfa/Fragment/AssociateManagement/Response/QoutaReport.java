package com.associate.sbmfa.Fragment.AssociateManagement.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class QoutaReport {
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("regan")
    @Expose
    private String regan;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("senior_code")
    @Expose
    private String seniorCode;
    @SerializedName("senior_name")
    @Expose
    private String seniorName;

    @SerializedName("quota_business_target_self_amt")
    @Expose
    private String quotaBusinessTargetSelfAmt;

    @SerializedName("achieved_target_self_amt")
    @Expose
    private Integer achievedTargetSelfAmt;


    @SerializedName("quota_business_target_self_percentage")
    @Expose
    private Object quotaBusinessTargetSelfPercentage;

    @SerializedName("achieved_target_self_percentage")
    @Expose
    private Object achievedTargetSelfPercentage;


    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("associate_name")
    @Expose
    private String associateName;
    @SerializedName("quota_business_target_team_amt")
    @Expose
    private String quotaBusinessTargetTeamAmt;
    @SerializedName("achieved_target_team_amt")
    @Expose
    private String achievedTargetTeamAmt;
    @SerializedName("quota_business_target_team_percentage")
    @Expose
    private String quotaBusinessTargetTeamPercentage;
    @SerializedName("achieved_target_team_percentage")
    @Expose
    private String achievedTargetTeamPercentage;

    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("associate_carder")
    @Expose
    private String associateCarder;


    @SerializedName("senior_carder")
    @Expose
    private String seniorCarder;



    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRegan() {
        return regan;
    }

    public void setRegan(String regan) {
        this.regan = regan;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSeniorCode() {
        return seniorCode;
    }

    public void setSeniorCode(String seniorCode) {
        this.seniorCode = seniorCode;
    }

    public String getSeniorName() {
        return seniorName;
    }

    public void setSeniorName(String seniorName) {
        this.seniorName = seniorName;
    }

    public String getQuotaBusinessTargetSelfAmt() {
        return quotaBusinessTargetSelfAmt;
    }

    public void setQuotaBusinessTargetSelfAmt(String quotaBusinessTargetSelfAmt) {
        this.quotaBusinessTargetSelfAmt = quotaBusinessTargetSelfAmt;
    }

    public Integer getAchievedTargetSelfAmt() {
        return achievedTargetSelfAmt;
    }

    public void setAchievedTargetSelfAmt(Integer achievedTargetSelfAmt) {
        this.achievedTargetSelfAmt = achievedTargetSelfAmt;
    }

    public Object getQuotaBusinessTargetSelfPercentage() {
        return quotaBusinessTargetSelfPercentage;
    }

    public void setQuotaBusinessTargetSelfPercentage(Object quotaBusinessTargetSelfPercentage) {
        this.quotaBusinessTargetSelfPercentage = quotaBusinessTargetSelfPercentage;
    }

    public Object getAchievedTargetSelfPercentage() {
        return achievedTargetSelfPercentage;
    }

    public void setAchievedTargetSelfPercentage(Object achievedTargetSelfPercentage) {
        this.achievedTargetSelfPercentage = achievedTargetSelfPercentage;
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

    public String getQuotaBusinessTargetTeamAmt() {
        return quotaBusinessTargetTeamAmt;
    }

    public void setQuotaBusinessTargetTeamAmt(String quotaBusinessTargetTeamAmt) {
        this.quotaBusinessTargetTeamAmt = quotaBusinessTargetTeamAmt;
    }

    public String getAchievedTargetTeamAmt() {
        return achievedTargetTeamAmt;
    }

    public void setAchievedTargetTeamAmt(String achievedTargetTeamAmt) {
        this.achievedTargetTeamAmt = achievedTargetTeamAmt;
    }

    public String getQuotaBusinessTargetTeamPercentage() {
        return quotaBusinessTargetTeamPercentage;
    }

    public void setQuotaBusinessTargetTeamPercentage(String quotaBusinessTargetTeamPercentage) {
        this.quotaBusinessTargetTeamPercentage = quotaBusinessTargetTeamPercentage;
    }

    public String getAchievedTargetTeamPercentage() {
        return achievedTargetTeamPercentage;
    }

    public void setAchievedTargetTeamPercentage(String achievedTargetTeamPercentage) {
        this.achievedTargetTeamPercentage = achievedTargetTeamPercentage;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssociateCarder() {
        return associateCarder;
    }

    public void setAssociateCarder(String associateCarder) {
        this.associateCarder = associateCarder;
    }

    public String getSeniorCarder() {
        return seniorCarder;
    }

    public void setSeniorCarder(String seniorCarder) {
        this.seniorCarder = seniorCarder;
    }

}