

<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
</head>
<body>
<header>
    <%@ include file="/CustomView/header.jsp"%>
</header>
<h3>${param.result}</h3>
<main>
    <div class="container">
        <div class="row">
            <div class="col s6 offset-s3">
                <div class="row">
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="updateEmployeePage.updateEmployee"/></h5>
                        </div>
                        <div class="row">
                            <form name="updateEmployee" method="post">
                                <input type="hidden" name="employeeID" value="${requestScope.employeeID}">
                                <table border="1">

                                    <tr>
                                        <td valign="top"><fmt:message key="updateEmployeePage.specialityRow"/></td>
                                        <td><input type="text" name="speciality" size="20" placeholder="speciality"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateEmployeePage.nameRow"/></td>
                                        <td><input type="text" name="employeeName" size="20" placeholder="name"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateEmployeePage.ordinalNumber"/></td>
                                        <td><input type="text" name="ordinalNumber" size="20" placeholder="number"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateEmployeePage.status"/></td>
                                        <td>
                                            <select class="browser-default" name="status">
                                                <option selected="selected" disabled="disabled">Empty</option>
                                                <option selected="selected">ready</option>
                                                <option selected="selected">not ready</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="updateEmployeePage.update"/>" formaction="/AdminPage/Employee?command=updateEmployee"></td>
                                    </tr>

                                </table>
                            </form>
                        </div>
                        <div class="row">
                            <div class="col s4 offset-s6">
                                <a class="waves-effect waves-light btn col s6 right" href="/AdminPage"><fmt:message key="updateEmployeePage.back"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
