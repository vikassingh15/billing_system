package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.VendorDTO;

@Component
public class VendorValidator implements Validator {

	CommonValidator commonValidator=new CommonValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return VendorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    	VendorDTO vendorDTO = (VendorDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (!errors.hasFieldErrors("name") && vendorDTO.getName().length() < 6 || vendorDTO.getName().length() > 32) {
            errors.rejectValue("name", "Size636");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        if(!errors.hasFieldErrors("city") && !commonValidator.isValidString(vendorDTO.getCity())) {
       	    errors.rejectValue("city", "Notcharcters");  
        }
        
        if(!errors.hasFieldErrors("pin") && !commonValidator.isValidPIN(String.valueOf(vendorDTO.getPin()))) {
       	    errors.rejectValue("pin", "NotPin");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson", "NotEmpty");
        if(!errors.hasFieldErrors("contactPerson") && !commonValidator.isValidString(vendorDTO.getContactPerson())) {
       	    errors.rejectValue("contactPerson", "Notcharcters");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if(!errors.hasFieldErrors("email") && !commonValidator.isEmail(vendorDTO.getEmail())) {
       	    errors.rejectValue("email", "NotValidEmail");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "NotEmpty");
       /* if(!errors.hasFieldErrors("website") && !commonValidator.isWebSite(String.valueOf(vendorDTO.getWebsite()))) {
       	    errors.rejectValue("website", "NotWebSite");  
        }
        */
        if(!errors.hasFieldErrors("mobile") && !commonValidator.isMobileNo(String.valueOf(vendorDTO.getMobile()))) {
       	    errors.rejectValue("mobile", "NotMobile");  
        }
        
        if(!errors.hasFieldErrors("phone") && !commonValidator.isNumaric(String.valueOf(vendorDTO.getPhone()))) {
       	    errors.rejectValue("phone", "NotNumaric");  
        }
        
        if(!errors.hasFieldErrors("fax") && !commonValidator.isNumaric(String.valueOf(vendorDTO.getFax()))) {
       	    errors.rejectValue("fax", "NotNumaric");  
        }
        
    }
}