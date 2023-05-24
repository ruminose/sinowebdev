package com.example.sino.demo.controller;

import java.util.List;

import javax.persistence.Id;

import com.example.sino.demo.model.Customer;
import com.example.sino.demo.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.sino.demo.repository.*;
import com.example.sino.demo.service.*;



@Controller
public class Ordercontroller {
    
    @Autowired
	private ProductsService productsservice;

    @Autowired
	private CustomerService customerservice;

    @GetMapping("/Order")
	public ModelAndView Products(){
		List<Products> list = productsservice.findAll();
		return new ModelAndView("Order","list",list);
	}
    @GetMapping(value = "/s")
           public String one (){
            return "searchtest";
        }
        @PostMapping("/search")
            public String search(@PathVariable Integer id, Model model) {
            Customer customer = customerservice.findbyid(id);
            model.addAttribute("Customer", customer);
            System.out.println(customer);
            return "searchcustomer";
        }	

    }



    // @PostMapping("/search")
    //     public String performSearch(@RequestParam String query, Model model) {
    //     List<Customer> customer = customerservice.findAll();
    //     model.addAttribute("Customer", customer);
    //     System.out.println(customer);
    //     return "searchcustomer";
    //     }