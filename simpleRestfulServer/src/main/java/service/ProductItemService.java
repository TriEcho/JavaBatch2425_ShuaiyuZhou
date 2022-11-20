package com.antra.service;

import java.util.List;

import com.antra.vo.ProductItem;

public interface ProductItemService {
    // CRUD
    ProductItem saveProduct(ProductItem p);
    ProductItem findById(long id);
    List<ProductItem> findAllProduct();
    ProductItem updateProduct(ProductItem p);
    void deleteProductById(long id);
}
