<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 16/01/2019
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Customers</title>
</head>
<body>

<section class="container">
    <form:form method="POST" modelAttribute="newCustomer" class="form-horizontal">
        <fieldset>
            <legend>Add new customer</legend>
            <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="customerId">Product Id</label>
                <div class="col-lg-10">
                        <%--The path attribute just indicates the field name that is relative to the form backing bean. So
                        the value that is entered in this input box at runtime will be bound to the corresponding
                        field of the form bean. for="..." and path="..." must be the same--%>
                    <form:input id="customerId" path="customerId" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="name">Customer Name</label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="address">Customer Address</label>
                <div class="col-lg-10">
                    <form:input id="address" path="address" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="noOfOrdersMade">Number of orders made</label>
                <div class="col-lg-10">
                    <form:input id="noOfOrdersMade" path="noOfOrdersMade" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10"> <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/></div>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>
