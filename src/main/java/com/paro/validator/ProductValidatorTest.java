package com.paro.validator;

import com.paro.config.WebApplicationContextConfig;
import com.paro.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import java.math.BigDecimal;
/*
If you remember correctly, you learned in the past that during the booting up of our
application, Spring MVC creates a web application context (Spring container) with the
necessary beans, as defined in the web application context configuration file. We need a
similar kind of context even before running our test classes, so that we can use those
defined beans (objects) in our test class to test them properly. The Spring Test framework
makes this possible via the @ContextConfiguration annotation.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class ProductValidatorTest {
    @Autowired
    private ProductValidator productValidator;
    @Test
    public void product_without_UnitPrice_should_be_invalid() {
    //Arrange
        Product product = new Product();
        BindException bindException = new BindException(product, " product");
    //Act
        ValidationUtils.invokeValidator(productValidator, product, bindException);
    //Assert
        Assert.assertEquals(3, bindException.getErrorCount());
        Assert.assertTrue(bindException.getLocalizedMessage().contains("Unit price is Invalid. It cannot be empty."));
    }
    @Test
    public void product_with_existing_productId_invalid() {
    //Arrange
        Product product = new Product("P1234","iPhone 5s", new BigDecimal(500));
        product.setCategory("Tablet");
        BindException bindException = new BindException(product, " product");
    //Act
        ValidationUtils.invokeValidator(productValidator, product, bindException);
    //Assert
        Assert.assertEquals(1, bindException.getErrorCount());
        Assert.assertTrue(bindException.getLocalizedMessage().contains("A product already exists with this product id."));
    }
    @Test
    public void a_valid_product_should_not_get_any_error_during_validation() {
    //Arrange
        Product product = new Product("P9876","iPhone 5s", new BigDecimal(500));
        product.setCategory("Tablet");
        BindException bindException = new BindException(product, " product");
    //Act
        ValidationUtils.invokeValidator (productValidator, product, bindException);
    //Assert
        Assert.assertEquals(0, bindException.getErrorCount());
    }
}
