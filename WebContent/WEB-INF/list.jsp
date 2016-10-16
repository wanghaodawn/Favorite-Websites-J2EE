<!-- Hao Wang (haow2) HW4 -->

<%@ page import="databean.FavoriteBean" %>
<%@ page import="databean.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />
<h3>The list of ${currUser.firstName} ${currUser.lastName}</h3>
<jsp:include page="error-list.jsp" />
    <table>
        <c:forEach var="favoriteBean" items="${favoriteList}">
            <tr>
                <td>URL:</td>
                <td>
                    <a href="list.do?favoriteId=${favoriteBean.favoriteId}">
                    ${favoriteBean.URL}</a>
                </td>
            </tr>
            <tr>
                <td>Comment:</td>
                <td>${favoriteBean.comment}</td>
            </tr>
            <tr>
                <td></td>
                <td>${favoriteBean.clickCount}&nbsp;Clicks</td>
            </tr>
        </c:forEach>
    </table>
<jsp:include page="template-bottom.jsp"/>
