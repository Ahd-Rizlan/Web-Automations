package api.methods;



public class validateResponce {
    private baseRequest baseRequest ;

    public validateResponce(baseRequest baseRequest) {
        this.baseRequest =baseRequest;
    }

    public void validateResponce(String JsonResponceFile) {
        new baseMethod.PayloadValidator().validateJsonFileWithResponse( JsonResponceFile, baseRequest.getResponse());
    }



    //todo validate it to exlude the files
    //todo also make sure that responces are neglected
}
