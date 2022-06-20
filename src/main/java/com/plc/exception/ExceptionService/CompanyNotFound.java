package com.plc.exception.ExceptionService;

public class CompanyNotFound extends RuntimeException{
   public CompanyNotFound(){
        super();
    }
    public CompanyNotFound(String msg){
        super(msg);
    }
    public CompanyNotFound(String msg, Throwable throwable){
        super(msg,throwable);
    }
}
