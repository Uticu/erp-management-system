package com.StrugarMaximIonut.erp.dto.products;

import com.StrugarMaximIonut.erp.model.Products;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductsRequestMapper implements Function<ProductsRequestDTO, Products> {
    @Override
    public Products apply(ProductsRequestDTO productsRequestDTO){
        Products products = new Products();
        products.setProductName(productsRequestDTO.productName());
        products.setProductPrice(productsRequestDTO.productPrice());
        products.setProductStock(productsRequestDTO.productStock());

        return products;
    }
}
