<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 29/01/2019
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset="utf-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Customer</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Shipping</h1>
            <p>Shipping details</p>
        </div>
    </div>
</section>
<section class="container">
    <form:form modelAttribute="order.shippingDetail" class="form-horizontal">
    <fieldset>
        <legend>Shipping Details</legend>
        <div class="form-group">
            <label class="control-label col-lg-2 col-lg-2" for="name">Name</label>
            <div class="col-lg-10">
                <form:input id="name" path="name" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2 col-lg-2" for="shippingDate">Shipping Date (dd/mm/yyyy)</label>
            <div class="col-lg-10">
                <form:input id="shippingDate" path="shippingDate" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="doorNo">Door No</label>
            <div class="col-lg-10">
                <form:input id="doorNo" path="shippingAddress.doorNo" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="streetName">Street Name</label>
            <div class="col-lg-10">
                <form:input id="streetName" path="shippingAddress.streetName." type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="areaName">Area Name</label>
            <div class="col-lg-10">
                <form:input id="areaName" path="shippingAddress.areaName" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="state">State</label>
            <div class="col-lg-10">
                <form:input id="state" path="shippingAddress.state" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="country">Country</label>
            <div class="col-lg-10">
                <form:input id="country" path="shippingAddress.country" type="text" class="form:input-large" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="zipCode">Zip Code</label>
            <div class="col-lg-10">
                <form:input id="zipCode" path="shippingAddress.zipCode" type="text" class="form:input-large" />
            </div>
        </div>
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button id="back" class="btn btn-default" name="_eventId_backToCollectCustomerInfo">back</button>
                <input type="submit" id="btnAdd" class="btn btn-primary" value="Add" name="_eventId_shippingDetailCollected"/>
                <button id="btnCancel" class="btn btndefault" name="_eventId_cancel">Cancel</button>
            </div>
        </div>
    </fieldset>
    </form:form>
</section>
</body>
</html>
