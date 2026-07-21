package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.model.Products;
import com.StrugarMaximIonut.erp.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public boolean findProductById(Integer productID){
        if(productsRepository.findById(productID).equals(Optional.empty())){
            return false;
        }
        return true;
    }

    public void deleteProductById(Integer productID){
        productsRepository.deleteById(productID);
    }
}
