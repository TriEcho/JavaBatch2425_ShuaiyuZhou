package com.antra.service;

import com.antra.service.ProductItemService;
import com.antra.dao.ProductItemRepository;
import com.antra.entity.ProductItemEntity;
import com.antra.vo.ProductItem;
import com.antra.util.ProductItemEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service("productItemService")
public class ProductItemServiceImpl implements ProductItemService {
    @Autowired
    private ProductItemRepository productRepo;

    @Override
    @Transactional
    public ProductItem saveProduct(ProductItem p) {
        ProductItemEntity productItemEntity = productRepo.save(new ProductItemEntity(
                p.getId(),
                p.getName(),
                p.getPrice()
        ));
        return ProductItemEntityConverter.convertEntityToProductItem(productItemEntity);
    }

    @Override
    public ProductItem findById(long id) {
        ProductItemEntity productItemEntity = productRepo.findById(id).orElse(null);
        return ProductItemEntityConverter.convertEntityToProductItem(productItemEntity);
    }

    @Override
    public List<ProductItem> findAllProduct() {
        List<ProductItemEntity> products = productRepo.findAll();
        return products.stream().map(e -> new ProductItem(
                e.getId(),
                e.getName(),
                e.getPrice()
        )).collect(Collectors.toList());
    }

    @Override
    public ProductItem updateProduct(ProductItem p) {
        ProductItemEntity productItemEntity = productRepo.saveAndFlush(new ProductItemEntity(
                p.getId(),
                p.getName(),
                p.getPrice()
        ));
        return ProductItemEntityConverter.convertEntityToProductItem(productItemEntity);
    }

    @Override
    public void deleteProductById(long id) {
        productRepo.deleteById(id);
    }
}
