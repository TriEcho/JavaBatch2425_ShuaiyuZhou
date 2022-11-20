package com.antra.util;

import com.antra.entity.ProductItemEntity;
import com.antra.vo.ProductItem;

public class ProductItemEntityConverter {
    public static ProductItem convertEntityToProductItem(ProductItemEntity pe){
        if(pe != null){
            ProductItem p = new ProductItem();
            p.setId(pe.getId());
            p.setName(pe.getName());
            p.setPrice(pe.getPrice());
            return p;
        }
        else return null;
    }
}
