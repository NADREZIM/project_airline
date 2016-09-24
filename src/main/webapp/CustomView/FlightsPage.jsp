
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
        <div class="row">
            <div class="col s12">
                <div class="section">
                    <div class="row">
                        <h5><fmt:message key="flightPage.allFlights"/></h5>
                    </div>
                    <form name="getAllFlights" method="post">
                    <div class="row">
                        <div class="row">
                        <table class="bordered">
                            <tbody>
                            <tr>
                                <th>    </th>
                                <th><fmt:message key="flightPage.flightNumber"/></th>
                                <th><fmt:message key="flightPage.flightName"/></th>
                                <th><fmt:message key="flightPage.departure"/></th>
                                <th><fmt:message key="flightPage.destination"/></th>
                                <th><fmt:message key="flightPage.departureDate"/></th>
                                <th><fmt:message key="flightPage.pilot"/></th>
                                <th><fmt:message key="flightPage.pilot"/></th>
                                <th><fmt:message key="flightPage.stewardess"/></th>
                                <th><fmt:message key="flightPage.stewardess"/></th>
                                <th><fmt:message key="flightPage.navigator"/></th>
                                <th><fmt:message key="flightPage.radiomen"/></th>
                                <th><fmt:message key="flightPage.status"/></th>
                            </tr>
                            <c:forEach items="${requestScope.allFlights}" var="flight">
                                <tr>
                                    <input type="hidden" name="crewID" value="${flight.crewTeam.crewTeamID}"/>
                                    <td>
                                        <input class="radio-button-crutch" type="radio" name="flightID" value="${flight.flightNumber}" id="rdb">
                                        <label for="rdB"> </label>
                                    </td>
                                    <td><c:out value="${flight.flightNumber}"></c:out></td>
                                    <td><c:out value="${flight.flightName}"></c:out></td>
                                    <td><c:out value="${flight.pointOfDeparture}"></c:out></td>
                                    <td><c:out value="${flight.pointOfDestination}"></c:out></td>
                                    <td><c:out value="${flight.departureDate}"></c:out></td>
                                    <c:forEach items="${flight.crewTeam.crewTeam}" var="employee">
                                        <td><c:out value="${employee.name}"></c:out></td>
                                    </c:forEach>
                                    <td><c:out value="${flight.flightStatus}"></c:out></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </div>
                        <div class="row right">
                            <input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="flightPage.updateB"/>" formaction="/AdminUpdateFlight?command=loadEmployee"/>
                            <input type="submit" class="waves-effect waves-light btn" value="<fmt:message key="flightPage.deleteB"/>" formaction="/AdminPage/Flights?command=deleteFlight"/>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="section">
                    <div class="row">
                        <h5><fmt:message key="flightPage.search"/></h5>
                    </div>
                    <div class="row">
                            <div class="row">
                                <div class="col s5 col offset-s1">
                                    <a href="/AdminPage/Flights?command=sortByName" class=" col s12 waves-effect waves-light btn"><fmt:message key="flightPage.sortByNameB"/></a>
                                </div>
                                <div class="col s5 col offset-s1">
                                    <a href="/AdminPage/Flights?command=sortByDpt" class=" col s12 waves-effect waves-light btn"><fmt:message key="flightPage.sortByDeparture"/></a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s5 col offset-s1">
                                    <a href="/AdminPage/Flights?command=sortByNameAndNumber" class=" col s12 waves-effect waves-light btn"><fmt:message key="flightPage.sortByNameAndNumber"/></a>
                                </div>
                                <div class="col s5 col offset-s1">
                                        <a href="/AdminPage/Flights?command=sortByDestinationAndDeparture" class=" col s12 waves-effect waves-light btn"><fmt:message key="flightPage.sortByDestinationAndDeparture"/></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <table>
                                <tbody>
                                <form method="get" action="/AdminPage/Flights">
                                    <input type="hidden" name="command" value="flightsByDeparture">
                                    <tr>
                                        <td valign="top"><fmt:message key="flightPage.byDeparture"/></td>
                                        <td><input type="text" name="departure" size="20" placeholder="departure city"></td>
                                        <td><input type="submit" value="<fmt:message key="flightPage.get"/>"/></td>
                                    </tr>
                                </form>
                                <form method="get" action="/AdminPage/Flights">
                                    <input type="hidden" name="command" value="flightsByDestination">
                                    <tr>
                                        <td valign="top"><fmt:message key="flightPage.byDestination"/></td>
                                        <td><input type="text" name="destination" size="20" placeholder="destination city"></td>
                                        <td><input type="submit" value="<fmt:message key="flightPage.get"/>"/></td>
                                    </tr>
                                </form>
                                <form method="get" action="/AdminPage/Flights">
                                    <input type="hidden" name="command" value="flightsByDepartureDate">
                                    <tr>
                                        <td valign="top"><fmt:message key="flightPage.byDepartureDate"/></td>
                                        <td><input type="text" name="departureDate" size="20" placeholder="departure date"></td>
                                        <td><input type="submit" value="<fmt:message key="flightPage.get"/>"/></td>
                                    </tr>
                                </form>
                                <form method="get" action="/AdminPage/Flights">
                                    <input type="hidden" name="command" value="flightsByMainParameters">
                                    <tr>
                                        <td valign="top"><fmt:message key="flightPage.byMainParameters"/></td>
                                        <td><input type="text" name="mainDeparture" size="20" placeholder="departure"></td>
                                        <td><input type="text" name="mainDestination" size="20" placeholder="destination"></td>
                                        <td><input type="text" name="mainDepartureDate" size="20" placeholder="departure date"></td>
                                        <td><input type="submit" value="<fmt:message key="flightPage.get"/>"/></td>
                                    </tr>
                                </form>
                                </tbody>
                            </table>
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
</main>
</body>
</html>