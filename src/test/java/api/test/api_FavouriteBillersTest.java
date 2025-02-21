package api.test;

import api.methods.baseMethod;
import api.methods.favouriteBillers;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
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

    @Test(priority = 1,testName = "Verify that Favourite Billers are Not Retrieved With Unauthorized Access")   // Bug reported : SVR4-517
    public void checkFavouriteBillersApiWithUnauthorizedAccess() {

        favouriteBillers.authorisedWithInvalidToken();
        favouriteBillers.setPageLimit(ConstantApiUtils.PAGE_LIMIT_ONE);
        favouriteBillers.setPageNo(ConstantApiUtils.PAGE_NO_TWO);
        favouriteBillers.invokeFavouriteBillersApi();
        favouriteBillers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }

    @Test(priority = 2,testName = "Verify that Favourite Billers are  Retrieved With Authorized Access")  // Bug reported : SVR4-517
    public void checkFavouriteBillersApiWithAuthorizedAccess() {

        favouriteBillers.authorisedWithValidToken();
        favouriteBillers.setPageLimit(ConstantApiUtils.PAGE_LIMIT_ONE);
        favouriteBillers.setPageNo(ConstantApiUtils.PAGE_NO_TWO);
        favouriteBillers.invokeFavouriteBillersApi();
        favouriteBillers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        favouriteBillers.validatePayload();
    }

}
