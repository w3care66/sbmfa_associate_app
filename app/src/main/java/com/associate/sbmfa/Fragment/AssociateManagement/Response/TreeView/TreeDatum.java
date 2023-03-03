package com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TreeDatum {

@SerializedName("carder_id")
@Expose
private Integer carderId;
@SerializedName("carder_name")
@Expose
private String carderName;
@SerializedName("data")
@Expose
private List<Datum> data = null;

public Integer getCarderId() {
return carderId;
}

public void setCarderId(Integer carderId) {
this.carderId = carderId;
}

public String getCarderName() {
return carderName;
}

public void setCarderName(String carderName) {
this.carderName = carderName;
}

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

}