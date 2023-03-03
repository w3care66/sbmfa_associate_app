package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvestmentResult {

@SerializedName("branch")
@Expose
private List<Branch> branch = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;

public List<Branch> getBranch() {
return branch;
}

public void setBranch(List<Branch> branch) {
this.branch = branch;
}

public Integer getTotalCount() {
return totalCount;
}

public void setTotalCount(Integer totalCount) {
this.totalCount = totalCount;
}

}