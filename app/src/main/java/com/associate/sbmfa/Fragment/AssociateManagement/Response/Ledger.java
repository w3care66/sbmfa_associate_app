package com.associate.sbmfa.Fragment.AssociateManagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ledger {

@SerializedName("ledger_id")
@Expose
private Integer ledgerId;
@SerializedName("date")
@Expose
private String date;

public Integer getLedgerId() {
return ledgerId;
}

public void setLedgerId(Integer ledgerId) {
this.ledgerId = ledgerId;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

}