package com.paro.validator;

import com.paro.domain.Product;
import com.paro.exception.ProductNotFoundException;
import com.paro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, String>{
    @Autowired
    private ProductService productService;
    public void initialize(ProductId constraintAnnotation) {
// intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
    }
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Product product;
        try {
            product = productService.getProductById(value);
        } catch (ProductNotFoundException e) {
            return true;
        }
        if(product!= null) {
            return false;
        }
        return true;
    }
}
