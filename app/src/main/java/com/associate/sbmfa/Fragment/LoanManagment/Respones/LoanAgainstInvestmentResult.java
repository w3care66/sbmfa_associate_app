package com.associate.sbmfa.Fragment.LoanManagment.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanAgainstInvestmentResult {
    @SerializedName("detail")
    @Expose
    private LoanAgainstDetails detail;

    public LoanAgainstDetails getDetail() {
        return detail;
    }

    public void setDetail(LoanAgainstDetails detail) {
        this.detail = detail;
    }
}