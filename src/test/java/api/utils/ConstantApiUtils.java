package api.utils;

import static api.methods.baseMethod.config;

/**
 * This Class is used to store all the test constant variables.
 */
public class ConstantApiUtils {

    //--------------status code ------------------
    public static final int API_STATS_CODE_200 = 200;
    public static final int API_STATS_CODE_401 = 401;
    public static final int API_STATS_CODE_403 = 403;

    public static final int API_STATS_CODE_500 = 500;
    public static final int API_STATS_CODE_404 = 404;
    public static final int API_STATS_CODE_400 = 400;

    //--------------header values--------------------------------
    public static final String TXT_AUTHORIZATION = "Authorization";
    public static final String TXT_AUTHORIZATION_INVALID_VAL = "Bearer eyJ4NXQiOiJOMkpqTWpOaU0yRXhZalJrTnpaalptWTFZVEF4Tm1GbE5qZzRPV1UxWVdRMll6YzFObVk1TlEiLCJraWQiOiJNREpsTmpJeE4yRTFPR1psT0dWbU1HUXhPVEZsTXpCbU5tRmpaalEwWTJZd09HWTBOMkkwWXpFNFl6WmpOalJoWW1SbU1tUTBPRGRpTkRoak1HRXdNQV9SUzI1NiIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJ2MTF1c2VyNSIsImF1dCI6IkFQUExJQ0FUSU9OX1VTRVIiLCJhdWQiOiJUdnAxUFFNRGp5WnBjQmVia1U1OW5iWm0yUDBhIiwibmJmIjoxNzE2Mjg4OTQ0LCJhenAiOiJUdnAxUFFNRGp5WnBjQmVia1U1OW5iWm0yUDBhIiwic2NvcGUiOiJ2aXNod2Ffd2ViX2FwcCIsImlzcyI6Imh0dHBzOlwvXC9hcGlzaXQuc2FtcGF0aC5pbnQ6OTQ0M1wvb2F1dGgyXC90b2tlbiIsImdyb3VwcyI6WyJbSW50ZXJuYWxcL2V2ZXJ5b25lIiwiT01OSVwvc2FtcGF0aF9yZXRhaWxfdXNlcl0iXSwiZXhwIjoxNzE2MjkyNTQ0LCJpYXQiOjE3MTYyODg5NDQsImp0aSI6IjYwYWJmMWU1LTJhNjAtNGRhOS1iOWRhLWRmYjZiZTcxMWNlNSJ9.dI8y0QhEYyNX7GoQcfYmFyVrnfwnyrzeXRJS6A3IMN_mbeXsS6T9v2tf22w0jeviomQwCpCboQXpUHhUDageYwag8cTTEDVbkf6b2DBmeieEqPaFlBANEIQQt_tEBnSow33RqR6Yrex9Avu9cBFEEmHcctlnh2_O_67n-h5U96EPHGycdMcledoP-wX7i8Rg6pkZmdBZUN_0hgskbtMh9Tybe9gNjF-43jmbnHIS-qevM4nHukj1UjWJDtCAWOIKnEyIILd7MXhYruV5JA0PdZV00uSj28bHCbkmyPyz0pn8jm743AdA89GIXDIuaX5oayUMUT6l4tpoTsU4aKuwxjhx";

    public static final String  TXT_CONTENT_TYPE = "Content-Type";

    public static final String TXT_APPLICATION_JSON = "application/json";
    public static final String TXT_APPLICATION_URL_ENCODE = "application/x-www-form-urlencoded";
    public static final String TXT_X_REQUEST_ID = "X-Request-ID";

    public static final String TXT_X_REQUEST_ID_VALUE_ELEVEN = "11";

