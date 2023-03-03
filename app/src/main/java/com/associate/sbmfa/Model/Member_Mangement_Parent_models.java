package com.associate.sbmfa.Model;
import java.util.List;
public class Member_Mangement_Parent_models {
    String id,name,progress,count;

    List<Member_Mangemanet_Child_model> Member_Mangemanet_Child_models;

public Member_Mangement_Parent_models(String id, String name, String progress, String count, List<Member_Mangemanet_Child_model> Member_Mangemanet_Child_models) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.count = count;
        this.Member_Mangemanet_Child_models = Member_Mangemanet_Child_models;

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

public List<Member_Mangemanet_Child_model> getMember_Mangemanet_Child_models() {
        return Member_Mangemanet_Child_models;
        }

public void setMember_Mangemanet_Child_models(List<Member_Mangemanet_Child_model> Member_Mangemanet_Child_models) {
        this.Member_Mangemanet_Child_models = Member_Mangemanet_Child_models;
        }

        }

