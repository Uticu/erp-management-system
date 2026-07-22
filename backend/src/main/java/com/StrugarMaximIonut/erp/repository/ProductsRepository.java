package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository  extends JpaRepository<Products, Integer> {
    boolean existsByProductName(String name);
}