    public static final String TXT_X_REQUEST_ID_VALUE_TRIPLE_ONE = "111";
    public static final String TXT_X_REQUEST_ID_VALUE_ONE = "1";
    public static final String TXT_X_REQUEST_ID_VALUE_TWENTY_TWO = "22";
    public static final String TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE = "123";
    public static final String TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_TWO = "122";
    public static final String TXT_X_REQUEST_ID_VALUE_ONE_TO_SIX = "123456";
    public static final String TXT_COOKIE = "Cookie";
    public static final String AUTHORIZATION = "Authorization";
    public static final String VALID_TOKEN =  "Bearer"+config.getProperty("accessToken");
    public static final String TXT_IDENTITY_TYPE = "IdentityType";
    public static final String TXT_IDENTITY_VALUE = "IdentityValue";
    public static final String TXT_SERVICE_NAME = "ServiceName";
    public static final String TXT_IDENTITY_TYPE_VALUE = "Nic";
    public static final String TXT_IDENTITY_VALUE_VALUE = "913611217V";
    public static final String TXT_SERVICE_NAME_VALUE = "GetCardCountSummaryforOmniMobile";

    public static final String TXT_SERVICE_NAME_VALUE_DASHBOARD_WEB_CARD = "DashboardWebCardDetailsforOmniMobile";
    public static final String TXT_X_REQUEST_ID_VALUE = "1234";
    public static final String TXT_TOKEN_ID = "TokenID";
    public static final String TXT_TOKEN_ID_VALUE = "0";


    //------------------------folder paths--------------------
    public static final String PATH_TO_SCHEMA_FOLDER = "/src/test/java/api/utils/Schemas/";
    public static final String PATH_TO_PAYLOAD_FOLDER = System.getProperty("user.dir").concat("/src/test/java/api/utils/payloads/");

    //-------------------------path params ---------------------------------------
    public static final String GET_PAYMENTS_BY_ID_PATH = "/apis/payments/web/getPaymentsByID/1.0.0";

    public static final String GET_LOAN_ACCOUNT_PATH = "/apis/accounts/web/getLoanAccounts/1.0.0";
    public static final String GET_FAVOURITE_BILLERS_PATH = "/apis/transaction/web/favouriteBillers/1.0.0";

    public static final String GET_TRANSACTION_DATA_BY_REFERENCE_PATH = "/apis/transaction/web/getTransactionDatabyReference/1.0.0";

    public static final String GET_BANK_LIST_PATH = "apis/transaction/web/getBankList/1.0.0";

    public static final String GET_BRANCH_LIST_BY_BANK_CODE_PATH = "/apis/transaction/web/getBranchListByBankCode/1.0.0/7269/branches";
    public static final String GET_ACCOUNTS_BY_TRAN_TYPE_PATH = "/apis/accounts/web/getAccountsByTrantype/1.0.0/LOA";
    public static final String GET_INITIATE_CARDS_FOR_SVR_PATH = "/apis/card/web/initiateCardsforSVR/1.0.0";

    public static final String GET_MOBILE_CASH_PATH = "/apis/transaction/web/getMobileCash/1.0.0";
    public static final String GET_CATEGORIES_PATH = "/apis/payments/bill_payment/categories/1.0.0";
    public static final String GET_WITHDRAW_MOBILE_CASH_PATH = "/apis/transaction/web/withdrawMobileCash/1.0.0";

    public static final String GET_REVERSE_MOBILE_CASH_PATH = "/apis/transaction/web/reverseMobileCash/1.0.0";

    public static final String GET_VALIDATE_TRANSFER_PATH = "/apis/transaction/web/validateTransfer/1.0.0";

    public static final String GET_DO_TRANSFER_FOR_VISHVA_PATH = "/apis/transaction/web/doTransferForVishwa/1.0.0";

