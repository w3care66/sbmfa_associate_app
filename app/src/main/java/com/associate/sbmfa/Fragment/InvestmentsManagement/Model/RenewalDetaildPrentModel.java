package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

import java.util.ArrayList;

public class RenewalDetaildPrentModel {
    String id,name,progress,chapter_count;
    ArrayList<RenewalDetailsChlidModel> chlidModels;

    public RenewalDetaildPrentModel(String id, String name, String progress, String chapter_count, ArrayList<RenewalDetailsChlidModel> chlidModels) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.chapter_count = chapter_count;
        this.chlidModels = chlidModels;
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

    public ArrayList<RenewalDetailsChlidModel> getChlidModels() {
        return chlidModels;
    }

    public void setChlidModels(ArrayList<RenewalDetailsChlidModel> chlidModels) {
        this.chlidModels = chlidModels;
    }
}
