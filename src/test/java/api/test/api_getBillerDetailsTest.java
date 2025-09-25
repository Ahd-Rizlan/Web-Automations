package api.test;

import api.methods.baseMethod;

import api.methods.getBillerDetails;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_getBillerDetailsTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
     public getBillerDetails getBillerDetails;


     @BeforeClass
     public void setUp() {
         getBillerDetails = new getBillerDetails();
     }

    @BeforeMethod()
    //Reset data before every test
    public void resetData (Method m) throws IOException, ParseException {
        getBillerDetails.setPayloadWithValidMerchantCode();
        setTestName(m.getName());
    }
    @Test(priority = 1,testName = "Verify that the Biller Details cannot be Retrieved with Unauthorized Access")
    //For unauthorized access
    public void checkBillerDetailsWithUnauthorizedAccess()  {
        getBillerDetails.authorisedWithInvalidToken();
        getBillerDetails.invokeBillerDetailsApi();
        getBillerDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }

    @Test(priority = 2,testName = "Verify that the Biller Details cannot be Retrieved with Incorrect Merchant Code")
    public void checkBillerDetailsWithIncorrectMerchantCode() throws IOException, ParseException {
        getBillerDetails.authorisedWithValidToken();
        getBillerDetails.setPayloadWithInvalidMerchantCode();
        getBillerDetails.invokeBillerDetailsApi();
        getBillerDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_500); //500 Thrown
        getBillerDetails.validatePayloadForIncorrectMerchantCode();
    }
    //Happy path
    @Test(priority = 2,testName = "Verify that the Biller Details can be Retrieved with Authorized Access")
    public void checkBillerDetailsWithAuthorizedAccess()  {
        getBillerDetails.authorisedWithValidToken();
        getBillerDetails.invokeBillerDetailsApi();
        getBillerDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        getBillerDetails.validatePayload();
    }

}