    public static final String GET_UTILIZED_MOBILE_CACHE_PATH = "/apis/transaction/web/getUnutilizedMobileCash/1.0.0";
    public static final String GET_DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_PATH = "/apis/card/web/dashboardWebCardDetailsforSVR/1.0.0";
    public static final String GET_CASA_ACCOUNT_PATH = "/apis/accounts/web/getCASAAccounts/1.0.0";
    public static final String GET_CUST_ACCOUNT_DETAILS_PATH = "/apis/accounts/web/getCustAccountDetails/1.0.0";
    public static final String GET_TRANSFERS_PATH = "/apis/transaction/web/getTransfers/1.0.0";
    public static final String GET_UPDATE_FAVOURITE_PAYEE_PATH = "/apis/transaction/web/updateFavouritePayee/1.0.0";
    public static final String GET_TRANSFER_PAYEE_LIST_PATH = "/apis/transaction/web/getTransferPayeeList/1.0.0";
    public static final String GET_PAYMENTS_PATH = "/apis/payments/web/getPayments/1.0.0";
    public static final String GET_UPDATE_PAYEE_AS_FAVOURITE_PATH = "/apis/payments/web/updatePayeeAsFavourite/1.0.0";
    public static final String GET_DELETE_BENEFICIARIES_PATH = "/apis/transaction/web/deleteBeneficiaries/1.0.0";
    public static final String GET_UPDATE_BENEFICIARIES_PATH = "/apis/transaction/web/updateBeneficiaries/1.0.0";
    public static final String GET_VALIDATE_PAYMENT_PATH = "/apis/payments/web/validatePayment/1.0.0";
    public static final String GET_MAKE_BILL_PAYMENT_PATH = "/apis/payments/web/makeBillPayment/1.0.0";
    public static final String GET_SAVE_PAYMENT_TEMPLATE_PATH = "/apis/payments/web/savePaymentTemplate/1.0.0";
    public static final String GET_ADD_BENEFICIARIES_PATH = "/apis/transaction/web/addBeneficiaries/1.0.0";
    public static final String GET_BENEFICIARIES_BY_TRAN_TYPE_PATH = "/apis/transaction/web/beneficiariesByTranType/1.0.0";
    public static final String GET_FAV_BENEFICIARIES_PATH = "/apis/transaction/web/favBeneficiaries/1.0.0";
    public static final String GET_UPDATE_PAYMENT_TEMPLATE_PATH = "/apis/payments/web/updatePaymentTemplate/1.0.0";
    public static final String GET_DELETE_PAYMENT_TEMPLATE_PATH = "/apis/payments/web/deletePaymentTemplate/1.0.0";
    public static final String GET_FD_ACCOUNT_PATH = "/apis/accounts/web/getFDAccounts/1.0.0";
    public static final String CARD_ACCOUNT_PATH = "/apis/card/web/getCardCountSummaryforSVR/1.0.0";
    public static final String DASHBOARD_CREDIT_CARD_DETAILS_FOR_SVR_PATH = "/apis/card/web/dashboardCreditCardDetailsforSVR/1.0.0";
    public static final String DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_PATH = "/apis/card/web/dashboardWebCardDetailsforSVR/1.0.0";
    public static final String WITHDRAWAL_MOBILE_CASH_RESPONSE = "//src//test//java//api//utils//response//withdrawMobileCash.json";
    public static final String WITHDRAWAL_MOBILE_CASH_BODY =  "//src//test//java//api//utils//payloads//withdrawMobileCashBody.json";
    public static final String REVERSE_MOBILE_CASH_RESPONSE = "//src//test//java//api//utils//response//reverseMobileCash.json";
    public static final String CARD_COUNT_SUMMARY_RESPONSE = "//src//test//java//api//utils//response//cardCountSummery.json";
    public static final String CARD_COUNT_SUMMARY_FOR_INCORRECT_INITIATED_SERNO_RESPONSE = "//src//test//java//api//utils//response//cardCountSummeryForIncorrectInitiatedSerno.json";
    public static final String CARD_COUNT_SUMMARY_FOR_INCORRECT_INITIATED_KEY_RESPONSE = "//src//test//java//api//utils//response//cardCountSummeryForIncorrectInitiatedKey.json";
    public static final String CARD_COUNT_SUMMARY_FOR_INCORRECT_CHAINSERNO_RESPONSE = "//src//test//java//api//utils//response//cardCountSummeryForIncorrectChainSerNo.json";
    public static final String CARD_COUNT_SUMMARY_FOR_INCORRECT_CHAINAUTH_RESPONSE = "//src//test//java//api//utils//response//cardCountSummeryForIncorrectChainAuth.json";
    public static final String CARD_COUNT_SUMMARY_FOR_INCORRECT_DEVICEID_RESPONSE = "//src//test//java//api//utils//response//cardCountSummeryForIncorrectDeviceID.json";

