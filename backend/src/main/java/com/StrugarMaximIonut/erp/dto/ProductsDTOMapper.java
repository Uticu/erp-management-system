package com.StrugarMaximIonut.erp.dto;

import com.StrugarMaximIonut.erp.model.Products;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductsDTOMapper implements Function<Products, ProductsDTO> {
    @Override
    public ProductsDTO apply(Products products){
        return new ProductsDTO(
            products.getProductID(),
            products.getProductName(),
            products.getProductPrice(),
            products.getProductStock());
    }
}
