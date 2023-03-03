package com.associate.sbmfa.Model;

public class Child_model {
    String id ,name ,practice_id,Quations;
    String time;
    String score;

    public Child_model(String id, String name, String practice_id, String quations,String time,String score) {
        this.id = id;
        this.name = name;
        this.practice_id = practice_id;
        this.Quations = quations;
        this.time = time;
        this.score = score;
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

    public String getPractice_id() {
        return practice_id;
    }

    public void setPractice_id(String practice_id) {
        this.practice_id = practice_id;
    }

    public String getQuations() {
        return Quations;
    }

    public void setQuations(String quations) {
        Quations = quations;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
