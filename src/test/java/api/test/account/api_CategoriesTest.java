package api.test.account;

import api.methods.baseMethod;
import api.methods.categories;
import api.methods.getMobileCash;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_CategoriesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public categories categories;

	@BeforeClass()
	public void setUp() {
		categories = new categories();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		categories.authorisedWithInvalidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401); //404 is returned

	}
	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		categories.authorisedWithValidToken();
		categories.invokeCategoriesApi();
		categories.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		categories.validatePayload();
	}
}
