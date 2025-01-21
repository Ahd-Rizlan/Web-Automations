package api.test.account;

import api.methods.baseMethod;
import api.methods.deleteBeneficiaries;
import api.methods.getPayments;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_deleteBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public deleteBeneficiaries deleteBeneficiaries;

	@BeforeClass()
	public void setUp() {
		deleteBeneficiaries = new deleteBeneficiaries();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		deleteBeneficiaries.authorisedWithInvalidToken();
		deleteBeneficiaries.invokeDeleteBeneficiariesApi();
		deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		deleteBeneficiaries.authorisedWithValidToken();
		deleteBeneficiaries.invokeDeleteBeneficiariesApi();
		deleteBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		deleteBeneficiaries.validatePayload();
	}

}
