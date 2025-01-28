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
	@Test(priority = 1)
	//For unauthorized access
	public void checkGetMobileCashUnauthorizedAccess()  {
		categories.authorisedWithInvalidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401); //404 is returned
	}

	@Test(priority = 2)
	//For incorrect MerchantCode | bug reported : SVR4-494
	public void checkCategoriesWithIncorrectMerchantCode() throws IOException, ParseException {
		categories.authorisedWithValidToken();
		categories.setPayloadWithInvalidMerchantCode();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		categories.validatePayloadForIncorrectMerchantCode();
	}
	//Happy path
	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		categories.authorisedWithValidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		categories.validatePayload();
	}
}
