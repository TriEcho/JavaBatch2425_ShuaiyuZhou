package com.antra.dao;

import com.antra.entity.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItemEntity, Long> {
    ProductItemEntity findByPriceBetween(double from, double to);
    ProductItemEntity findByName(String name);
}
