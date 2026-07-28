package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTOMapper;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestMapper;
import com.StrugarMaximIonut.erp.exception.products.NoProductsException;
import com.StrugarMaximIonut.erp.exception.products.ProductFoundException;
import com.StrugarMaximIonut.erp.exception.products.ProductNotFoundException;
import com.StrugarMaximIonut.erp.model.Products;
import com.StrugarMaximIonut.erp.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductsDTOMapper productsDTOMapper;
    private final ProductsRequestMapper productsRequestMapper;

    public ProductsService(ProductsRepository productsRepository, ProductsDTOMapper productsDTOMapper, ProductsRequestMapper productsRequestMapper){
        this.productsRepository = productsRepository;
        this.productsDTOMapper = productsDTOMapper;
        this.productsRequestMapper = productsRequestMapper;
    }

    public List<ProductsDTO> getAllProducts(){
        List<Products> productsList = productsRepository.findAll();

        if(productsList.isEmpty()){
            throw new NoProductsException("The database has no products in it");
        }

        return productsList.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    private Products findProductsEntityById(Integer id){
        return productsRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " is not in the database"));
    }

    public ProductsDTO getProductById(Integer id){
        return productsDTOMapper.apply(findProductsEntityById(id));
    }

    public void deleteById(Integer id){
        if(!productsRepository.existsById(id)){
            throw new ProductNotFoundException("Product with id " + id + " is not in the database");
        }
        productsRepository.deleteById(id);
    }

    public ProductsDTO modifyProducts(ProductsRequestDTO productsRequestDTO, Integer id){
        Products products = this.findProductsEntityById(id);
        products.setProductStock(productsRequestDTO.productStock());
        products.setProductPrice(productsRequestDTO.productPrice());
        products.setProductName(productsRequestDTO.productName());

        productsRepository.save(products);

        return productsDTOMapper.apply(products);
    }

    public ProductsDTO insertProducts(ProductsRequestDTO productsRequestDTO){
        if(productsRepository.existsByProductName(productsRequestDTO.productName())){
            throw new ProductFoundException("Product with name " + productsRequestDTO.productName() + " is already in database");
        }
        Products products = productsRequestMapper.apply(productsRequestDTO);
        productsRepository.save(products);

        return productsDTOMapper.apply(products);
    }

}
