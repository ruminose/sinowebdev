package com.example.sino.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.sino.demo.service.UserService;
import com.example.sino.demo.model.Users;
import com.example.sino.demo.repository.UsersRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class Logincontroller {
    @Autowired
	private UserService userservice;

    @Autowired
    private UsersRepository userRepository;

    @GetMapping("/")
    public String getpagelogin(Model model) {
        Users user = new Users();
        model.addAttribute("Users", user);
        return "Login";
    }

    @PostMapping("/Login")
    public ModelAndView loginuser(@ModelAttribute ("Users") Users users){
        List<Users> list = userservice.findAll();
        // System.out.println(users.getUsersname());
        // System.out.println(users.getPassword());
        String Usersname = users.getUsersname();
        Users userdata = userRepository.findByUsersname(Usersname);
        if(users.getPassword().equals(userdata.getPassword())){
            return new ModelAndView("User","list",list);
            
        }else{
            return new ModelAndView ("User");
        }
    }
}

// @PostMapping("/User")
//     public ModelAndView loginuser(@ModelAttribute ("Users") Users users){
//         List<Users> list = userservice.findAll();
//         // System.out.println(users.getUsersname());
//         // System.out.println(users.getPassword());
//         String Usersname = users.getUsersname();
//         Users userdata = userRepository.findByUsersname(Usersname);
//         if(users.getPassword().equals(userdata.getPassword())){
//             return new ModelAndView("User","list",list);
            
//         }else{
//             return new ModelAndView ("error");
//         }
//     }





//      @PostMapping("/Login")
//      public String login(@ModelAttribute(name="Users")Users users, Model model) {
    
//         String usersname = users.getUsersname();
//         String Password =  users.getPassword();
//         System.out.println(users);
//         if("Admin".equals(usersname) && ("Admin".equals(Password))){
//             return "Users";
//         }
//         model.addAttribute("Invalid value", true);
//         return "Login";
//     }
// }


        // @RequestMapping(method = RequestMethod.POST)
        // public String submit(Model model ,@ModelAttribute("Users") Users users ){
        //     if(users != null && users.getUsersname() != null & users.getPassword() != null){
        //         if(users.getUsersname().equals(users.getUsersname()) && users.getPassword().equals(users.getPassword())){
        //             model.addAttribute("msg", users.getUsersname());
        //             System.out.println(users);
        //             return "login successfully";
                    
        //         }else{
        //             model.addAttribute("Error", "Invalid Login Failed");
        //             return "Login";
        //         }
        //         }else{
        //             model.addAttribute("error", "Invalid Details");
        //             return"Login";
        //         }
                
        //     }
