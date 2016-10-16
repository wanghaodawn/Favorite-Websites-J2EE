<!-- Hao Wang (haow2) HW4 -->

<jsp:include page="template-top.jsp" />
<h3>Login</h3>

<jsp:include page="error-list.jsp" />

        <form action="login.do" method="POST">
            <table>
                <tr>
                    <td>Email Address:</td>
                    <td><input type="text" name="emailAddress" value="${form.emailAddress}"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password" value=""/></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="button" value="Login"/></td>
                </tr>
            </table>
        </form>
<jsp:include page="template-bottom.jsp" />