package com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateCollectionCompare;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("business_summary_report")
@Expose
private List<BusinessSummaryReport> businessSummaryReport = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;
@SerializedName("page")
@Expose
private String page;
@SerializedName("length")
@Expose
private String length;
@SerializedName("record_count")
@Expose
private Integer recordCount;

public List<BusinessSummaryReport> getBusinessSummaryReport() {
return businessSummaryReport;
}

public void setBusinessSummaryReport(List<BusinessSummaryReport> businessSummaryReport) {
this.businessSummaryReport = businessSummaryReport;
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

public Integer getRecordCount() {
return recordCount;
}

public void setRecordCount(Integer recordCount) {
this.recordCount = recordCount;
}

}