package com.paro.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CartItemTest {
    private CartItem cartItem;
    @Before
    public void setup() {
        cartItem = new CartItem("1");
    }
    /*
    The @Test (org.junit.Test) annotation marks a particular method as
a test method so that the JUnit framework can treat that method as a test method and
execute it when we choose the Run As | JUnit Test option
     */
    @Test
    public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
        //Arrange
        Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
        cartItem.setProduct(iphone);
        //Act
        BigDecimal totalPrice = cartItem.getTotalPrice();
        //Assert
        Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
    }
}
