package com.associate.sbmfa.Model;

public class Associate_list_model {
    String id;
    String branch_name,associate_name,br_code,associate_id;
    boolean isShow;
    AssociateData associateData;

    public Associate_list_model(String id,String branch_name, String associate_name, String br_code, String associate_id,AssociateData associateData, boolean isShow) {
        this.id = id;
        this.branch_name = branch_name;
        this.associate_name = associate_name;
        this.br_code = br_code;
        this.associate_id = associate_id;
        this.associateData = associateData;
        this.isShow = isShow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAssociate_name() {
        return associate_name;
    }

    public void setAssociate_name(String associate_name) {
        this.associate_name = associate_name;
    }

    public String getBr_code() {
        return br_code;
    }

    public void setBr_code(String br_code) {
        this.br_code = br_code;
    }

    public String getAssociate_id() {
        return associate_id;
    }

    public void setAssociate_id(String associate_id) {
        this.associate_id = associate_id;
    }

    public AssociateData getAssociateData() {
        return associateData;
    }

    public void setAssociateData(AssociateData associateData) {
        this.associateData = associateData;
    }
}
