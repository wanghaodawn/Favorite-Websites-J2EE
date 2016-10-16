<!-- Hao Wang (haow2) HW4 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Photo Sharing Website</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<%@ page import="databean.UserBean" %>

<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="130" bgcolor="#99FF99">
            <img border="0" src="login2.jpg" height="75">
            <img border="0" src="login3.jpg" height="75"> </td>
        <td bgcolor="#99FF99">&nbsp;  </td>
        <td width="500" bgcolor="#99FF99">
            <h1 align="center">
                Favorite Website
			</h1>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	
	<tr>
		<td bgcolor="#99FF99" valign="top" height="500">
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
    <c:choose>
        <c:when test="${ (empty user) }">
				<span class="menu-item"><a href="login.do">Login</a></span><br/>
				<span class="menu-item"><a href="register.do">Register</a></span><br/>
    </c:when>
        <c:otherwise>
				<span class="menu-head">${user.firstName} ${user.lastName}</span><br/>
				<span class="menu-item"><a href="manage.do">Manage Your Links</a></span><br/>
				<span class="menu-item"><a href="changepassword.do">Change Password</a></span><br/>
				<span class="menu-item"><a href="logout.do">Logout</a></span><br/>
        </c:otherwise>
    </c:choose>
				<span class="menu-item">&nbsp;</span><br/>
				<span class="menu-head">Favorite Lists:</span><br/>
    <c:forEach var="u" items="${userList}">
			    <span class="menu-item">
					<a href="list.do?emailAddress=${u.emailAddress}">
						${u.firstName} ${u.lastName}
					</a>
				</span>
				<br/>
    </c:forEach>
			</p>
		</td>
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
