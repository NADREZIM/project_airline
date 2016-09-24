
<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
    <title></title>
</head>
<body>
<header>
    <%@ include file="/CustomView/header.jsp"%>
</header>
<h3>${param.result}</h3>
<div class="container">
    <div class="row">
        <div class="col s12">
            <div class="section">
                <div class="row">
                    <h5><fmt:message key="requestPage.allRequests"/></h5>
                </div>
                <div class="row">
                    <form name="getAllRequests" method="post">
                    <table>
                        <tbody>
                        <tr>
                            <th><fmt:message key="requestPage.requestBody"/></th>
                            <th><fmt:message key="requestPage.flightNumber"/></th>
                            <th><fmt:message key="requestPage.requestSendDate"/></th>
                        </tr>
                        <c:forEach items="${requestScope.allRequests}" var="request">
                            <tr>
                                <td><c:out value="${request.requestBody}"></c:out></td>
                                <td><c:out value="${request.flightID}"></c:out></td>
                                <td><c:out value="${request.requestSendDate}"></c:out></td>
                                <td><input class="btn waves-effect" type="submit" value="<fmt:message key="requestPage.seeRequest"/>" formaction="/AdminPage/Requests/DetailInfo?command=baseUpload&requestID=${request.requestID}&flightID=${request.flightID}"></td>
                                <td><input class="btn waves-effect" type="submit" value="<fmt:message key="requestPage.rejectRequest"/>" formaction="/AdminPage/Requests?command=reject&requestID=${request.requestID}&flightID=${request.flightID}"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col s4 offset-s1">
            <a class="waves-effect waves-light btn col s6 right" href="/AdminPage"><fmt:message key="requestPage.back"/></a>
        </div>
    </div>
</div>



<p><a href="/AdminPage"></a></p>
</body>
</html>