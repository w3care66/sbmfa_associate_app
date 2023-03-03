package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SsbDetail {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("account_no")
@Expose
private String accountNo;
@SerializedName("balance")
@Expose
private String balance;
@SerializedName("account_holder_name")
@Expose
private String accountHolderName;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getAccountNo() {
return accountNo;
}

public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public String getBalance() {
return balance;
}

public void setBalance(String balance) {
this.balance = balance;
}

public String getAccountHolderName() {
return accountHolderName;
}

public void setAccountHolderName(String accountHolderName) {
this.accountHolderName = accountHolderName;
}

}