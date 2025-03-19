//package api.test;
//
//import api.methods.baseMethod;
//import api.methods.beneficiariesByTranType;
//import api.utils.ConstantApiUtils;
//import org.json.simple.parser.ParseException;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Method;
//
//public class api_beneficiariesByTranTypeTest extends baseMethod {
//    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
//    public beneficiariesByTranType beneficiariesByTranType;
//
//    @BeforeClass()
//    public void setUp() {
//        beneficiariesByTranType = new beneficiariesByTranType();
//    }
//    @BeforeMethod()
//    public void resetData (Method m){
//        setTestName(m.getName());
//    }
//    //Negative cases
//    @Test(priority = 1,testName = "Verify that Beneficiaries for each transaction type cannot be accessed with Unauthorized Access")
//    //For unauthorized access
//    public void checkBeneficiariesByTranTypeWithUnauthorizedAccess() {
//        beneficiariesByTranType.authorisedWithInvalidToken();
//        beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
//        beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
//
//    }
//
//    @Test(priority = 2,testName ="Verify that Beneficiaries Cannot Be Accessed With Empty Transaction Type" )
//    //For empty transaction type
//    public void checkBeneficiariesByTranTypeWithEmptyTranType() throws IOException, ParseException {
//        beneficiariesByTranType.authorisedWithValidToken();
//        beneficiariesByTranType.setPayloadWithEmptyTranType();
//        beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
//        beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//        beneficiariesByTranType.validatePayloadForEmptyTranType();
//    }
//
//    @Test(priority = 3,testName = "Verify that Beneficiaries for each transaction type can be accessed with Authorized Access")
//    //For happy path
//    public void checkBeneficiariesByTranTypeWithCorrectTranType() throws IOException, ParseException {
//        beneficiariesByTranType.authorisedWithValidToken();
//        beneficiariesByTranType.setPayloadWithValidData();
//        beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
//        beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//        beneficiariesByTranType.validatePayload();
//    }
//
//}
