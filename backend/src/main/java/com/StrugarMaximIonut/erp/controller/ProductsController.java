package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestDTO;
import com.StrugarMaximIonut.erp.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
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
    public ResponseEntity<ProductsDTO> getProductsById(@PathVariable Integer id){
        ProductsDTO productsDTO = productsService.getProductById(id);
        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<ProductsDTO> getProductsByName(String name){
        ProductsDTO list = productsService.getProductsByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameContains")
    public ResponseEntity<List<ProductsDTO>> getProductsContains(String nameContains){
        List<ProductsDTO> list = productsService.getProductsContainsString(nameContains);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameStartsWith")
    public ResponseEntity<List<ProductsDTO>> getProductsStartingWith(String nameStartsWith){
        List<ProductsDTO> list = productsService.getProductsStartinWithString(nameStartsWith);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "nameEndsWith")
    public ResponseEntity<List<ProductsDTO>> getProductsEndingWith(String nameEndsWith){
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
    public ResponseEntity<List<ProductsDTO>> getProductsLessThanPrice(BigDecimal lessThan){
        List<ProductsDTO> list = productsService.getProductsLessThanPrice(lessThan);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "equal")
    public ResponseEntity<List<ProductsDTO>> getProductsEqualPrice(BigDecimal equal){
        List<ProductsDTO> list = productsService.getProductsEqualPrice(equal);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = "greaterThan")
    public ResponseEntity<List<ProductsDTO>> getProductsGreaterThanPrice(BigDecimal greaterThan){
        List<ProductsDTO> list = productsService.getProductsGreaterThanPrice(greaterThan);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/search", params = {"minPrice", "maxPrice"})
    public ResponseEntity<List<ProductsDTO>> getProductsBetweenPrice(BigDecimal minPrice, BigDecimal maxPrice){
        List<ProductsDTO> list = productsService.getProductsBetweenPrice(minPrice, maxPrice);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){
        productsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> modifyProducts(@RequestBody ProductsRequestDTO productsRequestDTO, @PathVariable Integer id){
        ProductsDTO productsDTO = productsService.modifyProducts(productsRequestDTO, id);
        return ResponseEntity.ok(productsDTO);
    }

    @PostMapping("")
    public ResponseEntity<ProductsDTO> insertProducts(@RequestBody ProductsRequestDTO productsRequestDTO){
        ProductsDTO productsDTO = productsService.insertProducts(productsRequestDTO);
        return ResponseEntity.ok(productsDTO);
    }

}