    public static final String DASHBOARD_CREDITCARD_DETAILS_FOR_SVR_FOR_INCORRECT_INITIATED_SERNO_RESPONSE = "//src//test//java//api//utils//response//dashboardCreditCardDetailsforSVRForIncorrectInitiatedSerno.json";
    public static final String DASHBOARD_CREDIT_CARD_DETAILS_FOR_SVR_FOR_INCORRECT_INITIATED_KEY_RESPONSE = "//src//test//java//api//utils//response//dashboardCreditCardDetailsForSVRForIncorrectInitiatedKey.json";
    public static final String DASHBOARD_CREDITCARD_DETAILS_FOR_SVR_FOR_INCORRECT_CHAINSERNO_RESPONSE = "//src//test//java//api//utils//response//dashboardCreditCardDetailsForSVRForIncorrectChainSerNo.json";
    public static final String DASHBOARD_CREDITCARD_DETAILS_FOR_SVR_FOR_INCORRECT_CHAINAUTH_RESPONSE = "//src//test//java//api//utils//response//dashboardCreditCardDetailsForSVRForIncorrectChainAuth.json";
    public static final String DASHBOARD_CREDITCARD_DETAILS_FOR_SVR_FOR_INCORRECT_DEVICEID_RESPONSE = "//src//test//java//api//utils//response//dashboardCreditCardDetailsForSVRForIncorrectDeviceId.json";

    public static final String DASHBOARD_WEB_DETAILS_FOR_SVR_FOR_INCORRECT_INITIATED_SERNO_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsforSVRForIncorrectInitiatedSerno.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_FOR_INCORRECT_INITIATED_KEY_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsForSVRForIncorrectInitiatedKey.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_FOR_INCORRECT_CHAINSERNO_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsForSVRForIncorrectChainSerNo.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_FOR_INCORRECT_CHAINAUTH_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsForSVRForIncorrectChainAuth.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_FOR_SVR_FOR_INCORRECT_DEVICEID_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsForSVRForIncorrectDeviceId.json";

    public static final String REVERSE_MOBILE_CASH_BODY =  "//src//test//java//api//utils//payloads//reverseMobileCashBody.json";
    public static final String CASA_ACCOUNT_RESPONSE = "//src//test//java//api//utils//response//casaAccountPayload.json";
    public static final String CASA_GET_PAYMENTS_BY_ID_RESPONSE = "//src//test//java//api//utils//response//getPaymentsByID.json";
    public static final String CREDIT_CARD_DETAILS_RESPONSE = "//src//test//java//api//utils//response//creditCardDetails.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_RESPONSE = "//src//test//java//api//utils//response//dashboardWebCardDetailsforSVR.json";
    public static final String DASHBOARD_WEB_CARD_DETAILS_BODY = "//src//test//java//api//utils//payloads//dashboardWebCardDetailsforSVRBody.json";
    public static final String DO_TRANSFER_FOR_VISHWA_RESPONSE = "//src//test//java//api//utils//response//doTransferForVishwa.json";
    public static final String DO_TRANSFER_FOR_VISHWA_BODY = "//src//test//java//api//utils//payloads//doTransferForVishwaBody.json";

