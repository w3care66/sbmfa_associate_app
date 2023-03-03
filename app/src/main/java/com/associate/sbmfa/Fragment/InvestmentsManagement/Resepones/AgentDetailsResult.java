package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentDetailsResult {

    @SerializedName("associate_detail")
    @Expose
    private AssociateDetail associateDetail;

    public AssociateDetail getAssociateDetail() {
        return associateDetail;
    }

    public void setAssociateDetail(AssociateDetail associateDetail) {
        this.associateDetail = associateDetail;
    }

}