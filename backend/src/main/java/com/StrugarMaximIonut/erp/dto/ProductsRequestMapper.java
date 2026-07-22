package com.StrugarMaximIonut.erp.dto;

import com.StrugarMaximIonut.erp.model.Products;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductsRequestMapper implements Function<ProductsRequestDTO, Products> {
    @Override
    public Products apply(ProductsRequestDTO productsRequestDTO){
        Products products = new Products();
        products.setProductName(products.getProductName());
        products.setProductPrice(products.getProductPrice());
        products.setProductStock(products.getProductStock());

        return products;
    }
}
