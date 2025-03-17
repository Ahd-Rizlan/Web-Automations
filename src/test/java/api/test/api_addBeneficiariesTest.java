package api.test;

import api.methods.addBeneficiaries;
import api.methods.baseMethod;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_addBeneficiariesTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public addBeneficiaries addBeneficiaries;

    @BeforeClass()
    public void setUp() {
        addBeneficiaries = new addBeneficiaries();
    }

    @BeforeMethod()
    //Reset data before every test
    public void resetData (Method m) throws IOException, ParseException {
        addBeneficiaries.setPayloadWithValidData();
        setTestName(m.getName());
    }

    //Negative cases
//    @Test(priority = 1 , testName="Verify that Adding Beneficiaries cannot be with Unauthorized Access")
//    //For unauthorized access
//    public void checkAddBeneficiariesUnauthorizedAccess() {
//        addBeneficiaries.authorisedWithInvalidToken();
//        addBeneficiaries.invokeAddBeneficiariesApi();
//        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
//    }


//    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Type")
//    //For incorrect account type
//    public void checkAddBeneficiariesWithIncorrectAccountType() throws IOException, ParseException {
//        addBeneficiaries.authorisedWithValidToken();
//        addBeneficiaries.setPayloadWithInvalidAccountType();
//        addBeneficiaries.invokeAddBeneficiariesApi();
//        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//        addBeneficiaries.validateResponsePayloadForIncorrectAccountType();
//    }
//
    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Empty Account Type")
    //For empty account type
    public void checkAddBeneficiariesWithEmptyAccountName() throws IOException, ParseException {
        addBeneficiaries.authorisedWithValidToken();
        addBeneficiaries.setPayloadWithEmptyAccountName();
        addBeneficiaries.invokeAddBeneficiariesApi();
        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        addBeneficiaries.validateResponsePayloadForEmptyAccountName();
    }

    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Number")
    public void checkAddBeneficiariesWithIncorrectAccountNumber() throws IOException, ParseException {
        addBeneficiaries.authorisedWithValidToken();
        addBeneficiaries.setPayloadWithIncorrectAccountNumber();
        addBeneficiaries.invokeAddBeneficiariesApi();
        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        addBeneficiaries.validateResponsePayloadForIncorrectAccountNumber();
    }

    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Empty Bank code")
    public void checkAddBeneficiariesWithEmptyBankCode() throws IOException, ParseException {
        addBeneficiaries.authorisedWithValidToken();
        addBeneficiaries.setPayloadWithEmptyBankCode();
        addBeneficiaries.invokeAddBeneficiariesApi();
        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        addBeneficiaries.validateResponsePayloadForEmptyBankCode();
    }

    @Test(priority = 3, testName = "Verify that Adding Beneficiaries can be with Authorized Access")
    public void checkAddBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        addBeneficiaries.authorisedWithValidToken();
        addBeneficiaries.setPayloadWithValidData();
        addBeneficiaries.invokeAddBeneficiariesApi();
        addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        addBeneficiaries.validateResponsePayload();
    }

}
