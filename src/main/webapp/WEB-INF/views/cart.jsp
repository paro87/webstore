<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 25/01/2019
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="container" data-ng-app="cartApp">
    <div data-ng-controller="cartCtrl" data-ng-init="initCartId('${cartId}')">
        <div>
            <a class="btn btn-danger pull-left" data-ng-click="clearCart()"> <span class="glyphicon glyphicon-remove-sign"></span> Clear Cart</a>
            <a href="<spring:url value="/checkout?cartId=${cartId}"/>" class="btn btn-success pull-right"> <span class="glyphicon-shopping-cart glyphicon"></span> Check out </a>
        </div>
        <table class="table table-hover">
            <tr>
                <th>Product</th>
                <th>Unit price</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <tr data-ng-repeat="item in cart.cartItems">
                <td>{{item.product.productId}}-{{item.product.name}}</td>
                <td>{{item.product.unitPrice}}</td>
                <td>{{item.quantity}}</td>
                <td>{{item.totalPrice}}</td>
                <td><a href="#" class="label label-danger" data-ng-click="removeFromCart(item.product.productId)"> <span class="glyphicon glyphicon-remove"/></span> Remove</a></td>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th>Grand Total</th>
                <th>{{cart.grandTotal}}</th>
                <th></th>
            </tr>
        </table>
        <a href="<spring:url value="/market/products" />" class="btn btn-default"> <span class="glyphicon-hand-left glyphicon"></span> Continue shopping</a>
    </div>
</section>
