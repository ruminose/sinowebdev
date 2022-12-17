package com.example.sino.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.sino.demo.model.Customer;

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

import com.example.sino.demo.service.CustomerService;

@Controller
public class Customercontroller {

	@Autowired
	private CustomerService customerService;
	
	// ***************************************************************************************** //
	//customer
	@GetMapping("/Customer")
	public ModelAndView Customer(){
		List<Customer> list = customerService.findAll();
		return new ModelAndView("customer","list",list);
	}
	@GetMapping("/Addcustomer")
	public String AddCustomer(ModelMap map){
		Customer customer = new Customer();
		map.addAttribute("customer",customer);
		return "Addcustomer";
	}
	//Edit Customer
	@GetMapping(value = "/editcustomer/{id}")
	public String edit(@PathVariable int id, ModelMap model){
		
		Customer customer = customerService.findbyid(id);
		model.addAttribute("customer", customer);
		return "editcustomer";
	}

	//Save Edit and Submit customer
	@PostMapping(value = "/editsavecustomer")
	public String editsave(@Valid @ModelAttribute("customer") Customer p, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("has errors");
			return "editcustomer";
		} else {

			Customer std = customerService.findbyid(p.getId());

			std.setName(p.getName());
			std.setSurname(p.getSurname());
			std.setStatus(p.getStatus());

			customerService.Edit(std);
			return "redirect:/Customer";
		}
	}
	//save customer
	@PostMapping("/savecustomer")
	public String savecustomer(@Validated Customer customer,
	BindingResult result,
	RedirectAttributes redirectAttributes){

		if(result.hasErrors()){
			return "Addcustomer";
		}else{
			customerService.Edit(customer);
			return "redirect:/Customer";
		}
		
	}

	//Delete customer
	@GetMapping(value="/Deletecustomer/{id}")
	public ModelAndView delete(@PathVariable int id){
		Customer customer = customerService.findbyid(id);
		customerService.delete(customer);
		return new ModelAndView("redirect:/Customer");

	}
	//List status customer
	@ModelAttribute("status")
	public List<String> initializeStatus(){
		List<String> status = new ArrayList<String>();
		status.add("Active");
		status.add("inActive");
		// status.add("delete");
		return status;
	}
	//************************************************************************************************//
	// @ModelAttribute("category")
	// public List<String> initializeCategory(){
	// 	List<String> category = new ArrayList<String>();
	// 	category.add("Phone");
	// 	category.add("Notebook");
	// 	category.add("Accessery");
	// 	return category;
	// }
}
