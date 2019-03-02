package com.paro.controller;

import com.paro.domain.Cart;
import com.paro.dto.CartDto;
import com.paro.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
/*
Instead of putting the object into the Model, we
have returned the object itself. Why? Because we want to return the state of the object in
JSON format. Remember, REST-based web services should return data in JSON or XML
format and the client can use the data however they want; they may render it to an HTML
page, or they may send it to some external system as it is, as raw JSON/XML data.
The read Controller method is just returning Cart Java
objects; how come this Java object is being converted into JSON or XML format? This is
where the @RestController annotation comes in. The @RestController annotation
instructs Spring to convert all Java objects that are returned from the request-mapping
methods into JSON/XML format and send a response in the body of the HTTP response.
Similarly, when you send an HTTP request to a Controller method with JSON/XML data in
it, the @RequestBody annotation instructs Spring to convert it into the corresponding Java
object. That's why the create method has a cartDto parameter annotated with a
@RequestBody annotation.
 */
@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {
    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CartDto cartDto) {
        cartService.create(cartDto);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartService.read(cartId);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
        cartDto.setId(cartId);
        cartService.update(cartId, cartDto);
    }

    @RequestMapping(value = "/{cartId}", method =RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(value = "cartId") String cartId) {
        cartService.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void addItem(@PathVariable String productId, HttpSession session) {
        cartService.addItem(session.getId(), productId);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeItem(@PathVariable String productId, HttpSession session) {
        cartService.removeItem(session.getId(),productId);
    }
}
