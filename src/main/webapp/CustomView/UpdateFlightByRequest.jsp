
<html>
<head>
    <%@ include file="/CustomView/JSPF/head.jspf"%>
</head>
<body>
<header>
    <%@ include file="/CustomView/header.jsp"%>
    <script src="${pageContext.request.contextPath}/view/js/disMultiSelect.js"></script>
</header>
<h3>${param.result}</h3>
<main>
    <div class="container">
        <div class="row">
            <div class="col s6 offset-s3">
                <div class="row">
                    <div class="section">
                        <div class="row">
                            <h5><fmt:message key="updateFlightByRequest.updateFlight"/></h5>
                        </div>
                        <div class="row">
                            <form name="updateFlight" method="post">
                                <table border="1">
                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.flightNumber"/></td>
                                        <td valign="top"><input disabled="disabled"  value="${requestScope.flight.flightNumber}">
                                            <input type="hidden" name="flightNumber" value="${requestScope.flight.flightNumber}">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.flightName"/></td>
                                        <td valign="top"><input disabled="disabled" value="${requestScope.flight.flightName}">
                                            <input type="hidden" name="flightName" value="${requestScope.flight.flightName}">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.departure"/></td>
                                        <td valign="top"><input disabled="disabled" value="${requestScope.flight.pointOfDeparture}">
                                            <input type="hidden" name="flightDeparture" value="${requestScope.flight.pointOfDeparture}">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.destination"/></td>
                                        <td valign="top"><input disabled="disabled" value="${requestScope.flight.pointOfDestination}">
                                            <input type="hidden" name="flightDestination" value="${requestScope.flight.pointOfDestination}">
                                    </tr>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.departureDate"/></td>
                                        <td valign="top"><input disabled="disabled" value="${requestScope.flight.departureDate}">
                                            <input type="hidden" name="flightDateOfDeparture" value="${requestScope.flight.departureDate}">
                                    </tr>

                                    <c:forEach items="${requestScope.allAvailableEmployee}" var="speciality">
                                        <c:if test="${speciality.key eq 'allPilots'}">
                                            <c:set var="pilots" value="${speciality.value}"/>
                                        </c:if>
                                        <c:if test="${speciality.key eq 'allStewardess'}">
                                            <c:set var="stewardess" value="${speciality.value}"/>
                                        </c:if>
                                        <c:if test="${speciality.key eq 'allNavigators'}">
                                            <c:set var="navigators" value="${speciality.value}"/>
                                        </c:if>
                                        <c:if test="${speciality.key eq 'allRadioman'}">
                                            <c:set var="radiomen" value="${speciality.value}"/>
                                        </c:if>
                                    </c:forEach>

                                    <c:forEach items="${requestScope.specialityToEmployee}" var="speciality">

                                        <c:if test="${speciality.key eq 'pilot'}">
                                            <c:forEach items="${speciality.value}" var="employee">
                                                <c:if test="${employee eq null}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.pilot"/></td>
                                                        <td>
                                                            <select name="firstPilot" class="browser-default">
                                                                <option selected="selected" disabled="disabled">Empty</option>
                                                                <c:forEach items="${pilots}" var="pilot1">
                                                                    <option value="${pilot1}">
                                                                            ${pilot1}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${not empty employee}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.pilot"/></td>
                                                        <td valign="top"><input disabled="disabled" value="${employee.name}">
                                                            <input type="hidden" name="pilotName" value="${employee.name}">
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${speciality.key eq 'stewardess'}">
                                            <c:forEach items="${speciality.value}" var="employee">
                                                <c:if test="${employee eq null}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.stewardess"/></td>
                                                        <td>
                                                            <select name="firstStewardess" class="browser-default">
                                                                <option selected="selected" disabled="disabled">Empty</option>
                                                                <c:forEach items="${stewardess}" var="stewardess1">
                                                                    <option value="${stewardess1}">
                                                                            ${stewardess1}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${not empty employee}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.stewardess"/></td>
                                                        <td valign="top"><input disabled="disabled" value="${employee.name}">
                                                            <input type="hidden" name="stewardessName" value="${employee.name}">
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${speciality.key eq 'navigator'}">
                                            <c:forEach items="${speciality.value}" var="employee">
                                                <c:if test="${employee eq null}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.navigator"/></td>
                                                        <td>
                                                            <select name="navigator" class="browser-default">
                                                                <option selected="selected" disabled="disabled">Empty</option>
                                                                <c:forEach items="${navigators}" var="navigator">
                                                                    <option value="${navigator}">
                                                                            ${navigator}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${not empty employee}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.navigator"/></td>
                                                        <td valign="top"><input disabled="disabled" name="navigatorName" value="${employee.name}">
                                                            <input type="hidden" name="navigatorName" value="${employee.name}">

                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${speciality.key eq 'radiomen'}">
                                            <c:forEach items="${speciality.value}" var="employee">
                                                <c:if test="${employee eq null}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.radiomen"/></td>
                                                        <td>
                                                            <select name="radiomen" class="browser-default">
                                                                <option selected="selected" disabled="disabled">Empty</option>
                                                                <c:forEach items="${radiomen}" var="radiomen1">
                                                                    <option value="${radiomen1}">
                                                                            ${radiomen1}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${not empty employee}">
                                                    <tr>
                                                        <td valign="top"><fmt:message key="updateFlightByRequest.radiomen"/></td>
                                                        <td valign="top"><input disabled="disabled" value="${employee.name}">
                                                            <input type="hidden" name="radiomenName" value="${employee.name}">
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>

                                    <tr>
                                        <td valign="top"><fmt:message key="updateFlightByRequest.flightStatus"/></td>
                                        <td>
                                            <select name="flightStatus" class="browser-default">
                                                <option selected="selected" disabled="disabled">Empty</option>
                                                <option selected="selected">ready</option>
                                                <option selected="selected">not ready</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <input type="hidden" name="flightCrewTeamID" value="${requestScope.flight.crewTeam.crewTeamID}">
                                </table>
                                <input type="submit" value="<fmt:message key="updateFlightByRequest.update"/>" formaction="/AdminPage/Requests?command=updateFlightByRequest">
                            </form>
                        </div>
                        <div class="row">
                            <div class="col s4 offset-s6">
                                <a class="waves-effect waves-light btn col s6 right" href="/AdminPage"><fmt:message key="requestPage.back"/></a>
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
