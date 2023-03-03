package com.associate.sbmfa.Fragment.MemberMangement.response;

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
private String page;
@SerializedName("length")
@Expose
private String length;

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

}