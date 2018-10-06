package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.POItemsDTO;
import com.product.billing.dto.PurchaseOrderDTO;

@Component
public class PurchaseOrderValidator implements Validator {

	CommonValidator commonValidator=new CommonValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return PurchaseOrderDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    	PurchaseOrderDTO purchaseOrderDTO = (PurchaseOrderDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendor", "NotEmpty");
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newCustomerName", "NotEmpty");
        if (!errors.hasFieldErrors("newCustomerName") && purchaseOrderDTO.getNewCustomerName().length() < 6 || purchaseOrderDTO.getNewCustomerName().length() > 32) {
            errors.rejectValue("newCustomerName", "Size636");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPersonName", "NotEmpty");
        if (!errors.hasFieldErrors("contactPersonName") && purchaseOrderDTO.getContactPersonName().length() < 6 || purchaseOrderDTO.getContactPersonName().length() > 32) {
            errors.rejectValue("contactPersonName", "Size636");
        }
        ;        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        if(!errors.hasFieldErrors("address") &&  !commonValidator.isValidString(purchaseOrderDTO.getAddress())) {
       	    errors.rejectValue("address", "Notcharcters");  
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceApprovedBy", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discount", "NotEmpty");
        if(!errors.hasFieldErrors("discount") && purchaseOrderDTO.getDiscount()<=0) {
       	    errors.rejectValue("discount", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discounttype", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "advance", "NotEmpty");
        if(!errors.hasFieldErrors("advance") && purchaseOrderDTO.getAdvance()<=0) {
       	    errors.rejectValue("advance", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roundOff", "NotEmpty");
        if(purchaseOrderDTO.getDiscount()<=0) {
       	    errors.rejectValue("roundOff", "NotPostive");  
        }
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payableTotal", "NotEmpty");
        if (purchaseOrderDTO.getPayableTotal()<=0) {
            errors.rejectValue("payableTotal", "NotPostive");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalamount", "NotEmpty");
        if (purchaseOrderDTO.getTotalamount()<=0) {
            errors.rejectValue("totalamount", "NotPostive");
        }
        
        int index=0;
        for(POItemsDTO poItemsDTO:purchaseOrderDTO.getPoitems()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poitems["+index+"].type", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poitems["+index+"].particulars", "NotEmpty");
         
            if(Integer.parseInt(poItemsDTO.getType())==1) {
            	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poitems["+index+"].sqFt", "NotEmpty");
	          	if(poItemsDTO.getSqFt()<=0) {
	              errors.rejectValue("poitems["+index+"].sqFt", "NotPostive");
	          	}
	          	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poitems["+index+"].rate", "NotEmpty");
	          	if(poItemsDTO.getRate()<=0) {
	        	  errors.rejectValue("poitems["+index+"].rate", "NotPostive");
	          	}
            }else{
	        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poitems["+index+"].amount", "NotEmpty");
	            if (poItemsDTO.getAmount()<=0) {
	                errors.rejectValue("poitems["+index+"].amount", "NotPostive");
	            }
            }
            index++;
        }
    }
}