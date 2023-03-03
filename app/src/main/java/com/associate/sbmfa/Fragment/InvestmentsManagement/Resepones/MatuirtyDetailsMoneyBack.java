package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class MatuirtyDetailsMoneyBack {

    @SerializedName("maturity_amount")
    @Expose
    private Integer maturityAmount;
    @SerializedName("interest_rate")
    @Expose
    private Float interestRate;
    @SerializedName("tenure")
    @Expose
    private Integer tenure;

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Integer maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

}