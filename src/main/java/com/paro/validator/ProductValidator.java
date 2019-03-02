package com.paro.validator;

import com.paro.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

/*
our aim was to combine Bean Validations and our Spring-based validation
(unitsInStockValidator) together, to create a common adapter validator called
ProductValidator. If you notice closely, the ProductValidator class is nothing but an
implementation of the regular Spring validator.
We have autowired our existing bean validator into the ProductValidator class through
the following line:
@Autowired
private javax.validation.Validator beanValidator;
 */
public class ProductValidator implements Validator{
    @Autowired
    private javax.validation.Validator beanValidator;
    private Set<Validator> springValidators;
    public ProductValidator() {
        springValidators = new HashSet<Validator>();
    }
    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }
    public void validate(Object target, Errors errors) {
        /*
        we used this beanValidator reference inside the validate method of the
ProductValidator class to validate all Bean Validation annotations
         */
        Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
        for(Validator validator: springValidators) {
            validator.validate(target, errors);
        }
    }
}
