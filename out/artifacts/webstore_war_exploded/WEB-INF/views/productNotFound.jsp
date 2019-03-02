<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 19/01/2019
  Time: 08:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<section>
    <div class="container">
        <p>${url}</p>
        <p>${exception}</p>
    </div>
    <div class="container">
        <p>
            <a href="<spring:url value="/market/products" />" class="btn btn-primary"><span class="glyphicon-hand-left glyphicon"></span> products
            </a>
        </p>
    </div>
</section>

