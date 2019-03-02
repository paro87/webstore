package com.paro.validator;

import com.paro.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
/*
In classic Spring validation, the main validation construct is the Validator
(org.springframework.validation.Validator) interface. The Spring Validator
interface defines two methods for validation purposes, namely supports and validate.
The supports method indicates whether the validator can validate a specific class. If so, the
validate method can be called to validate an object of that class.
 */
@Component
public class UnitsInStockValidator implements Validator{
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if(product.getUnitPrice()!= null &&
                new BigDecimal(1000).compareTo(product.getUnitPrice())<=0 &&
                product.getUnitsInStock()>99) {
            errors.rejectValue("unitsInStock", "com.packt.webstore.validator.UnitsInStockValidator.message");
        }
    }
}
