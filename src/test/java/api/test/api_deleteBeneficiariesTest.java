package api.test;

import api.methods.baseMethod;
import api.methods.deleteBeneficiaries;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_deleteBeneficiariesTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public deleteBeneficiaries deleteBeneficiaries;
    @BeforeClass()
    public void setUp() {
        deleteBeneficiaries = new deleteBeneficiaries();
    }

    @BeforeMethod()
    public void resetData(Method m) {
        setTestName(m.getName());
    }

    @Test(priority = 1)
    //For Unauthorized access
    public void checkDeleteBeneficiariesWithUnauthorizedAccess() {
        deleteBeneficiaries.authorisedWithInvalidToken();
        deleteBeneficiaries.invokeDeleteBeneficiariesApi();
        deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

    }

    @Test(priority = 2)
    public void checkDeleteBeneficiariesWithAuthorizedAccess() {
        deleteBeneficiaries.authorisedWithValidToken();
        deleteBeneficiaries.invokeDeleteBeneficiariesApi();
        deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
       // deleteBeneficiaries.validatePayload(); //Bug reported [SVR4-557]
    }

}
