package api.test.account;

import api.methods.baseMethod;
import api.methods.doTransferForVishwa;
import api.methods.validateTransfer;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_doTransferForVishwa extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public doTransferForVishwa doTransferForVishwa;

	@BeforeClass()
	public void setUp() {
		doTransferForVishwa = new doTransferForVishwa();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		doTransferForVishwa.authorisedWithInvalidToken();
		doTransferForVishwa.invokeDoTransferForVishwaApi();
		doTransferForVishwa.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		doTransferForVishwa.authorisedWithValidToken();
		doTransferForVishwa.invokeDoTransferForVishwaApi();
		doTransferForVishwa.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		doTransferForVishwa.validatePayload();
	}

}
