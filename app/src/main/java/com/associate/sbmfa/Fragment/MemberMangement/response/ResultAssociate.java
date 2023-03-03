package com.associate.sbmfa.Fragment.MemberMangement.response;

import java.util.List;
 
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
 
public class ResultAssociate {

@SerializedName("associate_list")
@Expose
private List<Associate> associateList = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;

public List<Associate> getAssociateList() {
return associateList;
}

public void setAssociateList(List<Associate> associateList) {
this.associateList = associateList;
}

public Integer getTotalCount() {
return totalCount;
}

public void setTotalCount(Integer totalCount) {
this.totalCount = totalCount;
}

}