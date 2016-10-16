<!-- Hao Wang (haow2) HW4 -->

<jsp:include page="template-top.jsp" />
<h3>Change Password</h3>
<jsp:include page="error-list.jsp" />

<form action="changepassword.do" method="POST">
    <table>
        <tr>
            <td>New Password:</td>
            <td><input type="password" name="password1" value=""/></td>
        </tr>
        <tr>
            <td>Confirm Passowrd:</td>
            <td><input type="password" name="password2" value=""/></td>
            </tr>
        <tr>
            <td><input type="submit" name="action" value="Change"/></td>
        </tr>
    </table>
</form>

<jsp:include page="template-bottom.jsp"/>
