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
    public void resetData (Method m) throws IOException, ParseException {
        Beneficiaries.setPayloadWithValidData();
        setTestName(m.getName());
    }

    //Negative cases
    @Test(priority = 1 , testName="Verify that Adding Beneficiaries cannot be with Unauthorized Access")
    //For unauthorized access
    public void checkAddBeneficiariesUnauthorizedAccess() {
        Beneficiaries.authorisedWithInValidToken();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }


    @Test(priority = 2, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Type")
    //For incorrect account type
    public void checkAddBeneficiariesWithIncorrectAccountType() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithInvalidAccountType();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForIncorrectAccountType();
    }

    @Test(priority = 3, testName = "Verify that Adding Beneficiaries cannot be with Empty Account Type")
    //For empty account type
    public void checkAddBeneficiariesWithEmptyAccountName() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithEmptyAccountName();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForEmptyAccountName();
    }

//    @Test(priority = 4, testName = "Verify that Adding Beneficiaries cannot be with Incorrect Account Number")
//    public void checkAddBeneficiariesWithIncorrectAccountNumber() throws IOException, ParseException {
//        Beneficiaries.authorisedWithValidToken();
//        Beneficiaries.setPayloadWithIncorrectAccountNumber();
//        Beneficiaries.invokeBeneficiariesPOSTApi();
//        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//        Beneficiaries.validateResponsePayloadForIncorrectAccountNumber();
//    }

    @Test(priority = 5, testName = "Verify that Adding Beneficiaries cannot be with Empty Bank code")
    public void checkAddBeneficiariesWithEmptyBankCode() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithEmptyBankCode();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayloadForEmptyBankCode();
    }

    @Test(priority = 6, testName = "Verify that Adding Beneficiaries can be with Authorized Access")
    public void checkAddBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        Beneficiaries.authorisedWithValidToken();
        Beneficiaries.setPayloadWithValidData();
        Beneficiaries.invokeBeneficiariesPOSTApi();
        Beneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        Beneficiaries.validateResponsePayload();
    }

}
