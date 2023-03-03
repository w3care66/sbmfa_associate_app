package com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPlanTransDetails {

@SerializedName("transaction")
@Expose
private PlanDetailsTransaction transaction;

public PlanDetailsTransaction getTransaction() {
return transaction;
}

public void setTransaction(PlanDetailsTransaction transaction) {
this.transaction = transaction;
}

}