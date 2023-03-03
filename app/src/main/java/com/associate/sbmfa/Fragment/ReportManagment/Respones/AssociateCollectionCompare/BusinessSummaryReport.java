package com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateCollectionCompare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessSummaryReport {

@SerializedName("name")
@Expose
private String name;

@SerializedName("associate_code")
@Expose
private String associate_code;
@SerializedName("current_daily_deno_sum")
@Expose
private Integer currentDailyDenoSum;
@SerializedName("current_monthly_deno_sum")
@Expose
private Integer currentMonthlyDenoSum;
@SerializedName("current_fd_deno_sum")
@Expose
private Integer currentFdDenoSum;
@SerializedName("current_all_collection")
@Expose
private String currentAllCollection;
@SerializedName("current_tcc")
@Expose
private String currentTcc;
@SerializedName("current_ncc")
@Expose
private Integer currentNcc;
@SerializedName("current_loan_recovery_amount")
@Expose
private String currentLoanRecoveryAmount;
@SerializedName("compare_daily_deno_sum")
@Expose
private Integer compareDailyDenoSum;
@SerializedName("compare_monthly_deno_sum")
@Expose
private Integer compareMonthlyDenoSum;
@SerializedName("compare_fd_deno_sum")
@Expose
private Integer compareFdDenoSum;
@SerializedName("compare_all_collection")
@Expose
private String compareAllCollection;
@SerializedName("compare_tcc")
@Expose
private String compareTcc;
@SerializedName("compare_loan_recovery_amount")
@Expose
private String compareLoanRecoveryAmount;
@SerializedName("result_daily_deno_sum")
@Expose
private Integer resultDailyDenoSum;
@SerializedName("compare_ncc")
@Expose
private Integer compareNcc;
@SerializedName("result_monthly_deno_sum")
@Expose
private Integer resultMonthlyDenoSum;
@SerializedName("result_fd_deno_sum")
@Expose
private Integer resultFdDenoSum;
@SerializedName("result_tcc")
@Expose
private Integer resultTcc;
@SerializedName("result_loan_recovery_amount")
@Expose
private Integer resultLoanRecoveryAmount;
@SerializedName("result_collection_all")
@Expose
private Integer resultCollectionAll;
@SerializedName("result_ncc")
@Expose
private Integer resultNcc;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getCurrentDailyDenoSum() {
return currentDailyDenoSum;
}

public void setCurrentDailyDenoSum(Integer currentDailyDenoSum) {
this.currentDailyDenoSum = currentDailyDenoSum;
}

public Integer getCurrentMonthlyDenoSum() {
return currentMonthlyDenoSum;
}

public void setCurrentMonthlyDenoSum(Integer currentMonthlyDenoSum) {
this.currentMonthlyDenoSum = currentMonthlyDenoSum;
}

public Integer getCurrentFdDenoSum() {
return currentFdDenoSum;
}

public void setCurrentFdDenoSum(Integer currentFdDenoSum) {
this.currentFdDenoSum = currentFdDenoSum;
}

public String getCurrentAllCollection() {
return currentAllCollection;
}

public void setCurrentAllCollection(String currentAllCollection) {
this.currentAllCollection = currentAllCollection;
}

public String getCurrentTcc() {
return currentTcc;
}

public void setCurrentTcc(String currentTcc) {
this.currentTcc = currentTcc;
}

public Integer getCurrentNcc() {
return currentNcc;
}

public void setCurrentNcc(Integer currentNcc) {
this.currentNcc = currentNcc;
}

public String getCurrentLoanRecoveryAmount() {
return currentLoanRecoveryAmount;
}

public void setCurrentLoanRecoveryAmount(String currentLoanRecoveryAmount) {
this.currentLoanRecoveryAmount = currentLoanRecoveryAmount;
}

public Integer getCompareDailyDenoSum() {
return compareDailyDenoSum;
}

public void setCompareDailyDenoSum(Integer compareDailyDenoSum) {
this.compareDailyDenoSum = compareDailyDenoSum;
}

public Integer getCompareMonthlyDenoSum() {
return compareMonthlyDenoSum;
}

public void setCompareMonthlyDenoSum(Integer compareMonthlyDenoSum) {
this.compareMonthlyDenoSum = compareMonthlyDenoSum;
}

public Integer getCompareFdDenoSum() {
return compareFdDenoSum;
}

public void setCompareFdDenoSum(Integer compareFdDenoSum) {
this.compareFdDenoSum = compareFdDenoSum;
}

public String getCompareAllCollection() {
return compareAllCollection;
}

public void setCompareAllCollection(String compareAllCollection) {
this.compareAllCollection = compareAllCollection;
}

public String getCompareTcc() {
return compareTcc;
}

public void setCompareTcc(String compareTcc) {
this.compareTcc = compareTcc;
}

public String getCompareLoanRecoveryAmount() {
return compareLoanRecoveryAmount;
}

public void setCompareLoanRecoveryAmount(String compareLoanRecoveryAmount) {
this.compareLoanRecoveryAmount = compareLoanRecoveryAmount;
}

public Integer getResultDailyDenoSum() {
return resultDailyDenoSum;
}

public void setResultDailyDenoSum(Integer resultDailyDenoSum) {
this.resultDailyDenoSum = resultDailyDenoSum;
}

public Integer getCompareNcc() {
return compareNcc;
}

public void setCompareNcc(Integer compareNcc) {
this.compareNcc = compareNcc;
}

public Integer getResultMonthlyDenoSum() {
return resultMonthlyDenoSum;
}

public void setResultMonthlyDenoSum(Integer resultMonthlyDenoSum) {
this.resultMonthlyDenoSum = resultMonthlyDenoSum;
}

public Integer getResultFdDenoSum() {
return resultFdDenoSum;
}

public void setResultFdDenoSum(Integer resultFdDenoSum) {
this.resultFdDenoSum = resultFdDenoSum;
}

public Integer getResultTcc() {
return resultTcc;
}

public void setResultTcc(Integer resultTcc) {
this.resultTcc = resultTcc;
}

public Integer getResultLoanRecoveryAmount() {
return resultLoanRecoveryAmount;
}

public void setResultLoanRecoveryAmount(Integer resultLoanRecoveryAmount) {
this.resultLoanRecoveryAmount = resultLoanRecoveryAmount;
}

public Integer getResultCollectionAll() {
return resultCollectionAll;
}

public void setResultCollectionAll(Integer resultCollectionAll) {
this.resultCollectionAll = resultCollectionAll;
}

public Integer getResultNcc() {
return resultNcc;
}

public void setResultNcc(Integer resultNcc) {
this.resultNcc = resultNcc;
}

    public String getAssociate_code() {
        return associate_code;
    }

    public void setAssociate_code(String associate_code) {
        this.associate_code = associate_code;
    }
}