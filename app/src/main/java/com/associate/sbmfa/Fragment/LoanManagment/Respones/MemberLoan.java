package com.associate.sbmfa.Fragment.LoanManagment.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class MemberLoan {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("applicant_id")
@Expose
private String applicantId;

    @SerializedName("group_loan_id")
    @Expose
    private Integer group_loan_id;


@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("br_name")
@Expose
private String brName;
@SerializedName("br_code")
@Expose
private Integer brCode;
@SerializedName("so_name")
@Expose
private String soName;
@SerializedName("ro_name")
@Expose
private String roName;
@SerializedName("zo_name")
@Expose
private String zoName;
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("member_name")
@Expose
private String memberName;
@SerializedName("last_recovery_date")
@Expose
private String lastRecoveryDate;
@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("loan_type")
@Expose
private String loanType;
@SerializedName("amount")
@Expose
private String amount;
@SerializedName("status")
@Expose
private String status;
@SerializedName("approved_date")
@Expose
private String approvedDate;

@SerializedName("transfer_amount")
@Expose
private String transfer_amount;

@SerializedName("file_charge")
@Expose
private String file_charge;

@SerializedName("loan_amount")
@Expose
private String loan_amount;

    @SerializedName("tenure")
    @Expose
    private String tenure;

    @SerializedName("out_standing_amount")
    @Expose
    private String out_standing_amount;

@SerializedName("application_date")
@Expose
private String applicationDate;


    @SerializedName("payment_mode")
    @Expose
    private String payment_mode;

    @SerializedName("total_payment")
    @Expose
    private String total_payment;




    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getApplicantId() {
return applicantId;
}

public void setApplicantId(String applicantId) {
this.applicantId = applicantId;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public String getBrName() {
return brName;
}

public void setBrName(String brName) {
this.brName = brName;
}

public Integer getBrCode() {
return brCode;
}

public void setBrCode(Integer brCode) {
this.brCode = brCode;
}

public String getSoName() {
return soName;
}

public void setSoName(String soName) {
this.soName = soName;
}

public String getRoName() {
return roName;
}

public void setRoName(String roName) {
this.roName = roName;
}

public String getZoName() {
return zoName;
}

public void setZoName(String zoName) {
this.zoName = zoName;
}

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

public String getMemberName() {
return memberName;
}

public void setMemberName(String memberName) {
this.memberName = memberName;
}

public String getLastRecoveryDate() {
return lastRecoveryDate;
}

public void setLastRecoveryDate(String lastRecoveryDate) {
this.lastRecoveryDate = lastRecoveryDate;
}

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getLoanType() {
return loanType;
}

public void setLoanType(String loanType) {
this.loanType = loanType;
}

public String getAmount() {
return amount;
}

public void setAmount(String amount) {
this.amount = amount;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getApprovedDate() {
return approvedDate;
}

public void setApprovedDate(String approvedDate) {
this.approvedDate = approvedDate;
}

public String getApplicationDate() {
return applicationDate;
}

public void setApplicationDate(String applicationDate) {
this.applicationDate = applicationDate;
}

    public Integer getGroup_loan_id() {
        return group_loan_id;
    }

    public void setGroup_loan_id(Integer group_loan_id) {
        this.group_loan_id = group_loan_id;
    }


    public String getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(String transfer_amount) {
        this.transfer_amount = transfer_amount;
    }

    public String getFile_charge() {
        return file_charge;
    }

    public void setFile_charge(String file_charge) {
        this.file_charge = file_charge;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getOut_standing_amount() {
        return out_standing_amount;
    }

    public void setOut_standing_amount(String out_standing_amount) {
        this.out_standing_amount = out_standing_amount;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }


    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }
}