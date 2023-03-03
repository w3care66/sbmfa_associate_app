package com.associate.sbmfa.Fragment.InvestmentsManagement.Model;

public class RenewalPlanModel {
    String value, name, icon, color, plan_code;

    public RenewalPlanModel(String value, String name, String icon, String color, String plan_code) {
        this.value = value;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.plan_code = plan_code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlan_code() {
        return plan_code;
    }

    public void setPlan_code(String plan_code) {
        this.plan_code = plan_code;
    }
}