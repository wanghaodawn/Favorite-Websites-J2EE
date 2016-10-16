<!-- Hao Wang (haow2) HW4 -->

<%@page import="java.util.*"%>
<%@page import="databean.UserBean"%>
<%@ page import="databean.FavoriteBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<form action="manage.do" method="POST">
    <table>
        <tr>
            <td>URL:</td>
            <td><input type="text" name="URL" value="${form.URL}"/></td>
        </tr>
        <tr>
            <td>Comment:</td>
                <td><input type="text" name="comment" value="${form.comment}"/></td>
            </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="action" value="Add"/></td>
        </tr>
    </table>
</form>
<br /><br /><br />
<ul>
    <c:forEach var="favoriteBean" items="${favoriteList}">
        <li>
            <p>Delete:</p>
            <form action="delete.do" method="POST">
                <input type="hidden" name="id" value="${favoriteBean.favoriteId}"/>
                <input type="submit" value="Delete" />
            </form>
            <a href="list.do?favoriteId=${favoriteBean.favoriteId}">
                ${favoriteBean.URL}
            </a>
            <p>${favoriteBean.comment}</p>
            <p>${favoriteBean.clickCount}&nbsp;Clicks</p>
        </li>
    </c:forEach>
</ul>
<jsp:include page="template-bottom.jsp"/>
