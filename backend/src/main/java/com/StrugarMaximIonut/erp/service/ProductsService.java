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

import java.math.BigDecimal;
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

    private Products findProductEntityByName(String name){
        Products product = productsRepository.findByProductName(name);
        if(product == null){
            throw new ProductNotFoundException("Product with name " + name + " is not in the database");
        }
        return product;
    }

    public ProductsDTO getProductById(Integer id){
        return productsDTOMapper.apply(findProductsEntityById(id));
    }

    public ProductsDTO getProductsByName(String name){
        Products product  = findProductEntityByName(name);
        return productsDTOMapper.apply(product);
    }

    public List<ProductsDTO> getProductsContainsString(String nameContains){
        List<Products> list = productsRepository.findAllByProductNameContains(nameContains);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products that contains '" + nameContains + "' " +
                    "in their name in the database");
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsStartinWithString(String nameStartsWith){
        List<Products> list = productsRepository.findAllByProductNameStartsWith(nameStartsWith);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products where their name starts with '"
            + nameStartsWith + "' in the database");
        }
        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsEndingWithString(String nameEndsWith){
        List<Products> list = productsRepository.findAllByProductNameEndsWith(nameEndsWith);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products where their name ends with '"
                    + nameEndsWith + "' in the database");
        }
        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsInStock(){
        List<Products> list = productsRepository.findAllByProductStockGreaterThan(0);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products in stock");
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsOutOfStock(){
        List<Products> list = productsRepository.findAllByProductStockLessThanEqual((0));

        if(list.isEmpty()){
            throw new NoProductsException("There are no products out of stock");
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsLessThanPrice(BigDecimal lessThan){
        List<Products> list = productsRepository.findAllByProductPriceLessThan(lessThan);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products with the price less than " + lessThan);
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsEqualPrice(BigDecimal equal){
        List<Products> list = productsRepository.findAllByProductPriceEquals(equal);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products with the price that equals " + equal);
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsGreaterThanPrice(BigDecimal greaterThan){
        List<Products> list = productsRepository.findAllByProductPriceGreaterThan(greaterThan);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products with the price that is greater than " + greaterThan);
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsBetweenPrice(BigDecimal minPrice, BigDecimal maxPrice){
        List<Products> list = productsRepository.findAllByProductPriceBetween(minPrice, maxPrice);

        if(list.isEmpty()){
            throw new NoProductsException("There are no products with the price between " + minPrice + " and " + maxPrice);
        }

        return list.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id){
        if(!productsRepository.existsById(id)){
            throw new ProductNotFoundException("Product with id " + id + " is not in the database");
        }
        productsRepository.deleteById(id);
    }

    public ProductsDTO modifyProducts(ProductsRequestDTO productsRequestDTO, Integer id){
        Products products = this.findProductsEntityById(id);

        if(!(productsRequestDTO.productName().equalsIgnoreCase(products.getProductName()))
             && productsRepository.existsByProductName(productsRequestDTO.productName())){
            throw new ProductFoundException("Product " + productsRequestDTO.productName() + " is already in the database");
        }

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
