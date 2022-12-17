package com.example.sino.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sino.demo.model.Customer;

    public interface CustomerRepository extends JpaRepository <Customer, Integer> {
    

}

