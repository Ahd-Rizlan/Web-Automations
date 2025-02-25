package api.test;

import api.utils.ConstantApiUtils;
import api.methods.baseMethod;
import api.methods.validateTransfer;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.lang.reflect.Method;

public class api_validateTransferTest extends baseMethod{
    File schema = new File(System.getProperty("user.dir")+ ConstantApiUtils.PATH_TO_SCHEMA_FOLDER+ "Category_Schema.json");
    public validateTransfer validateTransfer;
    @BeforeClass()
    //This would run before the initiation of class
    public void setup(){
        validateTransfer = new validateTransfer();
    }

    @BeforeMethod()
    //This would run before the initiation of any Methods
    public void resetData (Method m){
        setTestName(m.getName());
    }
    }
