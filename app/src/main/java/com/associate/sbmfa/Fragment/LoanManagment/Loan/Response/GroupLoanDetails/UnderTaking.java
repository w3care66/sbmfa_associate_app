package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class UnderTaking {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}