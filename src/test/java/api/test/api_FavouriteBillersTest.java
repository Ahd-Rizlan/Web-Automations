package api.test;

import api.methods.baseMethod;
import api.methods.favouriteBillers;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_FavouriteBillersTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public favouriteBillers favouriteBillers;

    @BeforeClass()
    public void setUp() {
        favouriteBillers = new favouriteBillers();
    }

    @BeforeMethod()
    public void resetData(Method m) {
        setTestName(m.getName());
    }


    @Test(priority = 2,testName = "Verify that Favourite Billers are  Retrieved With Authorized Access")
    public void checkFavouriteBillersApiWithAuthorizedAccess() {
        favouriteBillers.authorisedWithValidToken();
        favouriteBillers.invokeFavouriteBillersApiPost();
        favouriteBillers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        favouriteBillers.validatePayload();
    }
    @Test(priority = 2,testName = "Verify that Favourite Billers are Not Retrieved With Invalid Merchant Code")
    public void checkFavouriteBillersApiWithInvalidMerchantCode() throws IOException, ParseException {

        favouriteBillers.authorisedWithValidToken();
        favouriteBillers.setPayloadWithInvalidMerchantCode();
        favouriteBillers.invokeFavouriteBillersApiPost();
        favouriteBillers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        favouriteBillers.validatePayloadWithInvalidInvalidMerchantCode();
    }

    @Test(priority = 1,testName = "Verify that Favourite Billers are Not Retrieved With Unauthorized Access")
    public void checkFavouriteBillersApiWithUnauthorizedAccess() throws IOException, ParseException {

        favouriteBillers.authorisedWithInvalidToken();
        favouriteBillers.setPayloadWithValidMerchantCode();
        favouriteBillers.setPageLimit(ConstantApiUtils.PAGE_LIMIT_ONE);
        favouriteBillers.setPageNo(ConstantApiUtils.PAGE_NO_TWO);
        favouriteBillers.invokeFavouriteBillersApiPost();
        favouriteBillers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }
}
