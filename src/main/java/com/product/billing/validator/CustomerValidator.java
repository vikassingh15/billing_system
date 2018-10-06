package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.CustomerDTO;

@Component
public class CustomerValidator implements Validator {
	
	CommonValidator commonValidator=new CommonValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
    	CustomerDTO customerDTO = (CustomerDTO) o;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (!errors.hasFieldErrors("name") && customerDTO.getName().length() < 6 || customerDTO.getName().length() > 32) {
            errors.rejectValue("name", "Size636");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentTerms", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        if(!errors.hasFieldErrors("city") && !commonValidator.isValidString(customerDTO.getCity())) {
       	    errors.rejectValue("city", "Notcharcters");  
        }
        
        if(!errors.hasFieldErrors("pin") && !commonValidator.isValidPIN(String.valueOf(customerDTO.getPin()))) {
       	    errors.rejectValue("pin", "NotPin");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "careofPerson", "NotEmpty");
        if(!errors.hasFieldErrors("careofPerson") && !commonValidator.isValidString(customerDTO.getCareofPerson())) {
       	    errors.rejectValue("careofPerson", "Notcharcters");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if(!errors.hasFieldErrors("email") && !commonValidator.isEmail(customerDTO.getEmail())) {
       	    errors.rejectValue("email", "NotValidEmail");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "NotEmpty");
        /*if(!errors.hasFieldErrors("website") && !commonValidator.isWebSite(String.valueOf(customerDTO.getWebsite()))) {
       	    errors.rejectValue("website", "NotWebSite");  
        }*/
        
        if(!errors.hasFieldErrors("mobile") && !commonValidator.isMobileNo(String.valueOf(customerDTO.getMobile()))) {
       	    errors.rejectValue("mobile", "NotMobile");  
        }
        
        if(!errors.hasFieldErrors("copmobileNo") && !commonValidator.isMobileNo(String.valueOf(customerDTO.getCopmobileNo()))) {
       	    errors.rejectValue("copmobileNo", "NotMobile");  
        }
        
        if(!errors.hasFieldErrors("phone") && !commonValidator.isNumaric(String.valueOf(customerDTO.getPhone()))) {
       	    errors.rejectValue("phone", "NotNumaric");  
        }
        
    }
}