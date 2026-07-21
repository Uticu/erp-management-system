package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.model.Products;
import com.StrugarMaximIonut.erp.service.ClientService;
import com.StrugarMaximIonut.erp.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    private ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping("/get-all-products")
    public List<Products> getAllProducst(){
        return productsService.getAllProducts();
    }

    @DeleteMapping("/remove-by-id/{id}")
    public boolean deleteProductById(@PathVariable("id") Integer productID){
        if(productsService.findProductById(productID)){
            productsService.deleteProductById(productID);
            return true;
        }
        return false;
    }

}
