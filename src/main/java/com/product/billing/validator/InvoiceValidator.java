package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.InvoiceDTO;
import com.product.billing.dto.InvoiceItemDTO;

@Component
public class InvoiceValidator implements Validator {

	CommonValidator commonValidator=new CommonValidator();

    @Override
    public boolean supports(Class<?> aClass) {
        return InvoiceDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    	InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendor", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson", "NotEmpty");
        if(!errors.hasFieldErrors("contactPerson") && !commonValidator.isValidString(invoiceDTO.getContactPerson())) {
       	    errors.rejectValue("contactPerson", "Notcharcters");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceNo", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceDate", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        if(!errors.hasFieldErrors("address") && !commonValidator.isValidString(invoiceDTO.getContactPerson())) {
       	    errors.rejectValue("address", "Notcharcters");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discounttype", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discount", "NotEmpty");
        if(!errors.hasFieldErrors("discount") &&  invoiceDTO.getDiscount()<=0) {
       	    errors.rejectValue("discount", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "advance", "NotEmpty");
        if(!errors.hasFieldErrors("advance") && invoiceDTO.getAdvance()<0) {
       	    errors.rejectValue("advance", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roundOff", "NotEmpty");
        if(!errors.hasFieldErrors("roundOff") && invoiceDTO.getRoundOff()<=0) {
       	    errors.rejectValue("roundOff", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payableTotal", "NotEmpty");
        if(!errors.hasFieldErrors("payableTotal") && invoiceDTO.getPayableTotal()<=0) {
       	    errors.rejectValue("payableTotal", "NotPostive");  
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalamount", "NotEmpty");
        if(!errors.hasFieldErrors("totalamount") && invoiceDTO.getTotalamount()<=0) {
       	    errors.rejectValue("totalamount", "NotPostive");  
        }
        
        int index=0;
        for(InvoiceItemDTO invoiceItemDTO:invoiceDTO.getInvoiceItems()) {
        	
        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].particulars", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].amount", "NotEmpty");
            if(!errors.hasFieldErrors("invoiceItems["+index+"].amount") && invoiceItemDTO.getAmount()<=0) {
           	    errors.rejectValue("invoiceItems["+index+"].amount", "NotPostive");  
            }

        	if(Integer.parseInt(invoiceItemDTO.getProductType())==1) {
                 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].quantity", "NotEmpty");
                 if(!errors.hasFieldErrors("invoiceItems["+index+"].quantity") && invoiceItemDTO.getQuantity()<=0) {
                	    errors.rejectValue("invoiceItems["+index+"].quantity", "NotPostive");  
                 }
                 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].rate", "NotEmpty");
                 if(!errors.hasFieldErrors("invoiceItems["+index+"].rate") && invoiceItemDTO.getRate()<=0) {
                	    errors.rejectValue("invoiceItems["+index+"].rate", "NotPostive");  
                 }
        	}else if(Integer.parseInt(invoiceItemDTO.getProductType())==2){
        		switch(Integer.parseInt(invoiceItemDTO.getType())) {
        		
        			case 2 :	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].side", "NotEmpty");
			        			
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].length", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].length") && invoiceItemDTO.getLength()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].length", "NotPostive");  
        				        }
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].width", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].width") && invoiceItemDTO.getWidth()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].width", "NotPostive");  
        				        }
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].quantity", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].quantity") && invoiceItemDTO.getQuantity()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].quantity", "NotPostive");  
        				        }
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].rate", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].rate") && invoiceItemDTO.getRate()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].rate", "NotPostive");  
        				        }
        						break;
        			case 3 :    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].length", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].length") && invoiceItemDTO.getLength()<=0) {
        							errors.rejectValue("invoiceItems["+index+"].length", "NotPostive");  
        						}
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].width", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].width") && invoiceItemDTO.getWidth()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].width", "NotPostive");  
        				        }
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].quantity", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].quantity") && invoiceItemDTO.getQuantity()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].quantity", "NotPostive");  
        				        }
        						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].rate", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].rate") && invoiceItemDTO.getRate()<=0) {
        				       	    errors.rejectValue("invoiceItems["+index+"].rate", "NotPostive");  
        				        }
        						break;
        			default:    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceItems["+index+"].amount", "NotEmpty");
        						if(!errors.hasFieldErrors("invoiceItems["+index+"].amount") && invoiceItemDTO.getAmount()<=0) {
        							errors.rejectValue("invoiceItems["+index+"].amount", "NotPostive");  
        						}
        				     	break;
        		}
        	}
        	index++;
        }
        
    }
}