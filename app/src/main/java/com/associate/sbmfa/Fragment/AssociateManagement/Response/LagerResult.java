package com.associate.sbmfa.Fragment.AssociateManagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LagerResult {

@SerializedName("ledger")
@Expose
private List<Ledger> ledger = null;
@SerializedName("total_count")
@Expose
private Integer totalCount;

public List<Ledger> getLedger() {
return ledger;
}

public void setLedger(List<Ledger> ledger) {
this.ledger = ledger;
}

public Integer getTotalCount() {
return totalCount;
}

public void setTotalCount(Integer totalCount) {
this.totalCount = totalCount;
}

}