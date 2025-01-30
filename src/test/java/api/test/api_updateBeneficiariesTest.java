package api.test;

import api.methods.baseMethod;
import api.methods.deleteBeneficiaries;
import api.methods.updateBeneficiaries;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_updateBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updateBeneficiaries updateBeneficiaries;

	@BeforeClass()
	public void setUp() {
		updateBeneficiaries = new updateBeneficiaries();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkUpdateBeneficiariesWithUnauthorizedAccess()  {
		updateBeneficiaries.authorisedWithInvalidToken();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void checkUpdateBeneficiariesWithAuthorizedAccess()  {
		updateBeneficiaries.authorisedWithValidToken();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updateBeneficiaries.validatePayload();
	}

}
