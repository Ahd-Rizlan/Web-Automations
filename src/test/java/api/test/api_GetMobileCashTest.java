package api.test;

import api.methods.baseMethod;
import api.methods.getMobileCash;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetMobileCashTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public getMobileCash getMobileCash;

    @BeforeClass()
    public void setUp() {
        getMobileCash = new getMobileCash();
    }

    @BeforeMethod()
    public void resetData(Method m) {
        setTestName(m.getName());
    }

    @Test(priority = 1)
    public void checkGetMobileCashUnauthorizedAccess() {
        getMobileCash.authorisedWithInvalidToken();
        getMobileCash.invokeGetMobileCashApi();
        getMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

    }

    @Test(priority = 2)
    public void checkMobileCashAuthorizedAccess() {
        getMobileCash.authorisedWithValidToken();
        getMobileCash.invokeGetMobileCashApi();
        getMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        getMobileCash.validatePayload();
    }

}
