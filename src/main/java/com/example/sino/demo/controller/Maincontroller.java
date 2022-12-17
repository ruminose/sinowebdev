package com.example.sino.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.sino.demo.model.Users;

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

import com.example.sino.demo.service.UserService;

@Controller
public class Maincontroller {

	@Autowired
	private UserService userservice;

	
	// ***************************************************************************************** //
	//User
	@GetMapping("/User")
	public ModelAndView Users(){
		List<Users> list = userservice.findAll();
		return new ModelAndView("User","list",list);
	}
	@GetMapping("/AddUser")
	public String AddUsers(ModelMap map){
		Users user = new Users();
		map.addAttribute("user",user);
		return "AddUser";
	}
	//Edit Users
	@GetMapping(value = "/EditUser/{id}")
	public String edit(@PathVariable int id, ModelMap model){
		
		Users user = userservice.findbyid(id);
		model.addAttribute("user", user);
		return "EditUser";
	}

	//Save Edit and Submit user
	@PostMapping(value = "/editsave")
	public String editsave(@Valid @ModelAttribute("user") Users p, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("has errors");
			return "EditUser";
		} else {

			Users std = userservice.findbyid(p.getId());

			std.setName(p.getName());
			std.setSurname(p.getSurname());
			std.setUsersname(p.getUsersname());
			std.setPassword(p.getPassword());
			std.setStatus(p.getStatus());

			userservice.Edit(std);
			return "redirect:/User";
		}
	}
	//save user
	@PostMapping("/saveuser")
	public String saveUser(@Validated Users user,
	BindingResult result,
	RedirectAttributes redirectAttributes){

		if(result.hasErrors()){
			return "AddUser";
		}else{
			userservice.Edit(user);
			return "redirect:/User";
		}
		
	}

	//Delete user
	@GetMapping(value="/DeleteUser/{id}")
	public ModelAndView delete(@PathVariable int id){
		Users user = userservice.findbyid(id);
		userservice.delete(user);
		return new ModelAndView("redirect:/User");

	}
	//List status user
	@ModelAttribute("status")
	public List<String> initializeStatus(){
		List<String> status = new ArrayList<String>();
		status.add("Active");
		status.add("inActive");
		// status.add("delete");
		return status;
	}
	//************************************************************************************************//
	@ModelAttribute("category")
	public List<String> initializeCategory(){
		List<String> category = new ArrayList<String>();
		category.add("Phone");
		category.add("Notebook");
		category.add("Accessery");
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