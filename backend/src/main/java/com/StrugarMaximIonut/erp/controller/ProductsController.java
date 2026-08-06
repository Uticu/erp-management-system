package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestDTO;
import com.StrugarMaximIonut.erp.service.ProductsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductsController {
    private ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductsDTO>> getProducts(){
        List<ProductsDTO> list = productsService.getAllProducts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductsById(
            @Min(value = 1, message = "Id must be atleast 1")
            @PathVariable Integer id){
        ProductsDTO productsDTO = productsService.getProductById(id);
        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<ProductsDTO> getProductsByName(
            @NotBlank(message = "Name is mandatory")
            @Size(max = 255, message = "Name cannot exceed 255 characters")
            String name){
        ProductsDTO list = productsService.getProductsByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameContains")
    public ResponseEntity<List<ProductsDTO>> getProductsContains(
            @NotBlank(message = "String is mandatory")
            @Size(max = 255, message = "String cannot exceed 255 characters")
            String nameContains){
        List<ProductsDTO> list = productsService.getProductsContainsString(nameContains);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameStartsWith")
    public ResponseEntity<List<ProductsDTO>> getProductsStartingWith(
            @NotBlank(message = "String is mandatory")
            @Size(max = 255, message = "String cannot exceed 255 characters")
            String nameStartsWith){
        List<ProductsDTO> list = productsService.getProductsStartinWithString(nameStartsWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameEndsWith")
    public ResponseEntity<List<ProductsDTO>> getProductsEndingWith(
            @NotBlank(message = "String is mandatory")
            @Size(max = 255, message = "String cannot exceed 255 characters")
            String nameEndsWith){
        List<ProductsDTO> list = productsService.getProductsEndingWithString(nameEndsWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "inStock")
    public ResponseEntity<List<ProductsDTO>> getProductsInStock(){
        List<ProductsDTO> list = productsService.getProductsInStock();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "outOfStock")
    public ResponseEntity<List<ProductsDTO>> getProductsOutOfStock(){
        List<ProductsDTO> list = productsService.getProductsOutOfStock();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "lessThan")
    public ResponseEntity<List<ProductsDTO>> getProductsLessThanPrice(
            @DecimalMin(value = "0.1", message = "Price must be atleast 0.1")
            @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
            BigDecimal lessThan){
        List<ProductsDTO> list = productsService.getProductsLessThanPrice(lessThan);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "equal")
    public ResponseEntity<List<ProductsDTO>> getProductsEqualPrice(
            @DecimalMin(value = "0.1", message = "Price must be atleast 0.1")
            @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
            BigDecimal equal){
        List<ProductsDTO> list = productsService.getProductsEqualPrice(equal);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "greaterThan")
    public ResponseEntity<List<ProductsDTO>> getProductsGreaterThanPrice(
            @DecimalMin(value = "0.1", message = "Price must be atleast 0.1")
            @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
            BigDecimal greaterThan){
        List<ProductsDTO> list = productsService.getProductsGreaterThanPrice(greaterThan);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = {"minPrice", "maxPrice"})
    public ResponseEntity<List<ProductsDTO>> getProductsBetweenPrice(
            @DecimalMin(value = "0.1", message = "Price must be atleast 0.1")
            @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
            BigDecimal minPrice,
            @DecimalMin(value = "0.1", message = "Price must be atleast 0.1")
            @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
            BigDecimal maxPrice){
        List<ProductsDTO> list = productsService.getProductsBetweenPrice(minPrice, maxPrice);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@Min(value = 1, message = "Id must be atleast 1")
                                                      @PathVariable Integer id){
        productsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> modifyProducts(@Valid @RequestBody ProductsRequestDTO productsRequestDTO,
                                                      @Min(value = 1, message = "Id must be atleast 1")
                                                      @PathVariable Integer id){
        ProductsDTO productsDTO = productsService.modifyProducts(productsRequestDTO, id);
        return ResponseEntity.ok(productsDTO);
    }

    @PostMapping("")
    public ResponseEntity<ProductsDTO> insertProducts(@Valid @RequestBody ProductsRequestDTO productsRequestDTO){
        ProductsDTO productsDTO = productsService.insertProducts(productsRequestDTO);
        return ResponseEntity.ok(productsDTO);
    }

}
