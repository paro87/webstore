<%--
  Created by IntelliJ IDEA.
  User: PAROS
  Date: 12/01/2019
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<section class="container">
    <div class="row"> <c:forEach items="${customers}" var="customer">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <div class="caption">
                    <h3>${customer.customerId}</h3>
                    <p>${customer.name}</p>
                    <p>${customer.address}</p>
                    <p>${customer.noOfOrdersMade}</p>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</section>


