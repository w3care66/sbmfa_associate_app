package com.associate.sbmfa.Fragment.AssociateManagement.Model;

import java.util.ArrayList;

public class InvestmentCommisionParentModel {
    String id, date, investmentAccount, planName;
    ArrayList<InvestmentCommisionChildModel>  investmentCommisionChildModels;

    public InvestmentCommisionParentModel(String id, String date, String investmentAccount, String planName, ArrayList<InvestmentCommisionChildModel>  investmentCommisionChildModels) {
        this.id = id;
        this.date = date;
        this.investmentAccount = investmentAccount;
        this.planName = planName;
        this.investmentCommisionChildModels=investmentCommisionChildModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(String investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public ArrayList<InvestmentCommisionChildModel> get_investment_commision_child_model() {
        return investmentCommisionChildModels;
    }

}