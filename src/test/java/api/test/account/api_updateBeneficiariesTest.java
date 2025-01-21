package api.test.account;

import api.methods.baseMethod;
import api.methods.deleteBeneficiaries;
import api.methods.updateBeneficiaries;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_updateBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updateBeneficiaries updateBeneficiaries;

	@BeforeClass()
	public void setUp() {
		updateBeneficiaries = new updateBeneficiaries();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		updateBeneficiaries.authorisedWithInvalidToken();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		updateBeneficiaries.authorisedWithValidToken();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updateBeneficiaries.validatePayload();
	}

}
