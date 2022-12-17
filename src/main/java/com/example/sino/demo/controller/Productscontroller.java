package com.example.sino.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.naming.spi.DirStateFactory.Result;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import com.example.sino.demo.model.Users;
import com.example.sino.demo.model.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.sino.demo.service.ProductsService;

@Controller
public class Productscontroller {

    //import Service
	@Autowired
	private ProductsService productsservice;
	
    //Create a new instance
	@GetMapping("/products")
	public ModelAndView Products(){
		List<Products> list = productsservice.findAll();
		return new ModelAndView("Products","list",list);
	}
    //AddProducts
	@GetMapping("/AddProducts")
	public String Addproducts(ModelMap map){
		Products products = new Products();
		map.addAttribute("products", products);
		return "AddProducts";
	}
    //Edit Products
    @GetMapping(value = "/Editproducts/{id}")
    public String edit(@PathVariable int id, ModelMap model){
        Products products = productsservice.findbyid(id);
        model.addAttribute("products",products);
        return "Editproducts";
    }
    //Save Edit and Submit Products
    @PostMapping(value = "/editsaveproducts")
    public String editsave( @ModelAttribute ("products") Products p, BindingResult result){

        if(result.hasErrors()){
            System.out.println("Error");
            return "Egitditproducts";
        }else{
            Products std = productsservice.findbyid(p.getId());

            std.setCode                     (p.getCode());
            std.setProductsname             (p.getProductsname());
            std.setProductscategory         (p.getProductscategory());
            std.setPrice                    (p.getPrice());
            std.setStatus                   (p.getStatus());

            productsservice.Edit(std);
            return "redirect:/products";
        }	
    }
    //Save products
    @PostMapping("/saveproducts")
    public String Saveproducts(Products products ,BindingResult result){
        if(result.hasErrors()){
            return"AddProducts";
        }else{
            productsservice.Edit(products);
            return "redirect:/products";
        }
    }
    //Delete products
    @GetMapping(value="/Deleteproducts/{id}")
    public ModelAndView delete(@PathVariable int id){
        Products products = productsservice.findbyid(id);
        productsservice.delete(products);
        
        return new ModelAndView("redirect:/products");
    }
    
    //List status products
    @ModelAttribute("status")
    public List<String> initializeStatus(){
        List<String> status = new ArrayList<String>();
        status.add("Active");
        status.add("inActive");

        return status;
    }
    @ModelAttribute("category")
    public List<String> initializeProductscategory(){
        List<String> category = new ArrayList<String>();
        category.add("Phone");
        category.add("Notebook");
        category.add("Computer");

        return category;
    }

}

/*
 * 
 * Eclip Ide
 * 
 * ctrl + space bar คีย์ลัด
 * 
 * ctrl + 1 ทางลัดแก้ไข
 * 
 * alt + shift + s
 * 
 * ctrl + shift + L ดูทางลัด
 * 
 * ctrl + shift + R ค้นหาชื่อไฟลในโปรเจ็กต์
 * 
 * ctrl + shift + T เสริทดู medthod ต่างๆ
 * 
 * F3 F4 ดูโครงสร้างฟังชั่นนั้นๆ
 * 
 */