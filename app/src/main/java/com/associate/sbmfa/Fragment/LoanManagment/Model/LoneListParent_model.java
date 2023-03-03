package com.associate.sbmfa.Fragment.LoanManagment.Model;

import java.util.ArrayList;

public class LoneListParent_model {

    String id,name,progress,chapter_count;

    ArrayList<Lone_List_Child_model> child_models;

    public LoneListParent_model(String id, String name, String progress, String chapter_count, ArrayList<Lone_List_Child_model> child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.chapter_count = chapter_count;
        this.child_models = child_models;

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

    public String getChapter_count() {
        return chapter_count;
    }

    public void setChapter_count(String chapter_count) {
        this.chapter_count = chapter_count;
    }

    public ArrayList<Lone_List_Child_model> getChild_models() {
        return child_models;
    }

    public void setChild_models(ArrayList<Lone_List_Child_model> child_models) {
        this.child_models = child_models;
    }

}