    public static final String FAV_BENEFICIARIES_RESPONSE = "//src//test//java//api//utils//response//favBeneficiaries.json";
    public static final String UPDATE_PAYMENT_TEMPLATE_RESPONSE = "//src//test//java//api//utils//response//updatePaymentTemplate.json";
    public static final String UPDATE_PAYMENT_TEMPLATE_BODY = "//src//test//java//api//utils//payloads//updatePaymentTemplateBody.json";
    public static final String DELETE_PAYMENT_TEMPLATE_RESPONSE = "//src//test//java//api//utils//response//deletePaymentTemplate.json";
    public static final String DELETE_PAYMENT_TEMPLATE_BODY = "//src//test//java//api//utils//payloads//deletePaymentTemplateBody.json";
    public static final String FAV_BENEFICIARIES_BODY = "//src//test//java//api//utils//payloads//favBeneficiariesBody.json";
    public static final String FD_ACCOUNT_RESPONSE = "//src//test//java//api//utils//response//fdAccount.json";
    public static final String GET_ACCOUNT_BY_TRAN_TYPE_RESPONSE = "//src//test//java//api//utils//response//accountsByTrantype.json";
    public static final String GET_BANK_LIST_RESPONSE = "//src//test//java//api//utils//response//getBankList.json";
    public static final String GET_BRANCH_LIST_BY_BANK_CODE_RESPONSE = "//src//test//java//api//utils//response//getBranchListByBankCode.json";
    public static final String GET_CUST_ACCOUNT_DETAILS_RESPONSE = "//src//test//java//api//utils//response//getCustAccountDetails.json";
    public static final String GET_LOAN_ACCOUNTS_RESPONSE = "//src//test//java//api//utils//response//loanAccounts.json";
    public static final String GET_FAVOURITE_BILLERS_RESPONSE = "//src//test//java//api//utils//response//favouriteBillers.json";
    public static final String GET_MOBILE_CACHE_RESPONSE = "//src//test//java//api//utils//response//mobileCash.json";
    public static final String GET_CATEGORIES_RESPONSE = "//src//test//java//api//utils//response//categories.json";
    public static final String GET_CATEGORIES_BODY = "//src//test//java//api//utils//payloads//categoriesBody.json";
    public static final String GET_MOBILE_CACHE_BODY = "//src//test//java//api//utils//payloads//mobileCashBody.json";
    public static final String GET_PAYMENTS_RESPONSE = "//src//test//java//api//utils//response//payments.json";
    public static final String GET_UPDATE_PAYEE_AS_FAVOURITE_RESPONSE = "//src//test//java//api//utils//response//updatePayeeAsFavourite.json";
    public static final String GET_UPDATE_PAYEE_AS_FAVOURITE_BODY = "//src//test//java//api//utils//payloads//updatePayeeAsFavouriteBody.json";
    public static final String GET_DELETE_BENEFICIARIES_RESPONSE = "//src//test//java//api//utils//response//deleteBeneficiaries.json";
    public static final String GET_DELETE_BENEFICIARIES_BODY = "//src//test//java//api//utils//payloads//deleteBeneficiariesBody.json";
    public static final String GET_UPDATE_BENEFICIARIES_RESPONSE = "//src//test//java//api//utils//response//updateBeneficiaries.json";
    public static final String GET_UPDATE_BENEFICIARIES_BODY = "//src//test//java//api//utils//payloads//updateBeneficiariesBody.json";
    public static final String GET_ADD_BENEFICIARIES_RESPONSE = "//src//test//java//api//utils//response//addBeneficiaries.json";
    public static final String GET_ADD_BENEFICIARIES_BODY = "//src//test//java//api//utils//payloads//addBeneficiariesBody.json";
    public static final String GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE = "//src//test//java//api//utils//response//addBeneficiariesWithIncorrectAccountType.json";
    public static final String GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE = "//src//test//java//api//utils//response//addBeneficiariesWithEmptyAccountName.json";
    public static final String GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE = "//src//test//java//api//utils//response//addBeneficiariesWithIncorrectAccountNumber.json";
    public static final String GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE = "//src//test//java//api//utils//response//addBeneficiariesWithEmptyBankCode.json";
    public static final String GET_PAYMENTS_BODY = "//src//test//java//api//utils//payloads//paymentsBody.json";
    public static final String GET_TRANSACTION_DATA_BY_REFERENCE_RESPONSE = "//src//test//java//api//utils//response//getTransactionDatabyReference.json";
    public static final String GET_TRANSFERS_RESPONSE = "//src//test//java//api//utils//response//transfers.json";
    public static final String GET_UPDATE_FAVOURITE_PAYEE_RESPONSE = "//src//test//java//api//utils//response//updateFavouritePayee.json";
    public static final String GET_UPDATE_FAVOURITE_PAYEE_BODY = "//src//test//java//api//utils//payloads//updateFavouritePayeeBody.json";
    public static final String GET_TRANSFERS_BODY = "//src//test//java//api//utils//payloads//transfersBody.json";
    public static final String GET_UNUTILIZED_MOBILE_CACHE_RESPONSE = "//src//test//java//api//utils//response//getUnutilizedMobileCash.json";
    public static final String GET_INITIATE_CARDS_FOR_SVR_RESPONSE = "//src//test//java//api//utils//response//svrCard.json";
    public static final String GET_INITIATE_CARDS_FOR_INVALID_TIMESTAMP_RESPONSE = "//src//test//java//api//utils//response//svrCardForInvalidTimestamp.json";
    public static final String GET_VALIDATE_TRANSFER_RESPONSE = "//src//test//java//api//utils//response//validateTransfer.json";
    public static final String GET_VALIDATE_TRANSFER_BODY = "//src//test//java//api//utils//payloads//validateTransferBody.json";
    public static final String GET_TRANSFER_PAYEE_lIST_RESPONSE = "//src//test//java//api//utils//response//getTransferPayeeList.json";
    public static final String GET_TRANSFER_PAYEE_lIST_BODY = "//src//test//java//api//utils//payloads//getTransferPayeeListBody.json";
    public static final String GET_BENEFICIARIES_BY_TRAN_TYPE_RESPONSE = "//src//test//java//api//utils//response//beneficiariesByTranType.json";
    public static final String GET_BENEFICIARIES_BY_TRAN_TYPE_BODY = "//src//test//java//api//utils//payloads//beneficiariesByTranTypeBody.json";
    public static final String GET_BENEFICIARIES_BY_TRAN_TYPE_WITH_EMPTY_TRAN_TYPE_RESPONSE = "//src//test//java//api//utils//response//beneficiariesByTranTypeWithEmptyTranType.json";
    public static final String GET_VALIDATE_PAYMENT_RESPONSE = "//src//test//java//api//utils//response//validatePayment.json";
    public static final String GET_VALIDATE_PAYMENT_BODY = "//src//test//java//api//utils//payloads//validatePaymentBody.json";
    public static final String GET_VALIDATE_PAYMENT_WITH_INCORRECT_BILLER_ID_RESPONSE = "//src//test//java//api//utils//response//validatePaymentWithIncorrectBillerId.json";
    public static final String GET_VALIDATE_PAYMENT_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE = "//src//test//java//api//utils//response//validatePaymentWithIncorrectaAccountId.json";
    public static final String GET_VALIDATE_PAYMENT_WITH_INCORRECT_CURRENCY_RESPONSE = "//src//test//java//api//utils//response//validatePaymentWithIncorrectCurrency.json";
    public static final String GET_VALIDATE_PAYMENT_WITH_INCORRECT_TRANSACTION_TYPE_RESPONSE = "//src//test//java//api//utils//response//validatePaymentWithIncorrectTransactionType.json";

