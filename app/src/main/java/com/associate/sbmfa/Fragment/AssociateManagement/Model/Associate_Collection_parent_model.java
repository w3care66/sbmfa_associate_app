package com.associate.sbmfa.Fragment.AssociateManagement.Model;

import java.util.ArrayList;

public class Associate_Collection_parent_model {
    String id,name,progress,count;
    ArrayList<Associate_collection_details_child> associate_collection_details_children;

    public Associate_Collection_parent_model(String id, String name, String progress, String count, ArrayList<Associate_collection_details_child> associate_collection_details_children) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.associate_collection_details_children = associate_collection_details_children;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<Associate_collection_details_child> getAssociate_collection_details_children() {
        return associate_collection_details_children;
    }

    public void setAssociate_collection_details_children(ArrayList<Associate_collection_details_child> associate_collection_details_children) {
        this.associate_collection_details_children = associate_collection_details_children;
    }
}
