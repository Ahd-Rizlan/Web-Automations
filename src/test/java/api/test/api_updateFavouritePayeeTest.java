package api.test;

import api.methods.baseMethod;
import api.methods.getTransfers;
import api.methods.updateFavouritePayee;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_updateFavouritePayeeTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updateFavouritePayee updateFavouritePayee;

	@BeforeClass()
	public void setUp() {
		updateFavouritePayee = new updateFavouritePayee();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		updateFavouritePayee.authorisedWithInvalidToken();
		updateFavouritePayee.invokeUpdateFavouritePayeeApi();
		updateFavouritePayee.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		updateFavouritePayee.authorisedWithValidToken();
		updateFavouritePayee.invokeUpdateFavouritePayeeApi();
		updateFavouritePayee.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updateFavouritePayee.validatePayload();
	}

}
