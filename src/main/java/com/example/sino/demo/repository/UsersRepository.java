package com.example.sino.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sino.demo.model.Users;


public interface UsersRepository extends JpaRepository <Users, Integer> {
    
    Users findByUsersname (String usersname);
}
