package com.associate.sbmfa.Model;

public class AssociateData {

    String phone,senior_code,senior_name,
            status,is_upload,achieved_traget,
            address,state,disitrict,city,
            village_name,pin_code,first_id,second_id,action;

    public AssociateData(String phone, String senior_code, String senior_name, String status, String is_upload, String achieved_traget, String address, String state, String disitrict, String city, String village_name, String pin_code, String first_id, String second_id, String action) {

        this.phone = phone;
        this.senior_code = senior_code;
        this.senior_name = senior_name;
        this.status = status;
        this.is_upload = is_upload;
        this.achieved_traget = achieved_traget;
        this.address = address;
        this.state = state;
        this.disitrict = disitrict;
        this.city = city;
        this.village_name = village_name;
        this.pin_code = pin_code;
        this.first_id = first_id;
        this.second_id = second_id;
        this.action = action;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSenior_code() {
        return senior_code;
    }

    public void setSenior_code(String senior_code) {
        this.senior_code = senior_code;
    }

    public String getSenior_name() {
        return senior_name;
    }

    public void setSenior_name(String senior_name) {
        this.senior_name = senior_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_upload() {
        return is_upload;
    }

    public void setIs_upload(String is_upload) {
        this.is_upload = is_upload;
    }

    public String getAchieved_traget() {
        return achieved_traget;
    }

    public void setAchieved_traget(String achieved_traget) {
        this.achieved_traget = achieved_traget;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisitrict() {
        return disitrict;
    }

    public void setDisitrict(String disitrict) {
        this.disitrict = disitrict;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getFirst_id() {
        return first_id;
    }

    public void setFirst_id(String first_id) {
        this.first_id = first_id;
    }

    public String getSecond_id() {
        return second_id;
    }

    public void setSecond_id(String second_id) {
        this.second_id = second_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
