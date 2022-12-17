package com.example.sino.demo.service;

import com.example.sino.demo.repository.UsersRepository;
import com.example.sino.demo.model.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UsersRepository userRepository;

    //Get All Users
    public List<Users> findAll() {
        return userRepository.findAll(Sort.by("id").ascending());
    }

    //Get user by id
    public Users findbyid(Integer id){
        return userRepository.getOne(id);
    }

    //Add and Edit Users
    public Users Edit(Users std){
        return userRepository.save(std);
    }

    //Delete user
    public void delete(Users std){
        userRepository.delete(std);
    }

}
