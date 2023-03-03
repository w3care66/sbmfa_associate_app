package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

@SerializedName("member")
@Expose
private List<Member> member = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;
@SerializedName("page")
@Expose
private Object page;
@SerializedName("length")
@Expose
private Object length;
@SerializedName("record_count")
@Expose
private Integer recordCount;

public List<Member> getMember() {
return member;
}

public void setMember(List<Member> member) {
this.member = member;
}

public Integer getTotalCount() {
return totalCount;
}

public void setTotalCount(Integer totalCount) {
this.totalCount = totalCount;
}

public Object getPage() {
return page;
}

public void setPage(Object page) {
this.page = page;
}

public Object getLength() {
return length;
}

public void setLength(Object length) {
this.length = length;
}

public Integer getRecordCount() {
return recordCount;
}

public void setRecordCount(Integer recordCount) {
this.recordCount = recordCount;
}

}