package com.paro.validator;

import com.paro.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductImageValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        long allowedSize=10000;
        //Throw error if the productImage size is bigger than allowedSize
        if(product.getProductImage()!= null &&
                product.getProductImage().getSize()>allowedSize) {
            errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
        }

    }
}
