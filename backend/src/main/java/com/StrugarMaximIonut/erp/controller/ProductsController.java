package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestMapper;
import com.StrugarMaximIonut.erp.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductsRequestMapper productsRequestMapper;
    private ProductsService productsService;

    public ProductsController(ProductsService productsService, ProductsRequestMapper productsRequestMapper){
        this.productsService = productsService;
        this.productsRequestMapper = productsRequestMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ProductsDTO>> getProducts(){
        List<ProductsDTO> list = productsService.getAllProducts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductsById(@PathVariable Integer id){
        ProductsDTO productsDTO = productsService.getProductById(id);
        return ResponseEntity.ok(productsDTO);
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

    @PostMapping("/{id}")
    public ResponseEntity<ProductsDTO> insertProducts(@RequestBody ProductsRequestDTO productsRequestDTO){
        ProductsDTO productsDTO = productsService.insertProducts(productsRequestDTO);
        return ResponseEntity.ok(productsDTO);
    }

}
