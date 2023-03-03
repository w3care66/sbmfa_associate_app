package com.associate.sbmfa.Rest;


import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCollectionReport;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.AssociateCommisionDetailsResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.MemberLegerResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.QoutaReportResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentDueReportResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MonthlyReportPlanResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.BranchListResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.EMIFormSubmitResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.GroupLoanDetailResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanAgaistInvestmentResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanAssociateDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanIDRecoveryResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoneListResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberCheckResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberListResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.AssociateMemberResponse;
import com.associate.sbmfa.Fragment.MemberMangement.response.MemberDetailsResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateBusinessReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateBusinessSummaryReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.AssociateCollectionCompare.AssociateCollectionCompareRespones;
import com.associate.sbmfa.Model.AssociateLoanDetail;
import com.associate.sbmfa.Model.StateCityModel;
import com.associate.sbmfa.Respones.AssociateDetailsViewResponse;
import com.associate.sbmfa.Respones.AssociateListResponse;
import com.associate.sbmfa.Respones.CommonResponseWithoutData;
import com.associate.sbmfa.Respones.GSTAmountResponse;
import com.associate.sbmfa.Respones.LoginResponse;
import com.associate.sbmfa.Respones.MemberRegisterDetails;
import com.associate.sbmfa.Respones.OTPSendResponse;
import com.associate.sbmfa.Respones.OtpVerifyResponse;
import com.associate.sbmfa.Respones.Profile.ProfileResponse;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.LoanLedgerResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.InvestmentLoanPlan.InvestmentCommissionResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.InvestmentLoanPlan.LoanCommsionResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree.TreeListResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.TreeListViewResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Form.Response.InvestmentSavingAccountPlanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.BranchResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.CollectorAgentRenewalResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.InvestmentPlanDetails.InvestmentlistingPlanDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityAmountBackMoneyResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityDetailResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MaturityKanyadhanYojanaResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberAgentResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.MemberDetailsInvestmentRegisterResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.PlanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewAccountDetailResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewPlanCommanResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewalDetailsResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.RenewalSubmitlResponse;
import com.associate.sbmfa.Fragment.InvestmentsManagement.Resepones.TenureResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.GroupLoanDetails.GroupLoanDetailsPlanResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.InvestmentPlanDetails.InvestmentPlanDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Loan.Response.PersonalLoanResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.LoanRecoverDetails.LoanRecoveryDetailsResponse;
import com.associate.sbmfa.Fragment.LoanManagment.Respones.Recovery.LoanRecoveryResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Aassociatebusinesscomparereporp.AssociateBusinessCompareReportResponse;
import com.associate.sbmfa.Fragment.ReportManagment.Respones.Matuirty.AssociateMaturityResponse;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.PlanLedgerResponse;
import com.associate.sbmfa.Fragment.AssociateLedgerManagement.Respones.TransactionPlanLedgerResponse;
import com.associate.sbmfa.Respones.SSBAmountBalanceResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {


    @Multipart
    @POST("associate_sendotp")
    Call<OTPSendResponse> OTP_SEND_RESPONES_CALL(@Part("associate_no") RequestBody member_id, @Part("device_id") RequestBody _deviceId);

    @Multipart
    @POST("associate_otpvarified")
    Call<OtpVerifyResponse> Otp_Varified_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                       @Part("otp") RequestBody otp,
                                                       @Part("token") RequestBody token,
                                                       @Part("fcm_token") RequestBody fcm_token);

    @Multipart
    @POST("associate_setupicode")
    Call<OtpVerifyResponse> SETUP_PIN_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                    @Part("upi") RequestBody upi,
                                                    @Part("token") RequestBody token);


    @Multipart
    @POST("branch_list")
    Call<BranchListResponse> BRANCH_LIST_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                       @Part("token") RequestBody token);

    @Multipart
    @POST("loan-amount-details")
    Call<LoanIDRecoveryResponse> LOAN_ID_RECOVERY_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                @Part("token") RequestBody token,
                                                                @Part("loanId") RequestBody loanId
    );

    @Multipart
    @POST("associate_user_profile")
    Call<ProfileResponse> PROFILE_RESPONSE_CALL(@Part("associate_no") RequestBody associate_no,
                                                @Part("id") RequestBody id,
                                                @Part("token") RequestBody token);

    @Multipart
    @POST("associate_list")
    Call<AssociateListResponse> ASSOCIATE_LIST_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                             @Part("token") RequestBody token,
                                                             @Part("page") RequestBody page,
                                                             @Part("length") RequestBody length);

    @Multipart
    @POST("loan_list")
    Call<LoneListResponse> LONE_LIST_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                   @Part("token") RequestBody token,
                                                   @Part("page") RequestBody page,
                                                   @Part("length") RequestBody length);

    @Multipart
    @POST("group_loan_list")
    Call<GroupLoanDetailResponse> GROUP_LONE_LIST_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                @Part("token") RequestBody token,
                                                                @Part("page") RequestBody page,
                                                                @Part("length") RequestBody length);

    @Multipart
    @POST("associate_business_report")
    Call<AssociateBusinessReportResponse> ASSOCIATE_BUSINESS_REPORT_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                                  @Part("token") RequestBody token,
                                                                                  @Part("page") RequestBody page,
                                                                                  @Part("length") RequestBody length,
                                                                                  @Part("branch_id") RequestBody id,
                                                                                  @Part("start_date") RequestBody start_date,
                                                                                  @Part("end_date") RequestBody end_date,
                                                                                  @Part("associate_id") RequestBody associate_id);

    @Multipart
    @POST("associate_business_summary_report")
    Call<AssociateBusinessSummaryReportResponse> ASSOCIATE_BUSINESS_SUMMARY_REPORT_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("from_date") RequestBody from_date,
            @Part("to_date") RequestBody to_date,
            @Part("associate_id") RequestBody associate_id,
            @Part("branch_id") RequestBody branch_id
    );

    @Multipart
    @POST("account_ledger")
    Call<PlanLedgerResponse> PLAN_LEDGER_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                       @Part("token") RequestBody token,
                                                       @Part("page") RequestBody page,
                                                       @Part("length") RequestBody length,
                                                       @Part("account_no") RequestBody account_no);

    @Multipart
    @POST("loan_ledger")
    Call<LoanLedgerResponse> LOAN_LEDGER_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                       @Part("token") RequestBody token,
                                                       @Part("page") RequestBody page,
                                                       @Part("length") RequestBody length,
                                                       @Part("account_no") RequestBody account_no);

    @Multipart
    @POST("transaction_detail")
    Call<TransactionPlanLedgerResponse> TRANSACTION_PLAN_LEDGER_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                              @Part("token") RequestBody token,
                                                                              @Part("is_ssb") RequestBody page,
                                                                              @Part("tranid") RequestBody length);

    @Multipart
    @POST("associate_detail")
    Call<AssociateDetailsViewResponse> ASSOCIATE_DETAILS_VIEW_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                            @Part("token") RequestBody token,
                                                                            @Part("id") RequestBody id);

    @Multipart
    @POST("common_renew")
    Call<RenewPlanCommanResponse> Branch_list_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                            @Part("token") RequestBody token);


    @Multipart
    @POST("associate_memberlist")
    Call<AssociateMemberResponse> Member_Mangement_RESPONES_CALL(@Part("associate_no") RequestBody _assciate_no,
                                                                 @Part("page") RequestBody page,
                                                                 @Part("length") RequestBody length,
                                                                 @Part("token") RequestBody token,
                                                                 @Part("from_date") RequestBody from_date,
                                                                 @Part("to_date") RequestBody to_date,
                                                                 @Part("associate_id") RequestBody associate_id,
                                                                 @Part("member_id") RequestBody memberid);

    @Multipart
    @POST("associate_membermatch")
    Call<AssociateMemberCheckResponse> Member_Check_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                                  @Part("Member_id") RequestBody member_id,
                                                                  @Part("token") RequestBody token);

    @Multipart
    @POST("associate_activelist")
    Call<AssociateMemberListResponse> Member_Associate_active_List_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                                                 @Part("token") RequestBody token);

    @Multipart
    @POST("associate_memberdetail")
    Call<MemberDetailsResponse> MEMBER_DETAILS_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                             @Part("id") RequestBody id,
                                                             @Part("token") RequestBody token);

    @Multipart
    @POST("commission_ledger_list")
    Call<MemberLegerResponse> Member_Leger_List_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                              @Part("token") RequestBody token);

    @Multipart
    @POST("associate_commission_list")
    Call<AssociateCommisionDetailsResponse> Member_Assocaite_Commision_RESPONES_CALL(@Part("associate_no") RequestBody _assciate_no,
                                                                                     @Part("page") RequestBody page,
                                                                                     @Part("length") RequestBody length,
                                                                                     @Part("token") RequestBody token,
                                                                                     @Part("ledger_id") RequestBody ledger_id);


    @Multipart
    @POST("associate_quota_list")
    Call<QoutaReportResponse> Quota_Busniess_Mangement_RESPONES_CALL(
            @Part("associate_no") RequestBody _assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token,
            @Part("from_date") RequestBody from_date,
            @Part("to_date") RequestBody to_date,
            @Part("associate_id") RequestBody associate_id
    );

    @Multipart
    @POST("branch_list")
    Call<BranchResponse> Branch_list_RESPONES_CALL1(@Part("associate_no") RequestBody associate_no,
                                                    @Part("token") RequestBody token);

    @Multipart
    @POST("plan_list")
    Call<PlanResponse> Plan_list_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                               @Part("token") RequestBody token);

    @Multipart
    @POST("associate_investment_commission")
    Call<InvestmentCommissionResponse> Member_Investment_Commision_RESPONES_CALL(@Part("associate_no") RequestBody _assciate_no,
                                                                                 @Part("page") RequestBody page,
                                                                                 @Part("length") RequestBody length,
                                                                                 @Part("token") RequestBody token,
                                                                                 @Part("ledger_id") RequestBody ledger_id,
                                                                                 @Part("member_id") RequestBody member_id
    );

    @Multipart
    @POST("associate_loan_commission")
    Call<LoanCommsionResponse> Member_Loan_Commision_RESPONES_CALL(@Part("associate_no") RequestBody _assciate_no,
                                                                   @Part("page") RequestBody page,
                                                                   @Part("length") RequestBody length,
                                                                   @Part("token") RequestBody token,
                                                                   @Part("ledger_id") RequestBody ledger_id,
                                                                   @Part("member_id") RequestBody member_id);

    @Multipart
    @POST("collector_agent")
    Call<CollectorAgentRenewalResponse> COLLECTOR_AGENT_RENEWAL_RESPONSE_CALL(@Part("associate_no") RequestBody associate_no,
                                                                              @Part("token") RequestBody token,
                                                                              @Part("associate_code") RequestBody associate_code
    );

    @Multipart
    @POST("renew_account_detail")
    Call<RenewAccountDetailResponse>
    RENEW_ACCOUNT_DETAIL_RESPONSE_CALL(@Part("associate_no") RequestBody associate_no,
                                       @Part("token") RequestBody token,
                                       @Part("plan_id") RequestBody plan_id,
                                       @Part("account_no") RequestBody account_no
    );


    @Multipart
    @POST("common_investment")
    Call<BranchResponse> Branch_list_Register_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                            @Part("token") RequestBody token);

    @Multipart
    @POST("invest_member_detail")
    Call<MemberDetailsInvestmentRegisterResponse> MEMBER_DETAILS_Investment_Register_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                                                                   @Part("member_id") RequestBody id,
                                                                                                   @Part("token") RequestBody token);

    @Multipart
    @POST("collector_agent")
    Call<MemberAgentResponse> MEMBER_DETAILS_Agent_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                                 @Part("associate_code") RequestBody associate_code,
                                                                 @Part("token") RequestBody token);

    @Multipart
    @POST("plan_tenure")
    Call<TenureResponse> GET_TENURE_RESPONES_CALL(@Part("associate_no") RequestBody associate_no,
                                                  @Part("plan_code") RequestBody plan_code,
                                                  @Part("token") RequestBody token);

    @Multipart
    @POST("plan_maturity")
    Call<MaturityDetailResponse> Maduirty_Filed_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("plan_code") RequestBody plan_code,
            @Part("amount") RequestBody amount,
            @Part("tenure") RequestBody tenure,
            @Part("category") RequestBody category);


    @Multipart
    @POST("mb_maturity")
    Call<MaturityAmountBackMoneyResponse> Maduirty_Filed_Money_Back_RESPONES_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("plan_code") RequestBody plan_code,
            @Part("amount") RequestBody amount
    );

    @Multipart
    @POST("samraddh_jeevan_maturity")
    Call<MaturityAmountBackMoneyResponse> Maduirty_Jeevan_Investment_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                                                   @Part("token") RequestBody token,
                                                                                   @Part("plan_code") RequestBody plan_code,
                                                                                   @Part("amount") RequestBody amount);

    @Multipart
    @POST("samraddh_bhavhishya_maturity")
    Call<MaturityAmountBackMoneyResponse> Maduirty_Bhavhishya_Investment_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                                                       @Part("token") RequestBody token,
                                                                                       @Part("plan_code") RequestBody plan_code,
                                                                                       @Part("amount") RequestBody amount);

    @Multipart
    @POST("samraddh_kanyadhan_yojana")
    Call<MaturityKanyadhanYojanaResponse> Maduirty_Kanyana_Dhyan_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                                               @Part("token") RequestBody token,
                                                                               @Part("plan_code") RequestBody plan_code,
                                                                               @Part("age") RequestBody amount);


    @Multipart
    @POST("renew_submit")
    Call<RenewalSubmitlResponse> SUBMITL_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("branch_id") RequestBody branch_id,
            @Part("collector_associate_code") RequestBody collector_associate_code,
            @Part("login_ssb_account_no") RequestBody login_ssb_account_no,
            @Part("payment_mode") RequestBody payment_mode,
            @Part("plan_id") RequestBody plan_id,
            @Part("account_info") RequestBody account_info);


    @Multipart
    @POST("associate_renew_report")
    Call<RenewalDetailsResponse> RENEWAL_DETAILS_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("start_date") RequestBody start_date,
            @Part("end_date") RequestBody end_date,
            @Part("plan_id") RequestBody plan_id,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length);


    @Multipart
    @POST("associate_renewSSB_report")
    Call<RenewalDetailsResponse> SSB_RENEWAL_DETAILS_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("start_date") RequestBody start_date,
            @Part("end_date") RequestBody end_date,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length);

    @Multipart
    @POST("associate_investment_list")
    Call<InvestmentlistingPlanDetailsResponse> Member_Investment_Details_RESPONES_CALL(
            @Part("associate_no") RequestBody _assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token,
            @Part("from_date") RequestBody from_date,
            @Part("to_date") RequestBody to_date,
            @Part("associate_id") RequestBody associate_id,
            @Part("plan_id") RequestBody planId
    );


    @Multipart
    @POST("associate-details")
    Call<LoanAssociateDetailsResponse>
    LOAN_ASSOCIATE_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody associate_no,
                                         @Part("token") RequestBody token,
                                         @Part("associate_code") RequestBody associate_code);


    @Multipart
    @POST("associate_business_compare_report")
    Call<AssociateBusinessCompareReportResponse> AssociateBusinessCompareReportResponse_RESPONES_CALL(
            @Part("associate_no") RequestBody _assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token,
            @Part("current_start_date") RequestBody from_date,
            @Part("current_end_date") RequestBody to_date,
            @Part("comp_start_date") RequestBody comp_start_date,
            @Part("comp_end_date") RequestBody comp_end_date,
            @Part("branch_id") RequestBody branch_id
    );

    @Multipart
    @POST("associate_maturity_report")
    Call<AssociateMaturityResponse> ASSOCIATE_MATUIRTY_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("type") RequestBody type);

    @Multipart
    @POST("associate_tree_list")
    Call<TreeListResponse> ASSOCIATE_TREE_LIST_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("id") RequestBody id);

    @Multipart
    @POST("associate_tree")
    Call<TreeListViewResponse> ASSOCIATE_TREE_View_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                 @Part("token") RequestBody token,
                                                                 @Part("id") RequestBody id);

    @Multipart
    @POST("associate_loginwithupi")
    Call<LoginResponse> VERIFY_PIN_RESPONES_CALL(@Part("associate_no") RequestBody member_id,
                                                 @Part("upi") RequestBody upi,
                                                 @Part("token") RequestBody token,
                                                 @Part("fcm_token") RequestBody fcm_token,
                                                 @Part("version") RequestBody currentVersion);

    @Multipart
    @POST("deposit-emi")
    Call<EMIFormSubmitResponse> LOAN_EMI_SUBMIT_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("deposiDate") RequestBody deposiDate,
            @Part("loanEmiPaymentMode") RequestBody loanEmiPaymentMode,
            @Part("depositAmount") RequestBody depositAmount,
            @Part("loanId") RequestBody loanId,
            @Part("penaltyAmount") RequestBody penaltyAmount);

    @Multipart
    @POST("deposit-group-loan-emi")
    Call<EMIFormSubmitResponse> GROUP_LOAN_EMI_SUBMIT_RESPONSE_CALL(
            @Part("associate_no") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("deposiDate") RequestBody deposiDate,
            @Part("loanEmiPaymentMode") RequestBody loanEmiPaymentMode,
            @Part("depositAmount") RequestBody depositAmount,
            @Part("loanId") RequestBody loanId,
            @Part("penaltyAmount") RequestBody penaltyAmount);


    @Multipart
    @POST("loan_recovery_list")
    Call<LoanRecoveryResponse> LOAN_RECOVERY_RESPONES_CALL(
            @Part("associate_no") RequestBody assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token);


    @Multipart
    @POST("group_loan_recovery")
    Call<LoanRecoveryResponse> GROUP_LOAN_RECOVERY_RESPONES_CALL(
            @Part("associate_no") RequestBody assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token);


    @Multipart
    @POST("loan_recovery")
    Call<LoanRecoveryDetailsResponse> LOAN_RECOVERY_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                          @Part("token") RequestBody token,
                                                                          @Part("page") RequestBody page,
                                                                          @Part("length") RequestBody length);

    @Multipart
    @POST("group_loan_recovery")
    Call<LoanRecoveryDetailsResponse> GROUP_LOAN_RECOVERY_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                                @Part("token") RequestBody token,
                                                                                @Part("page") RequestBody page,
                                                                                @Part("length") RequestBody length);

    @Multipart
    @POST("pl_loan_detail")
    Call<PersonalLoanResponse> PERSONAL_LONE_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                   @Part("token") RequestBody token,
                                                                   @Part("id") RequestBody id);

    @Multipart
    @POST("staff_loan_detail")
    Call<PersonalLoanResponse> STAFF_LONE_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                @Part("token") RequestBody token,
                                                                @Part("id") RequestBody id);
    @Multipart
    @POST("investment_loan_detail")
    Call<LoanAgaistInvestmentResponse> Investment_Loan_Detail(@Part("associate_no") RequestBody member_id,
                                                              @Part("token") RequestBody token,
                                                              @Part("id") RequestBody id);

    @Multipart
    @POST("ssb_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_SAVING_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage
    );

    @Multipart
    @POST("kanyadhan_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_SAMRADHHKANYAYOJANA_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("dob") RequestBody dob,
            @Part("age") RequestBody age,
            @Part("relation_with_guardians") RequestBody relgouar,
            @Part("daughter_name") RequestBody daughterName,
            @Part("phone_number") RequestBody phone);

    @Multipart
    @POST("mb_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_MONEY_BACK_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("ssb_account_no") RequestBody ssbaccounted);

    @Multipart
    @POST("ffd_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_FLEXI_FIXED_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure);

    @Multipart
    @POST("frd_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_FLEXI_RECURRING_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure);

    @Multipart
    @POST("jeevan_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_SAMRADHH_JEEVAN_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("ssb_account_no") RequestBody ssbaccounted);

    @Multipart
    @POST("dd_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_DAILY_DEPOSITE_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure);

    @Multipart
    @POST("mis_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_MONTHLY_INCOME_SCHEME_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure,
            @Part("ssb_account_no") RequestBody ssb_account_no
    );

    @Multipart
    @POST("fd_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_FIXED_DEPOSIT_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure);

    @Multipart
    @POST("rd_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_RECURRING_DEPOSIT_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage,
            @Part("tenure") RequestBody tenure);

    @Multipart
    @POST("sb_investment_register")
    Call<InvestmentSavingAccountPlanResponse> SUMBIT_BHAVHISYA_Investment_Register_RESPONES_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("token") RequestBody token,
            @Part("agent_code") RequestBody agent_code,
            @Part("member_id") RequestBody member_id,
            @Part("branch_id") RequestBody branch_id,
            @Part("form_number") RequestBody form_number,
            @Part("plan_id") RequestBody plan_id,
            @Part("amount") RequestBody amount,
            @Part("first_nominee_name") RequestBody first_nominee_name,
            @Part("first_nominee_relation") RequestBody first_nominee_relation,
            @Part("first_nominee_dob") RequestBody first_nominee_dob,
            @Part("first_nominee_age") RequestBody first_nominee_age,
            @Part("first_nominee_gender") RequestBody first_nominee_gender,
            @Part("first_nominee_percentage") RequestBody first_nominee_percentage,
            @Part("second_nominee_name") RequestBody second_nominee_name,
            @Part("second_nominee_relation") RequestBody second_nominee_relation,
            @Part("second_nominee_dob") RequestBody second_nominee_dob,
            @Part("second_nominee_age") RequestBody second_nominee_age,
            @Part("second_nominee_gender") RequestBody second_nominee_gender,
            @Part("second_nominee_percentage") RequestBody second_nominee_percentage);

    @Multipart
    @POST("investment_loan_detail")
    Call<InvestmentPlanDetailsResponse> INVESTMENT_LONE_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                              @Part("token") RequestBody token,
                                                                              @Part("id") RequestBody id);

    @Multipart
    @POST("group_loan_detail")
    Call<GroupLoanDetailsPlanResponse> GROUP_LONE_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                        @Part("token") RequestBody token,
                                                                        @Part("id") RequestBody id);

    @Multipart
    @POST("associate_investment_detail")
    Call<InvestmentDetailsResponse> INVESTMENT_DETAILS_RESPONSE_CALL(@Part("associate_no") RequestBody member_id,
                                                                     @Part("token") RequestBody token,
                                                                     @Part("id") RequestBody id);

    @Multipart
    @POST("associate_collection_report")
    Call<AssociateCollectionReport> ASSOCIATE_COLLECTION_REPORT_CALL(@Part("associate_no") RequestBody member_id,
                                                                     @Part("token") RequestBody token,
                                                                     @Part("branch_id") RequestBody id,
                                                                     @Part("start_date") RequestBody start_date,
                                                                     @Part("end_date") RequestBody end_date,
                                                                     @Part("page") RequestBody page,
                                                                     @Part("length") RequestBody length,
                                                                     @Part("associate_id") RequestBody associate_id);

    @Multipart
    @POST("associate_collection_compare_report")
    Call<AssociateCollectionCompareRespones> BUSINESS_COMPARE_REPORT_RESPONSE_CALL(
            @Part("associate_no") RequestBody _assciate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token,
            @Part("current_start_date") RequestBody from_date,
            @Part("current_end_date") RequestBody to_date,
            @Part("comp_start_date") RequestBody comp_start_date,
            @Part("comp_end_date") RequestBody comp_end_date,
            @Part("branch_id") RequestBody branch_id
    );

    @Multipart
    @POST("investment_due_report")
    Call<InvestmentDueReportResponse> INVESTMENT_DUE_REPORT_RESPONSE_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token,
            @Part("start_date") RequestBody start_date,
            @Part("slug") RequestBody slug,
            @Part("associate_code") RequestBody associate_code,
            @Part("scheme_account_number") RequestBody scheme_account_number,
            @Part("plan") RequestBody plan_id
    );
    @Multipart
    @POST("monthly_due_report_plan")
    Call<MonthlyReportPlanResponse> MONTHLY_REPORT_PLAN_RESPONSE_CALL(
            @Part("associate_no") RequestBody associate_no,
            @Part("page") RequestBody page,
            @Part("length") RequestBody length,
            @Part("token") RequestBody token

    );

    @Multipart
    @POST("associate_ssb_account_balance")
    Call<SSBAmountBalanceResponse> SSB_AMOUNT_BALANCE(@Part("associate_no") RequestBody associate_no, @Part("token") RequestBody token);

    @Multipart
    @POST("collactor_account")
    Call<AssociateLoanDetail> MEMBER_LOAN_DATA(@Part("associate_no") RequestBody associate_no, @Part("token") RequestBody token, @Part("type") RequestBody type);

    @Multipart
    @POST("submit_loan_payment")
    Call<CommonResponseWithoutData> SUBMIT_LOAN_PAYMENT(@Part("associate_no") RequestBody associate_no, @Part("account_id") RequestBody accountId, @Part("emi_amount") RequestBody emiAmount, @Part("plenty_amount") RequestBody plentyAmount, @Part("payment_mode") RequestBody paymentMode, @Part("token") RequestBody token, @Part("type") RequestBody _type);

    @Multipart
    @POST("submit_group_loan_payment")
    Call<CommonResponseWithoutData> SUBMIT_GROUP_LOAN_PAYMENT(@Part("associate_no") RequestBody associate_no, @Part("account_id") RequestBody accountId, @Part("emi_amount") RequestBody emiAmount, @Part("plenty_amount") RequestBody plentyAmount, @Part("payment_mode") RequestBody paymentMode, @Part("token") RequestBody token, @Part("type") RequestBody _type);

    @Multipart
    @POST("submit_renewal_payment")
    Call<CommonResponseWithoutData> SUBMIT_RENEWAL_PAYMENT(@Part("associate_no") RequestBody associate_no, @Part("account_id") RequestBody accountId, @Part("deno_amount") RequestBody denoAmount, @Part("payment_mode") RequestBody paymentMode, @Part("token") RequestBody token, @Part("deposit_amount") RequestBody depositAmount);

    @Multipart
    @POST("send_payment_otp")
    Call<OTPSendResponse> SEND_PAYMENT_OTP(@Part("associate_no") RequestBody member_id, @Part("type") RequestBody _type);

    @Multipart
    @POST("gst_amount_penalty")
    Call<GSTAmountResponse> GET_GST_AMOUNT(@Part("associate_no") RequestBody member_id, @Part("token") RequestBody _token, @Part("loanType") RequestBody _type, @Part("loanId") RequestBody _loanId, @Part("penaltyAmount") RequestBody _penaltyAmount);

    @Multipart
    @POST("getRegistrationdetails")
    Call<MemberRegisterDetails> MEMBER_REGISTER_DATA(@Part("associate_no") RequestBody associate_no, @Part("token") RequestBody token);

    @Multipart
    @POST("getStateDetail")
    Call<StateCityModel> GET_DISTRICT_CITY_DATA(@Part("associate_no") RequestBody associate_no, @Part("token") RequestBody token, @Part("district_id") RequestBody id);

}