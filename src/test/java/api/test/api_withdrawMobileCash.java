package api.test;

import api.methods.baseMethod;
import api.methods.withdrawMobileCash;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_withdrawMobileCash extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public withdrawMobileCash withdrawMobileCash;

    @BeforeClass()
    public void setUp() {
        withdrawMobileCash = new withdrawMobileCash();
    }

    @BeforeMethod()
    public void resetData(Method m) {
        setTestName(m.getName());
    }

    @Test(priority = 1, testName = "Verify that Mobile cash cannot be withdrawn with Unauthorized Access")
    public void checkwithdrawMobileCashWithUnauthorizedAccess() {
        withdrawMobileCash.authorisedWithInvalidToken();
        withdrawMobileCash.invokeWithdrawMobileCashApi();
        withdrawMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }
    @Test(priority = 2, testName = "Verify that Mobile cash cannot be withdrawn with Invalid NIC")
    public void checkwithdrawMobileCashWithInvalidNic() throws IOException, ParseException {
        withdrawMobileCash.authorisedWithValidToken();
        withdrawMobileCash.setPayloadWithInvalidNic();
        withdrawMobileCash.invokeWithdrawMobileCashApi();
        withdrawMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        withdrawMobileCash.validatePayload();
    }
    @Test(priority = 2, testName = "Verify that Mobile cash cannot be withdrawn with Invalid PIN")
    public void checkwithdrawMobileCashWithInvalidPin() throws IOException, ParseException {
        withdrawMobileCash.authorisedWithValidToken();
        withdrawMobileCash.setPayloadWithInvalidPin();
        withdrawMobileCash.invokeWithdrawMobileCashApi();
        withdrawMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        withdrawMobileCash.validatePayload();
    }
    @Test(priority = 3, testName = "Verify that Mobile cash can be withdrawn with Authorized Access")
    public void checkwithdrawMobileCashWithAuthorizedAccess() throws IOException, ParseException {
        withdrawMobileCash.authorisedWithValidToken();
        withdrawMobileCash.setPayloadWithValidData();
        withdrawMobileCash.invokeWithdrawMobileCashApi();
        withdrawMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        withdrawMobileCash.validatePayload();
    }

}
