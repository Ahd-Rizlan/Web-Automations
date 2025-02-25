package api.test;

import api.utils.ConstantApiUtils;
import api.methods.baseMethod;
import api.methods.validateTransfer;

import org.apache.hc.core5.reactor.Command;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.lang.reflect.Method;

public class api_validateTransferTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public validateTransfer validateTransfer;

    @BeforeClass()
    //This would run before the initiation of class
    public void setup() {
        validateTransfer = new validateTransfer();
    }

    @BeforeMethod()
    //This would run before the initiation of any Methods
    public void resetData(Method m) {
        setTestName(m.getName());
    }


    @Test(priority = 1, testName = "Verify that Transfers cannot be done with Unauthorized Access")
    public void checkValidateTransferWithUnauthorizedAccess() {
        validateTransfer.authorisedWithInvalidToken();
        validateTransfer.invokeValidateTransferApi();
        validateTransfer.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

    }
    @Test(priority = 1, testName = "Verify that Transfers can be done with Authorized Access")
    public void checkValidateTransferWithUnAuthorizedAccess() {
        validateTransfer.authorisedWithValidToken();
        validateTransfer.invokeValidateTransferApi();
        validateTransfer.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        validateTransfer.validatePayload();

    }



}