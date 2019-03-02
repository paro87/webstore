package com.paro.validator;

import com.paro.exception.CategoryRestriction;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidator implements ConstraintValidator<Category, String> {
    List<String> allowedCategories = new ArrayList<>();
    public CategoryValidator() {

        allowedCategories.add("Tablet");
        allowedCategories.add("Laptop");
        allowedCategories.add("Smartphone");

    }



    @Override
    public void initialize(Category constraintAnnotation) {

    }

    @Override
    public boolean isValid(String categoryField, ConstraintValidatorContext constraintValidatorContext) {

        try {
            //product = productService.getProductById(s);
            for(String category: allowedCategories){
                System.out.println(category);
                if (category.equals(categoryField))
                        return true;
            }

        } catch (CategoryRestriction e) {
            return false;
        }

        return false;
    }
}
