package com.paro.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartTest {
    private Cart cart;
    private CartItem cartItem;
    List<CartItem> cartItems= new ArrayList<>();
    @Before
    public void setup(){
        cartItem = new CartItem("1");
        cart=new Cart("1");
    }

    @Test
    public void cart_grandTotalTest(){
        //Arrange
        Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
        cartItem.setProduct(iphone);
        cartItems.add(cartItem);

        //Act
        BigDecimal grandTotal=cart.getGrandTotal();

        //Assert
        Assert.assertEquals(iphone.getUnitPrice(), grandTotal);
    }
}
