package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanLedgerResult {
@SerializedName("transcation")
@Expose
private List<LoanLedgerTranscation> transcation = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;
@SerializedName("page")
@Expose
private String page;
@SerializedName("length")
@Expose
private String length;
@SerializedName("account_no")
@Expose
private String accountNo;
public List<LoanLedgerTranscation> getTranscation() {
return transcation;
}

public void setTranscation(List<LoanLedgerTranscation> transcation) {
this.transcation = transcation;
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

public String getAccountNo() {
return accountNo;
}

public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

}