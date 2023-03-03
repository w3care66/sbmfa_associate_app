package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class MatuirtyKanyanDhyanResult {

    @SerializedName("maturity_detail")
    @Expose
    private MatuirtyKanyadhanResponse maturityDetail;

    public MatuirtyKanyadhanResponse getMaturityDetail() {
        return maturityDetail;
    }

    public void setMaturityDetail(MatuirtyKanyadhanResponse maturityDetail) {
        this.maturityDetail = maturityDetail;
    }

}