package com.associate.sbmfa.Fragment.AssociateManagement.Model;

import com.associate.sbmfa.Respones.AssociateDetailsMember;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Associate_details_Parent_model {
    String id,name,status,status_2,nominee_name,relation,nominee_age,email,mobile_no;
    List<AssociateDetailsMember> associateDetailsMembers;
    ArrayList<Associate_details_Child_model> associate_details_child_models;


    public Associate_details_Parent_model(String id, String name, String status, String status_2, String nominee_name, String relation, String nominee_age, String email, String mobile_no, ArrayList<Associate_details_Child_model> associate_details_child_models) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.status_2 = status_2;
        this.nominee_name = nominee_name;
        this.relation = relation;
        this.nominee_age = nominee_age;
        this.email = email;
        this.mobile_no = mobile_no;
        this.associate_details_child_models = associate_details_child_models;
    }

    public String getStatus_2() {
        return status_2;
    }

    public void setStatus_2(String status_2) {
        this.status_2 = status_2;
    }

    public String getNominee_name() {
        return nominee_name;
    }

    public void setNominee_name(String nominee_name) {
        this.nominee_name = nominee_name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNominee_age() {
        return nominee_age;
    }

    public void setNominee_age(String nominee_age) {
        this.nominee_age = nominee_age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Associate_details_Child_model> getAssociate_details_child_models() {
        return associate_details_child_models;
    }

    public void setAssociate_details_child_models(ArrayList<Associate_details_Child_model> associate_details_child_models) {
        this.associate_details_child_models = associate_details_child_models;
    }
}
