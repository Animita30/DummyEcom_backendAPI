package com.ecom.demo4_1.Service.ForUser;

import com.ecom.demo4_1.Dto.LoginDto;
import com.ecom.demo4_1.Dto.UserDto;
import com.ecom.demo4_1.Model.User;

public interface UserService {
    User registerUser(UserDto userDto);
    String loginUser(LoginDto loginDto);
}
