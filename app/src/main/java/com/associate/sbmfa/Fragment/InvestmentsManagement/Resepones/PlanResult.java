package com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanResult {

        @SerializedName("plan")
        @Expose
        private List<Plan> plan = null;
        @SerializedName("total_count")
        @Expose
        private Integer totalCount;

        public List<Plan> getPlan() {
            return plan;
        }

        public void setPlan(List<Plan> plan) {
            this.plan = plan;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

    }