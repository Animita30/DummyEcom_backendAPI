package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.LoginDto;
import com.ecom.demo4_1.Dto.UserDto;
import com.ecom.demo4_1.Model.User;
import com.ecom.demo4_1.Service.ForUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User registerUser(@RequestBody UserDto userDto) {

        return userService.registerUser(userDto);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }



}
