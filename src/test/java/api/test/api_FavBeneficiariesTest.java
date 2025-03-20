//package api.test;
//
//import api.methods.baseMethod;
//import api.methods.favBeneficiaries;
//import api.utils.ConstantApiUtils;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.lang.reflect.Method;
//
//
//public class api_FavBeneficiariesTest extends baseMethod {
//	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
//	public favBeneficiaries favBeneficiaries;
//
//	@BeforeClass()
//	public void setUp() {
//		favBeneficiaries = new favBeneficiaries();
//	}
//	@BeforeMethod()
//	public void resetData (Method m){
//		setTestName(m.getName());
//	}
//	@Test(priority = 1,testName = "Verify that Favourite Beneficiaries List is Not Accessible with Unauthorized Access ")
//	public void checkFavBeneficiaryDetailsApiUnauthorizedAccess() {
//		favBeneficiaries.authorisedWithInvalidToken();
//		favBeneficiaries.invokeFavBeneficiaries();
//		favBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
//	}
//
//	@Test(priority = 2,testName = "Verify that Favourite Beneficiaries List is Accessible with Authorized Access")
//	public void checkFavBeneficiaryDetailsApiAuthorizedAccess() {
//		favBeneficiaries.authorisedWithValidToken();
//		favBeneficiaries.invokeFavBeneficiaries();
//		favBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//		favBeneficiaries.validatePayload();
//	}
//	@Test(priority = 2,testName = "Verify that Favourite Beneficiaries  Not retrieved When Its Empty" )
//	public void checkFavBeneficiaryDetailsNotRetrievedWithNoFavBeneficiaries() {
//		favBeneficiaries.authorisedWithValidToken();
//		favBeneficiaries.invokeFavBeneficiaries();
//		favBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//		favBeneficiaries.invokeFavBeneficiariesWithEmptyBene();
//	}
//}
