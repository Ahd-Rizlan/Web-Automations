package api.test;

import api.methods.baseMethod;
import api.methods.categories;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_CategoriesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public categories categories;

	@BeforeClass()
	public void setUp() {
		categories = new categories();
	}

    @BeforeMethod()
    //Reset data before every test
    public void resetData (Method m) throws IOException, ParseException {
        categories.setPayloadWithValidMerchantCode();
		setTestName(m.getName());
    }

	//Negative cases
	@Test(priority = 1,testName = "Verify that the Categories cannot be Retrieved with Unauthorized Access")
	//For unauthorized access
	public void checkCategoriesWithUnauthorizedAccess()  {
		categories.authorisedWithInvalidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401); //404 is returned
	}

	@Test(priority = 2,testName = "Verify that the Categories cannot be Retrieved with Incorrect Merchant Code")
	//For incorrect MerchantCode | bug reported : SVR4-494
	public void checkCategoriesWithIncorrectMerchantCode() throws IOException, ParseException {
		categories.authorisedWithValidToken();
		categories.setPayloadWithInvalidMerchantCode();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_500); //500 Thrown
		categories.validatePayloadForIncorrectMerchantCode();
	}
	//Happy path
	@Test(priority = 2,testName = "Verify that the Categories can be Retrieved with Authorized Access")
	public void checkCategoriesWithAuthorizedAccess()  {
		categories.authorisedWithValidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		categories.validatePayload();
	}
}
