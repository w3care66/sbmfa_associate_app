package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BankDetail {

@SerializedName("bank_name")
@Expose
private String bankName;
@SerializedName("bank_ac")
@Expose
private String bankAc;
@SerializedName("ifsc")
@Expose
private String ifsc;
@SerializedName("cheque1")
@Expose
private String cheque1;
@SerializedName("cheque2")
@Expose
private String cheque2;

public String getBankName() {
return bankName;
}

public void setBankName(String bankName) {
this.bankName = bankName;
}

public String getBankAc() {
return bankAc;
}

public void setBankAc(String bankAc) {
this.bankAc = bankAc;
}

public String getIfsc() {
return ifsc;
}

public void setIfsc(String ifsc) {
this.ifsc = ifsc;
}

public String getCheque1() {
return cheque1;
}

public void setCheque1(String cheque1) {
this.cheque1 = cheque1;
}

public String getCheque2() {
return cheque2;
}

public void setCheque2(String cheque2) {
this.cheque2 = cheque2;
}

}