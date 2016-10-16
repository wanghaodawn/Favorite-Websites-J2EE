<!-- Hao Wang (haow2) HW4 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<p style="color: red">
    <c:forEach var="error" items="${errors}">
        ${error}
        <br/>
    </c:forEach>
</p>
