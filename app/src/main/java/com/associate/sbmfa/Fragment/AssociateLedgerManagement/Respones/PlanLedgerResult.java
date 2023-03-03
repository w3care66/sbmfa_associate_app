package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanLedgerResult {
@SerializedName("transcation")
@Expose
private List<PlanLedgerTranscation> transcation = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;
@SerializedName("page")
@Expose
private String page;
@SerializedName("length")
@Expose
private String length;
@SerializedName("0")
@Expose
private String _0;
@SerializedName("1")
@Expose
private String _1;
public List<PlanLedgerTranscation> getTranscation() {
return transcation;
}
public void setTranscation(List<PlanLedgerTranscation> transcation) {
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

public String get0() {
return _0;
}

public void set0(String _0) {
this._0 = _0;
}

public String get1() {
return _1;
}

public void set1(String _1) {
this._1 = _1;
}

}