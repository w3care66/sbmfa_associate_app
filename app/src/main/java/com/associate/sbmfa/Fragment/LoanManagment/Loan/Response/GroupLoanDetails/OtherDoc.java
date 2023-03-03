package com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails;

import java.util.List;
        
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class OtherDoc {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("file")
    @Expose
    private List<File> file = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

}