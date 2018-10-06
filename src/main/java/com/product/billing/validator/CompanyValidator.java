package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.CompanyDTO;

@Component
public class CompanyValidator implements Validator {
	
	CommonValidator commonValidator=new CommonValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return CompanyDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
    	CompanyDTO companyDTO = (CompanyDTO) o;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "NotEmpty");
        if (!errors.hasFieldErrors("companyName") && companyDTO.getCompanyName().length() < 6 || companyDTO.getCompanyName().length() > 32) {
            errors.rejectValue("companyName", "Size636");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "webSite", "NotEmpty");
       
        if(!errors.hasFieldErrors("phone") && !commonValidator.isMobileNo(String.valueOf(companyDTO.getPhone()))) {
       	    errors.rejectValue("phone", "NotMobile");  
        }
        
    }
}