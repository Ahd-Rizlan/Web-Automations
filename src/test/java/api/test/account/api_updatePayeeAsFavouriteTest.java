package api.test.account;

import api.methods.baseMethod;
import api.methods.getPayments;
import api.methods.updatePayeeAsFavourite;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_updatePayeeAsFavouriteTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updatePayeeAsFavourite updatePayeeAsFavourite;

	@BeforeClass()
	public void setUp() {
		updatePayeeAsFavourite = new updatePayeeAsFavourite();
	}

	@Test(priority = 1)
	public void checkUpdatePayeeAsFavouriteUnauthorizedAccess()  {
		updatePayeeAsFavourite.authorisedWithInvalidToken();
		updatePayeeAsFavourite.invokeUpdatePayeeAsFavouriteApi();
		updatePayeeAsFavourite.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkUpdatePayeeAsFavouriteAuthorizedAccess()  {
		updatePayeeAsFavourite.authorisedWithValidToken();
		updatePayeeAsFavourite.invokeUpdatePayeeAsFavouriteApi();
		updatePayeeAsFavourite.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updatePayeeAsFavourite.validatePayload();
	}

}
