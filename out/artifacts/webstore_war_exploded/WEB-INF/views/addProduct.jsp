<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 14/01/2019
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section>
    <div class="pull-right" style="padding-right:50px">
        <a href="?language=en" >English</a>|<a href="?language=tm" >Turkmen</a>
        <%--When the user clicks on one of these links, it will add a request parameter called language
to the URL with the corresponding locale value. For example, when we click on the English
link on the add products page at runtime, it will change the request URL
to http://localhost:8080/webstore/products/add?language=en; similarly if you
click on the Dutch link, it will change the request URL
to http://localhost:8080/webstore/products/add?language=nl.--%>
        <%--whatever value you have given to the language request parameter in the link should
match the translation message source file suffix. For example, in our case we have created a
Dutch translation message source file, messages_nl.properties; here the suffix is nl and
messages.properties without any suffix is considered the default for the en suffix.
That's why in step 2 we have given nl and en as the values for the language parameters
for Dutch and English, respectively:
<a href="?language=en" >English</a>|<a href="?language=nl" >Dutch</a>
if we do not give a language parameter in our URL, Spring will use the default
message source file (messages.properties) for translation; if we give a language
parameter, Spring will use that parameter value as the suffix to identify the correct
language message source file (messages_nl.properties).
--%>
        <a href="<c:url value="/logout" />">Logout</a>
    </div>
</section>

<section class="container">
    <form:form method="POST" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
        <%--enctype attribute indicates how the form data should be encoded when submitting it to the server--%>
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <%--Here we have used the “*” symbol for the path attribute, which that means we want to
        show all the errors and element attributes, just indicating which type of element Spring
        MVC should use to list all the errors.--%>
    <fieldset>
        <legend>Add new product</legend>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="productId"><spring:message code="addProduct.form.productId.label"/> </label>
            <div class="col-lg-10">
                <%--The path attribute just indicates the field name that is relative to the form backing bean. So
                the value that is entered in this input box at runtime will be bound to the corresponding
                field of the form bean. --%>
                <form:input id="productId" path="productId" type="text" class="form:input-large"/>
                <form:errors path="productId" cssClass="text-danger"/>
                <%--the path attribute value should be the same as the corresponding input tag. The path attribute is used to identify the field in the form bean to look for errors--%>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="name"><spring:message code="addProduct.form.name.label"/></label>
            <div class="col-lg-10">
                <form:input id="name" path="name" type="text" class="form:input-large"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="unitPrice"><spring:message code="addProduct.form.unitPrice.label"/></label>
            <div class="col-lg-10">
                <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                <form:errors path="unitPrice" cssClass="text-danger"/>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="manufacturer"><spring:message code="addProduct.form.manufacturer.label"/></label>
            <div class="col-lg-10">
                <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="category"><spring:message code="addProduct.form.category.label"/></label>
            <div class="col-lg-10">
                <form:input id="category" path="category" type="text" class="form:input-large"/>
                <form:errors path="category" cssClass="text-danger"/>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="unitsInStock"><spring:message code="addProduct.form.unitsInStock.label"/></label>
            <div class="col-lg-10">
                <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
                <form:errors path="unitsInStock" cssClass="text-danger"/>
            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2 col-lg-2" for="unitsInOrder"><spring:message code="addProduct.form.unitsInOrder.label"/></label>
            <div class="col-lg-10">

            </div>
        </div>
        <div class="form-group"><label class="control-label col-lg-2" for="description"><spring:message code="addProduct.form.description.label"/></label>
            <div class="col-lg-10">
                <form:textarea id="description" path="description" rows = "2"/>
            </div>
        </div>
        <div class="form-group"> <label class="control-label col-lg-2" for="discontinued"><spring:message code="addProduct.form.discontinued.label"/></label>

        </div>
        <div class="form-group"> <label class="control-label col-lg-2" for="condition"><spring:message code="addProduct.form.condition.label"/></label>
            <div class="col-lg-10">
                <form:radiobutton path="condition" value="New" />New
                <form:radiobutton path="condition" value="Old" />Old
                <form:radiobutton path="condition" value="Refurbished" />Refurbished
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-lg-2" for="productImage">
                <spring:message code="addProduct.form.productImage.label"/>
            </label>
            <div class="col-lg-10">
                    <form:input id="productImage" path="productImage" type="file" class="form:input-large" />
                <%--we want this form field to be bound with the domain object field; that's the reason we gave the path attribute as productImage. If you remember, this path name
is just the same MultipartFile reference name that we added in Product.java--%>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10"> <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/></div>
        </div>
    </fieldset>
    </form:form>
</section>

