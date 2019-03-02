package com.paro.domain.repository.impl;

import com.paro.domain.Cart;
import com.paro.domain.CartItem;
import com.paro.service.ProductService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartMapper implements RowMapper<Cart> {
    private CartItemMapper cartItemMapper;
    private NamedParameterJdbcTemplate jdbcTempleate;
    public CartMapper(NamedParameterJdbcTemplate jdbcTempleate, ProductService productService) {
        this.jdbcTempleate = jdbcTempleate;
        cartItemMapper = new  CartItemMapper(productService);
    }
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("ID");
        Cart cart = new Cart(id);
        String SQL = String.format("SELECT * FROM CART_ITEM WHERE CART_ID = '%s'", id);
        List<CartItem> cartItems = jdbcTempleate.query(SQL, cartItemMapper);
        cart.setCartItems(cartItems);
        return cart;
    }
}
