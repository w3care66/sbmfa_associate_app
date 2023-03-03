package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberNominees {

@SerializedName("name")
@Expose
private String name;
@SerializedName("relation")
@Expose
private Integer relation;
@SerializedName("gender")
@Expose
private Integer gender;
@SerializedName("gender_name")
@Expose
private String genderName;
@SerializedName("dob")
@Expose
private String dob;
@SerializedName("age")
@Expose
private Integer age;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getRelation() {
return relation;
}

public void setRelation(Integer relation) {
this.relation = relation;
}

public Integer getGender() {
return gender;
}

public void setGender(Integer gender) {
this.gender = gender;
}

public String getGenderName() {
return genderName;
}

public void setGenderName(String genderName) {
this.genderName = genderName;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

}