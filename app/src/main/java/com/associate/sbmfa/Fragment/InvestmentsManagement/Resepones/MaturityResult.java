package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaturityResult {

@SerializedName("maturity_detail")
@Expose
private MaturityDetail maturityDetail;

public MaturityDetail getMaturityDetail() {
return maturityDetail;
}

public void setMaturityDetail(MaturityDetail maturityDetail) {
this.maturityDetail = maturityDetail;
}

}