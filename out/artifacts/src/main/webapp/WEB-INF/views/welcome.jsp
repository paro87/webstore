<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 10/01/2019
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%--JavaServer Pages (JSP) is a technology that lets you embed Java code inside HTML pages.
This code can be inserted by means of <% %> blocks or by means of JSTL tags.JSTL is just a standard tag library provided by Oracle. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="jumbotron">
    <h1> ${greeting} </h1>
    <p> ${tagline} </p>
</div>

