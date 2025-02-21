package api.test;

import api.methods.baseMethod;
import api.methods.favBeneficiaries;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;


public class api_FavBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public favBeneficiaries favBeneficiaries;

	@BeforeClass()
	public void setUp() {
		favBeneficiaries = new favBeneficiaries();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that Credit Card Details are Not Accessible with Unauthorized Access for Favourite Beneficiaries")
	public void checkCreditCardDetailsApiUnauthorizedAccess() {
		favBeneficiaries.authorisedWithInvalidToken();
		favBeneficiaries.invokeFavBeneficiaries();
		favBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2,testName = "Verify that Credit Card Details are Accessible with Authorized Access for Favourite Beneficiaries")
	public void checkCreditCardDetailsApiAuthorizedAccess() {
		favBeneficiaries.authorisedWithValidToken();
		favBeneficiaries.invokeFavBeneficiaries();
		favBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		favBeneficiaries.validatePayload();
	}

}
