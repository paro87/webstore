<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 13/01/2019
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section>
    <div class="pull-right" style="padding-right:50px">
        <a href="?language=en&id=${product.productId}" >English</a>|<a href="?language=tm&id=${product.productId}" >Turkmen</a>
    </div>
</section>

<section class="container" data-ng-app="cartApp">
    <div class="row">
        <div class="col-md-5">

            <img src="<c:url value="/img/${product.productId}.png"></c:url>" alt="image" style = "width:100%"/>
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong><spring:message code="product.itemCode.label"/> : </strong><span class="label label-warning"> ${product.productId}</span>
            </p>
            <p>
                <strong><spring:message code="product.manufacturer.label"/> : </strong> ${product.manufacturer}
            </p>
            <p>
                <strong><spring:message code="product.category.label"/> : </strong> ${product.category}
            </p>
            <p><strong><spring:message code="product.unitsInStock.label"/> : </strong> ${product.unitsInStock}
            </p>
            <h4>${product.unitPrice} USD</h4>
            <a href="<spring:url value="/market/products" />" class="btn btn-default">
                <span class="glyphicon-hand-left glyphicon"></span> back
            </a>
            <p data-ng-controller="cartCtrl">
                <a href="#" class="btn btn-warning btn-large" data-ng-click="addToCart('${product.productId}')"><span class="glyphicon-shopping-cart glyphicon"></span> Order Now</a>
                <a href="<spring:url value="/cart" />" class="btn btn-default"> <span class="glyphicon-hand-right glyphicon"></span> View Cart</a>
            </p>
        </div>
    </div>
</section>

