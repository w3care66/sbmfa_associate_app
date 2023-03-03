package com.associate.sbmfa.Fragment.AssociateManagement.Model;

public class DependentsModel {
    String id,name,dependent_type,gender,relation,marital_status,living_with_associate,monthly_income;
    public DependentsModel(String id, String name, String dependent_type, String gender, String relation, String marital_status, String living_with_associate, String monthly_income) {
        this.id = id;
        this.name = name;
        this.dependent_type = dependent_type;
        this.gender = gender;
        this.relation = relation;
        this.marital_status = marital_status;
        this.living_with_associate = living_with_associate;
        this.monthly_income = monthly_income;
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

    public String getDependent_type() {
        return dependent_type;
    }

    public void setDependent_type(String dependent_type) {
        this.dependent_type = dependent_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getLiving_with_associate() {
        return living_with_associate;
    }

    public void setLiving_with_associate(String living_with_associate) {
        this.living_with_associate = living_with_associate;
    }

    public String getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(String monthly_income) {
        this.monthly_income = monthly_income;
    }
}
