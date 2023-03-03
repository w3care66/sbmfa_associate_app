package com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLoanRecoveryDetails {

@SerializedName("loan_recovery")
@Expose
private List<LoanRecovery> loanRecovery = null;

@SerializedName("group_loan_recovery")
@Expose
private List<LoanRecovery> loanRecovery1 = null;

@SerializedName("total_count")
@Expose
private Integer totalCount;
@SerializedName("page")
@Expose
private String page;
@SerializedName("length")
@Expose
private String length;

public List<LoanRecovery> getLoanRecovery() {
return loanRecovery;
}

public void setLoanRecovery(List<LoanRecovery> loanRecovery) {
this.loanRecovery = loanRecovery;
}

public Integer getTotalCount() {
return totalCount;
}

public void setTotalCount(Integer totalCount) {
this.totalCount = totalCount;
}

public String getPage() {
return page;
}

public void setPage(String page) {
this.page = page;
}

public String getLength() {
return length;
}

public void setLength(String length) {
this.length = length;
}


    public List<LoanRecovery> getLoanRecovery1() {
        return loanRecovery1;
    }

    public void setLoanRecovery1(List<LoanRecovery> loanRecovery1) {
        this.loanRecovery1 = loanRecovery1;
    }
}