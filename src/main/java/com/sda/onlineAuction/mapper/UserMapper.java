package com.sda.onlineAuction.mapper;

import com.sda.onlineAuction.dto.UserDto;
import com.sda.onlineAuction.model.User;
import com.sda.onlineAuction.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserMapper {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User map(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

//        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        String passwordCoded = bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(passwordCoded);
        user.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        return user;
    }

    public UserDto map(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserRole(user.getUserRole().name());
        return userDto;

    }
}
