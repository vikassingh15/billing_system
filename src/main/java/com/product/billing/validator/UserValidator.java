package com.product.billing.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.UserDTO;
import com.product.billing.model.User;
import com.product.billing.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o instanceof UserDTO){
            UserDTO userDTO = (UserDTO) o;
            validateUserDTO(errors, userDTO);
        }
        if(o instanceof User) {
            User user = (User) o;
            validateUser(errors, user);
        }
    }

    private void validateUserDTO(Errors errors, UserDTO userDTO) {
        validateUserName(errors, userDTO.getUsername());
        validatePassword(errors, userDTO.getPassword(), userDTO.getPasswordConfirm());
    }

    private void validatePassword(Errors errors, String password, String passwordConfirm) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (password.length() < 8 || password.length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!passwordConfirm.equals(password)) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }

    private void validateUserName(Errors errors, String username) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (username.length() < 6 || username.length() > 32) {
            errors.rejectValue("username", "Size636");
        }
        if (userService.findByUsername(username) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }

    private void validateUser(Errors errors, User userDTO) {
        validateUserName(errors, userDTO.getUsername());
        validatePassword(errors, userDTO.getPassword(), userDTO.getPasswordConfirm());
    }
}
