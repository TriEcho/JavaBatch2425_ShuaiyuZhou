package com.antra.exception;

import com.antra.exception.ProductItemException;

public class ProductItemNotFoundException extends ProductItemException {
    public ProductItemNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
