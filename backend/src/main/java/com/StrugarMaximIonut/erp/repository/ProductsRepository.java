package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsRepository  extends JpaRepository<Products, Integer> {
    boolean existsByProductName(String name);

    Products findByProductName(String productName);

    List<Products> findAllByProductNameContains(String productName);

    List<Products> findAllByProductNameStartsWith(String nameStartsWith);

    List<Products> findAllByProductNameEndsWith(String productName);

    List<Products> findAllByProductStockGreaterThan(Integer productStockIsGreaterThan);

    List<Products> findAllByProductStockLessThanEqual(Integer productStockIsLessThan);

    List<Products> findAllByProductPriceLessThan(BigDecimal productPriceIsLessThan);

    List<Products> findAllByProductPriceEquals(BigDecimal productPrice);

    List<Products> findAllByProductPriceGreaterThan(BigDecimal productPriceIsGreaterThan);

    List<Products> findAllByProductPriceBetween(BigDecimal productPriceAfter, BigDecimal productPriceBefore);
}
