package com.associate.sbmfa.Model;

public class Member_Mangemanet_Child_model {

    String Id,MemberRegisteredDate,BranchCode,BranchName,SectorName,MemberID,MemberName,AssociateID,Associatename,MemberAddress,AadharNumber,PenNumber;
    int number = 11;
    public Member_Mangemanet_Child_model(String id, String memberRegisteredDate, String branchCode, String branchName, String sectorName, String memberID, String memberName, String associateID, String associatename, String memberAddress, String aadharNumber, String penNumber) {
        Id = id;
        MemberRegisteredDate = memberRegisteredDate;
        BranchCode = branchCode;
        BranchName = branchName;
        SectorName = sectorName;
        MemberID = memberID;
        MemberName = memberName;
        AssociateID = associateID;
        Associatename = associatename;
        MemberAddress = memberAddress;
        AadharNumber = aadharNumber;
        PenNumber = penNumber;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMemberRegisteredDate() {
        return MemberRegisteredDate;
    }

    public void setMemberRegisteredDate(String memberRegisteredDate) {
        MemberRegisteredDate = memberRegisteredDate;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getSectorName() {
        return SectorName;
    }

    public void setSectorName(String sectorName) {
        SectorName = sectorName;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getAssociateID() {
        return AssociateID;
    }

    public void setAssociateID(String associateID) {
        AssociateID = associateID;
    }

    public String getAssociatename() {
        return Associatename;
    }

    public void setAssociatename(String associatename) {
        Associatename = associatename;
    }

    public String getMemberAddress() {
        return MemberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        MemberAddress = memberAddress;
    }

    public String getAadharNumber() {
        return AadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        AadharNumber = aadharNumber;
    }

    public String getPenNumber() {
        return PenNumber;
    }

    public void setPenNumber(String penNumber) {
        PenNumber = penNumber;
    }

    public int getNumber() {
        return number;
    }




}