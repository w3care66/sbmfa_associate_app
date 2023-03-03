package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;




        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class MatuirtyKanyadhanResponse {

    @SerializedName("maturity_amount")
    @Expose
    private Integer maturityAmount;
    @SerializedName("interest_rate")
    @Expose
    private Integer interestRate;
    @SerializedName("tenure")
    @Expose
    private Integer tenure;
    @SerializedName("monthly_deposit")
    @Expose
    private String monthlyDeposit;

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Integer maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Integer getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public String getMonthlyDeposit() {
        return monthlyDeposit;
    }

    public void setMonthlyDeposit(String monthlyDeposit) {
        this.monthlyDeposit = monthlyDeposit;
    }

}