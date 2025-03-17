package api.test;

import api.methods.baseMethod;
import api.methods.deleteBeneficiaries;
import api.methods.getTransferPayeeList;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static api.utils.ConstantApiUtils.VALID_ACCOUNT_NAME;

public class api_deleteBeneficiariesTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public deleteBeneficiaries deleteBeneficiaries;
    public getTransferPayeeList getTransferPayeeList;

    @BeforeClass()
    public void setUp() {
        deleteBeneficiaries = new deleteBeneficiaries();
        getTransferPayeeList = new getTransferPayeeList();
    }

    @BeforeMethod()
    public void resetData(Method m) {
        setTestName(m.getName());
    }


    @Test(priority = 1,testName = "Verify that the Beneficiaries cannot be deleted with Unauthorized Access ")
    //For Unauthorized access
    public void checkDeleteBeneficiariesWithUnauthorizedAccess() {
        deleteBeneficiaries.authorisedWithInvalidToken();
        deleteBeneficiaries.invokeDeleteBeneficiariesApi();
        deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

    }

    @Test(priority = 2,testName = "Verify that the Beneficiaries can be deleted with Authorized Access",dependsOnMethods = "checkGetTransferPayeeListWithAuthorizedAccess")
    public void checkDeleteBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
        deleteBeneficiaries.authorisedWithValidToken();
        deleteBeneficiaries.setPayloadWithValidBeneId();
        deleteBeneficiaries.invokeDeleteBeneficiariesApi();
        deleteBeneficiaries.setResponceWithValidBeneId();
        deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        deleteBeneficiaries.validatePayload();
    }
    @Test(priority = 2,testName = "Verify that the Transfer Payee List Retrieve with Authorized Access")
    public void checkGetTransferPayeeListWithAuthorizedAccess() throws IOException, ParseException {
        getTransferPayeeList.authorisedWithValidToken();
        getTransferPayeeList.setPayloadWithValidData();
        getTransferPayeeList.invokeGetTransferPayeeListApi();
        getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        getTransferPayeeList.extractBeneficiaryIDForAccount(VALID_ACCOUNT_NAME);

    }

}
