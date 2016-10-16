<!-- Hao Wang (haow2) HW4 -->

<jsp:include page="template-top.jsp" />
<h3>Registration</h3>
<jsp:include page="error-list.jsp" />
        <form action="register.do" method="POST">
            <input type="hidden" name="redirect" value="${redirect}"/>
            <table>
                <tr>
                    <td>Email Address:</td>
                    <td><input type="text" name="emailAddress" value="${form.emailAddress}"/></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="firstName" value="${form.firstName}"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="lastName" value="${form.lastName}"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password" value=""/></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><input type="text" name="confirmPassword" value=""/></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="button" value="Register"/>
                    </td>
                </tr>
            </table>
        </form>
<jsp:include page="template-bottom.jsp"/>
