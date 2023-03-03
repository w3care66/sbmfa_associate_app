package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultRenewalAccountDatails {

@SerializedName("account_detail")
@Expose
private AccountDetail accountDetail;

public AccountDetail getAccountDetail() {
    return accountDetail;
}

public void setAccountDetail(AccountDetail accountDetail) {
this.accountDetail = accountDetail;
}

}