    public static final String GET_MAKE_BILL_PAYMENT_RESPONSE = "//src//test//java//api//utils//response//makeBillPayment.json";
    public static final String GET_MAKE_BILL_PAYMENT_BODY = "//src//test//java//api//utils//payloads//makeBillPaymentBody.json";
    public static final String GET_SAVE_PAYMENT_TEMPLATE_RESPONSE = "//src//test//java//api//utils//response//savePaymentTemplate.json";
    public static final String GET_SAVE_PAYMENT_TEMPLATE_BODY = "//src//test//java//api//utils//payloads//savePaymentTemplateBody.json";
    public static final String GET_SAVE_PAYMENT_TEMPLATE_WITH_INCORRECT_BILLER_ID_RESPONSE = "//src//test//java//api//utils//response//savePaymentTemplateWithIncorrectBillerId.json";
    public static final String GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_BILLER_ID_RESPONSE = "//src//test//java//api//utils//response//makeBillPaymentWithIncorrectBillerId.json";
    public static final String GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_DEBIT_ACCOUNT_RESPONSE = "//src//test//java//api//utils//response//makeBillPaymentWithIncorrecDebitAccount.json";
    public static final String GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_CURRENCY_RESPONSE = "//src//test//java//api//utils//response//makeBillPaymentWithIncorrectCurrencyType.json";
    //---------Others------------
    public static final String PAGE_NO_ONE = "1";
    public static final String PAGE_NO_TWO = "2";

    public static final String PAGE_LIMIT_ONE = "1";
    public static final String TRANSACTION_ID = "30";

    //-------------Form-params-------------
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String USERNAMETEXT = "botuser";
    public static final String PASSWORD = "password";
    public static final String PASSWORDTEXT = "Hoax@1234";
    public static final String SCOPE = "scope";
    public static final String SCOPETEXT = "openid";
    public static final String ASSERTION = "assertion";
    public static final String SCOPEPARAM = "openid vishwa_web_app";
    public static final String GRANT_TOKEN = "urn:ietf:params:oauth:grant-type:jwt-bearer";

