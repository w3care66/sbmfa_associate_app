package com.associate.sbmfa.Fragment.AssociateManagement.Model;

public class AssociateTreeChildModel {
    String AssociateCode,AssociateName,AssociateCarder,SeniorCode,SeniorName,SeniorCarder,Status,Action;
    String id, branch_name;
    public AssociateTreeChildModel(String associateCode, String associateName, String associateCarder, String seniorCode, String seniorName, String seniorCarder, String status, String action,String iD, String branchName) {
        AssociateCode = associateCode;
        AssociateName = associateName;
        AssociateCarder = associateCarder;
        SeniorCode = seniorCode;
        SeniorName = seniorName;
        SeniorCarder = seniorCarder;
        Status = status;
        Action = action;
        id = iD;
        branch_name = branchName;
    }

    public String getAssociateCode() {
        return AssociateCode;
    }

    public void setAssociateCode(String associateCode) {
        AssociateCode = associateCode;
    }

    public String getAssociateName() {
        return AssociateName;
    }

    public void setAssociateName(String associateName) {
        AssociateName = associateName;
    }

    public String getAssociateCarder() {
        return AssociateCarder;
    }

    public void setAssociateCarder(String associateCarder) {
        AssociateCarder = associateCarder;
    }

    public String getSeniorCode() {
        return SeniorCode;
    }

    public void setSeniorCode(String seniorCode) {
        SeniorCode = seniorCode;
    }

    public String getSeniorName() {
        return SeniorName;
    }

    public void setSeniorName(String seniorName) {
        SeniorName = seniorName;
    }

    public String getSeniorCarder() {
        return SeniorCarder;
    }

    public void setSeniorCarder(String seniorCarder) {
        SeniorCarder = seniorCarder;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getId() {
        return id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
