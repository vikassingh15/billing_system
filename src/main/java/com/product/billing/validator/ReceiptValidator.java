package com.product.billing.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.product.billing.dto.ReceiptDTO;
import com.product.billing.dto.ReceiptItemsDTO;

@Component
public class ReceiptValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ReceiptDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReceiptDTO receiptDTO = (ReceiptDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendor", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymodetype", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receipNo", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiptDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newClientName", "NotEmpty");
        if (!errors.hasFieldErrors("newClientName") && receiptDTO.getNewClientName().length() < 6 || receiptDTO.getNewClientName().length() > 10) {
            errors.rejectValue("newClientName", "Size636");
        }
        
        int index=0;
        for(ReceiptItemsDTO receiptItemsDTO : receiptDTO.getReceiptItemsDTOs()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiptItemsDTOs["+index+"].invoiceItemId", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiptItemsDTOs["+index+"].status", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiptItemsDTOs["+index+"].receiptAmount", "NotEmpty");
            if (receiptItemsDTO.getReceiptAmount()<=0) {
                errors.rejectValue("receiptItemsDTOs["+index+"].receiptAmount", "NotPostive");
            }
            index++;
        }
      
    }
}