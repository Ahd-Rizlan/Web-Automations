package api.utils;


import api.methods.baseMethod;

public class validateResponse {
    private baseRequest baseRequest ;

    public validateResponse(baseRequest baseRequest) {
        this.baseRequest =baseRequest;
    }

    public void validateResponse(String JsonResponseFile) {
        new baseMethod.PayloadValidator().validateJsonFileWithResponse( JsonResponseFile, baseRequest.getResponse());
    }



    //todo validate it to exlude the files
    //todo also make sure that responces are neglected
}
