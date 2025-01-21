package api.test.account;

import api.methods.baseMethod;
import api.methods.dashboardWebCardDetailsForSVR;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_DashboardWebCardDetailsForSvrTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public dashboardWebCardDetailsForSVR dashboardWebCardDetailsForSVR ;

	@BeforeClass()
	public void setUp() {
		dashboardWebCardDetailsForSVR = new dashboardWebCardDetailsForSVR();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		dashboardWebCardDetailsForSVR.authorisedWithInvalidToken();
		dashboardWebCardDetailsForSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {

		dashboardWebCardDetailsForSVR.authorisedWithValidToken();
		dashboardWebCardDetailsForSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsForSVR.validatePayload();
	}

}
