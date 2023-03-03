package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MemberDetailsInvestmentRegisterResult {

    @SerializedName("member_detail")
    @Expose
    private MemberDetail memberDetail;
    @SerializedName("member_nominees")
    @Expose
    private MemberNominees memberNominees;


    @SerializedName("member_ssb_detail")
    @Expose
    private SsbDetail memberSsbDetail;

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

    public MemberNominees getMemberNominees() {
        return memberNominees;
    }

    public void setMemberNominees(MemberNominees memberNominees) {
        this.memberNominees = memberNominees;
    }

    public SsbDetail getMemberSsbDetail() {
        return memberSsbDetail;
    }

    public void setMemberSsbDetail(SsbDetail memberSsbDetail) {
        this.memberSsbDetail = memberSsbDetail;
    }


}