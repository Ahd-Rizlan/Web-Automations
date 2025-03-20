package api.test;

import api.methods.Beneficiaries;
import api.methods.baseMethod;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class api_BeneficiariesTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public Beneficiaries Beneficiaries;

    @BeforeClass()
    public void setUp() {
        Beneficiaries = new Beneficiaries();

    }

    @BeforeMethod()
    //Reset data before every test
    public void resetData(Method m) throws IOException, ParseException {
        Beneficiaries.setPayloadForAddBeneficiariesWithValidData();
        setTestName(m.getName());
    }

    //Negative cases
    @Test(priority = 1, testName = "Verify that CRUD Operations for Beneficiaries cannot be with Unauthorized Access")
    //For unauthorized access
    public void checkAddBeneficiariesUnauthorizedAccess() {
        Beneficiaries.authorisedWithInValidToken();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }


    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Type")
    //For incorrect account type
    public void checkAddBeneficiariesWithIncorrectAccountType() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithInvalidAccountType();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForIncorrectAccountType();
    }

    @Test(priority = 3, testName = "Verify that Adding Beneficiaries cannot be with Empty Account Type")
    //For empty account type
    public void checkAddBeneficiariesWithEmptyAccountName() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithEmptyAccountName();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForEmptyAccountName();
    }

    @Test(priority = 4, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Number")
    public void checkAddBeneficiariesWithIncorrectAccountNumber() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithIncorrectAccountNumber();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForIncorrectAccountNumber();
    }

    //Ticket Raised

    @Test(priority = 5, testName = "Verify that Adding Beneficiaries cannot be with Empty Bank code")
    public void checkAddBeneficiariesWithEmptyBankCode() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithEmptyBankCode();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForEmptyBankCode();
    }

    @Test(priority = 6, testName = "Verify that Adding Beneficiaries can be with Authorized Access")
    public void checkAddBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadForAddBeneficiariesWithValidData();
        Beneficiaries.updateBasePathForAddBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayload();
    }


    // ---------------------------- Update Beneficiaries -----------------------------------------------------
    @Test(priority = 7, testName = "Verify that the Transfer Payee List Retrieve with Authorized Access")
    public void checkGetTransferPayeeListWithAuthorizedAccess() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadForGetBeneficiaryList();
        Beneficiaries.updateBasePathForGetBeneficiariesList();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.extractBeneficiaryIDFomAddedName();
        Beneficiaries.validateResponsePayloadForGetBeneficiaryList();

    }

    @Test(priority = 8, testName = "Verify that the Beneficiaries can be Updated with Authorized Access", dependsOnMethods = "checkGetTransferPayeeListWithAuthorizedAccess")
    public void checkUpdateBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadForUpdateBeneficiaryForWithBeneficiaryId();
        Beneficiaries.updateBasePathForUpdateBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.setResponseWithUpdatedBeneficiaryId();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForUpdateBeneficiary();
    }

    // ---------------------------- Delete Beneficiaries -----------------------------------------------------
    @Test(priority = 9, testName = "Verify that the Beneficiaries can be deleted with Authorized Access", dependsOnMethods = "checkGetTransferPayeeListWithAuthorizedAccess")
    public void checkDeleteBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadForDeleteBeneficiaryForWithBeneficiaryId();
        Beneficiaries.updateBasePathForDeleteBeneficiaries();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.setResponseWithDeletedBeneficiaryId();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForDeleteBeneficiary();
    }

    @Test(priority = 10, testName = "Verify that Beneficiaries for each transaction type can be accessed with Authorized Access")
    //For happy path
    public void checkBeneficiariesByTranTypeWithCorrectTranType() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadForGetBeneficiaryByTransactionType();
        Beneficiaries.updateBasePathForGetBeneficiariesByTransactionType();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForGetBeneficiaryByTransactionType();
    }
}
