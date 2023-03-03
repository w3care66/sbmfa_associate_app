package com.associate.sbmfa.Fragment.AssociateManagement.Model;
public class Associate_details_Child_model {

    String associate_code,associate_cader,associate_branch_name,branch_code,sector_name,associate_image;
    String associate_carder_new, associate_join_date;
    public Associate_details_Child_model(String associate_code, String associate_cader, String associate_branch_name, String branch_code, String sector_name, String associate_image, String associate_carder_new, String associate_join_date) {
        this.associate_code = associate_code;
        this.associate_cader = associate_cader;
        this.associate_branch_name = associate_branch_name;
        this.branch_code = branch_code;
        this.sector_name = sector_name;
        this.associate_image = associate_image;
        this.associate_carder_new = associate_carder_new;
        this.associate_join_date = associate_join_date;
    }
    public String getAssociate_code() {
        return associate_code;
    }

    public void setAssociate_code(String associate_code) {
        this.associate_code = associate_code;
    }

    public String getAssociate_cader() {
        return associate_cader;
    }

    public void setAssociate_cader(String associate_cader) {
        this.associate_cader = associate_cader;
    }

    public String getAssociate_branch_name() {
        return associate_branch_name;
    }

    public void setAssociate_branch_name(String associate_branch_name) {
        this.associate_branch_name = associate_branch_name;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getSector_name() {
        return sector_name;
    }

    public void setSector_name(String sector_name) {
        this.sector_name = sector_name;
    }

    public String getAssociate_image() {
        return associate_image;
    }

    public void setAssociate_image(String associate_image) {
        this.associate_image = associate_image;
    }

    public String getAssociate_carder_new() {
        return associate_carder_new;
    }

    public String getAssociate_join_date() {
        return associate_join_date;
    }

    public void setAssociate_join_date(String associate_join_date) {
        this.associate_join_date = associate_join_date;
    }

    public void setAssociate_carder_new(String associate_carder_new) {
        this.associate_carder_new = associate_carder_new;
    }
}
