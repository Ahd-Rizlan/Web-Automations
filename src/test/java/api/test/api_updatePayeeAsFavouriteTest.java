package api.test;

import api.methods.baseMethod;
import api.methods.getPayments;
import api.methods.updatePayeeAsFavourite;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_updatePayeeAsFavouriteTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updatePayeeAsFavourite updatePayeeAsFavourite;

	@BeforeClass()
	public void setUp() {
		updatePayeeAsFavourite = new updatePayeeAsFavourite();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that Billers Cannot be Marked as Favourite With Unauthorized Access")
	public void checkUpdatePayeeAsFavouriteUnauthorizedAccess()  {
		updatePayeeAsFavourite.authorisedWithInvalidToken();
		updatePayeeAsFavourite.invokeUpdatePayeeAsFavouriteApi();
		updatePayeeAsFavourite.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2,testName = "Verify that Billers Can be Marked as Favourite With Authorized Access")
	public void checkUpdatePayeeAsFavouriteAuthorizedAccess()  {
		updatePayeeAsFavourite.authorisedWithValidToken();
		updatePayeeAsFavourite.invokeUpdatePayeeAsFavouriteApi();
		updatePayeeAsFavourite.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updatePayeeAsFavourite.validatePayload();
	}

}
