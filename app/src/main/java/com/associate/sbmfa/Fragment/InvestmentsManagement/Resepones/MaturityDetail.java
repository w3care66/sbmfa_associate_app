package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaturityDetail {

@SerializedName("maturity_amount")
@Expose
private Integer maturityAmount;
@SerializedName("interest_rate")
@Expose
private Double interestRate;

public Integer getMaturityAmount() {
return maturityAmount;
}

public void setMaturityAmount(Integer maturityAmount) {
this.maturityAmount = maturityAmount;
}

public Double getInterestRate() {
return interestRate;
}

public void setInterestRate(Double interestRate) {
this.interestRate = interestRate;
}

}