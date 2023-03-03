package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class MaturityResultMoneyBack {

    @SerializedName("maturity_detail")
    @Expose
    private MatuirtyDetailsMoneyBack maturityDetail;

    public MatuirtyDetailsMoneyBack getMaturityDetail() {
        return maturityDetail;
    }

    public void setMaturityDetail(MatuirtyDetailsMoneyBack maturityDetail) {
        this.maturityDetail = maturityDetail;
    }

}