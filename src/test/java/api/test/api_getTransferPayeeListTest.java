package api.test;

import api.methods.baseMethod;
import api.methods.getTransferPayeeList;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class api_getTransferPayeeListTest extends baseMethod {

	public getTransferPayeeList getTransferPayeeList;

	@BeforeClass()
	public void setUp() {
		getTransferPayeeList = new getTransferPayeeList();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetTransferPayeeListWithUnauthorizedAccess()  {
		getTransferPayeeList.authorisedWithInvalidToken();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void checkGetTransferPayeeListWithAuthorizedAccess()  {
		getTransferPayeeList.authorisedWithValidToken();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransferPayeeList.validatePayload();
	}

}
