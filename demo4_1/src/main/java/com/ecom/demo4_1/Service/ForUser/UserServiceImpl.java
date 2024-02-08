package com.ecom.demo4_1.Service.ForUser;

import com.ecom.demo4_1.Dto.LoginDto;
import com.ecom.demo4_1.Dto.UserDto;
import com.ecom.demo4_1.Model.User;
import com.ecom.demo4_1.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(UserDto userDto){
        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setMobile(userDto.getMobile());
        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginDto loginDto){
        User user=userRepository.findByUsername(loginDto.getUsername());
        if(user!=null && user.getPassword().equals(loginDto.getPassword())){
            return "login successful";
        }
        else{
            return "invalid credentials";
        }
    }
}
