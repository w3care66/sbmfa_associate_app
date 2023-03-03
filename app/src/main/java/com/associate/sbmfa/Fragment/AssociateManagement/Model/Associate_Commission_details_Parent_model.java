package com.associate.sbmfa.Fragment.AssociateManagement.Model;

import java.util.List;

public class Associate_Commission_details_Parent_model {
    String id,name,progress,count;

    List<Associate_Commission_details_Child_Model> Associate_Commission_details_Parent_model;

    public Associate_Commission_details_Parent_model(String id, String name, String progress, String count, List<Associate_Commission_details_Child_Model> Associate_Commission_details_Parent_model) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.Associate_Commission_details_Parent_model = Associate_Commission_details_Parent_model;

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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getcount() {
        return count;
    }

    public void setcount(String count) {
        this.count = count;
    }

    public List<Associate_Commission_details_Child_Model> getAssociate_commission_details_child_models() {
        return Associate_Commission_details_Parent_model;
    }

    public void setAssociate_commission_details_child_models(List<Associate_Commission_details_Child_Model> Associate_Commission_details_Parent_model) {
        this.Associate_Commission_details_Parent_model = Associate_Commission_details_Parent_model;
    }

}

