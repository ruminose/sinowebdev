package com.example.sino.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sino.demo.model.Products;

    public interface ProductsRepository extends JpaRepository <Products, Integer> {
    
}
