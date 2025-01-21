package api.test.account;

import api.methods.baseMethod;
import api.methods.getTransfers;
import api.methods.updateFavouritePayee;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_updateFavouritePayeeTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updateFavouritePayee updateFavouritePayee;

	@BeforeClass()
	public void setUp() {
		updateFavouritePayee = new updateFavouritePayee();
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
