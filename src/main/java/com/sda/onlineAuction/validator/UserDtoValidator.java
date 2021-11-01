package com.sda.onlineAuction.validator;

import com.sda.onlineAuction.dto.UserDto;
import com.sda.onlineAuction.model.User;
import com.sda.onlineAuction.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDtoValidator {
    @Autowired
    private UserRepository userRepository;

    public void validate(UserDto userDto, BindingResult bindingResult) {
        validatePassword(userDto, bindingResult);
        validateEmail(userDto, bindingResult);
        validateName(userDto, bindingResult);
    }

    private void validatePassword(UserDto userDto, BindingResult bindingResult) {
        String password = userDto.getPassword();
        if (password.length() < 6) {
            FieldError fieldError = new FieldError("userDto", "password", "The password is too short");
            bindingResult.addError(fieldError);
        }
    }

    private void validateEmail(UserDto userDto, BindingResult bindingResult) {
        String email = userDto.getEmail();
        String regex = "^(.+)@(.+)$";
        // ^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            FieldError fieldError = new FieldError("userDto","email","This email is already connected to an account");
            bindingResult.addError(fieldError);
        }
        if (!matcher.matches()) {
            FieldError fieldError = new FieldError("userDto", "email", "Please introduce a valid email!");
            bindingResult.addError(fieldError);
        }




    }

    private void validateName(UserDto userDto, BindingResult bindingResult) {
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+") || StringUtils.isBlank(firstName)) {
            FieldError fieldError = new FieldError("userDto", "firstName", "Please insert a real name!");
            bindingResult.addError(fieldError);

        }
        if (lastName.isEmpty() || !lastName.matches("[a-zA-Z]+") || StringUtils.isBlank(lastName)) {
            FieldError fieldError = new FieldError("userDto", "lastName", "Please insert a real name!");
            bindingResult.addError(fieldError);
        }
    }
}
