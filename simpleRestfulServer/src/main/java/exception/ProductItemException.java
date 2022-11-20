package com.antra.exception;

import jdk.jshell.spi.ExecutionControl;

public abstract class ProductItemException extends RuntimeException{
    private String errorMsg;

    public String getErrorMsg(){
        return errorMsg;
    }

    public ProductItemException(){
        super();
    }

    public ProductItemException(String errorMsg){
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
