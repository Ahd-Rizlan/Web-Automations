package api.test.account;

import api.methods.baseMethod;
import api.methods.getTransferPayeeList;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_getTransferPayeeListTest extends baseMethod {

	public getTransferPayeeList getTransferPayeeList;

	@BeforeClass()
	public void setUp() {
		getTransferPayeeList = new getTransferPayeeList();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getTransferPayeeList.authorisedWithInvalidToken();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getTransferPayeeList.authorisedWithValidToken();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransferPayeeList.validatePayload();
	}

}
