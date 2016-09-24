

<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
    <title></title>
</head>
<body>
<header>
    <%@ include file="/CustomView/header.jsp"%>
</header>
<main>
    <div class="container">
        <h3>${param.result}</h3>
        <div class="row">
            <div class="col s8 offset-s2">
                <div class="row">
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="employeePage.createEmployee"/></h5>
                        </div>
                        <div class="row">
                            <form name="createEmployee" method="post">
                                <table border="1">

                                    <tr>
                                        <td valign="top"><fmt:message key="employeePage.speciality"/></td>
                                        <td><input type="text" name="speciality" size="20" placeholder="speciality"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="employeePage.name"/></td>
                                        <td><input type="text" name="employeeName" size="20" placeholder="name"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="employeePage.ordinalNumber"/></td>
                                        <td><input type="text" name="ordinalNumber" size="20" placeholder="number"></td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="employeePage.create"/>" formaction="/AdminPage/Employee?command=createEmployee"></td>
                                    </tr>

                                </table>
                            </form>
                        </div>
                    </div>
                    <div class="divider"></div>

                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="employeePage.allEmployees"/></h5>
                        </div>
                        <div class="row">
                            <form name="allEmployees" method="post">
                                <table>
                                    <tbody>
                                    <tr>
                                        <th></th>
                                        <th><fmt:message key="employeePage.ID"/></th>
                                        <th><fmt:message key="employeePage.specialityRow"/></th>
                                        <th><fmt:message key="employeePage.nameRow"/></th>
                                        <th><fmt:message key="employeePage.ordinalNumberRow"/></th>
                                        <th><fmt:message key="employeePage.statusRow"/></th>

                                    </tr>
                                    <c:forEach items="${requestScope.allEmployees}" var="employee">
                                        <tr>
                                            <c:if test="${employee.status eq true}">
                                                <c:set var="status" value="on flight"/>
                                                <td></td>
                                                <td><c:out value="${employee.employeeID}"></c:out></td>
                                                <td><c:out value="${employee.specialty}"></c:out></td>
                                                <td><c:out value="${employee.name}"></c:out></td>
                                                <td><c:out value="${employee.ordinalNumber}"></c:out></td>
                                                <td><c:out value="${status}"></c:out></td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <c:if test="${employee.status eq false}">
                                                <td><input type="radio" class="radio-button-crutch" name="employeeID" value="${employee.employeeID}"></td>
                                                <c:set var="status" value="free"/>
                                                <td><c:out value="${employee.employeeID}"></c:out></td>
                                                <td><c:out value="${employee.specialty}"></c:out></td>
                                                <td><c:out value="${employee.name}"></c:out></td>
                                                <td><c:out value="${employee.ordinalNumber}"></c:out></td>
                                                <td><c:out value="${status}"></c:out></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col s4 col offset-s1">
                                        <input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="employeePage.delete"/>" formaction="/AdminPage/Employee?command=deleteEmployee"/>
                                    </div>
                                    <div class="col s4 col offset-s1">
                                        <input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="employeePage.update"/>" formaction="/AdminPage/Employee?command=drawForm"/>
                                    </div>
                                </div>
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
