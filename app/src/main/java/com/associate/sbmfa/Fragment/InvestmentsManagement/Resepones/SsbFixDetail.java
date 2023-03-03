package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SsbFixDetail {

@SerializedName("amount")
@Expose
private Integer amount;

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

}