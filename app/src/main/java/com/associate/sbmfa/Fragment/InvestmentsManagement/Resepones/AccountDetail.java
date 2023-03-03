package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class AccountDetail {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("member_auto_id")
@Expose
private Integer memberAutoId;
@SerializedName("member_name")
@Expose
private String memberName;
@SerializedName("associate_auto_id")
@Expose
private Integer associateAutoId;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("amount")
@Expose
private Integer amount;

@SerializedName("due_amount")
@Expose
private Integer due_amount;

@SerializedName("deposite_amount")
@Expose
private Integer deposite_amount;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public Integer getMemberAutoId() {
return memberAutoId;
}

public void setMemberAutoId(Integer memberAutoId) {
this.memberAutoId = memberAutoId;
}

public String getMemberName() {
return memberName;
}

public void setMemberName(String memberName) {
this.memberName = memberName;
}

public Integer getAssociateAutoId() {
return associateAutoId;
}

public void setAssociateAutoId(Integer associateAutoId) {
this.associateAutoId = associateAutoId;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

    public Integer getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(Integer due_amount) {
        this.due_amount = due_amount;
    }

    public Integer getDeposite_amount() {
        return deposite_amount;
    }

    public void setDeposite_amount(Integer deposite_amount) {
        this.deposite_amount = deposite_amount;
    }
}