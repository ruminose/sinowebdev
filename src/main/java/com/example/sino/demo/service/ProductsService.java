package com.example.sino.demo.service;

import com.example.sino.demo.repository.ProductsRepository;
import com.example.sino.demo.model.Products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    
    @Autowired
    ProductsRepository productsRepository;

    //Get All Users
    public List<Products> findAll() {
        return productsRepository.findAll(Sort.by("id").ascending());
    }

    //Get user by id
    public Products findbyid(Integer id){
        return productsRepository.getOne(id);
    }

    //Add and Edit Users
    public Products Edit(Products std){
        return productsRepository.save(std);
    }

    //Delete user
    public void delete(Products std){
        productsRepository.delete(std);
    }

}
