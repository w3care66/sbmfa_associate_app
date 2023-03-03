package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MemberDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("member_id_proof")
    @Expose
    private String memberIdProof;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @SerializedName("category")
    @Expose
    private String mobileNo;
    @SerializedName("mobile_no")
    @Expose
    private Object category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberIdProof() {
        return memberIdProof;
    }

    public void setMemberIdProof(String memberIdProof) {
        this.memberIdProof = memberIdProof;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

}