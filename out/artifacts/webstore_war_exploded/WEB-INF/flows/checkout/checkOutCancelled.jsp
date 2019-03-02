<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 29/01/2019
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset="utf-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Invalid cart </title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1 class="alert alert-danger">check out cancelled</h1>
            <p>Your Check out process cancelled! you may continue shopping..</p>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <p>
            <a href="<spring:url value="/market/products" />" class="btn btn-primary"><span class="glyphicon-hand-left glyphicon"></span> product</a>
        </p>
    </div>
</section>
</body>
</html>