    // Header Constants
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_URL = "application/x-www-form-urlencoded";

    // Withdrawal Mobile Cash
    public static final String INVALID_BENEFICIARY_NIC = "199685903144";
    public static final String INVALID_BENEFICIARY_PIN = "916944";
    public static final String VALID_BENEFICIARY_NIC = "199685903153";
    public static final String VALID_BENEFICIARY_PIN = "916957";

    //Reverse Mobile cash
    public static final String INVALID_SENDER_NIC = "197321701177";
    public static final String INVALID_SENDER_PIN = "55550309";
    public static final String VALID_SENDER_NIC = "197321701100";
    public static final String VALID_SENDER_PIN = "00050309";

    //Add Beneficiaries
    public static final String INVALID_ACCOUNT_TYPE = "TPSx";
    public static final String VALID_ACCOUNT_TYPE = "TPS";
    public static final String EMPTY_ACCOUNT_NAME_VALUE = "";
    public static final String VALID_ACCOUNT_NAME = "test test";

    public static final String INCORRECT_ACCOUNT_NUMBER_VALUE = "123";
    public static final String CORRECT_ACCOUNT_NUMBER_VALUE = "1207557890051";

    //Bank Code
    public static final String EMPTY_BANK_CODE_VALUE = "";
    public static final String CORRECT_BANK_CODE_VALUE = "4567";

    //beneficiariesByTranType
    public static final String EMPTY_TRAN_TYPE_VALUE = "";
    public static final String CORRECT_TRAN_TYPE_VALUE = "CC";

    // getPaymentsByID
    public static final String PAYMENT_ID = "3433";

    //validatePayment
    public static final String INVALID_BILLER_ID = "00000000020";
    public static final String VALID_BILLER_ID = "0000000002";
    public static final String VALID_ACCOUNT_NUMBER = "100100001068";
    public static final String INVALID_ACCOUNT_NUMBER = "1001000010680";

    public static final String VALID_CURRENCY = "LKR";
    public static final String INVALID_CURRENCY = "$";
    public static final String VALID_TRANSACTION_TYPE = "ONLINE";
    public static final String INVALID_TRANSACTION_TYPE = "OTC";

    //makeBillPayment
    public static final String MAKE_BILL_PAYMENT_INVALID_BILLER_ID = "00000002740";
    public static final String MAKE_BILL_PAYMENT_VALID_BILLER_ID = "0000000274";
    public static final String MAKE_BILL_PAYMENT_INVALID_DEBIT_ACCOUNT = "1009500058790";
    public static final String MAKE_BILL_PAYMENT_VALID_DEBIT_ACCOUNT = "100950005879";

    //savePaymentTemplate
    public static final String SAVE_PAYMENT_TEMPLATE_VALID_BILLER_ID = "0000000274";
    public static final String SAVE_PAYMENT_TEMPLATE_INVALID_BILLER_ID = "00000002740";

    //initiateCardsforSVR
    public static final String INITIATE_CARDS_FOR_SVR_WITH_VALID_DEVICE_ID = "FA46572E-16CE-476F-9389-BBF11F9AC1EC";
    public static final String INITIATE_CARDS_FOR_SVR_WITH_INVALID_DEVICE_ID = "FA46572E-16CE-XXXX-9389-BBF11F9AC1EC";
    public static final String INITIATE_CARDS_FOR_SVR_WITH_INVALID_TIMESTAMP = "2025-01-20 08:54:00";

    //cardCountSummeryApiMethods
    public static final String CARD_COUNT_SUMMERY_API_WITH_INVALID_DEVICE_ID = "FA46572E-16CE-XXXX-9389-BBF11F9AC1EC";
    public static final String CARD_COUNT_SUMMERY_API_WITH_INVALID_INITIATEDSERNO = "7777777.0";
    public static final String CARD_COUNT_SUMMERY_API_WITH_INVALID_INITIATEDKEY = "blQkvxQEtdx";
    public static final String CARD_COUNT_SUMMERY_API_WITH_INVALID_CHAINSERNO = "3724940.0";
    public static final String CARD_COUNT_SUMMERY_API_WITH_INVALID_CHAINAUTH = "2C2ED5F7F59A2DC2E06400144FF8DDBEX";

}
