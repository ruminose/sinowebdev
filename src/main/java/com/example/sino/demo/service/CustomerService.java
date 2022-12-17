package com.example.sino.demo.service;

import com.example.sino.demo.repository.CustomerRepository;
import com.example.sino.demo.model.Customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository CustomerRepository;

    //Get All Users
    public List<Customer> findAll() {
        return CustomerRepository.findAll(Sort.by("id").ascending());
    }

    //Get user by id
    public Customer findbyid(Integer id){
        return CustomerRepository.getOne(id);
    }

    //Add and Edit Users
    public Customer Edit(Customer std){
        return CustomerRepository.save(std);
    }

    //Delete user
    public void delete(Customer std){
        CustomerRepository.delete(std);
    }

}
